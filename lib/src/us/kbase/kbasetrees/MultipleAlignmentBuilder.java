package us.kbase.kbasetrees;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import us.kbase.auth.AuthToken;
import us.kbase.common.service.UObject;
import us.kbase.common.utils.Alignment;
import us.kbase.common.utils.ClustalParser;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.common.utils.FastaWriter;
import us.kbase.workspace.GetObjectInfoNewParams;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.ObjectSpecification;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.SaveObjectsParams;

public class MultipleAlignmentBuilder extends DefaultTaskBuilder<ConstructMultipleAlignmentParams> {

	@Override
	public Class<ConstructMultipleAlignmentParams> getInputDataType() {
		return ConstructMultipleAlignmentParams.class;
	}

	public MultipleAlignmentBuilder init(File tempDir, File dataDir, ObjectStorage ws) {
		return (MultipleAlignmentBuilder)super.init(tempDir, dataDir, ws);
	}

	@Override
	public String getOutRef(ConstructMultipleAlignmentParams inputData) {
		String objId = inputData.getOutMsaId();
		if (objId == null)
			objId = "msa" + System.currentTimeMillis();
		return inputData.getOutWorkspace() + "/" + objId;
	}
	
	@Override
	public String getTaskDescription() {
		return "Multiple alignment construction for gene/protein list using one of widely used methods";
	}
	
	private File getMethodBin(String method) {
		return new File(new File(dataDir, "bin"), method + "." + getOsSuffix());
	}
	
	private File writeTempScript(String cmd) throws Exception {
		File f = File.createTempFile("cmd", "sh", getTempDir());
		PrintWriter pw = new PrintWriter(f);
		pw.println("#!/bin/bash");
		pw.println(cmd);
		pw.close();
		return f;
	}
	
