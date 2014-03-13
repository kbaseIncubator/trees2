
package us.kbase.tree;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: AbundanceResult</p>
 * <pre>
 * Structure to group output of the compute_abundance_profile method.
 *     mapping <string,int> abundances - maps the raw row ID of each leaf node in the input tree to the number
 *                                       of hits that map to the given leaf; only row IDs with 1 or more hits
 *                                       are added to this map, thus missing leaf nodes imply 0 hits
 *     int n_hits                      - the total number of hits in this sample to any leaf
 *     int n_reads                     - the total number of reads that were identified for the input protein
 *                                       family; if the protein family could not be found this will be zero.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "abundances",
    "n_hits",
    "n_reads"
})
public class AbundanceResult {

    @JsonProperty("abundances")
    private Map<String, Long> abundances;
    @JsonProperty("n_hits")
    private java.lang.Long nHits;
    @JsonProperty("n_reads")
    private java.lang.Long nReads;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("abundances")
    public Map<String, Long> getAbundances() {
        return abundances;
    }

    @JsonProperty("abundances")
    public void setAbundances(Map<String, Long> abundances) {
        this.abundances = abundances;
    }

    public AbundanceResult withAbundances(Map<String, Long> abundances) {
        this.abundances = abundances;
        return this;
    }

    @JsonProperty("n_hits")
    public java.lang.Long getNHits() {
        return nHits;
    }

    @JsonProperty("n_hits")
    public void setNHits(java.lang.Long nHits) {
        this.nHits = nHits;
    }

    public AbundanceResult withNHits(java.lang.Long nHits) {
        this.nHits = nHits;
        return this;
    }

    @JsonProperty("n_reads")
    public java.lang.Long getNReads() {
        return nReads;
    }

    @JsonProperty("n_reads")
    public void setNReads(java.lang.Long nReads) {
        this.nReads = nReads;
    }

    public AbundanceResult withNReads(java.lang.Long nReads) {
        this.nReads = nReads;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((("AbundanceResult"+" [abundances=")+ abundances)+", nHits=")+ nHits)+", nReads=")+ nReads)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
