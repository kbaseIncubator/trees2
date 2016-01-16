
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
 * <p>Original spec-file type: fields_Series</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "title",
    "summary",
    "design",
    "externalSourceId",
    "kbaseSubmissionDate",
    "externalSourceDate",
    "source_id"
})
public class FieldsSeries {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("design")
    private String design;
    @JsonProperty("externalSourceId")
    private String externalSourceId;
    @JsonProperty("kbaseSubmissionDate")
    private String kbaseSubmissionDate;
    @JsonProperty("externalSourceDate")
    private String externalSourceDate;
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

    public FieldsSeries withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public FieldsSeries withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public FieldsSeries withSummary(String summary) {
        this.summary = summary;
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

    public FieldsSeries withDesign(String design) {
        this.design = design;
        return this;
    }

    @JsonProperty("externalSourceId")
    public String getExternalSourceId() {
        return externalSourceId;
    }

    @JsonProperty("externalSourceId")
    public void setExternalSourceId(String externalSourceId) {
        this.externalSourceId = externalSourceId;
    }

    public FieldsSeries withExternalSourceId(String externalSourceId) {
        this.externalSourceId = externalSourceId;
        return this;
    }

    @JsonProperty("kbaseSubmissionDate")
    public String getKbaseSubmissionDate() {
        return kbaseSubmissionDate;
    }

    @JsonProperty("kbaseSubmissionDate")
    public void setKbaseSubmissionDate(String kbaseSubmissionDate) {
        this.kbaseSubmissionDate = kbaseSubmissionDate;
    }

    public FieldsSeries withKbaseSubmissionDate(String kbaseSubmissionDate) {
        this.kbaseSubmissionDate = kbaseSubmissionDate;
        return this;
    }

    @JsonProperty("externalSourceDate")
    public String getExternalSourceDate() {
        return externalSourceDate;
    }

    @JsonProperty("externalSourceDate")
    public void setExternalSourceDate(String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
    }

    public FieldsSeries withExternalSourceDate(String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
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

    public FieldsSeries withSourceId(String sourceId) {
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
        return ((((((((((((((((((("FieldsSeries"+" [id=")+ id)+", title=")+ title)+", summary=")+ summary)+", design=")+ design)+", externalSourceId=")+ externalSourceId)+", kbaseSubmissionDate=")+ kbaseSubmissionDate)+", externalSourceDate=")+ externalSourceDate)+", sourceId=")+ sourceId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
