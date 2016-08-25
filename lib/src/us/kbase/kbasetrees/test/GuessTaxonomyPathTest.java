package us.kbase.kbasetrees.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.ini4j.Ini;
import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple9;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.FastaReader;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasetrees.CloseGenomesFinder;
import us.kbase.kbasetrees.GuessTaxonomyPathParams;
import us.kbase.kbasetrees.ObjectStorage;
import us.kbase.kbasetrees.SpeciesTreeBuilder;
import us.kbase.workspace.GetObjectInfoNewParams;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.SubObjectIdentity;
import us.kbase.workspace.WorkspaceIdentity;

public class GuessTaxonomyPathTest {
	
	@Test
	public void testOneGenome() throws Exception {
		FastaReader fr = new FastaReader(new File("data/test", "Shewanella_ANA_3_uid58347.fasta"));
		List<Feature> features = new ArrayList<Feature>();
		for (Map.Entry<String, String> entry : fr.readAll().entrySet())
			features.add(new Feature().withId(entry.getKey()).withProteinTranslation(entry.getValue()));
		String ref = "Shewanella_ANA_3_uid58347.genome";
		String genomeName = "Shewanella_ANA_3_uid58347";
		String taxonomyPath = build(ref, genomeName, features);
		Assert.assertEquals("Bacteria; Proteobacteria; Gammaproteobacteria; Alteromonadales; Shewanellaceae; Shewanella;", taxonomyPath);
	}
	
	private static String build(String genomeRef, String genomeName, 
			List<Feature> features) throws Exception {
		return build(genomeRef, new Genome().withScientificName(genomeName).withFeatures(features));
	}
	
	private static String build(final String genomeRef, final Genome genome) throws Exception {
		Map<String, String> allCfg = new LinkedHashMap<String, String>();
		allCfg.put("temp.dir", "data");
		Map<String, String> cfg = loadTestCfg();
		String wsUrl = cfg.get("workspace.srv.url");
		String user = cfg.get("test.user1");
		String pwd = cfg.get("test.pwd1");
		String genomeWsName = cfg.get("public.genomes.ws");
		AuthToken token = AuthService.login(user, pwd).getToken();
		final ObjectStorage devStorage = SpeciesTreeBuilder.createDefaultObjectStorage(wsUrl);
		return CloseGenomesFinder.guessTaxonomy(token, new GuessTaxonomyPathParams().withQueryGenome(genomeRef), 
				new File("temp_files"), new File("data"), genomeWsName, new ObjectStorage() {
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
					        AuthToken authToken, SaveObjectsParams params) throws Exception {
						throw new IllegalStateException("Unsupported method");
					}
					@Override
					public List<ObjectData> getObjects(AuthToken authToken,
							List<ObjectIdentity> objectIds) throws Exception {
						Assert.assertEquals(1, objectIds.size());
						Assert.assertEquals(genomeRef, objectIds.get(0).getRef());
						return Arrays.asList(new ObjectData().withData(new UObject(genome)));
					}
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> listObjects(
					        AuthToken authToken, ListObjectsParams params) throws Exception {
						return devStorage.listObjects(authToken, params);
					}
					@Override
					public List<ObjectData> getObjectSubset(AuthToken authToken, List<SubObjectIdentity> objectIds) throws Exception {
						return devStorage.getObjectSubset(authToken, objectIds);
					}
					@Override
					public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> getObjectInfoNew(
					        AuthToken authToken, GetObjectInfoNewParams params)
							throws Exception {
						return devStorage.getObjectInfoNew(authToken, params);
					}
					@Override
					public Tuple9<Long, String, String, String, Long, String, String, String, Map<String, String>> getWorkspaceInfo(
					        AuthToken authToken, WorkspaceIdentity wsi) throws Exception {
					    return devStorage.getWorkspaceInfo(authToken, wsi);
					}
				});
	}
	
	private static Map<String, String> loadTestCfg() throws Exception {
		return new Ini(new File("test.cfg")).get("trees");
	}
}
