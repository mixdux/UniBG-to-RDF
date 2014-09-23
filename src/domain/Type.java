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

@Namespace(Constants.RDFS_NS)
@RdfType("Resource")
public class Type extends Thing{
     
    @RdfProperty(Constants.RDFS_NS+"label")
    private String name;

    public Type() {
    }
    
    public Type(int id) {
        this.id = id;
    }

    public Type(String name) {
        this.name = name;
    }
    
    public Type(String nameEn, String nameSrRs) {
        this.name = nameEn+","+nameSrRs;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameEn) {
        this.name = nameEn;
    }   
    
}
