/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import domain.Building;
import domain.Feature;
import domain.Organisation;
import domain.Site;
import domain.Type;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import thewebsemantic.Bean2RDF;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 *
 * @author Milan
 */
public class ModelUtil {

    public static Model createNewModel() {
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("rooms", Constants.ROOMS_NS);
        model.setNsPrefix("foaf", Constants.FOAF_NS);
        model.setNsPrefix("rdf", Constants.RDF_NS);
        model.setNsPrefix("rdfs", Constants.RDFS_NS);
        model.setNsPrefix("dcterms", Constants.DCTERMS_NS);
        model.setNsPrefix("oo", Constants.OO_NS);
        model.setNsPrefix("org", Constants.ORG_NS);
        model.setNsPrefix("geo", Constants.GEO_NS);
        model.setNsPrefix("xsd", Constants.XSD_NS);
        model.setNsPrefix("southampton", Constants.SOUTHAMPTON_NS);
        return model;
    }

    public static void populateModel(Model model, List<Type> typeList,
            List<Site> siteList, List<Organisation> organisationList,
            List<Building> buildingsList) {
        Bean2RDF writer = new Bean2RDF(model);
        for (int i = 0; i < typeList.size(); i++) {
            writer.save(typeList.get(i));
        }
        for (int i = 0; i < organisationList.size(); i++) {
            writer.saveDeep(organisationList.get(i));
        }
        for (int i = 0; i < buildingsList.size(); i++) {
            writer.saveDeep(buildingsList.get(i));
        }
        for (int i = 0; i < siteList.size(); i++) {
            writer.saveDeep(siteList.get(i));
        }
    }

    public static void writeModelToFile(Model model, String filepath) {
        try {
            model.write(new BufferedWriter(new FileWriter(new File(filepath))), "TURTLE");
        } catch (IOException ex) {
            System.out.println("Could not write model to file");
            System.out.println(ex.getMessage());
        }
    }
    
    public static Model getTDBModel(){
        Dataset dataset = TDBFactory.createDataset(Constants.PATH_TDB);
        return dataset.getDefaultModel();
    }

}
