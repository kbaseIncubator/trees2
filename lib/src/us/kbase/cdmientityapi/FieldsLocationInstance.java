
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
 * <p>Original spec-file type: fields_LocationInstance</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "index",
    "label",
    "pH",
    "potential"
})
public class FieldsLocationInstance {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("index")
    private Long index;
    @JsonProperty("label")
    private List<String> label;
    @JsonProperty("pH")
    private Double pH;
    @JsonProperty("potential")
    private Double potential;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsLocationInstance withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("index")
    public Long getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(Long index) {
        this.index = index;
    }

    public FieldsLocationInstance withIndex(Long index) {
        this.index = index;
        return this;
    }

    @JsonProperty("label")
    public List<String> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<String> label) {
        this.label = label;
    }

    public FieldsLocationInstance withLabel(List<String> label) {
        this.label = label;
        return this;
    }

    @JsonProperty("pH")
    public Double getPH() {
        return pH;
    }

    @JsonProperty("pH")
    public void setPH(Double pH) {
        this.pH = pH;
    }

    public FieldsLocationInstance withPH(Double pH) {
        this.pH = pH;
        return this;
    }

    @JsonProperty("potential")
    public Double getPotential() {
        return potential;
    }

    @JsonProperty("potential")
    public void setPotential(Double potential) {
        this.potential = potential;
    }

    public FieldsLocationInstance withPotential(Double potential) {
        this.potential = potential;
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
        return ((((((((((((("FieldsLocationInstance"+" [id=")+ id)+", index=")+ index)+", label=")+ label)+", pH=")+ pH)+", potential=")+ potential)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
