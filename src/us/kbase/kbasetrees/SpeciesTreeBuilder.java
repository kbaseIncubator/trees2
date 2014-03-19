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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
import us.kbase.common.taskqueue.TaskQueueConfig;
import us.kbase.common.taskqueue.TaskRunner;
import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.common.utils.FastaReader;
import us.kbase.common.utils.FastaWriter;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;

public class SpeciesTreeBuilder implements TaskRunner<ConstructSpeciesTreeParams> {
	
	private File tempDir;
	private File dataDir;
	private ObjectStorage storage;
	
	public static void main(String[] args) throws Exception {
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(
				new File("temp_files"), new File("data"), null);
		System.out.println(stb.makeTreeForBasicCogs(true));
	}
	
	@Override
	public Class<ConstructSpeciesTreeParams> getInputDataType() {
		return ConstructSpeciesTreeParams.class;
	}

	@Override
	public void init(TaskQueueConfig queueCfg, Map<String, String> configParams) {
		init(getDirParam(configParams, "temp.dir"), getDirParam(configParams, "data.dir"),
				createDefaultObjectStorage(queueCfg.getWsUrl()));
	}
	
	public static ObjectStorage createDefaultObjectStorage(final String wsUrl) {
		return new ObjectStorage() {
			
			@Override
			public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> saveObjects(
					String authToken, SaveObjectsParams params) throws Exception {
				WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(authToken));
				client.setAuthAllowedForHttp(true);
				return client.saveObjects(params);
			}
			
			@Override
			public List<ObjectData> getObjects(String authToken,
					List<ObjectIdentity> objectIds) throws Exception {
				WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(authToken));
				client.setAuthAllowedForHttp(true);
				return client.getObjects(objectIds);
			}
		};
	}
	
	public SpeciesTreeBuilder init(File tempDir, File dataDir, ObjectStorage ws) {
		this.tempDir = tempDir;
		if (!tempDir.exists())
			tempDir.mkdir();
		this.dataDir = dataDir;
		if (!dataDir.exists())
			throw new IllegalStateException("Directory " + dataDir + " doesn't exist");
		this.storage = ws;
		return this;
	}

	private static File getDirParam(Map<String, String> configParams, String param) {
		String tempDirPath = configParams.get(param);
		if (tempDirPath == null)
			throw new IllegalStateException("Parameter " + param + " is not defined in configuration");
		return new File(tempDirPath);
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
	public void run(String token, ConstructSpeciesTreeParams inputData,
			String jobId, String outRef) throws Exception {
		boolean useCog103Only = inputData.getUseRibosomalS9Only() != null && inputData.getUseRibosomalS9Only() == 1L;
		String treeText = makeTreeForBasicCogs(useCog103Only);
		SpeciesTree tree = new SpeciesTree().withSpeciesTree(treeText)
				.withAlignmentRef("").withCogs(loadCogsCodes(useCog103Only))
				.withIdMap(Collections.<String, Tuple2 <String, String>>emptyMap());
		String id = outRef.substring(outRef.indexOf('/') + 1);
		saveResult(inputData.getOutWorkspace(), id, token, tree);
	}
	
	private void saveResult(String ws, String id, String token, SpeciesTree res) throws Exception {
		ObjectSaveData data = new ObjectSaveData().withData(new UObject(res)).withType("KBaseTrees.SpeciesTree");
		try {
			long objid = Long.parseLong(id);
			data.withObjid(objid);
		} catch (NumberFormatException ex) {
			data.withName(id);
		}
		storage.saveObjects(token, new SaveObjectsParams().withWorkspace(ws).withObjects(
				Arrays.asList(data)));
		System.out.println("Tree data was saved into " + ws + "/" + id);
	}

	
	public String makeTreeForBasicCogs(boolean useCog103Only) throws Exception {
		Map<String, String> aln = concatCogAlignments(useCog103Only);
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
		return new File(new File(dataDir, "bin"), "FastTree." + getOsSuffix());
	}

	private File getFormatRpsDbBin() {
		return new File(new File(dataDir, "bin"), "makeprofiledb." + getOsSuffix());
	}

	private File getRpsBlastBin() {
		return new File(new File(dataDir, "bin"), "rpsblast." + getOsSuffix());
	}

	private String getOsSuffix() {
		String osName = System.getProperty("os.name").toLowerCase();
		String suffix;
		if (osName.contains("linux")) {
			suffix = "linux";
		} else if (osName.contains("mac os x")) {
			suffix = "macosx";
		} else {
			throw new IllegalStateException("Unsupported OS type: " + osName);
		}
		return suffix;
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
				p.waitFor();
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
		return new String(result.toByteArray(), Charset.forName("UTF-8"));
	}
	
	private File getCogsDir() {
		return new File(dataDir, "cogs");
	}
	
	private List<String> loadCogsCodes(boolean useCog103Only) throws IOException {
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
	
	private Map<String, String> concatCogAlignments(boolean useCog103Only) throws IOException {
		Map<String, String> concat = new TreeMap<String, String>();
		Set<String> commonIdSet = new HashSet<String>();
		for (String cogCode : loadCogsCodes(useCog103Only)) {
			Map<String, String> aln = loadCogAlignment(cogCode);
			commonIdSet.addAll(aln.keySet());
		}
		for (String cogCode : loadCogsCodes(useCog103Only)) {
			Map<String, String> aln = trimAlignment(loadCogAlignment(cogCode));
			int alnLen = aln.get(aln.keySet().iterator().next()).length();
			for (String taxId : commonIdSet) {
				String seq = aln.get(taxId);
				if (seq == null) {
					char[] arr = new char[alnLen];
					Arrays.fill(arr, '-');
					seq = new String(arr);
				}
				String prev = concat.get(taxId);
				if (prev == null)
					prev = "";
				concat.put(taxId, prev + seq);
			}
		}
		return concat;
	}
	
	public File formatRpsDb(List<File> scorematFiles) throws Exception {
		File tempInputFile = File.createTempFile("rps", ".db", tempDir);
		PrintWriter pw = new PrintWriter(tempInputFile);
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
					"-in", tempInputFile.getAbsolutePath(), "-threshold", "9.82", 
					"-scale", "100.0", "-dbtype", "rps", "-index", "true"));
			errBaos = new ByteArrayOutputStream();
			cp = new CorrectProcess(p, null, "formatrpsdb", errBaos, "");
			p.waitFor();
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
		return tempInputFile;
	}
	
	public File runRpsBlast(File dbFile, File fastaQuery) throws Exception {
		File tempOutputFile = File.createTempFile("rps", ".tab", tempDir);
		CorrectProcess cp = null;
		ByteArrayOutputStream errBaos = null;
		Exception err = null;
		String binPath = getRpsBlastBin().getAbsolutePath();
		int procExitValue = -1;
		FileOutputStream fos = new FileOutputStream(tempOutputFile);
		try {
			Process p = Runtime.getRuntime().exec(CorrectProcess.arr(binPath,
					"-db", dbFile.getAbsolutePath(), "-query", fastaQuery.getAbsolutePath(), 
					"-outfmt", "6 qseqid stitle qstart qseq sstart sseq evalue bitscore pident", 
					"-evalue", "1e-05"));
			errBaos = new ByteArrayOutputStream();
			cp = new CorrectProcess(p, fos, "", errBaos, "");
			p.waitFor();
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
		return tempOutputFile;
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

	public Map<String, String> trimAlignment(Map<String, String> aln) {
		return AlignUtil.trimAlignment(aln, 0.95);
	}

	private List<File> listScoreMatrixFiles(boolean useCog103Only) throws IOException {
		List<File> ret = new ArrayList<File>();
		for (String cog : loadCogsCodes(useCog103Only))
			ret.add(new File(getCogsDir(), "rps.COG" + cog + ".smp"));
		return ret;
	}
	
	/*private Map<String, String> loadGenomeProteins(String token, String genomeRef, boolean useCog103Only) throws Exception {
		Genome genome = storage.getObjects(token, 
				Arrays.asList(new ObjectIdentity().withRef(genomeRef))).get(0).getData().asClassInstance(Genome.class);
		String genomeName = genome.getScientificName();
		File fastaFile = File.createTempFile("proteome", "fasta", tempDir);
		FastaWriter fw = new FastaWriter(fastaFile);
		try {
			for (Feature feat : genome.getFeatures()) {
				String protName = feat.getId();
				String seq = feat.getProteinTranslation();
				if (seq == null || seq.length() == 0)
					continue;
				fw.write(protName, seq);
			}
		} finally {
			try { fw.close(); } catch (Exception ignore) {}
		}
		File dbFile = formatRpsDb(Arrays.asList(smpF));
		File tabFile = runRpsBlast(dbFile, fastaFile);
		stb.cleanDbFiles(dbFile);
		stb.processRpsOutput(tabFile, new SpeciesTreeBuilder.RpsBlastCallback() {
			@Override
			public void next(String query, String subj, int qstart, String qseq,
					int sstart, String sseq, String evalue, double bitscore,
					double ident) throws Exception {
				int queryTaxId = Integer.parseInt(query);
				if (taxSubst.containsKey(queryTaxId))
					queryTaxId = taxSubst.get(queryTaxId);
				if (!taxMap.containsKey(queryTaxId))
					return;
				if (outAln.containsKey(queryTaxId))
					return;
				String ret = AlignUtil.removeGapsFromSubject(consensusLen, qseq, sstart - 1, sseq);
				outAln.put(queryTaxId, ret);
			}
		});
		tabFile.delete();
	}*/
	
	public static interface RpsBlastCallback {
		public void next(String query, String subj, int qstart, String qseq, int sstart, String sseq, 
				String evalue, double bitscore, double ident) throws Exception;
	}
}
