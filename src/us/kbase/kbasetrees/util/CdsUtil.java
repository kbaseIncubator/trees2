package us.kbase.kbasetrees.util;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.forester.io.parsers.nhx.NHXFormatException;
import org.forester.io.parsers.nhx.NHXParser;
import org.forester.phylogeny.Phylogeny;

import us.kbase.kbasetrees.MSA;
import us.kbase.kbasetrees.Tree;
import us.kbase.kbasetrees.CdsImportTreeParameters;
import us.kbase.kbasetrees.exceptions.KBaseTreesException;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.cdmientityapi.CDMIEntityAPIClient;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;


/**
 * 
 */
public class CdsUtil {

	
	protected CDMIEntityAPIClient cdmi;
	
	public CdsUtil() {
		cdmi = new CDMIEntityAPIClient();
	}
	public CdsUtil(URL url) {
		cdmi = new CDMIEntityAPIClient(url);
	}
	
	
	protected void initParams(CdsImportTreeParameters p) {
		if(p.getLinkNodesToBestFeature()==null) {
			p.setLinkNodesToBestFeature(0L);
		}
		if(p.getLinkNodesToBestGenome()==null) {
			p.setLinkNodesToBestGenome(0L);
		}
		if(p.getLinkNodesToBestGenomeName()==null) {
			p.setLinkNodesToBestGenomeName(0L);
		}
		if(p.getLinkNodesToAllFeatures()==null) {
			p.setLinkNodesToAllFeatures(0L);
		} 
		if(p.getLinkNodesToAllGenomes()==null) {
			p.setLinkNodesToAllGenomes(0L);
		}
		if(p.getLinkNodesToAllGenomeNames()==null) {
			p.setLinkNodesToAllGenomeNames(0L);
		}
		if(p.getLoadAlignmentForTree()==null) {
			p.setLoadAlignmentForTree(0L);
		}
	}

	//if(p.getTargetWorkspaceId()==null && p.getTargetWorkspaceName()==null) {
	//	throw new KBaseTreesException("To import tree data to your workspace, you must specify either a workspace name or ID; you provided none.");
	//} else if(p.getTargetWorkspaceId()!=null && p.getTargetWorkspaceName()!=null) {
	//	throw new KBaseTreesException("To import tree data to your workspace, you must specify either a workspace name or ID; you provided both.");
	//}
	
