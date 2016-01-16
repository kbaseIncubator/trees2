
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
 * <p>Original spec-file type: fields_IsModifiedToBuildAlignment</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "modification_type",
    "modification_value"
})
public class FieldsIsModifiedToBuildAlignment {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("modification_type")
    private String modificationType;
    @JsonProperty("modification_value")
    private String modificationValue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsModifiedToBuildAlignment withFromLink(String fromLink) {
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

    public FieldsIsModifiedToBuildAlignment withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("modification_type")
    public String getModificationType() {
        return modificationType;
    }

    @JsonProperty("modification_type")
    public void setModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

    public FieldsIsModifiedToBuildAlignment withModificationType(String modificationType) {
        this.modificationType = modificationType;
        return this;
    }

    @JsonProperty("modification_value")
    public String getModificationValue() {
        return modificationValue;
    }

    @JsonProperty("modification_value")
    public void setModificationValue(String modificationValue) {
        this.modificationValue = modificationValue;
    }

    public FieldsIsModifiedToBuildAlignment withModificationValue(String modificationValue) {
        this.modificationValue = modificationValue;
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
        return ((((((((((("FieldsIsModifiedToBuildAlignment"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", modificationType=")+ modificationType)+", modificationValue=")+ modificationValue)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
