package us.kbase.kbasetrees.util;

/** basic wrapper class for CDS feature data */
public class FeatureDataBasic {

	protected String genomeId;
	protected String genomeScientificName;
	protected String function;
	
	public FeatureDataBasic() {};
	
	public FeatureDataBasic(String genomeId, String genomeScientificName, String function) {
		this.genomeId = genomeId;
		this.genomeScientificName = genomeScientificName;
		this.function = function;
	}
	
	public String getGenomeId() {
		return genomeId;
	}
	public String getFunction() {
		return function;
	}
	public String getGenomeScientificName() {
		return genomeScientificName;
	}

	public void setGenomeId(String genomeId) {
		this.genomeId = genomeId;
	}

	public void setGenomeScientificName(String genomeScientificName) {
		this.genomeScientificName = genomeScientificName;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public String toString() {
		return "FeatureDataBasic [genomeId=" + genomeId
				+ ", genomeScientificName=" + genomeScientificName
				+ ", function=" + function + "]";
	}
}
