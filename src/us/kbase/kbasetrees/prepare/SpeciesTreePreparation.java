package us.kbase.kbasetrees.prepare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;
import org.ini4j.Ini;

import us.kbase.auth.AuthService;
import us.kbase.kbasetrees.ObjectStorage;
import us.kbase.kbasetrees.SpeciesTreeBuilder;

/**
 * Multiple alignments of COG-families are done in gene_families module 
 * (us.kbase.kbasegenefamilies.SpeciesTreePrepation). Here is the code
 * constructing default species tree.
 * @author rsutormin
 */
public class SpeciesTreePreparation {
	
	public static void main(String[] args) throws Exception {
		Map<String, String> testCfg = loadTestCfg();
		File tempDir = new File(testCfg.get("scratch"));
		File dataDir = new File(testCfg.get("data.dir"));
		String user = testCfg.get("test.user1");
		String pwd = testCfg.get("test.pwd1");
		File cogsDir = new File(dataDir, "cogs");
		String token = AuthService.login(user, pwd).getTokenString();
		ObjectStorage storage = SpeciesTreeBuilder.createDefaultObjectStorage(testCfg.get("workspace.srv.url"));
		SpeciesTreeBuilder stb = new SpeciesTreeBuilder().init(tempDir, dataDir, storage);
		List<String> cogCodes = stb.loadCogsCodes(false);
		for (String cogCode : cogCodes) {
			File outputFile = new File(cogsDir, "COG" + cogCode + ".trim.faa.gz");
			if (outputFile.exists())
				continue;
			File inputFile = new File(cogsDir, "COG" + cogCode + ".trim.faa");
			InputStream is = new FileInputStream(inputFile);
			OutputStream os = new GZIPOutputStream(new FileOutputStream(outputFile));
			IOUtils.copy(is, os);
			is.close();
			os.close();
			inputFile.delete();
		}
		Map<String, String> aln = stb.concatCogAlignments(false);
		System.out.println("Genomes in common alignment: " + aln.size());
		System.out.println("Sequence length in common alignment: " + aln.get(aln.keySet().iterator().next()).length());
		Map<String, String> kbToNames = stb.loadGenomeKbToNames();
		Map<String, String> kbToRefs = stb.loadGenomeKbToRefs(token);
		PrintWriter pw = new PrintWriter(new File(cogsDir, "genome_names.new"));
		try {
			for (String genomeKb : aln.keySet()) {
				String ref = kbToRefs.get(genomeKb);
				if (ref == null)
					throw new IllegalStateException("Can't find genome object for id: " + genomeKb);
				String name = kbToNames.get(genomeKb);
				if (name == null || name.equals("null"))
					System.err.println("Can't find genome name for id: " + genomeKb);
				pw.println(genomeKb + "\t" + name);
			}
		} finally {
			pw.close();
		}
		String tree = stb.makeTree(aln);
		PrintWriter pw2 = new PrintWriter(new File(cogsDir, "species_tree.txt"));
		pw2.print(tree);
		pw2.close();
	}

	private static Map<String, String> loadTestCfg() throws Exception {
		return new Ini(new File("test.cfg")).get("trees");
	}
}
