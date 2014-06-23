
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
 * <p>Original spec-file type: fields_Involves</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "coefficient",
    "cofactor"
})
public class FieldsInvolves {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("coefficient")
    private Double coefficient;
    @JsonProperty("cofactor")
    private Long cofactor;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsInvolves withFromLink(String fromLink) {
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

    public FieldsInvolves withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("coefficient")
    public Double getCoefficient() {
        return coefficient;
    }

    @JsonProperty("coefficient")
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public FieldsInvolves withCoefficient(Double coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    @JsonProperty("cofactor")
    public Long getCofactor() {
        return cofactor;
    }

    @JsonProperty("cofactor")
    public void setCofactor(Long cofactor) {
        this.cofactor = cofactor;
    }

    public FieldsInvolves withCofactor(Long cofactor) {
        this.cofactor = cofactor;
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
        return ((((((((((("FieldsInvolves"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", coefficient=")+ coefficient)+", cofactor=")+ cofactor)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
