/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Entities.Event;
import Utils.Criteres;
import Utils.DataSource;
import Utils.Interval;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meriem
 */
public class EventC {

    Connection cn = DataSource.getInstance().getConnexion();
  public Event recupereResultat(ResultSet rs){
                Event e = new Event();
         try {
                 e.setId_event(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setNbr_place(rs.getInt(3));
                e.setDepart(rs.getString(4));
                e.setArrivee(rs.getString(5));
                e.setDate_allee(rs.getTimestamp(6));
                e.setDate_retour(rs.getTimestamp(7));
                e.setDescription(rs.getString(8));
         } catch (SQLException ex) {
             Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return e;
   }
    public void ajouterEvent(Event e) {
        String requete = "insert into event (nom,nbr_place,depart,arrivee,date_allee,date_retour,description) values (?,?,?,?,?,?,?) "; // précomplier
        try {

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, e.getNom());
            pst.setInt(2, e.getNbr_place());
            pst.setString(3, e.getDepart());
            pst.setString(4, e.getArrivee());
            pst.setTimestamp(5, (Timestamp) e.getDate_allee());
            pst.setTimestamp(6, (Timestamp) e.getDate_retour());
            pst.setString(7, e.getDescription());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Event> afficherEvent() {
        List<Event> list = new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
        String requete = "select * from event ORDER BY id_event DESC";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()) {
               
            
                list.add(recupereResultat(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void supprimerEvent(int id) {
        try {
            PreparedStatement pt = cn.prepareStatement("delete from event where id_event=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        public void modifierEvent(int id ,String Nom, int  NbrPlace, String Depart, Timestamp date_allee, Timestamp date_retour,String description) {
       
       String   requete = "update event set nom=?, nbr_place=?, depart=?, date_allee=?, date_retour=?,description=?  where id_event=?";
         try {
            PreparedStatement pt= cn.prepareStatement(requete);
            pt.setInt(7, id);
            pt.setString(1,Nom);
            pt.setInt(2, NbrPlace);
            pt.setString(3, Depart);
            pt.setTimestamp(4, date_allee);
            pt.setTimestamp(5, date_retour);
            pt.setString(6, description); 
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public List<Event> rechercher(String nom )
     {
         List<Event> list= new ArrayList<>();
           try {
            PreparedStatement pt = cn.prepareStatement("select * from event where nom=?");
            pt.setString(1, nom);
           ResultSet rs= pt.executeQuery();
            int i=0;
            while (rs.next()){
                Event e = new Event();
                e.setId_event(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setNbr_place(rs.getInt(3));
                e.setDepart(rs.getString(4));
                e.setArrivee(rs.getString(5));
                e.setDate_allee(rs.getTimestamp(6));
                e.setDate_retour(rs.getTimestamp(7));
                e.setDescription(rs.getString(8));
                list.add(e);   
                i++;
            }
            
            if(i==0) {System.out.println(" cette destination N'existe pas");}
            else{System.out.println(" cette destination existe");
          
            
            }
               
            
          } catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;  
     }

   public List<Event> filtrerParInterval(Interval listeInterval){
        
     
     List<Event> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("event", listeInterval.getListeListeInterval());
        try {
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
     }
   
   public List<Event> filterSelonDesCritere(Criteres critere){
   List<Event> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("event",critere.getListeCritere());
   
   try {
            Statement st = cn.createStatement();
            if(!requete.equals("")){
                ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
               list.add(recupereResultat(rs));
            }
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }

   public List<Event> trier(String ordre,String champs){
   List<Event> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"event",champs);
   
   try {
            Statement st = cn.createStatement();
            if(!requete.equals("")){
                ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
            list.add(recupereResultat(rs));
            }
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }

    public List<Event> RechercheAvancePrRedution(String mot){
   List<Event> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("event",mot);
      // System.out.println(requete);
   try {
            Statement st = cn.createStatement();
            if(!requete.equals("")){
                ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
               list.add(recupereResultat(rs));
            }
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }

}
