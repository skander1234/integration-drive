/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Core.ChauffeurC;
import models.Core.ClientC;
import models.Core.TaxiC;
import models.Core.UserC;
import test.drive;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLEditionProfilChauffeurController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private TextField Numchassi;
    @FXML
    private TextField login;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField ancienPassword;
    @FXML
    private TextField adresse;
    @FXML
    private TextField cin;
    @FXML
    private TextField permis;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField Phonenumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       new FadeIn(parent).play();
        Phonenumber.setText(String.valueOf(test.drive.chauffeur.getN_tel()));
        login.setText(test.drive.chauffeur.getLogin());
        mail.setText(test.drive.chauffeur.getMail());
        adresse.setText(test.drive.chauffeur.getAdresse());
        cin.setText(String.valueOf(test.drive.chauffeur.getCin()));
        permis.setText(test.drive.chauffeur.getPermis());
        nom.setText(test.drive.chauffeur.getNom());
        prenom.setText(test.drive.chauffeur.getPrenom());
        Numchassi.setText(test.drive.taxi.getNum_chassis());
        
    }    

    @FXML
    private void supprimer(MouseEvent event) {
        
        ChauffeurC u=new ChauffeurC();
        u.supprimerChauffeur(test.drive.chauffeur.getId_user());
                test.drive.type_user="rien";
                        test.drive.client=null;
                        test.drive.chauffeur=null;
                        test.drive.taxi=null;
        
                                Parent root;
                     try {
                         root = FXMLLoader.load(getClass().getResource("/views/FXMLIndexPage.fxml"));
                                                 Scene scene = new Scene(root);
                        new Thread(()->{
            try {
                Thread.sleep(500);
                //ot.hide(); // permet de fermer la fenetre  
                Platform.runLater(()->{
                   
               test.drive.stage.setScene(scene);
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
                                
                                
                     } catch (IOException ex) {
                         Logger.getLogger(ControlerLoginPage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
         models.Utils.FonctionsPartages.notification("Suppression profil","aurevoir ","success"); 
    }


    @FXML
    private void changePhoneNumber(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","n_tel",Integer.parseInt(Phonenumber.getText()))==true){
          models.Utils.FonctionsPartages.notification("Edition profil","le numero exite deja","warning");
      }else{
          UserC u=new UserC();
          test.drive.chauffeur.setN_tel(Integer.parseInt(Phonenumber.getText()));
          u.modifierReservation(test.drive.chauffeur.getId_user(), "n_tel",Integer.parseInt(Phonenumber.getText()));
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succe","success");
      }
    }

    @FXML
    private void changerLogin(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","login",login.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","le login exite deja","warning");
      }else{
          UserC u=new UserC();
          test.drive.chauffeur.setLogin(login.getText());
          u.modifierReservation(test.drive.chauffeur.getId_user(), "login",login.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      }
    }

    @FXML
    private void changerMail(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","mail",mail.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","le mail exite deja","warning");
      }else{
          UserC u=new UserC();
          test.drive.chauffeur.setMail(mail.getText());
          u.modifierReservation(test.drive.chauffeur.getId_user(), "mail",mail.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      }
    }

    @FXML
    private void changerPassword(MouseEvent event) {
        if(models.Utils.MD5.crypt(ancienPassword.getText()).equals(test.drive.chauffeur.getMdp())){

      UserC u=new UserC();
          test.drive.chauffeur.setMdp(models.Utils.MD5.crypt(password.getText()));
          u.modifierReservation(test.drive.chauffeur.getId_user(), "mdp",models.Utils.MD5.crypt(password.getText()));
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      
      
      
        }else{
         models.Utils.FonctionsPartages.notification("Edition profil","La confirmation du mot de passe ne correspond pas","warning");   
        }
    }

    @FXML
    private void changeAdresse(MouseEvent event) {
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setAdresse(adresse.getText());
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "adresse",adresse.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
    }

    @FXML
    private void changeCin(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("chauffeur","cin",cin.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","la cin exite deja","warning");
      }else{
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setCin(Integer.parseInt(cin.getText()));
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "cin",Integer.parseInt(cin.getText()));
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      }
    }

    @FXML
    private void changerPermi(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("chauffeur","permis",permis.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","le permis exite deja","warning");
      }else{
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setPermis(permis.getText());
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "permis",permis.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      }
    }

    @FXML
    private void chaneNom(MouseEvent event) {
     
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setNom(nom.getText());
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "nom",nom.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      
    }

    @FXML
    private void changePrenom(MouseEvent event) {
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setPrenom(prenom.getText());
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "prenom",prenom.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
    }

    @FXML
    private void changeChassi(MouseEvent event) {
          TaxiC u=new TaxiC();
           if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("taxi","num_chassis",Numchassi.getText())==false){
          test.drive.taxi.setNum_chassis(Numchassi.getText());
          u.modifierTaxi(test.drive.taxi.getId_taxi(), "num_chassis",Numchassi.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");      
           }else{
           models.Utils.FonctionsPartages.notification("Edition chassi","ce chassi existe deja","error");    
           }
    }

    @FXML
    private void supprimer_taxi(MouseEvent event) {
        TaxiC u=new TaxiC();
        u.supprimerTaxi(test.drive.taxi.getId_taxi());
        models.Utils.FonctionsPartages.notification("Supprimer taxi","suppression du taxi terminer","success");
    }

    
}
