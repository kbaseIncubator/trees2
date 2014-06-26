
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
 * <p>Original spec-file type: CdsImportTreeParameters</p>
 * <pre>
 * Parameters for importing phylogentic tree data from the Central Data Store to
 * the Workspace, which allows you to manipulate, edit, and use the tree data in
 * the narrative interface.
 * load_alignment_for_tree - if true, load the alignment that was used to build the tree (default = false)
 * default label => one of protein_md5, feature, genome, genome_species
 * @optional load_alignment_for_tree
 * @optional ws_tree_name additional_tree_ws_metadata
 * @optional ws_alignment_name additional_alignment_ws_metadata
 * @optional link_nodes_to_protein_sequence link_nodes_to_exemplar_feature link_nodes_to_exemplar_genome
 * @optional default_label
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "tree_id",
    "load_alignment_for_tree",
    "ws_tree_name",
    "additional_tree_ws_metadata",
    "ws_alignment_name",
    "additional_alignment_ws_metadata",
    "link_nodes_to_protein_sequence",
    "link_nodes_to_exemplar_feature",
    "link_nodes_to_exemplar_genome",
    "default_label"
})
public class CdsImportTreeParameters {

    @JsonProperty("tree_id")
    private java.lang.String treeId;
    @JsonProperty("load_alignment_for_tree")
    private Long loadAlignmentForTree;
    @JsonProperty("ws_tree_name")
    private java.lang.String wsTreeName;
    @JsonProperty("additional_tree_ws_metadata")
    private Map<String, String> additionalTreeWsMetadata;
    @JsonProperty("ws_alignment_name")
    private java.lang.String wsAlignmentName;
    @JsonProperty("additional_alignment_ws_metadata")
    private Map<String, String> additionalAlignmentWsMetadata;
    @JsonProperty("link_nodes_to_protein_sequence")
    private Long linkNodesToProteinSequence;
    @JsonProperty("link_nodes_to_exemplar_feature")
    private Long linkNodesToExemplarFeature;
    @JsonProperty("link_nodes_to_exemplar_genome")
    private Long linkNodesToExemplarGenome;
    @JsonProperty("default_label")
    private java.lang.String defaultLabel;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("tree_id")
    public java.lang.String getTreeId() {
        return treeId;
    }

    @JsonProperty("tree_id")
    public void setTreeId(java.lang.String treeId) {
        this.treeId = treeId;
    }

    public CdsImportTreeParameters withTreeId(java.lang.String treeId) {
        this.treeId = treeId;
        return this;
    }

    @JsonProperty("load_alignment_for_tree")
    public Long getLoadAlignmentForTree() {
        return loadAlignmentForTree;
    }

    @JsonProperty("load_alignment_for_tree")
    public void setLoadAlignmentForTree(Long loadAlignmentForTree) {
        this.loadAlignmentForTree = loadAlignmentForTree;
    }

    public CdsImportTreeParameters withLoadAlignmentForTree(Long loadAlignmentForTree) {
        this.loadAlignmentForTree = loadAlignmentForTree;
        return this;
    }

    @JsonProperty("ws_tree_name")
    public java.lang.String getWsTreeName() {
        return wsTreeName;
    }

    @JsonProperty("ws_tree_name")
    public void setWsTreeName(java.lang.String wsTreeName) {
        this.wsTreeName = wsTreeName;
    }

    public CdsImportTreeParameters withWsTreeName(java.lang.String wsTreeName) {
        this.wsTreeName = wsTreeName;
        return this;
    }

    @JsonProperty("additional_tree_ws_metadata")
    public Map<String, String> getAdditionalTreeWsMetadata() {
        return additionalTreeWsMetadata;
    }

    @JsonProperty("additional_tree_ws_metadata")
    public void setAdditionalTreeWsMetadata(Map<String, String> additionalTreeWsMetadata) {
        this.additionalTreeWsMetadata = additionalTreeWsMetadata;
    }

    public CdsImportTreeParameters withAdditionalTreeWsMetadata(Map<String, String> additionalTreeWsMetadata) {
        this.additionalTreeWsMetadata = additionalTreeWsMetadata;
        return this;
    }

