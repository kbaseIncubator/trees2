
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
 * <p>Original spec-file type: fields_ContainsAlignedDNA</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "index_in_concatenation",
    "beg_pos_in_parent",
    "end_pos_in_parent",
    "parent_seq_len",
    "beg_pos_aln",
    "end_pos_aln",
    "kb_feature_id"
})
public class FieldsContainsAlignedDNA {

    @JsonProperty("from_link")
    private String fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("index_in_concatenation")
    private Long indexInConcatenation;
    @JsonProperty("beg_pos_in_parent")
    private Long begPosInParent;
    @JsonProperty("end_pos_in_parent")
    private Long endPosInParent;
    @JsonProperty("parent_seq_len")
    private Long parentSeqLen;
    @JsonProperty("beg_pos_aln")
    private Long begPosAln;
    @JsonProperty("end_pos_aln")
    private Long endPosAln;
    @JsonProperty("kb_feature_id")
    private String kbFeatureId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public String getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(String fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsContainsAlignedDNA withFromLink(String fromLink) {
        this.fromLink = fromLink;
        return this;
    }

    @JsonProperty("to_link")
    public String getToLink() {
        return toLink;
    }

    @JsonProperty("to_link")
    public void setToLink(String toLink) {
        this.toLink = toLink;
    }

    public FieldsContainsAlignedDNA withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("index_in_concatenation")
    public Long getIndexInConcatenation() {
        return indexInConcatenation;
    }

    @JsonProperty("index_in_concatenation")
    public void setIndexInConcatenation(Long indexInConcatenation) {
        this.indexInConcatenation = indexInConcatenation;
    }

    public FieldsContainsAlignedDNA withIndexInConcatenation(Long indexInConcatenation) {
        this.indexInConcatenation = indexInConcatenation;
        return this;
    }

    @JsonProperty("beg_pos_in_parent")
    public Long getBegPosInParent() {
        return begPosInParent;
    }

    @JsonProperty("beg_pos_in_parent")
    public void setBegPosInParent(Long begPosInParent) {
        this.begPosInParent = begPosInParent;
    }

    public FieldsContainsAlignedDNA withBegPosInParent(Long begPosInParent) {
        this.begPosInParent = begPosInParent;
        return this;
    }

    @JsonProperty("end_pos_in_parent")
    public Long getEndPosInParent() {
        return endPosInParent;
    }

    @JsonProperty("end_pos_in_parent")
    public void setEndPosInParent(Long endPosInParent) {
        this.endPosInParent = endPosInParent;
    }

    public FieldsContainsAlignedDNA withEndPosInParent(Long endPosInParent) {
        this.endPosInParent = endPosInParent;
        return this;
    }

    @JsonProperty("parent_seq_len")
    public Long getParentSeqLen() {
        return parentSeqLen;
    }

    @JsonProperty("parent_seq_len")
    public void setParentSeqLen(Long parentSeqLen) {
        this.parentSeqLen = parentSeqLen;
    }

    public FieldsContainsAlignedDNA withParentSeqLen(Long parentSeqLen) {
        this.parentSeqLen = parentSeqLen;
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

    public FieldsContainsAlignedDNA withBegPosAln(Long begPosAln) {
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

    public FieldsContainsAlignedDNA withEndPosAln(Long endPosAln) {
        this.endPosAln = endPosAln;
        return this;
    }

    @JsonProperty("kb_feature_id")
    public String getKbFeatureId() {
        return kbFeatureId;
    }

    @JsonProperty("kb_feature_id")
    public void setKbFeatureId(String kbFeatureId) {
        this.kbFeatureId = kbFeatureId;
    }

    public FieldsContainsAlignedDNA withKbFeatureId(String kbFeatureId) {
        this.kbFeatureId = kbFeatureId;
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
        return ((((((((((((((((((((("FieldsContainsAlignedDNA"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", indexInConcatenation=")+ indexInConcatenation)+", begPosInParent=")+ begPosInParent)+", endPosInParent=")+ endPosInParent)+", parentSeqLen=")+ parentSeqLen)+", begPosAln=")+ begPosAln)+", endPosAln=")+ endPosAln)+", kbFeatureId=")+ kbFeatureId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
