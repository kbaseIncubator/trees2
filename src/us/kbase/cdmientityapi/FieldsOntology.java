
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
 * <p>Original spec-file type: fields_Ontology</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "definition",
    "ontologySource"
})
public class FieldsOntology {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("definition")
    private String definition;
    @JsonProperty("ontologySource")
    private String ontologySource;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsOntology withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public FieldsOntology withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("definition")
    public String getDefinition() {
        return definition;
    }

    @JsonProperty("definition")
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public FieldsOntology withDefinition(String definition) {
        this.definition = definition;
        return this;
    }

    @JsonProperty("ontologySource")
    public String getOntologySource() {
        return ontologySource;
    }

    @JsonProperty("ontologySource")
    public void setOntologySource(String ontologySource) {
        this.ontologySource = ontologySource;
    }

    public FieldsOntology withOntologySource(String ontologySource) {
        this.ontologySource = ontologySource;
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
        return ((((((((((("FieldsOntology"+" [id=")+ id)+", name=")+ name)+", definition=")+ definition)+", ontologySource=")+ ontologySource)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
