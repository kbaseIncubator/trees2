
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
 * <p>Original spec-file type: fields_HasIndicatedSignalFrom</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "rma_value",
    "level"
})
public class FieldsHasIndicatedSignalFrom {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("rma_value")
    private Double rmaValue;
    @JsonProperty("level")
    private Long level;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsHasIndicatedSignalFrom withFromLink(String fromLink) {
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

    public FieldsHasIndicatedSignalFrom withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("rma_value")
    public Double getRmaValue() {
        return rmaValue;
    }

    @JsonProperty("rma_value")
    public void setRmaValue(Double rmaValue) {
        this.rmaValue = rmaValue;
    }

    public FieldsHasIndicatedSignalFrom withRmaValue(Double rmaValue) {
        this.rmaValue = rmaValue;
        return this;
    }

    @JsonProperty("level")
    public Long getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(Long level) {
        this.level = level;
    }

    public FieldsHasIndicatedSignalFrom withLevel(Long level) {
        this.level = level;
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
        return ((((((((((("FieldsHasIndicatedSignalFrom"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", rmaValue=")+ rmaValue)+", level=")+ level)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
