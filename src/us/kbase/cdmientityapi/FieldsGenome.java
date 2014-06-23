
package us.kbase.cdmientityapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: fields_Genome</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "pegs",
    "rnas",
    "scientific_name",
    "complete",
    "prokaryotic",
    "dna_size",
    "contigs",
    "domain",
    "genetic_code",
    "gc_content",
    "phenotype",
    "md5",
    "source_id"
})
public class FieldsGenome {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("pegs")
    private Long pegs;
    @JsonProperty("rnas")
    private Long rnas;
    @JsonProperty("scientific_name")
    private java.lang.String scientificName;
    @JsonProperty("complete")
    private Long complete;
    @JsonProperty("prokaryotic")
    private Long prokaryotic;
    @JsonProperty("dna_size")
    private Long dnaSize;
    @JsonProperty("contigs")
    private Long contigs;
    @JsonProperty("domain")
    private java.lang.String domain;
    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("gc_content")
    private Double gcContent;
    @JsonProperty("phenotype")
    private List<String> phenotype;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsGenome withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("pegs")
    public Long getPegs() {
        return pegs;
    }

    @JsonProperty("pegs")
    public void setPegs(Long pegs) {
        this.pegs = pegs;
    }

    public FieldsGenome withPegs(Long pegs) {
        this.pegs = pegs;
        return this;
    }

    @JsonProperty("rnas")
    public Long getRnas() {
        return rnas;
    }

    @JsonProperty("rnas")
    public void setRnas(Long rnas) {
        this.rnas = rnas;
    }

    public FieldsGenome withRnas(Long rnas) {
        this.rnas = rnas;
        return this;
    }

    @JsonProperty("scientific_name")
    public java.lang.String getScientificName() {
        return scientificName;
    }

    @JsonProperty("scientific_name")
    public void setScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
    }

    public FieldsGenome withScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
        return this;
    }

    @JsonProperty("complete")
    public Long getComplete() {
        return complete;
    }

    @JsonProperty("complete")
    public void setComplete(Long complete) {
        this.complete = complete;
    }

    public FieldsGenome withComplete(Long complete) {
        this.complete = complete;
        return this;
    }

    @JsonProperty("prokaryotic")
    public Long getProkaryotic() {
        return prokaryotic;
    }

    @JsonProperty("prokaryotic")
    public void setProkaryotic(Long prokaryotic) {
        this.prokaryotic = prokaryotic;
    }

    public FieldsGenome withProkaryotic(Long prokaryotic) {
        this.prokaryotic = prokaryotic;
        return this;
    }

    @JsonProperty("dna_size")
    public Long getDnaSize() {
        return dnaSize;
    }

    @JsonProperty("dna_size")
    public void setDnaSize(Long dnaSize) {
        this.dnaSize = dnaSize;
    }

    public FieldsGenome withDnaSize(Long dnaSize) {
        this.dnaSize = dnaSize;
        return this;
    }

    @JsonProperty("contigs")
    public Long getContigs() {
        return contigs;
    }

    @JsonProperty("contigs")
    public void setContigs(Long contigs) {
        this.contigs = contigs;
    }

    public FieldsGenome withContigs(Long contigs) {
        this.contigs = contigs;
        return this;
    }

    @JsonProperty("domain")
    public java.lang.String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }

    public FieldsGenome withDomain(java.lang.String domain) {
        this.domain = domain;
        return this;
    }

    @JsonProperty("genetic_code")
    public Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public FieldsGenome withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("gc_content")
    public Double getGcContent() {
        return gcContent;
    }

    @JsonProperty("gc_content")
    public void setGcContent(Double gcContent) {
        this.gcContent = gcContent;
    }

    public FieldsGenome withGcContent(Double gcContent) {
        this.gcContent = gcContent;
        return this;
    }

    @JsonProperty("phenotype")
    public List<String> getPhenotype() {
        return phenotype;
    }

    @JsonProperty("phenotype")
    public void setPhenotype(List<String> phenotype) {
        this.phenotype = phenotype;
    }

    public FieldsGenome withPhenotype(List<String> phenotype) {
        this.phenotype = phenotype;
        return this;
    }

    @JsonProperty("md5")
    public java.lang.String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(java.lang.String md5) {
        this.md5 = md5;
    }

    public FieldsGenome withMd5(java.lang.String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public FieldsGenome withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
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
        return ((((((((((((((((((((((((((((((("FieldsGenome"+" [id=")+ id)+", pegs=")+ pegs)+", rnas=")+ rnas)+", scientificName=")+ scientificName)+", complete=")+ complete)+", prokaryotic=")+ prokaryotic)+", dnaSize=")+ dnaSize)+", contigs=")+ contigs)+", domain=")+ domain)+", geneticCode=")+ geneticCode)+", gcContent=")+ gcContent)+", phenotype=")+ phenotype)+", md5=")+ md5)+", sourceId=")+ sourceId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
