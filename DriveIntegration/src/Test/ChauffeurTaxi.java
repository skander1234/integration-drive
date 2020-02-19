/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Core.ChauffeurC;
import Core.TaxiC;
import Core.UserC;
import Entities.Chauffeur;
import Entities.Taxi;
import Entities.User;
import Utils.Criteres;
import Utils.DataSource;
import Utils.Interval;

/**
 *
 * @author HP
 */
public class ChauffeurTaxi {
    
    public static void startConsole(){
         DataSource ds =  DataSource.getInstance();
        
          User s = new User(22121743,"skrdsaaayjua","dsrhygreaaa",2,"aarhtrddg@sggdaa.com");
          UserC us = new UserC();
          //int id=us.ajouterUser(s);
        s.setId_user(7);
        Chauffeur p = new Chauffeur(s,"gsdgaa",88304678,"22/115678","skander","mejbri");
   //ChauffeurC ps = new ChauffeurC();
  //ps.ajouterChauffeur(p);
    //    System.out.println(s);
   //System.out.println(ps.afficher());
   //ps.modifierChauffeur(8,"adresse","aa");
     //  System.out.println(ps.afficher());
     //  ps.supprimerChauffeur(8);
      //System.out.println(ps.afficher()+"deleted");
          
          Taxi t = new Taxi(p,"dgsd","aaaaaaaaaaaaa");
   TaxiC pt = new TaxiC();
  pt.ajouterTaxi(t);
  System.out.println(pt.afficher());
  // pt.modifierTaxi(18,"photo","aaasdg");
    //   System.out.println(pt.afficher());
     //   pt.supprimerTaxi(18);
      //System.out.println(pt.afficher()+"deleted");
         /*Criteres critere = new Criteres();//tu veut une choose specifiquee
    critere.ajouterCritere("id_taxi", "18");
    
    
    System.out.println(pt.filterSelonDesCritere(critere));
        */
        
    /* Interval listeInterval=new Interval();
     listeInterval.ajouter("id_taxi",16,18);
     listeInterval.ajouter("id_chauffeur",5,7);
     System.out.println(pt.filtrerParInterval(listeInterval));
     */
      // System.out.println(pt.trier("desc","id_taxi"));
            /*Criteres critere = new Criteres();//tu veut une choose specifiquee
    critere.ajouterCritere("id_taxi", "13");
    critere.ajouterCritere("id_chauffeur", "49");
    
    System.out.println(ps.filterSelonDesCritere(critere));
        */
       /* 
     Interval listeInterval=new Interval();
     listeInterval.ajouter("id_taxi",12,14);
     listeInterval.ajouter("id_chauffeur",28,47);
     System.out.println(ps.filtrerParInterval(listeInterval));
     */
        //System.out.println(pt.trier("desc", "qte"));
          //System.out.println(ps.RechercheAvance("a"));
          //System.out.println(pt.RechercheAvance("a"));
    }
}
