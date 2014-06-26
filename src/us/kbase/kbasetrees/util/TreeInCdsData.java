package us.kbase.kbasetrees.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeInCdsData {

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
	}

	public void setIsTreeFrom_source(String isTreeFrom_source) {
		this.isTreeFrom_source = isTreeFrom_source;
	}

	public void setIsBuiltFrom_alignment_id(String isBuiltFrom_alignment_id) {
		this.isBuiltFrom_alignment_id = isBuiltFrom_alignment_id;
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
		return "TreeInCdsData [tree_id=" + tree_id + ", tree_data_type="
				+ tree_data_type + ", tree_timestamp=" + tree_timestamp
				+ ", tree_method=" + tree_method + ", tree_parameters="
				+ tree_parameters + ", tree_protocol=" + tree_protocol
				+ ", tree_source_id=" + tree_source_id + ", isTreeFrom_source="
				+ isTreeFrom_source + ", hasTreeAttribute=" + hasTreeAttribute
				+ ", isBuiltFrom_alignment_id=" + isBuiltFrom_alignment_id
				+ "]";
	}

	public void addTreeAttribute(String key, String value) {
		this.hasTreeAttribute.put(key,value);
	}
	
}
