
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
 * <p>Original spec-file type: fields_Sample</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "title",
    "dataSource",
    "externalSourceId",
    "description",
    "molecule",
    "type",
    "kbaseSubmissionDate",
    "externalSourceDate",
    "custom",
    "originalLog2Median",
    "source_id",
    "dataQualityLevel"
})
public class FieldsSample {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("dataSource")
    private String dataSource;
    @JsonProperty("externalSourceId")
    private String externalSourceId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("molecule")
    private String molecule;
    @JsonProperty("type")
    private String type;
    @JsonProperty("kbaseSubmissionDate")
    private String kbaseSubmissionDate;
    @JsonProperty("externalSourceDate")
    private String externalSourceDate;
    @JsonProperty("custom")
    private String custom;
    @JsonProperty("originalLog2Median")
    private Double originalLog2Median;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("dataQualityLevel")
    private Long dataQualityLevel;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsSample withId(String id) {
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

    public FieldsSample withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("dataSource")
    public String getDataSource() {
        return dataSource;
    }

    @JsonProperty("dataSource")
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public FieldsSample withDataSource(String dataSource) {
        this.dataSource = dataSource;
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

    public FieldsSample withExternalSourceId(String externalSourceId) {
        this.externalSourceId = externalSourceId;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public FieldsSample withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("molecule")
    public String getMolecule() {
        return molecule;
    }

    @JsonProperty("molecule")
    public void setMolecule(String molecule) {
        this.molecule = molecule;
    }

    public FieldsSample withMolecule(String molecule) {
        this.molecule = molecule;
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

    public FieldsSample withType(String type) {
        this.type = type;
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

    public FieldsSample withKbaseSubmissionDate(String kbaseSubmissionDate) {
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

    public FieldsSample withExternalSourceDate(String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
        return this;
    }

    @JsonProperty("custom")
    public String getCustom() {
        return custom;
    }

    @JsonProperty("custom")
    public void setCustom(String custom) {
        this.custom = custom;
    }

    public FieldsSample withCustom(String custom) {
        this.custom = custom;
        return this;
    }

    @JsonProperty("originalLog2Median")
    public Double getOriginalLog2Median() {
        return originalLog2Median;
    }

    @JsonProperty("originalLog2Median")
    public void setOriginalLog2Median(Double originalLog2Median) {
        this.originalLog2Median = originalLog2Median;
    }

    public FieldsSample withOriginalLog2Median(Double originalLog2Median) {
        this.originalLog2Median = originalLog2Median;
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

    public FieldsSample withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("dataQualityLevel")
    public Long getDataQualityLevel() {
        return dataQualityLevel;
    }

    @JsonProperty("dataQualityLevel")
    public void setDataQualityLevel(Long dataQualityLevel) {
        this.dataQualityLevel = dataQualityLevel;
    }

    public FieldsSample withDataQualityLevel(Long dataQualityLevel) {
        this.dataQualityLevel = dataQualityLevel;
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
        return ((((((((((((((((((((((((((((("FieldsSample"+" [id=")+ id)+", title=")+ title)+", dataSource=")+ dataSource)+", externalSourceId=")+ externalSourceId)+", description=")+ description)+", molecule=")+ molecule)+", type=")+ type)+", kbaseSubmissionDate=")+ kbaseSubmissionDate)+", externalSourceDate=")+ externalSourceDate)+", custom=")+ custom)+", originalLog2Median=")+ originalLog2Median)+", sourceId=")+ sourceId)+", dataQualityLevel=")+ dataQualityLevel)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
