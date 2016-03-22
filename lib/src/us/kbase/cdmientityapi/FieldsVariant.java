
package us.kbase.cdmientityapi;

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
 * <p>Original spec-file type: fields_Variant</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "role_rule",
    "code",
    "type",
    "comment"
})
public class FieldsVariant {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("role_rule")
    private List<String> roleRule;
    @JsonProperty("code")
    private java.lang.String code;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("comment")
    private java.lang.String comment;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsVariant withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("role_rule")
    public List<String> getRoleRule() {
        return roleRule;
    }

    @JsonProperty("role_rule")
    public void setRoleRule(List<String> roleRule) {
        this.roleRule = roleRule;
    }

    public FieldsVariant withRoleRule(List<String> roleRule) {
        this.roleRule = roleRule;
        return this;
    }

    @JsonProperty("code")
    public java.lang.String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public FieldsVariant withCode(java.lang.String code) {
        this.code = code;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public FieldsVariant withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("comment")
    public java.lang.String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }

    public FieldsVariant withComment(java.lang.String comment) {
        this.comment = comment;
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
        return ((((((((((((("FieldsVariant"+" [id=")+ id)+", roleRule=")+ roleRule)+", code=")+ code)+", type=")+ type)+", comment=")+ comment)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
