package us.kbase.kbasetrees.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNodeData {

	
	Map<String, Map<String, List<String>>> kbRefs;
	
	TreeNodeData(int expectedSize) {
		kbRefs = new HashMap<String,Map<String,List<String>>>(expectedSize);
	}
	
	void addProtRef(String nodeId, String MD5) {
		Map<String,List<String>> node = kbRefs.get(nodeId);
	}
	
	
}
