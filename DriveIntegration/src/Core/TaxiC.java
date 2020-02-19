/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;


import Entities.Chauffeur;
import Entities.Taxi;
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
 * @author Mejbri Skander
 */
public class TaxiC {
       Connection cn =DataSource.getInstance().getConnexion();
           public Taxi recupereResultat(ResultSet rs){
                Taxi p = new Taxi();
         try {
                p.setId_taxi(rs.getInt(1));
                ChauffeurC ch=new ChauffeurC();
                p.setChauffeur(ch.retournerChauffeur(rs.getInt(2)));
                p.setPhoto(rs.getString(3));
                p.setNum_chassis(rs.getString(4));
         } catch (SQLException ex) {
             Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return p;
   }
   public void ajouterTaxi(Taxi t){
       
       if(t.getNum_chassis().length()==13)
       {
           if(FonctionsPartages.verifierValeurDunChampsExistant("taxi", "num_chassis", t.getNum_chassis())==false && FonctionsPartages.verifierValeurDunChampsExistant("taxi", "id_chauffeur", t.getChauffeur().getId_user())==false){
        String requete ="insert into taxi(id_chauffeur,photo,num_chassis) values (?,?,?) ";
        try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1,t.getChauffeur().getId_user());
            pst.setString(2,t.getPhoto());
            pst.setString(3,t.getNum_chassis());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaxiC.class.getName()).log(Level.SEVERE, null, ex);
        }          
           }else{
               System.out.println("le numero de chassi ou bien le chauffeur possede deja une voiture");
           }
       }else{
           System.out.println("num chassi incorrect de saisie");
       }
        
         
    }
    public List<Taxi> afficher(){
          List<Taxi> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select * from taxi";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                             list.add(recupereResultat(rs));

            }
        }
         catch (SQLException ex) {
            Logger.getLogger(TaxiC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
           
    }
    
       public boolean modifierTaxi(int id,String champs,Object value){
    String   requete = "update taxi set "+champs+"=?  where id_taxi=?";
         if(FonctionsPartages.verifierExistanteDuneValeur("taxi","id_taxi",id)==true && FonctionsPartages.verifierSiChampExistant("taxi",champs)==true){
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
       
      public void supprimerTaxi( int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from taxi where id_taxi=?");
           pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaxiC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
       public List<Taxi> filtrerParInterval(Interval listeInterval){
        
     
     List<Taxi> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("taxi", listeInterval.getListeListeInterval());
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
   
   public List<Taxi> filterSelonDesCritere(Criteres critere){
   List<Taxi> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("taxi",critere.getListeCritere());
   
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

   public List<Taxi> trier(String ordre,String champs){
   List<Taxi> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"taxi",champs);
   
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
          public List<Taxi> RechercheAvance(String mot){
   List<Taxi> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("taxi",mot);
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

      public Taxi retournerTaxi(int id){
        try {
               PreparedStatement pt=cn.prepareStatement("select * from taxi where id_taxi=?");
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
