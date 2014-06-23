
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
 * <p>Original spec-file type: fields_HasTrait</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "value",
    "statistic_type",
    "measure_id"
})
public class FieldsHasTrait {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("value")
    private Double value;
    @JsonProperty("statistic_type")
    private String statisticType;
    @JsonProperty("measure_id")
    private String measureId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsHasTrait withFromLink(String fromLink) {
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

    public FieldsHasTrait withToLink(String toLink) {
        this.toLink = toLink;
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

    public FieldsHasTrait withValue(Double value) {
        this.value = value;
        return this;
    }

    @JsonProperty("statistic_type")
    public String getStatisticType() {
        return statisticType;
    }

    @JsonProperty("statistic_type")
    public void setStatisticType(String statisticType) {
        this.statisticType = statisticType;
    }

    public FieldsHasTrait withStatisticType(String statisticType) {
        this.statisticType = statisticType;
        return this;
    }

    @JsonProperty("measure_id")
    public String getMeasureId() {
        return measureId;
    }

    @JsonProperty("measure_id")
    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    public FieldsHasTrait withMeasureId(String measureId) {
        this.measureId = measureId;
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
        return ((((((((((((("FieldsHasTrait"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", value=")+ value)+", statisticType=")+ statisticType)+", measureId=")+ measureId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
