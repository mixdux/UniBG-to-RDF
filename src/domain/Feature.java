/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author Milan
 */
public class Feature extends Thing{
    
    private String name;

    public Feature() {
    }
    
    public Feature(String nameEn) {
        this.name = nameEn;
    }

    public Feature(String nameEn, String nameSrRs) {
        this.name = nameEn +","+nameSrRs;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameEn) {
        this.name = nameEn;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
