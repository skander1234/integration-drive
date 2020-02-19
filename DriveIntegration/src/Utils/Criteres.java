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
public class Criteres {
    Map<String,List<Object>> listeCritere;

    public Criteres() {
        listeCritere = new HashMap<>();
    }

    public Map<String, List<Object>> getListeCritere() {
        return listeCritere;
    }

    public void setListeCritere(Map<String, List<Object>> listeCritere) {
        this.listeCritere = listeCritere;
    }
    
    public void ajouterCritere(String champs,Object o){
    if(listeCritere.containsKey(champs)==false){
        listeCritere.put(champs,new ArrayList<>());
    }
    listeCritere.get(champs).add(o);
    
    }
}
