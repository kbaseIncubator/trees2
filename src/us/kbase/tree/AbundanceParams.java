
package us.kbase.tree;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: AbundanceParams</p>
 * <pre>
 * Structure to group input parameters to the compute_abundance_profile method.
 *     kbase_id tree_id                - the KBase ID of the tree to compute abundances for; the tree is
 *                                       used to identify the set of sequences that were aligned to build
 *                                       the tree; each leaf node of a tree built from an alignment will
 *                                       be mapped to a sequence; the compute_abundance_profile method
 *                                       assumes that trees are built from protein sequences
 *     string protein_family_name      - the name of the protein family used to pull a small set of reads
 *                                       from a metagenomic sample; currently only COG families are supported
 *     string protein_family_source    - the name of the source of the protein family; currently supported
 *                                       protein families are: 'COG'
 *     string metagenomic_sample_id    - the ID of the metagenomic sample to lookup; see the KBase communities
 *                                       service to identifiy metagenomic samples
 *     int percent_identity_threshold  - the minimum acceptable percent identity for hits, provided as a percentage
 *                                       and not a fraction (i.e. set to 87.5 for 87.5%)
 *     int match_length_threshold      - the minimum acceptable length of a match to consider a hit
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "tree_id",
    "protein_family_name",
    "protein_family_source",
    "metagenomic_sample_id",
    "percent_identity_threshold",
    "match_length_threshold",
    "mg_auth_key"
})
public class AbundanceParams {

    @JsonProperty("tree_id")
    private String treeId;
    @JsonProperty("protein_family_name")
    private String proteinFamilyName;
    @JsonProperty("protein_family_source")
    private String proteinFamilySource;
    @JsonProperty("metagenomic_sample_id")
    private String metagenomicSampleId;
    @JsonProperty("percent_identity_threshold")
    private Long percentIdentityThreshold;
    @JsonProperty("match_length_threshold")
    private Long matchLengthThreshold;
    @JsonProperty("mg_auth_key")
    private String mgAuthKey;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tree_id")
    public String getTreeId() {
        return treeId;
    }

    @JsonProperty("tree_id")
    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public AbundanceParams withTreeId(String treeId) {
        this.treeId = treeId;
        return this;
    }

    @JsonProperty("protein_family_name")
    public String getProteinFamilyName() {
        return proteinFamilyName;
    }

    @JsonProperty("protein_family_name")
    public void setProteinFamilyName(String proteinFamilyName) {
        this.proteinFamilyName = proteinFamilyName;
    }

    public AbundanceParams withProteinFamilyName(String proteinFamilyName) {
        this.proteinFamilyName = proteinFamilyName;
        return this;
    }

    @JsonProperty("protein_family_source")
    public String getProteinFamilySource() {
        return proteinFamilySource;
    }

    @JsonProperty("protein_family_source")
    public void setProteinFamilySource(String proteinFamilySource) {
        this.proteinFamilySource = proteinFamilySource;
    }

    public AbundanceParams withProteinFamilySource(String proteinFamilySource) {
        this.proteinFamilySource = proteinFamilySource;
        return this;
    }

    @JsonProperty("metagenomic_sample_id")
    public String getMetagenomicSampleId() {
        return metagenomicSampleId;
    }

    @JsonProperty("metagenomic_sample_id")
    public void setMetagenomicSampleId(String metagenomicSampleId) {
        this.metagenomicSampleId = metagenomicSampleId;
    }

    public AbundanceParams withMetagenomicSampleId(String metagenomicSampleId) {
        this.metagenomicSampleId = metagenomicSampleId;
        return this;
    }

    @JsonProperty("percent_identity_threshold")
    public Long getPercentIdentityThreshold() {
        return percentIdentityThreshold;
    }

    @JsonProperty("percent_identity_threshold")
    public void setPercentIdentityThreshold(Long percentIdentityThreshold) {
        this.percentIdentityThreshold = percentIdentityThreshold;
    }

    public AbundanceParams withPercentIdentityThreshold(Long percentIdentityThreshold) {
        this.percentIdentityThreshold = percentIdentityThreshold;
        return this;
    }

    @JsonProperty("match_length_threshold")
    public Long getMatchLengthThreshold() {
        return matchLengthThreshold;
    }

    @JsonProperty("match_length_threshold")
    public void setMatchLengthThreshold(Long matchLengthThreshold) {
        this.matchLengthThreshold = matchLengthThreshold;
    }

    public AbundanceParams withMatchLengthThreshold(Long matchLengthThreshold) {
        this.matchLengthThreshold = matchLengthThreshold;
        return this;
    }

    @JsonProperty("mg_auth_key")
    public String getMgAuthKey() {
        return mgAuthKey;
    }

    @JsonProperty("mg_auth_key")
    public void setMgAuthKey(String mgAuthKey) {
        this.mgAuthKey = mgAuthKey;
    }

    public AbundanceParams withMgAuthKey(String mgAuthKey) {
        this.mgAuthKey = mgAuthKey;
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
        return ((((((((((((((((("AbundanceParams"+" [treeId=")+ treeId)+", proteinFamilyName=")+ proteinFamilyName)+", proteinFamilySource=")+ proteinFamilySource)+", metagenomicSampleId=")+ metagenomicSampleId)+", percentIdentityThreshold=")+ percentIdentityThreshold)+", matchLengthThreshold=")+ matchLengthThreshold)+", mgAuthKey=")+ mgAuthKey)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
