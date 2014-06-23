
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
 * <p>Original spec-file type: fields_HasVariationIn</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "position",
    "len",
    "data",
    "data2",
    "quality"
})
public class FieldsHasVariationIn {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("position")
    private Long position;
    @JsonProperty("len")
    private Long len;
    @JsonProperty("data")
    private String data;
    @JsonProperty("data2")
    private String data2;
    @JsonProperty("quality")
    private Double quality;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsHasVariationIn withFromLink(String fromLink) {
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

    public FieldsHasVariationIn withToLink(String toLink) {
        this.toLink = toLink;
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

    public FieldsHasVariationIn withPosition(Long position) {
        this.position = position;
        return this;
    }

    @JsonProperty("len")
    public Long getLen() {
        return len;
    }

    @JsonProperty("len")
    public void setLen(Long len) {
        this.len = len;
    }

    public FieldsHasVariationIn withLen(Long len) {
        this.len = len;
        return this;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    public FieldsHasVariationIn withData(String data) {
        this.data = data;
        return this;
    }

    @JsonProperty("data2")
    public String getData2() {
        return data2;
    }

    @JsonProperty("data2")
    public void setData2(String data2) {
        this.data2 = data2;
    }

    public FieldsHasVariationIn withData2(String data2) {
        this.data2 = data2;
        return this;
    }

    @JsonProperty("quality")
    public Double getQuality() {
        return quality;
    }

    @JsonProperty("quality")
    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public FieldsHasVariationIn withQuality(Double quality) {
        this.quality = quality;
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
        return ((((((((((((((((("FieldsHasVariationIn"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", position=")+ position)+", len=")+ len)+", data=")+ data)+", data2=")+ data2)+", quality=")+ quality)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
