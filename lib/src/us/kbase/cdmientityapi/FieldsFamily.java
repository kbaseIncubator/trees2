
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
 * <p>Original spec-file type: fields_Family</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "type",
    "release",
    "family_function",
    "alignment"
})
public class FieldsFamily {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("release")
    private java.lang.String release;
    @JsonProperty("family_function")
    private List<String> familyFunction;
    @JsonProperty("alignment")
    private List<String> alignment;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsFamily withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public FieldsFamily withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("release")
    public java.lang.String getRelease() {
        return release;
    }

    @JsonProperty("release")
    public void setRelease(java.lang.String release) {
        this.release = release;
    }

    public FieldsFamily withRelease(java.lang.String release) {
        this.release = release;
        return this;
    }

    @JsonProperty("family_function")
    public List<String> getFamilyFunction() {
        return familyFunction;
    }

    @JsonProperty("family_function")
    public void setFamilyFunction(List<String> familyFunction) {
        this.familyFunction = familyFunction;
    }

    public FieldsFamily withFamilyFunction(List<String> familyFunction) {
        this.familyFunction = familyFunction;
        return this;
    }

    @JsonProperty("alignment")
    public List<String> getAlignment() {
        return alignment;
    }

    @JsonProperty("alignment")
    public void setAlignment(List<String> alignment) {
        this.alignment = alignment;
    }

    public FieldsFamily withAlignment(List<String> alignment) {
        this.alignment = alignment;
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
        return ((((((((((((("FieldsFamily"+" [id=")+ id)+", type=")+ type)+", release=")+ release)+", familyFunction=")+ familyFunction)+", alignment=")+ alignment)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
