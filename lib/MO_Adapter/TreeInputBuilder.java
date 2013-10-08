import java.sql.*;
import java.io.*;
import java.util.*;

import KBTreeUtil.KBTree;
import KBTreeUtil.KBTreeUtil;


// Possible Groups (** indicates alignment files are ready)
	// 16S
	// 23S
	// 5S
	// Adhoc
	// COG       
	// FastBLAST
	// GENE3D    
	// PFAM      
	// PIRSF     
	// SMART     
	// species
	// SSF       
	// TIGRFAMs
	
public class TreeInputBuilder {

	
	//public static final String CS_URL   = "jdbc:mysql://127.0.0.1:13306/kbase_sapling_v2";
	public static final String CS_URL   = "jdbc:mysql://fir.mcs.anl.gov/kbase_sapling_v2";
	public static final String CS_USER  = "kbase_sapselect";
	public static final String CS_PSSWD = "kbase4me2";
	
	public static final int SQL_ID_BATCH_SIZE = 1250;
	
	public static final String RAW_NEWICK_EXTENSION = ".tree.rooted";
	public static final String RAW_FASTA_EXTENSION = ".trim.fasta";
	
	
	public static long TIMESTAMP;

	public static String ALN_METHOD="";
	public static String ALN_PARAM="";

	
	public static void main(String[] args) throws Exception
	{
		// start a timer so that we can measure performance
		long startTime = System.currentTimeMillis();
		
		// get the gene family we want to dump, and set the alignment variables we will dump
		if(args.length!=3) {
			System.out.println("incorrect usage, so i must abort.");
			System.out.println("usage: TreeInputBuilder [GENE_FAMILY_GROUP] [INPUT_FOLDER] [DUMP_FOLDER]");
			System.out.println("  careful, the gene family group dir in the dump folder gets wiped each run.");
			System.out.println("  the input folder must contain: ");
			System.out.println("    (1) folder named rooted with rooted trees in newick format with ext .tree.rooted");
			System.out.println("    (2) folder named trimfasta with trimmed fasta alignments with ext .trim.fasta");
			System.out.println("    (3) file named kbTreeIdMap.txt, 3 columns, whitespace delimited with: ");
			System.out.println("           col 1 = name; col 2 = kb tree id; col 3 kb aln id; (see ReserveIds.pl)");
			System.out.println("    (4) names of tree/aln files are used as tree/aln names, and must match up");
			System.out.println();
			return;
		}
		
		// load the KBTree C++ Library for newick parsing and tree manipulations
		System.loadLibrary("KBTreeUtil");
		// load jdbc
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// and figure out what timestamp to use	
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		//we can set to a particular timestamp if we want: calendar.clear(); calendar.set(2013, Calendar.JANUARY, 2);
		TIMESTAMP = calendar.getTimeInMillis() / 1000L;
		
		//TreeInputBuilder tib = new TreeInputBuilder("COG", "../../../", "../../../dump/");
		TreeInputBuilder tib = new TreeInputBuilder(args[0], args[1], args[2]);
		tib.setupDumpLocation(true);
		
		tib.setupCsConn();
		tib.process();
		tib.closeCsConn();
		
		// give us some stats on how we did
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(" -> elapsedtime="+estimatedTime*0.001+"s");
		System.out.println(" -> done");
	}
	
	protected List <String> treeNames;
	protected String alignmentMethod;
	protected String alignmentParameters;
	
	protected String treeGroupName;
	
	protected String pathToDumpDir;
	protected String pathToAlnDir;
	protected String pathToTreeDir;
	
	protected HashMap<String,String[]> treeIdLookupTable; //pos0=kbTreeId, pos1=kbAlnId
	
	protected BufferedWriter BW_trees;
	protected BufferedWriter BW_treeAttribute;
	protected BufferedWriter BW_treeNodeAttribute;
	protected BufferedWriter BW_aln;
	protected BufferedWriter BW_aln_row;
	protected BufferedWriter BW_alnAttribute;
	protected BufferedWriter BW_containsProtein;
	protected BufferedWriter BW_containsNucleotide;
	
