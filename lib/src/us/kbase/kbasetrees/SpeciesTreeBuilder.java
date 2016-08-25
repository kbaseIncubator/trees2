package us.kbase.kbasetrees;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.common.utils.FastaReader;
import us.kbase.common.utils.FastaWriter;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasetrees.util.TreeStructureUtil;
import us.kbase.kbasetrees.util.WorkspaceUtil;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceIdentity;

public class SpeciesTreeBuilder extends DefaultTaskBuilder<ConstructSpeciesTreeParams> {
	
	private static final String SPECIES_TREE_TYPE = "SpeciesTree";
	private static final String MAX_EVALUE = "1e-05";
	private static final int MIN_COVERAGE = 50;
	private static final int DEFAULT_NEAREST_GENOME_COUNT = 100;
    private static final int MIN_NEAREST_GENOME_COUNT = 1;
	private static final int MAX_NEAREST_GENOME_COUNT = 200;
	private static final String defaultGenomeWsName = "KBasePublicGenomesV3";
	private static final String[] genomeWsTypes = {"KBaseGenomes.Genome", "KBaseGenomeAnnotations.GenomeAnnotation"};
	
	private Map<String, String> genomeKbToRefMap = null;
	private String genomeWsName = null;
	
	@Override
	public Class<ConstructSpeciesTreeParams> getInputDataType() {
		return ConstructSpeciesTreeParams.class;
	}
	
	@Override
	public void init(String wsUrl, Map<String, String> configParams) {
		super.init(wsUrl, configParams);
		String genomeWsName = configParams.get("public.genomes.ws");
		this.genomeWsName = genomeWsName == null ? defaultGenomeWsName : genomeWsName;
	}

	public SpeciesTreeBuilder init(File tempDir, File dataDir, ObjectStorage ws) {
		return init(tempDir, dataDir, null, ws);
	}
	
	public SpeciesTreeBuilder init(File tempDir, File dataDir, String genomeWsName, ObjectStorage ws) {
		super.init(tempDir, dataDir, ws);
		this.genomeWsName = genomeWsName == null ? defaultGenomeWsName : genomeWsName;
		return this;
	}

	@Override
	public String getTaskDescription() {
		return "Species tree construction based on user defined genomes using rps-blast ans fast-tree";
	}

	@Override
	public String getOutRef(ConstructSpeciesTreeParams inputData) {
		String objId = inputData.getOutTreeId();
		if (objId == null)
			objId = "tree" + System.currentTimeMillis();
		return inputData.getOutWorkspace() + "/" + objId;
	}

	@Override
	public void run(AuthToken token, ConstructSpeciesTreeParams inputData,
			String outRef) throws Exception {
		boolean useCog103Only = inputData.getUseRibosomalS9Only() != null && 
				inputData.getUseRibosomalS9Only() == 1L;
		long nearestGenomeCount = inputData.getNearestGenomeCount() != null ? 
				inputData.getNearestGenomeCount() : DEFAULT_NEAREST_GENOME_COUNT;
		if (nearestGenomeCount < MIN_NEAREST_GENOME_COUNT)
		    throw new IllegalStateException("Neighbor public genome count can not be less than " + MIN_NEAREST_GENOME_COUNT);
		if (nearestGenomeCount > MAX_NEAREST_GENOME_COUNT)
		    throw new IllegalStateException("Neighbor public genome count can not be more than " + MAX_NEAREST_GENOME_COUNT);
		List<String> genomeRefs = inputData.getNewGenomes();
		if (genomeRefs == null) {
			String genomeSetRef = inputData.getGenomesetRef();
			if (genomeSetRef == null)
				throw new IllegalStateException("Either new_genomes or genomeset_ref field should be defined");
			@SuppressWarnings("unchecked")
			Map<String, Object> genomeSet = storage.getObjects(token, Arrays.asList(
					new ObjectIdentity().withRef(genomeSetRef))).get(0).getData().asClassInstance(Map.class);
			@SuppressWarnings("unchecked")
			Map<String, Object> genomeSetElements = (Map<String, Object>)genomeSet.get("elements");
			genomeRefs = new ArrayList<String>();
		    for (String key : genomeSetElements.keySet()) {
				@SuppressWarnings("unchecked")
		    	Map<String, Object> genomeSetElem = (Map<String, Object>)genomeSetElements.get(key);
		    	genomeRefs.add((String)genomeSetElem.get("ref"));
		    }
		}
		Tree tree = placeUserGenomes(token, genomeRefs, useCog103Only, false,
				(int)nearestGenomeCount);
		String id = outRef.substring(outRef.indexOf('/') + 1);
		saveResult(inputData.getOutWorkspace(), id, token, tree, inputData);
	}
	
