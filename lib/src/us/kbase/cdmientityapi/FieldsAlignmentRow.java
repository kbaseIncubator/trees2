
package us.kbase.cdmientityapi;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: fields_AlignmentRow</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "row_number",
    "row_id",
    "row_description",
    "n_components",
    "beg_pos_aln",
    "end_pos_aln",
    "md5_of_ungapped_sequence",
    "sequence"
})
public class FieldsAlignmentRow {

    @JsonProperty("id")
    private String id;
    @JsonProperty("row_number")
    private Long rowNumber;
    @JsonProperty("row_id")
    private String rowId;
    @JsonProperty("row_description")
    private String rowDescription;
    @JsonProperty("n_components")
    private Long nComponents;
    @JsonProperty("beg_pos_aln")
    private Long begPosAln;
    @JsonProperty("end_pos_aln")
    private Long endPosAln;
    @JsonProperty("md5_of_ungapped_sequence")
    private String md5OfUngappedSequence;
    @JsonProperty("sequence")
    private String sequence;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsAlignmentRow withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("row_number")
    public Long getRowNumber() {
        return rowNumber;
    }

    @JsonProperty("row_number")
    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public FieldsAlignmentRow withRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    @JsonProperty("row_id")
    public String getRowId() {
        return rowId;
    }

    @JsonProperty("row_id")
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public FieldsAlignmentRow withRowId(String rowId) {
        this.rowId = rowId;
        return this;
    }

    @JsonProperty("row_description")
    public String getRowDescription() {
        return rowDescription;
    }

    @JsonProperty("row_description")
    public void setRowDescription(String rowDescription) {
        this.rowDescription = rowDescription;
    }

    public FieldsAlignmentRow withRowDescription(String rowDescription) {
        this.rowDescription = rowDescription;
        return this;
    }

    @JsonProperty("n_components")
    public Long getNComponents() {
        return nComponents;
    }

    @JsonProperty("n_components")
    public void setNComponents(Long nComponents) {
        this.nComponents = nComponents;
    }

    public FieldsAlignmentRow withNComponents(Long nComponents) {
        this.nComponents = nComponents;
        return this;
    }

    @JsonProperty("beg_pos_aln")
    public Long getBegPosAln() {
        return begPosAln;
    }

    @JsonProperty("beg_pos_aln")
    public void setBegPosAln(Long begPosAln) {
        this.begPosAln = begPosAln;
    }

    public FieldsAlignmentRow withBegPosAln(Long begPosAln) {
        this.begPosAln = begPosAln;
        return this;
    }

    @JsonProperty("end_pos_aln")
    public Long getEndPosAln() {
        return endPosAln;
    }

    @JsonProperty("end_pos_aln")
    public void setEndPosAln(Long endPosAln) {
        this.endPosAln = endPosAln;
    }

    public FieldsAlignmentRow withEndPosAln(Long endPosAln) {
        this.endPosAln = endPosAln;
        return this;
    }

    @JsonProperty("md5_of_ungapped_sequence")
    public String getMd5OfUngappedSequence() {
        return md5OfUngappedSequence;
    }

    @JsonProperty("md5_of_ungapped_sequence")
    public void setMd5OfUngappedSequence(String md5OfUngappedSequence) {
        this.md5OfUngappedSequence = md5OfUngappedSequence;
    }

    public FieldsAlignmentRow withMd5OfUngappedSequence(String md5OfUngappedSequence) {
        this.md5OfUngappedSequence = md5OfUngappedSequence;
        return this;
    }

    @JsonProperty("sequence")
    public String getSequence() {
        return sequence;
    }

    @JsonProperty("sequence")
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public FieldsAlignmentRow withSequence(String sequence) {
        this.sequence = sequence;
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
        return ((((((((((((((((((((("FieldsAlignmentRow"+" [id=")+ id)+", rowNumber=")+ rowNumber)+", rowId=")+ rowId)+", rowDescription=")+ rowDescription)+", nComponents=")+ nComponents)+", begPosAln=")+ begPosAln)+", endPosAln=")+ endPosAln)+", md5OfUngappedSequence=")+ md5OfUngappedSequence)+", sequence=")+ sequence)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
