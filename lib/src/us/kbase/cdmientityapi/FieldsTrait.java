
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
 * <p>Original spec-file type: fields_Trait</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "trait_name",
    "unit_of_measure",
    "TO_ID",
    "protocol"
})
public class FieldsTrait {

    @JsonProperty("id")
    private String id;
    @JsonProperty("trait_name")
    private String traitName;
    @JsonProperty("unit_of_measure")
    private String unitOfMeasure;
    @JsonProperty("TO_ID")
    private String TOID;
    @JsonProperty("protocol")
    private String protocol;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsTrait withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("trait_name")
    public String getTraitName() {
        return traitName;
    }

    @JsonProperty("trait_name")
    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public FieldsTrait withTraitName(String traitName) {
        this.traitName = traitName;
        return this;
    }

    @JsonProperty("unit_of_measure")
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @JsonProperty("unit_of_measure")
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public FieldsTrait withUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        return this;
    }

    @JsonProperty("TO_ID")
    public String getTOID() {
        return TOID;
    }

    @JsonProperty("TO_ID")
    public void setTOID(String TOID) {
        this.TOID = TOID;
    }

    public FieldsTrait withTOID(String TOID) {
        this.TOID = TOID;
        return this;
    }

    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public FieldsTrait withProtocol(String protocol) {
        this.protocol = protocol;
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
        return ((((((((((((("FieldsTrait"+" [id=")+ id)+", traitName=")+ traitName)+", unitOfMeasure=")+ unitOfMeasure)+", TOID=")+ TOID)+", protocol=")+ protocol)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
