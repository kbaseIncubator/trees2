
package us.kbase.kbasetaxonomy;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: GenomeTaxon</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome_ref",
    "taxon_ref"
})
public class GenomeTaxon {

    @JsonProperty("genome_ref")
    private String genomeRef;
    @JsonProperty("taxon_ref")
    private String taxonRef;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genome_ref")
    public String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public GenomeTaxon withGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("taxon_ref")
    public String getTaxonRef() {
        return taxonRef;
    }

    @JsonProperty("taxon_ref")
    public void setTaxonRef(String taxonRef) {
        this.taxonRef = taxonRef;
    }

    public GenomeTaxon withTaxonRef(String taxonRef) {
        this.taxonRef = taxonRef;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((("GenomeTaxon"+" [genomeRef=")+ genomeRef)+", taxonRef=")+ taxonRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
