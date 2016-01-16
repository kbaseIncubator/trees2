
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
 * <p>Original spec-file type: TreeDecoration</p>
 * <pre>
 * something looks strange with this field:  it was removed so we can compile
 *  mapping<tree_leaf_id tree_leaf_id, tuple<string substructure_label, string> substructure_by_leaf;
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "tree_id",
    "viz_title",
    "string_dataset_labels",
    "string_dataset_viz_types",
    "int_dataset_labels",
    "int_dataset_viz_types",
    "float_dataset_labels",
    "float_dataset_viz_types",
    "tree_val_list_string",
    "tree_val_list_int",
    "tree_val_list_float",
    "collapsed_by_node",
    "substructure_by_node",
    "rooted_flag",
    "alt_root_id"
})
public class TreeDecoration {

    @JsonProperty("tree_id")
    private java.lang.String treeId;
    @JsonProperty("viz_title")
    private java.lang.String vizTitle;
    @JsonProperty("string_dataset_labels")
    private List<String> stringDatasetLabels;
    @JsonProperty("string_dataset_viz_types")
    private List<String> stringDatasetVizTypes;
    @JsonProperty("int_dataset_labels")
    private List<String> intDatasetLabels;
    @JsonProperty("int_dataset_viz_types")
    private List<String> intDatasetVizTypes;
    @JsonProperty("float_dataset_labels")
    private List<String> floatDatasetLabels;
    @JsonProperty("float_dataset_viz_types")
    private List<String> floatDatasetVizTypes;
    @JsonProperty("tree_val_list_string")
    private Map<String, List<Tuple2 <String, String>>> treeValListString;
    @JsonProperty("tree_val_list_int")
    private Map<String, List<Tuple2 <Long, String>>> treeValListInt;
    @JsonProperty("tree_val_list_float")
    private Map<String, List<Tuple2 <Double, String>>> treeValListFloat;
    @JsonProperty("collapsed_by_node")
    private Map<String, String> collapsedByNode;
    @JsonProperty("substructure_by_node")
    private Map<String, Tuple2 <String, String>> substructureByNode;
    @JsonProperty("rooted_flag")
    private java.lang.String rootedFlag;
    @JsonProperty("alt_root_id")
    private java.lang.String altRootId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("tree_id")
    public java.lang.String getTreeId() {
        return treeId;
    }

    @JsonProperty("tree_id")
    public void setTreeId(java.lang.String treeId) {
        this.treeId = treeId;
    }

    public TreeDecoration withTreeId(java.lang.String treeId) {
        this.treeId = treeId;
        return this;
    }

    @JsonProperty("viz_title")
    public java.lang.String getVizTitle() {
        return vizTitle;
    }

    @JsonProperty("viz_title")
    public void setVizTitle(java.lang.String vizTitle) {
        this.vizTitle = vizTitle;
    }

    public TreeDecoration withVizTitle(java.lang.String vizTitle) {
        this.vizTitle = vizTitle;
        return this;
    }

    @JsonProperty("string_dataset_labels")
    public List<String> getStringDatasetLabels() {
        return stringDatasetLabels;
    }

    @JsonProperty("string_dataset_labels")
    public void setStringDatasetLabels(List<String> stringDatasetLabels) {
        this.stringDatasetLabels = stringDatasetLabels;
    }

    public TreeDecoration withStringDatasetLabels(List<String> stringDatasetLabels) {
        this.stringDatasetLabels = stringDatasetLabels;
        return this;
    }

    @JsonProperty("string_dataset_viz_types")
    public List<String> getStringDatasetVizTypes() {
        return stringDatasetVizTypes;
    }

    @JsonProperty("string_dataset_viz_types")
    public void setStringDatasetVizTypes(List<String> stringDatasetVizTypes) {
        this.stringDatasetVizTypes = stringDatasetVizTypes;
    }

    public TreeDecoration withStringDatasetVizTypes(List<String> stringDatasetVizTypes) {
        this.stringDatasetVizTypes = stringDatasetVizTypes;
        return this;
    }

    @JsonProperty("int_dataset_labels")
    public List<String> getIntDatasetLabels() {
        return intDatasetLabels;
    }

    @JsonProperty("int_dataset_labels")
    public void setIntDatasetLabels(List<String> intDatasetLabels) {
        this.intDatasetLabels = intDatasetLabels;
    }

    public TreeDecoration withIntDatasetLabels(List<String> intDatasetLabels) {
        this.intDatasetLabels = intDatasetLabels;
        return this;
    }

    @JsonProperty("int_dataset_viz_types")
    public List<String> getIntDatasetVizTypes() {
        return intDatasetVizTypes;
    }

    @JsonProperty("int_dataset_viz_types")
    public void setIntDatasetVizTypes(List<String> intDatasetVizTypes) {
        this.intDatasetVizTypes = intDatasetVizTypes;
    }

    public TreeDecoration withIntDatasetVizTypes(List<String> intDatasetVizTypes) {
        this.intDatasetVizTypes = intDatasetVizTypes;
        return this;
    }

    @JsonProperty("float_dataset_labels")
    public List<String> getFloatDatasetLabels() {
        return floatDatasetLabels;
    }

