package us.kbase.kbasetrees.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class TreeInCdsData {

	private static int ESTIMATED_NODE_COUNT = 500;
	
	protected String tree_id;
	protected String tree_data_type;
	protected String tree_timestamp;
	protected String tree_method;
	protected String tree_parameters;
	protected String tree_protocol;
	protected String tree_source_id;
	protected String tree_newick;
	
	protected String isTreeFrom_source;
	
	protected HashMap<String, String> hasTreeAttribute;
	
	protected String isBuiltFrom_alignment_id;

	protected Map<String,Map<String,List<String>>> kb_refs;
	
	
	public TreeInCdsData(String tree_id, String tree_data_type,
			String tree_timestamp, String tree_method, String tree_parameters,
			String tree_protocol, String tree_source_id, String tree_newick) {
		super();
		this.tree_id = tree_id;
		this.tree_data_type = tree_data_type;
		this.tree_timestamp = tree_timestamp;
		this.tree_method = tree_method;
		this.tree_parameters = tree_parameters;
		this.tree_protocol = tree_protocol;
		this.tree_source_id = tree_source_id;
		this.tree_newick = tree_newick;
		
		this.hasTreeAttribute = new HashMap<String,String>();
		kb_refs = new HashMap<String,Map<String,List<String>>>(ESTIMATED_NODE_COUNT);
	}

	public void setIsTreeFrom_source(String isTreeFrom_source) {
		this.isTreeFrom_source = isTreeFrom_source;
	}

	public void setIsBuiltFrom_alignment_id(String isBuiltFrom_alignment_id) {
		this.isBuiltFrom_alignment_id = isBuiltFrom_alignment_id;
	}
	
	public void addKbRef(String nodeId, String type, String kbRef) {
		Map<String,List<String>> nodeRefs = kb_refs.get(nodeId);
		if(nodeRefs == null) {
			nodeRefs = new HashMap<String,List<String>>();
			kb_refs.put(nodeId, nodeRefs);
		}
		List<String> refList = kb_refs.get(nodeId).get(type);
		if(refList == null) {
			refList = Arrays.asList(kbRef);
			nodeRefs.put(type, refList);
		} else {
			refList.add(kbRef);
		}
	}
	
	public void removeKbRefOfType(String nodeId, String type) {
		Map<String,List<String>> nodeRefs = kb_refs.get(nodeId);
		if(nodeRefs != null) {
			nodeRefs.remove(type);
		}
	}
	
	/**
	 * we need to get the list of protein refs for nodes that do not have a specific type defined
	 * for instance, give me all protein refs for nodes that do not have a feature ref defined
	 * 
	 * this is needed when we want to get exactly one genome or one feature per node
	 * 
	 */
	public Map<String,List<String>> getProtRefs(String unlessThisTypeIsDefined) {
		Iterator<Entry<String, Map<String, List<String>>>> it = kb_refs.entrySet().iterator();
		Map<String,List<String>> result = new HashMap<String,List<String>>();
		while (it.hasNext()) {
			Entry<String, Map<String, List<String>>> pairs = (Entry<String, Map<String, List<String>>>)it.next();
			List<String> prot = pairs.getValue().get("p");
			if(prot==null) continue;
			if(!pairs.getValue().containsKey(unlessThisTypeIsDefined)) {
				for(String p:prot) {
					if(result.containsKey(p)) {
						result.get(p).add(pairs.getKey());
					} else {
						List<String> node_ids = new ArrayList<String>();
						node_ids.add(pairs.getKey());
						result.put(p, node_ids);
					}
				}
			}
		}
		
		return result;
	}
	
	
	public String getTree_id() {
		return tree_id;
	}

	public String getTree_data_type() {
		return tree_data_type;
	}

	public String getTree_timestamp() {
		return tree_timestamp;
	}

	public String getTree_method() {
		return tree_method;
	}

	public String getTree_parameters() {
		return tree_parameters;
	}

	public String getTree_protocol() {
		return tree_protocol;
	}

	public String getTree_source_id() {
		return tree_source_id;
	}

	public String getTree_newick() {
		return tree_newick;
	}

	public String getIsTreeFrom_source() {
		return isTreeFrom_source;
	}

	public Map<String, String> getHasTreeAttribute() {
		return hasTreeAttribute;
	}

	public String getIsBuiltFrom_alignment_id() {
		return isBuiltFrom_alignment_id;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append( "TreeInCdsData [\n\ttree_id=" + tree_id + ", \n\ttree_data_type="
				+ tree_data_type + ", \n\ttree_timestamp=" + tree_timestamp
				+ ", \n\ttree_method=" + tree_method + ", \n\ttree_parameters="
				+ tree_parameters + ", \n\ttree_protocol=" + tree_protocol
				+ ", \n\ttree_source_id=" + tree_source_id + ", \n\tisTreeFrom_source="
				+ isTreeFrom_source + ", \n\thasTreeAttribute=" + hasTreeAttribute
				+ ", \n\tisBuiltFrom_alignment_id=" + isBuiltFrom_alignment_id
				+ "]\n");
		Iterator<Entry<String, Map<String, List<String>>>> it = kb_refs.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Map<String, List<String>>> pairs = (Entry<String, Map<String, List<String>>>)it.next();
			s.append("\t"+pairs.getKey()+":\n");
			Iterator<Entry<String, List<String>>> it2 = pairs.getValue().entrySet().iterator();
			while (it2.hasNext()) {
				Entry<String, List<String>> pairs2 = (Entry<String, List<String>>)it2.next();
				s.append("\t\t"+pairs2.getKey()+":[");
				List<String> refs = pairs2.getValue();
				for(String r:refs) {
					s.append(r);
				}
				s.append("]\n");
			}
		}
		
		return s.toString();
		
	}

	public void addTreeAttribute(String key, String value) {
		this.hasTreeAttribute.put(key,value);
	}

	
	
}