	public List<TreeImportPackage> getTreesForImport(List<CdsImportTreeParameters> params, String wsNameOrId) throws KBaseTreesException {
		

		List<TreeImportPackage> treesForImport = new ArrayList<TreeImportPackage>(params.size());
		
		// (1) get the tree data
		List<String> tree_ids = new ArrayList<String>(params.size());
		for(CdsImportTreeParameters p:params) {
			tree_ids.add(p.getTreeId());
			initParams(p);
		}

		Map<String,TreeInCdsData> trees = null;
		try {
			trees = getTreeData(tree_ids,true,true,true,true);
		} catch (IOException e) {
			throw new KBaseTreesException("Error retriving tree data from the CDS ("+cdmi.getURL()+"): "+e.getMessage());
		} catch (JsonClientException e) {
			throw new KBaseTreesException("Error retriving tree data from the CDS ("+cdmi.getURL()+"): "+e.getMessage());
		}
		
		// (2) for each tree, get the requested node mappings
		for(CdsImportTreeParameters p:params) {
			TreeInCdsData tree = trees.get(p.getTreeId());
			if(tree!=null){
				boolean includeExemplarFeatures = false;
				boolean includeExemplarGenomes = false;
				boolean includeExemplarGenomeNames = false;
				if(p.getLinkNodesToBestFeature()>=1) {
					includeExemplarFeatures = true;
				}
				if(p.getLinkNodesToBestGenome()>=1) {
					includeExemplarGenomes = true;
				}
				if(p.getLinkNodesToBestGenomeName()>=1) {
					includeExemplarGenomeNames = true;
				}
				try {
					addNodeRefProtein(tree, includeExemplarFeatures, includeExemplarGenomes, includeExemplarGenomeNames);
					addBestFeatures(tree, includeExemplarFeatures, includeExemplarGenomes, includeExemplarGenomeNames);
				} catch (IOException e) {
					throw new KBaseTreesException("Error retriving tree node data from the CDS ("+cdmi.getURL()+", tree:"+p.getTreeId()+"): "+e.getMessage());
				} catch (JsonClientException e) {
					throw new KBaseTreesException("Error retriving tree node data from the CDS ("+cdmi.getURL()+", tree:"+p.getTreeId()+"): "+e.getMessage());
				}
				
				//(3) set the default label
				this.setDefaultLabel(tree,p.getDefaultLabel());
				
				System.out.println(tree);
				
				// (3) if this is set, we need to get the alignment as well
				TreeImportPackage tip = new TreeImportPackage();
				if(p.getLoadAlignmentForTree()>=1) {
					Map<String, MSAinCdsData> alnData;
					try {
						alnData = getAlignmentData(Arrays.asList(tree.getIsBuiltFrom_alignment_id()),true,true);
					} catch (JsonClientException e) {
						throw new KBaseTreesException("Error retriving alignment data from the CDS ("+cdmi.getURL()+", aln:"+tree.getIsBuiltFrom_alignment_id()+"): "+e.getMessage());
					} catch (IOException e) {
						throw new KBaseTreesException("Error retriving alignment data from the CDS ("+cdmi.getURL()+", aln:"+tree.getIsBuiltFrom_alignment_id()+"): "+e.getMessage());
					} 
					MSAinCdsData msaData = alnData.get(tree.getIsBuiltFrom_alignment_id());
					
					msaData.getMsa().setKbRefs(tree.getTree_kb_refs());
					msaData.getMsa().setDefaultRowLabels(tree.getDefaultNodeLabels());
					String wsAlnName = tree.getIsBuiltFrom_alignment_id();
					if(p.getWsAlignmentName()!=null) {
						if(!p.getWsAlignmentName().isEmpty()) {
							wsAlnName = p.getWsAlignmentName();
						}
					}
					tip.setMsa(msaData.getMsa(),wsAlnName, msaData.getMetaData(), Arrays.asList(msaData.getProv()));
					System.out.println(msaData.getMsa());
				}
				
				// create the tree object, tree
				Tree treeForImport = new Tree()
										.withTree(tree.getTree_newick())
										.withTreeAttributes(tree.getHasTreeAttribute())
										.withType(tree.getTree_data_type())
										.withName(tree.getTree_id())
										.withKbRefs(tree.getTree_kb_refs())
										.withDefaultNodeLabels(tree.getDefaultNodeLabels());
										
				ProvenanceAction treeProv = new ProvenanceAction()
										.withDescription("Imported from KBase CDS ("+p.getTreeId()+"), built with protocol: "+tree.getTree_protocol())
										.withMethod(tree.tree_method)
										.withMethodParams(Arrays.asList(new UObject(tree.getTree_parameters())))
										.withService("KBaseTrees")
										.withInputWsObjects(Arrays.asList(wsNameOrId+"/"+tip.getMsaWsName()));
				
				String wsTreeName = tree.getTree_id();
				if(p.getWsTreeName()!=null) {
					if(!p.getWsTreeName().isEmpty()) {
						wsTreeName = p.getWsTreeName();
					}
				}
				Map<String,String> md = computeTreeMetadata(treeForImport);
				tip.setTreeData(treeForImport, wsTreeName, md, Arrays.asList(treeProv));
				
				
			} else {
				throw new KBaseTreesException("Error: no tree data from the CDS ("+cdmi.getURL()+") for tree '"+p.getTreeId()+"' found.");
			}
			
		}
		
		// (3) actually return the trees
		
		return treesForImport;
	}
	
	
	private Map<String, String> computeTreeMetadata(Tree treeForImport) throws KBaseTreesException {
		Map<String,String> metadata = new HashMap<String,String>();
		for(Entry<String,String> attribute : treeForImport.getTreeAttributes().entrySet()) {
			metadata.put(attribute.getKey(), attribute.getValue());
		}
		
		NHXParser parser = new NHXParser();
		Phylogeny[] trees;
		try {
			parser.setSource(treeForImport.getTree());
			trees = parser.parse();
		} catch (NHXFormatException e) {
			throw new KBaseTreesException("Error: no tree data from the CDS ("+cdmi.getURL()+") for tree '"+treeForImport.getName()+"' could not be parsed. Details: " + e.getMessage());
		} catch (IOException e) {
			throw new KBaseTreesException("Error: no tree data from the CDS ("+cdmi.getURL()+") for tree '"+treeForImport.getName()+"' could not be parsed. Details: " + e.getMessage());
		}
		int leafCount=0; int nodeCount=0;
		for(int k=0; k<trees.length; k++) {
			leafCount += trees[k].getNumberOfExternalNodes();
			nodeCount += trees[k].getNodeCount();
		}
		metadata.put("Leaf Count", Long.toString(leafCount));
		metadata.put("Node Count", Long.toString(nodeCount));
		return null;
	}
	public Map<String,TreeInCdsData> getTreeData(
									List<String>tree_ids,
									boolean withNewick,
									boolean withAlnId,
									boolean withSource,
									boolean withTreeAttributes) throws IOException, JsonClientException {
		
		// (0) set up the return structure
		Map<String, TreeInCdsData> trees = new HashMap<String,TreeInCdsData>(tree_ids.size());
		
		// (1) get the trees
		String objectNames        = "Tree";
		String filterClause       = "Tree(id) in "+getInClause(tree_ids.size());
		List<String> queryParams  = tree_ids;
		String fields             = "Tree(id) Tree(data-type) Tree(timestamp) Tree(method) Tree(parameters) Tree(protocol) Tree(source-id)";
		if(withNewick) {fields += " Tree(newick)";}
		
		List<List<String>> tree_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
		for(List<String> row: tree_rows) {
			String newick = null;
			if(withNewick) { newick = row.get(7); }
			trees.put(row.get(0), new TreeInCdsData(
					row.get(0),
					row.get(1),
					row.get(2),
					row.get(3),
					row.get(4),
					row.get(5),
					row.get(6),
					newick));
		}
		
		// (2) get optional tree attribute information
		if(withTreeAttributes) {
			objectNames        = "HasTreeAttribute";
			filterClause       = "HasTreeAttribute(from-link) in "+getInClause(tree_ids.size());
			fields             = "HasTreeAttribute(from-link) HasTreeAttribute(to-link) HasTreeAttribute(value)";
			List<List<String>> attribute_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
			for(List<String> row: attribute_rows) {
				trees.get(row.get(0)).addTreeAttribute(row.get(1), row.get(2));
			}
		}
		
		// (3) get optional is built from alignment information
		if(withAlnId) {
			objectNames        = "IsBuiltFromAlignment";
			filterClause       = "IsBuiltFromAlignment(from-link) in "+getInClause(tree_ids.size());
			fields             = "IsBuiltFromAlignment(from-link) IsBuiltFromAlignment(to-link)";
			List<List<String>> isBuiltFrom_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
			for(List<String> row: isBuiltFrom_rows) {
				trees.get(row.get(0)).setIsBuiltFrom_alignment_id(row.get(1));
			}
		}
		
		// (4) get optional source information
		if(withSource) {
			objectNames        = "IsTreeFrom";
			filterClause       = "IsTreeFrom(from-link) in "+getInClause(tree_ids.size());
			fields             = "IsTreeFrom(from-link) IsTreeFrom(to-link)";
			List<List<String>> isTreeFrom_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
			for(List<String> row: isTreeFrom_rows) {
				trees.get(row.get(0)).setIsTreeFrom_source(row.get(1));
			}
		}
		
		return trees;
	}
	
