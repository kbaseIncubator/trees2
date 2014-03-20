package us.kbase.kbasetrees.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.AlignUtil;
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
	
	@Test
	public void testTreeConstruction() throws Exception {
		List<Feature> features = new ArrayList<Feature>();
		final Genome tempGenome = new Genome().withScientificName("Super genome")
				.withFeatures(features);
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
						return Arrays.asList(new ObjectData().withData(new UObject(tempGenome)));
					}
				});
		for (String cog : stb.loadCogsCodes(false)) {
			Map<String, String> aln = stb.loadCogAlignment(cog);
			String seq = aln.get("354");
			if (seq == null)
				continue;
			seq = AlignUtil.removeGaps(seq);
			features.add(new Feature().withId("Copy-of-" + cog).withProteinTranslation(seq));
		}
		stb.run("token", new ConstructSpeciesTreeParams().withNewGenomes(Arrays.asList("aRef"))
				.withUseRibosomalS9Only(1L).withOutWorkspace("ws"), "", "ws/123");
		SpeciesTree tree = treeWrap[0];
		Assert.assertEquals(new ArrayList<String>(Arrays.asList("103")), tree.getCogs());
		Assert.assertTrue(tree.getSpeciesTree().contains("aRef"));
		Assert.assertEquals("Super genome", tree.getIdMap().get("aRef").getE2());
	}
}
