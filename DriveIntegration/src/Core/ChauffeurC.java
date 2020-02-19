/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;


import Entities.Chauffeur;
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
 * @author Belgaroui Ghazi
 */
public class ChauffeurC  {
       Connection cn =DataSource.getInstance().getConnexion();
       
       public Chauffeur recupereResultat(ResultSet rs){
                Chauffeur p = new Chauffeur();
         try {
             p.setId_user(rs.getInt(1));
             UserC us= new UserC();
             User user= us.retournerUser(rs.getInt(1));
             p.setN_tel(user.getN_tel());
             p.setLogin(user.getLogin());
             p.setMail(user.getMail());
             p.setEtat(user.getEtat());
             p.setMdp(user.getMdp());
             p.setAdresse(rs.getString(2));
             p.setCin(rs.getInt(3));
             p.setPermis(rs.getString(4));
             p.setNom(rs.getString(5));
             p.setPrenom(rs.getString(6));
         } catch (SQLException ex) {
             Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return p;
   }
   public void ajouterChauffeur(Chauffeur p){
       if(Utils.FonctionsPartages.verifierCin(p.getCin())==true && Utils.FonctionsPartages.verifierPermis(p.getPermis())==true){
        
           if(FonctionsPartages.verifierValeurDunChampsExistant("chauffeur", "cin", p.getCin())==false && FonctionsPartages.verifierValeurDunChampsExistant("chauffeur", "permis", p.getPermis())==false){
          String requete ="insert into chauffeur(id_user,adresse,cin,permis,nom,prenom) values (?,?,?,?,?,?) ";
        try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1,p.getId_user());
            pst.setString(2,p.getAdresse());
            pst.setInt(3,p.getCin());
            pst.setString(4,p.getPermis());
            pst.setString(5,p.getNom());
            pst.setString(6,p.getPrenom());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurC.class.getName()).log(Level.SEVERE, null, ex);
        }      
           }else{
               System.out.println("le chauffeur doit etre unique");
           }
           
          
       }else{
           
           System.out.println("cin ou permis incorrect");
           
       }
         
         
    }
    public List<Chauffeur> afficher(){
          List<Chauffeur> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select * from chauffeur";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
              list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(ChauffeurC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
           
    }

       public boolean modifierChauffeur(int id,String champs,Object value){
    String   requete = "update chauffeur set "+champs+"=?  where id_user=?";
         if(FonctionsPartages.verifierExistanteDuneValeur("chauffeur","id_user",id)==true && FonctionsPartages.verifierSiChampExistant("chauffeur",champs)==true){
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
                 System.out.println("tyut");
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
      public void supprimerChauffeur( int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from chauffeur where id_user=?");
           pt.setInt(1,id);
            pt.executeUpdate();
            UserC us=new UserC();
            us.supprimerUser(id);
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
       public List<Chauffeur> filtrerParInterval(Interval listeInterval){
        
     
     List<Chauffeur> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("chauffeur", listeInterval.getListeListeInterval());
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
   
   public List<Chauffeur> filterSelonDesCritere(Criteres critere){
   List<Chauffeur> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("chauffeur",critere.getListeCritere());
   
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

   public List<Chauffeur> trier(String ordre,String champs){
   List<Chauffeur> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"chauffeur",champs);
   
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
    public List<Chauffeur> RechercheAvance(String mot){
   List<Chauffeur> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("chauffeur",mot);
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
    
   public Chauffeur retournerChauffeur(int id){
        try {
               PreparedStatement pt=cn.prepareStatement("select * from chauffeur where id_user=?");
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
