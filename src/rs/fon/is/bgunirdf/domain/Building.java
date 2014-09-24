/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rs.fon.is.bgunirdf.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import thewebsemantic.Transient;
import rs.fon.is.bgunirdf.util.Constants;

/**
 *
 * @author Milan
 */

@Namespace(Constants.ROOMS_NS)
@RdfType("Building")
public class Building extends Thing{
    
    @RdfProperty(Constants.SOUTHAMPTON_NS+"buildingDate")
    private String date;
    
    @RdfProperty(Constants.DCTERMS_NS+"description")
    private String description;
    
    @RdfProperty(Constants.SOUTHAMPTON_NS+"buildingFeature")
    private String features;
    
    @RdfProperty(Constants.FOAF_NS+"page")
    private URI webpage;
    
    @RdfProperty(Constants.GEO_NS+"long")
    private float longitude;
    
    @RdfProperty(Constants.GEO_NS+"lat")
    private float latitude;
    
    @RdfProperty(Constants.DCTERMS_NS+"spatial")
    private String geoPolygon;
    
    @RdfProperty(Constants.OO_NS+"formalOrganization")
    private Organisation organisation;
    
    @RdfProperty(Constants.DCTERMS_NS+"subject")
    private Thing type;
    
    @Transient
    private List<Site> site;
    
    public Building(){
        site = new ArrayList<Site>();
    }

    public Building(int id){
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public URI getWebpage() {
        return webpage;
    }

    public void setWebpage(URI webpage) {
        this.webpage = webpage;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getGeoPolygon() {
        return geoPolygon;
    }

    public void setGeoPolygon(String geoPolygon) {
        this.geoPolygon = geoPolygon;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Thing getType() {
        return type;
    }

    public void setType(Thing type) {
        this.type = type;
    }

    public List<Site> getSites() {
        return site;
    }

    public void setSites(List<Site> site) {
        this.site = site;
    }  
    
    public void setSite(Site site) {
        this.site.add(site);
    }
    
}
