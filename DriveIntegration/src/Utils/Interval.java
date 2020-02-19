/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Armand
 */
public class Interval {
        Map<String,Object[]> listeInterval;

    public Interval() {
        this.listeInterval = new HashMap<>();
    }

    public Map<String, Object[]> getListeListeInterval() {
        return listeInterval;
    }

    public void setListeCritere(Map<String, Object[]> listeInterval) {
        this.listeInterval = listeInterval;
    }
    
    public void ajouter(String champs,Object o1,Object o2){
         Object[] o = {o1,o2};
        listeInterval.put(champs,o);
    
    }
        
}
