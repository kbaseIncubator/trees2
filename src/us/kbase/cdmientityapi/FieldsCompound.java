
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
 * <p>Original spec-file type: fields_Compound</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "label",
    "abbr",
    "source_id",
    "ubiquitous",
    "mod_date",
    "mass",
    "formula",
    "charge",
    "deltaG",
    "deltaG_error"
})
public class FieldsCompound {

    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    private String label;
    @JsonProperty("abbr")
    private String abbr;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("ubiquitous")
    private Long ubiquitous;
    @JsonProperty("mod_date")
    private String modDate;
    @JsonProperty("mass")
    private Double mass;
    @JsonProperty("formula")
    private String formula;
    @JsonProperty("charge")
    private Double charge;
    @JsonProperty("deltaG")
    private Double deltaG;
    @JsonProperty("deltaG_error")
    private Double deltaGError;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsCompound withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public FieldsCompound withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("abbr")
    public String getAbbr() {
        return abbr;
    }

    @JsonProperty("abbr")
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public FieldsCompound withAbbr(String abbr) {
        this.abbr = abbr;
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

    public FieldsCompound withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("ubiquitous")
    public Long getUbiquitous() {
        return ubiquitous;
    }

    @JsonProperty("ubiquitous")
    public void setUbiquitous(Long ubiquitous) {
        this.ubiquitous = ubiquitous;
    }

    public FieldsCompound withUbiquitous(Long ubiquitous) {
        this.ubiquitous = ubiquitous;
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

    public FieldsCompound withModDate(String modDate) {
        this.modDate = modDate;
        return this;
    }

    @JsonProperty("mass")
    public Double getMass() {
        return mass;
    }

    @JsonProperty("mass")
    public void setMass(Double mass) {
        this.mass = mass;
    }

    public FieldsCompound withMass(Double mass) {
        this.mass = mass;
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

    public FieldsCompound withFormula(String formula) {
        this.formula = formula;
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

    public FieldsCompound withCharge(Double charge) {
        this.charge = charge;
        return this;
    }

    @JsonProperty("deltaG")
    public Double getDeltaG() {
        return deltaG;
    }

    @JsonProperty("deltaG")
    public void setDeltaG(Double deltaG) {
        this.deltaG = deltaG;
    }

    public FieldsCompound withDeltaG(Double deltaG) {
        this.deltaG = deltaG;
        return this;
    }

    @JsonProperty("deltaG_error")
    public Double getDeltaGError() {
        return deltaGError;
    }

    @JsonProperty("deltaG_error")
    public void setDeltaGError(Double deltaGError) {
        this.deltaGError = deltaGError;
    }

    public FieldsCompound withDeltaGError(Double deltaGError) {
        this.deltaGError = deltaGError;
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
        return ((((((((((((((((((((((((("FieldsCompound"+" [id=")+ id)+", label=")+ label)+", abbr=")+ abbr)+", sourceId=")+ sourceId)+", ubiquitous=")+ ubiquitous)+", modDate=")+ modDate)+", mass=")+ mass)+", formula=")+ formula)+", charge=")+ charge)+", deltaG=")+ deltaG)+", deltaGError=")+ deltaGError)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
