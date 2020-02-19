/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;
import Entities.Velo;
import Utils.Criteres;
import Utils.DataSource;
import Utils.Interval;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Belgaroui Ghazi
 */
public class VeloC {
    
    Connection cn =DataSource.getInstance().getConnexion();
     public Velo recupereResultat(ResultSet rs){
                Velo p = new Velo();
         try {
             p.setId(rs.getInt(1));
                p.setType(rs.getString(2));
                p.setAdresse(rs.getString(3));
                p.setQte(rs.getInt(4));
                p.setPhoto(rs.getString(5));
                p.setPrix(rs.getFloat(6));
         } catch (SQLException ex) {
             Logger.getLogger(UserC.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
                
                return p;
   }
   public void ajouterVelo(Velo p){
          String requete ="insert into velo(type,adresse,qte,photo,prix) values (?,?,?,?,?) "; // précomplier
        try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1,p.getType());
            pst.setString(2,p.getAdresse());
            pst.setInt(3,p.getQte());
            pst.setString(4,p.getPhoto());
            pst.setFloat(5,p.getPrix());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeloC.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    public List<Velo> afficher(){
          List<Velo> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select * from velo";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
              
                list.add(recupereResultat(rs));
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(VeloC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
           
    }
     public void modifierVelo(int id, String type, String adresse,int qte,String photo,float prix) {
       
       String   requete = "update velo set type=?, adresse=?,qte=?,photo=?,prix=?  where id=?";
         try {
            PreparedStatement pt= cn.prepareStatement(requete);
            pt.setString(1,type);
            pt.setString(2, adresse);
            pt.setInt(3, qte);
            pt.setString(4, photo);
            pt.setFloat(5, prix);
            pt.setInt(6, id);
             System.out.println(id+type+adresse+qte+photo);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeloC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void supprimerVelo( int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from velo where id=?");
           pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VeloC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
       public List<Velo> rechercher(String type)
     {
         List<Velo> list= new ArrayList<>();
           try {
            PreparedStatement pt=cn.prepareStatement("select * from velo where type=?");
            pt.setString(1,type);
            ResultSet rs= pt.executeQuery();
            int i=0;
            while (rs.next()){
                Velo p = new Velo();
                p.setId(rs.getInt(1));
                p.setType(rs.getString(2));
                p.setAdresse(rs.getString(3));
                p.setQte(rs.getInt(4));
                p.setPhoto(rs.getString(5));
                p.setPrix(rs.getFloat(6));
                list.add(p);
                i++;
            }
            
            if(i==0) {System.out.println("aucune reponse");}
            else{System.out.println("voila");}
               
            
          } catch (SQLException ex) {
            Logger.getLogger(VeloC.class.getName()).log(Level.SEVERE, null, ex);
        }
           return list;
     }
       public List<Velo> filtrerParInterval(Interval listeInterval){
        
     
     List<Velo> list =new ArrayList<>();
          String requete = Utils.FonctionsPartages.genererRequetteInterval("velo", listeInterval.getListeListeInterval());
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
         public List<Velo> filterSelonDesCritere(Criteres critere){
   List<Velo> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteTrie("velo",critere.getListeCritere());
   
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
         public List<Velo> trier(String ordre,String champs){
   List<Velo> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequettetrier(ordre,"velo",champs);
   
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
          public List<Velo> RechercheAvance(String mot){
   List<Velo> list =new ArrayList<>();
   String requete=Utils.FonctionsPartages.genererRequetteRechercherAvancer("velo",mot);
  
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
}