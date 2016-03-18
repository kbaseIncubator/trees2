package us.kbase.kbasetrees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;

import us.kbase.common.service.Tuple11;
import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.FastaReader;
import us.kbase.common.utils.FastaWriter;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.SubObjectIdentity;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceIdentity;

public class TreePeparation {
	
	public static final String cddUrlPrefix = "ftp://ftp.ncbi.nlm.nih.gov/pub/mmdb/cdd";

	private static final Map<Integer, Integer> taxSubst = new HashMap<Integer, Integer>();
	
	private static final int MIN_COVERAGE = 50;
    private static final int MIN_IDENT = 30;
    private static final int MIN_COGS = 10;

	
	static {
		taxSubst.put(269483, 482957);
	}
	
	public static void main(String[] args) throws Exception {
	    File rootDir = args.length > 0 ? new File(args[0]) : new File("test_local/workdir");
	    File workDir = new File(rootDir, "init");
        File dataDir = new File(rootDir, "data");
	    untarCdd(workDir, false);
        untarCdd(workDir, true);
        loadGenomesFromWS("https://kbase.us/services/ws", "KBasePublicGenomesV5", workDir);
        runRpsBlast(workDir);
        finalStage(workDir, dataDir);
	}
	
	private static void finalStage(File workDir, File dataDir) throws Exception {
	    if (!dataDir.exists())
	        dataDir.mkdir();
	    List<Integer> cogCodes = loadCogCodes();
        final Map<Integer, String> cog2consensus = new LinkedHashMap<Integer, String>();
	    for (int cogCode : cogCodes) {
	        String fileName = "" + cogCode;
	        char[] prefix = new char[4 - fileName.length()];
	        Arrays.fill(prefix, '0');
	        fileName = "COG" + new String(prefix) + fileName;
            File smpF = new File(workDir, fileName + ".smp");
            File smpCopy = new File(dataDir, "rps.COG" + cogCode + ".smp");
            if (!smpCopy.exists())
                Files.copy(smpF.toPath(), smpCopy.toPath());
	        File alnF = new File(workDir, fileName + ".FASTA");
            Map<String, String> aln = FastaReader.readFromFile(alnF);
            String alignedConsensus = aln.get("lcl|consensus");
            String consensus = AlignUtil.removeGaps(alignedConsensus);
            cog2consensus.put(cogCode, consensus);
	    }
        File rpsoutDir = new File(workDir, "rpsout");
        SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(rpsoutDir, workDir, null);
        Map<Integer, PrintWriter> cog2pw = new LinkedHashMap<Integer, PrintWriter>();
        PrintWriter gnPw = new PrintWriter(new File(dataDir, "genome_names.txt"));
        BufferedReader gnBr = new BufferedReader(new FileReader(new File(workDir, "genome_names.txt")));
        int genomeCount = 0;
        while (true) {
            String line = gnBr.readLine();
            if (line == null)
                break;
            String[] parts = line.split(Pattern.quote("\t"));
            String genomeKbaseId = parts[0];
            String genomeName = parts[1];
            File tabFile = new File(rpsoutDir, genomeKbaseId.replace('|', '_') + ".faa.tab");
            if (!tabFile.exists())
                continue;
            final Map<Integer, ProteinToCogAlignemt> cog2bestHit = 
                    new LinkedHashMap<Integer, ProteinToCogAlignemt>();
            stb.processRpsOutput(tabFile, new SpeciesTreeBuilder.RpsBlastCallback() {
                @Override
                public void next(String query, String subject, int qstart, String qseq,
                        int sstart, String sseq, String evalue, double bitscore,
                        double ident) throws Exception {
                    if (!subject.startsWith("COG"))
                        throw new IllegalStateException("Unexpected subject name in prs blast result: " + subject);
                    int cogCode = Integer.parseInt(subject.substring(3));
                    String consensus = cog2consensus.get(cogCode);
                    int alnLen = consensus.length();
                    String alignedSeq = AlignUtil.removeGapsFromSubject(alnLen, qseq, sstart - 1, sseq);
                    int coverage = 100 - AlignUtil.getGapPercent(alignedSeq);
                    double fullIdent = 0;
                    for (int i = 0; i < consensus.length(); i++) {
                        char ch = alignedSeq.charAt(i);
                        if (ch != '-' && consensus.charAt(i) == ch)
                            fullIdent += 1;
                    }
                    fullIdent *= 100.0 / consensus.length();
                    if (coverage < MIN_COVERAGE || ident < MIN_IDENT)
                        return;
                    ProteinToCogAlignemt prevHit = cog2bestHit.get(cogCode);
                    if (prevHit == null || prevHit.getIdentity() < fullIdent) {
                        ProteinToCogAlignemt result = new ProteinToCogAlignemt();
                        result.setTrimmedFeatureSeq(alignedSeq);
                        result.setBitscore(bitscore);
                        result.setCogCode("" + cogCode);
                        result.setAlignedCogConsensus(sseq);
                        result.setCoverage(coverage);
                        result.setEvalue(Double.parseDouble(evalue));
                        result.setFeatureId(query);
                        result.setAlignedFeatureSeq(qseq);
                        result.setIdentity(fullIdent);
                        cog2bestHit.put(cogCode, result);
                    }
                }
            });
            if (cog2bestHit.size() < MIN_COGS)
                continue;
            double matches = 0;
            int div = 0;
            gnPw.println(genomeKbaseId + "\t" + genomeName);
            genomeCount++;
            for (int cogCode : cog2bestHit.keySet()) {
                ProteinToCogAlignemt res = cog2bestHit.get(cogCode);
                String consensus = cog2consensus.get(cogCode);
                matches += consensus.length() * res.getIdentity() / 100.0;
                div += consensus.length();
                PrintWriter pw = cog2pw.get(cogCode);
                if (pw == null) {
                    File f = new File(dataDir, "COG" + cogCode + ".trim.faa.gz");
                    pw = new PrintWriter(new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(f))));
                    cog2pw.put(cogCode, pw);
                }
                pw.println(">" + genomeKbaseId);
                pw.println(res.getTrimmedFeatureSeq());
                pw.flush();
            }
            System.out.println(genomeKbaseId + "\t" + cog2bestHit.size() + "\t" + (matches * 100 / div));
        }
        gnPw.close();
        gnBr.close();
        for (int cogCode : cog2pw.keySet())
            cog2pw.get(cogCode).close();
        System.out.println("Genomes with stored data: " + genomeCount);
	}
	
	private static void runRpsBlast(File workDir) throws Exception {
	    File rpsoutDir = new File(workDir, "rpsout");
	    if (!rpsoutDir.exists())
	        rpsoutDir.mkdir();
	    File genomesDir = new File(workDir, "genomes");
	    SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(rpsoutDir, workDir, null);
        List<Integer> cogCodes = loadCogCodes();
        List<File> smpFiles = new ArrayList<File>();
        for (int cogCode : cogCodes) {
            String fileName = "" + cogCode;
            char[] prefix = new char[4 - fileName.length()];
            Arrays.fill(prefix, '0');
            fileName = "COG" + new String(prefix) + fileName + ".smp";
            smpFiles.add(new File(workDir, fileName));
        }
        File dbFile = new File(rpsoutDir, "rps.db");
        if (!dbFile.exists())
            stb.formatRpsDb(smpFiles, dbFile);
        long time = System.currentTimeMillis();
        int genomes = 0;
        for (File fastaFile : genomesDir.listFiles()) {
            if (fastaFile.length() == 0)
                continue;
            File tabFile = new File(rpsoutDir, fastaFile.getName() + ".tab");
            if (tabFile.exists())
                continue;
            try {
                stb.runRpsBlast(dbFile, fastaFile, tabFile);
            } catch (Exception ex) {
                System.err.println("Error processing " + fastaFile.getName() + ": " + ex.getMessage());
                continue;
            }
            genomes++;
            if (genomes % 100 == 0) {
                long diff = System.currentTimeMillis() - time;
                System.out.println("Processing " + genomes + " genomes: " + (diff / 1000) + " sec. " +
                		"(" + (diff / genomes) + " ms./gnm)");
            }
        }
	}
	
	private static void loadGenomesFromWS(String wsUrl, String publicGenomesWsName, 
	        File workDir) throws Exception {
	    WorkspaceClient wsCl = new WorkspaceClient(new URL(wsUrl));
	    wsCl.setAuthAllowedForHttp(true);
	    int maxId = (int)(long)wsCl.getWorkspaceInfo(
	            new WorkspaceIdentity().withWorkspace(publicGenomesWsName)).getE5();
	    System.out.println("Max id: " + maxId);
	    int itemsPerPart = 10000;
	    int partCount = (maxId + itemsPerPart - 1) / itemsPerPart;
	    PrintWriter pw = new PrintWriter(new File(workDir, "genome_names.txt"));
        PrintWriter pw2 = new PrintWriter(new File(workDir, "genome_infos.txt"));
	    Map<String, String> refsToKBaseId = new TreeMap<String, String>();
	    for (int partPos = 0; partPos < partCount; partPos++) {
	        long minObjectID = partPos * itemsPerPart + 1;
	        long maxObjectID = (partPos + 1) * itemsPerPart;
	        ListObjectsParams params = new ListObjectsParams().withWorkspaces(Arrays.asList(
	                publicGenomesWsName)).withIncludeMetadata(1L).withType("KBaseGenomes.Genome");
	        params.getAdditionalProperties().put("minObjectID", minObjectID);
            params.getAdditionalProperties().put("maxObjectID", maxObjectID);
            List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> data = 
                    wsCl.listObjects(params);
            System.out.println("For block [" + minObjectID + "-" + maxObjectID + "]: " + data.size());
            for (Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>> info : data) {
                // 1 - obj_id objid - the numerical id of the object. 
                // 2 - obj_name name - the name of the object. 
                // 3 - type_string type - the type of the object. 
                // 4 - timestamp save_date - the save date of the object. 
                // 5 - obj_ver ver - the version of the object. 
                // 6 - username saved_by - the user that saved or copied the object. 
                // 7 - ws_id wsid - the workspace containing the object. 
                // 8 - ws_name workspace - the workspace containing the object. 
                // 9 - string chsum - the md5 checksum of the object. 
                // 10 - int size - the size of the object in bytes. 
                // 11 - usermeta meta - arbitrary user-supplied metadata about the object.)
                String ref = info.getE7() + "/" + info.getE1() + "/" + info.getE5();
                String kbaseId = info.getE2();
                String domain = info.getE11().get("Domain");
                String sciName = info.getE11().get("Name");
                pw2.println(ref + "\t" + kbaseId + "\t" + domain + "\t" + sciName);
                if (domain != null && (domain.equals("Bacteria") || domain.equals("Archaea"))) {
                    pw.println(kbaseId + "\t" + sciName);
                    refsToKBaseId.put(ref, kbaseId);
                }
            }
	    }
	    pw.close();
	    pw2.close();
	    File genomeDir = new File(workDir, "genomes");
	    if (!genomeDir.exists())
	        genomeDir.mkdir();
	    long time = System.currentTimeMillis();
	    int genomes = 0;
	    for (String ref: refsToKBaseId.keySet()) {
	        String kbaseId = refsToKBaseId.get(ref);
            File outputFile = new File(genomeDir, kbaseId.replace('|', '_') + ".faa");
            File errorFile = new File(genomeDir, kbaseId.replace('|', '_') + ".err");
            if (outputFile.exists() || errorFile.exists())
                continue;
            try {
                genomes++;
                Map<String, String> featureIdToSeq = new LinkedHashMap<String, String>();
                Genome genome = wsCl.getObjectSubset(Arrays.asList(new SubObjectIdentity().withRef(ref)
                        .withIncluded(Arrays.asList("features/[*]/id", "features/[*]/type", 
                                "features/[*]/protein_translation")))).get(0).getData().asClassInstance(
                                        Genome.class);
                for (Feature f : genome.getFeatures()) {
                    if (f.getType() != null && f.getType().equals("CDS") && f.getId() != null &&
                            f.getProteinTranslation() != null && f.getProteinTranslation().length() > 0) {
                        featureIdToSeq.put(f.getId().replace('|', '_'), f.getProteinTranslation());
                    }
                }
                FastaWriter fw = null;
                try {
                    fw = new FastaWriter(outputFile);
                    for (String key : featureIdToSeq.keySet())
                        fw.write(key, featureIdToSeq.get(key));
                    fw.close();
                } catch (Exception ex) {
                    if (fw != null)
                        try {
                            fw.close();
                        } catch (Exception ignore) {}
                    throw ex;
                }
                if (genomes % 100 == 0) {
                    long diff = System.currentTimeMillis() - time;
                    System.out.println("Loading " + genomes + " genomes: " + (diff / 1000) + " sec. " +
                    		"(" + (diff / genomes) + " ms./gnm)");
                }
            } catch (Exception ex) {
                System.err.println("Error loading " + ref + " (" + kbaseId + "): " + ex.getMessage());
                if (outputFile.exists())
                    try {
                        outputFile.delete();
                    } catch (Exception ignore) {}
                try {
                    PrintWriter errPw = new PrintWriter(errorFile);
                    errPw.println("Error loading " + ref + " (" + kbaseId + "):");
                    ex.printStackTrace(errPw);
                    errPw.close();
                } catch (Exception ignore) {}
            }
	    }
	}
	
	private static void untarCdd(File workDir, boolean fasta) throws Exception {
	    List<Integer> cogCodes = loadCogCodes();
	    Set<String> selectedFiles = new HashSet<String>();
	    for (int cogCode : cogCodes) {
            String fileName = "" + cogCode;
            char[] prefix = new char[4 - fileName.length()];
            Arrays.fill(prefix, '0');
            fileName = "COG" + new String(prefix) + fileName;
            File f = new File(workDir, fileName + (fasta ? ".FASTA" : ".smp"));
            if (!f.exists())
                selectedFiles.add(f.getName());
	    }
	    if (selectedFiles.isEmpty())
	        return;
	    File inputFile = checkLocalCddTarGz(workDir, fasta ? "fasta.tar.gz" : "cdd.tar.gz");
	    final TarArchiveInputStream is = 
	            (TarArchiveInputStream)new ArchiveStreamFactory().createArchiveInputStream("tar", 
	                    new GZIPInputStream(new FileInputStream(inputFile)));
	    try {
	        TarArchiveEntry entry = null; 
	        while ((entry = (TarArchiveEntry)is.getNextEntry()) != null) {
	            if ((!entry.isDirectory()) && selectedFiles.contains(entry.getName())) {
	                System.out.println(entry.getName());
	                File output = new File(workDir, entry.getName());
	                final OutputStream os = new FileOutputStream(output); 
	                try {
	                    IOUtils.copy(is, os);
	                    os.close();
	                } catch (Exception ex) {
	                    try {
	                        os.close();
	                    } catch (Exception ignore) {}
                        try {
                            output.delete();
                        } catch (Exception ignore) {}
	                }
	                selectedFiles.remove(entry.getName());
	                if (selectedFiles.isEmpty())
	                    break;
	            }
	        }
	    } finally {
	        try {
	            is.close();
	        } catch (Exception ignore) {}
	    }
	    if (!selectedFiles.isEmpty())
	        System.err.println("WARNING! Some files were not found in CDD: " + 
	                new TreeSet<String>(selectedFiles));
	}
	
	private static File checkLocalCddTarGz(File workDir, String fileName) throws Exception {
	    if (!workDir.exists())
	        workDir.mkdirs();
	    File targetFile = new File(workDir, fileName);
	    if (!targetFile.exists()) {
	        InputStream input = new URL(cddUrlPrefix + "/" + fileName).openStream();
	        OutputStream output = new FileOutputStream(targetFile);
	        try {
	            IOUtils.copy(input, output);
	        } finally {
	            try {
	                input.close();
	            } catch (Exception ignore) {}
	            try {
	                output.close();
	            } catch (Exception ignore) {}
	        }
	    }
	    return targetFile;
	}

	private static List<Integer> loadCogCodes() throws IOException {
	    return loadCogCodes(TreePeparation.class.getResourceAsStream("cog_list.properties"));
	}

	private static List<Integer> loadCogCodes(InputStream is) throws IOException {
	    List<Integer> cogCodes = new ArrayList<Integer>();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
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

	/*private static void listSmpDescriptions() throws Exception {
	    File workDir = new File("data/cogs");
        File inputList = new File(workDir, "cog_list.txt");
	    List<Integer> cogCodes = loadCogCodes(inputList);
	    Set<String> descrList = new TreeSet<String>();
	    for (int cogCode : cogCodes) {
	        File smpF = new File(workDir, "rps.COG" + cogCode + ".smp");
	        String smpText = FileUtils.readFileToString(smpF);
	        int pos = smpText.indexOf("descr {");
	        String part1 = smpText.substring(pos + 7, smpText.indexOf("}", pos)).trim();
	        if (part1.startsWith("title \""))
	            part1 = part1.substring(7);
	        if (part1.endsWith("\""))
	            part1 = part1.substring(0, part1.length() - 1);
	        StringBuilder descr = new StringBuilder();
	        for (String part2 : part1.split(Pattern.quote("\n"))) {
	            String part3 = part2.trim();
	            if (descr.length() > 0)
	                descr.append(" ");
	            descr.append(part3);
	        }
	        descrList.add(descr.toString());
	    }
	    for (String descr : descrList) {
	        System.out.println(descr);
	    }
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
	
    private static List<Integer> loadCogCodes(File inputList) throws IOException {
        return loadCogCodes(new FileInputStream(inputList));
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
	}*/
}
