package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import us.kbase.common.service.ServerException;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.FastaReader;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasetrees.ConstructMultipleAlignmentParams;
import us.kbase.kbasetrees.ConstructSpeciesTreeParams;
import us.kbase.kbasetrees.ConstructTreeForAlignmentParams;
import us.kbase.kbasetrees.MSA;
import us.kbase.kbasetrees.Tree;
import us.kbase.workspace.CreateWorkspaceParams;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.SaveObjectsParams;

public class ServicesStartupLongTest extends ServicesStartupLongTester {

	@Test
	public void testExtractLeafNodeNames() throws Exception {
		List<String> nodes = treesClient.extractLeafNodeNames("(n)");
		Assert.assertEquals(1, nodes.size());
		Assert.assertEquals("n", nodes.get(0));
	}

	@Test
	public void testConstructMultipleAlignment() throws Exception {
		String msaId = "msa.1";
		Map<String, String> seqs = loadProtSeqs();
		String jobId = treesClient.constructMultipleAlignment(
				new ConstructMultipleAlignmentParams().withGeneSequences(seqs)
				.withOutWorkspace(defaultWokspace).withOutMsaId(msaId));
		waitForJob(jobId);
		MSA msa = getWsObject(defaultWokspace + "/" + msaId, MSA.class);
		for (String id : seqs.keySet()) {
			String seq = seqs.get(id);
			String aligned = msa.getAlignment().get(id);
			Assert.assertEquals(seq, AlignUtil.removeGaps(aligned));
		}
	}

	@Test
	public void testConstructTreeForAlignment() throws Exception {
		String msaId = "msa.2";
		Map<String, String> aln = loadProtAlignedSeqs();
		MSA msa = new MSA().withAlignment(aln)
				.withAlignmentLength((long)aln.get(aln.keySet().iterator().next()).length());
		saveWsObject(defaultWokspace, "KBaseTrees.MSA", msaId, msa);
		String treeId = "tree.1";
		String jobId = treesClient.constructTreeForAlignment(
				new ConstructTreeForAlignmentParams().withMsaRef(defaultWokspace + "/" + msaId)
				.withOutWorkspace(defaultWokspace).withOutTreeId(treeId));
		waitForJob(jobId);
		Tree tree = getWsObject(defaultWokspace + "/" + treeId, Tree.class);
		List<String> nodes = treesClient.extractLeafNodeNames(tree.getTree());
		for (String nodeId : nodes) {
			String nodeLabel = tree.getDefaultNodeLabels().get(nodeId);
			Assert.assertNotNull(nodeLabel, aln.get(nodeLabel));
		}
		List<ProvenanceAction> provs = getWsProvenance(defaultWokspace + "/" + treeId);
		Assert.assertEquals(1, provs.size());
		Assert.assertEquals("Tree was constructed using clustal program", provs.get(0).getDescription());
		Map<String, String> aln3 = new LinkedHashMap<String, String>();
		for (Map.Entry<String, String> entry : aln.entrySet()) {
			String seq = "A" + entry.getValue().substring(1);
			aln3.put(entry.getKey() + ".3", seq);
		}
		String msaId3 = "msa.3";
		MSA msa3 = new MSA().withAlignment(aln3)
				.withAlignmentLength((long)aln3.get(aln3.keySet().iterator().next()).length())
				.withParentMsaRef(defaultWokspace + "/" + msaId);
		try {
			saveWsObject(defaultWokspace, "KBaseTrees.MSA", msaId3, msa3);
		} catch (ServerException ex) {
			System.err.println("Server error detailes: " + ex.getData());
		}
		String treeId3 = "tree.3";
		String jobId3 = treesClient.constructTreeForAlignment(
				new ConstructTreeForAlignmentParams().withMsaRef(defaultWokspace + "/" + msaId3)
				.withOutWorkspace(defaultWokspace).withOutTreeId(treeId3));
		waitForJob(jobId3);
		Tree tree3 = getWsObject(defaultWokspace + "/" + treeId3, Tree.class);
		for (String nodeId : treesClient.extractLeafNodeNames(tree3.getTree())) {
			String nodeLabel = tree3.getDefaultNodeLabels().get(nodeId);
			if (nodeLabel.endsWith(".3")) {
				Assert.assertNotNull(nodeLabel, aln.get(nodeLabel.substring(0, nodeLabel.length() - 2)));
			} else {
				Assert.assertNotNull(nodeLabel, aln.get(nodeLabel));
			}
		}
	}
	
