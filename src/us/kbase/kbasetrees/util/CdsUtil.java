package us.kbase.kbasetrees.util;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.kbase.kbasetrees.Tree;
import us.kbase.kbasetrees.CdsImportTreeParameters;
import us.kbase.kbasetrees.exceptions.KBaseTreesException;
import us.kbase.cdmientityapi.CDMIEntityAPIClient;
import us.kbase.common.service.JsonClientException;


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
	}

	//if(p.getTargetWorkspaceId()==null && p.getTargetWorkspaceName()==null) {
	//	throw new KBaseTreesException("To import tree data to your workspace, you must specify either a workspace name or ID; you provided none.");
	//} else if(p.getTargetWorkspaceId()!=null && p.getTargetWorkspaceName()!=null) {
	//	throw new KBaseTreesException("To import tree data to your workspace, you must specify either a workspace name or ID; you provided both.");
	//}
	
	public void getTreesForImport(List<CdsImportTreeParameters> params) throws KBaseTreesException {
		
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
			if(trees.containsKey(p.getTreeId())){
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
					addNodeRefProtein(trees.get(p.getTreeId()), includeExemplarFeatures, includeExemplarGenomes, includeExemplarGenomeNames);
				} catch (IOException e) {
					throw new KBaseTreesException("Error retriving tree node data from the CDS ("+cdmi.getURL()+", tree:"+p.getTreeId()+"): "+e.getMessage());
				} catch (JsonClientException e) {
					throw new KBaseTreesException("Error retriving tree node data from the CDS ("+cdmi.getURL()+", tree:"+p.getTreeId()+"): "+e.getMessage());
				}
				
				System.out.println(trees.get(p.getTreeId()));
			} else {
				throw new KBaseTreesException("Error: no tree data from the CDS ("+cdmi.getURL()+") for tree '"+p.getTreeId()+"' found.");
			}
			
		}
		
		//return null;
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
				
				if(includeExemplarFeatures) {
					fields += " ContainsAlignedProtein(kb-feature-id)";
				}
				
				List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				List<String> nodeIdList = null; // list of row ID / feature ID pair 
				List<String> featureIdList = null; // list of row ID / feature ID pair 
				if(includeExemplarGenomes || includeExemplarGenomeNames) { 
					nodeIdList = new ArrayList<String>(rows.size());
					featureIdList = new ArrayList<String>(rows.size());
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
				removeInvalidFeatures(tree,nodeIdList,featureIdList);
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
		String objectNames        = "Feature";
		String filterClause       = "Feature(id) in "+getInClause(feature_ids.size());
		List<String> queryParams  = feature_ids;
		String fields             = "Feature(id)";
		List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
		
		Map<String,String> featureLookup = new HashMap<String,String>(rows.size());
		List<String> invalidList = new ArrayList<String>();
		for(List<String> row: rows) {
			featureLookup.put(row.get(0), "");
			System.out.println("found:"+row.get(0));
		}
		for(int k=0;k<node_ids.size();k++) {
			if(feature_ids.get(k).isEmpty()) continue;
			if(!featureLookup.containsKey(feature_ids.get(k))) {
				invalidList.add(node_ids.get(k));
				System.out.println("adding invalid node:"+feature_ids.get(k)+" found in "+node_ids.get(k));
			}
		}
		return invalidList;
	}
	
	
	/*public void addMissingFeatures(
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
				
				if(includeExemplarFeatures) {
					fields += " ContainsAlignedProtein(kb-feature-id)";
				}
				
				List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				List<String> nodeIdList = null; // list of row ID / feature ID pair 
				List<String> featureIdList = null; // list of row ID / feature ID pair 
				if(includeExemplarGenomes || includeExemplarGenomeNames) { 
					nodeIdList = new ArrayList<String>(rows.size());
					featureIdList = new ArrayList<String>(rows.size());
				}
				for(List<String> row: rows) {
					tree.addKbRef(row.get(0), "p", row.get(1));
					if(includeExemplarFeatures) {
						tree.addKbRef(row.get(0), "f", row.get(2));
					}
					if(includeExemplarGenomes || includeExemplarGenomeNames) {
						nodeIdList.add(row.get(0));
						featureIdList.add(row.get(2));
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
			}
		}
	}*/
	
	
	
	public void addNodeDataExemplarFeature(boolean includeExamplarGenome, boolean includeGenomeName) {
		
	}
	
	
	public void addNodeDataOneFeature( boolean includeGenome, boolean includeGenomeName) {
		
	}
	
	
	public void setDefaultLabel(String key) {
		
	}
	
	
	public void getTreeNodeData(
			TreeInCdsData tree,
			int levelOfDetail
				) throws KBaseTreesException, IOException, JsonClientException {
		
		
		String alnId = tree.getIsBuiltFrom_alignment_id();
		
		if(alnId!=null) {
			if(!alnId.trim().isEmpty()) {
				
				String objectNames        = "IncludesAlignmentRow AlignmentRow ContainsAlignedProtein";
				String filterClause       = "IncludesAlignmentRow(from-link) in (?)";
				List<String> queryParams  = Arrays.asList(alnId);
				String fields             = "AlignmentRow(row-id) ContainsAlignedProtein(to-link) ContainsAlignedProtein(kb-feature-id)";
				
				
				/*if(getFeatureIds) {
					objectNames += " IsProteinFor";
					fields += " IsProteinFor(to-link)";
				}
				if(getFeatureFunctions) {
					objectNames += " Feature";
					fields += " Feature(function)";
				}
				if(getGenomeIds) {
					objectNames += " IsOwnedBy";
					fields += " IsOwnedBy(to-link)";
				}
				if(getSpeciesNames) {
					objectNames += " Genome";
					fields += " Genome(scientific-name)";
				}*/
				
				List<List<String>> rows = cdmi.getAll(objectNames,filterClause,queryParams,fields,0L);
				for(List<String> row: rows) {
					for(String i:row) {
						System.out.print("\t"+i);
					}
					System.out.println();
				}
				
				
				
				
			} else {
				throw new KBaseTreesException("Error: tree data from the CDS ("+cdmi.getURL()+") for tree '"+tree.getTree_id()+"' was not matched to an alignment.");
			}
		} else {
			throw new KBaseTreesException("Error: tree data from the CDS ("+cdmi.getURL()+") for tree '"+tree.getTree_id()+"' was not matched to an alignment.");
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
		
		if(getGenomeId) {
			fields += " IsOwnedBy(to-link)";
		}
		if(getGenomeName) {
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
	
	public static void main(String[] args) throws KBaseTreesException {
		CdsUtil cdsUtil = new CdsUtil();
		List<CdsImportTreeParameters> params = Arrays.asList(
				new CdsImportTreeParameters().withTreeId("kb|tree.1000500")
					.withLinkNodesToBestFeature(1L)
					.withLinkNodesToBestGenome(1L)
					.withLinkNodesToBestGenomeName(1L)
				//,new CdsImportTreeParameters().withTreeId("kb|tree.1000001")
				);
		cdsUtil.getTreesForImport(params);
	}
	
}
