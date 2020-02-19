/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Entities.Client;
import Entities.Reservation;
import Entities.User;
import Utils.Criteres;
import Utils.DataSource;
import Utils.FonctionsPartages;
import Utils.Interval;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Armand
 */
public class ReservationC {
     Connection cn =DataSource.getInstance().getConnexion();
 
    public Reservation recupereResultat(ResultSet rs){
                Reservation p = new Reservation();
         try {
             p.setId_reservation(rs.getInt(1));
             ClientC us=new ClientC();
             ChauffeurC vs=new ChauffeurC();
             p.setClient(us.retournerClient(rs.getInt(2)));
                p.setChauffeur(vs.retournerChauffeur(rs.getInt(3)));
                p.setDepart(rs.getString(4));
                p.setArrive(rs.getString(5));
                p.setHeure(rs.getTimestamp(6));
                p.setPrix(rs.getDouble(7));
                p.setType_reservation(rs.getString(8));
                
                
                if(p.getType_reservation().equals("livraison")){
                p.setCode_liv(rs.getInt(9));
                }
                if(p.getType_reservation().equals("covoiturage")){
                p.setNbr_place(rs.getInt(10));
                }
         } catch (SQLException ex) {
             Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return p;
   }
        public boolean modifierReservation(int id,String champs,Object value){
    String   requete = "update reservation set "+champs+"=?  where id_reservation=?";
         if(FonctionsPartages.verifierExistanteDuneValeur("reservation","id_reservation",id)==true && FonctionsPartages.verifierSiChampExistant("reservation",champs)==true){
       try {
            PreparedStatement pt= cn.prepareStatement(requete);
            
            if (value instanceof Integer){
            pt.setInt(1,(int) value);
            }
             if (value instanceof Float){
            pt.setFloat(1,(float) value);
            }   
             if (value instanceof Double){
            pt.setDouble(1,(double) value);
            } 
             if (value instanceof String){
            pt.setString(1,(String) value);
            } 
             if (value instanceof Date){
            pt.setDate(1,(Date) value);
            } 
             if (value instanceof Timestamp){
            pt.setTimestamp(1,(Timestamp) value);
            } 
            pt.setInt(2, id);
            pt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReservationC.class.getName()).log(Level.SEVERE, null, ex);
        }  
       }else{
             System.out.println("le champs ou l'identifiant est incorrect");
         }
       
       return false;
   }

      
   public void ajouterReservation(Reservation p){
          String requete ="insert into reservation (id_client,id_chauffeur,depart,arrive,heure,prix,type_reservation,code_liv,nbr_place) values (?,?,?,?,?,?,?,?,?) "; // précomplier
        if(p.getType_reservation().equals("livraison") || p.getType_reservation().equals("personnel") || p.getType_reservation().equals("covoiturage")){
            try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1,p.getClient().getId_user());
            pst.setInt(2,p.getChauffeur().getId_user());
            pst.setString(3,p.getDepart());
            pst.setString(4,p.getArrive());
            pst.setTimestamp(5, p.getHeure());
            pst.setDouble(6,p.getPrix());
            pst.setString(7,p.getType_reservation());
            pst.setInt(8,p.getCode_liv());
            pst.setInt(9,p.getNbr_place());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            System.out.println("le type de reservation est incorrect");
        }
         
    }
    public List<Reservation> afficher(){
          List<Reservation> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select * from reservation";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
           
    }
      public void supprimerReservation(int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from reservation where id_reservation=?");
           pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
      
        public List<Reservation> filtrerParInterval(Interval listeInterval){
        
     
     List<Reservation> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("reservation", listeInterval.getListeListeInterval());
        try {
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
     }
   
   public List<Reservation> filterSelonDesCritere(Criteres critere){
   List<Reservation> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("reservation",critere.getListeCritere());
   
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
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }

   public List<Reservation> trier(String ordre,String champs){
   List<Reservation> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"reservation",champs);
   
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
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }
           
   public List<Reservation> RechercheAvance(String mot){
   List<Reservation> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("reservation",mot);

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
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }
      
      public Reservation retournerReservation(int id){
        try {
               PreparedStatement pt=cn.prepareStatement("select * from reservation where id_reservation=?");
           pt.setInt(1,id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()){
              return recupereResultat(rs);
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(ReservationC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;
   }
}
