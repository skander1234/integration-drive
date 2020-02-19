/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Core.EventC;
import Core.InscriptionC;
import Core.OffreC;
import Entities.Client;
import Entities.Event;
import Entities.Inscription;
import Entities.Offre;
import Entities.User;
import Utils.Criteres;
import Utils.Interval;
import Utils.JavamailUtil;
import Utils.PDFutil;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meriem
 */
public class OffreEvent {
    
    public static void startConsole(){
        /**
         * *****************************************************************
         */
        /**
         * *****************************************************************
         */
        /*Event*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate1;
        Date parsedDate;
        Date parsedDate12;
        Date parsedDate11;
        try {
            // date pour l'Ajout
            parsedDate = dateFormat.parse("2000-11-11 11:20:21 PM"); 
            parsedDate1 = dateFormat.parse("2000-11-15 23:11:21 PM ");
            Timestamp timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
            Timestamp timestamp2 = new java.sql.Timestamp(parsedDate1.getTime());
               // Fin Ajout

            // date pour la Modif 
            parsedDate11 = dateFormat.parse("2000-11-22 11:20:21");
            parsedDate12 = dateFormat.parse("2000-11-25 23:11:21");
            Timestamp timestamp11 = new java.sql.Timestamp(parsedDate11.getTime());
            Timestamp timestamp22 = new java.sql.Timestamp(parsedDate12.getTime());
              parsedDate11 = dateFormat.parse("2000-11-24 11:20:21");
            parsedDate12 = dateFormat.parse("2000-11-27 23:11:21");
            Timestamp timestamp111 = new java.sql.Timestamp(parsedDate11.getTime());
            Timestamp timestamp222 = new java.sql.Timestamp(parsedDate12.getTime());
            
            // Fin Modif
            
              // Event e = new Event("Festivale de grenade ", 100, "Tunis","Testour",timestamp1, timestamp2," Bienvenue à tous ");
            EventC ec = new EventC();
            //    ec.ajouterEvent(e);
            //    ec.supprimerEvent(1);
                ec.modifierEvent(2,"arrivee","Carthage");
           // System.out.println(ec.afficherEvent());
            //   System.out.println(ec.rechercher("Mouled")); 
        

        /**
         * *****************************************************************
         */
        EventC us = new EventC();
        
      //      System.out.println(us.RechercheAvancePrRedution("3")); 
        
        

     //   Criteres critere = new Criteres();
      //  critere.ajouterCritere("depart", "Tunis");
      // critere.ajouterCritere("arrivee", "Carthage");
     //   System.out.println(us.filterSelonDesCritere(critere));

        //  System.out.println(us.afficherEvent());
        System.out.println("*******************************************************");
        
        // 
     //   Interval listeInterval = new Interval();
      //  listeInterval.ajouter("id_event", 2, 4);
   //     listeInterval.ajouter("nbr_place", 100, 120);
    //  System.out.println(us.filtrerParInterval(listeInterval));
      

        /**
         * *****************************************************************
         */
        /**
         * *****************************************************************
         */
        /* OFFRE */
      //  Offre op =new Offre(timestamp1,timestamp2,"location","Promotion",23.1f);
        
       // Offre or =new Offre(timestamp11,timestamp22,"reservation","Promotion",0.5f,"JKSLK32");
        OffreC oc = new OffreC();
        
    //    Criteres critere = new Criteres();
     // critere.ajouterCritere("type", "location");
       // critere.ajouterCritere("nom", "Promotion");
      //  System.out.println(oc.filterSelonDesCritere(critere));
     //   System.out.println(oc.trier("desc", "reduction_offre"));
        
        Interval listeInterval = new Interval();
     listeInterval.ajouter("id_offre", 4, 8);
    listeInterval.ajouter("prix_offre", 20, 22.5);
        
//      System.out.println(oc.filtrerParInterval(null));
        
          //System.out.println(oc.afficherReduction()); 
       // oc.ajouterReduction(op);
         //oc.ajouterReduction(or);
     //   oc.ajouterReduction(or);
        //oc.supprimerOffre(13);
       // oc.modifierOffre(21,"nom","Fete des arbres");
        //   System.out.println(oc.afficherPromo());
      //  System.out.println(oc.afficherReduction());
         
        
        
        
        
       //  System.out.println(oc.RechercheAvance("p")); 
        //    System.out.println("*** location");
      //      Event e = new Event("Festivale de Carthage", 12, "Tunis","Carthage",timestamp11, timestamp22,"Bienvenu à la meilleure musique");
     
             Event e2 = new Event("Festivale de Carthage", 12, "Tunis","Carthage",timestamp111, timestamp222,"Bienvenu à la meilleure musique");
      //      Event e1 = new Event("Festivale de Carthage", 12, "Tunis","Carthage",timestamp11, timestamp22,"Bienvenu à la meilleure musique");
     
           //  EventC eC =new EventC();
          //   eC.ajouterEvent(e2);
             User u=new User(16,699875176,"armand","12345678",1,"armand@gmail.com");
             Client c= new Client(u,12);
             Event e3 = new Event(11,"Festivale de Carthage", 12, "Tunis","Carthage",timestamp111, timestamp222,"Bienvenu à la meilleure musique");
             InscriptionC ic = new InscriptionC();
             Inscription i = new Inscription(e3, c);
             ic.ajouterInscrit(i);
             
        /*            JavamailUtil mail = new JavamailUtil();
            try {
                    
                mail.sendMail("ghadadadou07@gmail.com");
            } catch (Exception ex) {
                Logger.getLogger(OffreEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
     /*     PDFutil pdf = new PDFutil();
            try {
                pdf.listEvent();
                
                //Inscription in = new Inscription(e,c);
                // InscriptionC inC = new InscriptionC();
                //  inC.ajouterInscrit(in);
                
                 
            } catch (SQLException ex) {
                Logger.getLogger(OffreEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(OffreEvent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OffreEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
                                */
        
       }
        
        
        
        catch (ParseException ex) {
            Logger.getLogger(DriveIntegration.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
