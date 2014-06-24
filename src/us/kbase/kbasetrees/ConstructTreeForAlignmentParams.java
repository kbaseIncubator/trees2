
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
 * <p>Original spec-file type: ConstructTreeForAlignmentParams</p>
 * <pre>
 * Input data type for construct_tree_for_alignment method. Method produces object of Tree type.
 * msa_ref - (required) reference to MSA input object.
 *         tree_method - (optional) tree construction program, one of 'Clustal' (Neighbor-joining approach) or 
 * 'FastTree' (where Maximum likelihood is used), (default is 'Clustal').
 *         min_nongap_percentage_for_trim - (optional) minimum percentage of non-gapped positions in alignment column,
 * if you define this parameter in 50, then columns having less than half non-gapped letters are trimmed
 * (default value is 0 - it means no trimming at all). 
 *         out_workspace - (required) the workspace to deposit the completed tree
 *         out_tree_id - (optional) the name of the newly constructed tree (will be random if not present or null)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "msa_ref",
    "tree_method",
    "min_nongap_percentage_for_trim",
    "out_workspace",
    "out_tree_id"
})
public class ConstructTreeForAlignmentParams {

    @JsonProperty("msa_ref")
    private String msaRef;
    @JsonProperty("tree_method")
    private String treeMethod;
    @JsonProperty("min_nongap_percentage_for_trim")
    private Long minNongapPercentageForTrim;
    @JsonProperty("out_workspace")
    private String outWorkspace;
    @JsonProperty("out_tree_id")
    private String outTreeId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("msa_ref")
    public String getMsaRef() {
        return msaRef;
    }

    @JsonProperty("msa_ref")
    public void setMsaRef(String msaRef) {
        this.msaRef = msaRef;
    }

    public ConstructTreeForAlignmentParams withMsaRef(String msaRef) {
        this.msaRef = msaRef;
        return this;
    }

    @JsonProperty("tree_method")
    public String getTreeMethod() {
        return treeMethod;
    }

    @JsonProperty("tree_method")
    public void setTreeMethod(String treeMethod) {
        this.treeMethod = treeMethod;
    }

    public ConstructTreeForAlignmentParams withTreeMethod(String treeMethod) {
        this.treeMethod = treeMethod;
        return this;
    }

    @JsonProperty("min_nongap_percentage_for_trim")
    public Long getMinNongapPercentageForTrim() {
        return minNongapPercentageForTrim;
    }

    @JsonProperty("min_nongap_percentage_for_trim")
    public void setMinNongapPercentageForTrim(Long minNongapPercentageForTrim) {
        this.minNongapPercentageForTrim = minNongapPercentageForTrim;
    }

    public ConstructTreeForAlignmentParams withMinNongapPercentageForTrim(Long minNongapPercentageForTrim) {
        this.minNongapPercentageForTrim = minNongapPercentageForTrim;
        return this;
    }

    @JsonProperty("out_workspace")
    public String getOutWorkspace() {
        return outWorkspace;
    }

    @JsonProperty("out_workspace")
    public void setOutWorkspace(String outWorkspace) {
        this.outWorkspace = outWorkspace;
    }

    public ConstructTreeForAlignmentParams withOutWorkspace(String outWorkspace) {
        this.outWorkspace = outWorkspace;
        return this;
    }

    @JsonProperty("out_tree_id")
    public String getOutTreeId() {
        return outTreeId;
    }

    @JsonProperty("out_tree_id")
    public void setOutTreeId(String outTreeId) {
        this.outTreeId = outTreeId;
    }

    public ConstructTreeForAlignmentParams withOutTreeId(String outTreeId) {
        this.outTreeId = outTreeId;
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
        return ((((((((((((("ConstructTreeForAlignmentParams"+" [msaRef=")+ msaRef)+", treeMethod=")+ treeMethod)+", minNongapPercentageForTrim=")+ minNongapPercentageForTrim)+", outWorkspace=")+ outWorkspace)+", outTreeId=")+ outTreeId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
