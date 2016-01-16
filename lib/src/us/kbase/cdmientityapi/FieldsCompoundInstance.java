
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
 * <p>Original spec-file type: fields_CompoundInstance</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "charge",
    "formula"
})
public class FieldsCompoundInstance {

    @JsonProperty("id")
    private String id;
    @JsonProperty("charge")
    private Double charge;
    @JsonProperty("formula")
    private String formula;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsCompoundInstance withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("charge")
    public Double getCharge() {
        return charge;
    }

    @JsonProperty("charge")
    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public FieldsCompoundInstance withCharge(Double charge) {
        this.charge = charge;
        return this;
    }

    @JsonProperty("formula")
    public String getFormula() {
        return formula;
    }

    @JsonProperty("formula")
    public void setFormula(String formula) {
        this.formula = formula;
    }

    public FieldsCompoundInstance withFormula(String formula) {
        this.formula = formula;
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
        return ((((((((("FieldsCompoundInstance"+" [id=")+ id)+", charge=")+ charge)+", formula=")+ formula)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
