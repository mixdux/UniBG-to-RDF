/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rs.fon.is.bgunirdf.domain;

import java.util.HashSet;
import java.util.Set;
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
@RdfType("Site")
public class Site extends Thing {
    
    @Transient
    private String id;
    
    @RdfProperty(Constants.DCTERMS_NS+"spatial")
    private String geoPolygon;
    
    @RdfProperty(Constants.ROOMS_NS+"contains")
    private Set<Building> containedBuildings;

    public Site(String nameEn, String polygon) {
        this.name = nameEn;
        this.geoPolygon = "POLYGON(("+polygon+"))";
        containedBuildings = new HashSet<Building>();
    }

    public Site(String id){
        this.id = id;
        containedBuildings = new HashSet<Building>();
    }
    
    public Site(){
        containedBuildings = new HashSet<Building>();
    }

    public String getGeoPolygon() {
        return geoPolygon;
    }

    public void setGeoPolygon(String geoPolygon) {
        this.geoPolygon = geoPolygon;
    }

    public Set<Building> getContainedBuildings() {
        return containedBuildings;
    }

    public void setContainedBuildings(Set<Building> containedBuildings) {
        this.containedBuildings = containedBuildings;
    }
 
    public String returnId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
