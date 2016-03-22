
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
 * <p>Original spec-file type: fields_Subsystem</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "version",
    "curator",
    "notes",
    "description",
    "usable",
    "private",
    "cluster_based",
    "experimental"
})
public class FieldsSubsystem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private Long version;
    @JsonProperty("curator")
    private String curator;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("description")
    private String description;
    @JsonProperty("usable")
    private Long usable;
    @JsonProperty("private")
    private Long _private;
    @JsonProperty("cluster_based")
    private Long clusterBased;
    @JsonProperty("experimental")
    private Long experimental;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsSubsystem withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("version")
    public Long getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Long version) {
        this.version = version;
    }

    public FieldsSubsystem withVersion(Long version) {
        this.version = version;
        return this;
    }

    @JsonProperty("curator")
    public String getCurator() {
        return curator;
    }

    @JsonProperty("curator")
    public void setCurator(String curator) {
        this.curator = curator;
    }

    public FieldsSubsystem withCurator(String curator) {
        this.curator = curator;
        return this;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public FieldsSubsystem withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public FieldsSubsystem withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("usable")
    public Long getUsable() {
        return usable;
    }

    @JsonProperty("usable")
    public void setUsable(Long usable) {
        this.usable = usable;
    }

    public FieldsSubsystem withUsable(Long usable) {
        this.usable = usable;
        return this;
    }

    @JsonProperty("private")
    public Long getPrivate() {
        return _private;
    }

    @JsonProperty("private")
    public void setPrivate(Long _private) {
        this._private = _private;
    }

    public FieldsSubsystem withPrivate(Long _private) {
        this._private = _private;
        return this;
    }

    @JsonProperty("cluster_based")
    public Long getClusterBased() {
        return clusterBased;
    }

    @JsonProperty("cluster_based")
    public void setClusterBased(Long clusterBased) {
        this.clusterBased = clusterBased;
    }

    public FieldsSubsystem withClusterBased(Long clusterBased) {
        this.clusterBased = clusterBased;
        return this;
    }

    @JsonProperty("experimental")
    public Long getExperimental() {
        return experimental;
    }

    @JsonProperty("experimental")
    public void setExperimental(Long experimental) {
        this.experimental = experimental;
    }

    public FieldsSubsystem withExperimental(Long experimental) {
        this.experimental = experimental;
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
        return ((((((((((((((((((((("FieldsSubsystem"+" [id=")+ id)+", version=")+ version)+", curator=")+ curator)+", notes=")+ notes)+", description=")+ description)+", usable=")+ usable)+", _private=")+ _private)+", clusterBased=")+ clusterBased)+", experimental=")+ experimental)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
