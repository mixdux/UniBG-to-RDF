/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import bgunirdf.Parser;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;
import domain.Building;
import domain.Feature;
import domain.Organisation;
import domain.Site;
import domain.Type;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milan
 */
public class Populators {

    public static List<Feature> featureListPopulator(List<WorksheetEntry> worksheets) {
        List<Feature> featureList = new ArrayList<Feature>();
        for (WorksheetEntry worksheet : worksheets) {
            if (worksheet.getTitle().getPlainText().equals("Features")) {
                try {
                    URL cellFeedUrl = new URI(worksheet.getCellFeedUrl().toString()
                            + "?min-row=4").toURL();
                    CellFeed cellFeed = Connection.service.getFeed(cellFeedUrl, CellFeed.class);
                    featureList = ObjectCreators.featureCreator(cellFeed);
                } catch (URISyntaxException ex) {
                    System.out.println("Could not populate Features list");
                    System.out.println(ex.getMessage());
                } catch (MalformedURLException ex) {
                    System.out.println("Could not populate Features list");
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Could not populate Features list");
                    System.out.println(ex.getMessage());
                } catch (ServiceException ex) {
                    System.out.println("Could not populate Features list");
                    System.out.println(ex.getMessage());
                }
            }
        }
        return featureList;
    }
    
    public static List<Type> typeListPopulator(List<WorksheetEntry> worksheets) {
        List<Type> typeList = new ArrayList<Type>();
        for (WorksheetEntry worksheet : worksheets) {
            if (worksheet.getTitle().getPlainText().equals("Types")) {
                try {
                    URL cellFeedUrl = new URI(worksheet.getCellFeedUrl().toString()
                            + "?min-row=4").toURL();
                    CellFeed cellFeed = Connection.service.getFeed(cellFeedUrl, CellFeed.class);
                    typeList = ObjectCreators.typeCreator(cellFeed);
                } catch (URISyntaxException ex) {
                    System.out.println("Could not populate Types list");
                    System.out.println(ex.getMessage());
                } catch (MalformedURLException ex) {
                    System.out.println("Could not populate Types list");
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Could not populate Types list");
                    System.out.println(ex.getMessage());
                } catch (ServiceException ex) {
                    System.out.println("Could not populate Types list");
                    System.out.println(ex.getMessage());
                }
            }
        }
        return typeList;
    }
    
    public static List<Organisation> organisationListPopulator(List<WorksheetEntry> worksheets) {
        List<Organisation> organisationList = new ArrayList<Organisation>();
        for (WorksheetEntry worksheet : worksheets) {
            if (worksheet.getTitle().getPlainText().equals("Organisation")) {
                try {
                    URL cellFeedUrl = new URI(worksheet.getCellFeedUrl().toString()
                            + "?min-row=4").toURL();
                    CellFeed cellFeed = Connection.service.getFeed(cellFeedUrl, CellFeed.class);
                    organisationList = ObjectCreators.organisationsCreator(cellFeed);
                } catch (URISyntaxException ex) {
                    System.out.println("Could not populate Organisations list");
                    System.out.println(ex.getMessage());
                } catch (MalformedURLException ex) {
                    System.out.println("Could not populate Organisations list");
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Could not populate Organisations list");
                    System.out.println(ex.getMessage());
                } catch (ServiceException ex) {
                    System.out.println("Could not populate Organisations list");
                    System.out.println(ex.getMessage());
                }
            }
        }
        return organisationList;
    }
    
    public static List<Site> siteListPopulator(List<WorksheetEntry> worksheets) {
        List<Site> siteList = new ArrayList<Site>();
        for (WorksheetEntry worksheet : worksheets) {
            if (worksheet.getTitle().getPlainText().equals("Sites")) {
                try {
                    URL cellFeedUrl = new URI(worksheet.getCellFeedUrl().toString()
                            + "?min-row=4").toURL();
                    CellFeed cellFeed = Connection.service.getFeed(cellFeedUrl, CellFeed.class);
                    siteList = ObjectCreators.sitesCreator(cellFeed);
                } catch (URISyntaxException ex) {
                    System.out.println("Could not populate Sites list");
                    System.out.println(ex.getMessage());
                } catch (MalformedURLException ex) {
                    System.out.println("Could not populate Sites list");
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Could not populate Sites list");
                    System.out.println(ex.getMessage());
                } catch (ServiceException ex) {
                    System.out.println("Could not populate Sites list");
                    System.out.println(ex.getMessage());
                }
            }
        }
        return siteList;
    }
    
    public static List<Building> buildingListPopulator(List<WorksheetEntry> worksheets,
            List<Feature> features, List<Organisation> orgs, List<Type> types) {
        List<Building> buildingList = new ArrayList<Building>();
        for (WorksheetEntry worksheet : worksheets) {
            if (worksheet.getTitle().getPlainText().equals("Buildings")) {
                try {
                    URL cellFeedUrl = new URI(worksheet.getCellFeedUrl().toString()
                            + "?min-row=4").toURL();
                    CellFeed cellFeed = Connection.service.getFeed(cellFeedUrl, CellFeed.class);
                    buildingList = ObjectCreators.buildingCreator(cellFeed, features, orgs, types);
                } catch (URISyntaxException ex) {
                    System.out.println("Could not populate Buildings list");
                    System.out.println(ex.getMessage());
                } catch (MalformedURLException ex) {
                    System.out.println("Could not populate Buildings list");
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Could not populate Buildings list");
                    System.out.println(ex.getMessage());
                } catch (ServiceException ex) {
                    System.out.println("Could not populate Buildings list");
                    System.out.println(ex.getMessage());
                }
            }
        }
        return buildingList;
    }
    

}
