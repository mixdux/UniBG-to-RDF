/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.is.bgunirdf.logic;

import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import rs.fon.is.bgunirdf.domain.Building;
import rs.fon.is.bgunirdf.domain.Feature;
import rs.fon.is.bgunirdf.domain.Organisation;
import rs.fon.is.bgunirdf.domain.Site;
import rs.fon.is.bgunirdf.domain.Thing;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import rs.fon.is.bgunirdf.util.Constants;

/**
 *
 * @author Milan
 */
public class ObjectCreators {

    public static List<Building> buildingCreator(CellFeed cellFeed,
            List<Feature> features, List<Organisation> orgs, List<Thing> types) throws URISyntaxException {
        List<Building> allBuildings = new ArrayList<Building>();
        Building building = new Building();
        for (CellEntry cell : cellFeed.getEntries()) {
            switch (cell.getTitle().getPlainText().charAt(0)) {
                case 'A':
                    building.setId(Integer.parseInt(cell.getCell().getValue()));
                    building.setUri(new URI(Constants.DOMAIN + "/buildings/" + building.getId()));
                    break;
                case 'B':
                    building.setName(cell.getCell().getValue());
                    break;
                case 'C':
                    building.setDate(cell.getCell().getValue());
                    break;
                case 'D':
                    building.setDescription(cell.getCell().getValue());
                    break;
                case 'E':
                    building.setFeatures(featurePopulator(cell.getCell().getValue(), features));
                    break;
                case 'F':
                    building.setWebpage(new URI(cell.getCell().getValue()));
                    break;
                case 'G':
                    building.setLongitude(Float.parseFloat(cell.getCell().getValue()));
                    break;
                case 'H':
                    building.setLatitude(Float.parseFloat(cell.getCell().getValue()));
                    break;
                case 'I':
                    building.setGeoPolygon("POLYGON((" + cell.getCell().getValue() + "))");
                    break;
                case 'J':
                    building.setOrganisation(orgs.get(Integer.parseInt(cell.getCell().getValue())-1));
                    break;
                case 'K':
                    building.setType(types.get(Integer.parseInt(cell.getCell().getValue())-1));
                    break;
                case 'L':
                    String[] sites = cell.getCell().getValue().split(",");
                    for (String site : sites) {
                        building.setSite(new Site(site));
                    }
                    //building.setSite(new ArrayList<Site>(){{ add(new Site(cellFinal.getCell().getValue())); }});
                    allBuildings.add(building);
                    building = new Building();
                    System.out.println("Building " + allBuildings.size() + " -> " + (allBuildings.get(allBuildings.size() - 1).getName()));
                    break;
            }
        }
        System.out.println("\n");
        return allBuildings;
    }

    public static List<Site> sitesCreator(CellFeed cellFeed) throws URISyntaxException {
        List<Site> allSites = new ArrayList<Site>();
        Site site = new Site();
        for (CellEntry cell : cellFeed.getEntries()) {
            switch (cell.getTitle().getPlainText().charAt(0)) {
                case 'A':
                    site.setId(cell.getCell().getValue());
                    site.setUri(new URI(Constants.DOMAIN + "/sites/" + site.returnId()));
                    break;
                case 'B':
                    site.setName(cell.getCell().getValue());
                    break;
                case 'C':
                    site.setGeoPolygon(cell.getCell().getValue());
                    //site.setUri(new URI("www.primenet/sites/"+site.id));
                    allSites.add(site);
                    site = new Site();
                    System.out.println("Site " + allSites.size() + " -> " + (allSites.get(allSites.size() - 1)).getName());
                    break;
            }
        }
        System.out.println("\n");
        return allSites;
    }

    public static List<Organisation> organisationsCreator(CellFeed cellFeed) throws URISyntaxException {
        List<Organisation> allOrganisations = new ArrayList<Organisation>();
        Organisation org = new Organisation();
        for (CellEntry cell : cellFeed.getEntries()) {
            switch (cell.getTitle().getPlainText().charAt(0)) {
                case 'A':
                    org.setId(Integer.parseInt(cell.getCell().getValue()));
                    org.setUri(new URI(Constants.DOMAIN + "/organisations/" + org.getId()));
                    break;
                case 'B':
                    org.setName(cell.getCell().getValue());
                    break;
                case 'C':
                    //org.setName(org.getName() + "," + cell.getCell().getValue());
                    allOrganisations.add(org);
                    org = new Organisation();
                    System.out.println("Organisation " + allOrganisations.size() + " -> " + (allOrganisations.get(allOrganisations.size() - 1)).getName());
                    break;
            }
        }
        System.out.println("\n");
        return allOrganisations;
    }

    public static List<Feature> featureCreator(CellFeed cellFeed) throws URISyntaxException {
        List<Feature> allFeatures = new ArrayList<Feature>();
        Feature feature = new Feature();
        for (CellEntry cell : cellFeed.getEntries()) {
            switch (cell.getTitle().getPlainText().charAt(0)) {
                case 'A':
                    feature.setId(Integer.parseInt(cell.getCell().getValue()));
                    feature.setUri(new URI(Constants.DOMAIN + "/features/" + feature.getId()));
                    break;
                case 'B':
                    feature.setName(cell.getCell().getValue());
                    break;
                case 'C':
                    //feature.setName(feature.getName() + "," + cell.getCell().getValue());
                    allFeatures.add(feature);
                    feature = new Feature();
                    System.out.println("Feature " + allFeatures.size() + " -> " + (allFeatures.get(allFeatures.size() - 1)).getName());
                    break;
            }
        }
        System.out.println("\n");
        return allFeatures;
    }

    public static List<Thing> typeCreator(CellFeed cellFeed) throws URISyntaxException {
        List<Thing> allTypes = new ArrayList<Thing>();
        Thing type = new Thing();
        for (CellEntry cell : cellFeed.getEntries()) {
            switch (cell.getTitle().getPlainText().charAt(0)) {
                case 'A':
                    type.setId(Integer.parseInt(cell.getCell().getValue()));
                    type.setUri(new URI(Constants.DOMAIN + "/types/" + type.getId()));
                    break;
                case 'B':
                    type.setName(cell.getCell().getValue());
                    break;
                case 'C':
                    //type.setName(type.getName() + "," + cell.getCell().getValue());
                    allTypes.add(type);
                    type = new Thing();
                    System.out.println("Type " + allTypes.size() + " -> " + (allTypes.get(allTypes.size() - 1)).getName());
                    break;
            }
        }
        System.out.println("\n");
        return allTypes;
    }

    private static String featurePopulator(String featureArray, List<Feature> features) {
        if (featureArray == null) {
            return null;
        }
        String[] featuresArray = featureArray.split(",");
        String verboseFeatures = "";
        for (String featureNumber : featuresArray) {
            if ((Integer.parseInt(featureNumber) - 1) > features.size()) {
                return featureArray;
            }
            verboseFeatures += (features.get(Integer.parseInt(featureNumber) - 1)).toString().split(",")[0] + ",";
        }
        return verboseFeatures.substring(0, verboseFeatures.length() - 1);
    }

}
