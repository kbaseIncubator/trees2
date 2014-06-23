
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
 * <p>Original spec-file type: fields_StudyExperiment</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_name",
    "design",
    "originator"
})
public class FieldsStudyExperiment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("source_name")
    private String sourceName;
    @JsonProperty("design")
    private String design;
    @JsonProperty("originator")
    private String originator;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsStudyExperiment withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("source_name")
    public String getSourceName() {
        return sourceName;
    }

    @JsonProperty("source_name")
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public FieldsStudyExperiment withSourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    @JsonProperty("design")
    public String getDesign() {
        return design;
    }

    @JsonProperty("design")
    public void setDesign(String design) {
        this.design = design;
    }

    public FieldsStudyExperiment withDesign(String design) {
        this.design = design;
        return this;
    }

    @JsonProperty("originator")
    public String getOriginator() {
        return originator;
    }

    @JsonProperty("originator")
    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public FieldsStudyExperiment withOriginator(String originator) {
        this.originator = originator;
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
        return ((((((((((("FieldsStudyExperiment"+" [id=")+ id)+", sourceName=")+ sourceName)+", design=")+ design)+", originator=")+ originator)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