	public TreeInputBuilder(String treeGroupName, String inputDir, String outputDir)
		throws Exception {
	
		System.out.println(" -> running from dir: "+System.getProperty("user.dir"));	
		
		this.treeGroupName = treeGroupName;
		pathToDumpDir =    outputDir+treeGroupName;
		pathToAlnDir  =    inputDir+treeGroupName+"/trimfasta/";
		pathToTreeDir =    inputDir+treeGroupName+"/rooted/";
		String idTablePath =    inputDir+treeGroupName+"/kbTreeIdMap.txt";
		
		if(! (new File(pathToDumpDir)).exists() )
			throw new IOException("outputDir ("+pathToDumpDir+") does not exist!");
		if(! (new File(pathToAlnDir)).exists() )
			throw new IOException("alignment directory ("+pathToAlnDir+") does not exist!");
		if(! (new File(pathToTreeDir)).exists() )
			throw new IOException("newick tree directory ("+pathToTreeDir+") does not exist!");
		if(! (new File(idTablePath)).exists() )
			throw new IOException("id table file ("+idTablePath+") does not exist!");
		
		if(treeGroupName.compareTo("COG")==0) { 		alignmentMethod="RPS-BLAST"; alignmentParameters="-e 1e-5"; }
		else if(treeGroupName.compareTo("GENE3D")==0) { 	alignmentMethod="HMMER3"; alignmentParameters="-Z 2219 -E 0.001"; }
		else if(treeGroupName.compareTo("PFAM")==0) { 		alignmentMethod="HMMER3"; alignmentParameters="--cut_ga"; }
		else if(treeGroupName.compareTo("PIRSF")==0) { 		alignmentMethod="HMMER3"; alignmentParameters="-Z 2791 -E 0.02"; }
		else if(treeGroupName.compareTo("SMART")==0) { 		alignmentMethod="HMMER3"; alignmentParameters="-Z 885 -E 2.04e-5"; }
		else if(treeGroupName.compareTo("SSF")==0) { 		alignmentMethod="HMMER3"; alignmentParameters="-Z 2019 -E 0.02"; }
		else if(treeGroupName.compareTo("TIGRFAMs")==0) { 	alignmentMethod="HMMER3"; alignmentParameters="--cut_tc"; }
		else if(treeGroupName.compareTo("Adhoc")==0) { 	      	alignmentMethod="FastBLAST-topHomologs.pl"; alignmentParameters=""; }
		else {
			throw new Exception("unrecognized gene family group named \""+treeGroupName+"\"");
		}
		System.out.println(" -> setting alignment method to: '"+alignmentMethod+"' and parameters to: '"+alignmentParameters+"'");
		
		// read in the tree id lookup table
		treeIdLookupTable = new HashMap<String,String[]>();
		BufferedReader idReader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(idTablePath))));
		String newick=""; String line="";
		while ((line=idReader.readLine()) != null) {
			String [] toks = line.trim().split("\\s+");
			treeIdLookupTable.put(toks[0], new String[]{toks[1],toks[2]});
		};
		
		//Determine the tree list
		assembleTreeList();
	}
	
	
	protected Connection conn;
	protected Statement st;
	
	public void setupCsConn() throws Exception {
		System.out.println(" -> opening connection to "+CS_URL);
		conn = DriverManager.getConnection(CS_URL,CS_USER,CS_PSSWD);
		st = conn.createStatement();
	}
	
	public void closeCsConn() throws Exception {
		conn.close();
	}

	protected void assembleTreeList() throws IOException {
		// using the tree files in the input, generate the list of tree names
		File treeFolder = new File(pathToTreeDir);
		File [] listOfTreeFiles = treeFolder.listFiles();
		treeNames = new ArrayList<String>(10000);
		for (int k=0; k<listOfTreeFiles.length; k++) {
			if(listOfTreeFiles[k].isFile()) {
				// split based on '.' does not work because pfam ids have dots in thier name    
				 //treeNames.add(listOfTreeFiles[k].getName().split("\\.")[0]);
				String t_name = listOfTreeFiles[k].getName().replace(RAW_NEWICK_EXTENSION,"");
				treeNames.add(t_name);
				if(! (new File(pathToAlnDir+t_name+RAW_FASTA_EXTENSION)).exists() ) {
					throw new IOException("alignment file for tree ("+t_name+") could not be found!");
				}
			}
		}
		Collections.sort(treeNames);
		System.out.println(" -> processing "+treeNames.size()+" trees");
	}
	
	
	public void setupDumpLocation(boolean overwrite) throws IOException{
		File dir = new File(pathToDumpDir+"/Raw_Alignment_Files");
		if(dir.exists()) {
			if(overwrite) {
				for(File f: dir.listFiles()) { f.delete(); }
				dir.delete();
			} else {
				throw new IOException("Raw_Alignment_Files directory already exists, and you did not turn on the overwrite option");
			}
		}
		dir.mkdirs();
		
		dir = new File(pathToDumpDir+"/Raw_Tree_Files");
		if(dir.exists()) {
			if(overwrite) {
				for(File f: dir.listFiles()) { f.delete(); }
				dir.delete();
			} else {
				throw new IOException("Raw_Alignment_Files directory already exists, and you did not turn on the overwrite option");
			}
		}
		dir.mkdirs();
		
		BW_trees   = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/Tree.tab");
		BW_treeAttribute   = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/TreeAttribute.tab");
		BW_treeNodeAttribute   = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/TreeNodeAttribute.tab");
		BW_aln     = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/Alignment.tab");
		BW_aln_row = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/AlignmentRow.tab");
		BW_alnAttribute = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/AlignmentAttribute.tab");
		BW_containsProtein = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/ContainsAlignedProtein.tab");
		BW_containsNucleotide = openOutputFileStreamNoMatterWhat(pathToDumpDir+"/ContainsAlignedNucleotides.tab");
	}
	

	public void process() throws Exception {
		int row_count = 1; int written_count=0;
		
		long processStartTime = System.currentTimeMillis();
		for(int k=0; k<treeNames.size(); k++) {
			// start with the basics
			long iterationStartTime = System.currentTimeMillis();
			String name = treeNames.get(k);
			System.out.println(" -> ["+row_count+"/"+treeNames.size()+"]: processing tree "+name);
			
			
			// determine the id of the tree/alignment
			String [] kbids = getKbaseIds(name);
			String kbTreeID = kbids[0];
			String kbAlnID = kbids[1];
			
			// process the alignment file
			String alignmentFileName = pathToAlnDir+name+RAW_FASTA_EXTENSION;
			System.out.println("     | processing alignment file: "+alignmentFileName);
			
			AlignmentInformation ai = processAlignmentFile(alignmentFileName,pathToDumpDir+"/Raw_Alignment_Files/"+kbAlnID+".fasta");
			System.out.println("     | alignment contains "+ai.n_rows+" rows");
			if(ai.n_rows==0) {
				System.out.println("     | alignment is empty!  skipping this alignment/tree pair.");
				continue;
			}
			
			// grab the info about each feature
			fetchFeatureInfoFromCS(ai);
			
			
			// process the tree file
			String treeFileName = pathToTreeDir+name+RAW_NEWICK_EXTENSION;
			System.out.println("     | processing tree file: "+treeFileName);
			DataInputStream inTREE = new DataInputStream(new FileInputStream(treeFileName));
			BufferedReader brTREE = new BufferedReader(new InputStreamReader(inTREE));
			String newick=""; String treeFileLine="";
			while ((treeFileLine=brTREE.readLine()) != null) { newick+=treeFileLine.trim(); };
			processNewickGeneTree(newick, ai, pathToDumpDir+"/Raw_Tree_Files/"+kbTreeID+".newick");
			
			
			// WRITE THE ALIGNMENT RECORD IN ALIGNMENT.TAB FILE
			System.out.println("     | writing alignment/tree meta data info ");
			BW_aln.write(kbAlnID+"\t"); // kb_aln_id	 M	 unique kbase id reserved for the alignment from ID server: 'kb|aln.XXXXX'
			BW_aln.write(ai.n_rows+"\t");   // n_rows	 M	 number of rows in the alignment, must be an integer valued 1 or greater
			BW_aln.write(ai.n_cols+"\t");   // n_cols	 R	 number of columns in the alignment, must be an integer valued 1 or greater
			BW_aln.write("active\t");       // status	 M	 string indicating if the alignment is "active", "superseded" or "bad"
			BW_aln.write("0\t");            // is_concatenation	 M	 boolean to indicate if the alignment was composed by concatenating multiple alignments together; use numeric 0 and 1
			BW_aln.write("Protein\t");      // sequence_type	 M	 string indicating the type of sequence; initial support should include "Protein", "DNA", "RNA", and "Mixed"; the first letter needs to be capitalized for protein and mixed
			BW_aln.write(TIMESTAMP+"\t");      // timestamp	 M	 the time at which this alignment was loaded into KBase. Other timestamps can be added to AlignmentAttribute?; the time format is an integer indicating seconds since epoch
			BW_aln.write(ALN_METHOD+"\t");  // method	 R	 string that either maps to another object that captures workflows, or is simple alignment method name, e.g. "MOPipeline"
			BW_aln.write(ALN_PARAM+"\t");       // parameters	 R	 free form string that might be a hash to provide additional alignment parameters e.g., the program option values used
			BW_aln.write("MO Pipeline\t"); // protocol	 O	 human readable description of the alignment, if needed
			BW_aln.write("KBase\t");           // source_db	 M	 the database where this alignment originated, eg MO, SEED
			BW_aln.write(name);      // source_db_aln_id	 M	 the id of this alignment in the original database
			BW_aln.write("\n");
			BW_aln.flush();
						
			// WRITE THE TREE RECORD IN TREE.TAB FILE
			BW_trees.write(kbTreeID+"\t");  // kb_tree_id	 M	 unique kbase id reserved for the tree from ID server: 'kb|tree.XXXXX'
			BW_trees.write(kbAlnID+"\t");   // kb_aln_id	 M	 the kbase id of the alignment from which this tree was built
			BW_trees.write("active\t");        // status	 M	 string indicating if the tree is "active", "superseded" or "bad"
			BW_trees.write("sequence_alignment\t");  // data_type	 M	 lowercase string indicating the type of data this tree is built from; we set this to "sequence_alignment" for all alignment-based trees, but we may support "taxonomy", "gene_content" trees and more in the future
			BW_trees.write(TIMESTAMP+"\t");      // timestamp	 M	 the time at which this alignment was loaded into KBase. Other timestamps can be added to AlignmentAttribute?; the time format is an integer indicating seconds since epoch
			BW_trees.write("FastTree2\t");  // method	 R	 string that either maps to another object that captures workflows, or is simple alignment method name, e.g. "MOPipeline"
			BW_trees.write("-fastest\t");       // parameters	 R	 free form string that might be a hash to provide additional alignment parameters e.g., the program option values used
			BW_trees.write("MO Pipeline\t"); // protocol	 O	 human readable description of the alignment, if needed
			BW_trees.write("KBase\t");           // source_db	 M	 the database where this alignment originated, eg MO, SEED
			BW_trees.write(name);      // source_db_aln_id	 M	 the id of this alignment in the original database   
			BW_trees.write("\n");
			BW_trees.flush();
			
			// write the alignment row information
			writeAlignmentRowDataForProtAln(kbAlnID, ai);
					
			// WRITE ANY OTHER ATTRIBUTES WE NEED TO THE APPROPRIATE FILES
			BW_alnAttribute.write(kbAlnID+"\t"+"seeded_by_src\tMOL:"+treeGroupName+"\n");
			BW_alnAttribute.write(kbAlnID+"\t"+"seeded_by_id\t"+name+"\n");
			BW_alnAttribute.flush();
						
			BW_treeAttribute.write(kbTreeID+"\t"+"seeded_by_src\tMOL:"+treeGroupName+"\n");
			BW_treeAttribute.write(kbTreeID+"\t"+"seeded_by_id\t"+name+"\n");
			BW_treeAttribute.write(kbTreeID+"\t"+"style\t"+"phylogram\n");
			BW_treeAttribute.write(kbTreeID+"\t"+"bootstrap_type\t"+"Shimodaira-Hasegawa Test\n");
			BW_treeAttribute.write(kbTreeID+"\t"+"rooted\t"+"midpoint\n");
			BW_treeAttribute.flush();
			
			long estimatedIterationTime = System.currentTimeMillis() - iterationStartTime;
			System.out.println("     | iteration time="+estimatedIterationTime*0.001+"s");
			double estimatedTimeRemaining = ((((System.currentTimeMillis()-processStartTime)/row_count)*(treeNames.size()-row_count))*0.001/60);
			estimatedTimeRemaining = (double)Math.round(estimatedTimeRemaining * 10) / 10;
			System.out.println("     | estimated time remaining="+estimatedTimeRemaining+" min");
			
			
			row_count++;
		}
	}


	
	
	protected void fetchFeatureInfoFromCS(AlignmentInformation ai) throws Exception {
		
		System.out.println("     | fetching protein sequence id and length for each row");
		int row_count = 0;
		
		// construct the list of feature ids in this alignment
		StringBuilder sb = new StringBuilder(SQL_ID_BATCH_SIZE*25);
		for(int i=0; i<ai.n_rows; i++) {
			sb.append(",'"+ai.kbFeatureIds.get(i)+"'");
			if(i%SQL_ID_BATCH_SIZE==0) {
				// start at index 1 to get rid of leading comma
				row_count += fetchBatchSet(sb.toString().substring(1), ai);
				sb = new StringBuilder(SQL_ID_BATCH_SIZE*25);
			}
		}
		String query = sb.toString();
		if(!query.isEmpty()) {
			row_count += fetchBatchSet(query.substring(1), ai);
		}
		System.out.println("     | CS query retrieved "+row_count+" rows");
	}
	
	protected int fetchBatchSet(String idlist, AlignmentInformation ai) throws Exception {
		String query = "SELECT to_link,from_link " +
				"FROM IsProteinFor WHERE " +
				"IsProteinFor.to_link IN ("+idlist+")";
		int row_count=0;
		StringBuilder sb = new StringBuilder(SQL_ID_BATCH_SIZE*25);
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			// pos1= feature id; pos2= protein MD5; pos3= protein length
			String featureId = rs.getString(1);
			String md5 = rs.getString(2);
			ai.parentProteinMd5.put(featureId,md5);
			sb.append(",'"+md5+"'");
			row_count++;
		}
		rs.close();
		
		// now fetch the protein sequence length
		query = "SELECT id,LENGTH(sequence) " +
				"FROM ProteinSequence WHERE " +
				"ProteinSequence.id IN ("+sb.toString().substring(1)+")";
		rs = st.executeQuery(query);
		while (rs.next()) {
			// pos1= feature id; pos2= protein MD5; pos3= protein length
			String md5 = rs.getString(1);
			int length = rs.getInt(2);
			ai.parentProteinLength.put(md5,new Integer(length));
		}
		
		return row_count;
	}
	
	
	protected String [] getKbaseIds(String name) throws IOException {
		String [] ids = treeIdLookupTable.get(name);
		if(ids==null)
			throw new IOException("registered kbase ids for "+name+" not found!");
		return ids;
	}
	

	static class AlignmentInformation {
		// need to be vectors because they are created as the file is read
		ArrayList <String> ids;
		ArrayList <String> kbFeatureIds;
		String replacementIdMappingString; // in the form fromName1;toName1;...fromNameN;toNameN;
		ArrayList <String> descriptions;
		
		// keys are kb feature ids, data is info on the protein
		HashMap<String,String> parentProteinMd5;
		HashMap<String,Integer> parentProteinLength;
		
		// can be arrays for efficiency since they are computed after everything
		// has been read in already
		ArrayList <Integer> row_start_pos_in_alignment;
		ArrayList <Integer> row_end_pos_in_alignment;
		ArrayList <String> MD5ofGapRemovedSequences;
		
		// info on parent seq
		ArrayList <Integer> begin_pos_in_parent;
		ArrayList <Integer> end_pos_in_parent;
		
		// rows and columns of the alignment
		int n_rows;
		int n_cols;
		
		AlignmentInformation() {
			int initialCapacity = 3000;
			ids = new ArrayList <String> (initialCapacity);
			kbFeatureIds = new ArrayList <String> (initialCapacity);
			descriptions = new ArrayList <String> (initialCapacity);
			replacementIdMappingString = "";
			parentProteinMd5 = new HashMap <String,String> (initialCapacity);
			parentProteinLength = new HashMap <String,Integer> (initialCapacity);
			
			row_start_pos_in_alignment = new ArrayList <Integer> (initialCapacity);
			row_end_pos_in_alignment = new ArrayList <Integer> (initialCapacity);
			MD5ofGapRemovedSequences = new ArrayList <String> (initialCapacity);
			
			begin_pos_in_parent = new ArrayList <Integer> (initialCapacity);
			end_pos_in_parent = new ArrayList <Integer> (initialCapacity);
			
			n_rows=0;
			n_cols=0;
		}
	}
	
	
	
	protected AlignmentInformation processAlignmentFile(String inputPath, String outputPath) throws IOException {
		AlignmentInformation ai = new AlignmentInformation();
		
		File file =new File(outputPath);
		if(!file.exists()){ file.createNewFile(); }
		BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
		
		HashMap <String,Boolean> foundIds = new HashMap<String,Boolean>(); // something to let us know if we find duplicate ids
		StringBuilder replacementIdMapping = new StringBuilder(10000);
		DataInputStream in = new DataInputStream(new FileInputStream(inputPath));
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line="",nextLine="", id=""; int currentPos = 0;
		while (true)   {
			if(nextLine.length()>0) { line=nextLine; nextLine=""; }
			else {
				line=br.readLine();
				if(line==null) { break; }
			}
			line=line.trim();
			if(line.startsWith(">")) {
				// drop the line identifier and split on whitespace
				String [] tokens = (line.substring(1)).split("\\s");
				if(tokens.length==0) { throw new IOException("error in parsing fasta file: " + inputPath); }
				id = tokens[0];
				ai.descriptions.add(new String( (line.substring(id.length()+1)).trim() ));
				
				// populate the locus ids and start/stop pos based on the FASTA ID string
				String [] idTokens = id.split("_");
				String replacementId = new String("kb|"+idTokens[1]+"_"+idTokens[2]+"_"+idTokens[3]);
				ai.ids.add(replacementId);
				ai.kbFeatureIds.add("kb|"+idTokens[1]);
				replacementIdMapping.append(id+";"+replacementId+";");
				currentPos = ai.ids.size()-1;
				if(foundIds.containsKey(replacementId)) {
					throw new IOException("cannot read alignment file ("+inputPath+"), duplicate id: "+replacementId);
				}
				foundIds.put(replacementId,new Boolean(true));
				
				ai.begin_pos_in_parent.add(Integer.parseInt(idTokens[2]));
				ai.end_pos_in_parent.add(Integer.parseInt(idTokens[3]));

				// Loop over the next lines until we get to the next comment or and end of file null
				// to grab that sequence
				StringBuilder sequenceBuilder = new StringBuilder(Integer.parseInt(idTokens[3]));
				while(true) {
					line=br.readLine();
					if(line==null) { break; }
					line=line.trim();
					if(line.startsWith(">")) {nextLine=line; break; }
					sequenceBuilder.append(line);
				}
				String sequence = sequenceBuilder.toString();
				if(ai.n_cols==0) { ai.n_cols = sequence.length(); }
				if(ai.n_cols!=0 && ai.n_cols!=sequence.length())
					throw new IOException ("alignment ("+inputPath+") has sequences that are not all the same length! "+sequence.length()+" vs. "+ai.n_cols);
					
				// trim ends to determine where in the alignment index this row starts and ends
				for(int i=0; i<sequence.length(); i++) {
					if(sequence.charAt(i)!='-' && sequence.charAt(i)!=' ') {
						ai.row_start_pos_in_alignment.add(i+1);
						break;
					}
				}
				for(int i=sequence.length()-1; i>=0; i--) {
					if(sequence.charAt(i)!='-' && sequence.charAt(i)!=' ') {
						ai.row_end_pos_in_alignment.add(i+1);
						break;
					}
				}
				
				// remove gaps and compute the MD5
				ai.MD5ofGapRemovedSequences.add(computeMD5(sequence.replace("-","")));
				// write the row to the alignment file
				writeNextFASTAfileEntry(ai.ids.get(currentPos),ai.descriptions.get(currentPos), sequence, out);
			}
		}
		ai.n_rows=ai.ids.size();
		ai.replacementIdMappingString = replacementIdMapping.toString();
		//Close the input and output streams
		in.close();
		out.close();
		
		return ai;
	}
	
	public static void writeNextFASTAfileEntry(String id, String description, String sequence, BufferedWriter bw) {
		try {
			bw.write(">"+id+" "+description+"\n");
			bw.write(sequence+"\n");
		} catch (IOException e) { System.err.println("IOException when writing FASTA file: "+e.getMessage()); }
	}
	
	
	
	
	
	protected void processNewickGeneTree(String newick, AlignmentInformation ai, String finalTreeFilename) throws IOException
	{
		//NOTE FROM MO DOC: For gene trees, the leaf ids are of the form locusId_version_begin_end or locusId_begin_end. 
		//                : For species trees, the leaf ids are taxonomy ids.
		// Parse the newick string
		boolean verboseTreeOutput = false;
		boolean assumeInternalNodeNamesAreBootstrapValues=true;
		KBTree t = new KBTree(newick,verboseTreeOutput,assumeInternalNodeNamesAreBootstrapValues);
		
		// remove the private genes (keeping some tallies to make sure we got em all)
		long originalLeafCount = t.getLeafCount(); long originalNodeCount = t.getNodeCount();
		System.out.println("     | parsed raw tree, node count="+originalNodeCount+", leaf count="+originalLeafCount);
		
		t.replaceNodeNames(ai.replacementIdMappingString);
		
		if(t.getLeafCount() != ai.n_rows) {
			throw new IOException("cannot write newick, leaf count ("+t.getLeafCount()+") and alignment row count ("+ai.n_rows+") mismatch!");
		}
		// write the simplified tree to newick format
		t.writeNewickToFile(finalTreeFilename);
		System.out.println("     | wrote relabeled tree to file: "+finalTreeFilename);
	}
	

	
	
	protected void writeAlignmentRowDataForProtAln(String KBaseAlnID, AlignmentInformation ai) throws IOException
	{
		int currentRow = 1;
		for(int k=0; k<ai.ids.size(); k++) {			
			BW_aln_row.write(KBaseAlnID+"\t");    // kb_aln_id	 M	 unique kbase id reserved for the alignment from ID server
			BW_aln_row.write(currentRow+"\t");    // row_number	 M	 row number in the alignment file, count starts at '1'
			BW_aln_row.write(ai.ids.get(k)+"\t"); // row_id	 M	 identifier for this row which must be unique in this alignment and must also be the first word in the alignment fasta file.
			BW_aln_row.write(ai.descriptions.get(k)+"\t");     // row_description	 O	 optional text description of the row copied from the original fasta file
			BW_aln_row.write(1+"\t");             // n_components	 M	 the number of components (e.g. concatenated sequences) that correspond to this alignment row
			BW_aln_row.write(ai.row_start_pos_in_alignment.get(k)+"\t");  //beg_pos_in_aln	 R	 the column (index starting at pos '1') in the alignment where this sequence row begins (ie. truncating any ending gaps)
			BW_aln_row.write(ai.row_end_pos_in_alignment.get(k)+"\t");    //end_pos_in_aln	 R	 the column (index starting at pos '1') in the alignment where this sequence row ends (ie. truncating any ending gaps)
			BW_aln_row.write(ai.MD5ofGapRemovedSequences.get(k));            // md5_of_ungapped_seq	 M	 the MD5 (uppercase) of the aligned sequence on this row with gaps stripped; U should be converted to T in nucleotide sequences (even for RNA)
			BW_aln_row.write("\n");
			
			// get data regarding this sequence
			String featureId = ai.kbFeatureIds.get(k);
			String parentMd5 = ai.parentProteinMd5.get(featureId);
			if(parentMd5==null)
				throw new IOException("Error in processing! Feature "+featureId+" was not found in the CS!  aborting!");
			Integer parentLength = ai.parentProteinLength.get(parentMd5);
			if(parentLength==null)
				throw new IOException("Error in processing! Protein "+parentMd5+" was not found in the CS!  aborting!");
			
			BW_containsProtein.write(KBaseAlnID+"\t");    // kb-aln-id	 M	 maps this component to a particular alignment identified by the kbase id
			BW_containsProtein.write(currentRow+"\t");    // aln-row-number	 M	 row number in alignment file, count starts at '1'
			BW_containsProtein.write(1+"\t");             // index-in-concatenation	 M	 ordering starting from left to right in alignment row starting at '1'
			BW_containsProtein.write(parentMd5+"\t");     // parent-seq-id	 M	 MD5 for protein sequence
			BW_containsProtein.write(ai.begin_pos_in_parent.get(k)+"\t");           // beg-pos-in-parent	 M	 the alignment includes the original sequence starting at this postion, 1-based
			BW_containsProtein.write(ai.end_pos_in_parent.get(k)+"\t");             // end-pos-in-parent	 M	 the alignment includes the original sequence ending at this postion, 1-based
			BW_containsProtein.write(parentLength.intValue()+"\t");     // parent-seq-len	 M	 the length of the untrimmed sequence for quick reference of the coverage of this alignment
			BW_containsProtein.write(ai.row_start_pos_in_alignment.get(k)+"\t");   // beg-pos-in-aln	 M	 integer value providing a coordinate/mapping to the starting column in the alignment where this sequence component begins
			BW_containsProtein.write(ai.row_end_pos_in_alignment.get(k)+"\t");     // end-pos-in-aln	 M	 integer value providing a coordinate/mapping to the ending column in the alignment where this sequence component ends
			BW_containsProtein.write(featureId);                           // kb-feature-id	 O	 associated kbase feature id, e.g., when intending to refer to a particular genome
			BW_containsProtein.write("\n");
			currentRow++;
		}
		BW_aln_row.flush();
		BW_containsProtein.flush();
	}
	
	

		
	/** given a sequence, compute the MD5 in the same style as KBase */
	public String computeMD5(String MD5) {
		//String testSeq = "MIQEENQNMSFAQVISILGVILMGSFVTILNQTLMSTALPSIMREFSITATQGQWLTTAYMLINGIMVPITAY
		//LVNRFTTRQLYLFSMIVFSIGTFVAATSSVYGVLIAGRMIQAVGAGIILPLQMIVVLYIFPIEKRGSAMGLIGLAMNFAPAIGPTFSGWVVQN
		//YHWNMLFYFILPFAIIDVIVAIFVLKNVGKTGRPKLDLISVIYSTLGFGGLLFGLSNASSHDFISVNVALPIVVGIISLILLVNRSNHSKEPL
		//LNFGIFKYRGYRLNLIISFVLTAGMYGAIMLLPIYLQNIREMTPMESGMTLLPGAIVMAVMSPITGRMFDKYGSKRLAMSGLTLVTIGTFIIG
		//LIDLNTPIVYIVLLQVVRSLGFALTLMPIQTAAFNAVPLELASHASAMFNTQRQLAGSMGTALFVVVMTVVSQNGVSQHLPSELADLAGFQTVF
		//KLVGVFSLVAFIMTLFIKENPYLPKAKLAENH";
		//System.out.println(computeMD5(testSeq));
		// should be: 05b66e1a9fef4a63878e80febcbd6b95
		//System.exit(1);
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(MD5.getBytes(java.nio.charset.Charset.forName("UTF8")));
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) { }
		return null;
	}

	/** method that overwrites whatever already exists, use with caution */
	public BufferedWriter openOutputFileStreamNoMatterWhat(String absolute_path) {
		BufferedWriter bw = null;
		try {
			File file =new File(absolute_path);
			if(!file.exists()){
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			System.out.println(" -> created file \""+absolute_path+"\"");
		} catch (IOException ex) {
		    System.err.println("could not create file: "+absolute_path);
			System.err.println("IOException: "+ex.getMessage());
		        ex.printStackTrace();
		}
		return bw;
	}
	
	
	/* can't believe I have to define this code!  but java.nio does not seem to exist on my java install */
	public static void copy(String f1, String f2) throws IOException {
		File fromFile = new File(f1);
		File toFile = new File(f2);

		if (!fromFile.exists()) throw new IOException("FileCopy: " + "no such source file: "+ f1);
		if (!fromFile.isFile()) throw new IOException("FileCopy: " + "can't copy directory: "+ f2);
		if (!fromFile.canRead()) throw new IOException("FileCopy: " + "source file is unreadable: "+ f1);
		if (toFile.isDirectory()) toFile = new File(toFile, fromFile.getName());
		
		// i don't care if the file exists, this will overwrite
		String parent = toFile.getParent();
		if (parent == null) parent = System.getProperty("user.dir");
		File dir = new File(parent);
		if (!dir.exists()) throw new IOException("FileCopy: "+ "destination directory doesn't exist: " + parent);
		if (dir.isFile()) throw new IOException("FileCopy: " + "destination is not a directory: " + parent);
		if (!dir.canWrite()) throw new IOException("FileCopy: " + "destination directory is unwriteable: " + parent);

		FileInputStream from = new FileInputStream(fromFile);
		FileOutputStream to = new FileOutputStream(toFile);
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = from.read(buffer)) != -1) {
			to.write(buffer, 0, bytesRead);
		}
		from.close();
	        to.close();
	}

}
