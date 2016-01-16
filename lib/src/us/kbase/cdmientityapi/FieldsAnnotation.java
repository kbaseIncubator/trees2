
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
 * <p>Original spec-file type: fields_Annotation</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "annotator",
    "comment",
    "annotation_time"
})
public class FieldsAnnotation {

    @JsonProperty("id")
    private String id;
    @JsonProperty("annotator")
    private String annotator;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("annotation_time")
    private String annotationTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsAnnotation withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("annotator")
    public String getAnnotator() {
        return annotator;
    }

    @JsonProperty("annotator")
    public void setAnnotator(String annotator) {
        this.annotator = annotator;
    }

    public FieldsAnnotation withAnnotator(String annotator) {
        this.annotator = annotator;
        return this;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    public FieldsAnnotation withComment(String comment) {
        this.comment = comment;
        return this;
    }

    @JsonProperty("annotation_time")
    public String getAnnotationTime() {
        return annotationTime;
    }

    @JsonProperty("annotation_time")
    public void setAnnotationTime(String annotationTime) {
        this.annotationTime = annotationTime;
    }

    public FieldsAnnotation withAnnotationTime(String annotationTime) {
        this.annotationTime = annotationTime;
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
        return ((((((((((("FieldsAnnotation"+" [id=")+ id)+", annotator=")+ annotator)+", comment=")+ comment)+", annotationTime=")+ annotationTime)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
