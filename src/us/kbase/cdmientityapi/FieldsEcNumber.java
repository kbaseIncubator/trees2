
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
 * <p>Original spec-file type: fields_EcNumber</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "obsolete",
    "replacedby"
})
public class FieldsEcNumber {

    @JsonProperty("id")
    private String id;
    @JsonProperty("obsolete")
    private Long obsolete;
    @JsonProperty("replacedby")
    private String replacedby;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsEcNumber withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("obsolete")
    public Long getObsolete() {
        return obsolete;
    }

    @JsonProperty("obsolete")
    public void setObsolete(Long obsolete) {
        this.obsolete = obsolete;
    }

    public FieldsEcNumber withObsolete(Long obsolete) {
        this.obsolete = obsolete;
        return this;
    }

    @JsonProperty("replacedby")
    public String getReplacedby() {
        return replacedby;
    }

    @JsonProperty("replacedby")
    public void setReplacedby(String replacedby) {
        this.replacedby = replacedby;
    }

    public FieldsEcNumber withReplacedby(String replacedby) {
        this.replacedby = replacedby;
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
        return ((((((((("FieldsEcNumber"+" [id=")+ id)+", obsolete=")+ obsolete)+", replacedby=")+ replacedby)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
