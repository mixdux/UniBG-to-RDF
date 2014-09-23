/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bgunirdf;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.*;
import domain.Building;
import domain.Feature;
import domain.Organisation;
import domain.Site;
import domain.Type;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URI;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import thewebsemantic.Bean2RDF;
import util.AfterProcessor;
import util.Connection;
import util.Constants;
import util.ModelUtil;
import util.ObjectCreators;
import util.Populators;

/**
 *
 * @author Milan
 */
public class Parser {

    public static void main(String[] args) {

        SpreadsheetEntry spreadsheet = Connection.getTheSpreadsheet();
        if (spreadsheet == null) {
            return;
        }

        System.out.println(spreadsheet.getTitle().getPlainText());

        List<WorksheetEntry> worksheets = new ArrayList<WorksheetEntry>();
        try {
            worksheets = spreadsheet.getWorksheets();
        } catch (ServiceException ex) {
            System.out.println("Could not get worksheets");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Could not get worksheets");
            System.out.println(ex.getMessage());
        }

        List<Type> typeSpsh = Populators.typeListPopulator(worksheets);
        List<Feature> featureSpsh = Populators.featureListPopulator(worksheets);
        List<Site> siteSpsh = Populators.siteListPopulator(worksheets);
        List<Organisation> organisationSpsh = Populators.organisationListPopulator(worksheets);
        List<Building> buildingsSpsh = Populators.buildingListPopulator(worksheets, featureSpsh, organisationSpsh, typeSpsh);

        (new AfterProcessor()).afterProcessing(buildingsSpsh, siteSpsh);

        //Model model = ModelUtil.createNewModel();
        Model model = ModelUtil.getTDBModel();

        ModelUtil.populateModel(model, typeSpsh, siteSpsh, organisationSpsh, buildingsSpsh);

        ModelUtil.writeModelToFile(model, Constants.PATH_FILE);
        
    }
}
