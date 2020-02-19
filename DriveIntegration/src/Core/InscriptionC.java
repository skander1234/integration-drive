/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Entities.Inscription;
import Entities.Event;
import Entities.Location;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meriem
 */
public class InscriptionC {

    Connection cn = DataSource.getInstance().getConnexion();

    public Inscription recupereResultat(ResultSet rs) {
        Inscription p = new Inscription();
        try {
            p.setId_inscription(rs.getInt(1));
            ClientC us = new ClientC();
            EventC es = new EventC();
            p.setClient(us.retournerClient(rs.getInt(2)));
            p.setEvent(es.retournerEvent(rs.getInt(3)));

        } catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    
    
       public void ajouterInscrit(Inscription p){
           
       if(p.getEvent().getNbr_place()!=0 || Utils.FonctionsPartages.verifierExistanteDuneValeur("inscription", "id_client", p.getClient().getId_user())==true){
        try {
            int nbr_place=0;
            nbr_place =  Utils.FonctionsPartages.calculerNbreDePlace(p.getEvent().getNbr_place());
            p.getEvent().setNbr_place(nbr_place);
            
            String    requete ="insert into inscription(id_client,id_event) values (?,?) ";
            try {
                
                
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setInt(1,p.getClient().getId_user());
                pst.setInt(2,p.getEvent().getId_event());
                pst.executeUpdate();
                System.out.println("  nbr :   "+nbr_place+"   nbrrrrrrrrr  "+p.getEvent().getNbr_place());
                
            } catch (SQLException ex) {
                Logger.getLogger(InscriptionC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String requete2 ="UPDATE event SET nbr_place=? WHERE id_event=11";
            
            PreparedStatement pst1 = cn.prepareStatement(requete2);
            pst1.setInt(1, nbr_place);
             // pst1.setInt(2,p.getEvent().getId_event());
             pst1.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       }
       
       
       
      
       else{
           System.out.println("le client est deja inscrit ou bien il n ya plus de place");
       }
          
    }
       
       
       
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
