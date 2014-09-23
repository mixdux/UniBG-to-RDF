/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import util.Constants;

/**
 *
 * @author Milan
 */
@Namespace(Constants.ORG_NS)
@RdfType("FormalOrganization")
public class Organisation extends Thing{
    
    @RdfProperty(Constants.RDFS_NS+"label")
    private String name;

    public Organisation() {
    }
    
    public Organisation(int id){
        this.id = id;
    }
    
    public Organisation(String nameEn) {
        this.name = nameEn;
    }
    
    public Organisation(String nameEn, String nameRS) {
        this.name = nameEn+","+nameRS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
