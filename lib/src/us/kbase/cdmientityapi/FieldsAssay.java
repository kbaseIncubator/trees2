
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
 * <p>Original spec-file type: fields_Assay</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_id",
    "assay_type",
    "assay_type_id"
})
public class FieldsAssay {

    @JsonProperty("id")
    private String id;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("assay_type")
    private String assayType;
    @JsonProperty("assay_type_id")
    private String assayTypeId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsAssay withId(String id) {
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

    public FieldsAssay withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("assay_type")
    public String getAssayType() {
        return assayType;
    }

    @JsonProperty("assay_type")
    public void setAssayType(String assayType) {
        this.assayType = assayType;
    }

    public FieldsAssay withAssayType(String assayType) {
        this.assayType = assayType;
        return this;
    }

    @JsonProperty("assay_type_id")
    public String getAssayTypeId() {
        return assayTypeId;
    }

    @JsonProperty("assay_type_id")
    public void setAssayTypeId(String assayTypeId) {
        this.assayTypeId = assayTypeId;
    }

    public FieldsAssay withAssayTypeId(String assayTypeId) {
        this.assayTypeId = assayTypeId;
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
        return ((((((((((("FieldsAssay"+" [id=")+ id)+", sourceId=")+ sourceId)+", assayType=")+ assayType)+", assayTypeId=")+ assayTypeId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
