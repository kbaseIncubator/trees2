
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
 * <p>Original spec-file type: fields_CodonUsage</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "frequencies",
    "genetic_code",
    "type",
    "subtype"
})
public class FieldsCodonUsage {

    @JsonProperty("id")
    private String id;
    @JsonProperty("frequencies")
    private String frequencies;
    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("type")
    private String type;
    @JsonProperty("subtype")
    private String subtype;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsCodonUsage withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("frequencies")
    public String getFrequencies() {
        return frequencies;
    }

    @JsonProperty("frequencies")
    public void setFrequencies(String frequencies) {
        this.frequencies = frequencies;
    }

    public FieldsCodonUsage withFrequencies(String frequencies) {
        this.frequencies = frequencies;
        return this;
    }

    @JsonProperty("genetic_code")
    public Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public FieldsCodonUsage withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public FieldsCodonUsage withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("subtype")
    public String getSubtype() {
        return subtype;
    }

    @JsonProperty("subtype")
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public FieldsCodonUsage withSubtype(String subtype) {
        this.subtype = subtype;
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
        return ((((((((((((("FieldsCodonUsage"+" [id=")+ id)+", frequencies=")+ frequencies)+", geneticCode=")+ geneticCode)+", type=")+ type)+", subtype=")+ subtype)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
