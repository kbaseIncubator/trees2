
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
 * <p>Original spec-file type: fields_Environment</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "temperature",
    "description",
    "oxygenConcentration",
    "pH",
    "source_id"
})
public class FieldsEnvironment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("description")
    private String description;
    @JsonProperty("oxygenConcentration")
    private Double oxygenConcentration;
    @JsonProperty("pH")
    private Double pH;
    @JsonProperty("source_id")
    private String sourceId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsEnvironment withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("temperature")
    public Double getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public FieldsEnvironment withTemperature(Double temperature) {
        this.temperature = temperature;
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

    public FieldsEnvironment withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("oxygenConcentration")
    public Double getOxygenConcentration() {
        return oxygenConcentration;
    }

    @JsonProperty("oxygenConcentration")
    public void setOxygenConcentration(Double oxygenConcentration) {
        this.oxygenConcentration = oxygenConcentration;
    }

    public FieldsEnvironment withOxygenConcentration(Double oxygenConcentration) {
        this.oxygenConcentration = oxygenConcentration;
        return this;
    }

    @JsonProperty("pH")
    public Double getPH() {
        return pH;
    }

    @JsonProperty("pH")
    public void setPH(Double pH) {
        this.pH = pH;
    }

    public FieldsEnvironment withPH(Double pH) {
        this.pH = pH;
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

    public FieldsEnvironment withSourceId(String sourceId) {
        this.sourceId = sourceId;
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
        return ((((((((((((((("FieldsEnvironment"+" [id=")+ id)+", temperature=")+ temperature)+", description=")+ description)+", oxygenConcentration=")+ oxygenConcentration)+", pH=")+ pH)+", sourceId=")+ sourceId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
