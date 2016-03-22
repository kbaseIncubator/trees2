
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
 * <p>Original spec-file type: fields_IncludesAdditionalCompounds</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "concentration",
    "units"
})
public class FieldsIncludesAdditionalCompounds {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("concentration")
    private Double concentration;
    @JsonProperty("units")
    private String units;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIncludesAdditionalCompounds withFromLink(String fromLink) {
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

    public FieldsIncludesAdditionalCompounds withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("concentration")
    public Double getConcentration() {
        return concentration;
    }

    @JsonProperty("concentration")
    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public FieldsIncludesAdditionalCompounds withConcentration(Double concentration) {
        this.concentration = concentration;
        return this;
    }

    @JsonProperty("units")
    public String getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(String units) {
        this.units = units;
    }

    public FieldsIncludesAdditionalCompounds withUnits(String units) {
        this.units = units;
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
        return ((((((((((("FieldsIncludesAdditionalCompounds"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", concentration=")+ concentration)+", units=")+ units)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
