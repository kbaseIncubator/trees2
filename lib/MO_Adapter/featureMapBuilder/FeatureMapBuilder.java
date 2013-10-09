

import java.util.*;
import java.io.*;

/**
 * # code to dump the feature and sequence map to file
 * nohup mysql -e "SELECT id AS pid, LENGTH(sequence) AS length FROM ProteinSequence LIMIT 100" -hfir.mcs.anl.gov -P3306 -ukbase_sapselect -pkbase4me2 kbase_sapling_v2 > prot_size_mapping.txt &
 * nohup mysql -e "SELECT to_link AS fid, from_link AS pid FROM IsProteinFor" -hfir.mcs.anl.gov -P3306 -ukbase_sapselect -pkbase4me2 kbase_sapling_v2 > fid_mapping.txt &
 *
 * NOTE: if running on the full database, be sure to bump up the jvm memory size!  (as of Oct 2013, need ~40gb)
 *
 * reads two tab-delimitied files into hashmaps, serializes them so they can be
 * loaded into memory quickly later
 */
public class FeatureMapBuilder {

    
    // tab delimited file, with header, containing:
    // feature_id   protein_md5
    // WARNING!  FIRST ROW IS ASSUMED TO BE HEADER AND IS THROWN OUT
    public static final String featureMapFile = "featureMapExample.txt"; //"feature_map/fid_mapping.txt";
    
    // tab delimited file, with header, containing:
    // protein_md5  length
    // WARNING!  FIRST ROW IS ASSUMED TO BE HEADER AND IS THROWN OUT
    public static final String protSequenceLengthFile = "proteinLengthExample.txt"; // "feature_map/prot_size_mapping.txt";
    
    
    public static final int EXPECTED_FEATURE_COUNT = 500; // 46639160;
    public static final int EXPECTED_PROTEIN_COUNT = 500; // 32880435;
    

    public static void main(String[] args) throws Exception {
        buildFeatureMap();
        printSerializedFeatureMap();
        buildProteinLengthMap();
        printSerializedProtLenMap();
    }
    
    
    private static void buildFeatureMap() throws Exception {
        System.out.println("allocating feature map storage");
        HashMap <String,String> featureMap = new HashMap <String,String> (EXPECTED_FEATURE_COUNT);
        System.out.println("reading: "+featureMapFile);
        BufferedReader br = new BufferedReader(new FileReader(featureMapFile));
        String line; String [] tokens; boolean isFirstRow = true;
        while ((line = br.readLine()) != null) {
            if(isFirstRow){ isFirstRow = false; continue; }
            tokens = line.split("\\t");
            featureMap.put(tokens[0],tokens[1]);
        }
        br.close();
        System.out.println("processed "+featureMap.size()+" rows");
        System.out.println("serializing feature map");
        OutputStream os = new BufferedOutputStream(new FileOutputStream(featureMapFile+".serialized"));
        ObjectOutput output = new ObjectOutputStream(os);
        output.writeObject(featureMap);
        output.close();
        os.close();
        System.out.println("done.");
    }
    
    private static void printSerializedFeatureMap() throws Exception {
        InputStream buffer = new BufferedInputStream(new FileInputStream(featureMapFile+".serialized"));
        ObjectInput input = new ObjectInputStream (buffer);
        @SuppressWarnings("unchecked")
        HashMap<String,String> recoveredFeatures = (HashMap<String,String>)input.readObject();
        //display its data
        for(Map.Entry<String,String> entry: recoveredFeatures.entrySet()){
            System.out.println("  --recovered: " + entry.getKey()+"  -> "+entry.getValue());
        }
    }
    
    
    private static void buildProteinLengthMap() throws Exception {
        HashMap <String,Integer> protLenMap = new HashMap <String,Integer> (EXPECTED_PROTEIN_COUNT);
        System.out.println("allocating protein length map storage");
        System.out.println("reading: "+protSequenceLengthFile);
        BufferedReader br = new BufferedReader(new FileReader(protSequenceLengthFile));
        String line; String [] tokens; boolean isFirstRow = true;
        while ((line = br.readLine()) != null) {
            if(isFirstRow){ isFirstRow = false; continue; }
            tokens = line.split("\\t");
            protLenMap.put(tokens[0],Integer.valueOf(tokens[1]));
        }
        br.close();
        System.out.println("processed "+protLenMap.size()+" rows");
        System.out.println("serializing protein length map");
        OutputStream os = new BufferedOutputStream(new FileOutputStream(protSequenceLengthFile+".serialized"));
        ObjectOutput output = new ObjectOutputStream(os);
        output.writeObject(protLenMap);
        output.close();
        os.close();
        System.out.println("done.");
    }
    
    private static void printSerializedProtLenMap() throws Exception {
        InputStream buffer = new BufferedInputStream(new FileInputStream(protSequenceLengthFile+".serialized"));
        ObjectInput input = new ObjectInputStream (buffer);
        @SuppressWarnings("unchecked")
        HashMap<String,Integer> recoveredProts = (HashMap<String,Integer>)input.readObject();
        //display its data
        for(Map.Entry<String,Integer> entry: recoveredProts.entrySet()){
            System.out.println("  --recovered: " + entry.getKey()+"  -> "+entry.getValue());
        }
    }
    
    
}