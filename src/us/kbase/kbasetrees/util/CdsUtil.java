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
				try {
					getTreeNodeData(trees.get(p.getTreeId()),true,true,true,true,true);
				} catch (IOException e) {
					throw new KBaseTreesException("Error retriving tree data from the CDS ("+cdmi.getURL()+"): "+e.getMessage());
				} catch (JsonClientException e) {
					throw new KBaseTreesException("Error retriving tree data from the CDS ("+cdmi.getURL()+"): "+e.getMessage());
				}
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
	
	
	
	
	public void getTreeNodeData(TreeInCdsData tree,
			boolean getProteinIds,
			boolean getFeatureIds,
			boolean getFeatureFunctions,
			boolean getGenomeIds,
			boolean getSpeciesNames) throws KBaseTreesException, IOException, JsonClientException {
		
		if(getProteinIds==false && getFeatureIds==false && getGenomeIds==false && getSpeciesNames==false) {
			return;
		}
		
		String alnId = tree.getIsBuiltFrom_alignment_id();
		Map<String,List<String>> protein_ids = new HashMap<String,List<String>>();
		Map<String,List<String>> feature_ids = new HashMap<String,List<String>>();
		Map<String,List<String>> feature_functions  = new HashMap<String,List<String>>();
		Map<String,List<String>> genome_ids  = new HashMap<String,List<String>>();
		Map<String,List<String>> species_names  = new HashMap<String,List<String>>();
		
		if(alnId!=null) {
			if(!alnId.trim().isEmpty()) {
				
				String objectNames        = "IncludesAlignmentRow AlignmentRow ContainsAlignedProtein";
				
				String filterClause       = "IncludesAlignmentRow(from-link) in (?)";
				List<String> queryParams  = Arrays.asList(alnId);
				String fields             = "AlignmentRow(row-id) ContainsAlignedProtein(to-link) ContainsAlignedProtein(kb-feature-id)";
				
				if(getFeatureIds) {
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
				}
				
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
				new CdsImportTreeParameters().withTreeId("kb|tree.1000000"),
				new CdsImportTreeParameters().withTreeId("kb|tree.1000001")
				);
		cdsUtil.getTreesForImport(params);
	}
	
}
