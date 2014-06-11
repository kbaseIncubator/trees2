package us.kbase.kbasetrees.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.FastaReader;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasetrees.ConstructSpeciesTreeParams;
import us.kbase.kbasetrees.ObjectStorage;
import us.kbase.kbasetrees.SpeciesTree;
import us.kbase.kbasetrees.SpeciesTreeBuilder;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;

public class SpeciesTreeBuilderTest {
	
	public static void main(String[] args) throws Exception {
		String ws2url = "http://140.221.84.209:7058/";
		String userId = "nardevuser1";
		String pwd = "*****";
		String wsId = "nardevuser1:home";
		List<String> genomeRefs = Arrays.asList(new String[] {
				wsId + "/Shewanella_ANA_3_uid58347.genome",
				wsId + "/Shewanella_MR_7_uid58343.genome", 
				wsId + "/Shewanella_MR_4_uid58345.genome",
				wsId + "/Shewanella_baltica_BA175_uid52601.genome",
		});
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(
				new File("temp_files"), new File("data"), SpeciesTreeBuilder.createDefaultObjectStorage(ws2url));
		String token = AuthService.login(userId, pwd).getTokenString();
		SpeciesTree tree = stb.placeUserGenomes(token, genomeRefs, true);
		System.out.println(tree.getSpeciesTree());
	}
	
	@Test
	public void testOneGenome() throws Exception {
		FastaReader fr = new FastaReader(new File("data/test", "Shewanella_ANA_3_uid58347.fasta"));
		List<Feature> features = new ArrayList<Feature>();
		for (Map.Entry<String, String> entry : fr.readAll().entrySet())
			features.add(new Feature().withId(entry.getKey()).withProteinTranslation(entry.getValue()));
		String ref = "Shewanella_ANA_3_uid58347.genome";
		String genomeName = "Shewanella_ANA_3_uid58347";
		SpeciesTree tree = build(ref, genomeName, features);
		Assert.assertEquals(new ArrayList<String>(Arrays.asList("103")), tree.getCogs());
		Assert.assertTrue(tree.getSpeciesTree().contains("user1"));
		Assert.assertEquals(ref, tree.getIdMap().get("user1").getE1());
		Assert.assertEquals(genomeName, tree.getIdMap().get("user1").getE2());
	}
	
	private static SpeciesTree build(String genomeRef, String genomeName, 
			List<Feature> features) throws Exception {
		Map<String, Genome> ref2genome = new LinkedHashMap<String, Genome>();
		ref2genome.put(genomeRef, new Genome().withScientificName(genomeName)
				.withFeatures(features));
		return build(ref2genome);
	}
	
	private static SpeciesTree build(final Map<String, Genome> ref2genome) throws Exception {
		final SpeciesTree[] treeWrap = new SpeciesTree[] { null };
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(
				new File("temp_files"), new File("data"), new ObjectStorage() {
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
							String authToken, SaveObjectsParams params) throws Exception {
						treeWrap[0] = params.getObjects().get(0).getData().asClassInstance(SpeciesTree.class);
						return new ArrayList<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>>();
					}
					@Override
					public List<ObjectData> getObjects(String authToken,
							List<ObjectIdentity> objectIds) throws Exception {
						Genome genome = ref2genome.get(objectIds.get(0).getRef());
						return Arrays.asList(new ObjectData().withData(new UObject(genome)));
					}
				});

		stb.run("token", new ConstructSpeciesTreeParams().withNewGenomes(new ArrayList<String>(ref2genome.keySet()))
				.withUseRibosomalS9Only(1L).withOutWorkspace("ws"), "", "ws/123");
		return treeWrap[0];
	}
}
