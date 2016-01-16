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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import us.kbase.common.service.Tuple2;
import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.FastaReader;
import us.kbase.common.utils.FastaWriter;
import us.kbase.kbasetrees.test.GenomeIdMapping;

public class TreePeparation {
	
	// Keith's data (from "cog+speciestree.MO.may2011.tar.gz")
	private static final String cogDir = "/Users/rsutormin/Work/2014-03-11_species_tree";

	// NCBI taxonomy data (nodes.dmp, names.dmp) from ftp://ftp.ncbi.nlm.nih.gov/pub/taxonomy/taxdmp.zip
	private static final String taxDir = "/Users/rsutormin/Work/2013-12-14_ncbi_tax";
	
	// From ftp://ftp.ncbi.nih.gov/pub/mmdb/cdd/fasta.tar.gz
	private static final String smpDir = "/Users/rsutormin/Work/2014-03-17_trees/smp";
	
	// From ftp://ftp.ncbi.nih.gov/pub/mmdb/cdd/cdd.tar.gz	
	private static final String cddAlnDir = "/Users/rsutormin/Work/2014-03-17_trees/faa";

	private static final Map<Integer, Integer> taxSubst = new HashMap<Integer, Integer>();
	
	static {
		taxSubst.put(269483, 482957);
	}
	
	public static void main(String[] args) throws Exception {
		prepareCogAlignments();
		prepareGenomeTaxToKBase();
		checkTrim();
	}
	
	private static void prepareCogAlignments() throws Exception {
		File inputList = new File(new File(cogDir), "COGlist");
		File alnDir = new File(new File(cogDir), "speciestree");
		File outDir = new File("data/cogs");
		List<Integer> cogCodes = loadCogCodes(inputList);
		File tmpDir = new File("temp_files");
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(tmpDir, new File("data"), null);
		final Map<Integer, String> taxMap = TaxTreeLoader.loadTaxNames(new File(taxDir));
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
	}

	private static void prepareGenomeTaxToKBase() throws Exception {
		final Set<Integer> commonIdSet = new TreeSet<Integer>();
		File inputList = new File(new File(cogDir), "COGlist");
		File outDir = new File("data/cogs");
		List<Integer> cogCodes = loadCogCodes(inputList);
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(new File("temp_files"), new File("data"), null);
		for (int cogCode : cogCodes) {
			for (String key : stb.loadCogAlignment("" +cogCode).keySet())
				commonIdSet.add(Integer.parseInt(key));
		}
		Map<Integer, String> taxMap = TaxTreeLoader.loadTaxNames(new File(taxDir));
		Map<String, Tuple2<String, String>> globalTax2kbase = loadGlobalTaxIdMapping();
		Map<String, Tuple2<String, String>> tax2kbase = new LinkedHashMap<String, Tuple2<String, String>>();
		Set<String> genomeNames = new TreeSet<String>();
		int lostTaxIds = 0;
		for (int taxId : commonIdSet) {
			Tuple2<String, String> value = globalTax2kbase.get("" + taxId);
			if (value == null) {
				value = new Tuple2<String, String>().withE1("").withE2(taxMap.get(taxId));
				lostTaxIds++;
			}
			tax2kbase.put("" + taxId, value);
			if (genomeNames.contains(value.getE2()))
				System.err.println("Duplication in genome names: " + value.getE2());
			genomeNames.add(value.getE2());
		}
		System.out.println("Lost tax id count: " + lostTaxIds);
		new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValue(
				new File(outDir, "tax2kbase.json"), tax2kbase);
	}
	
	private static Map<String, Tuple2<String, String>> loadGlobalTaxIdMapping() throws Exception {
		Map<String, Tuple2<String, String>> originalGlobalTax2kbase = GenomeIdMapping.getGlobalTaxIdMapping();
		Map<String, Integer> tax2ver = new LinkedHashMap<String, Integer>();
		Map<String, Tuple2<String, String>> globalTax2kbase = new LinkedHashMap<String, Tuple2<String, String>>();
		for (String key : originalGlobalTax2kbase.keySet()) {
			String[] taxIdAndVersion = key.split("\\.");
			String taxId = taxIdAndVersion[0];
			try {
				Integer.parseInt(taxId);
			} catch (NumberFormatException ex) {
				continue;
			}
			int newVersion = taxIdAndVersion.length == 1 ? -1 : Integer.parseInt(taxIdAndVersion[1]);
			if (tax2ver.containsKey(taxId) && tax2ver.get(taxId) >= newVersion)
				continue;
			globalTax2kbase.put(taxId, originalGlobalTax2kbase.get(key));
			tax2ver.put(taxId, newVersion);
		}
		return globalTax2kbase;
	}
	
	private static void checkTrim() throws Exception {
		File inputList = new File(new File(cogDir), "COGlist");
		File alnDir = new File(new File(cogDir), "speciestree");
		int oldTrimSum = 0;
		int newTrimSum = 0;
		List<Integer> cogCodes = loadCogCodes(inputList);
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(new File("temp_files"), new File("data"), null);
		for (int cogCode : cogCodes) {
			File oldTrimFile = new File(alnDir, "COG" + cogCode + ".trim");
			int oldTrimLen = loadTrimmedAlignment(oldTrimFile).entrySet().iterator().next().getValue().length();
			int newTrimLen = stb.trimAlignment(stb.loadCogAlignment("" +cogCode)).entrySet().iterator().next().getValue().length();
			System.out.println("COG" + cogCode + "\t" + oldTrimLen + "\t" + newTrimLen);
			oldTrimSum += oldTrimLen;
			newTrimSum += newTrimLen;
		}
		System.out.println("Summary\t" + oldTrimSum + "\t" + newTrimSum);
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
	
	private static Map<Integer, String> loadTrimmedAlignment(File f) throws IOException {
		Map<Integer, String> ret = new TreeMap<Integer, String>();
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
			List<Integer> ids = new ArrayList<Integer>();
			int curPos = 0;
			boolean firstBlock = true;
			for (int line = 0; ; line++) {
				String l = br.readLine();
				if (l == null)
					break;
				if (l.trim().length() == 0) {
					curPos = 0;
					firstBlock = false;
					continue;
				}
				if (l.length() < 11)
					throw new IllegalStateException("Wrong line format [" + l + "] at " + line);
				String prefix = l.substring(0, 11).trim();
				int id = -1;
				if (curPos >= ids.size()) {
					if (!firstBlock)
						throw new IllegalStateException("Internal error");
					id = Integer.parseInt(prefix);
					ids.add(id);
				} else {
					if (firstBlock)
						throw new IllegalStateException("Internal error");
					if (prefix.length() > 0)
						throw new IllegalStateException("Wrong prefix format [" + prefix + "] at " + line);
					id = ids.get(curPos);
				}
				l = l.substring(11);
				String[] parts = l.split(Pattern.quote(" "));
				StringBuilder sb = new StringBuilder();
				for (String part : parts)
					sb.append(part);
				String seq = ret.get(id);
				if (seq == null) {
					if (!firstBlock)
						throw new IllegalStateException("Internal error");
					seq = "";
				}
				ret.put(id, seq + sb.toString());
				curPos++;
			}
			return ret;
		} finally {
			br.close();
		}
	}
}
