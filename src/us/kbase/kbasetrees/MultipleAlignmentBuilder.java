package us.kbase.kbasetrees;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import us.kbase.common.service.UObject;
import us.kbase.common.utils.Alignment;
import us.kbase.common.utils.ClustalParser;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.common.utils.FastaWriter;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;

public class MultipleAlignmentBuilder extends DefaultTaskBuilder<ConstructMultipleAlignment> {

	@Override
	public Class<ConstructMultipleAlignment> getInputDataType() {
		return ConstructMultipleAlignment.class;
	}

	public MultipleAlignmentBuilder init(File tempDir, File dataDir, ObjectStorage ws) {
		return (MultipleAlignmentBuilder)super.init(tempDir, dataDir, ws);
	}

	@Override
	public String getOutRef(ConstructMultipleAlignment inputData) {
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
	
	@Override
	public void run(String token, ConstructMultipleAlignment inputData,
			String jobId, String outRef) throws Exception {
		Map<Integer, String> numToId = new TreeMap<Integer, String>();
		File inputFasta = File.createTempFile("msaInput", ".fa", getTempDir());
		File resultFile = null;
		List<File> toDelete = new ArrayList<File>(Arrays.asList(inputFasta));
		Alignment aln = null;
		try {
			FastaWriter fw = new FastaWriter(inputFasta);
			for (Map.Entry<String, String> entry : inputData.getGeneSequences().entrySet()) {
				int num = numToId.size() + 1;
				numToId.put(num, entry.getKey());
				fw.write("" + num, entry.getValue());
			}
			fw.close();
			resultFile = File.createTempFile("msaOutput", ".aln", getTempDir());
			toDelete.add(resultFile);
			String method = inputData.getAlignmentMethod().toLowerCase();
			if (method.equals("muscle")) {  // version 3.8.31
				String binPath = getMethodBin("muscle").getAbsolutePath();
				runProgram(binPath, "-in", inputFasta.getAbsolutePath(), "-out", resultFile.getAbsolutePath(), "-clw");
			} else if (method.equals("clustal")) { // version 2.1
				File dndFile = new File(inputFasta.getParentFile(), inputFasta.getName().substring(0, inputFasta.getName().length() - 2) + "dnd");
				toDelete.add(dndFile);
				String binPath = getMethodBin("clustalw2").getAbsolutePath();
				String type = (inputData.getIsProteinMode() != null && inputData.getIsProteinMode() == 0) ? "DNA" : "PROTEIN";
				runProgram(binPath, "/INFILE=" + inputFasta.getAbsolutePath(), 
						"-OUTFILE=" + resultFile, "/OUTORDER=INPUT", "-TYPE=" + type);
			} else if (method.equals("t-coffee")) {  // version 10.00
				String binPath = getMethodBin("tcoffee").getAbsolutePath();
				String type = (inputData.getIsProteinMode() != null && inputData.getIsProteinMode() == 0) ? "dna" : "protein";
				runProgram(binPath, inputFasta.getAbsolutePath(), "-type", type, "-outfile=" + resultFile.getAbsolutePath(), "-output=clustalw");
			} else {
				throw new IllegalStateException("Method " + inputData.getAlignmentMethod() + " is not supported");
			}
			aln = ClustalParser.parse(new BufferedReader(new FileReader(resultFile)), null);
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
		saveResult(inputData.getOutWorkspace(), id, token, ret);
	}
	
	private void saveResult(String ws, String id, String token, MSA res) throws Exception {
		ObjectSaveData data = new ObjectSaveData().withData(new UObject(res)).withType("KBaseTrees.MSA");
		try {
			long objid = Long.parseLong(id);
			data.withObjid(objid);
		} catch (NumberFormatException ex) {
			data.withName(id);
		}
		storage.saveObjects(token, new SaveObjectsParams().withWorkspace(ws).withObjects(
				Arrays.asList(data)));
	}

	private String runProgram(String... cmd) throws Exception {
		CorrectProcess cp = null;
		ByteArrayOutputStream errBaos = null;
		Exception err = null;
		int procExitValue = -1;
		ByteArrayOutputStream result = new ByteArrayOutputStream();
			try {
				Process p = Runtime.getRuntime().exec(CorrectProcess.arr(cmd));
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
