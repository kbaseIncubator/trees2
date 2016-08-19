package us.kbase.kbasetrees;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple2;
import us.kbase.kbasetrees.SpeciesTreeBuilder.AlignConcat;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.SubObjectIdentity;

public class CloseGenomesFinder {

	public static List<String> findGenomes(AuthToken token, FindCloseGenomesParams params,
			String wsUrl, Map<String, String> configParams) throws Exception {
		return findGenomes(token, params, DefaultTaskBuilder.getDirParam(configParams, "scratch"), 
				DefaultTaskBuilder.getDirParam(configParams, "data.dir"), configParams.get("public.genomes.ws"),
				DefaultTaskBuilder.createDefaultObjectStorage(wsUrl));
	}

	public static List<String> findGenomes(AuthToken token, FindCloseGenomesParams params, 
			File tempDir, File dataDir, String genomeWsName, ObjectStorage ws) throws Exception {
		return findGenomes(token, params, tempDir, dataDir, genomeWsName, ws, false);
	}

	private static List<String> findGenomes(AuthToken token, FindCloseGenomesParams params, 
			File tempDir, File dataDir, String genomeWsName, ObjectStorage ws, boolean stopOnZeroDist) throws Exception {
		long maxDist = params.getMaxMismatchPercent() == null ? 5L : params.getMaxMismatchPercent();
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder();
		stb.init(tempDir, dataDir, genomeWsName, ws);
		Map<String, String> idLabelMap = new TreeMap<String, String>();
		Map<String, Map<String, List<String>>> idRefMap = 
				new TreeMap<String, Map<String, List<String>>>();
		Set<String> seeds = new HashSet<String>();
		AlignConcat concat = stb.placeUserGenomesIntoAlignment(token, 
				Arrays.asList(params.getQueryGenome()), true, idLabelMap, idRefMap, seeds);
		List<Tuple2<String, Integer>> kbIdToMinDist = stb.sortPublicGenomesByMismatches(
				seeds, concat, stopOnZeroDist);
		Map<String, String> kbToRefs = stb.loadGenomeKbToRefs(token);
		List<String> ret = new ArrayList<String>();
		for (Tuple2<String, Integer> idAndDist : kbIdToMinDist) {
			int dist = idAndDist.getE2();
			if (dist > maxDist)
				break;
			String kbId = idAndDist.getE1();
			String ref = null;
			if (idRefMap.get(kbId) == null) {
				ref = kbToRefs.get(kbId);
				if (ref == null) {
					System.err.println("[trees] CloseGenomeFinder: Can't find genome for kbId=" + kbId);
					continue;
				}
			} else {
				ref = idRefMap.get(kbId).get("g").get(0);
			}
			ret.add(ref);
		}
		return ret;
	}
	
	public static String guessTaxonomy(AuthToken token, GuessTaxonomyPathParams params,
            String wsUrl, Map<String, String> configParams) throws Exception {
		return guessTaxonomy(token, params, DefaultTaskBuilder.getDirParam(configParams, "scratch"), 
				DefaultTaskBuilder.getDirParam(configParams, "data.dir"), configParams.get("public.genomes.ws"),
				DefaultTaskBuilder.createDefaultObjectStorage(wsUrl));

	}
	
	public static String guessTaxonomy(AuthToken token, GuessTaxonomyPathParams params, 
			File tempDir, File dataDir, String genomeWsName, ObjectStorage ws) throws Exception {
		String ret = guessTaxonomy(token, params, tempDir, dataDir, genomeWsName, ws, true);
		if (ret == null)
			ret = guessTaxonomy(token, params, tempDir, dataDir, genomeWsName, ws, false);
		return ret;
	}

	public static String guessTaxonomy(AuthToken token, GuessTaxonomyPathParams params, 
			File tempDir, File dataDir, String genomeWsName, ObjectStorage ws, boolean stopOnZeroDist) throws Exception {
		List<String> genomeRefs = findGenomes(token, new FindCloseGenomesParams().
				withQueryGenome(params.getQueryGenome()), tempDir, dataDir, genomeWsName, ws, stopOnZeroDist);
		String ret = null;
		String key = "taxonomy";
		for (String genomeRef : genomeRefs) {
			ObjectData od = ws.getObjectSubset(token, Arrays.asList(
					new SubObjectIdentity().withRef(genomeRef)
					.withIncluded(Arrays.asList(key)))).get(0);
			Map<?, ?> genomeObj = od.getData().asClassInstance(Map.class);
			//String genomeName = (String)genomeObj.get("scientific_name");
			String taxonomy = (String)genomeObj.get(key);
			//System.out.println(genomeName + " -> " + taxonomy);
			if (taxonomy != null) {
				taxonomy = taxonomy.trim();
				if (taxonomy.contains(";")) {
					ret = taxonomy.substring(0, taxonomy.lastIndexOf(';') + 1);
					break;
				}
			}
		}
		return ret;
	}
}
