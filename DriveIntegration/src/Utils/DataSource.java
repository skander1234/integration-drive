/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armand
 */
public class DataSource {
    Connection cnx;
    String url="jdbc:mysql://localhost:3306/drive";
    String login="root";
    String mdp="";
  static  DataSource instance; // cree atribut static de type la classe
  private DataSource(){
        try {
            cnx = DriverManager.getConnection(url, login, mdp); //meth d'instance, de classe
            System.err.println("cnx etablie");
            //interdir la creation des instances .. MDRR private
            
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   }
   public Connection getConnexion(){
   return cnx;
   }
   public static DataSource getInstance(){ // methode (de classe) AHAHHA static qui retourne l instance (type de classe)w taaml el if ..
     if (instance==null){
       instance =  new DataSource();
        }
     return instance; 
   }
}
