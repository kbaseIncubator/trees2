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

import us.kbase.common.service.Tuple2;
import us.kbase.common.service.Tuple7;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.FastaReader;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasetrees.ConstructMultipleAlignment;
import us.kbase.kbasetrees.ConstructSpeciesTreeParams;
import us.kbase.kbasetrees.ConstructTreeForAlignmentParams;
import us.kbase.kbasetrees.MSA;
import us.kbase.kbasetrees.SpeciesTree;
import us.kbase.kbasetrees.Tree;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
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
				new ConstructMultipleAlignment().withGeneSequences(seqs)
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
	}
	
	@Test
	public void testConstructSpeciesTree() throws Exception {
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
				.withOutTreeId(spTreeId).withUseRibosomalS9Only(1L));
		waitForJob(jobId);
		SpeciesTree tree = getWsObject(defaultWokspace + "/" + spTreeId, SpeciesTree.class);
		try {
			List<String> nodeIds = treesClient.extractLeafNodeNames(tree.getSpeciesTree());
			Assert.assertEquals(1686, nodeIds.size());
			for (String nodeId : nodeIds) {
				Tuple2<String, String> idName = tree.getIdMap().get(nodeId);
				Assert.assertNotNull(idName);
				if (nodeId.startsWith("user")) {
					Assert.assertEquals(genomeRef, idName.getE1());
					Assert.assertEquals(genomeName, idName.getE2());
				}
			}
		} catch (Exception ex) {
			System.err.println(tree.getSpeciesTree());
			throw ex;
		}
	}
	
	/****************************************** Utility methods *********************************************/
	
	private static void saveWsObject(String wsName, String type, String objName, Object data) throws Exception {
		wsClient.saveObjects(new SaveObjectsParams().withWorkspace(wsName)
				.withObjects(Arrays.asList(new ObjectSaveData()
				.withType(type).withName(objName).withData(new UObject(data)))));
	}
	
	private static <T> T getWsObject(String ref, Class<T> type) throws Exception {
		T ret = wsClient.getObjects(Arrays.asList(new ObjectIdentity().withRef(ref))).get(0).getData().asClassInstance(type);
		return ret;
	}
	
	private static void waitForJob(String jobId) throws Exception {
		while (true) {
			Tuple7<String, String, String, Long, String, Long, Long> status = ujsClient.getJobStatus(jobId);
			boolean completed = status.getE6() == 1L;
			if (!completed) {
				Thread.sleep(1000);
				continue;
			}
			boolean isError = status.getE7() == 1L;
			if (isError) {
				System.err.println("Detailed error: " + ujsClient.getDetailedError(jobId));
				throw new IllegalStateException("Error in job execution (see console for detailes)");
			}
			break;
		}
	}

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
