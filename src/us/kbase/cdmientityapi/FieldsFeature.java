
package us.kbase.cdmientityapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: fields_Feature</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "feature_type",
    "source_id",
    "sequence_length",
    "function",
    "alias"
})
public class FieldsFeature {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("feature_type")
    private java.lang.String featureType;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("sequence_length")
    private Long sequenceLength;
    @JsonProperty("function")
    private java.lang.String function;
    @JsonProperty("alias")
    private List<String> alias;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsFeature withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("feature_type")
    public java.lang.String getFeatureType() {
        return featureType;
    }

    @JsonProperty("feature_type")
    public void setFeatureType(java.lang.String featureType) {
        this.featureType = featureType;
    }

    public FieldsFeature withFeatureType(java.lang.String featureType) {
        this.featureType = featureType;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public FieldsFeature withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("sequence_length")
    public Long getSequenceLength() {
        return sequenceLength;
    }

    @JsonProperty("sequence_length")
    public void setSequenceLength(Long sequenceLength) {
        this.sequenceLength = sequenceLength;
    }

    public FieldsFeature withSequenceLength(Long sequenceLength) {
        this.sequenceLength = sequenceLength;
        return this;
    }

    @JsonProperty("function")
    public java.lang.String getFunction() {
        return function;
    }

    @JsonProperty("function")
    public void setFunction(java.lang.String function) {
        this.function = function;
    }

    public FieldsFeature withFunction(java.lang.String function) {
        this.function = function;
        return this;
    }

    @JsonProperty("alias")
    public List<String> getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public FieldsFeature withAlias(List<String> alias) {
        this.alias = alias;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((((((("FieldsFeature"+" [id=")+ id)+", featureType=")+ featureType)+", sourceId=")+ sourceId)+", sequenceLength=")+ sequenceLength)+", function=")+ function)+", alias=")+ alias)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
