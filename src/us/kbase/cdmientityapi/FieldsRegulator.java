
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
 * <p>Original spec-file type: fields_Regulator</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "rfam_id",
    "tf_family",
    "type",
    "taxonomy"
})
public class FieldsRegulator {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rfam_id")
    private String rfamId;
    @JsonProperty("tf_family")
    private String tfFamily;
    @JsonProperty("type")
    private String type;
    @JsonProperty("taxonomy")
    private String taxonomy;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsRegulator withId(String id) {
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

    public FieldsRegulator withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("rfam_id")
    public String getRfamId() {
        return rfamId;
    }

    @JsonProperty("rfam_id")
    public void setRfamId(String rfamId) {
        this.rfamId = rfamId;
    }

    public FieldsRegulator withRfamId(String rfamId) {
        this.rfamId = rfamId;
        return this;
    }

    @JsonProperty("tf_family")
    public String getTfFamily() {
        return tfFamily;
    }

    @JsonProperty("tf_family")
    public void setTfFamily(String tfFamily) {
        this.tfFamily = tfFamily;
    }

    public FieldsRegulator withTfFamily(String tfFamily) {
        this.tfFamily = tfFamily;
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

    public FieldsRegulator withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("taxonomy")
    public String getTaxonomy() {
        return taxonomy;
    }

    @JsonProperty("taxonomy")
    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public FieldsRegulator withTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
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
        return ((((((((((((((("FieldsRegulator"+" [id=")+ id)+", name=")+ name)+", rfamId=")+ rfamId)+", tfFamily=")+ tfFamily)+", type=")+ type)+", taxonomy=")+ taxonomy)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
