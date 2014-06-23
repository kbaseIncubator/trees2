
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
 * <p>Original spec-file type: fields_TaxonomicGrouping</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "domain",
    "hidden",
    "scientific_name",
    "alias"
})
public class FieldsTaxonomicGrouping {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("domain")
    private Long domain;
    @JsonProperty("hidden")
    private Long hidden;
    @JsonProperty("scientific_name")
    private java.lang.String scientificName;
    @JsonProperty("alias")
    private List<String> alias;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsTaxonomicGrouping withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("domain")
    public Long getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(Long domain) {
        this.domain = domain;
    }

    public FieldsTaxonomicGrouping withDomain(Long domain) {
        this.domain = domain;
        return this;
    }

    @JsonProperty("hidden")
    public Long getHidden() {
        return hidden;
    }

    @JsonProperty("hidden")
    public void setHidden(Long hidden) {
        this.hidden = hidden;
    }

    public FieldsTaxonomicGrouping withHidden(Long hidden) {
        this.hidden = hidden;
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

    public FieldsTaxonomicGrouping withScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
        return this;
    }

    @JsonProperty("alias")
    public List<String> getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public FieldsTaxonomicGrouping withAlias(List<String> alias) {
        this.alias = alias;
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
        return ((((((((((((("FieldsTaxonomicGrouping"+" [id=")+ id)+", domain=")+ domain)+", hidden=")+ hidden)+", scientificName=")+ scientificName)+", alias=")+ alias)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
