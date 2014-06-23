
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
 * <p>Original spec-file type: fields_Location</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "mod_date",
    "name",
    "source_id",
    "abbr"
})
public class FieldsLocation {

    @JsonProperty("id")
    private String id;
    @JsonProperty("mod_date")
    private String modDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("abbr")
    private Long abbr;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsLocation withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("mod_date")
    public String getModDate() {
        return modDate;
    }

    @JsonProperty("mod_date")
    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public FieldsLocation withModDate(String modDate) {
        this.modDate = modDate;
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

    public FieldsLocation withName(String name) {
        this.name = name;
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

    public FieldsLocation withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("abbr")
    public Long getAbbr() {
        return abbr;
    }

    @JsonProperty("abbr")
    public void setAbbr(Long abbr) {
        this.abbr = abbr;
    }

    public FieldsLocation withAbbr(Long abbr) {
        this.abbr = abbr;
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
        return ((((((((((((("FieldsLocation"+" [id=")+ id)+", modDate=")+ modDate)+", name=")+ name)+", sourceId=")+ sourceId)+", abbr=")+ abbr)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
