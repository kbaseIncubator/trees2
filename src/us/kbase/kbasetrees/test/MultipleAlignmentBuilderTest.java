package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import us.kbase.common.service.Tuple11;
import us.kbase.common.utils.AlignUtil;
import us.kbase.kbasetrees.ConstructMultipleAlignment;
import us.kbase.kbasetrees.MSA;
import us.kbase.kbasetrees.MultipleAlignmentBuilder;
import us.kbase.kbasetrees.ObjectStorage;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;

public class MultipleAlignmentBuilderTest {

	@Test
	public void testMuscle() throws Exception {
		build("Muscle");
	}

	@Test
	public void testClustal() throws Exception {
		build("Clustal");
	}

	@Test
	public void testTCoffee() throws Exception {
		build("T-Coffee");
	}

	@Test
	public void testProbCons() throws Exception {
		build("ProbCons");
	}

	@Test
	public void testMafft() throws Exception {
		build("Mafft");
	}

	private static MSA build(String method) throws Exception {
		final MSA[] retWrap = new MSA[] { null };
		long time = System.currentTimeMillis();
		MultipleAlignmentBuilder stb = new MultipleAlignmentBuilder().init(
				new File("temp_files"), new File("data"), new ObjectStorage() {
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
							String authToken, SaveObjectsParams params) throws Exception {
						retWrap[0] = params.getObjects().get(0).getData().asClassInstance(MSA.class);
						return new ArrayList<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>>();
					}
					@Override
					public List<ObjectData> getObjects(String authToken,
							List<ObjectIdentity> objectIds) throws Exception {
						throw new IllegalStateException();
					}
				});
		Map<String, String> seqs = loadProtSeqs();
		stb.run("token", new ConstructMultipleAlignment().withAlignmentMethod(method).withGeneSequences(seqs).withOutWorkspace("ws"), "", "ws/123");
		MSA msa = retWrap[0];
		for (String id : retWrap[0].getRowOrder()) {
			String seq = retWrap[0].getAlignment().get(id);
			Assert.assertEquals((long)msa.getAlignmentLength(), (long)seq.length());
			//System.out.println(method + "\t" + id + "\t" + seq);  //.substring(0, 50) + "...");
		}
		Assert.assertEquals(seqs.size(), msa.getAlignment().size());
		for (String id : seqs.keySet()) {
			Assert.assertEquals(seqs.get(id), AlignUtil.removeGaps(msa.getAlignment().get(id)));
		}
		System.out.println("Multiple alignment (" + method + "), time: " + (System.currentTimeMillis() - time) + " ms");
		return msa;
	}

	private static Map<String, String> loadProtSeqs() throws Exception {
		File testFile = new File(new File("data", "test"), "msa_seqs.txt");
		Map<String, String> ret = new LinkedHashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			String[] parts = l.split(" ");
			ret.put(parts[0].trim(), AlignUtil.removeGaps(parts[parts.length - 1].trim()));
		}
		br.close();
		return ret;
	}
}
