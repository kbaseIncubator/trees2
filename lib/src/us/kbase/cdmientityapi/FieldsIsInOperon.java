
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
 * <p>Original spec-file type: fields_IsInOperon</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "rank"
})
public class FieldsIsInOperon {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("rank")
    private Long rank;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsInOperon withFromLink(String fromLink) {
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

    public FieldsIsInOperon withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("rank")
    public Long getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Long rank) {
        this.rank = rank;
    }

    public FieldsIsInOperon withRank(Long rank) {
        this.rank = rank;
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
        return ((((((((("FieldsIsInOperon"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", rank=")+ rank)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
