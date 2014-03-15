
package us.kbase.kbasetrees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: ConstructSpeciesTreeParams</p>
 * <pre>
 * Input data type for construct_species_tree method.
 *         new_genomes - (required) the list of genome references to use in constructing a tree
 *         out_workspace - (required) the workspace to deposit the completed tree
 *         out_tree_id - (optional) the name of the newly constructed tree (will be random if not present or null)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "new_genomes",
    "out_workspace",
    "out_tree_id"
})
public class ConstructSpeciesTreeParams {

    @JsonProperty("new_genomes")
    private List<String> newGenomes;
    @JsonProperty("out_workspace")
    private java.lang.String outWorkspace;
    @JsonProperty("out_tree_id")
    private java.lang.String outTreeId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("new_genomes")
    public List<String> getNewGenomes() {
        return newGenomes;
    }

    @JsonProperty("new_genomes")
    public void setNewGenomes(List<String> newGenomes) {
        this.newGenomes = newGenomes;
    }

    public ConstructSpeciesTreeParams withNewGenomes(List<String> newGenomes) {
        this.newGenomes = newGenomes;
        return this;
    }

    @JsonProperty("out_workspace")
    public java.lang.String getOutWorkspace() {
        return outWorkspace;
    }

    @JsonProperty("out_workspace")
    public void setOutWorkspace(java.lang.String outWorkspace) {
        this.outWorkspace = outWorkspace;
    }

    public ConstructSpeciesTreeParams withOutWorkspace(java.lang.String outWorkspace) {
        this.outWorkspace = outWorkspace;
        return this;
    }

    @JsonProperty("out_tree_id")
    public java.lang.String getOutTreeId() {
        return outTreeId;
    }

    @JsonProperty("out_tree_id")
    public void setOutTreeId(java.lang.String outTreeId) {
        this.outTreeId = outTreeId;
    }

    public ConstructSpeciesTreeParams withOutTreeId(java.lang.String outTreeId) {
        this.outTreeId = outTreeId;
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
        return ((((((((("ConstructSpeciesTreeParams"+" [newGenomes=")+ newGenomes)+", outWorkspace=")+ outWorkspace)+", outTreeId=")+ outTreeId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
