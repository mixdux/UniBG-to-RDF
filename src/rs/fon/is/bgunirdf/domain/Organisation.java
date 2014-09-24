/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rs.fon.is.bgunirdf.domain;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import rs.fon.is.bgunirdf.util.Constants;

/**
 *
 * @author Milan
 */
@Namespace(Constants.ORG_NS)
@RdfType("FormalOrganization")
public class Organisation extends Thing{

    public Organisation() {
    }
    
    public Organisation(int id){
        this.id = id;
    }
    
    public Organisation(String nameEn) {
        name = nameEn;
    }
    
    public Organisation(String nameEn, String nameRS) {
        name = nameEn+","+nameRS;
    }
    
}