	private void saveResult(String ws, String id, AuthToken token, Tree res,
			ConstructSpeciesTreeParams inputData) throws Exception {
		Map<String, String> meta = new LinkedHashMap<String, String>();
		meta.put("type", SPECIES_TREE_TYPE);
		List<String> inputWsObjects = inputData.getNewGenomes() != null ?
				inputData.getNewGenomes() : Arrays.asList(inputData.getGenomesetRef());
		ObjectSaveData data = new ObjectSaveData().withData(new UObject(res))
				.withType("KBaseTrees.Tree")
				.withMeta(meta)
				.withProvenance(Arrays.asList(new ProvenanceAction()
				.withDescription("Species tree was constructed using rps-blast program")
				.withInputWsObjects(inputWsObjects)
				.withService("KBaseTrees").withServiceVer(KBaseTreesServer.SERVICE_VERSION)
				.withMethod("construct_species_tree")
				.withMethodParams(Arrays.asList(new UObject(inputData)))));
		try {
			long objid = Long.parseLong(id);
			data.withObjid(objid);
		} catch (NumberFormatException ex) {
			data.withName(id);
		}
		storage.saveObjects(token, new SaveObjectsParams().withWorkspace(ws).withObjects(
				Arrays.asList(data)));
	}

	
	public String makeTreeForBasicCogs(boolean useCog103Only) throws Exception {
		return makeTree(concatCogAlignments(useCog103Only).toMap());
	}
	
	public String makeTree(Map<String, String> aln) throws Exception {
		File tempFile = File.createTempFile("aln", ".faa", tempDir);
		try {
			FastaWriter fw = new FastaWriter(tempFile);
			for (Map.Entry<String, String> entry : aln.entrySet())
				fw.write(entry.getKey(), entry.getValue());
			fw.close();
			return runFastTree(tempFile);
		} finally {
			try { tempFile.delete(); } catch (Exception ignore) {}
		}
	}
	
	private File getFastTreeBin() {
		return new File(getBinDir(), "FastTree." + getOsSuffix());
	}

	private File getFormatRpsDbBin() {
		return new File(getBinDir(), "makeprofiledb." + getOsSuffix());
	}

	private File getRpsBlastBin() {
		return new File(getBinDir(), "rpsblast." + getOsSuffix());
	}

	private String runFastTree(File input) throws Exception {
		CorrectProcess cp = null;
		ByteArrayOutputStream errBaos = null;
		Exception err = null;
		String binPath = getFastTreeBin().getAbsolutePath();
		int procExitValue = -1;
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		try {
			Process p = Runtime.getRuntime().exec(CorrectProcess.arr(binPath,
					"-fastest", input.getAbsolutePath()));
			errBaos = new ByteArrayOutputStream();
			cp = new CorrectProcess(p, result, "", errBaos, "");
			cp.waitFor();
			errBaos.close();
			procExitValue = p.exitValue();
		} catch(Exception ex) {
			try{ 
				errBaos.close(); 
			} catch (Exception ignore) {}
			try{ 
				if(cp!=null) 
					cp.destroy(); 
			} catch (Exception ignore) {}
			err = ex;
		}
		if (errBaos != null || result.size() == 0) {
			String err_text = new String(errBaos.toByteArray());
			if (err_text.length() > 0)
				err = new Exception("FastTree: " + err_text, err);
		}
		if (procExitValue != 0) {
			if (err == null)
				err = new IllegalStateException("FastTree exit code: " + procExitValue);
			throw err;
		}
		return new String(result.toByteArray(), Charset.forName("UTF-8")).trim();
	}
	
