/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Entities.Avis;
import Entities.Reservation;
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
 * @author Belgaroui Ghazi
 */
public class AvisC {
       Connection cn =DataSource.getInstance().getConnexion();
       
       public Avis recupereResultat(ResultSet rs){
                         Avis a = new Avis();
           try {
               a.setId_avis(rs.getInt(1));
               ClientC us=new ClientC();
               ChauffeurC vs=new ChauffeurC();
               a.setClient(us.retournerClient(rs.getInt(3)));
               a.setChauffeur(vs.retournerChauffeur(rs.getInt(2)));
                a.setMsg(rs.getString(4));
                a.setNote(rs.getInt(5));
           } catch (SQLException ex) {
               Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
           }

                 
                
                return a;
   } 
       
       
       
       
       
   public void ajouterAvis(Avis a){
     if (20>a.getNote()){
          String requete ="insert into avis(id_chauffeur,id_client,msg,note) values (?,?,?,?) ";
        try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1,a.getChauffeur().getId_user());
            pst.setInt(2,a.getClient().getId_user());
            pst.setString(3,a.getMsg());
            pst.setInt(4,a.getNote());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
        }
     }else {
         System.out.println("la note devra etre inferieur a 20");
     }
   }
    public List<Avis> afficher(){
          List<Avis> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select * from avis";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                
              list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
           
    }
  
             public boolean modifierAvis(int id,String champs,Object value){
    String   requete = "update avis set "+champs+"=?  where id_avis=?";
         if(FonctionsPartages.verifierExistanteDuneValeur("avis","id_avis",id)==true && FonctionsPartages.verifierSiChampExistant("avis",champs)==true){
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
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
        }  
       }else{
             System.out.println("le champs ou l'identifiant est incorrect");
         }
       
       return false;
   }
             
                public void supprimerAvis( int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from avis where id_avis=?");
           pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
      
      public List<Avis> filtrerParInterval(Interval listeInterval){
        
     
     List<Avis> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("avis", listeInterval.getListeListeInterval());
        try {
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
     }
      
      
      
      public List<Avis> filterSelonDesCritere(Criteres critere){
   List<Avis> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("avis",critere.getListeCritere());
   
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
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }
      
      
      
         public List<Avis> trier(String ordre,String champs){
   List<Avis> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"avis",champs);
   
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
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }

      public List<Avis> RechercheAvance(String mot){
   List<Avis> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("avis",mot);
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
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
    }
   
   return list;
   }
   
      
        public Avis retournerAvis(int id){
        try {
               PreparedStatement pt=cn.prepareStatement("select * from avis where id_avis=?");
           pt.setInt(1,id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()){
              return recupereResultat(rs);
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(AvisC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;
   }

}