	@Override
	public void run(AuthToken token, ConstructMultipleAlignmentParams inputData,
			String outRef) throws Exception {
		Map<Integer, String> numToId = new TreeMap<Integer, String>();
		File inputFasta = File.createTempFile("msaInput", ".fa", getTempDir());
		File resultFile = null;
		String resultAlnText = null;
		List<File> toDelete = new ArrayList<File>(Arrays.asList(inputFasta));
		String method = inputData.getAlignmentMethod();
		if (method == null) {
			method = "clustal";
		} else {
			method = method.toLowerCase();
		}
		Alignment aln = null;
		try {
			FastaWriter fw = new FastaWriter(inputFasta);
			if (inputData.getGeneSequences() != null) {
				for (Map.Entry<String, String> entry : inputData.getGeneSequences().entrySet()) {
					int num = numToId.size() + 1;
					numToId.put(num, entry.getKey());
					fw.write("" + num, entry.getValue());
				}
			} else if (inputData.getFeaturesetRef() != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> elements = (Map<String, Object>)storage.getObjects(token, Arrays.asList(new ObjectIdentity().withRef(
						inputData.getFeaturesetRef()))).get(0).getData().asClassInstance(Map.class).get("elements");
				for (String key : elements.keySet()) {
					@SuppressWarnings("unchecked")
					Map<String, Object> elem = ((Map<String, Map<String, Object>>)elements.get(key)).get("data");
					String id = (String)elem.get("id");
					if (elem.containsKey("genome_ref")) {
						String genomeRef = (String)elem.get("genome_ref");
						String genome_obj_name = storage.getObjectInfoNew(token, new GetObjectInfoNewParams().withObjects(
								Arrays.asList(new ObjectSpecification().withRef(genomeRef)))).get(0).getE2();
						id = genome_obj_name + '/' + id;
					}
					String seq = (String)elem.get("protein_translation");
					int num = numToId.size() + 1;
					numToId.put(num, id);
					fw.write("" + num, seq);
				}
			} else {
				throw new IllegalStateException("Either gene_sequences or featureset_ref should be defined in input parameters");
			}
			fw.close();
			resultFile = File.createTempFile("msaOutput", ".aln", getTempDir());
			toDelete.add(resultFile);
			File tmp = getTempDir();
			if (method.equals("muscle")) {  // version 3.8.31
				String binPath = getMethodBin("muscle").getAbsolutePath();
				runProgram(tmp, binPath, "-in", inputFasta.getAbsolutePath(), "-out", resultFile.getAbsolutePath(), "-clw");
			} else if (method.equals("clustal")) { // version 2.1
				File dndFile = new File(inputFasta.getParentFile(), inputFasta.getName().substring(0, inputFasta.getName().length() - 2) + "dnd");
				toDelete.add(dndFile);
				String binPath = getMethodBin("clustalw2").getAbsolutePath();
				String type = (inputData.getIsProteinMode() != null && inputData.getIsProteinMode() == 0) ? "DNA" : "PROTEIN";
				runProgram(tmp, binPath, "/INFILE=" + inputFasta.getName(), 
						"-OUTFILE=" + resultFile.getName(), "/OUTORDER=INPUT", "-TYPE=" + type);
			} else if (method.equals("t-coffee")) {  // version 10.00
				File dndFile = new File(inputFasta.getParentFile(), inputFasta.getName().substring(0, inputFasta.getName().length() - 2) + "dnd");
				toDelete.add(dndFile);
				String binPath = getMethodBin("tcoffee").getAbsolutePath();
				String type = (inputData.getIsProteinMode() != null && inputData.getIsProteinMode() == 0) ? "dna" : "protein";
				runProgram(tmp, binPath, inputFasta.getAbsolutePath(), "-type", type, "-outfile=" + resultFile.getAbsolutePath(), "-output=clustalw");
			} else if (method.equals("probcons")) {  // version 1.12
				File dndFile = new File(inputFasta.getParentFile(), inputFasta.getName().substring(0, inputFasta.getName().length() - 2) + "dnd");
				toDelete.add(dndFile);
				String binPath = getMethodBin("probcons").getAbsolutePath();
				resultAlnText = runProgram(tmp, binPath, "-clustalw", inputFasta.getAbsolutePath());
			} else if (method.equals("mafft")) {  // version 7.157b
				String binPath = getMethodBin("mafft").getAbsolutePath();
				String libPath = getMethodBin("mafftlib").getAbsolutePath();
				File cmdFile = writeTempScript("export MAFFT_BINARIES=" + libPath + "\n" + binPath + " --clustalout " + inputFasta.getAbsolutePath());
				toDelete.add(cmdFile);
				resultAlnText = runProgram(tmp, "bash", cmdFile.getAbsolutePath());
			} else {
				throw new IllegalStateException("Method " + inputData.getAlignmentMethod() + " is not supported");
			}
			if (resultAlnText == null) {
				aln = ClustalParser.parse(new BufferedReader(new FileReader(resultFile)), null);
			} else {
				aln = ClustalParser.parse(new BufferedReader(new StringReader(resultAlnText)), null);
			}
		} finally {
			for (File f : toDelete)
				if (f.exists())
					f.delete();
		}
		String alnType = null;
		if (inputData.getIsProteinMode() != null) {
			alnType = (inputData.getIsProteinMode() == 0) ? "dna" : "protein";
		}
		MSA ret = new MSA()
				.withAlignmentLength((long)aln.alignSeqs[0].length()).withSequenceType(alnType);
		Map<String, String> idToAln = new LinkedHashMap<String, String>();
		List<String> seqIds = new ArrayList<String>();
		for (int i = 0; i < aln.protNames.length; i++) {
			String num = aln.protNames[i];
			String id = numToId.get(Integer.parseInt(num));
			String seq = aln.alignSeqs[i];
			idToAln.put(id, seq);
			seqIds.add(id);
		}
		ret.withAlignment(idToAln).withRowOrder(seqIds);
		String id = outRef.substring(outRef.indexOf('/') + 1);
		saveResult(inputData.getOutWorkspace(), id, token, ret, method, inputData);
	}
	
	private void saveResult(String ws, String id, AuthToken token, MSA res, String method,
			ConstructMultipleAlignmentParams inputData) throws Exception {
		Map<String, String> seqs = inputData.getGeneSequences();
		inputData.setGeneSequences(null);
		ObjectSaveData data = new ObjectSaveData().withData(new UObject(res))
				.withType("KBaseTrees.MSA")
				.withProvenance(Arrays.asList(new ProvenanceAction()
				.withDescription("MSA was constructed using " + method + " program (sequences " +
						"are not present in method parameters, just remove gaps from aligned ones)")
				.withService("KBaseTrees").withServiceVer(KBaseTreesServer.SERVICE_VERSION)
				.withMethod("construct_multiple_alignment")
				.withMethodParams(Arrays.asList(new UObject(inputData)))));
		try {
			long objid = Long.parseLong(id);
			data.withObjid(objid);
		} catch (NumberFormatException ex) {
			data.withName(id);
		}
		storage.saveObjects(token, new SaveObjectsParams().withWorkspace(ws).withObjects(
				Arrays.asList(data)));
		inputData.setGeneSequences(seqs);
	}

	private String runProgram(File tempDir, String... cmd) throws Exception {
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
					err = new Exception("MSA: " + err_text, err);
			}
		if (procExitValue != 0) {
			if (err == null)
				err = new IllegalStateException("MSA exit code: " + procExitValue);
			throw err;
		}
		return new String(result.toByteArray(), Charset.forName("UTF-8")).trim();
	}
}
