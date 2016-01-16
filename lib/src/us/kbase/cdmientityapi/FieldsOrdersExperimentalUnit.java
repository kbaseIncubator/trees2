
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
 * <p>Original spec-file type: fields_OrdersExperimentalUnit</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "time",
    "timeMeta"
})
public class FieldsOrdersExperimentalUnit {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("time")
    private Double time;
    @JsonProperty("timeMeta")
    private String timeMeta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsOrdersExperimentalUnit withFromLink(String fromLink) {
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

    public FieldsOrdersExperimentalUnit withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("time")
    public Double getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Double time) {
        this.time = time;
    }

    public FieldsOrdersExperimentalUnit withTime(Double time) {
        this.time = time;
        return this;
    }

    @JsonProperty("timeMeta")
    public String getTimeMeta() {
        return timeMeta;
    }

    @JsonProperty("timeMeta")
    public void setTimeMeta(String timeMeta) {
        this.timeMeta = timeMeta;
    }

    public FieldsOrdersExperimentalUnit withTimeMeta(String timeMeta) {
        this.timeMeta = timeMeta;
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
        return ((((((((((("FieldsOrdersExperimentalUnit"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", time=")+ time)+", timeMeta=")+ timeMeta)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