	public Map<String,MSAinCdsData> getAlignmentData(List<String> alnIds, boolean withSource, boolean withAttributes) throws IOException, JsonClientException {
		
		if(alnIds==null) return new HashMap<String,MSAinCdsData>();
		Map<String, MSAinCdsData> msas = new HashMap<String,MSAinCdsData>(alnIds.size());
		if(alnIds.size()>0) {
			// (1) get the basic alignment data
			String objectNames        = "Alignment";
			String filterClause       = "Alignment(id) in "+getInClause(alnIds.size());
			List<String> queryParams  = alnIds;
			String fields             = "Alignment(id) Alignment(n-cols) Alignment(is-concatenation) "
											+ "Alignment(sequence-type) Alignment(method) Alignment(parameters) Alignment(protocol) Alignment(source-id)";
			
			List<List<String>> aln_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
			for(List<String> row: aln_rows) {
				
				MSA msa = new MSA()
							.withName(row.get(0)) //id
							.withAlignmentLength(Long.parseLong(row.get(1))) //n-cols
							.withSequenceType(row.get(3)) // sequence-type
							.withAlignmentAttributes(new HashMap<String,String>())
							.withAlignment(new HashMap<String,String>()); 
				ProvenanceAction prov = new ProvenanceAction()
							.withDescription("Imported from KBase CDS ("+row.get(0)+"), built with protocol: "+row.get(6))
							.withMethod(row.get(4))
							.withMethodParams(Arrays.asList(new UObject(row.get(5))))
							.withService("KBaseTrees");
				
				MSAinCdsData data = new MSAinCdsData(msa,prov);
				msas.put(row.get(0),data);
			}
			
			if(withSource) {
				objectNames        = "WasAlignedBy";
				filterClause       = "WasAlignedBy(from-link) in "+getInClause(alnIds.size());
				fields             = "WasAlignedBy(from-link) WasAlignedBy(to-link)";
				List<List<String>> isTreeFrom_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				for(List<String> row: isTreeFrom_rows) {
					msas.get(row.get(0)).addMetaData("original-source",row.get(1));
				}
			}
			if(withAttributes) {
				objectNames        = "HasAlignmentAttribute";
				filterClause       = "HasAlignmentAttribute(from-link) in "+getInClause(alnIds.size());
				fields             = "HasAlignmentAttribute(from-link) HasAlignmentAttribute(to-link) HasAlignmentAttribute(value)";
				List<List<String>> attribute_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				for(List<String> row: attribute_rows) {
					msas.get(row.get(0)).getMsa().getAlignmentAttributes().put(row.get(1), row.get(2));
				}
			}
			
			// now we need to get the alignment rows
			objectNames        = "IncludesAlignmentRow AlignmentRow";
			filterClause       = "IncludesAlignmentRow(from-link) in (?)";
			fields             = "IncludesAlignmentRow(from-link) AlignmentRow(row-id) AlignmentRow(beg-pos-aln) AlignmentRow(end-pos-aln) AlignmentRow(sequence)";
			for(Entry<String,MSAinCdsData> alignment : msas.entrySet()) {
				queryParams        = Arrays.asList(alignment.getKey());
				List<List<String>> attribute_rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				for(List<String> row: attribute_rows) {
					//  TODO add trim info to alignment
					msas.get(row.get(0)).getMsa().getAlignment().put(row.get(1), row.get(4));
				}
			}
			
		}
		return msas;
	}
	
