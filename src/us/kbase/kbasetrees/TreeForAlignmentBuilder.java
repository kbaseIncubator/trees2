package us.kbase.kbasetrees;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import us.kbase.common.service.UObject;
import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.common.utils.FastaWriter;
import us.kbase.kbasetrees.util.TreeStructureUtil;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.SaveObjectsParams;

public class TreeForAlignmentBuilder extends DefaultTaskBuilder<ConstructTreeForAlignmentParams> {
	
	@Override
	public Class<ConstructTreeForAlignmentParams> getInputDataType() {
		return ConstructTreeForAlignmentParams.class;
	}

	public TreeForAlignmentBuilder init(File tempDir, File dataDir, ObjectStorage ws) {
		return (TreeForAlignmentBuilder)super.init(tempDir, dataDir, ws);
	}

	@Override
	public String getTaskDescription() {
		return "Species tree construction based on user defined genomes using rps-blast ans fast-tree";
	}

	@Override
	public String getOutRef(ConstructTreeForAlignmentParams inputData) {
		String objId = inputData.getOutTreeId();
		if (objId == null)
			objId = "tree" + System.currentTimeMillis();
		return inputData.getOutWorkspace() + "/" + objId;
	}

	@Override
	public void run(String token, ConstructTreeForAlignmentParams inputData,
			String jobId, String outRef) throws Exception {
		Long minNongapPercentage = inputData.getMinNongapPercentageForTrim();
		Map<String, String> preAln = loadAlignment(token, inputData.getMsaRef(), 
				minNongapPercentage == null ? 0 : minNongapPercentage);
		Map<String, String> numbersToOrignalNodeNames = new LinkedHashMap<String, String>();
		Map<String, String> numbersToAln = new LinkedHashMap<String, String>();
		for (Map.Entry<String, String> entry : preAln.entrySet()) {
			int num = numbersToOrignalNodeNames.size() + 1;
			numbersToOrignalNodeNames.put("" + num, entry.getKey());
			numbersToAln.put("" + num, entry.getValue());
		}
		String method = inputData.getTreeMethod();
		if (method == null) {
			method = "clustal";
		} else {
			method = method.toLowerCase();
		}
		String treeText = makeTree(numbersToAln, method);
		treeText = TreeStructureUtil.rerootTreeToMidpoint(treeText);
		Tree tree = new Tree().withTree(treeText).withDefaultNodeLabels(numbersToOrignalNodeNames);
		String id = outRef.substring(outRef.indexOf('/') + 1);
		saveResult(inputData.getOutWorkspace(), id, token, tree, method, inputData);
	}
	
	private void saveResult(String ws, String id, String token, Tree res, String method, 
			ConstructTreeForAlignmentParams inputData) throws Exception {
		ObjectSaveData data = new ObjectSaveData().withData(new UObject(res))
				.withType("KBaseTrees.Tree")
				.withProvenance(Arrays.asList(new ProvenanceAction()
				.withDescription("Tree was constructed using " + method + " program")
				.withInputWsObjects(Arrays.asList(inputData.getMsaRef()))
				.withService("KBaseTrees").withServiceVer(KBaseTreesServer.getStaticServiceVersion())
				.withMethod("construct_tree_for_alignment")
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

	
	private String makeTree(Map<String, String> aln, String method) throws Exception {
		File tempFile = File.createTempFile("aln", ".fa", tempDir);
		File treeFile = null;
		try {
			FastaWriter fw = new FastaWriter(tempFile);
			for (Map.Entry<String, String> entry : aln.entrySet())
				fw.write(entry.getKey(), entry.getValue());
			fw.close();
			if (method == null || method.toLowerCase().equals("clustal")) {
				String binPath = getClustalBin().getAbsolutePath();
				runFastTree(tempDir, binPath, "-INFILE=" + tempFile.getName(), "-TREE", "-OUTPUT=PHYLIP");
				treeFile = new File(tempDir, tempFile.getName().replace(".fa", ".ph"));
				StringBuilder ret = new StringBuilder();
				BufferedReader br = new BufferedReader(new FileReader(treeFile));
				while (true) {
					String l = br.readLine();
					if (l == null)
						break;
					ret.append(l.trim());
				}
				br.close();
				return ret.toString();
			} else if (method.toLowerCase().equals("fasttree")) {
				String binPath = getFastTreeBin().getAbsolutePath();
				return runFastTree(tempDir, binPath, "-fastest", tempFile.getAbsolutePath());
			} else {
				throw new IllegalStateException("Unsupported tree construction method: " + method);
			}
		} finally {
			try { tempFile.delete(); } catch (Exception ignore) {}
			if (treeFile != null && treeFile.exists())
				try { treeFile.delete(); } catch (Exception ignore) {}
		}
	}
	
	private File getFastTreeBin() {
		return new File(getBinDir(), "FastTree." + getOsSuffix());
	}

	private File getClustalBin() {
		return new File(getBinDir(), "clustalw2." + getOsSuffix());
	}

	private String runFastTree(File tempDir, String... cmd) throws Exception {
		CorrectProcess cp = null;
		ByteArrayOutputStream errBaos = null;
		Exception err = null;
		int procExitValue = -1;
		ByteArrayOutputStream result = new ByteArrayOutputStream();
			try {
				Process p = Runtime.getRuntime().exec(CorrectProcess.arr(cmd), null, tempDir);
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
			if (errBaos != null) {
				String err_text = new String(errBaos.toByteArray());
				if (err_text.length() > 0)
					err = new Exception("FastTree: " + err_text, err);
			}
		if (procExitValue != 0) {
			if (err == null)
				err = new IllegalStateException("Tree construction exit code: " + procExitValue);
			throw err;
		}
		return new String(result.toByteArray(), Charset.forName("UTF-8")).trim();
	}
	
	private Map<String, String> loadAlignment(String token, String msaRef, long minNongapPercentage) throws Exception {
		Map<String, String> ret = new LinkedHashMap<String, String>();
		loadAlignment(token, msaRef, ret);
		return AlignUtil.trimAlignment(ret, minNongapPercentage / 100.0);
	}
	
	private void loadAlignment(String token, String msaRef, Map<String, String> ret) throws Exception {
		final MSA msa = storage.getObjects(token, Arrays.asList(
				new ObjectIdentity().withRef(msaRef))).get(0).getData().asClassInstance(MSA.class);
		if (ret.size() > 0) {
			long len = ret.values().iterator().next().length();
			if (len != msa.getAlignmentLength())
				throw new IllegalStateException("Parent and child MSAs have different aligned sequence " +
						"lenths: " + len + " and " + msa.getAlignmentLength());
		}
		ret.putAll(msa.getAlignment());
		if (msa.getParentMsaRef() != null)
			loadAlignment(token, msa.getParentMsaRef(), ret);
	}
}
