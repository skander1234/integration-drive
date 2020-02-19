/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Core.LocationC;
import Core.VeloC;
import Entities.Location;
import Entities.Velo;
import Utils.Criteres;
import Utils.DataSource;
import Utils.Interval;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Belgaroui Ghazi
 */
public class LocationVelo {
   
   
    public static void startConsole(){
            DataSource ds =  DataSource.getInstance();
          
         
      
          System.out.println(ds.hashCode());
          SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          java.util.Date parsedDate;
          try{
          parsedDate = dateFormat.parse("2021-02-09 11:20:21");
             java.util.Date parsedDate2 = dateFormat.parse("2021-02-09 12:55:05");
          Timestamp timestamp1=new java.sql.Timestamp(parsedDate.getTime());
          Timestamp timestamp2=new java.sql.Timestamp(parsedDate2.getTime());
          
         // Location p = new Location(1,15,timestamp1,timestamp2,3);
   LocationC pr = new LocationC();
   //pr.ajouterLocation(p);
    //System.out.println(pr.afficher());
    //System.out.println(pr.Trier());
  // pr.modifierLocation(47,30,2,timestamp1,timestamp2,3);
      // System.out.println(pr.afficher());
        //pr.supprimerLocation(46);
       // System.out.println(pr.afficher());
             /* Criteres critere = new Criteres();//tu veut une choose specifiquee
    critere.ajouterCritere("prix", "0");
    critere.ajouterCritere("id_velo", "100");*/
    
    /*Interval listeInterval=new Interval();
     listeInterval.ajouter("id",2,12);
     listeInterval.ajouter("qte",3,11);
     System.out.println(pr.filtrerParInterval(listeInterval));*/
    
    //System.out.println(pr.filterSelonDesCritere(critere));
          //System.out.println(pr.RechercheAvance("46"));
          } catch (ParseException ex) {
            Logger.getLogger(DriveIntegration.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
          //Velo p = new Velo("ghada","hs",3,"hsdh",3);
          VeloC ps=new VeloC();
          //ps.ajouterVelo(p);
          //System.out.println(ps.afficher());
            //ps.modifierVelo(2,"skander","hs",3,"hsdh",30);
            
          
        //System.out.println(ps.RechercheAvance("a"));
        //ps.supprimerVelo(11);
        //System.out.println(ps.afficher()+"deleted");
          
      
                   /* Criteres critere = new Criteres();//tu veut une choose specifiquee
    critere.ajouterCritere("prix", "0");
    critere.ajouterCritere("id_velo", "100");
    
    System.out.println(ps.filterSelonDesCritere(critere));*/
        
        
        
        
    
     /*Interval listeInterval=new Interval();
     listeInterval.ajouter("id",2,12);
     listeInterval.ajouter("qte",3,11);
     System.out.println(ps.filtrerParInterval(listeInterval));*/
     
        //System.out.println(ps.trier("desc", "qte"));
 
          
    }

 }