
package us.kbase.kbasetrees;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: FilterParams</p>
 * <pre>
 * cutoff_value                  => def: 0 || [any_valid_float_value]
 * use_cutoff_value              => def: 0 || 1
 * normalization_scope           => def:'per_column' || 'global'
 * normalization_type            => def:'none' || 'total' || 'mean' || 'max' || 'min'
 * normalization_post_process    => def:'none' || 'log10' || 'log2' || 'ln'
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "cutoff_value",
    "use_cutoff_value",
    "cutoff_number_of_records",
    "use_cutoff_number_of_records",
    "normalization_scope",
    "normalization_type",
    "normalization_post_process"
})
public class FilterParams {

    @JsonProperty("cutoff_value")
    private Double cutoffValue;
    @JsonProperty("use_cutoff_value")
    private Long useCutoffValue;
    @JsonProperty("cutoff_number_of_records")
    private Double cutoffNumberOfRecords;
    @JsonProperty("use_cutoff_number_of_records")
    private Long useCutoffNumberOfRecords;
    @JsonProperty("normalization_scope")
    private String normalizationScope;
    @JsonProperty("normalization_type")
    private String normalizationType;
    @JsonProperty("normalization_post_process")
    private String normalizationPostProcess;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cutoff_value")
    public Double getCutoffValue() {
        return cutoffValue;
    }

    @JsonProperty("cutoff_value")
    public void setCutoffValue(Double cutoffValue) {
        this.cutoffValue = cutoffValue;
    }

    public FilterParams withCutoffValue(Double cutoffValue) {
        this.cutoffValue = cutoffValue;
        return this;
    }

    @JsonProperty("use_cutoff_value")
    public Long getUseCutoffValue() {
        return useCutoffValue;
    }

    @JsonProperty("use_cutoff_value")
    public void setUseCutoffValue(Long useCutoffValue) {
        this.useCutoffValue = useCutoffValue;
    }

    public FilterParams withUseCutoffValue(Long useCutoffValue) {
        this.useCutoffValue = useCutoffValue;
        return this;
    }

    @JsonProperty("cutoff_number_of_records")
    public Double getCutoffNumberOfRecords() {
        return cutoffNumberOfRecords;
    }

    @JsonProperty("cutoff_number_of_records")
    public void setCutoffNumberOfRecords(Double cutoffNumberOfRecords) {
        this.cutoffNumberOfRecords = cutoffNumberOfRecords;
    }

    public FilterParams withCutoffNumberOfRecords(Double cutoffNumberOfRecords) {
        this.cutoffNumberOfRecords = cutoffNumberOfRecords;
        return this;
    }

    @JsonProperty("use_cutoff_number_of_records")
    public Long getUseCutoffNumberOfRecords() {
        return useCutoffNumberOfRecords;
    }

    @JsonProperty("use_cutoff_number_of_records")
    public void setUseCutoffNumberOfRecords(Long useCutoffNumberOfRecords) {
        this.useCutoffNumberOfRecords = useCutoffNumberOfRecords;
    }

    public FilterParams withUseCutoffNumberOfRecords(Long useCutoffNumberOfRecords) {
        this.useCutoffNumberOfRecords = useCutoffNumberOfRecords;
        return this;
    }

    @JsonProperty("normalization_scope")
    public String getNormalizationScope() {
        return normalizationScope;
    }

    @JsonProperty("normalization_scope")
    public void setNormalizationScope(String normalizationScope) {
        this.normalizationScope = normalizationScope;
    }

    public FilterParams withNormalizationScope(String normalizationScope) {
        this.normalizationScope = normalizationScope;
        return this;
    }

    @JsonProperty("normalization_type")
    public String getNormalizationType() {
        return normalizationType;
    }

    @JsonProperty("normalization_type")
    public void setNormalizationType(String normalizationType) {
        this.normalizationType = normalizationType;
    }

    public FilterParams withNormalizationType(String normalizationType) {
        this.normalizationType = normalizationType;
        return this;
    }

    @JsonProperty("normalization_post_process")
    public String getNormalizationPostProcess() {
        return normalizationPostProcess;
    }

    @JsonProperty("normalization_post_process")
    public void setNormalizationPostProcess(String normalizationPostProcess) {
        this.normalizationPostProcess = normalizationPostProcess;
    }

    public FilterParams withNormalizationPostProcess(String normalizationPostProcess) {
        this.normalizationPostProcess = normalizationPostProcess;
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
        return ((((((((((((((((("FilterParams"+" [cutoffValue=")+ cutoffValue)+", useCutoffValue=")+ useCutoffValue)+", cutoffNumberOfRecords=")+ cutoffNumberOfRecords)+", useCutoffNumberOfRecords=")+ useCutoffNumberOfRecords)+", normalizationScope=")+ normalizationScope)+", normalizationType=")+ normalizationType)+", normalizationPostProcess=")+ normalizationPostProcess)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
