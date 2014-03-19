package us.kbase.kbasetrees.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description of a GenomeItem (from the "items" list that is generated as
 * search results). This describes most of the genome information that the
 * Search service provides.
 * This will eventually be supplanted by a real Search API, but is useful
 * for decorating trees with genome information for now.
 * @author wjriehl
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenomeItem {
	
	private boolean complete;
	private String domain;
	@JsonProperty("gc_content")
	private float gcContent;
	@JsonProperty("genome_dna_size")
	private long genomeDnaSize;
	@JsonProperty("genome_id")
	private String genomeId;
	@JsonProperty("genome_source_id")
	private String genomeSourceId;
	@JsonProperty("genome_source")
	private String genomeSource;	
	@JsonProperty("num_contigs")
	private int numContigs;
	@JsonProperty("object_type")
	private String objectType;
	@JsonProperty("scientific_name")
	private String scientificName;
	private String taxonomy;
	@JsonProperty("object_id")
	private String objectId;
	
	public String getGenomeSource() {
		return genomeSource;
	}
	public void setGenomeSource(String genomeSource) {
		this.genomeSource = genomeSource;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public float getGcContent() {
		return gcContent;
	}
	public void setGcContent(float gcContent) {
		this.gcContent = gcContent;
	}
	public long getGenomeDnaSize() {
		return genomeDnaSize;
	}
	public void setGenomeDnaSize(long genomeDnaSize) {
		this.genomeDnaSize = genomeDnaSize;
	}
	public String getGenomeId() {
		return genomeId;
	}
	public void setGenomeId(String genomeId) {
		this.genomeId = genomeId;
	}
	public String getGenomeSourceId() {
		return genomeSourceId;
	}
	public void setGenomeSourceId(String genomeSourceId) {
		this.genomeSourceId = genomeSourceId;
	}
	public int getNumContigs() {
		return numContigs;
	}
	public void setNumContigs(int numContigs) {
		this.numContigs = numContigs;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	public String getTaxonomy() {
		return taxonomy;
	}
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}
	
	
}
