
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
 * <p>Original spec-file type: fields_Biomass</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "mod_date",
    "name",
    "dna",
    "protein",
    "cell_wall",
    "lipid",
    "cofactor",
    "energy"
})
public class FieldsBiomass {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("mod_date")
    private java.lang.String modDate;
    @JsonProperty("name")
    private List<String> name;
    @JsonProperty("dna")
    private Double dna;
    @JsonProperty("protein")
    private Double protein;
    @JsonProperty("cell_wall")
    private Double cellWall;
    @JsonProperty("lipid")
    private Double lipid;
    @JsonProperty("cofactor")
    private Double cofactor;
    @JsonProperty("energy")
    private Double energy;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public FieldsBiomass withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("mod_date")
    public java.lang.String getModDate() {
        return modDate;
    }

    @JsonProperty("mod_date")
    public void setModDate(java.lang.String modDate) {
        this.modDate = modDate;
    }

    public FieldsBiomass withModDate(java.lang.String modDate) {
        this.modDate = modDate;
        return this;
    }

    @JsonProperty("name")
    public List<String> getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(List<String> name) {
        this.name = name;
    }

    public FieldsBiomass withName(List<String> name) {
        this.name = name;
        return this;
    }

    @JsonProperty("dna")
    public Double getDna() {
        return dna;
    }

    @JsonProperty("dna")
    public void setDna(Double dna) {
        this.dna = dna;
    }

    public FieldsBiomass withDna(Double dna) {
        this.dna = dna;
        return this;
    }

    @JsonProperty("protein")
    public Double getProtein() {
        return protein;
    }

    @JsonProperty("protein")
    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public FieldsBiomass withProtein(Double protein) {
        this.protein = protein;
        return this;
    }

    @JsonProperty("cell_wall")
    public Double getCellWall() {
        return cellWall;
    }

    @JsonProperty("cell_wall")
    public void setCellWall(Double cellWall) {
        this.cellWall = cellWall;
    }

    public FieldsBiomass withCellWall(Double cellWall) {
        this.cellWall = cellWall;
        return this;
    }

    @JsonProperty("lipid")
    public Double getLipid() {
        return lipid;
    }

    @JsonProperty("lipid")
    public void setLipid(Double lipid) {
        this.lipid = lipid;
    }

    public FieldsBiomass withLipid(Double lipid) {
        this.lipid = lipid;
        return this;
    }

    @JsonProperty("cofactor")
    public Double getCofactor() {
        return cofactor;
    }

    @JsonProperty("cofactor")
    public void setCofactor(Double cofactor) {
        this.cofactor = cofactor;
    }

    public FieldsBiomass withCofactor(Double cofactor) {
        this.cofactor = cofactor;
        return this;
    }

    @JsonProperty("energy")
    public Double getEnergy() {
        return energy;
    }

    @JsonProperty("energy")
    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public FieldsBiomass withEnergy(Double energy) {
        this.energy = energy;
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
        return ((((((((((((((((((((("FieldsBiomass"+" [id=")+ id)+", modDate=")+ modDate)+", name=")+ name)+", dna=")+ dna)+", protein=")+ protein)+", cellWall=")+ cellWall)+", lipid=")+ lipid)+", cofactor=")+ cofactor)+", energy=")+ energy)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
