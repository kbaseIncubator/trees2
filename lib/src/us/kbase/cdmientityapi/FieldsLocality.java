
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
 * <p>Original spec-file type: fields_Locality</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_name",
    "city",
    "state",
    "country",
    "origcty",
    "elevation",
    "latitude",
    "longitude",
    "lo_accession"
})
public class FieldsLocality {

    @JsonProperty("id")
    private String id;
    @JsonProperty("source_name")
    private String sourceName;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("origcty")
    private String origcty;
    @JsonProperty("elevation")
    private Long elevation;
    @JsonProperty("latitude")
    private Long latitude;
    @JsonProperty("longitude")
    private Long longitude;
    @JsonProperty("lo_accession")
    private String loAccession;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public FieldsLocality withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("source_name")
    public String getSourceName() {
        return sourceName;
    }

    @JsonProperty("source_name")
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public FieldsLocality withSourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public FieldsLocality withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    public FieldsLocality withState(String state) {
        this.state = state;
        return this;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    public FieldsLocality withCountry(String country) {
        this.country = country;
        return this;
    }

    @JsonProperty("origcty")
    public String getOrigcty() {
        return origcty;
    }

    @JsonProperty("origcty")
    public void setOrigcty(String origcty) {
        this.origcty = origcty;
    }

    public FieldsLocality withOrigcty(String origcty) {
        this.origcty = origcty;
        return this;
    }

    @JsonProperty("elevation")
    public Long getElevation() {
        return elevation;
    }

    @JsonProperty("elevation")
    public void setElevation(Long elevation) {
        this.elevation = elevation;
    }

    public FieldsLocality withElevation(Long elevation) {
        this.elevation = elevation;
        return this;
    }

    @JsonProperty("latitude")
    public Long getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public FieldsLocality withLatitude(Long latitude) {
        this.latitude = latitude;
        return this;
    }

    @JsonProperty("longitude")
    public Long getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public FieldsLocality withLongitude(Long longitude) {
        this.longitude = longitude;
        return this;
    }

    @JsonProperty("lo_accession")
    public String getLoAccession() {
        return loAccession;
    }

    @JsonProperty("lo_accession")
    public void setLoAccession(String loAccession) {
        this.loAccession = loAccession;
    }

    public FieldsLocality withLoAccession(String loAccession) {
        this.loAccession = loAccession;
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
        return ((((((((((((((((((((((("FieldsLocality"+" [id=")+ id)+", sourceName=")+ sourceName)+", city=")+ city)+", state=")+ state)+", country=")+ country)+", origcty=")+ origcty)+", elevation=")+ elevation)+", latitude=")+ latitude)+", longitude=")+ longitude)+", loAccession=")+ loAccession)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
