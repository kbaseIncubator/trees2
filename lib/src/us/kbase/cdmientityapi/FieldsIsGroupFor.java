
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
 * <p>Original spec-file type: fields_IsGroupFor</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link"
})
public class FieldsIsGroupFor {

    @JsonProperty("from_link")
    private Long fromLink;
    @JsonProperty("to_link")
    private Long toLink;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public Long getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(Long fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsGroupFor withFromLink(Long fromLink) {
        this.fromLink = fromLink;
        return this;
    }

    @JsonProperty("to_link")
    public Long getToLink() {
        return toLink;
    }

    @JsonProperty("to_link")
    public void setToLink(Long toLink) {
        this.toLink = toLink;
    }

    public FieldsIsGroupFor withToLink(Long toLink) {
        this.toLink = toLink;
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
        return ((((((("FieldsIsGroupFor"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
