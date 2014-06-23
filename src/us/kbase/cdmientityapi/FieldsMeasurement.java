
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
 * <p>Original spec-file type: fields_Measurement</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_id",
    "value",
    "mean",
    "median",
    "stddev",
    "N",
    "p_value",
    "Z_score"
})
public class FieldsMeasurement {

    @JsonProperty("id")
    private String id;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("value")
    private Double value;
    @JsonProperty("mean")
    private Double mean;
    @JsonProperty("median")
    private Double median;
    @JsonProperty("stddev")
    private Double stddev;
    @JsonProperty("N")
    private Long N;
    @JsonProperty("p_value")
    private Double pValue;
    @JsonProperty("Z_score")
    private Double ZScore;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsMeasurement withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("source_id")
    public String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public FieldsMeasurement withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    public FieldsMeasurement withValue(Double value) {
        this.value = value;
        return this;
    }

    @JsonProperty("mean")
    public Double getMean() {
        return mean;
    }

    @JsonProperty("mean")
    public void setMean(Double mean) {
        this.mean = mean;
    }

    public FieldsMeasurement withMean(Double mean) {
        this.mean = mean;
        return this;
    }

    @JsonProperty("median")
    public Double getMedian() {
        return median;
    }

    @JsonProperty("median")
    public void setMedian(Double median) {
        this.median = median;
    }

    public FieldsMeasurement withMedian(Double median) {
        this.median = median;
        return this;
    }

    @JsonProperty("stddev")
    public Double getStddev() {
        return stddev;
    }

    @JsonProperty("stddev")
    public void setStddev(Double stddev) {
        this.stddev = stddev;
    }

    public FieldsMeasurement withStddev(Double stddev) {
        this.stddev = stddev;
        return this;
    }

    @JsonProperty("N")
    public Long getN() {
        return N;
    }

    @JsonProperty("N")
    public void setN(Long N) {
        this.N = N;
    }

    public FieldsMeasurement withN(Long N) {
        this.N = N;
        return this;
    }

    @JsonProperty("p_value")
    public Double getPValue() {
        return pValue;
    }

    @JsonProperty("p_value")
    public void setPValue(Double pValue) {
        this.pValue = pValue;
    }

    public FieldsMeasurement withPValue(Double pValue) {
        this.pValue = pValue;
        return this;
    }

    @JsonProperty("Z_score")
    public Double getZScore() {
        return ZScore;
    }

    @JsonProperty("Z_score")
    public void setZScore(Double ZScore) {
        this.ZScore = ZScore;
    }

    public FieldsMeasurement withZScore(Double ZScore) {
        this.ZScore = ZScore;
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
        return ((((((((((((((((((((("FieldsMeasurement"+" [id=")+ id)+", sourceId=")+ sourceId)+", value=")+ value)+", mean=")+ mean)+", median=")+ median)+", stddev=")+ stddev)+", N=")+ N)+", pValue=")+ pValue)+", ZScore=")+ ZScore)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