	private File getCogsDir() {
		return new File(dataDir, "cogs");
	}
	
	public List<String> loadCogsCodes(boolean useCog103Only) throws IOException {
		if (useCog103Only)
			return Arrays.asList("103");
		File inputList = new File(getCogsDir(), "cog_list.txt");
		List<String> cogCodes = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inputList));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			if (l.trim().length() == 0)
				continue;
			cogCodes.add(l.trim());
		}
		br.close();
		return cogCodes;
	}
	
	public Map<String, String> loadCogAlignment(String cogCode) throws IOException {
		GZIPInputStream is = new GZIPInputStream(new FileInputStream(
				new File(getCogsDir(), "COG" + cogCode + ".trim.faa.gz")));
		FastaReader fr = new FastaReader(new InputStreamReader(is));
		Map<String, String> aln = fr.readAll();
		fr.close();
		return aln;
	}
	
	public AlignConcat concatCogAlignments(boolean useCog103Only) throws IOException {
		Map<String, Map<String, String>> cogAlignments = new LinkedHashMap<String, Map<String, String>>();
		for (String cogCode : loadCogsCodes(useCog103Only)) 
			cogAlignments.put(cogCode, loadCogAlignment(cogCode));
		return concatCogAlignments(cogAlignments);
	}
	
	private AlignConcat concatCogAlignments(Map<String, Map<String, String>> alignments) throws IOException {
		//Map<String, String> concat = new TreeMap<String, String>();
		Set<String> commonIdSet = new HashSet<String>();
		for (String cogCode : alignments.keySet()) {
			Map<String, String> aln = alignments.get(cogCode);
			commonIdSet.addAll(aln.keySet());
		}
		List<String> cogCodes = new ArrayList<String>();
		Map<String, List<Integer>> cog2positions = new LinkedHashMap<String, List<Integer>>();
		for (String cogCode : alignments.keySet()) {
		    cogCodes.add(cogCode);
			List<Integer> positions = trimAlignment(alignments.get(cogCode));
			cog2positions.put(cogCode, positions);
		}
		return new AlignConcat(alignments, commonIdSet, cogCodes, cog2positions);
	}

	public File formatRpsDb(List<File> scorematFiles) throws Exception {
        File tempInputFile = File.createTempFile("rps", ".db", tempDir);
        return formatRpsDb(scorematFiles, tempInputFile);
	}

	public File formatRpsDb(List<File> scorematFiles, File dbFile) throws Exception {
		PrintWriter pw = new PrintWriter(dbFile);
		for (File f : scorematFiles) {
			pw.println(f.getAbsolutePath());
		}
		pw.close();
		CorrectProcess cp = null;
		ByteArrayOutputStream errBaos = null;
		Exception err = null;
		String binPath = getFormatRpsDbBin().getAbsolutePath();
		int procExitValue = -1;
		try {
			Process p = Runtime.getRuntime().exec(CorrectProcess.arr(binPath,
					"-in", dbFile.getAbsolutePath(), "-threshold", "9.82", 
					"-scale", "100.0", "-dbtype", "rps", "-index", "true"));
			errBaos = new ByteArrayOutputStream();
			cp = new CorrectProcess(p, null, "formatrpsdb", errBaos, "");
			cp.waitFor();
			errBaos.close();
			procExitValue = p.exitValue();
		} catch(Exception ex) {
			try{ 
				errBaos.close(); 
			} catch (Exception ignore) {}
			try{ 
				if(cp!=null) 
					cp.destroy(); 
			} catch (Exception ignore) {}
			err = ex;
		}
		if (errBaos != null) {
			String err_text = new String(errBaos.toByteArray());
			if (err_text.length() > 0)
				err = new Exception("FastTree: " + err_text, err);
		}
		if (procExitValue != 0) {
			if (err == null)
				err = new IllegalStateException("FastTree exit code: " + procExitValue);
			throw err;
		}
		return dbFile;
	}

	public File runRpsBlast(File dbFile, File fastaQuery) throws Exception {
        File tempOutputFile = File.createTempFile("rps", ".tab", tempDir);
        return runRpsBlast(dbFile, fastaQuery, tempOutputFile);
	}

	public File runRpsBlast(File dbFile, File fastaQuery, File outputFile) throws Exception {
		CorrectProcess cp = null;
		ByteArrayOutputStream errBaos = null;
		Exception err = null;
		String binPath = getRpsBlastBin().getAbsolutePath();
		int procExitValue = -1;
		FileOutputStream fos = new FileOutputStream(outputFile);
		try {
			Process p = Runtime.getRuntime().exec(CorrectProcess.arr(binPath,
					"-db", dbFile.getAbsolutePath(), "-query", fastaQuery.getAbsolutePath(), 
					"-outfmt", "6 qseqid stitle qstart qseq sstart sseq evalue bitscore pident", 
					"-evalue", MAX_EVALUE));
			errBaos = new ByteArrayOutputStream();
			cp = new CorrectProcess(p, fos, "", errBaos, "");
			cp.waitFor();
			errBaos.close();
			procExitValue = p.exitValue();
		} catch(Exception ex) {
			try{ 
				errBaos.close(); 
			} catch (Exception ignore) {}
			try{ 
				if(cp!=null) 
					cp.destroy(); 
			} catch (Exception ignore) {}
			err = ex;
		} finally {
			try { fos.close(); } catch (Exception ignore) {}
		}
		if (errBaos != null) {
			String err_text = new String(errBaos.toByteArray());
			if (err_text.length() > 0)
				err = new Exception("FastTree: " + err_text, err);
		}
		if (procExitValue != 0) {
			if (err == null)
				err = new IllegalStateException("FastTree exit code: " + procExitValue);
			throw err;
		}
		return outputFile;
	}
	
	public void processRpsOutput(File results, RpsBlastCallback callback) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(results));
		Pattern tabSep = Pattern.compile(Pattern.quote("\t"));
		try {
			while (true) {
				String l = br.readLine();
				if (l == null)
					break;
				if (l.trim().length() == 0)
					continue;
				String[] parts = tabSep.split(l);
				String subj = parts[1].substring(0, parts[1].indexOf(','));
				callback.next(parts[0], subj, Integer.parseInt(parts[2]), parts[3], 
						Integer.parseInt(parts[4]), parts[5], parts[6], 
						Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
			}
		} finally {
			br.close();
		}
	}
	
	public void cleanDbFiles(File dbListFile) {
		for (File f : dbListFile.getParentFile().listFiles()) {
			if (f.getName().startsWith(dbListFile.getName()))
				f.delete();
		}
	}

	public List<Integer> trimAlignment(Map<String, String> aln) {
		return trimAlignment(aln, 0.95);
	}

	public static List<Integer> trimAlignment(Map<String, String> aln, double minNonGapPart) {
	    List<String> tmp = new ArrayList<String>();
	    for (String key : aln.keySet())
	        tmp.add(aln.get(key));
	    String[] arr = tmp.toArray(new String[tmp.size()]);
	    List<Integer> posList = new ArrayList<Integer>();
	    for (int pos = 0; pos < arr[0].length(); pos++) {
	        int nonGaps = 0;
	        for (int n = 0; n < arr.length; n++) 
	            if (arr[n].charAt(pos) != '-')
	                nonGaps++;
	        if (nonGaps >= minNonGapPart * arr.length)
	            posList.add(pos);
	    }
	    /*Map<String, String> ret = new LinkedHashMap<String, String>();
	    for (String key : aln.keySet()) {
	        String seq = aln.get(key);
	        char[] newSeq = new char[posList.size()];
	        for (int i = 0; i < posList.size(); i++) 
	            newSeq[i] = seq.charAt(posList.get(i));
	        ret.put(key, new String(newSeq));
	    }*/
	    return posList;
	}

	private List<File> listScoreMatrixFiles(boolean useCog103Only) throws IOException {
		List<File> ret = new ArrayList<File>();
		for (String cog : loadCogsCodes(useCog103Only))
			ret.add(new File(getCogsDir(), "rps.COG" + cog + ".smp"));
		return ret;
	}
	
	public Map<String, String> loadGenomeKbToNames() throws IOException {
		return loadGenomeKbToNames(getCogsDir());
	}
	
	public static Map<String, String> loadGenomeKbToNames(File cogsDir) throws IOException {	
		File genomeNamesFile = new File(cogsDir, "genome_names.txt");
		BufferedReader br = new BufferedReader(new FileReader(genomeNamesFile));
		Map<String, String> ret = new LinkedHashMap<String, String>();
		try {
			while (true) {
				String l = br.readLine();
				if (l == null)
					break;
				if (l.isEmpty())
					continue;
				String[] parts = l.split("\t");
				ret.put(parts[0], parts[1]);
			}
		} finally {
			br.close();
		}
		return ret;
	}
	
	public Map<String, String> loadGenomeKbToRefs(AuthToken token) throws Exception {
		if (genomeKbToRefMap != null)
			return genomeKbToRefMap;
		Map<String, String> ret = new TreeMap<String, String>();
		ObjectStorage ws = getStorage(); 
		String wsName = genomeWsName;
		int objectCount = (int)(long)ws.getWorkspaceInfo(token, new WorkspaceIdentity().withWorkspace(wsName)).getE5();
		int partSize = 10000;
		int partCount = (objectCount + partSize - 1) / partSize;
		for (String wsType : genomeWsTypes) {
		    for (int partNum = 0; partNum < partCount; partNum++) {
		        long minObjectID = partNum * partSize;
		        long maxObjectID = (partNum + 1) * partSize - 1;
		        for (Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> info : 
		            ws.listObjects(token, new ListObjectsParams().withWorkspaces(Arrays.asList(wsName))
		                    .withType(wsType).withMinObjectID(minObjectID).withMaxObjectID(maxObjectID))) {
		            String ref = WorkspaceUtil.getRefFromObjectInfo(info);
		            String objectName = info.getE2();
		            ret.put(objectName, ref);
		        }
		    }
		}
		genomeKbToRefMap = ret;
		return ret;
	}

	public Tree placeUserGenomes(AuthToken token, List<String> genomeRefList, 
			boolean useCog103Only, boolean userGenomesOnly, int nearestGenomeCount) throws Exception {
		Map<String, String> idLabelMap = new TreeMap<String, String>();
		Map<String, Map<String, List<String>>> idRefMap = new TreeMap<String, Map<String, List<String>>>();
		Set<String> seeds = new HashSet<String>();

		AlignConcat alnConcat = placeUserGenomesIntoAlignment(token,
				genomeRefList, useCog103Only, idLabelMap, idRefMap, seeds);
		
		// Filtering
		Set<String> nearestNodes = new HashSet<String>();
		if (!userGenomesOnly) {
			List<Tuple2<String, Integer>> kbIdToMinDist = sortPublicGenomesByMismatches(
					seeds, alnConcat, false);
			if (kbIdToMinDist.size() > nearestGenomeCount)
				kbIdToMinDist = kbIdToMinDist.subList(0, nearestGenomeCount);
			for (Tuple2<String, Integer> entry : kbIdToMinDist)
				nearestNodes.add(entry.getE1());
		}
		Map<String, String> concat = new TreeMap<String, String>();
		for (String kbId : alnConcat.getGenomeIds())
			if (seeds.contains(kbId) || nearestNodes.contains(kbId))
				concat.put(kbId, alnConcat.getSequence(kbId));
		Map<String, String> kbToNames = loadGenomeKbToNames();
		Map<String, String> kbToRefs = loadGenomeKbToRefs(token);
		Map<String, Map<String, List<String>>> idKbMap = new TreeMap<String, Map<String, List<String>>>();
		for (String genomeKb : new ArrayList<String>(concat.keySet())) {
			Map<String, List<String>> refMap = new TreeMap<String, List<String>>();
			refMap.put("g", Arrays.asList(genomeKb));
			idKbMap.put(genomeKb, refMap);
			if (seeds.contains(genomeKb))
				continue;
			String ref = kbToRefs.get(genomeKb);
			if (ref == null) {
				System.err.println("[trees] SpeciesTreeBuilder: Can't find genome object for id: " + genomeKb);
				concat.remove(genomeKb);
				continue;
			}
			String name = kbToNames.get(genomeKb);
			idLabelMap.put(genomeKb, name);
			refMap = new TreeMap<String, List<String>>();
			refMap.put("g", Arrays.asList(ref));
			idRefMap.put(genomeKb, refMap);
		}
		String treeText = makeTree(concat);
		// Rerooting
        treeText = TreeStructureUtil.rerootTreeToMidpoint(treeText);
		Map<String, String> props = new TreeMap<String, String>();
		props.put("cog_codes", UObject.getMapper().writeValueAsString(loadCogsCodes(useCog103Only)));
		return new Tree().withTree(treeText).withDefaultNodeLabels(idLabelMap)
				.withLeafList(new ArrayList<String>(idLabelMap.keySet()))
				.withWsRefs(idRefMap).withKbRefs(idKbMap)
				.withTreeAttributes(props).withType(SPECIES_TREE_TYPE);
	}

	public List<Tuple2<String, Integer>> sortPublicGenomesByMismatches(
			Set<String> seeds, AlignConcat concat, boolean stopOnZeroDist) {
		List<Tuple2<String, Integer>> kbIdToMinDist = new ArrayList<Tuple2<String, Integer>>();
		Set<String> genomeIds = concat.getGenomeIds();
		for (String kbId : genomeIds) {
			if (seeds.contains(kbId))
				continue;
			char[] kbSeq = concat.getSequence(kbId).toCharArray();
			int minDist = -1;
			for (String userId : genomeIds) {
				if (!seeds.contains(userId))
					continue;
				char[] userSeq = concat.getSequence(userId).toCharArray();
				int dist = getDistance(kbSeq, userSeq);
				minDist = (minDist < 0) ? dist : Math.min(dist, minDist);
				if (stopOnZeroDist && minDist == 0)
					break;
			}
			kbIdToMinDist.add(new Tuple2<String, Integer>().withE1(kbId).withE2(minDist));
			if (stopOnZeroDist && minDist == 0)
				break;
		}
		Collections.sort(kbIdToMinDist, new Comparator<Tuple2<String, Integer>>() {
			@Override
			public int compare(Tuple2<String, Integer> o1, Tuple2<String, Integer> o2) {
				return o1.getE2().compareTo(o2.getE2());
			}
		});
		return kbIdToMinDist;
	}

	public AlignConcat placeUserGenomesIntoAlignment(AuthToken token,
			List<String> genomeRefList, boolean useCog103Only,
			Map<String, String> idLabelMap,
			Map<String, Map<String, List<String>>> idRefMap, Set<String> seeds)
			throws IOException {
		Map<String, Map<String, String>> cogAlignments = new LinkedHashMap<String, Map<String, String>>();
		for (String cogCode : loadCogsCodes(useCog103Only)) 
			cogAlignments.put(cogCode, loadCogAlignment(cogCode));
		List<GenomeToCogsAlignment> userData = new ArrayList<GenomeToCogsAlignment>();
		for (String genomeRef : genomeRefList) {
			Genome genome = null;
			try {
				genome = storage.getObjects(token, Arrays.asList(
						new ObjectIdentity().withRef(genomeRef))).get(0).getData().asClassInstance(Genome.class);
				userData.add(alignGenomeProteins(token, genomeRef, genome, useCog103Only, cogAlignments));
			} catch (Exception ex) {
				String genomeName = genome == null ? genomeRef : genome.getScientificName();
				throw new IllegalStateException("Error processing genome " + genomeName + " (" + ex.getMessage() + ")", ex);
			}
		}
		for (String cogCode : cogAlignments.keySet()) {
			for (int genomePos = 0; genomePos < userData.size(); genomePos++) {
				GenomeToCogsAlignment genomeRes = userData.get(genomePos);
				List<ProteinToCogAlignemt> alns = genomeRes.getCogToProteins().get(cogCode);
				if (alns == null || alns.isEmpty())
					continue;
				String alignedSeq = alns.get(0).getTrimmedFeatureSeq();
				String genomeRef = genomeRes.getGenomeRef();
				String nodeName = "user" + (genomePos + 1);
				cogAlignments.get(cogCode).put(nodeName, alignedSeq);
				seeds.add(nodeName);
				if (!idLabelMap.containsKey(nodeName)) {
					idLabelMap.put(nodeName, genomeRes.getGenomeName());
					Map<String, List<String>> refMap = new TreeMap<String, List<String>>();
					refMap.put("g", Arrays.<String>asList(genomeRef));
					idRefMap.put(nodeName, refMap);
				}
			}
		}
		return concatCogAlignments(cogAlignments);
	}
	
	private int getDistance(char[] s1, char[] s2) {
		int ret = 0;
		int len = s1.length;
		if (len != s2.length)
			throw new IllegalStateException();
		for (int i = 0; i < len; i++)
			if (s1[i] != s2[i])
				ret++;
		return ret;
	}
	
	private GenomeToCogsAlignment alignGenomeProteins(AuthToken token, String genomeRef, final Genome genome,
			boolean useCog103Only, final Map<String, Map<String, String>> cogAlignments) throws Exception {
		String genomeName = genome.getScientificName();
		File fastaFile = File.createTempFile("proteome", ".fasta", tempDir);
		File dbFile = null;
		File tabFile = null;
		try {
			FastaWriter fw = new FastaWriter(fastaFile);
			int protCount = 0;
			try {
				for (int pos = 0; pos < genome.getFeatures().size(); pos++) {
					Feature feat = genome.getFeatures().get(pos);
					String seq = feat.getProteinTranslation();
					if (seq == null || seq.isEmpty())
						continue;
					fw.write("" + pos, seq);
					protCount++;
				}
			} finally {
				try { fw.close(); } catch (Exception ignore) {}
			}
			if (protCount == 0)
				throw new IllegalStateException("No protein translations");
			dbFile = formatRpsDb(listScoreMatrixFiles(useCog103Only));
			tabFile = runRpsBlast(dbFile, fastaFile);
			final Map<String, List<ProteinToCogAlignemt>> cog2proteins = 
					new LinkedHashMap<String, List<ProteinToCogAlignemt>>();
			processRpsOutput(tabFile, new SpeciesTreeBuilder.RpsBlastCallback() {
				@Override
				public void next(String query, String subject, int qstart, String qseq,
						int sstart, String sseq, String evalue, double bitscore,
						double ident) throws Exception {
					if (!subject.startsWith("COG"))
						throw new IllegalStateException("Unexpected subject name in prs blast result: " + subject);
					String cogCode = "" + Integer.parseInt(subject.substring(3));
					Map<String, String> cogAln = cogAlignments.get(cogCode);
					int alnLen = cogAln.get(cogAln.keySet().iterator().next()).length();
					String alignedSeq = AlignUtil.removeGapsFromSubject(alnLen, qseq, sstart - 1, sseq);
					int coverage = 100 - AlignUtil.getGapPercent(alignedSeq);
					if (coverage < MIN_COVERAGE)
						return;
					List<ProteinToCogAlignemt> protList = cog2proteins.get(cogCode);
					if (protList == null) {
						protList = new ArrayList<ProteinToCogAlignemt>();
						cog2proteins.put(cogCode, protList);
					}
					ProteinToCogAlignemt result = new ProteinToCogAlignemt();
					result.setTrimmedFeatureSeq(alignedSeq);
					result.setBitscore(bitscore);
					result.setCogCode(cogCode);
					result.setAlignedCogConsensus(sseq);
					result.setCoverage(coverage);
					result.setEvalue(Double.parseDouble(evalue));
					int featurePos = Integer.parseInt(query);
					result.setFeatureId(genome.getFeatures().get(featurePos).getId());
					result.setAlignedFeatureSeq(qseq);
					result.setIdentity(ident);
					protList.add(result);
				}
			});
			if (cog2proteins.isEmpty())
				throw new IllegalStateException("No one protein family member found");
			for (List<ProteinToCogAlignemt> results : cog2proteins.values())
				if (results.size() > 1)
					Collections.sort(results, new Comparator<ProteinToCogAlignemt>() {
						@Override
						public int compare(ProteinToCogAlignemt o1, ProteinToCogAlignemt o2) {
							return Double.valueOf(o1.getEvalue()).compareTo(o2.getEvalue());
						}
					});
			GenomeToCogsAlignment ret = new GenomeToCogsAlignment();
			ret.setGenomeRef(genomeRef);
			ret.setGenomeName(genomeName);
			ret.setCogToProteins(cog2proteins);
			return ret;
		} finally {
			try { fastaFile.delete(); } catch (Exception ignore) {}
			if (dbFile != null)
				try { cleanDbFiles(dbFile); } catch (Exception ignore) {}
			if (tabFile != null)
				try { tabFile.delete(); } catch (Exception ignore) {}
		}
	}
	
	public static interface RpsBlastCallback {
		public void next(String query, String subj, int qstart, String qseq, int sstart, String sseq, 
				String evalue, double bitscore, double ident) throws Exception;
	}
	
	public static class AlignConcat {
	    private Map<String, Map<String, String>> alignments;
	    private Set<String> commonIdSet;
        private List<String> cogCodes;
        private Map<String, List<Integer>> cog2positions;
	    
	    public AlignConcat(Map<String, Map<String, String>> alignments, Set<String> commonIdSet,
	            List<String> cogCodes, Map<String, List<Integer>> cog2positions) {
	        this.alignments = alignments;
	        this.commonIdSet = Collections.unmodifiableSet(commonIdSet);
	        this.cogCodes = cogCodes;
	        this.cog2positions = cog2positions;
	    }

	    public Set<String> getGenomeIds() {
            return commonIdSet;
        }
	    
	    public String getSequence(String genomeId) {
	        StringBuilder ret = new StringBuilder();
	        for (String cogCode : cogCodes) {
	            String part = alignments.get(cogCode).get(genomeId);
	            List<Integer> positions = cog2positions.get(cogCode);
	            for (int pos : positions) {
	                ret.append(part == null ? '-' : part.charAt(pos));
	            }
	        }
	        return ret.toString();
	    }
	    
	    public Map<String, String> toMap() {
	        Map<String, String> ret = new TreeMap<String, String>();
	        for (String genomeId : commonIdSet)
	            ret.put(genomeId, getSequence(genomeId));
	        return ret;
	    }
	}
}
