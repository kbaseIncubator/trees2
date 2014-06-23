
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
 * <p>Original spec-file type: fields_IsLocatedIn</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "ordinal",
    "begin",
    "len",
    "dir"
})
public class FieldsIsLocatedIn {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("ordinal")
    private Long ordinal;
    @JsonProperty("begin")
    private Long begin;
    @JsonProperty("len")
    private Long len;
    @JsonProperty("dir")
    private String dir;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsLocatedIn withFromLink(String fromLink) {
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

    public FieldsIsLocatedIn withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("ordinal")
    public Long getOrdinal() {
        return ordinal;
    }

    @JsonProperty("ordinal")
    public void setOrdinal(Long ordinal) {
        this.ordinal = ordinal;
    }

    public FieldsIsLocatedIn withOrdinal(Long ordinal) {
        this.ordinal = ordinal;
        return this;
    }

    @JsonProperty("begin")
    public Long getBegin() {
        return begin;
    }

    @JsonProperty("begin")
    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public FieldsIsLocatedIn withBegin(Long begin) {
        this.begin = begin;
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

    public FieldsIsLocatedIn withLen(Long len) {
        this.len = len;
        return this;
    }

    @JsonProperty("dir")
    public String getDir() {
        return dir;
    }

    @JsonProperty("dir")
    public void setDir(String dir) {
        this.dir = dir;
    }

    public FieldsIsLocatedIn withDir(String dir) {
        this.dir = dir;
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
        return ((((((((((((((("FieldsIsLocatedIn"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", ordinal=")+ ordinal)+", begin=")+ begin)+", len=")+ len)+", dir=")+ dir)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
