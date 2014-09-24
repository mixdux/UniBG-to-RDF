/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.is.bgunirdf.logic;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import rs.fon.is.bgunirdf.util.Constants;
import thewebsemantic.Bean2RDF;

/**
 *
 * @author Milan
 */
public class RDFPersistance {

    public static RDFPersistance instance;
    private Model model;
    private Dataset dataProvider;
    private Bean2RDF writer;

    public static RDFPersistance getInstance() {
        if (instance == null) {
            instance = new RDFPersistance();
        }
        return instance;
    }

    private RDFPersistance() {
        dataProvider = TDBFactory.createDataset(Constants.PATH_TDB);
        model = dataProvider.getDefaultModel();
        ModelUtil.setNsPrefixes(model);
        writer = new Bean2RDF(model);
    }

    public Model getModel() {
        return model;
    }

    public Dataset getDataProvider() {
        return dataProvider;
    }
    
    public Bean2RDF getWriter(){
        return writer;
    }

}
