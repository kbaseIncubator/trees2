
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
 * <p>Original spec-file type: fields_Reaction</p>
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
    "abbr",
    "direction",
    "deltaG",
    "deltaG_error",
    "thermodynamic_reversibility",
    "default_protons",
    "status"
})
public class FieldsReaction {

    @JsonProperty("id")
    private String id;
    @JsonProperty("mod_date")
    private String modDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("abbr")
    private String abbr;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("deltaG")
    private Double deltaG;
    @JsonProperty("deltaG_error")
    private Double deltaGError;
    @JsonProperty("thermodynamic_reversibility")
    private String thermodynamicReversibility;
    @JsonProperty("default_protons")
    private Double defaultProtons;
    @JsonProperty("status")
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsReaction withId(String id) {
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

    public FieldsReaction withModDate(String modDate) {
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

    public FieldsReaction withName(String name) {
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

    public FieldsReaction withSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public FieldsReaction withAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public FieldsReaction withDirection(String direction) {
        this.direction = direction;
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

    public FieldsReaction withDeltaG(Double deltaG) {
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

    public FieldsReaction withDeltaGError(Double deltaGError) {
        this.deltaGError = deltaGError;
        return this;
    }

    @JsonProperty("thermodynamic_reversibility")
    public String getThermodynamicReversibility() {
        return thermodynamicReversibility;
    }

    @JsonProperty("thermodynamic_reversibility")
    public void setThermodynamicReversibility(String thermodynamicReversibility) {
        this.thermodynamicReversibility = thermodynamicReversibility;
    }

    public FieldsReaction withThermodynamicReversibility(String thermodynamicReversibility) {
        this.thermodynamicReversibility = thermodynamicReversibility;
        return this;
    }

    @JsonProperty("default_protons")
    public Double getDefaultProtons() {
        return defaultProtons;
    }

    @JsonProperty("default_protons")
    public void setDefaultProtons(Double defaultProtons) {
        this.defaultProtons = defaultProtons;
    }

    public FieldsReaction withDefaultProtons(Double defaultProtons) {
        this.defaultProtons = defaultProtons;
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

    public FieldsReaction withStatus(String status) {
        this.status = status;
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
        return ((((((((((((((((((((((((("FieldsReaction"+" [id=")+ id)+", modDate=")+ modDate)+", name=")+ name)+", sourceId=")+ sourceId)+", abbr=")+ abbr)+", direction=")+ direction)+", deltaG=")+ deltaG)+", deltaGError=")+ deltaGError)+", thermodynamicReversibility=")+ thermodynamicReversibility)+", defaultProtons=")+ defaultProtons)+", status=")+ status)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
