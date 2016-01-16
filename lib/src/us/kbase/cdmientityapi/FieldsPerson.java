
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
 * <p>Original spec-file type: fields_Person</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "firstName",
    "lastName",
    "contactEmail",
    "institution",
    "source_id"
})
public class FieldsPerson {

    @JsonProperty("id")
    private String id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("contactEmail")
    private String contactEmail;
    @JsonProperty("institution")
    private String institution;
    @JsonProperty("source_id")
    private String sourceId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsPerson withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public FieldsPerson withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public FieldsPerson withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @JsonProperty("contactEmail")
    public String getContactEmail() {
        return contactEmail;
    }

    @JsonProperty("contactEmail")
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public FieldsPerson withContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    @JsonProperty("institution")
    public String getInstitution() {
        return institution;
    }

    @JsonProperty("institution")
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public FieldsPerson withInstitution(String institution) {
        this.institution = institution;
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

    public FieldsPerson withSourceId(String sourceId) {
        this.sourceId = sourceId;
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
        return ((((((((((((((("FieldsPerson"+" [id=")+ id)+", firstName=")+ firstName)+", lastName=")+ lastName)+", contactEmail=")+ contactEmail)+", institution=")+ institution)+", sourceId=")+ sourceId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
