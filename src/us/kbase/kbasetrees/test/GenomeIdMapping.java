package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.kbasetrees.KBaseTreesClient;
import us.kbase.kbasetrees.SpeciesTree;
import us.kbase.tree.TreeClient;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.WorkspaceClient;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A simple text class for mapping NCBI tax ids to KBase genome ids. These can then be used to decorate
 * generated Species Trees. 
 * @author wjriehl
 *
 */
public class GenomeIdMapping {
	public static final String SEARCH_URL = "http://kbase.us/services/search/getResults";
	public static final String TREE_URL = "http://kbase.us/services/trees/"; //"http://140.221.85.58:8284/";
	
	/**
	 * Gets a global taxonomy ID -> KBase ID map.
	 * This is done by fetching all KBase genome information, then extracting the source IDs.
	 * Source IDs (note that they might not always be NCBI taxonomy IDs, though they mostly are) are then
	 * used as keys to map onto KBase IDs and Scientific Names.
	 * 
	 * This returns the map from Source ID onto a Tuple2<KBase ID, Scientific Name>
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Map<String, Tuple2<String, String>> getGlobalTaxIdMapping() 
			throws MalformedURLException, IOException {
		Map<String, Tuple2<String, String>> taxIdMap = new HashMap<String, Tuple2<String, String>>();

		boolean done = false;
		int itemsPerPage = 13000;
		int pageNum = 1;
		
		/* Pull all pages of results from the Search service
		 * The last page will either be empty, or contain less than the 'itemsPerPage' limit.
		 */
		while (!done) {
			String query = "itemsPerPage=" + itemsPerPage + "&page=" + pageNum + "&q=*&category=genomes";

			final BufferedReader br = new BufferedReader(new InputStreamReader(new URL(SEARCH_URL + "?" + query).openStream()));
			SearchResult result = new ObjectMapper().readValue(br, SearchResult.class);
			br.close();

			for (GenomeItem genome : result.getItems()) {
				taxIdMap.put(genome.getGenomeSourceId(), 
						    new Tuple2<String, String>()
							    .withE1(genome.getGenomeId())
							    .withE2(genome.getScientificName()));
			}
			if (result.getItemCount() < itemsPerPage)
				done = true;
			else
				pageNum++;
		}
		return taxIdMap;
	}
	
	/**
	 * Fetches a SpeciesTree from a user's workspaces.
	 * @param userName
	 * @param pwd
	 * @param workspace
	 * @param treeId
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JsonClientException
	 */
	public static SpeciesTree fetchTree(String userName, String pwd, String workspace, String treeId) 
			throws MalformedURLException, IOException, JsonClientException {
		WorkspaceClient wc = TreeServerPlaying.createWorkspaceClient(userName, pwd);
		
		SpeciesTree tree = wc.getObjects(Arrays.asList(new ObjectIdentity().withWorkspace(workspace).withName(treeId)))
				.get(0).getData().asClassInstance(SpeciesTree.class);
		
		return tree;
	}
	
	public static KBaseTreesClient createKBaseTreesClient(String user, String password) 
			throws UnauthorizedException, MalformedURLException, IOException {
		KBaseTreesClient treeClient = new KBaseTreesClient(new URL(TREE_URL), user, password);
		treeClient.setAuthAllowedForHttp(true);
		return treeClient;
	}
	
	public static TreeClient createTreeClient(String user, String password) 
			throws UnauthorizedException, MalformedURLException, IOException {
		TreeClient treeClient = new TreeClient(new URL(TREE_URL), user, password);
		treeClient.setAuthAllowedForHttp(true);
		return treeClient;
	}

	/**
	 * Relabels tree nodes from NCBI tax ids to KBase ID - scientific name
	 * E.g. "300269" becomes "kb|g.720-Shigella sonnei Ss046"
	 * 
	 * This is just a proof-of-principle test method.
	 * @param user
	 * @param password
	 * @param tree
	 * @return
	 * @throws Exception
	 */
	public static SpeciesTree relabelTreeNodes(String user, String password, SpeciesTree tree) throws Exception {
		Map<String, Tuple2<String, String>> idMap = getGlobalTaxIdMapping();
		
		TreeClient treeClient = createTreeClient(user, password);
		
		Map<String, String> replacements = new HashMap<String, String>();
		for (String taxId : idMap.keySet()) {
			String kbaseId = idMap.get(taxId).getE1().trim();
			String sciName = idMap.get(taxId).getE2().trim();
			
			sciName = sciName.replace(':', '-');
			sciName = sciName.replace(',', '_');
			sciName = sciName.replace('(', '_');
			sciName = sciName.replace(')', '_');
			
			String[] splitId = taxId.split("\\.");
			replacements.put(splitId[0], kbaseId + "-" + sciName); 
		}
		
		// Show all nodes in this tree that do not map onto the global list
//		List<String> nodes = treeClient.extractLeafNodeNames(tree.getSpeciesTree().trim());
//		for (String node : nodes) {
//			if (!replacements.containsKey(node)) {
//				System.out.println(node);
//			}
//		}
		
		String relabeledTree = treeClient.replaceNodeNames(tree.getSpeciesTree().trim(), replacements);
		tree.setSpeciesTree(relabeledTree);

		return tree;
	}
	
	/**
	 * Generates the id mapping between taxonomy ids and KBase ids.
	 * From a list of taxonomy ids of interest, this fetches the KBase genomes that they correspond
	 * to and builds a mapping between them.
	 * 
	 * Returns a Map<String, Tuple2<String,String>> where the key is the NCBI tax id
	 * and the value is a <KBase ID, Scientific Name> tuple.
	 * @param taxIds
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Map<String, Tuple2<String, String>> generateTaxId2KBaseId(List<String> taxIds) 
			throws MalformedURLException, IOException {
		Map<String, Tuple2<String, String>> idMap = new HashMap<String, Tuple2<String, String>>();
		Map<String, Tuple2<String, String>> globalMap = getGlobalTaxIdMapping();
		
		for (String taxId : taxIds) {
			Tuple2<String, String> kbaseId = new Tuple2<String, String>().withE1(null).withE2(null);
			if (globalMap.containsKey(taxId)) {
				kbaseId = globalMap.get(taxId);
			}
			idMap.put(taxId, kbaseId);
		}
		return idMap;
	}
	
	public static void main(String[] args) {
		String workspace = "wjriehl:home";
		String userName = "wjriehl";
		String pwd = "xxxxx";
		String treeId = "tree1395103422144";
		
		try {
			SpeciesTree tree = fetchTree(userName, pwd, workspace, treeId);
			tree = relabelTreeNodes(userName, pwd, tree);
		} catch (Exception e) {
			System.out.println(e);
		}
	}	
}
