
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
 * <p>Original spec-file type: fields_Media</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "mod_date",
    "name",
    "is_minimal",
    "source_id",
    "type"
})
public class FieldsMedia {

    @JsonProperty("id")
    private String id;
    @JsonProperty("mod_date")
    private String modDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_minimal")
    private String isMinimal;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("type")
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsMedia withId(String id) {
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

    public FieldsMedia withModDate(String modDate) {
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

    public FieldsMedia withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("is_minimal")
    public String getIsMinimal() {
        return isMinimal;
    }

    @JsonProperty("is_minimal")
    public void setIsMinimal(String isMinimal) {
        this.isMinimal = isMinimal;
    }

    public FieldsMedia withIsMinimal(String isMinimal) {
        this.isMinimal = isMinimal;
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

    public FieldsMedia withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public FieldsMedia withType(String type) {
        this.type = type;
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
        return ((((((((((((((("FieldsMedia"+" [id=")+ id)+", modDate=")+ modDate)+", name=")+ name)+", isMinimal=")+ isMinimal)+", sourceId=")+ sourceId)+", type=")+ type)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
