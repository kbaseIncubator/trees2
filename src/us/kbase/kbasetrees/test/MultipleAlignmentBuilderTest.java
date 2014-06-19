package us.kbase.kbasetrees.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	private static MSA build(String method) throws Exception {
		final MSA[] retWrap = new MSA[] { null };
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

		stb.run("token", new ConstructMultipleAlignment().withAlignmentMethod(method).withGeneSequences(loadProtSeqs()).withOutWorkspace("ws"), "", "ws/123");
		for (String id : retWrap[0].getRowOrder()) {
			String seq = retWrap[0].getAlignment().get(id);
			System.out.println(method + "\t" + id + "\t" + seq.substring(0, 50) + "...");
		}
		return retWrap[0];
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