	@Test
	public void testConstructSpeciesTree() throws Exception {
		// Sync genome objects (just fake wrappers with kbase-id as name)
		String wsName = "KBasePublicGenomesLoad";
		String genomeWsType = "KBaseGenomes.Genome";
		wsClient.createWorkspace(new CreateWorkspaceParams().withWorkspace(wsName).withGlobalread("r"));
		List<ObjectSaveData> objects = new ArrayList<ObjectSaveData>();
		// (kb|g.2626:0.00128,kb|g.2627:0.00128,((kb|g.25423:0.00356,(((kb|g.1283:0.00055,kb|g.27370:0.00055)1.000:0.01782,(kb|g.1032:0.00054,kb|g.852:0.00072)1.000:0.00669)1.000:0.01927,(kb|g.20848:0.00055,kb|g.371:0.00055)1.000:0.00677)0.903:0.00356)1.000:0.00630,(kb|g.3779:0.00055,user1:0.00055)1.000:0.00299)0.469:0.00059);
		List<String> genomeKbIds = Arrays.asList(
				"kb|g.2626", "kb|g.2627", "kb|g.25423", "kb|g.1283", "kb|g.27370", 
				"kb|g.1032", "kb|g.852", "kb|g.20848", "kb|g.371", "kb|g.3779");
		for (String kbId : genomeKbIds) {
			Map<String, Object> data = new LinkedHashMap<String, Object>(4);
			data.put("id", kbId);
			data.put("scientific_name", "");
			data.put("domain", "");
			data.put("genetic_code", 11L);
			objects.add(new ObjectSaveData().withName(kbId).withType(genomeWsType).withData(new UObject(data)));
		}
		wsClient.saveObjects(new SaveObjectsParams().withWorkspace(wsName).withObjects(objects));
		// Store test genome (real data)
		FastaReader fr = new FastaReader(new File("data/test", "Shewanella_ANA_3_uid58347.fasta"));
		List<Feature> features = new ArrayList<Feature>();
		for (Map.Entry<String, String> entry : fr.readAll().entrySet())
			features.add(new Feature().withId(entry.getKey()).withProteinTranslation(entry.getValue())
					.withType("cds"));
		String genomeName = "Shewanella_ANA_3_uid58347";
		String genomeId = genomeName + ".genome";
		Genome genome = new Genome().withScientificName(genomeName)
				.withFeatures(features).withId(genomeId).withDomain("Bacteria").withGeneticCode(11L);
		saveWsObject(defaultWokspace, "KBaseGenomes.Genome", genomeId, genome);
		String spTreeId = "sptree.1";
		String genomeRef = defaultWokspace + "/" + genomeId;
		String jobId = treesClient.constructSpeciesTree(new ConstructSpeciesTreeParams().withNewGenomes(
				Arrays.asList(genomeRef)).withOutWorkspace(defaultWokspace)
				.withOutTreeId(spTreeId).withUseRibosomalS9Only(0L).withNearestGenomeCount(10L));
		waitForJob(jobId);
		Tree tree = getWsObject(defaultWokspace + "/" + spTreeId, Tree.class);
		try {
			List<String> nodeIds = treesClient.extractLeafNodeNames(tree.getTree());
			Assert.assertEquals(11, nodeIds.size());
			for (String nodeId : nodeIds) {
				String label = tree.getDefaultNodeLabels().get(nodeId);
				Map<String, List<String>> refs = tree.getWsRefs().get(nodeId);
				Assert.assertNotNull(label);
				if (nodeId.startsWith("user")) {
					//TODO: resolve genomeRef before comparison: Assert.assertEquals(genomeRef, refs.get("g").get(0));
					Assert.assertEquals(genomeName, label);
				}
			}
		} catch (Exception ex) {
			System.err.println(tree.getTree());
			throw ex;
		}
	}
	
	/****************************************** Utility methods *********************************************/
	
	private static Map<String, String> loadProtSeqs() throws Exception {
		Map<String, String> ret = new LinkedHashMap<String, String>();
		for (Map.Entry<String, String> entry : loadProtAlignedSeqs().entrySet()) 
			ret.put(entry.getKey(), AlignUtil.removeGaps(entry.getValue()));
		return ret;
	}

	private static Map<String, String> loadProtAlignedSeqs() throws Exception {
		File testFile = new File(new File("data", "test"), "msa_seqs.txt");
		Map<String, String> ret = new LinkedHashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			String[] parts = l.split(" ");
			ret.put(parts[0].trim(), parts[parts.length - 1].trim());
		}
		br.close();
		return ret;
	}
}
