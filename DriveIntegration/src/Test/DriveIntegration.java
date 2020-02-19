/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Core.*;
import Entities.*;
import Utils.Criteres;
import Utils.Interval;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armand
 */
public class DriveIntegration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int choix = -1;
         
         while(choix == 0 || (choix<0 || choix>5)){
             
             System.out.println(" 0 - Quitter");
             System.out.println(" 1 - Module client et reservation");
             System.out.println(" 2 - Module chauffeur et taxi");
             System.out.println(" 3 - Module avis et reclamation");
             System.out.println(" 4 - Module location et velo");
             System.out.println(" 5 - Module offre et evenement");
             Scanner sc = new Scanner(System.in);
             System.out.println(" ");
                System.out.println("Veuillez choisir un numéro :");
                choix = sc.nextInt();
                
                if(choix>0 || choix>=5){
                
                    switch (choix) {
  case 1:
    Test.ClientReservation.startConsole();
    break;
  case 2:
    Test.ChauffeurTaxi.startConsole();
    break;
  case 3:
    Test.AvisetReclamation.startConsole();
    break;
  case 4:
    Test.LocationVelo.startConsole();
    break;
  case 5:
    Test.OffreEvent.startConsole();
    break;
}
                    
                }
         }
    
    
      

    }

}
