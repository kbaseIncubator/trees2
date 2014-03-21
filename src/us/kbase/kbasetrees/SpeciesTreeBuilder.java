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
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private static String MAX_EVALUE = "1e-05";
	private static int MIN_COVERAGE = 50;
	
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
		SpeciesTree tree = placeUserGenomes(token, inputData.getNewGenomes(), useCog103Only);
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
	}

	
	public String makeTreeForBasicCogs(boolean useCog103Only) throws Exception {
		return makeTree(concatCogAlignments(useCog103Only));
	}
	
	private String makeTree(Map<String, String> aln) throws Exception {
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
	
	private Map<String, String> concatCogAlignments(boolean useCog103Only) throws IOException {
		Map<String, Map<String, String>> cogAlignments = new LinkedHashMap<String, Map<String, String>>();
		for (String cogCode : loadCogsCodes(useCog103Only)) 
			cogAlignments.put(cogCode, loadCogAlignment(cogCode));
		return concatCogAlignments(cogAlignments);
	}
	
	private Map<String, String> concatCogAlignments(Map<String, Map<String, String>> alignments) throws IOException {
		Map<String, String> concat = new TreeMap<String, String>();
		Set<String> commonIdSet = new HashSet<String>();
		for (String cogCode : alignments.keySet()) {
			Map<String, String> aln = alignments.get(cogCode);
			commonIdSet.addAll(aln.keySet());
		}
		for (String cogCode : alignments.keySet()) {
			Map<String, String> aln = trimAlignment(alignments.get(cogCode));
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
					"-evalue", MAX_EVALUE));
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
	
	public SpeciesTree placeUserGenomes(String token, List<String> genomeRefList, 
			boolean useCog103Only) throws Exception {
		Map<String, Map<String, String>> cogAlignments = new LinkedHashMap<String, Map<String, String>>();
		for (String cogCode : loadCogsCodes(useCog103Only)) 
			cogAlignments.put(cogCode, loadCogAlignment(cogCode));
		List<GenomeToCogsAlignment> userData = new ArrayList<GenomeToCogsAlignment>();
		for (String genomeRef : genomeRefList) {
			try {
				userData.add(alignGenomeProteins(token, genomeRef, useCog103Only, cogAlignments));
			} catch (Exception ex) {
				throw new IllegalStateException("Error processing genome " + genomeRef + " (" + ex.getMessage() + ")", ex);
			}
		}
		Map<String, Tuple2<String, String>> tax2kbase = new ObjectMapper().readValue(
				new File(getCogsDir(), "tax2kbase.json"), new TypeReference<Map<String, Tuple2<String, String>>>() {});
		for (String cogCode : cogAlignments.keySet()) {
			for (int genomePos = 0; genomePos < userData.size(); genomePos++) {
				GenomeToCogsAlignment genomeRes = userData.get(genomePos);
				List<ProteinToCogAlignemt> alns = genomeRes.getCogToProteins().get(cogCode);
				if (alns == null || alns.isEmpty()) {
					//System.out.println(getClass().getName() + ": no cog " + cogCode + " for genome " + genomeRes.getGenomeRef());
					continue;
				}
				String alignedSeq = alns.get(0).getTrimmedFeatureSeq();
				String genomeRef = genomeRes.getGenomeRef();
				String nodeName = "user" + (genomePos + 1);
				cogAlignments.get(cogCode).put(nodeName, alignedSeq);
				if (!tax2kbase.containsKey(nodeName))
					tax2kbase.put(nodeName, new Tuple2<String, String>().withE1(genomeRef).withE2(genomeRes.getGenomeName()));
			}
		}
		String treeText = makeTree(concatCogAlignments(cogAlignments));
		return new SpeciesTree().withSpeciesTree(treeText)
				.withAlignmentRef("").withCogs(loadCogsCodes(useCog103Only))
				.withIdMap(tax2kbase);
	}
	
	private GenomeToCogsAlignment alignGenomeProteins(String token, String genomeRef, boolean useCog103Only,
			final Map<String, Map<String, String>> cogAlignments) throws Exception {
		final Genome genome = storage.getObjects(token, Arrays.asList(
				new ObjectIdentity().withRef(genomeRef))).get(0).getData().asClassInstance(Genome.class);
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
}
