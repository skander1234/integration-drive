/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Core.LocationC;
import Core.VeloC;
import Entities.Client;
import Entities.Location;
import Entities.User;
import Entities.Velo;
import Utils.Criteres;
import Utils.DataSource;
import Utils.Interval;
import Utils.PDFutil;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
          User u=new User(16,54526394,"hj","iop",1,"mail@gmail.com");
          Client c= new Client(u,12);
          
          Velo v = new Velo(2,"velo","hs",3,"hsdh",3);
          Location p = new Location(c,v,timestamp1,timestamp2);
          LocationC pr = new LocationC();
          pr.ajouterLocation(p);
          //System.out.println(pr.afficher());
        
          
      // pr.modifierLocation(58,"prix",40);
      // System.out.println(pr.afficher());
          
        //pr.supprimerLocation(58);
       //System.out.println(pr.afficher());
          
          
          //System.out.println(pr.trier("desc", "id_velo"));
          
          /*  Criteres critere = new Criteres();//tu veut une choose specifiquee
    critere.ajouterCritere("id_client", "1");
    critere.ajouterCritere("id_velo", "19");*/
    //System.out.println(pr.filterSelonDesCritere(critere));
    
    
    
    /*Interval listeInterval=new Interval();
     listeInterval.ajouter("id_velo",15,16);
     listeInterval.ajouter("id_client",1,2);
     System.out.println(pr.filtrerParInterval(listeInterval));*/
    
    
          //System.out.println(pr.RechercheAvance("5"));
          PDFutil pdf = new PDFutil();
            
                try {
                    pdf.listLocation();
                } catch (SQLException ex) {
                    Logger.getLogger(LocationVelo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(LocationVelo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LocationVelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
          } catch (ParseException ex) {
            Logger.getLogger(DriveIntegration.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
          Velo p = new Velo("velo","hs",3,"hsdh",3);
          VeloC ps=new VeloC();
          //ps.ajouterVelo(p);
          //System.out.println(ps.afficher());
           // ps.modifierVelo(2,"type","heyyy");
            
          
        
        //ps.supprimerVelo(11);
        //System.out.println(ps.afficher());
          
          
          //System.out.println(ps.RechercheAvance("a"));
      
     /*Criteres critere = new Criteres();//tu veut une choose specifiquee
    critere.ajouterCritere("prix", "3");
    critere.ajouterCritere("id", "19");
    
    System.out.println(ps.filterSelonDesCritere(critere));*/
        
        
        
        
    
     /*Interval listeInterval=new Interval();
     listeInterval.ajouter("id",2,12);
     listeInterval.ajouter("qte",3,11);
     System.out.println(ps.filtrerParInterval(listeInterval));*/
     
        //System.out.println(ps.trier("desc", "qte"));
 
          
    }

 }