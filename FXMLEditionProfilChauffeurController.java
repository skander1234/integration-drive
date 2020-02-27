/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import static controls.ControlerIndexPage.jun_copy;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import models.Core.ChauffeurC;
import models.Core.ClientC;
import models.Core.TaxiC;
import models.Core.UserC;
import models.Entities.Taxi;
import models.Utils.Criteres;
import rest.file.uploader.tn.FileUploader;
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
    @FXML
    private Button cdf;
    
    public static Stage stageCamera;
    @FXML
    private ImageView imageProfil;
    
    @FXML
    private ImageView imageTaxi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TaxiC tc=new TaxiC();
            Criteres c=new Criteres();
    c.ajouterCritere("id_chauffeur", test.drive.chauffeur.getId_user());
    for(Taxi vt:tc.filterSelonDesCritere(c)){
        test.drive.taxi=vt;
    }
       new FadeIn(parent).play();

       if(test.drive.taxi!=null){
           Image image = new Image("http://localhost/drive/uploads/"+test.drive.taxi.getPhoto());
    imageTaxi.setImage(image);            
       }
       if(!test.drive.chauffeur.getPhoto().replaceAll(" ","").equals("")){
           Image image = new Image("http://localhost/drive/uploads/"+test.drive.chauffeur.getPhoto());
    imageProfil.setImage(image); 
       }
               
                   password.setEditable(false);
            cdf.setVisible(false);
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
          models.Utils.FonctionsPartages.notification("Edition profil","number already exists","warning");
      }else{
          if(Phonenumber.getText().length()!= 8){
                   models.Utils.FonctionsPartages.notification("erreur PHONENUMBER", " phonenumber incorrect ", "warning");
               }
                else{
          UserC u=new UserC();
          test.drive.chauffeur.setN_tel(Integer.parseInt(Phonenumber.getText()));
          u.modifierUser(test.drive.chauffeur.getId_user(), "n_tel",Integer.parseInt(Phonenumber.getText()));
          models.Utils.FonctionsPartages.notification("Edition profil","modification with success","success");
      }
    }
    }
    @FXML
    private void changerLogin(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","login",login.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","login already exists","warning");
      }else{
          UserC u=new UserC();
          test.drive.chauffeur.setLogin(login.getText());
          u.modifierUser(test.drive.chauffeur.getId_user(), "login",login.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification with success","success");
      }
    }

    @FXML
    private void changerMail(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("user","mail",mail.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","mail already exists","warning");
      }else{
          if(models.Utils.FonctionsPartages.verifierAdresseMail(mail.getText())){
            models.Utils.FonctionsPartages.notification("Bravo mail", "correct mail", "success");
          UserC u=new UserC();
          test.drive.chauffeur.setMail(mail.getText());
          u.modifierUser(test.drive.chauffeur.getId_user(), "mail",mail.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification with success","success");
        }
          else{
              models.Utils.FonctionsPartages.notification("Erreur mail", "error mail", "warning");
          }
    }
    }
    @FXML
    private void changerPassword(MouseEvent event) {
        if(models.Utils.MD5.crypt(ancienPassword.getText()).equals(test.drive.chauffeur.getMdp())){

      UserC u=new UserC();
          test.drive.chauffeur.setMdp(models.Utils.MD5.crypt(password.getText()));
          u.modifierUser(test.drive.chauffeur.getId_user(), "mdp",models.Utils.MD5.crypt(password.getText()));
          models.Utils.FonctionsPartages.notification("Edition profil","modification done successfully","success");
      
      
      
        }else{
         models.Utils.FonctionsPartages.notification("Edition profil","password incorrect","warning");   
        }
    }

    @FXML
    private void changeAdresse(MouseEvent event) {
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setAdresse(adresse.getText());
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "adresse",adresse.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification done successfully","success");
    }

    @FXML
    private void changeCin(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("chauffeur","cin",cin.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","cin already exists","warning");
      }else{
          if(cin.getText().length()!= 8){
                   models.Utils.FonctionsPartages.notification("erreur CIN", " cin erreur ", "warning");
               }
                else{
                   models.Utils.FonctionsPartages.notification("ok ok  CIN", " cin correct ", "success");
                    ChauffeurC u=new ChauffeurC();
                    test.drive.chauffeur.setCin(Integer.parseInt(cin.getText()));
                    u.modifierChauffeur(test.drive.chauffeur.getId_user(), "cin",Integer.parseInt(cin.getText()));
                    models.Utils.FonctionsPartages.notification("Edition profil","modification done successfully","success");

          }
       }
    }

    @FXML
     private void changerPermi(MouseEvent event) {
      if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("chauffeur","permis",permis.getText())==true){
          models.Utils.FonctionsPartages.notification("Edition profil","permis already exists","warning");
      }else{
          if(models.Utils.FonctionsPartages.verifierPermis(permis.getText())){
            models.Utils.FonctionsPartages.notification("Bravo Permis", "bravo correct", "success");
            ChauffeurC u=new ChauffeurC();
            test.drive.chauffeur.setPermis(permis.getText());
            u.modifierChauffeur(test.drive.chauffeur.getId_user(), "permis",permis.getText());
            models.Utils.FonctionsPartages.notification("Edition profil","modification done successfully","success");
          }
                else{
                   models.Utils.FonctionsPartages.notification("Erreur Permis", "erreur permis", "warning");
               }
          }
    }

    @FXML
    private void chaneNom(MouseEvent event) {
     
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setNom(nom.getText());
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "nom",nom.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification done successfully","success");
      
    }

    @FXML
    private void changePrenom(MouseEvent event) {
          ChauffeurC u=new ChauffeurC();
          test.drive.chauffeur.setPrenom(prenom.getText());
          u.modifierChauffeur(test.drive.chauffeur.getId_user(), "prenom",prenom.getText());
          models.Utils.FonctionsPartages.notification("Edition profil","modification done successfully","success");
    }

    @FXML
    private void changeChassi(MouseEvent event) {
          TaxiC u=new TaxiC();
           if( models.Utils.FonctionsPartages.verifierExistanteDuneValeur("taxi","num_chassis",Numchassi.getText())==false){
               if(Numchassi.getText().length() != 13){
                   models.Utils.FonctionsPartages.notification("Erreur Chassis", "error chassis", "warning");                   
               }
               else{
                    models.Utils.FonctionsPartages.notification("Bravo Chassis", "correct Chassis", "success");
                    test.drive.taxi.setNum_chassis(Numchassi.getText());
                    u.modifierTaxi(test.drive.taxi.getId_taxi(), "num_chassis",Numchassi.getText());
                    models.Utils.FonctionsPartages.notification("Edition profil","modification done successfully","success");      
               }
          }else{
           models.Utils.FonctionsPartages.notification("Edition chassi"," chassi already exists","error");    
           }
    }

    @FXML
    private void supprimer_taxi(MouseEvent event) {
        TaxiC u=new TaxiC();
        u.supprimerTaxi(test.drive.taxi.getId_taxi());
        models.Utils.FonctionsPartages.notification("Supprimer taxi","done","success");
    }

    @FXML
    private void verifierPass(KeyEvent event) {
                        String pass=ancienPassword.getText();
        if(models.Utils.MD5.crypt(ancienPassword.getText()).equals(test.drive.chauffeur.getMdp())){
            password.setEditable(true);
            cdf.setVisible(true);
        }else{
            password.setEditable(false);
            cdf.setVisible(false);
        }
    }

    @FXML
    private void changerPhototProfilUser(MouseEvent event) throws IOException {
         stageCamera = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/views/camera.fxml"));
        
        Scene scene = new Scene(root);
        
        stageCamera.setScene(scene);
        stageCamera.show();
    }

    @FXML
    private void changertaxi(MouseEvent event) throws ProtocolException, IOException {
                JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(null);
    File f = chooser.getSelectedFile();
    String fileName=f.getAbsolutePath();
    FileUploader fu=new FileUploader("localhost/drive");
    String fileNameInServer = fu.upload(fileName);
    Image image = new Image("http://localhost/drive/uploads/"+fileNameInServer);
    TaxiC tc=new TaxiC();
    test.drive.taxi.setPhoto(fileName);
    tc.modifierTaxi(test.drive.taxi.getId_taxi(), "photo", fileNameInServer);
    Parent root;
                               root = FXMLLoader.load(getClass().getResource("/views/FXMLEditionProfilChauffeur.fxml"));
                       jun_copy.setCenter(root);
    }

    
}
