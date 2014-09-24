
package us.kbase.kbasetaxonomy;

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
 * <p>Original spec-file type: Taxon</p>
 * <pre>
 * Data type for taxonomy node.
 * @optional parent_id parent_ref rank mito_genetic_code division
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "tax_id",
    "parent_id",
    "parent_ref",
    "name",
    "aliases",
    "rank",
    "genetic_code",
    "mito_genetic_code",
    "division"
})
public class Taxon {

    @JsonProperty("tax_id")
    private Long taxId;
    @JsonProperty("parent_id")
    private Long parentId;
    @JsonProperty("parent_ref")
    private java.lang.String parentRef;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("aliases")
    private List<String> aliases;
    @JsonProperty("rank")
    private java.lang.String rank;
    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("mito_genetic_code")
    private Long mitoGeneticCode;
    @JsonProperty("division")
    private java.lang.String division;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("tax_id")
    public Long getTaxId() {
        return taxId;
    }

    @JsonProperty("tax_id")
    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Taxon withTaxId(Long taxId) {
        this.taxId = taxId;
        return this;
    }

    @JsonProperty("parent_id")
    public Long getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Taxon withParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    @JsonProperty("parent_ref")
    public java.lang.String getParentRef() {
        return parentRef;
    }

    @JsonProperty("parent_ref")
    public void setParentRef(java.lang.String parentRef) {
        this.parentRef = parentRef;
    }

    public Taxon withParentRef(java.lang.String parentRef) {
        this.parentRef = parentRef;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Taxon withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("aliases")
    public List<String> getAliases() {
        return aliases;
    }

    @JsonProperty("aliases")
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public Taxon withAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    @JsonProperty("rank")
    public java.lang.String getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(java.lang.String rank) {
        this.rank = rank;
    }

    public Taxon withRank(java.lang.String rank) {
        this.rank = rank;
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

    public Taxon withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("mito_genetic_code")
    public Long getMitoGeneticCode() {
        return mitoGeneticCode;
    }

    @JsonProperty("mito_genetic_code")
    public void setMitoGeneticCode(Long mitoGeneticCode) {
        this.mitoGeneticCode = mitoGeneticCode;
    }

    public Taxon withMitoGeneticCode(Long mitoGeneticCode) {
        this.mitoGeneticCode = mitoGeneticCode;
        return this;
    }

    @JsonProperty("division")
    public java.lang.String getDivision() {
        return division;
    }

    @JsonProperty("division")
    public void setDivision(java.lang.String division) {
        this.division = division;
    }

    public Taxon withDivision(java.lang.String division) {
        this.division = division;
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
        return ((((((((((((((((((((("Taxon"+" [taxId=")+ taxId)+", parentId=")+ parentId)+", parentRef=")+ parentRef)+", name=")+ name)+", aliases=")+ aliases)+", rank=")+ rank)+", geneticCode=")+ geneticCode)+", mitoGeneticCode=")+ mitoGeneticCode)+", division=")+ division)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
