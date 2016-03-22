
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
 * <p>Original spec-file type: fields_ObservationalUnit</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_name",
    "source_name2",
    "plant_id"
})
public class FieldsObservationalUnit {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("source_name")
    private java.lang.String sourceName;
    @JsonProperty("source_name2")
    private List<String> sourceName2;
    @JsonProperty("plant_id")
    private java.lang.String plantId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsObservationalUnit withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("source_name")
    public java.lang.String getSourceName() {
        return sourceName;
    }

    @JsonProperty("source_name")
    public void setSourceName(java.lang.String sourceName) {
        this.sourceName = sourceName;
    }

    public FieldsObservationalUnit withSourceName(java.lang.String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    @JsonProperty("source_name2")
    public List<String> getSourceName2() {
        return sourceName2;
    }

    @JsonProperty("source_name2")
    public void setSourceName2(List<String> sourceName2) {
        this.sourceName2 = sourceName2;
    }

    public FieldsObservationalUnit withSourceName2(List<String> sourceName2) {
        this.sourceName2 = sourceName2;
        return this;
    }

    @JsonProperty("plant_id")
    public java.lang.String getPlantId() {
        return plantId;
    }

    @JsonProperty("plant_id")
    public void setPlantId(java.lang.String plantId) {
        this.plantId = plantId;
    }

    public FieldsObservationalUnit withPlantId(java.lang.String plantId) {
        this.plantId = plantId;
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
        return ((((((((((("FieldsObservationalUnit"+" [id=")+ id)+", sourceName=")+ sourceName)+", sourceName2=")+ sourceName2)+", plantId=")+ plantId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
