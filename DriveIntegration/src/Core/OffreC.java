/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Entities.Event;
import Entities.Location;
import Entities.Offre;
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
 * @author Meriem
 */
public class OffreC {

    Connection cn = DataSource.getInstance().getConnexion();
       public Offre recupereResultat(ResultSet rs){
               Offre e = new Offre();
         try {
                     
                e.setId_offre(rs.getInt(1));
                e.setDate_d(rs.getTimestamp(2));
                e.setDate_f(rs.getTimestamp(3));
                e.setType(rs.getString(4));
                e.setNom(rs.getString(5));
                e.setReduction_offre(rs.getFloat(6));
                if(rs.getString(7) != null){
                    e.setCode_promo(rs.getString(7));
                }
              
         } catch (SQLException ex) {
             Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return e;
   } 
 
  


   public void supprimerOffre(int id) {
        try {
            PreparedStatement pt = cn.prepareStatement("delete from offre where id_offre=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
 
       public boolean modifierEvent(int id,String champs,Object value){
    String   requete = "update offre set "+champs+"=?  where id_offre=?";
         if(FonctionsPartages.verifierExistanteDuneValeur("offre","id_offre",id)==true && FonctionsPartages.verifierSiChampExistant("offre",champs)==true){
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


    public List<Offre> afficherReduction() {
        List<Offre> list = new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
        String requete = "select * from offre  ORDER BY id_offre DESC "; //WHERE reduction_offre is NOT NULL || id_offre,date_d,date_f,type,nom,reduction_offre,code_promo,reduction_promo,prix_offre
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()) {
             list.add(recupereResultat(rs));
             
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    
    /**********************************************************************************/

   /* public float CalculerPrixOffre(Offre o){
        Location l =new Location();
        float new_prix=0;
    if(o.getType().equals("location")){
     new_prix =( l.getPrix() * o.getReduction_offre())/100;
    }
    if(o.getType().equals("reservation")){
     new_prix =( l.getPrix() * o.getReduction_offre())/100;
    }
    return new_prix;
    }*/
    
    
    public void ajouterReduction(Offre o) {
        if(o.getCode_promo() != null){
        if(o.getType().equals("reservation")||o.getType().equals("location"))
        { 
        if(FonctionsPartages.verifierValeurDunChampsExistant("offre", "code_promo", o.getCode_promo())==false){
         String requete = "insert into offre (date_d,date_f,type,nom,reduction_offre,code_promo) values (?,?,?,?,?,?) "; // précomplier
        try {

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setTimestamp(1, (Timestamp) o.getDate_d());
            pst.setTimestamp(2, (Timestamp) o.getDate_f());
            pst.setString(3, o.getType());
            pst.setString(4, o.getNom());
            pst.setFloat(5, o.getReduction_offre());
            pst.setString(6, o.getCode_promo());
     
            pst.executeUpdate();
            System.out.println("Offre ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
                System.out.println("le code promo doit se differencier");
            }
        }else{
                System.out.println("le type estsoit reservation soit location");
            }
        }else{
    if(o.getType().equals("reservation")||o.getType().equals("location"))
        { 
        
         String requete = "insert into offre (date_d,date_f,type,nom,reduction_offre) values (?,?,?,?,?) "; // précomplier
        try {

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setTimestamp(1, (Timestamp) o.getDate_d());
            pst.setTimestamp(2, (Timestamp) o.getDate_f());
            pst.setString(3, o.getType());
            pst.setString(4, o.getNom());
            pst.setFloat(5, o.getReduction_offre());
     
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }else{
                System.out.println("le type estsoit reservation soit location");
            }         
        }
       
    }

    /***************************************************************************/
    

       
      public List<Offre> RechercheAvance(String mot){
   List<Offre> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("offre",mot);
     //  System.out.println(requete);
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
    



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**********************************************************************************************************************************/
         public List<Offre> filtrerParInterval(Interval listeInterval){
        
     
     List<Offre> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("offre", listeInterval.getListeListeInterval());
        try {
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
     }
   public List<Offre> filterSelonDesCritere(Criteres critere){
   List<Offre> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("offre",critere.getListeCritere());
   
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

   public List<Offre> trier(String ordre,String champs){
   List<Offre> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"offre",champs);
   
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
