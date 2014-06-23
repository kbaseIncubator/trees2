
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
 * <p>Original spec-file type: fields_ContainsExperimentalUnit</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "location",
    "groupMeta"
})
public class FieldsContainsExperimentalUnit {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("location")
    private String location;
    @JsonProperty("groupMeta")
    private String groupMeta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsContainsExperimentalUnit withFromLink(String fromLink) {
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

    public FieldsContainsExperimentalUnit withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    public FieldsContainsExperimentalUnit withLocation(String location) {
        this.location = location;
        return this;
    }

    @JsonProperty("groupMeta")
    public String getGroupMeta() {
        return groupMeta;
    }

    @JsonProperty("groupMeta")
    public void setGroupMeta(String groupMeta) {
        this.groupMeta = groupMeta;
    }

    public FieldsContainsExperimentalUnit withGroupMeta(String groupMeta) {
        this.groupMeta = groupMeta;
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
        return ((((((((((("FieldsContainsExperimentalUnit"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", location=")+ location)+", groupMeta=")+ groupMeta)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
