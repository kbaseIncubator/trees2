
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
 * <p>Original spec-file type: fields_TimeSeries</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_id",
    "name",
    "comments",
    "timeUnits"
})
public class FieldsTimeSeries {

    @JsonProperty("id")
    private String id;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("timeUnits")
    private String timeUnits;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsTimeSeries withId(String id) {
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

    public FieldsTimeSeries withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public FieldsTimeSeries withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    public FieldsTimeSeries withComments(String comments) {
        this.comments = comments;
        return this;
    }

    @JsonProperty("timeUnits")
    public String getTimeUnits() {
        return timeUnits;
    }

    @JsonProperty("timeUnits")
    public void setTimeUnits(String timeUnits) {
        this.timeUnits = timeUnits;
    }

    public FieldsTimeSeries withTimeUnits(String timeUnits) {
        this.timeUnits = timeUnits;
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
        return ((((((((((((("FieldsTimeSeries"+" [id=")+ id)+", sourceId=")+ sourceId)+", name=")+ name)+", comments=")+ comments)+", timeUnits=")+ timeUnits)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
