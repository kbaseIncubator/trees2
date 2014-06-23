
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
 * <p>Original spec-file type: fields_IsConservedDomainModelFor</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "from_link",
    "to_link",
    "percent_identity",
    "alignment_length",
    "mismatches",
    "gap_openings",
    "protein_start",
    "protein_end",
    "domain_start",
    "domain_end",
    "e_value",
    "bit_score"
})
public class FieldsIsConservedDomainModelFor {

    @JsonProperty("from_link")
    private Long fromLink;
    @JsonProperty("to_link")
    private String toLink;
    @JsonProperty("percent_identity")
    private Double percentIdentity;
    @JsonProperty("alignment_length")
    private Long alignmentLength;
    @JsonProperty("mismatches")
    private Long mismatches;
    @JsonProperty("gap_openings")
    private Long gapOpenings;
    @JsonProperty("protein_start")
    private Long proteinStart;
    @JsonProperty("protein_end")
    private Long proteinEnd;
    @JsonProperty("domain_start")
    private Long domainStart;
    @JsonProperty("domain_end")
    private Long domainEnd;
    @JsonProperty("e_value")
    private Double eValue;
    @JsonProperty("bit_score")
    private Double bitScore;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from_link")
    public Long getFromLink() {
        return fromLink;
    }

    @JsonProperty("from_link")
    public void setFromLink(Long fromLink) {
        this.fromLink = fromLink;
    }

    public FieldsIsConservedDomainModelFor withFromLink(Long fromLink) {
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

    public FieldsIsConservedDomainModelFor withToLink(String toLink) {
        this.toLink = toLink;
        return this;
    }

    @JsonProperty("percent_identity")
    public Double getPercentIdentity() {
        return percentIdentity;
    }

    @JsonProperty("percent_identity")
    public void setPercentIdentity(Double percentIdentity) {
        this.percentIdentity = percentIdentity;
    }

    public FieldsIsConservedDomainModelFor withPercentIdentity(Double percentIdentity) {
        this.percentIdentity = percentIdentity;
        return this;
    }

    @JsonProperty("alignment_length")
    public Long getAlignmentLength() {
        return alignmentLength;
    }

    @JsonProperty("alignment_length")
    public void setAlignmentLength(Long alignmentLength) {
        this.alignmentLength = alignmentLength;
    }

    public FieldsIsConservedDomainModelFor withAlignmentLength(Long alignmentLength) {
        this.alignmentLength = alignmentLength;
        return this;
    }

    @JsonProperty("mismatches")
    public Long getMismatches() {
        return mismatches;
    }

    @JsonProperty("mismatches")
    public void setMismatches(Long mismatches) {
        this.mismatches = mismatches;
    }

    public FieldsIsConservedDomainModelFor withMismatches(Long mismatches) {
        this.mismatches = mismatches;
        return this;
    }

    @JsonProperty("gap_openings")
    public Long getGapOpenings() {
        return gapOpenings;
    }

    @JsonProperty("gap_openings")
    public void setGapOpenings(Long gapOpenings) {
        this.gapOpenings = gapOpenings;
    }

    public FieldsIsConservedDomainModelFor withGapOpenings(Long gapOpenings) {
        this.gapOpenings = gapOpenings;
        return this;
    }

    @JsonProperty("protein_start")
    public Long getProteinStart() {
        return proteinStart;
    }

    @JsonProperty("protein_start")
    public void setProteinStart(Long proteinStart) {
        this.proteinStart = proteinStart;
    }

    public FieldsIsConservedDomainModelFor withProteinStart(Long proteinStart) {
        this.proteinStart = proteinStart;
        return this;
    }

    @JsonProperty("protein_end")
    public Long getProteinEnd() {
        return proteinEnd;
    }

    @JsonProperty("protein_end")
    public void setProteinEnd(Long proteinEnd) {
        this.proteinEnd = proteinEnd;
    }

    public FieldsIsConservedDomainModelFor withProteinEnd(Long proteinEnd) {
        this.proteinEnd = proteinEnd;
        return this;
    }

    @JsonProperty("domain_start")
    public Long getDomainStart() {
        return domainStart;
    }

    @JsonProperty("domain_start")
    public void setDomainStart(Long domainStart) {
        this.domainStart = domainStart;
    }

    public FieldsIsConservedDomainModelFor withDomainStart(Long domainStart) {
        this.domainStart = domainStart;
        return this;
    }

    @JsonProperty("domain_end")
    public Long getDomainEnd() {
        return domainEnd;
    }

    @JsonProperty("domain_end")
    public void setDomainEnd(Long domainEnd) {
        this.domainEnd = domainEnd;
    }

    public FieldsIsConservedDomainModelFor withDomainEnd(Long domainEnd) {
        this.domainEnd = domainEnd;
        return this;
    }

    @JsonProperty("e_value")
    public Double getEValue() {
        return eValue;
    }

    @JsonProperty("e_value")
    public void setEValue(Double eValue) {
        this.eValue = eValue;
    }

    public FieldsIsConservedDomainModelFor withEValue(Double eValue) {
        this.eValue = eValue;
        return this;
    }

    @JsonProperty("bit_score")
    public Double getBitScore() {
        return bitScore;
    }

    @JsonProperty("bit_score")
    public void setBitScore(Double bitScore) {
        this.bitScore = bitScore;
    }

    public FieldsIsConservedDomainModelFor withBitScore(Double bitScore) {
        this.bitScore = bitScore;
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
        return ((((((((((((((((((((((((((("FieldsIsConservedDomainModelFor"+" [fromLink=")+ fromLink)+", toLink=")+ toLink)+", percentIdentity=")+ percentIdentity)+", alignmentLength=")+ alignmentLength)+", mismatches=")+ mismatches)+", gapOpenings=")+ gapOpenings)+", proteinStart=")+ proteinStart)+", proteinEnd=")+ proteinEnd)+", domainStart=")+ domainStart)+", domainEnd=")+ domainEnd)+", eValue=")+ eValue)+", bitScore=")+ bitScore)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
