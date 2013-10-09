

import java.util.*;
import java.io.*;

/**
 * reads two tab-delimitied files into hashmaps, serializes them so they can be
 * loaded into memory quickly later
 */
public class FeatureMapBuilder {

    
    // tab delimited file, with header, containing:
    // feature_id   protein_md5
    // WARNING!  FIRST ROW IS ASSUMED TO BE HEADER AND IS THROWN OUT
    public static final String featureMapFile = "featureMapExample.txt";
    
    // tab delimited file, with header, containing:
    // protein_md5  length
    // WARNING!  FIRST ROW IS ASSUMED TO BE HEADER AND IS THROWN OUT
    public static final String protSequenceLengthFile = "proteinLengthExample.txt";
    
    
    public static final int EXPECTED_FEATURE_COUNT = 500;
    public static final int EXPECTED_PROTEIN_COUNT = 500;
    
    
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
        HashMap <String,String> protLenMap = new HashMap <String,String> (EXPECTED_PROTEIN_COUNT);
        System.out.println("allocating protein length map storage");
        System.out.println("reading: "+protSequenceLengthFile);
        BufferedReader br = new BufferedReader(new FileReader(protSequenceLengthFile));
        String line; String [] tokens; boolean isFirstRow = true;
        while ((line = br.readLine()) != null) {
            if(isFirstRow){ isFirstRow = false; continue; }
            tokens = line.split("\\t");
            protLenMap.put(tokens[0],tokens[1]);
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
        HashMap<String,String> recoveredProts = (HashMap<String,String>)input.readObject();
        //display its data
        for(Map.Entry<String,String> entry: recoveredProts.entrySet()){
            System.out.println("  --recovered: " + entry.getKey()+"  -> "+entry.getValue());
        }
    }
    
    
}