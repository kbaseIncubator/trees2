
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
 * <p>Original spec-file type: TreeMetaData</p>
 * <pre>
 * Meta data associated with a tree.
 *     kbase_id alignment_id - if this tree was built from an alignment, this provides that alignment id
 *     string type - the type of tree; possible values currently are "sequence_alignment" and "genome" for trees
 *                   either built from a sequence alignment, or imported directly indexed to genomes.
 *     string status - set to 'active' if this is the latest built tree for a particular gene family
 *     timestamp date_created - time at which the tree was built/loaded in seconds since the epoch
 *     string tree_contruction_method - the name of the software used to construct the tree
 *     string tree_construction_parameters - any non-default parameters of the tree construction method
 *     string tree_protocol - simple free-form text which may provide additional details of how the tree was built
 *     int node_count - total number of nodes in the tree
 *     int leaf_count - total number of leaf nodes in the tree (generally this cooresponds to the number of sequences)
 *     string source_db - the source database where this tree originated, if one exists
 *     string source_id - the id of this tree in an external database, if one exists
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "alignment_id",
    "type",
    "status",
    "date_created",
    "tree_contruction_method",
    "tree_construction_parameters",
    "tree_protocol",
    "node_count",
    "leaf_count",
    "source_db",
    "source_id"
})
public class TreeMetaData {

    @JsonProperty("alignment_id")
    private String alignmentId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("tree_contruction_method")
    private String treeContructionMethod;
    @JsonProperty("tree_construction_parameters")
    private String treeConstructionParameters;
    @JsonProperty("tree_protocol")
    private String treeProtocol;
    @JsonProperty("node_count")
    private Long nodeCount;
    @JsonProperty("leaf_count")
    private Long leafCount;
    @JsonProperty("source_db")
    private String sourceDb;
    @JsonProperty("source_id")
    private String sourceId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("alignment_id")
    public String getAlignmentId() {
        return alignmentId;
    }

    @JsonProperty("alignment_id")
    public void setAlignmentId(String alignmentId) {
        this.alignmentId = alignmentId;
    }

    public TreeMetaData withAlignmentId(String alignmentId) {
        this.alignmentId = alignmentId;
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

    public TreeMetaData withType(String type) {
        this.type = type;
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

    public TreeMetaData withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("date_created")
    public String getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("date_created")
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public TreeMetaData withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @JsonProperty("tree_contruction_method")
    public String getTreeContructionMethod() {
        return treeContructionMethod;
    }

    @JsonProperty("tree_contruction_method")
    public void setTreeContructionMethod(String treeContructionMethod) {
        this.treeContructionMethod = treeContructionMethod;
    }

    public TreeMetaData withTreeContructionMethod(String treeContructionMethod) {
        this.treeContructionMethod = treeContructionMethod;
        return this;
    }

    @JsonProperty("tree_construction_parameters")
    public String getTreeConstructionParameters() {
        return treeConstructionParameters;
    }

    @JsonProperty("tree_construction_parameters")
    public void setTreeConstructionParameters(String treeConstructionParameters) {
        this.treeConstructionParameters = treeConstructionParameters;
    }

    public TreeMetaData withTreeConstructionParameters(String treeConstructionParameters) {
        this.treeConstructionParameters = treeConstructionParameters;
        return this;
    }

    @JsonProperty("tree_protocol")
    public String getTreeProtocol() {
        return treeProtocol;
    }

    @JsonProperty("tree_protocol")
    public void setTreeProtocol(String treeProtocol) {
        this.treeProtocol = treeProtocol;
    }

    public TreeMetaData withTreeProtocol(String treeProtocol) {
        this.treeProtocol = treeProtocol;
        return this;
    }

    @JsonProperty("node_count")
    public Long getNodeCount() {
        return nodeCount;
    }

    @JsonProperty("node_count")
    public void setNodeCount(Long nodeCount) {
        this.nodeCount = nodeCount;
    }

    public TreeMetaData withNodeCount(Long nodeCount) {
        this.nodeCount = nodeCount;
        return this;
    }

    @JsonProperty("leaf_count")
    public Long getLeafCount() {
        return leafCount;
    }

    @JsonProperty("leaf_count")
    public void setLeafCount(Long leafCount) {
        this.leafCount = leafCount;
    }

    public TreeMetaData withLeafCount(Long leafCount) {
        this.leafCount = leafCount;
        return this;
    }

    @JsonProperty("source_db")
    public String getSourceDb() {
        return sourceDb;
    }

    @JsonProperty("source_db")
    public void setSourceDb(String sourceDb) {
        this.sourceDb = sourceDb;
    }

    public TreeMetaData withSourceDb(String sourceDb) {
        this.sourceDb = sourceDb;
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

    public TreeMetaData withSourceId(String sourceId) {
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
        return ((((((((((((((((((((((((("TreeMetaData"+" [alignmentId=")+ alignmentId)+", type=")+ type)+", status=")+ status)+", dateCreated=")+ dateCreated)+", treeContructionMethod=")+ treeContructionMethod)+", treeConstructionParameters=")+ treeConstructionParameters)+", treeProtocol=")+ treeProtocol)+", nodeCount=")+ nodeCount)+", leafCount=")+ leafCount)+", sourceDb=")+ sourceDb)+", sourceId=")+ sourceId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
