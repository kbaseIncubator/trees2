
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
import us.kbase.common.service.Tuple2;


/**
 * <p>Original spec-file type: SpeciesTree</p>
 * <pre>
 * The structure of a tree itself.
 *         tree - the Newick string representing the tree itself
 *         id_map - maps from internal tree node ids to workspace references for each genome
 *         alignment_ref - (optional) the reference to the alignment from which the tree was built
 *         cogs - the list of NCBI COG ids that were used to build the tree
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "species_tree",
    "id_map",
    "alignment_ref",
    "cogs"
})
public class SpeciesTree {

    @JsonProperty("species_tree")
    private java.lang.String speciesTree;
    @JsonProperty("id_map")
    private Map<String, Tuple2 <String, String>> idMap;
    @JsonProperty("alignment_ref")
    private java.lang.String alignmentRef;
    @JsonProperty("cogs")
    private List<String> cogs;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("species_tree")
    public java.lang.String getSpeciesTree() {
        return speciesTree;
    }

    @JsonProperty("species_tree")
    public void setSpeciesTree(java.lang.String speciesTree) {
        this.speciesTree = speciesTree;
    }

    public SpeciesTree withSpeciesTree(java.lang.String speciesTree) {
        this.speciesTree = speciesTree;
        return this;
    }

    @JsonProperty("id_map")
    public Map<String, Tuple2 <String, String>> getIdMap() {
        return idMap;
    }

    @JsonProperty("id_map")
    public void setIdMap(Map<String, Tuple2 <String, String>> idMap) {
        this.idMap = idMap;
    }

    public SpeciesTree withIdMap(Map<String, Tuple2 <String, String>> idMap) {
        this.idMap = idMap;
        return this;
    }

    @JsonProperty("alignment_ref")
    public java.lang.String getAlignmentRef() {
        return alignmentRef;
    }

    @JsonProperty("alignment_ref")
    public void setAlignmentRef(java.lang.String alignmentRef) {
        this.alignmentRef = alignmentRef;
    }

    public SpeciesTree withAlignmentRef(java.lang.String alignmentRef) {
        this.alignmentRef = alignmentRef;
        return this;
    }

    @JsonProperty("cogs")
    public List<String> getCogs() {
        return cogs;
    }

    @JsonProperty("cogs")
    public void setCogs(List<String> cogs) {
        this.cogs = cogs;
    }

    public SpeciesTree withCogs(List<String> cogs) {
        this.cogs = cogs;
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
        return ((((((((((("SpeciesTree"+" [speciesTree=")+ speciesTree)+", idMap=")+ idMap)+", alignmentRef=")+ alignmentRef)+", cogs=")+ cogs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
