
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
 * <p>Original spec-file type: fields_Impacts</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "source_name",
    "rank",
    "pvalue",
    "position"
})
public class FieldsImpacts {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("source_name")
    private String sourceName;
    @JsonProperty("rank")
    private Long rank;
    @JsonProperty("pvalue")
    private Double pvalue;
    @JsonProperty("position")
    private Long position;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsImpacts withFromLink(String fromLink) {
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

    public FieldsImpacts withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("source_name")
    public String getSourceName() {
        return sourceName;
    }

    @JsonProperty("source_name")
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public FieldsImpacts withSourceName(String sourceName) {
        this.sourceName = sourceName;
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

    public FieldsImpacts withRank(Long rank) {
        this.rank = rank;
        return this;
    }

    @JsonProperty("pvalue")
    public Double getPvalue() {
        return pvalue;
    }

    @JsonProperty("pvalue")
    public void setPvalue(Double pvalue) {
        this.pvalue = pvalue;
    }

    public FieldsImpacts withPvalue(Double pvalue) {
        this.pvalue = pvalue;
        return this;
    }

    @JsonProperty("position")
    public Long getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Long position) {
        this.position = position;
    }

    public FieldsImpacts withPosition(Long position) {
        this.position = position;
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
        return ((((((((((((((("FieldsImpacts"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", sourceName=")+ sourceName)+", rank=")+ rank)+", pvalue=")+ pvalue)+", position=")+ position)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
