
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
 * @optional link_nodes_to_best_feature link_nodes_to_best_genome link_nodes_to_best_genome_name
 * @optional link_nodes_to_all_features link_nodes_to_all_genomes link_nodes_to_all_genome_names
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
    "link_nodes_to_best_feature",
    "link_nodes_to_best_genome",
    "link_nodes_to_best_genome_name",
    "link_nodes_to_all_features",
    "link_nodes_to_all_genomes",
    "link_nodes_to_all_genome_names",
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
    @JsonProperty("link_nodes_to_best_feature")
    private Long linkNodesToBestFeature;
    @JsonProperty("link_nodes_to_best_genome")
    private Long linkNodesToBestGenome;
    @JsonProperty("link_nodes_to_best_genome_name")
    private Long linkNodesToBestGenomeName;
    @JsonProperty("link_nodes_to_all_features")
    private Long linkNodesToAllFeatures;
    @JsonProperty("link_nodes_to_all_genomes")
    private Long linkNodesToAllGenomes;
    @JsonProperty("link_nodes_to_all_genome_names")
    private Long linkNodesToAllGenomeNames;
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

    @JsonProperty("link_nodes_to_best_feature")
    public Long getLinkNodesToBestFeature() {
        return linkNodesToBestFeature;
    }

    @JsonProperty("link_nodes_to_best_feature")
    public void setLinkNodesToBestFeature(Long linkNodesToBestFeature) {
        this.linkNodesToBestFeature = linkNodesToBestFeature;
    }

    public CdsImportTreeParameters withLinkNodesToBestFeature(Long linkNodesToBestFeature) {
        this.linkNodesToBestFeature = linkNodesToBestFeature;
        return this;
    }

    @JsonProperty("link_nodes_to_best_genome")
    public Long getLinkNodesToBestGenome() {
        return linkNodesToBestGenome;
    }

    @JsonProperty("link_nodes_to_best_genome")
    public void setLinkNodesToBestGenome(Long linkNodesToBestGenome) {
        this.linkNodesToBestGenome = linkNodesToBestGenome;
    }

    public CdsImportTreeParameters withLinkNodesToBestGenome(Long linkNodesToBestGenome) {
        this.linkNodesToBestGenome = linkNodesToBestGenome;
        return this;
    }

    @JsonProperty("link_nodes_to_best_genome_name")
    public Long getLinkNodesToBestGenomeName() {
        return linkNodesToBestGenomeName;
    }

    @JsonProperty("link_nodes_to_best_genome_name")
    public void setLinkNodesToBestGenomeName(Long linkNodesToBestGenomeName) {
        this.linkNodesToBestGenomeName = linkNodesToBestGenomeName;
    }

    public CdsImportTreeParameters withLinkNodesToBestGenomeName(Long linkNodesToBestGenomeName) {
        this.linkNodesToBestGenomeName = linkNodesToBestGenomeName;
        return this;
    }

    @JsonProperty("link_nodes_to_all_features")
    public Long getLinkNodesToAllFeatures() {
        return linkNodesToAllFeatures;
    }

    @JsonProperty("link_nodes_to_all_features")
    public void setLinkNodesToAllFeatures(Long linkNodesToAllFeatures) {
        this.linkNodesToAllFeatures = linkNodesToAllFeatures;
    }

    public CdsImportTreeParameters withLinkNodesToAllFeatures(Long linkNodesToAllFeatures) {
        this.linkNodesToAllFeatures = linkNodesToAllFeatures;
        return this;
    }

    @JsonProperty("link_nodes_to_all_genomes")
    public Long getLinkNodesToAllGenomes() {
        return linkNodesToAllGenomes;
    }

    @JsonProperty("link_nodes_to_all_genomes")
    public void setLinkNodesToAllGenomes(Long linkNodesToAllGenomes) {
        this.linkNodesToAllGenomes = linkNodesToAllGenomes;
    }

    public CdsImportTreeParameters withLinkNodesToAllGenomes(Long linkNodesToAllGenomes) {
        this.linkNodesToAllGenomes = linkNodesToAllGenomes;
        return this;
    }

    @JsonProperty("link_nodes_to_all_genome_names")
    public Long getLinkNodesToAllGenomeNames() {
        return linkNodesToAllGenomeNames;
    }

    @JsonProperty("link_nodes_to_all_genome_names")
    public void setLinkNodesToAllGenomeNames(Long linkNodesToAllGenomeNames) {
        this.linkNodesToAllGenomeNames = linkNodesToAllGenomeNames;
    }

    public CdsImportTreeParameters withLinkNodesToAllGenomeNames(Long linkNodesToAllGenomeNames) {
        this.linkNodesToAllGenomeNames = linkNodesToAllGenomeNames;
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
        return ((((((((((((((((((((((((((((("CdsImportTreeParameters"+" [treeId=")+ treeId)+", loadAlignmentForTree=")+ loadAlignmentForTree)+", wsTreeName=")+ wsTreeName)+", additionalTreeWsMetadata=")+ additionalTreeWsMetadata)+", wsAlignmentName=")+ wsAlignmentName)+", additionalAlignmentWsMetadata=")+ additionalAlignmentWsMetadata)+", linkNodesToBestFeature=")+ linkNodesToBestFeature)+", linkNodesToBestGenome=")+ linkNodesToBestGenome)+", linkNodesToBestGenomeName=")+ linkNodesToBestGenomeName)+", linkNodesToAllFeatures=")+ linkNodesToAllFeatures)+", linkNodesToAllGenomes=")+ linkNodesToAllGenomes)+", linkNodesToAllGenomeNames=")+ linkNodesToAllGenomeNames)+", defaultLabel=")+ defaultLabel)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
