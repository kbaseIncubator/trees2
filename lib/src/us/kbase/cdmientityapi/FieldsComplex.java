
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
 * <p>Original spec-file type: fields_Complex</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "source_id",
    "mod_date"
})
public class FieldsComplex {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private List<String> name;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("mod_date")
    private java.lang.String modDate;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsComplex withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public List<String> getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(List<String> name) {
        this.name = name;
    }

    public FieldsComplex withName(List<String> name) {
        this.name = name;
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

    public FieldsComplex withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("mod_date")
    public java.lang.String getModDate() {
        return modDate;
    }

    @JsonProperty("mod_date")
    public void setModDate(java.lang.String modDate) {
        this.modDate = modDate;
    }

    public FieldsComplex withModDate(java.lang.String modDate) {
        this.modDate = modDate;
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
        return ((((((((((("FieldsComplex"+" [id=")+ id)+", name=")+ name)+", sourceId=")+ sourceId)+", modDate=")+ modDate)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
