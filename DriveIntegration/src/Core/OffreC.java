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
                e.setCode_promo(rs.getString(7));
                e.setReduction_promo(rs.getFloat(8));
                e.setPrix_offre(rs.getFloat(9));
         } catch (SQLException ex) {
             Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return e;
   } 
  public Offre recupereResultatReduction(ResultSet rs){
               Offre e = new Offre();
         try {
                     
                e.setId_offre(rs.getInt(1));
                e.setDate_d(rs.getTimestamp(2));
                e.setDate_f(rs.getTimestamp(3));
                e.setType(rs.getString(4));
                e.setNom(rs.getString(5));
                e.setReduction_offre(rs.getFloat(6));
             //   e.setCode_promo(rs.getString(7));
           //     e.setReduction_promo(rs.getFloat(8));
                e.setPrix_offre(rs.getFloat(7));
         } catch (SQLException ex) {
             Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return e;
   }
  
    public Offre recupereResultaPromo(ResultSet rs){
               Offre e = new Offre();
         try {
                     
                e.setId_offre(rs.getInt(1));
                e.setDate_d(rs.getTimestamp(2));
                e.setDate_f(rs.getTimestamp(3));
                e.setType(rs.getString(4));
                e.setNom(rs.getString(5));
           //     e.setReduction_offre(rs.getFloat(6));
                e.setCode_promo(rs.getString(6));
                e.setReduction_promo(rs.getFloat(7));
                e.setPrix_offre(rs.getFloat(8));
         } catch (SQLException ex) {
             Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return e;
   }

    public void ajouterReduction(Offre o) {
        String requete = "insert into offre (date_d,date_f,type,nom,reduction_offre,prix_offre) values (?,?,?,?,?,?) "; // précomplier
        try {

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setTimestamp(1, (Timestamp) o.getDate_d());
            pst.setTimestamp(2, (Timestamp) o.getDate_f());
            pst.setString(3, o.getType());
            pst.setString(4, o.getNom());
            pst.setFloat(5, o.getReduction_offre());
            pst.setFloat(6, o.getPrix_offre());
            pst.executeUpdate();
            System.out.println("Reduction ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void ModifierReduction(int id, Timestamp date_allee, Timestamp date_retour, String type, String Nom, float reduction_offre, float prix_offre) {

        String requete = "update offre set date_d=?, date_f=?,type=?,nom=?, reduction_offre=?, prix_offre=?  where id_offre=?";
        try {
            PreparedStatement pt = cn.prepareStatement(requete);

            pt.setTimestamp(1, date_allee);
            pt.setTimestamp(2, date_retour);
            pt.setString(3, type);
            pt.setString(4, Nom);
            pt.setFloat(5, reduction_offre);
            pt.setFloat(6, prix_offre);
            pt.setInt(7, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public List<Offre> afficherReduction() {
        List<Offre> list = new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
        String requete = "select id_offre,date_d,date_f,type,nom,reduction_offre,prix_offre from offre WHERE code_promo is  NULL ORDER BY id_offre DESC "; //WHERE reduction_offre is NOT NULL || id_offre,date_d,date_f,type,nom,reduction_offre,code_promo,reduction_promo,prix_offre
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()) {
             list.add(recupereResultatReduction(rs));
             
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    
    /**********************************************************************************/
       public List<Offre> afficherPromo() {
        List<Offre> list = new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
        String requete = "select id_offre,date_d,date_f,type,nom,code_promo,reduction_promo,prix_offre from offre WHERE code_promo is NOT NULL ORDER BY id_offre DESC "; //WHERE reduction_offre is NOT NULL || id_offre,date_d,date_f,type,nom,reduction_offre,code_promo,reduction_promo,prix_offre
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()) {
             
             
                list.add(recupereResultaPromo(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    public float CalculerPrixOffre(Offre o){
        Location l =new Location();
        float new_prix=0;
    if(o.getType().equals("location")){
     new_prix =( l.getPrix() * o.getReduction_offre())/100;
    }
    if(o.getType().equals("reservation")){
     new_prix =( l.getPrix() * o.getReduction_offre())/100;
    }
    return new_prix;
    }
    
    
    public void ajouterCodePromo(Offre o) {
        String requete = "insert into offre (date_d,date_f,type,nom,code_promo,reduction_promo,prix_offre) values (?,?,?,?,?,?,?) "; // précomplier
        try {

            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setTimestamp(1, (Timestamp) o.getDate_d());
            pst.setTimestamp(2, (Timestamp) o.getDate_f());
            pst.setString(3, o.getType());
            pst.setString(4, o.getNom());
            pst.setString(5, o.getCode_promo());
            pst.setFloat(6, o.getReduction_promo());
            pst.setFloat(7, o.getPrix_offre());
            pst.executeUpdate();
            System.out.println("Code Promo ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierCodePromo(int id, Timestamp date_allee, Timestamp date_retour, String type, String Nom,String code_promo, float reduction_promo, float prix_offre) {

        String requete = "update offre set date_d=?, date_f=?,type=?,nom=?,code_promo=?, reduction_promo=?, prix_offre=?  where id_offre=?";
        try {
            PreparedStatement pt = cn.prepareStatement(requete);

            pt.setTimestamp(1, date_allee);
            pt.setTimestamp(2, date_retour);
            pt.setString(3, type);
            pt.setString(4, Nom);
            pt.setString(5, code_promo);
            pt.setFloat(6, reduction_promo);
            pt.setFloat(7, prix_offre);
            pt.setInt(8, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OffreC.class.getName()).log(Level.SEVERE, null, ex);
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