	public void addNodeRefProtein(
			TreeInCdsData tree,
			boolean includeExemplarFeatures,
			boolean includeExemplarGenomes,
			boolean includeExemplarGenomeNames) throws IOException, JsonClientException {
		
		String alnId = tree.getIsBuiltFrom_alignment_id();
		if(alnId!=null) {
			if(!alnId.trim().isEmpty()) {
				
				String objectNames        = "IncludesAlignmentRow AlignmentRow ContainsAlignedProtein";
				String filterClause       = "IncludesAlignmentRow(from-link) in (?)";
				List<String> queryParams  = Arrays.asList(alnId);
				String fields             = "AlignmentRow(row-id) ContainsAlignedProtein(to-link)";
				
				if(includeExemplarFeatures || includeExemplarGenomes || includeExemplarGenomeNames) {
					fields += " ContainsAlignedProtein(kb-feature-id)";
				}
				
				List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				List<String> nodeIdList = null; // list of row ID / feature ID pair 
				List<String> featureIdList = null; // list of row ID / feature ID pair 
				boolean runCleanup = false;
				if(includeExemplarGenomes || includeExemplarGenomeNames) { 
					nodeIdList = new ArrayList<String>(rows.size());
					featureIdList = new ArrayList<String>(rows.size());
					runCleanup = true;
				}
				for(List<String> row: rows) {
					tree.addKbRef(row.get(0), "p", row.get(1));
					if(includeExemplarFeatures) {
						if(!row.get(2).equals("")) {
							tree.addKbRef(row.get(0), "f", row.get(2));
						}
					}
					if(includeExemplarGenomes || includeExemplarGenomeNames) {
						if(!row.get(2).isEmpty()) {
							nodeIdList.add(row.get(0));
							featureIdList.add(row.get(2));
						}
					}
				}
				
				if(includeExemplarGenomes || includeExemplarGenomeNames) {
					Map<String,FeatureDataBasic> featureData = getFeatureDataBasic(
							featureIdList, 
							includeExemplarGenomes,
							includeExemplarGenomeNames,
							false);
					
					for(int k=0; k<nodeIdList.size(); k++) {
						FeatureDataBasic f = featureData.get(featureIdList.get(k));
						if(f!=null) {
							if(includeExemplarGenomes) {
								tree.addKbRef(nodeIdList.get(k), "g", f.getGenomeId());
							}
							if(includeExemplarGenomeNames) {
								tree.addKbRef(nodeIdList.get(k), "s", f.getGenomeScientificName());
							}
						}
					}
				}
				
				// we need to clean up because the canonical feature ids sometimes no longer exist! arg!!!
				if(runCleanup) {
					removeInvalidFeatures(tree,nodeIdList,featureIdList);
				}
			}
		}
	}
	