    @JsonProperty("float_dataset_labels")
    public void setFloatDatasetLabels(List<String> floatDatasetLabels) {
        this.floatDatasetLabels = floatDatasetLabels;
    }

    public TreeDecoration withFloatDatasetLabels(List<String> floatDatasetLabels) {
        this.floatDatasetLabels = floatDatasetLabels;
        return this;
    }

    @JsonProperty("float_dataset_viz_types")
    public List<String> getFloatDatasetVizTypes() {
        return floatDatasetVizTypes;
    }

    @JsonProperty("float_dataset_viz_types")
    public void setFloatDatasetVizTypes(List<String> floatDatasetVizTypes) {
        this.floatDatasetVizTypes = floatDatasetVizTypes;
    }

    public TreeDecoration withFloatDatasetVizTypes(List<String> floatDatasetVizTypes) {
        this.floatDatasetVizTypes = floatDatasetVizTypes;
        return this;
    }

    @JsonProperty("tree_val_list_string")
    public Map<String, List<Tuple2 <String, String>>> getTreeValListString() {
        return treeValListString;
    }

    @JsonProperty("tree_val_list_string")
    public void setTreeValListString(Map<String, List<Tuple2 <String, String>>> treeValListString) {
        this.treeValListString = treeValListString;
    }

    public TreeDecoration withTreeValListString(Map<String, List<Tuple2 <String, String>>> treeValListString) {
        this.treeValListString = treeValListString;
        return this;
    }

    @JsonProperty("tree_val_list_int")
    public Map<String, List<Tuple2 <Long, String>>> getTreeValListInt() {
        return treeValListInt;
    }

    @JsonProperty("tree_val_list_int")
    public void setTreeValListInt(Map<String, List<Tuple2 <Long, String>>> treeValListInt) {
        this.treeValListInt = treeValListInt;
    }

    public TreeDecoration withTreeValListInt(Map<String, List<Tuple2 <Long, String>>> treeValListInt) {
        this.treeValListInt = treeValListInt;
        return this;
    }

    @JsonProperty("tree_val_list_float")
    public Map<String, List<Tuple2 <Double, String>>> getTreeValListFloat() {
        return treeValListFloat;
    }

    @JsonProperty("tree_val_list_float")
    public void setTreeValListFloat(Map<String, List<Tuple2 <Double, String>>> treeValListFloat) {
        this.treeValListFloat = treeValListFloat;
    }

    public TreeDecoration withTreeValListFloat(Map<String, List<Tuple2 <Double, String>>> treeValListFloat) {
        this.treeValListFloat = treeValListFloat;
        return this;
    }

    @JsonProperty("collapsed_by_node")
    public Map<String, String> getCollapsedByNode() {
        return collapsedByNode;
    }

    @JsonProperty("collapsed_by_node")
    public void setCollapsedByNode(Map<String, String> collapsedByNode) {
        this.collapsedByNode = collapsedByNode;
    }

    public TreeDecoration withCollapsedByNode(Map<String, String> collapsedByNode) {
        this.collapsedByNode = collapsedByNode;
        return this;
    }

    @JsonProperty("substructure_by_node")
    public Map<String, Tuple2 <String, String>> getSubstructureByNode() {
        return substructureByNode;
    }

    @JsonProperty("substructure_by_node")
    public void setSubstructureByNode(Map<String, Tuple2 <String, String>> substructureByNode) {
        this.substructureByNode = substructureByNode;
    }

    public TreeDecoration withSubstructureByNode(Map<String, Tuple2 <String, String>> substructureByNode) {
        this.substructureByNode = substructureByNode;
        return this;
    }

    @JsonProperty("rooted_flag")
    public java.lang.String getRootedFlag() {
        return rootedFlag;
    }

    @JsonProperty("rooted_flag")
    public void setRootedFlag(java.lang.String rootedFlag) {
        this.rootedFlag = rootedFlag;
    }

    public TreeDecoration withRootedFlag(java.lang.String rootedFlag) {
        this.rootedFlag = rootedFlag;
        return this;
    }

    @JsonProperty("alt_root_id")
    public java.lang.String getAltRootId() {
        return altRootId;
    }

    @JsonProperty("alt_root_id")
    public void setAltRootId(java.lang.String altRootId) {
        this.altRootId = altRootId;
    }

    public TreeDecoration withAltRootId(java.lang.String altRootId) {
        this.altRootId = altRootId;
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
        return ((((((((((((((((((((((((((((((((("TreeDecoration"+" [treeId=")+ treeId)+", vizTitle=")+ vizTitle)+", stringDatasetLabels=")+ stringDatasetLabels)+", stringDatasetVizTypes=")+ stringDatasetVizTypes)+", intDatasetLabels=")+ intDatasetLabels)+", intDatasetVizTypes=")+ intDatasetVizTypes)+", floatDatasetLabels=")+ floatDatasetLabels)+", floatDatasetVizTypes=")+ floatDatasetVizTypes)+", treeValListString=")+ treeValListString)+", treeValListInt=")+ treeValListInt)+", treeValListFloat=")+ treeValListFloat)+", collapsedByNode=")+ collapsedByNode)+", substructureByNode=")+ substructureByNode)+", rootedFlag=")+ rootedFlag)+", altRootId=")+ altRootId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
