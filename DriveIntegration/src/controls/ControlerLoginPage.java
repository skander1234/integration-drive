/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import models.Entities.User;
import models.Utils.*;
import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.octicons.OctIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Core.*;
import models.Entities.*;
import com.jfoenix.controls.JFXRadioButton;
import com.nexmo.client.NexmoClientException;
import javafx.scene.control.ToggleGroup;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class ControlerLoginPage implements Initializable {
    @FXML
    private Button boutton_inscription;
    @FXML
    private Button bouttonLogin;
    @FXML
    private AnchorPane slideBar;
    @FXML
    private AnchorPane parent;
    @FXML
    private Text titre;
    @FXML
    private TextField user_login;
    @FXML
    private JFXButton btn_continu;
    @FXML
    private PasswordField user_password;
    @FXML
    private JFXButton btn_facebook;
    @FXML
    private JFXButton btn_gmail;
    @FXML
    private Label fond_ocon_login;
    @FXML
    private Label fond_icon;
    @FXML
    private MaterialDesignIconView icon_password;
    @FXML
    private FontAwesomeIconView icon_login;
    @FXML
    private OctIconView left_continu;
    @FXML
    private Text titre2;
    @FXML
    private MaterialDesignIconView icon_password2;
    @FXML
    private TextField user_login2;
    @FXML
    private JFXButton button_continu2;
    @FXML
    private FontAwesomeIconView icone_login2;
    @FXML
    private PasswordField user_password2;
    @FXML
    private PasswordField user_password_confirm;
    @FXML
    private TextField phone_number;
    @FXML
    private Label fond_icone5;
    @FXML
    private Label fond_icone4;
    @FXML
    private Label fond_icone2;
    @FXML
    private MaterialDesignIconView icon_password3;
    @FXML
    private Label fond_icon1;
    @FXML
    private ImageView icon_tunisie;
    @FXML
    private Text indication_pays;
    @FXML
    private StackPane rootpane;
    @FXML
    private ToggleGroup type_user;
    @FXML
    private JFXRadioButton type_client;
    @FXML
    private JFXRadioButton type_chauffeur;
    @FXML
    private TextField code_confirmation;
    
    private int code_aleatoire=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         DropShadow dropShadow = new DropShadow();
 //
 dropShadow.setOffsetX(0);
 dropShadow.setOffsetY(0);
 dropShadow.setColor(Color.web("#515151"));
 slideBar.setEffect(dropShadow);
 code_confirmation.setVisible(false);
 new FadeIn(bouttonLogin).play();
 new FadeIn(titre).play();
 new FadeIn(user_login).play();
 new FadeIn(btn_continu).play();
 new FadeIn(user_password).play();
 new FadeIn(btn_facebook).play();
 new FadeIn(btn_gmail).play();
 new FadeIn(fond_ocon_login).play();
 new FadeIn(fond_icon).play();
 new FadeIn(icon_password).play();
 new FadeIn(icon_login).play();
 new FadeIn(left_continu).play();
    }    

    @FXML
    private void left(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1),slideBar);
        translate.setToX(parent.getLayoutX());
        translate.play();
 
  new FadeIn(bouttonLogin).play();
 new FadeIn(titre).play();
 new FadeIn(user_login).play();
 new FadeIn(btn_continu).play();
 new FadeIn(user_password).play();
 new FadeIn(btn_facebook).play();
 new FadeIn(btn_gmail).play();
 new FadeIn(fond_ocon_login).play();
 new FadeIn(fond_icon).play();
 new FadeIn(icon_password).play();
 new FadeIn(icon_login).play();
 new FadeIn(left_continu).play();
 
   new FadeOut(boutton_inscription).play();
 new FadeOut(titre2).play();
 new FadeOut(icon_password2).play();
 new FadeOut(user_login2).play();
 new FadeOut(button_continu2).play();
 new FadeOut(icone_login2).play();
 new FadeOut(user_password2).play();
 new FadeOut(user_password_confirm).play();
 new FadeOut(phone_number).play();
 new FadeOut(fond_icone5).play();
 new FadeOut(fond_icone4).play();
 new FadeOut(fond_icone2).play();
 new FadeOut(icon_password3).play();
 new FadeOut(fond_icon1).play();
 new FadeOut(icon_tunisie).play();
 new FadeOut(indication_pays).play();
    }
    
    
        public void left() {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1),slideBar);
        translate.setToX(parent.getLayoutX());
        translate.play();
 
  new FadeIn(bouttonLogin).play();
 new FadeIn(titre).play();
 new FadeIn(user_login).play();
 new FadeIn(btn_continu).play();
 new FadeIn(user_password).play();
 new FadeIn(btn_facebook).play();
 new FadeIn(btn_gmail).play();
 new FadeIn(fond_ocon_login).play();
 new FadeIn(fond_icon).play();
 new FadeIn(icon_password).play();
 new FadeIn(icon_login).play();
 new FadeIn(left_continu).play();
 
   new FadeOut(boutton_inscription).play();
 new FadeOut(titre2).play();
 new FadeOut(icon_password2).play();
 new FadeOut(user_login2).play();
 new FadeOut(button_continu2).play();
 new FadeOut(icone_login2).play();
 new FadeOut(user_password2).play();
 new FadeOut(user_password_confirm).play();
 new FadeOut(phone_number).play();
 new FadeOut(fond_icone5).play();
 new FadeOut(fond_icone4).play();
 new FadeOut(fond_icone2).play();
 new FadeOut(icon_password3).play();
 new FadeOut(fond_icon1).play();
 new FadeOut(icon_tunisie).play();
 new FadeOut(indication_pays).play();
    }

    @FXML
    private void right(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1),slideBar);
        translate.setToX(slideBar.getLayoutX() + (parent.getPrefWidth()-slideBar.getPrefWidth()));
        translate.play();
        
 new FadeOut(bouttonLogin).play();
 new FadeOut(titre).play();
 new FadeOut(user_login).play();
 new FadeOut(btn_continu).play();
 new FadeOut(user_password).play();
 new FadeOut(btn_facebook).play();
 new FadeOut(btn_gmail).play();
 new FadeOut(fond_ocon_login).play();
 new FadeOut(fond_icon).play();
 new FadeOut(icon_password).play();
 new FadeOut(icon_login).play();
 new FadeOut(left_continu).play();
 
 new FadeIn(boutton_inscription).play();
 new FadeIn(titre2).play();
 new FadeIn(icon_password2).play();
 new FadeIn(user_login2).play();
 new FadeIn(button_continu2).play();
 new FadeIn(icone_login2).play();
 new FadeIn(user_password2).play();
 new FadeIn(user_password_confirm).play();
 new FadeIn(phone_number).play();
 new FadeIn(fond_icone5).play();
 new FadeIn(fond_icone4).play();
 new FadeIn(fond_icone2).play();
 new FadeIn(icon_password3).play();
 new FadeIn(fond_icon1).play();
 new FadeIn(icon_tunisie).play();
 new FadeIn(indication_pays).play();
    }

    @FXML
    private void forgot_password(MouseEvent event) {
            Stage ot = new Stage();
            BoxBlur blur = new BoxBlur(10,10,10);
                JFXDialogLayout dialogLayout = new JFXDialogLayout();
                JFXDialog dialog = new JFXDialog(rootpane,dialogLayout,JFXDialog.DialogTransition.TOP);
        dialogLayout.setBody(new Label("Pleace wait"));
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1)->{
        ot.close();
        parent.setEffect(null);
        });
        parent.setEffect(blur);
        
                FXMLLoader startFXML = new FXMLLoader(getClass().getResource("/views/FXMLForgotPasswordPage.fxml"));
            Parent root1;
        try {
            root1 = (Parent) startFXML.load();
         ot.setTitle("oui");
            ot.setScene(new Scene(root1));
            ot.initStyle(StageStyle.TRANSPARENT);
        ot.show();
        ot.centerOnScreen(); // permet de centrer la fenetre
        } catch (IOException ex) {
            Logger.getLogger(ControlerLoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
             

    
}


    @FXML
    private void inscription(MouseEvent event) {
        String message=null;
        if(user_login2.getText().replaceAll(" ", "").equals("") || user_password2.getText().replaceAll(" ", "").equals("") || user_password_confirm.getText().replaceAll(" ", "").equals("") || phone_number.getText().replaceAll(" ", "").equals("") || (type_client.isSelected()==false && type_chauffeur.isSelected()==false)){
            message="remplir tout les champs";
        }else{
            if(!user_password2.getText().equals(user_password_confirm.getText())){
              message="la confirmation du password ne correspond pas"; 
            }else{
                String type="";
                if(type_client.isSelected()==true){
                    type="client";
                }else{
                    type="chauffeur";
                }
                
                        if(code_aleatoire==0){
            code_aleatoire=(int) (Math.random() * ( 6000 - 1000 ));
            code_confirmation.setVisible(true);
             String messagesms = "voici votre code de confirmation pour continuer avec drive : "+Integer.toString(code_aleatoire);
                try {
                    models.Utils.FonctionsPartages.sms(93894029, messagesms);
                } catch (NexmoClientException ex) {
                    Logger.getLogger(ControlerLoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
                        
        if(code_confirmation.getText().replaceAll(" ", "").equals(String.valueOf(code_aleatoire))==true){
                 User u = new User(Integer.parseInt(phone_number.getText()),user_login2.getText(),user_password2.getText(),0,user_login2.getText(),type);
                UserC us=new UserC();
                String resultat=us.ajouterUser(u);  
                if(resultat.equals("ok")){
                    code_aleatoire=0;
                    code_confirmation.setVisible(false);
                    message="Operation effectuee avec succee, vous pouvez vous connecter pour continuer";
                    user_login2.setText("");
                    user_password.setText("");
                    user_password2.setText("");
                    phone_number.setText("");
                    left();
                }else{
                    message=resultat;
                }
                }else{
                   if(code_confirmation.isVisible()==true){
                       message="saisir le code de confirmation envoyé par sms";
                   }
                    
                }

                
            


                
            }
        }
        
        String title= "Sign In";
        TrayNotification tray=new TrayNotification();
        if(message==null){
            message="";
        }
        if(message.equals("Operation effectuee avec succee, vous pouvez vous connecter pour continuer")){
        models.Utils.FonctionsPartages.notification(title, message, "success");  
        }else{
          if(message!=null){
              models.Utils.FonctionsPartages.notification(title, message, "warning");  
          }
        }
     }

    @FXML
    private void SeConnecter(MouseEvent event) {
        if(user_login.getText().replaceAll(" ", "").equals("")==false && user_password.getText().replaceAll(" ", "").equals("")==false){
                 String u=user_login.getText();
        String password=MD5.crypt(user_password.getText());
        
        UserC us=new UserC();
        String reponse=us.testerConnexion(password, u);
        if(reponse.equals("")){
            if(test.drive.type_user.equals("client")){
                
          models.Utils.FonctionsPartages.notification("Sign In", "bienvenu "+test.drive.client.getLogin() , "success");          
            }
             if(test.drive.type_user.equals("chauffeur")){
          models.Utils.FonctionsPartages.notification("Sign In", "bienvenu "+test.drive.chauffeur.getLogin() , "success");                  
            }
         ControlerIndexPage.ot.close();
         
                        Parent root;
                     try {
                         root = FXMLLoader.load(getClass().getResource("/views/FXMLIndexPage.fxml"));
                                                 Scene scene = new Scene(root);
                        //        stage.setWidth(1500);
                        //stage.setHeight(950);
                        
                         System.out.println();
                        test.drive.stage.setScene(scene);
                     } catch (IOException ex) {
                         Logger.getLogger(ControlerLoginPage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                       
        }else{
         models.Utils.FonctionsPartages.notification("Sign In", reponse, "warning"); 
        }   
        }else{
          models.Utils.FonctionsPartages.notification("Sign In", "remplir tous les champs", "warning");   
        }
    }
}
