package us.kbase.kbasetrees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.zip.GZIPOutputStream;

import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.FastaReader;
import us.kbase.common.utils.FastaWriter;

public class TreePeparation {
	
	private static final String cogDir = "/Users/rsutormin/Work/2014-03-11_species_tree";
	private static final String taxDir = "/Users/rsutormin/Work/2013-12-14_ncbi_tax";
	private static final String smpDir = "/Users/rsutormin/Work/2014-03-17_trees/smp";
	private static final String cddAlnDir = "/Users/rsutormin/Work/2014-03-17_trees/faa";

	private static final Map<Integer, Integer> taxSubst = new HashMap<Integer, Integer>();
	
	static {
		taxSubst.put(269483, 482957);
	}
	
	public static void main(String[] args) throws Exception {
		File inputList = new File(new File(cogDir), "COGlist");
		File alnDir = new File(new File(cogDir), "speciestree");
		File outDir = new File("data/cogs");
		List<Integer> cogCodes = loadCogCodes(inputList);
		File tmpDir = new File("temp_files");
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(tmpDir, new File("data"), null);
		final Map<Integer, String> taxMap = TaxTreeLoader.loadTaxNames(new File(taxDir));
		final Set<Integer> commonIdSet = new TreeSet<Integer>();
		for (int cogCode : cogCodes) {
			System.out.println("COG " + cogCode);
			String fileName = "" + cogCode;
			char[] prefix = new char[4 - fileName.length()];
			Arrays.fill(prefix, '0');
			fileName = "COG" + new String(prefix) + fileName;
			File alnF = new File(cddAlnDir, fileName + ".FASTA");
			Map<String, String> aln = FastaReader.readFromFile(alnF);
			String alignedConsensus = aln.get("lcl|consensus");
			String consensus = AlignUtil.removeGaps(alignedConsensus);
			final int consensusLen = consensus.length();
			final Map<Integer, String> outAln = new TreeMap<Integer, String>();
			File smpF = new File(smpDir, fileName + ".smp");
			File smpCopy = new File(outDir, "rps.COG" + cogCode + ".smp");
			if (smpCopy.exists())
				smpCopy.delete();
			Files.copy(smpF.toPath(), smpCopy.toPath());
			File dbFile = stb.formatRpsDb(Arrays.asList(smpF));
			File fastaFile = new File(alnDir, "COG" + cogCode + ".faa");
			File tabFile = stb.runRpsBlast(dbFile, fastaFile);
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
					commonIdSet.add(queryTaxId);
				}
			});
			tabFile.delete();
			File outFile = new File(outDir, "COG" + cogCode + ".trim.faa.gz");
			GZIPOutputStream os = new GZIPOutputStream(new FileOutputStream(outFile));
			FastaWriter fw = new FastaWriter(new PrintWriter(new OutputStreamWriter(os)));
			for (int taxId : outAln.keySet())
				fw.write("" + taxId, outAln.get(taxId).toString());
			fw.close();
		}
		PrintWriter pw = new PrintWriter(new File(outDir, "genome_tax.txt"));
		for (int taxId : commonIdSet)
			pw.println(taxId + "=" + taxMap.get(taxId));
		pw.close();
	}

	private static List<Integer> loadCogCodes(File inputList)
			throws FileNotFoundException, IOException {
		List<Integer> cogCodes = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(inputList));
		while (true) {
			String l = br.readLine();
			if (l == null)
				break;
			if (l.trim().length() == 0)
				continue;
			cogCodes.add(Integer.parseInt(l.trim()));
		}
		br.close();
		return cogCodes;
	}
}
