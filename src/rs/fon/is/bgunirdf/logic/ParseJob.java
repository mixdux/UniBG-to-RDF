/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.is.bgunirdf.logic;

import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;
import com.hp.hpl.jena.rdf.model.Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import rs.fon.is.bgunirdf.domain.Building;
import rs.fon.is.bgunirdf.domain.Feature;
import rs.fon.is.bgunirdf.domain.Organisation;
import rs.fon.is.bgunirdf.domain.Site;
import rs.fon.is.bgunirdf.domain.Thing;
import rs.fon.is.bgunirdf.util.Constants;

/**
 *
 * @author Milan
 */
public class ParseJob {

    public void parse(SpreadsheetEntry spreadsheet) {
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

        List<Thing> typeSpsh = Populators.typeListPopulator(worksheets);
        List<Feature> featureSpsh = Populators.featureListPopulator(worksheets);
        List<Site> siteSpsh = Populators.siteListPopulator(worksheets);
        List<Organisation> organisationSpsh = Populators.organisationListPopulator(worksheets);
        List<Building> buildingsSpsh = Populators.buildingListPopulator(worksheets, featureSpsh, organisationSpsh, typeSpsh);

        (new AfterProcessor()).afterProcessing(buildingsSpsh, siteSpsh);

        Model model = RDFPersistance.getInstance().getModel();
        ModelUtil modelUtil = new ModelUtil();
        modelUtil.populateModel(model, typeSpsh, siteSpsh, organisationSpsh, buildingsSpsh);
        modelUtil.writeModelToFile(model, Constants.PATH_FILE);
    }
}
