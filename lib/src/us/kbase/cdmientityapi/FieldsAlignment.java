
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
 * <p>Original spec-file type: fields_Alignment</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "n_rows",
    "n_cols",
    "status",
    "is_concatenation",
    "sequence_type",
    "timestamp",
    "method",
    "parameters",
    "protocol",
    "source_id"
})
public class FieldsAlignment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("n_rows")
    private Long nRows;
    @JsonProperty("n_cols")
    private Long nCols;
    @JsonProperty("status")
    private String status;
    @JsonProperty("is_concatenation")
    private Long isConcatenation;
    @JsonProperty("sequence_type")
    private String sequenceType;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("method")
    private String method;
    @JsonProperty("parameters")
    private String parameters;
    @JsonProperty("protocol")
    private String protocol;
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

    public FieldsAlignment withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("n_rows")
    public Long getNRows() {
        return nRows;
    }

    @JsonProperty("n_rows")
    public void setNRows(Long nRows) {
        this.nRows = nRows;
    }

    public FieldsAlignment withNRows(Long nRows) {
        this.nRows = nRows;
        return this;
    }

    @JsonProperty("n_cols")
    public Long getNCols() {
        return nCols;
    }

    @JsonProperty("n_cols")
    public void setNCols(Long nCols) {
        this.nCols = nCols;
    }

    public FieldsAlignment withNCols(Long nCols) {
        this.nCols = nCols;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public FieldsAlignment withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("is_concatenation")
    public Long getIsConcatenation() {
        return isConcatenation;
    }

    @JsonProperty("is_concatenation")
    public void setIsConcatenation(Long isConcatenation) {
        this.isConcatenation = isConcatenation;
    }

    public FieldsAlignment withIsConcatenation(Long isConcatenation) {
        this.isConcatenation = isConcatenation;
        return this;
    }

    @JsonProperty("sequence_type")
    public String getSequenceType() {
        return sequenceType;
    }

    @JsonProperty("sequence_type")
    public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
    }

    public FieldsAlignment withSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
        return this;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public FieldsAlignment withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    public FieldsAlignment withMethod(String method) {
        this.method = method;
        return this;
    }

    @JsonProperty("parameters")
    public String getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public FieldsAlignment withParameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public FieldsAlignment withProtocol(String protocol) {
        this.protocol = protocol;
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

    public FieldsAlignment withSourceId(String sourceId) {
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
        return ((((((((((((((((((((((((("FieldsAlignment"+" [id=")+ id)+", nRows=")+ nRows)+", nCols=")+ nCols)+", status=")+ status)+", isConcatenation=")+ isConcatenation)+", sequenceType=")+ sequenceType)+", timestamp=")+ timestamp)+", method=")+ method)+", parameters=")+ parameters)+", protocol=")+ protocol)+", sourceId=")+ sourceId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
