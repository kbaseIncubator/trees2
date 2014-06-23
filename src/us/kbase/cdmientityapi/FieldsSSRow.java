
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
 * <p>Original spec-file type: fields_SSRow</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "curated",
    "region"
})
public class FieldsSSRow {

    @JsonProperty("id")
    private String id;
    @JsonProperty("curated")
    private Long curated;
    @JsonProperty("region")
    private String region;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsSSRow withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("curated")
    public Long getCurated() {
        return curated;
    }

    @JsonProperty("curated")
    public void setCurated(Long curated) {
        this.curated = curated;
    }

    public FieldsSSRow withCurated(Long curated) {
        this.curated = curated;
        return this;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    public FieldsSSRow withRegion(String region) {
        this.region = region;
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
        return ((((((((("FieldsSSRow"+" [id=")+ id)+", curated=")+ curated)+", region=")+ region)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
