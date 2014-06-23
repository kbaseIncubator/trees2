
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
 * <p>Original spec-file type: fields_IsTerminusFor</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "group_number"
})
public class FieldsIsTerminusFor {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private Long toLink;
    @JsonProperty("group_number")
    private Long groupNumber;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsTerminusFor withFromLink(String fromLink) {
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

    public FieldsIsTerminusFor withToLink(Long toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("group_number")
    public Long getGroupNumber() {
        return groupNumber;
    }

    @JsonProperty("group_number")
    public void setGroupNumber(Long groupNumber) {
        this.groupNumber = groupNumber;
    }

    public FieldsIsTerminusFor withGroupNumber(Long groupNumber) {
        this.groupNumber = groupNumber;
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
        return ((((((((("FieldsIsTerminusFor"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", groupNumber=")+ groupNumber)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
