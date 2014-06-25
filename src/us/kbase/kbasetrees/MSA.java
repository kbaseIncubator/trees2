
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
import us.kbase.common.service.Tuple4;


/**
 * <p>Original spec-file type: MSA</p>
 * <pre>
 * Type for multiple sequence alignment.
 * int alignment_length - number of columns in alignment.
 * string alignment_method - name of program used for this alignment construction (optional),
 *         currently service supports one of: Muscle, Clustal, ProbCons, T-Coffee, Mafft.
 *         is_protein_mode - 1 in case sequences are amino acids, 0 in case of nucleotides (optional).
 * mapping<string, string> alignment - mapping from sequence id to aligned sequence
 * list<string> sequence_id_order - list of sequence ids defining alignment order (optional). 
 * @optional name description sequence_type
 * @optional trim_info alignment_attributes row_order
 * @optional source_id source_db
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "description",
    "sequence_type",
    "alignment_length",
    "alignment",
    "trim_info",
    "alignment_attributes",
    "row_order",
    "source_id",
    "source_db"
})
public class MSA {

    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("sequence_type")
    private java.lang.String sequenceType;
    @JsonProperty("alignment_length")
    private java.lang.Long alignmentLength;
    @JsonProperty("alignment")
    private Map<String, String> alignment;
    @JsonProperty("trim_info")
    private Map<String, Tuple4 <Long, Long, Long, String>> trimInfo;
    @JsonProperty("alignment_attributes")
    private Map<String, String> alignmentAttributes;
    @JsonProperty("row_order")
    private List<String> rowOrder;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("source_db")
    private java.lang.String sourceDb;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public MSA withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public java.lang.String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public MSA withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("sequence_type")
    public java.lang.String getSequenceType() {
        return sequenceType;
    }

    @JsonProperty("sequence_type")
    public void setSequenceType(java.lang.String sequenceType) {
        this.sequenceType = sequenceType;
    }

    public MSA withSequenceType(java.lang.String sequenceType) {
        this.sequenceType = sequenceType;
        return this;
    }

    @JsonProperty("alignment_length")
    public java.lang.Long getAlignmentLength() {
        return alignmentLength;
    }

    @JsonProperty("alignment_length")
    public void setAlignmentLength(java.lang.Long alignmentLength) {
        this.alignmentLength = alignmentLength;
    }

    public MSA withAlignmentLength(java.lang.Long alignmentLength) {
        this.alignmentLength = alignmentLength;
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

    @JsonProperty("trim_info")
    public Map<String, Tuple4 <Long, Long, Long, String>> getTrimInfo() {
        return trimInfo;
    }

    @JsonProperty("trim_info")
    public void setTrimInfo(Map<String, Tuple4 <Long, Long, Long, String>> trimInfo) {
        this.trimInfo = trimInfo;
    }

    public MSA withTrimInfo(Map<String, Tuple4 <Long, Long, Long, String>> trimInfo) {
        this.trimInfo = trimInfo;
        return this;
    }

    @JsonProperty("alignment_attributes")
    public Map<String, String> getAlignmentAttributes() {
        return alignmentAttributes;
    }

    @JsonProperty("alignment_attributes")
    public void setAlignmentAttributes(Map<String, String> alignmentAttributes) {
        this.alignmentAttributes = alignmentAttributes;
    }

    public MSA withAlignmentAttributes(Map<String, String> alignmentAttributes) {
        this.alignmentAttributes = alignmentAttributes;
        return this;
    }

    @JsonProperty("row_order")
    public List<String> getRowOrder() {
        return rowOrder;
    }

    @JsonProperty("row_order")
    public void setRowOrder(List<String> rowOrder) {
        this.rowOrder = rowOrder;
    }

    public MSA withRowOrder(List<String> rowOrder) {
        this.rowOrder = rowOrder;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public MSA withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("source_db")
    public java.lang.String getSourceDb() {
        return sourceDb;
    }

    @JsonProperty("source_db")
    public void setSourceDb(java.lang.String sourceDb) {
        this.sourceDb = sourceDb;
    }

    public MSA withSourceDb(java.lang.String sourceDb) {
        this.sourceDb = sourceDb;
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
        return ((((((((((((((((((((((("MSA"+" [name=")+ name)+", description=")+ description)+", sequenceType=")+ sequenceType)+", alignmentLength=")+ alignmentLength)+", alignment=")+ alignment)+", trimInfo=")+ trimInfo)+", alignmentAttributes=")+ alignmentAttributes)+", rowOrder=")+ rowOrder)+", sourceId=")+ sourceId)+", sourceDb=")+ sourceDb)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
