package us.kbase.kbasetrees;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import java.util.zip.GZIPInputStream;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple2;
import us.kbase.common.service.UObject;
import us.kbase.common.taskqueue.TaskQueueConfig;
import us.kbase.common.taskqueue.TaskRunner;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.common.utils.FastaReader;
import us.kbase.common.utils.FastaWriter;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;

public class SpeciesTreeBuilder implements TaskRunner<ConstructSpeciesTreeParams> {
	
	private File tempDir;
	private File dataDir;
	private String wsUrl;
	
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
				queueCfg.getWsUrl());
	}
	
	private SpeciesTreeBuilder init(File tempDir, File dataDir, String wsUrl) {
		System.out.println(getClass().getName() + ": tempDir=" + tempDir + ", " +
				"dataDir=" + dataDir + ", ws=" + wsUrl);
		this.tempDir = tempDir;
		if (!tempDir.exists())
			tempDir.mkdir();
		this.dataDir = dataDir;
		if (!dataDir.exists())
			throw new IllegalStateException("Directory " + dataDir + " doesn't exist");
		this.wsUrl = wsUrl;
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
		WorkspaceClient client = new WorkspaceClient(new URL(wsUrl), new AuthToken(token));
		client.setAuthAllowedForHttp(true);
		client.saveObjects(new SaveObjectsParams().withWorkspace(ws).withObjects(
				Arrays.asList(data)));
		System.out.println("Tree data was saved into " + ws + "/" + id);
	}

	
	public String makeTreeForBasicCogs(boolean useCog103Only) throws Exception {
		Map<String, String> aln = concatCogAlignments(useCog103Only);
		File tempFile = File.createTempFile("aln", ".faa", tempDir);
		FastaWriter fw = new FastaWriter(tempFile);
		for (Map.Entry<String, String> entry : aln.entrySet())
			fw.write(entry.getKey(), entry.getValue());
		fw.close();
		return runFastTree(tempFile);
	}
	
	private File getFastTreeBin() {
		File bin = new File(dataDir, "bin");
		String osName = System.getProperty("os.name").toLowerCase();
		String suffix;
		if (osName.contains("linux")) {
			suffix = "linux";
		} else if (osName.contains("mac os x")) {
			suffix = "macosx";
		} else {
			throw new IllegalStateException("Unsupported OS type: " + osName);
		}
		return new File(bin, "FastTree." + suffix);
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
	
	private Map<String, String> loadCogAlignment(String cogCode) throws IOException {
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
			Map<String, String> aln = loadCogAlignment(cogCode);
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
}