    @JsonProperty("ws_alignment_name")
    public java.lang.String getWsAlignmentName() {
        return wsAlignmentName;
    }

    @JsonProperty("ws_alignment_name")
    public void setWsAlignmentName(java.lang.String wsAlignmentName) {
        this.wsAlignmentName = wsAlignmentName;
    }

    public CdsImportTreeParameters withWsAlignmentName(java.lang.String wsAlignmentName) {
        this.wsAlignmentName = wsAlignmentName;
        return this;
    }

    @JsonProperty("additional_alignment_ws_metadata")
    public Map<String, String> getAdditionalAlignmentWsMetadata() {
        return additionalAlignmentWsMetadata;
    }

    @JsonProperty("additional_alignment_ws_metadata")
    public void setAdditionalAlignmentWsMetadata(Map<String, String> additionalAlignmentWsMetadata) {
        this.additionalAlignmentWsMetadata = additionalAlignmentWsMetadata;
    }

    public CdsImportTreeParameters withAdditionalAlignmentWsMetadata(Map<String, String> additionalAlignmentWsMetadata) {
        this.additionalAlignmentWsMetadata = additionalAlignmentWsMetadata;
        return this;
    }

    @JsonProperty("link_nodes_to_protein_sequence")
    public Long getLinkNodesToProteinSequence() {
        return linkNodesToProteinSequence;
    }

    @JsonProperty("link_nodes_to_protein_sequence")
    public void setLinkNodesToProteinSequence(Long linkNodesToProteinSequence) {
        this.linkNodesToProteinSequence = linkNodesToProteinSequence;
    }

    public CdsImportTreeParameters withLinkNodesToProteinSequence(Long linkNodesToProteinSequence) {
        this.linkNodesToProteinSequence = linkNodesToProteinSequence;
        return this;
    }

    @JsonProperty("link_nodes_to_exemplar_feature")
    public Long getLinkNodesToExemplarFeature() {
        return linkNodesToExemplarFeature;
    }

    @JsonProperty("link_nodes_to_exemplar_feature")
    public void setLinkNodesToExemplarFeature(Long linkNodesToExemplarFeature) {
        this.linkNodesToExemplarFeature = linkNodesToExemplarFeature;
    }

    public CdsImportTreeParameters withLinkNodesToExemplarFeature(Long linkNodesToExemplarFeature) {
        this.linkNodesToExemplarFeature = linkNodesToExemplarFeature;
        return this;
    }

    @JsonProperty("link_nodes_to_exemplar_genome")
    public Long getLinkNodesToExemplarGenome() {
        return linkNodesToExemplarGenome;
    }

    @JsonProperty("link_nodes_to_exemplar_genome")
    public void setLinkNodesToExemplarGenome(Long linkNodesToExemplarGenome) {
        this.linkNodesToExemplarGenome = linkNodesToExemplarGenome;
    }

    public CdsImportTreeParameters withLinkNodesToExemplarGenome(Long linkNodesToExemplarGenome) {
        this.linkNodesToExemplarGenome = linkNodesToExemplarGenome;
        return this;
    }

    @JsonProperty("default_label")
    public java.lang.String getDefaultLabel() {
        return defaultLabel;
    }

    @JsonProperty("default_label")
    public void setDefaultLabel(java.lang.String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }

    public CdsImportTreeParameters withDefaultLabel(java.lang.String defaultLabel) {
        this.defaultLabel = defaultLabel;
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
        return ((((((((((((((((((((((("CdsImportTreeParameters"+" [treeId=")+ treeId)+", loadAlignmentForTree=")+ loadAlignmentForTree)+", wsTreeName=")+ wsTreeName)+", additionalTreeWsMetadata=")+ additionalTreeWsMetadata)+", wsAlignmentName=")+ wsAlignmentName)+", additionalAlignmentWsMetadata=")+ additionalAlignmentWsMetadata)+", linkNodesToProteinSequence=")+ linkNodesToProteinSequence)+", linkNodesToExemplarFeature=")+ linkNodesToExemplarFeature)+", linkNodesToExemplarGenome=")+ linkNodesToExemplarGenome)+", defaultLabel=")+ defaultLabel)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
