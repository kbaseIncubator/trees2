package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import junit.framework.Assert;

import org.junit.Test;

import us.kbase.common.service.ServerException;
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
