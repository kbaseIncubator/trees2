
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
 * <p>Original spec-file type: fields_HasPresenceOf</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "concentration",
    "minimum_flux",
    "maximum_flux"
})
public class FieldsHasPresenceOf {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("concentration")
    private Double concentration;
    @JsonProperty("minimum_flux")
    private Double minimumFlux;
    @JsonProperty("maximum_flux")
    private Double maximumFlux;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsHasPresenceOf withFromLink(String fromLink) {
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

    public FieldsHasPresenceOf withToLink(String toLink) {
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

    public FieldsHasPresenceOf withConcentration(Double concentration) {
        this.concentration = concentration;
        return this;
    }

    @JsonProperty("minimum_flux")
    public Double getMinimumFlux() {
        return minimumFlux;
    }

    @JsonProperty("minimum_flux")
    public void setMinimumFlux(Double minimumFlux) {
        this.minimumFlux = minimumFlux;
    }

    public FieldsHasPresenceOf withMinimumFlux(Double minimumFlux) {
        this.minimumFlux = minimumFlux;
        return this;
    }

    @JsonProperty("maximum_flux")
    public Double getMaximumFlux() {
        return maximumFlux;
    }

    @JsonProperty("maximum_flux")
    public void setMaximumFlux(Double maximumFlux) {
        this.maximumFlux = maximumFlux;
    }

    public FieldsHasPresenceOf withMaximumFlux(Double maximumFlux) {
        this.maximumFlux = maximumFlux;
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
        return ((((((((((((("FieldsHasPresenceOf"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", concentration=")+ concentration)+", minimumFlux=")+ minimumFlux)+", maximumFlux=")+ maximumFlux)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
