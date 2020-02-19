/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armand
 */
public class UserC {
     Connection cn =DataSource.getInstance().getConnexion();

   public User recupereResultat(ResultSet rs){
                User p = new User();
         try {
             p.setId_user(rs.getInt(1));
             p.setN_tel(rs.getInt(2));
                p.setLogin(rs.getString(3));
                p.setMdp(rs.getString(4));
                p.setEtat(rs.getInt(5));
                p.setMail(rs.getString(6)); 
         } catch (SQLException ex) {
             Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return p;
   }
   
             public boolean modifierReservation(int id,String champs,Object value){
    String   requete = "update user set "+champs+"=?  where id_user=?";
         if(FonctionsPartages.verifierExistanteDuneValeur("user","id_user",id)==true && FonctionsPartages.verifierSiChampExistant("user",champs)==true){
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
             
             
   public int ajouterUser(User p){
       if(FonctionsPartages.verifierAdresseMail(p.getMail()) && FonctionsPartages.verifierNumeroPhone(String.valueOf(p.getN_tel()))){
           
           if(FonctionsPartages.verifierValeurDunChampsExistant("user", "n_tel", p.getN_tel())==false && FonctionsPartages.verifierValeurDunChampsExistant("user", "login", p.getLogin())==false && FonctionsPartages.verifierValeurDunChampsExistant("user", "mail", p.getMail())==false){
                   
           
            String requete ="insert into user (n_tel,login,mdp,etat,mail) values (?,?,?,?,?) "; // précomplier
       if(p.getEtat()<0 || p.getEtat()>3){
           System.out.println("l'etat doit etre compris entre 0 et 3");
       }else{
            try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1,p.getN_tel());
            pst.setString(2,p.getLogin());
            pst.setString(3,p.getMdp());
            pst.setInt(4,p.getEtat());
            pst.setString(5,p.getMail());
            pst.executeUpdate();
            Criteres c= new Criteres();
            c.ajouterCritere("login",p.getLogin());
            UserC us=new UserC();
            return us.filterSelonDesCritere(c).get(0).getId_user();
        } catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
           
           }else{
               System.out.println("l'utilisateur doit etre unique");
           }
         
       }else{
           System.out.println("adresse mail ou numero incorrect");
       }
         return -1;
    }
    public List<User> afficher(){
          List<User> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select * from user";
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

      public void supprimerUser(int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from user where id_user=?");
           pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
      
   public List<User> filtrerParInterval(Interval listeInterval){
        
     
     List<User> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("user", listeInterval.getListeListeInterval());
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
   
   public List<User> filterSelonDesCritere(Criteres critere){
   List<User> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("user",critere.getListeCritere());
   
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

   public List<User> trier(String ordre,String champs){
   List<User> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"user",champs);
   
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
           
   public List<User> RechercheAvance(String mot){
   List<User> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("user",mot);
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
   
      public User retournerUser(int id){
        try {
               PreparedStatement pt=cn.prepareStatement("select * from user where id_user=?");
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
