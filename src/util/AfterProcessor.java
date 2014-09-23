/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Building;
import domain.Feature;
import domain.Organisation;
import domain.Site;
import domain.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milan
 */
public class AfterProcessor {

    public void afterProcessing(List<Building> buildings, List<Site> sites) {
        for (Building building : buildings) {
            siteResolver(building, sites);
        }
        for (Site site : sites) {
            if (site.getContainedBuildings().size() == 0) {
                System.out.println("EMPTY! " + site.getName());
            }
        }
    }

    private void siteResolver(Building building, List<Site> sites) {
        for (Site site : sites) {
            if (building.getSites().size() == 1) {
                if (site.id.equals(building.getSites().get(0).id)) {
                    site.getContainedBuildings().add(building);
                }
            } else {
                for (Site siteMany : building.getSites()) {
                    if (site.id.equals(siteMany.id)) {
                        site.getContainedBuildings().add(building);
                    }
                }
            }
        }
    }
}
