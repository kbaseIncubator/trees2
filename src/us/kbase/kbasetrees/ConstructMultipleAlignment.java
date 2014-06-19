
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
 * <p>Original spec-file type: ConstructMultipleAlignment</p>
 * <pre>
 * Input data type for construct_species_tree method.
 *         gene_sequences - (required) the mapping from gene ids to their sequences
 *         alignment_method - (required) alignment program, one of: Muscle, Clustal, ProbCons, T-Coffee, 
 *                 Mafft.
 *         is_protein_mode - (optional) 1 in case sequences are amino acids, 0 in case of nucleotides 
 *                 (default value is 1).
 *         out_workspace - (required) the workspace to deposit the completed alignment
 *         out_msa_id - (optional) the name of the newly constructed msa (will be random if not present 
 *                 or null)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "gene_sequences",
    "alignment_method",
    "is_protein_mode",
    "out_workspace",
    "out_msa_id"
})
public class ConstructMultipleAlignment {

    @JsonProperty("gene_sequences")
    private Map<String, String> geneSequences;
    @JsonProperty("alignment_method")
    private java.lang.String alignmentMethod;
    @JsonProperty("is_protein_mode")
    private Long isProteinMode;
    @JsonProperty("out_workspace")
    private java.lang.String outWorkspace;
    @JsonProperty("out_msa_id")
    private java.lang.String outMsaId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("gene_sequences")
    public Map<String, String> getGeneSequences() {
        return geneSequences;
    }

    @JsonProperty("gene_sequences")
    public void setGeneSequences(Map<String, String> geneSequences) {
        this.geneSequences = geneSequences;
    }

    public ConstructMultipleAlignment withGeneSequences(Map<String, String> geneSequences) {
        this.geneSequences = geneSequences;
        return this;
    }

    @JsonProperty("alignment_method")
    public java.lang.String getAlignmentMethod() {
        return alignmentMethod;
    }

    @JsonProperty("alignment_method")
    public void setAlignmentMethod(java.lang.String alignmentMethod) {
        this.alignmentMethod = alignmentMethod;
    }

    public ConstructMultipleAlignment withAlignmentMethod(java.lang.String alignmentMethod) {
        this.alignmentMethod = alignmentMethod;
        return this;
    }

    @JsonProperty("is_protein_mode")
    public Long getIsProteinMode() {
        return isProteinMode;
    }

    @JsonProperty("is_protein_mode")
    public void setIsProteinMode(Long isProteinMode) {
        this.isProteinMode = isProteinMode;
    }

    public ConstructMultipleAlignment withIsProteinMode(Long isProteinMode) {
        this.isProteinMode = isProteinMode;
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

    public ConstructMultipleAlignment withOutWorkspace(java.lang.String outWorkspace) {
        this.outWorkspace = outWorkspace;
        return this;
    }

    @JsonProperty("out_msa_id")
    public java.lang.String getOutMsaId() {
        return outMsaId;
    }

    @JsonProperty("out_msa_id")
    public void setOutMsaId(java.lang.String outMsaId) {
        this.outMsaId = outMsaId;
    }

    public ConstructMultipleAlignment withOutMsaId(java.lang.String outMsaId) {
        this.outMsaId = outMsaId;
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
        return ((((((((((((("ConstructMultipleAlignment"+" [geneSequences=")+ geneSequences)+", alignmentMethod=")+ alignmentMethod)+", isProteinMode=")+ isProteinMode)+", outWorkspace=")+ outWorkspace)+", outMsaId=")+ outMsaId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
