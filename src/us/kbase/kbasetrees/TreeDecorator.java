
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
 * <p>Original spec-file type: TreeDecorator</p>
 * <pre>
 * Data type that stores a view of a tree.
 * @optional node_labels node_attributes node_values
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ws_tree_id",
    "node_labels",
    "node_attributes",
    "node_values"
})
public class TreeDecorator {

    @JsonProperty("ws_tree_id")
    private java.lang.String wsTreeId;
    @JsonProperty("node_labels")
    private Map<String, String> nodeLabels;
    @JsonProperty("node_attributes")
    private Map<String, Map<String, String>> nodeAttributes;
    @JsonProperty("node_values")
    private Map<String, Map<String, Double>> nodeValues;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ws_tree_id")
    public java.lang.String getWsTreeId() {
        return wsTreeId;
    }

    @JsonProperty("ws_tree_id")
    public void setWsTreeId(java.lang.String wsTreeId) {
        this.wsTreeId = wsTreeId;
    }

    public TreeDecorator withWsTreeId(java.lang.String wsTreeId) {
        this.wsTreeId = wsTreeId;
        return this;
    }

    @JsonProperty("node_labels")
    public Map<String, String> getNodeLabels() {
        return nodeLabels;
    }

    @JsonProperty("node_labels")
    public void setNodeLabels(Map<String, String> nodeLabels) {
        this.nodeLabels = nodeLabels;
    }

    public TreeDecorator withNodeLabels(Map<String, String> nodeLabels) {
        this.nodeLabels = nodeLabels;
        return this;
    }

    @JsonProperty("node_attributes")
    public Map<String, Map<String, String>> getNodeAttributes() {
        return nodeAttributes;
    }

    @JsonProperty("node_attributes")
    public void setNodeAttributes(Map<String, Map<String, String>> nodeAttributes) {
        this.nodeAttributes = nodeAttributes;
    }

    public TreeDecorator withNodeAttributes(Map<String, Map<String, String>> nodeAttributes) {
        this.nodeAttributes = nodeAttributes;
        return this;
    }

    @JsonProperty("node_values")
    public Map<String, Map<String, Double>> getNodeValues() {
        return nodeValues;
    }

    @JsonProperty("node_values")
    public void setNodeValues(Map<String, Map<String, Double>> nodeValues) {
        this.nodeValues = nodeValues;
    }

    public TreeDecorator withNodeValues(Map<String, Map<String, Double>> nodeValues) {
        this.nodeValues = nodeValues;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((("TreeDecorator"+" [wsTreeId=")+ wsTreeId)+", nodeLabels=")+ nodeLabels)+", nodeAttributes=")+ nodeAttributes)+", nodeValues=")+ nodeValues)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
