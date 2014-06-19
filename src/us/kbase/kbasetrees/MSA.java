
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
 * <p>Original spec-file type: MSA</p>
 * <pre>
 * Type for multiple sequence alignment.
 *         int alignment_length - number of columns in alignment.
 *         string alignment_method - name of program used for this alignment construction (optional),
 *                 currently service supports one of: Muscle, Clustal, ProbCons, T-Coffee, Mafft.
 * is_protein_mode - 1 in case sequences are amino acids, 0 in case of nucleotides (optional).
 *         mapping<string, string> alignment - mapping from sequence id to aligned sequence
 *         list<string> sequence_id_order - list of sequence ids defining alignment order (optional). 
 *         @optional alignment_method
 *         @optional is_protein_mode
 *         @optional sequence_id_order
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "alignment_length",
    "alignment_method",
    "is_protein_mode",
    "alignment",
    "sequence_id_order"
})
public class MSA {

    @JsonProperty("alignment_length")
    private Long alignmentLength;
    @JsonProperty("alignment_method")
    private java.lang.String alignmentMethod;
    @JsonProperty("is_protein_mode")
    private Long isProteinMode;
    @JsonProperty("alignment")
    private Map<String, String> alignment;
    @JsonProperty("sequence_id_order")
    private List<String> sequenceIdOrder;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("alignment_length")
    public Long getAlignmentLength() {
        return alignmentLength;
    }

    @JsonProperty("alignment_length")
    public void setAlignmentLength(Long alignmentLength) {
        this.alignmentLength = alignmentLength;
    }

    public MSA withAlignmentLength(Long alignmentLength) {
        this.alignmentLength = alignmentLength;
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

    public MSA withAlignmentMethod(java.lang.String alignmentMethod) {
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

    public MSA withIsProteinMode(Long isProteinMode) {
        this.isProteinMode = isProteinMode;
        return this;
    }

    @JsonProperty("alignment")
    public Map<String, String> getAlignment() {
        return alignment;
    }

    @JsonProperty("alignment")
    public void setAlignment(Map<String, String> alignment) {
        this.alignment = alignment;
    }

    public MSA withAlignment(Map<String, String> alignment) {
        this.alignment = alignment;
        return this;
    }

    @JsonProperty("sequence_id_order")
    public List<String> getSequenceIdOrder() {
        return sequenceIdOrder;
    }

    @JsonProperty("sequence_id_order")
    public void setSequenceIdOrder(List<String> sequenceIdOrder) {
        this.sequenceIdOrder = sequenceIdOrder;
    }

    public MSA withSequenceIdOrder(List<String> sequenceIdOrder) {
        this.sequenceIdOrder = sequenceIdOrder;
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
        return ((((((((((((("MSA"+" [alignmentLength=")+ alignmentLength)+", alignmentMethod=")+ alignmentMethod)+", isProteinMode=")+ isProteinMode)+", alignment=")+ alignment)+", sequenceIdOrder=")+ sequenceIdOrder)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
