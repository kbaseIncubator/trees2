package us.kbase.kbasetrees.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.FastaReader;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasetrees.CloseGenomesFinder;
import us.kbase.kbasetrees.FindCloseGenomesParams;
import us.kbase.kbasetrees.ObjectStorage;
import us.kbase.kbasetrees.SpeciesTreeBuilder;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;

public class CloseGenomesFinderTest {
	
	@Test
	public void testOneGenome() throws Exception {
		FastaReader fr = new FastaReader(new File("data/test", "Shewanella_ANA_3_uid58347.fasta"));
		List<Feature> features = new ArrayList<Feature>();
		for (Map.Entry<String, String> entry : fr.readAll().entrySet())
			features.add(new Feature().withId(entry.getKey()).withProteinTranslation(entry.getValue()));
		String ref = "Shewanella_ANA_3_uid58347.genome";
		String genomeName = "Shewanella_ANA_3_uid58347";
		List<String> list = build(ref, genomeName, features);
		Assert.assertEquals(24, list.size());
	}
	
	private static List<String> build(String genomeRef, String genomeName, 
			List<Feature> features) throws Exception {
		return build(genomeRef, new Genome().withScientificName(genomeName)
				.withFeatures(features));
	}
	
	private static List<String> build(final String genomeRef, final Genome genome) throws Exception {
		File cogDir = new File("data", "cogs");
		final List<String> genomeKbIds = new ArrayList<String>(SpeciesTreeBuilder.loadGenomeKbToNames(cogDir).keySet());
		Map<String, String> allCfg = new LinkedHashMap<String, String>();
		allCfg.put("temp.dir", "data");
		return CloseGenomesFinder.findGenomes("token", new FindCloseGenomesParams().withQueryGenome(genomeRef), new File("temp_files"), new File("data"),
				new ObjectStorage() {
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
							String authToken, SaveObjectsParams params) throws Exception {
						throw new IllegalStateException("Unsupported method");
					}
					@Override
					public List<ObjectData> getObjects(String authToken,
							List<ObjectIdentity> objectIds) throws Exception {
						return Arrays.asList(new ObjectData().withData(new UObject(genome)));
					}
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> listObjects(
							String authToken, ListObjectsParams params)
							throws Exception {
						List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> ret =
								new ArrayList<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>>();
						if (params.getSkip() > 0)
							return ret;
						for (int i = 0; i < genomeKbIds.size(); i++) {
							String kbId = genomeKbIds.get(i);
							ret.add(new Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>()
									.withE7(1L).withE1(i + 1L).withE5(1L).withE2(kbId));
						}
						return ret;
					}
				});
	}
}
