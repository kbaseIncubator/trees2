
package us.kbase.tree;

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
 * <p>Original spec-file type: AlignmentMetaData</p>
 * <pre>
 * Meta data associated with an alignment.
 *     list<kbase_id> tree_ids - the set of trees that were built from this alignment
 *     string status - set to 'active' if this is the latest alignment for a particular set of sequences
 *     string sequence_type - indicates what type of sequence is aligned (e.g. protein vs. dna)
 *     boolean is_concatenation - true if the alignment is based on the concatenation of multiple non-contiguous
 *                             sequences, false if each row cooresponds to exactly one sequence (possibly with gaps)
 *     timestamp date_created - time at which the alignment was built/loaded in seconds since the epoch
 *     int n_rows - number of rows in the alignment
 *     int n_cols - number of columns in the alignment
 *     string alignment_construction_method - the name of the software tool used to build the alignment
 *     string alignment_construction_parameters - set of non-default parameters used to construct the alignment
 *     string alignment_protocol - simple free-form text which may provide additional details of how the alignment was built
 *     string source_db - the source database where this alignment originated, if one exists
 *     string source_id - the id of this alignment in an external database, if one exists
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "tree_ids",
    "status",
    "sequence_type",
    "is_concatenation",
    "date_created",
    "n_rows",
    "n_cols",
    "alignment_construction_method",
    "alignment_construction_parameters",
    "alignment_protocol",
    "source_db",
    "source_id"
})
public class AlignmentMetaData {

    @JsonProperty("tree_ids")
    private List<String> treeIds;
    @JsonProperty("status")
    private java.lang.String status;
    @JsonProperty("sequence_type")
    private java.lang.String sequenceType;
    @JsonProperty("is_concatenation")
    private java.lang.String isConcatenation;
    @JsonProperty("date_created")
    private java.lang.String dateCreated;
    @JsonProperty("n_rows")
    private Long nRows;
    @JsonProperty("n_cols")
    private Long nCols;
    @JsonProperty("alignment_construction_method")
    private java.lang.String alignmentConstructionMethod;
    @JsonProperty("alignment_construction_parameters")
    private java.lang.String alignmentConstructionParameters;
    @JsonProperty("alignment_protocol")
    private java.lang.String alignmentProtocol;
    @JsonProperty("source_db")
    private java.lang.String sourceDb;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("tree_ids")
    public List<String> getTreeIds() {
        return treeIds;
    }

    @JsonProperty("tree_ids")
    public void setTreeIds(List<String> treeIds) {
        this.treeIds = treeIds;
    }

    public AlignmentMetaData withTreeIds(List<String> treeIds) {
        this.treeIds = treeIds;
        return this;
    }

    @JsonProperty("status")
    public java.lang.String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public AlignmentMetaData withStatus(java.lang.String status) {
        this.status = status;
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

    public AlignmentMetaData withSequenceType(java.lang.String sequenceType) {
        this.sequenceType = sequenceType;
        return this;
    }

    @JsonProperty("is_concatenation")
    public java.lang.String getIsConcatenation() {
        return isConcatenation;
    }

    @JsonProperty("is_concatenation")
    public void setIsConcatenation(java.lang.String isConcatenation) {
        this.isConcatenation = isConcatenation;
    }

    public AlignmentMetaData withIsConcatenation(java.lang.String isConcatenation) {
        this.isConcatenation = isConcatenation;
        return this;
    }

    @JsonProperty("date_created")
    public java.lang.String getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("date_created")
    public void setDateCreated(java.lang.String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public AlignmentMetaData withDateCreated(java.lang.String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @JsonProperty("n_rows")
    public Long getNRows() {
        return nRows;
    }

    @JsonProperty("n_rows")
    public void setNRows(Long nRows) {
        this.nRows = nRows;
    }

    public AlignmentMetaData withNRows(Long nRows) {
        this.nRows = nRows;
        return this;
    }

    @JsonProperty("n_cols")
    public Long getNCols() {
        return nCols;
    }

    @JsonProperty("n_cols")
    public void setNCols(Long nCols) {
        this.nCols = nCols;
    }

    public AlignmentMetaData withNCols(Long nCols) {
        this.nCols = nCols;
        return this;
    }

    @JsonProperty("alignment_construction_method")
    public java.lang.String getAlignmentConstructionMethod() {
        return alignmentConstructionMethod;
    }

    @JsonProperty("alignment_construction_method")
    public void setAlignmentConstructionMethod(java.lang.String alignmentConstructionMethod) {
        this.alignmentConstructionMethod = alignmentConstructionMethod;
    }

    public AlignmentMetaData withAlignmentConstructionMethod(java.lang.String alignmentConstructionMethod) {
        this.alignmentConstructionMethod = alignmentConstructionMethod;
        return this;
    }

    @JsonProperty("alignment_construction_parameters")
    public java.lang.String getAlignmentConstructionParameters() {
        return alignmentConstructionParameters;
    }

    @JsonProperty("alignment_construction_parameters")
    public void setAlignmentConstructionParameters(java.lang.String alignmentConstructionParameters) {
        this.alignmentConstructionParameters = alignmentConstructionParameters;
    }

    public AlignmentMetaData withAlignmentConstructionParameters(java.lang.String alignmentConstructionParameters) {
        this.alignmentConstructionParameters = alignmentConstructionParameters;
        return this;
    }

    @JsonProperty("alignment_protocol")
    public java.lang.String getAlignmentProtocol() {
        return alignmentProtocol;
    }

    @JsonProperty("alignment_protocol")
    public void setAlignmentProtocol(java.lang.String alignmentProtocol) {
        this.alignmentProtocol = alignmentProtocol;
    }

    public AlignmentMetaData withAlignmentProtocol(java.lang.String alignmentProtocol) {
        this.alignmentProtocol = alignmentProtocol;
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

    public AlignmentMetaData withSourceDb(java.lang.String sourceDb) {
        this.sourceDb = sourceDb;
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

    public AlignmentMetaData withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
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
        return ((((((((((((((((((((((((((("AlignmentMetaData"+" [treeIds=")+ treeIds)+", status=")+ status)+", sequenceType=")+ sequenceType)+", isConcatenation=")+ isConcatenation)+", dateCreated=")+ dateCreated)+", nRows=")+ nRows)+", nCols=")+ nCols)+", alignmentConstructionMethod=")+ alignmentConstructionMethod)+", alignmentConstructionParameters=")+ alignmentConstructionParameters)+", alignmentProtocol=")+ alignmentProtocol)+", sourceDb=")+ sourceDb)+", sourceId=")+ sourceId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
