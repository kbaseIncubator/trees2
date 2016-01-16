
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
 * <p>Original spec-file type: fields_IsCoupledTo</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "co_occurrence_evidence",
    "co_expression_evidence"
})
public class FieldsIsCoupledTo {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("co_occurrence_evidence")
    private Long coOccurrenceEvidence;
    @JsonProperty("co_expression_evidence")
    private Long coExpressionEvidence;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsCoupledTo withFromLink(String fromLink) {
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

    public FieldsIsCoupledTo withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("co_occurrence_evidence")
    public Long getCoOccurrenceEvidence() {
        return coOccurrenceEvidence;
    }

    @JsonProperty("co_occurrence_evidence")
    public void setCoOccurrenceEvidence(Long coOccurrenceEvidence) {
        this.coOccurrenceEvidence = coOccurrenceEvidence;
    }

    public FieldsIsCoupledTo withCoOccurrenceEvidence(Long coOccurrenceEvidence) {
        this.coOccurrenceEvidence = coOccurrenceEvidence;
        return this;
    }

    @JsonProperty("co_expression_evidence")
    public Long getCoExpressionEvidence() {
        return coExpressionEvidence;
    }

    @JsonProperty("co_expression_evidence")
    public void setCoExpressionEvidence(Long coExpressionEvidence) {
        this.coExpressionEvidence = coExpressionEvidence;
    }

    public FieldsIsCoupledTo withCoExpressionEvidence(Long coExpressionEvidence) {
        this.coExpressionEvidence = coExpressionEvidence;
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
        return ((((((((((("FieldsIsCoupledTo"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", coOccurrenceEvidence=")+ coOccurrenceEvidence)+", coExpressionEvidence=")+ coExpressionEvidence)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
