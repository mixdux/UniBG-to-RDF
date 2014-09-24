/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.is.bgunirdf.logic;

import rs.fon.is.bgunirdf.domain.Building;
import rs.fon.is.bgunirdf.domain.Site;
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
            if (site.getContainedBuildings().isEmpty()) {
                System.out.println("EMPTY! " + site.getName());
            }
        }
    }

    private void siteResolver(Building building, List<Site> sites) {
        for (Site site : sites) {
            if (building.getSites().size() == 1) {
                if (site.returnId().equals(building.getSites().get(0).returnId())) {
                    site.getContainedBuildings().add(building);
                }
            } else {
                for (Site siteMany : building.getSites()) {
                    if (site.returnId().equals(siteMany.returnId())) {
                        site.getContainedBuildings().add(building);
                    }
                }
            }
        }
    }
}
