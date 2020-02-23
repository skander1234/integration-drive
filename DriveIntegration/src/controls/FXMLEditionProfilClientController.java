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
import models.Core.ClientC;
import models.Core.UserC;
import test.drive;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLEditionProfilClientController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField login;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField ancienPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeIn(parent).play();
        phoneNumber.setText(String.valueOf(test.drive.client.getN_tel()));
        login.setText(test.drive.client.getLogin());
        mail.setText(test.drive.client.getMail());
        mail.setText(test.drive.client.getMail());
    }    

    @FXML
    private void changePhoneNumber(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","n_tel",Integer.parseInt(phoneNumber.getText()))==true){
          models.Utils.FonctionsPartages.notification("Edition profil","le numero exite deja","warning");
      }else{
          UserC u=new UserC();
          test.drive.client.setN_tel(Integer.parseInt(phoneNumber.getText()));
          u.modifierReservation(test.drive.client.getId_user(), "n_tel",Integer.parseInt(phoneNumber.getText()));
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succe","success");
      }
    }

    @FXML
    private void changerLogin(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","login",login.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","le login exite deja","warning");
      }else{
          UserC u=new UserC();
          test.drive.client.setLogin(login.getText());
          u.modifierReservation(test.drive.client.getId_user(), "login",login.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      }
    }

    @FXML
    private void changerMail(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","mail",mail.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","le mail exite deja","warning");
      }else{
          UserC u=new UserC();
          test.drive.client.setMail(mail.getText());
          u.modifierReservation(test.drive.client.getId_user(), "mail",mail.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      }
    }

    @FXML
    private void changerPassword(MouseEvent event) {
        if(models.Utils.MD5.crypt(ancienPassword.getText()).equals(test.drive.client.getMdp())){

      UserC u=new UserC();
          test.drive.client.setMdp(models.Utils.MD5.crypt(password.getText()));
          u.modifierReservation(test.drive.client.getId_user(), "mdp",models.Utils.MD5.crypt(password.getText()));
          models.Utils.FonctionsPartages.notification("Edition profil","modification effectuer avec succee","success");
      
      
      
        }else{
         models.Utils.FonctionsPartages.notification("Edition profil","La confirmation du mot de passe ne correspond pas","warning");   
        }
    }

    @FXML
    private void supprimer(MouseEvent event) {
        
        ClientC u=new ClientC();
        u.supprimerClient(test.drive.client.getId_user());
                test.drive.type_user="rien";
                        test.drive.client=null;
                        test.drive.chauffeur=null;
        
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
    
}
