
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
 * <p>Original spec-file type: fields_CoregulatedSet</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_id",
    "binding_location"
})
public class FieldsCoregulatedSet {

    @JsonProperty("id")
    private String id;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("binding_location")
    private List<Long> bindingLocation;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsCoregulatedSet withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("source_id")
    public String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public FieldsCoregulatedSet withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("binding_location")
    public List<Long> getBindingLocation() {
        return bindingLocation;
    }

    @JsonProperty("binding_location")
    public void setBindingLocation(List<Long> bindingLocation) {
        this.bindingLocation = bindingLocation;
    }

    public FieldsCoregulatedSet withBindingLocation(List<Long> bindingLocation) {
        this.bindingLocation = bindingLocation;
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
        return ((((((((("FieldsCoregulatedSet"+" [id=")+ id)+", sourceId=")+ sourceId)+", bindingLocation=")+ bindingLocation)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
