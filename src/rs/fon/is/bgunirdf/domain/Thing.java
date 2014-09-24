/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rs.fon.is.bgunirdf.domain;

import java.net.URI;
import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import thewebsemantic.Transient;
import rs.fon.is.bgunirdf.util.Constants;

/**
 *
 * @author Milan
 */

@Namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#")
@RdfType("Thing")
public class Thing {
    
    @Id
    private URI uri;
    
    @RdfProperty(Constants.RDFS_NS+"label")
    protected String name;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Transient
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
