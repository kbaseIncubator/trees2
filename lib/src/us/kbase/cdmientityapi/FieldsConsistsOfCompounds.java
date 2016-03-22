
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
 * <p>Original spec-file type: fields_ConsistsOfCompounds</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "molar_ratio"
})
public class FieldsConsistsOfCompounds {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("molar_ratio")
    private Double molarRatio;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsConsistsOfCompounds withFromLink(String fromLink) {
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

    public FieldsConsistsOfCompounds withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("molar_ratio")
    public Double getMolarRatio() {
        return molarRatio;
    }

    @JsonProperty("molar_ratio")
    public void setMolarRatio(Double molarRatio) {
        this.molarRatio = molarRatio;
    }

    public FieldsConsistsOfCompounds withMolarRatio(Double molarRatio) {
        this.molarRatio = molarRatio;
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
        return ((((((((("FieldsConsistsOfCompounds"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", molarRatio=")+ molarRatio)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
