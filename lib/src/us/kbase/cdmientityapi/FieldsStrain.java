
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
 * <p>Original spec-file type: fields_Strain</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "source_id",
    "aggregateData",
    "wildtype",
    "referenceStrain"
})
public class FieldsStrain {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("aggregateData")
    private String aggregateData;
    @JsonProperty("wildtype")
    private String wildtype;
    @JsonProperty("referenceStrain")
    private String referenceStrain;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsStrain withId(String id) {
        this.id = id;
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

    public FieldsStrain withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public FieldsStrain withDescription(String description) {
        this.description = description;
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

    public FieldsStrain withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("aggregateData")
    public String getAggregateData() {
        return aggregateData;
    }

    @JsonProperty("aggregateData")
    public void setAggregateData(String aggregateData) {
        this.aggregateData = aggregateData;
    }

    public FieldsStrain withAggregateData(String aggregateData) {
        this.aggregateData = aggregateData;
        return this;
    }

    @JsonProperty("wildtype")
    public String getWildtype() {
        return wildtype;
    }

    @JsonProperty("wildtype")
    public void setWildtype(String wildtype) {
        this.wildtype = wildtype;
    }

    public FieldsStrain withWildtype(String wildtype) {
        this.wildtype = wildtype;
        return this;
    }

    @JsonProperty("referenceStrain")
    public String getReferenceStrain() {
        return referenceStrain;
    }

    @JsonProperty("referenceStrain")
    public void setReferenceStrain(String referenceStrain) {
        this.referenceStrain = referenceStrain;
    }

    public FieldsStrain withReferenceStrain(String referenceStrain) {
        this.referenceStrain = referenceStrain;
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
        return ((((((((((((((((("FieldsStrain"+" [id=")+ id)+", name=")+ name)+", description=")+ description)+", sourceId=")+ sourceId)+", aggregateData=")+ aggregateData)+", wildtype=")+ wildtype)+", referenceStrain=")+ referenceStrain)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
