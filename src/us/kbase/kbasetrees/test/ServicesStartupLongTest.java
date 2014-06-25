package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import us.kbase.common.service.Tuple7;
import us.kbase.common.utils.AlignUtil;
import us.kbase.kbasetrees.ConstructMultipleAlignment;
import us.kbase.kbasetrees.MSA;
import us.kbase.workspace.ObjectIdentity;

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
