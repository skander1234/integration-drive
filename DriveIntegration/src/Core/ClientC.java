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
public class ClientC {
         Connection cn =DataSource.getInstance().getConnexion();
       public Client recupereResultat(ResultSet rs){
                Client p = new Client();
         try {
                p.setId_user(rs.getInt(1));
             UserC us= new UserC();
             User user= us.retournerUser(rs.getInt(1));
              p.setN_tel(user.getN_tel());
                p.setLogin(user.getLogin());
                p.setMdp(user.getMdp());
                p.setEtat(user.getEtat());
                p.setMail(user.getMail());
                
                p.setNbr_res_annulee(rs.getInt(2));
                
         } catch (SQLException ex) {
             Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return p;
   }
            public boolean modifierReservation(int id,String champs,Object value){
    String   requete = "update client set "+champs+"=?  where id_user=?";
         if(FonctionsPartages.verifierExistanteDuneValeur("client","id_user",id)==true && FonctionsPartages.verifierSiChampExistant("client",champs)==true){
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
            
            
   public void ajouterClient(Client p){
          String requete ="insert into client (id_user,nbr_res_annulee) values (?,?) "; // précomplier
        try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1,p.getId_user());
            pst.setInt(2,p.getNbr_res_annulee());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    public List<Client> afficher(){
          List<Client> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select client.*,user.* from client,user where client.id_user=user.id_user";
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

      public void supprimerClient(int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from client where id_user=?");
           pt.setInt(1,id);
            pt.executeUpdate();
            UserC u=new UserC();
            u.supprimerUser(id);
        } catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
      
        public List<Client> filtrerParInterval(Interval listeInterval){
        
     
     List<Client> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("client", listeInterval.getListeListeInterval());
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
   
   public List<Client> filterSelonDesCritere(Criteres critere){
   List<Client> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("client",critere.getListeCritere());
   
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

   public List<Client> trier(String ordre,String champs){
   List<Client> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"client",champs);
   
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
           
   public List<Client> RechercheAvance(String mot){
   List<Client> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("client",mot);
       System.out.println(requete);
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
       public Client retournerClient(int id){
        try {
               PreparedStatement pt=cn.prepareStatement("select * from client where id_user=?");
           pt.setInt(1,id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()){
              return recupereResultat(rs);
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(ChauffeurC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;
   }
       
       
       
}