	/* if a node is marked as having in invalid feature in the list of features, then the entire
	 * feature list for the specified node is booted!
	 */
	protected void removeInvalidFeatures(
			TreeInCdsData tree,
			List<String> node_ids,
			List<String> feature_ids) throws IOException, JsonClientException {
		List <String> invalidFeatureNodes = flagInvalidFeatureNodes(node_ids, feature_ids);
		for(String f : invalidFeatureNodes) {
			tree.removeKbRefOfType(f,"f");
		}
	}
	
	protected List<String> flagInvalidFeatureNodes(List<String> node_ids, List<String> feature_ids) throws IOException, JsonClientException {
		if(feature_ids.size()==0) {
			return new ArrayList<String>();
		}
		String objectNames        = "Feature";
		String filterClause       = "Feature(id) in "+getInClause(feature_ids.size());
		List<String> queryParams  = feature_ids;
		String fields             = "Feature(id)";
		List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
		
		Map<String,String> featureLookup = new HashMap<String,String>(rows.size());
		List<String> invalidList = new ArrayList<String>();
		for(List<String> row: rows) {
			featureLookup.put(row.get(0), "");
			//System.out.println("found:"+row.get(0));
		}
		for(int k=0;k<node_ids.size();k++) {
			if(feature_ids.get(k).isEmpty()) continue;
			if(!featureLookup.containsKey(feature_ids.get(k))) {
				invalidList.add(node_ids.get(k));
				//System.out.println("adding invalid node:"+feature_ids.get(k)+" found in "+node_ids.get(k));
			}
		}
		return invalidList;
	}
	
