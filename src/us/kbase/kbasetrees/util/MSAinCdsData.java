package us.kbase.kbasetrees.util;

import java.util.HashMap;
import java.util.Map;

import us.kbase.kbasetrees.MSA;
import us.kbase.workspace.ProvenanceAction;

public class MSAinCdsData {

	protected MSA msa;
	protected ProvenanceAction prov;
	protected Map<String,String> metadata;
	
	public  MSAinCdsData(MSA msa, ProvenanceAction prov) {
		this.msa = msa;
		this.prov = prov;
		this.metadata = new HashMap<String,String>();
	}
	
	public MSA getMsa() { return msa; }
	public ProvenanceAction getProv() { return prov; }
	public Map<String,String> getMetaData() { return metadata; }
	
	
	public void addMetaData(String key, String value) {
		metadata.put(key, value);
	}
	
}
