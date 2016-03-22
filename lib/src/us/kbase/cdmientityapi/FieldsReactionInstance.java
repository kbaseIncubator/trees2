
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
 * <p>Original spec-file type: fields_ReactionInstance</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "direction",
    "protons"
})
public class FieldsReactionInstance {

    @JsonProperty("id")
    private String id;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("protons")
    private Double protons;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsReactionInstance withId(String id) {
        this.id = id;
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

    public FieldsReactionInstance withDirection(String direction) {
        this.direction = direction;
        return this;
    }

    @JsonProperty("protons")
    public Double getProtons() {
        return protons;
    }

    @JsonProperty("protons")
    public void setProtons(Double protons) {
        this.protons = protons;
    }

    public FieldsReactionInstance withProtons(Double protons) {
        this.protons = protons;
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
        return ((((((((("FieldsReactionInstance"+" [id=")+ id)+", direction=")+ direction)+", protons=")+ protons)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
