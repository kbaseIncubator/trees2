
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
 * <p>Original spec-file type: fields_AlleleFrequency</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_id",
    "position",
    "minor_AF",
    "minor_allele",
    "major_AF",
    "major_allele",
    "obs_unit_count"
})
public class FieldsAlleleFrequency {

    @JsonProperty("id")
    private String id;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("position")
    private Long position;
    @JsonProperty("minor_AF")
    private Double minorAF;
    @JsonProperty("minor_allele")
    private String minorAllele;
    @JsonProperty("major_AF")
    private Double majorAF;
    @JsonProperty("major_allele")
    private String majorAllele;
    @JsonProperty("obs_unit_count")
    private Long obsUnitCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsAlleleFrequency withId(String id) {
        this.id = id;
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

    public FieldsAlleleFrequency withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("position")
    public Long getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Long position) {
        this.position = position;
    }

    public FieldsAlleleFrequency withPosition(Long position) {
        this.position = position;
        return this;
    }

    @JsonProperty("minor_AF")
    public Double getMinorAF() {
        return minorAF;
    }

    @JsonProperty("minor_AF")
    public void setMinorAF(Double minorAF) {
        this.minorAF = minorAF;
    }

    public FieldsAlleleFrequency withMinorAF(Double minorAF) {
        this.minorAF = minorAF;
        return this;
    }

    @JsonProperty("minor_allele")
    public String getMinorAllele() {
        return minorAllele;
    }

    @JsonProperty("minor_allele")
    public void setMinorAllele(String minorAllele) {
        this.minorAllele = minorAllele;
    }

    public FieldsAlleleFrequency withMinorAllele(String minorAllele) {
        this.minorAllele = minorAllele;
        return this;
    }

    @JsonProperty("major_AF")
    public Double getMajorAF() {
        return majorAF;
    }

    @JsonProperty("major_AF")
    public void setMajorAF(Double majorAF) {
        this.majorAF = majorAF;
    }

    public FieldsAlleleFrequency withMajorAF(Double majorAF) {
        this.majorAF = majorAF;
        return this;
    }

    @JsonProperty("major_allele")
    public String getMajorAllele() {
        return majorAllele;
    }

    @JsonProperty("major_allele")
    public void setMajorAllele(String majorAllele) {
        this.majorAllele = majorAllele;
    }

    public FieldsAlleleFrequency withMajorAllele(String majorAllele) {
        this.majorAllele = majorAllele;
        return this;
    }

    @JsonProperty("obs_unit_count")
    public Long getObsUnitCount() {
        return obsUnitCount;
    }

    @JsonProperty("obs_unit_count")
    public void setObsUnitCount(Long obsUnitCount) {
        this.obsUnitCount = obsUnitCount;
    }

    public FieldsAlleleFrequency withObsUnitCount(Long obsUnitCount) {
        this.obsUnitCount = obsUnitCount;
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
        return ((((((((((((((((((("FieldsAlleleFrequency"+" [id=")+ id)+", sourceId=")+ sourceId)+", position=")+ position)+", minorAF=")+ minorAF)+", minorAllele=")+ minorAllele)+", majorAF=")+ majorAF)+", majorAllele=")+ majorAllele)+", obsUnitCount=")+ obsUnitCount)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
