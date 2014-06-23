
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
 * <p>Original spec-file type: fields_Tree</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "status",
    "data_type",
    "timestamp",
    "method",
    "parameters",
    "protocol",
    "source_id",
    "newick"
})
public class FieldsTree {

    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("data_type")
    private String dataType;
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
    @JsonProperty("newick")
    private String newick;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsTree withId(String id) {
        this.id = id;
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

    public FieldsTree withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("data_type")
    public String getDataType() {
        return dataType;
    }

    @JsonProperty("data_type")
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public FieldsTree withDataType(String dataType) {
        this.dataType = dataType;
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

    public FieldsTree withTimestamp(String timestamp) {
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

    public FieldsTree withMethod(String method) {
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

    public FieldsTree withParameters(String parameters) {
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

    public FieldsTree withProtocol(String protocol) {
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

    public FieldsTree withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("newick")
    public String getNewick() {
        return newick;
    }

    @JsonProperty("newick")
    public void setNewick(String newick) {
        this.newick = newick;
    }

    public FieldsTree withNewick(String newick) {
        this.newick = newick;
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
        return ((((((((((((((((((((("FieldsTree"+" [id=")+ id)+", status=")+ status)+", dataType=")+ dataType)+", timestamp=")+ timestamp)+", method=")+ method)+", parameters=")+ parameters)+", protocol=")+ protocol)+", sourceId=")+ sourceId)+", newick=")+ newick)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