	/**
	 * This method assumes you have already added protein md5 info to the tree data.
	 */
	public void addBestFeatures(
			TreeInCdsData tree,
			boolean includeFeature,
			boolean includeGenomes,
			boolean includeGenomeNames) throws IOException, JsonClientException {
		
		// 1) make sure that there is an alignment we can use to get the data
		String alnId = tree.getIsBuiltFrom_alignment_id();
		if(alnId!=null) {
			if(!alnId.trim().isEmpty()) {
				
				// 2) get the list of protein sequences
				Map<String, List<String>> protRefLookup = null;
				if(includeFeature) {
					protRefLookup = tree.getProtRefs("f");
				} else if(includeGenomes) {
					protRefLookup = tree.getProtRefs("g");
				} else if(includeGenomeNames) {
					protRefLookup = tree.getProtRefs("s");
				}
				if(protRefLookup==null) { return; }
				if(protRefLookup.size()==0) { return; }
				
				Set<String> protMd5sSet =  protRefLookup.keySet();
				List<String> protMd5s = new ArrayList<String>(protMd5sSet.size());
				protMd5s.addAll(protMd5sSet);
				
				// 3) query to get features for every protein sequence
				String objectNames        = "IsProteinFor";
				String filterClause       = "IsProteinFor(from-link) in "+getInClause(protMd5s.size());
				List<String> queryParams  = protMd5s;
				String fields             = "IsProteinFor(from-link) IsProteinFor(to-link)";
				List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				
				List<String> featureIds = new ArrayList<String>(protMd5s.size());
				Map<String,String> foundFeature = new HashMap<String,String>(protMd5s.size());
				for(List<String> row: rows) {
					if(foundFeature.containsKey(row.get(0))) continue;
					foundFeature.put(row.get(0),row.get(1));
					featureIds.add(row.get(1));
				}
				
				// 4) get the actual feature info
				if(featureIds.size()==0) { return; }
				Map<String,FeatureDataBasic> featuredata = getFeatureDataBasic(featureIds,includeGenomes,includeGenomeNames,false);
				
				// 5) set the info in the tree data
				Iterator<Entry<String, String>> it = foundFeature.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, String> pair = it.next();
					List<String> nodeIdsToUpdate = protRefLookup.get(pair.getKey());
					String featureId = pair.getValue();
					for(String nodeId : nodeIdsToUpdate) {
						if(includeFeature) {
							tree.addKbRef(nodeId, "f", featureId);
						}
						FeatureDataBasic fd= featuredata.get(featureId);
						if(fd!=null) {
							if(includeGenomes) {
								String genomeId = fd.getGenomeId();
								if(genomeId!=null) {
									tree.addKbRef(nodeId, "g", genomeId);
								}
							} 
							if(includeGenomeNames) {
								String genomeName = fd.getGenomeScientificName();
								if(genomeName!=null) {
									tree.addKbRef(nodeId, "s", genomeName);
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	
	/**
	 * label type should be one of 'p', 'f', 'g', 's'
	 */
	public void setDefaultLabel(TreeInCdsData tree, String labelType) {
		if(labelType==null) { return; }
		String selector = "p";
		if(labelType.toLowerCase().equals("p") || labelType.toLowerCase().equals("protein")) {
			selector = "p";
		} else if(labelType.toLowerCase().equals("f") || labelType.toLowerCase().equals("feature")) {
			selector = "f";
		} else if(labelType.toLowerCase().equals("g") || labelType.toLowerCase().equals("genome")) {
			selector = "g";
		} else if(labelType.toLowerCase().equals("s") || labelType.toLowerCase().equals("genomename")) {
			selector = "s";
		} else {
			return;
		}
		
		Map<String, Map<String, List<String>>> nodeRefData = tree.getTree_kb_refs();
		for(Entry<String, Map<String, List<String>>> node : nodeRefData.entrySet()) {
			List <String> refs = node.getValue().get(selector);
			if(refs!=null) {
				if(refs.size()>=1) {
					tree.setDefaultNodeLabel(node.getKey(), refs.get(0));
				}
			}
		}
	}
	
	
	
	public Map<String,FeatureDataBasic> getFeatureDataBasic(List<String>featureIds, 
			boolean getGenomeId,
			boolean getGenomeName,
			boolean getFunction) throws IOException, JsonClientException {
		
		String objectNames        = "IsOwnedBy";
		String filterClause       = "IsOwnedBy(from-link) in "+getInClause(featureIds.size());;
		List<String> queryParams  = featureIds;
		String fields             = "IsOwnedBy(from-link)";
		
		if(getGenomeId || getGenomeName || getFunction) {
			fields += " IsOwnedBy(to-link)";
		}
		if(getGenomeName || getFunction) {
			objectNames += " Genome";
			fields += " Genome(scientific-name)";
		}
		if(getFunction) {
			objectNames = "Feature "+objectNames;
			fields += " Feature(function)";
		}
		Map<String,FeatureDataBasic> result = new HashMap<String,FeatureDataBasic>(featureIds.size());
		List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
		for(List<String> row: rows) {
			FeatureDataBasic f = new FeatureDataBasic();
			if(getGenomeId) { f.genomeId = row.get(1); }
			if(getGenomeName) { f.genomeScientificName = row.get(2); }
			if(getFunction) { f.function = row.get(3); }
			result.put(row.get(0), f);
		}
		return result;
	}
	
	
	protected String getInClause(int n_records) {
		StringBuilder b = new StringBuilder();
		b.append("(");
		for(int i=0;i<n_records;i++) {
			if(i!=0) { b.append(","); }
			b.append("?");
		}
		b.append(")");
		return b.toString();
	}
	
	public static void main(String[] args) throws KBaseTreesException, IOException, JsonClientException {
		CdsUtil cdsUtil = new CdsUtil();
		List<CdsImportTreeParameters> params = Arrays.asList(
				new CdsImportTreeParameters()
					.withTreeId("kb|tree.1000500")
					//.withTreeId("kb|tree.992714")
					.withLinkNodesToBestFeature(1L)
					.withLinkNodesToBestGenome(1L)
					.withLinkNodesToBestGenomeName(1L)
					.withDefaultLabel("f")
					.withLoadAlignmentForTree(1L)
				//,new CdsImportTreeParameters().withTreeId("kb|tree.1000001")
				);
		cdsUtil.getTreesForImport(params,"myws");
	}
	
}
