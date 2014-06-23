
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
 * <p>Original spec-file type: fields_AssertsFunctionFor</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "function",
    "external_id",
    "organism",
    "gi_number",
    "release_date"
})
public class FieldsAssertsFunctionFor {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("function")
    private String function;
    @JsonProperty("external_id")
    private String externalId;
    @JsonProperty("organism")
    private String organism;
    @JsonProperty("gi_number")
    private Long giNumber;
    @JsonProperty("release_date")
    private String releaseDate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsAssertsFunctionFor withFromLink(String fromLink) {
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

    public FieldsAssertsFunctionFor withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("function")
    public String getFunction() {
        return function;
    }

    @JsonProperty("function")
    public void setFunction(String function) {
        this.function = function;
    }

    public FieldsAssertsFunctionFor withFunction(String function) {
        this.function = function;
        return this;
    }

    @JsonProperty("external_id")
    public String getExternalId() {
        return externalId;
    }

    @JsonProperty("external_id")
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public FieldsAssertsFunctionFor withExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    @JsonProperty("organism")
    public String getOrganism() {
        return organism;
    }

    @JsonProperty("organism")
    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public FieldsAssertsFunctionFor withOrganism(String organism) {
        this.organism = organism;
        return this;
    }

    @JsonProperty("gi_number")
    public Long getGiNumber() {
        return giNumber;
    }

    @JsonProperty("gi_number")
    public void setGiNumber(Long giNumber) {
        this.giNumber = giNumber;
    }

    public FieldsAssertsFunctionFor withGiNumber(Long giNumber) {
        this.giNumber = giNumber;
        return this;
    }

    @JsonProperty("release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("release_date")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public FieldsAssertsFunctionFor withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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
        return ((((((((((((((((("FieldsAssertsFunctionFor"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", function=")+ function)+", externalId=")+ externalId)+", organism=")+ organism)+", giNumber=")+ giNumber)+", releaseDate=")+ releaseDate)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
