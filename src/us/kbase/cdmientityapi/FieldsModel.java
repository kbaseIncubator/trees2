
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
 * <p>Original spec-file type: fields_Model</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "mod_date",
    "name",
    "version",
    "type",
    "status",
    "reaction_count",
    "compound_count",
    "annotation_count"
})
public class FieldsModel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("mod_date")
    private String modDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private Long version;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("reaction_count")
    private Long reactionCount;
    @JsonProperty("compound_count")
    private Long compoundCount;
    @JsonProperty("annotation_count")
    private Long annotationCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsModel withId(String id) {
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

    public FieldsModel withModDate(String modDate) {
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

    public FieldsModel withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("version")
    public Long getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Long version) {
        this.version = version;
    }

    public FieldsModel withVersion(Long version) {
        this.version = version;
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

    public FieldsModel withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public FieldsModel withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("reaction_count")
    public Long getReactionCount() {
        return reactionCount;
    }

    @JsonProperty("reaction_count")
    public void setReactionCount(Long reactionCount) {
        this.reactionCount = reactionCount;
    }

    public FieldsModel withReactionCount(Long reactionCount) {
        this.reactionCount = reactionCount;
        return this;
    }

    @JsonProperty("compound_count")
    public Long getCompoundCount() {
        return compoundCount;
    }

    @JsonProperty("compound_count")
    public void setCompoundCount(Long compoundCount) {
        this.compoundCount = compoundCount;
    }

    public FieldsModel withCompoundCount(Long compoundCount) {
        this.compoundCount = compoundCount;
        return this;
    }

    @JsonProperty("annotation_count")
    public Long getAnnotationCount() {
        return annotationCount;
    }

    @JsonProperty("annotation_count")
    public void setAnnotationCount(Long annotationCount) {
        this.annotationCount = annotationCount;
    }

    public FieldsModel withAnnotationCount(Long annotationCount) {
        this.annotationCount = annotationCount;
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
        return ((((((((((((((((((((("FieldsModel"+" [id=")+ id)+", modDate=")+ modDate)+", name=")+ name)+", version=")+ version)+", type=")+ type)+", status=")+ status)+", reactionCount=")+ reactionCount)+", compoundCount=")+ compoundCount)+", annotationCount=")+ annotationCount)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
