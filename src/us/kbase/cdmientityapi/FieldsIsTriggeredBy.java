
package us.kbase.cdmientityapi;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: fields_IsTriggeredBy</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "optional",
    "type",
    "triggering"
})
public class FieldsIsTriggeredBy {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("optional")
    private Long optional;
    @JsonProperty("type")
    private String type;
    @JsonProperty("triggering")
    private Long triggering;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsTriggeredBy withFromLink(String fromLink) {
        this.fromLink = fromLink;
        return this;
    }

    @JsonProperty("to_link")
    public String getToLink() {
        return toLink;
    }

    @JsonProperty("to_link")
    public void setToLink(String toLink) {
        this.toLink = toLink;
    }

    public FieldsIsTriggeredBy withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("optional")
    public Long getOptional() {
        return optional;
    }

    @JsonProperty("optional")
    public void setOptional(Long optional) {
        this.optional = optional;
    }

    public FieldsIsTriggeredBy withOptional(Long optional) {
        this.optional = optional;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public FieldsIsTriggeredBy withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("triggering")
    public Long getTriggering() {
        return triggering;
    }

    @JsonProperty("triggering")
    public void setTriggering(Long triggering) {
        this.triggering = triggering;
    }

    public FieldsIsTriggeredBy withTriggering(Long triggering) {
        this.triggering = triggering;
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
        return ((((((((((((("FieldsIsTriggeredBy"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", optional=")+ optional)+", type=")+ type)+", triggering=")+ triggering)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
