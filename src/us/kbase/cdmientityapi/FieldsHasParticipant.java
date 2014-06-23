
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
 * <p>Original spec-file type: fields_HasParticipant</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "type"
})
public class FieldsHasParticipant {

    @JsonProperty("from_link")
    private Long fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("type")
    private Long type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public Long getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(Long fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsHasParticipant withFromLink(Long fromLink) {
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

    public FieldsHasParticipant withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("type")
    public Long getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Long type) {
        this.type = type;
    }

    public FieldsHasParticipant withType(Long type) {
        this.type = type;
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
        return ((((((((("FieldsHasParticipant"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", type=")+ type)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
