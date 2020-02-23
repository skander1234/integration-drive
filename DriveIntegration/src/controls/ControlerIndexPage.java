/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import test.drive;
import static test.drive.stage;
import javax.swing.Timer;
import models.Entities.*;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class ControlerIndexPage implements Initializable {
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    @FXML
    private JFXButton btn_try;
    @FXML
    private ImageView imgf;
    @FXML
    private AnchorPane top_bar;
    
    private double x,y=0;
    @FXML
    private Rectangle head2;
    @FXML
    private Label close;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane rootAnchorPane;
    @FXML
    private ImageView show_image;
    @FXML
    private ImageView img_function;
    @FXML
    private ImageView imgf1;
    @FXML
    private ImageView fond_dispa;
    @FXML
    private AnchorPane menu;
    
    private int nbr_clickMenuDrive,nbr_clickReservatio,nbr_clickLocation,menu_active=0;
    @FXML
    private AnchorPane list_menu_drive;
    @FXML
    private Button button_reservation;
    @FXML
    private AnchorPane rte;
    @FXML
    private BorderPane jun;
    public static BorderPane jun_copy;
    @FXML
    private AnchorPane list_menu_reservation;
    @FXML
    private AnchorPane list_menu_location;
    @FXML
    private Button button_drive;
    @FXML
    private Button button_location;
    @FXML
    private Button button_avis;
    @FXML
    private Button button_reclamation;
    
    @FXML
    private FontAwesomeIconView icone_btn_reservation;
    @FXML
    private AnchorPane menuAdmin;
    @FXML
    private Button button_drive1;
    @FXML
    private Button button_reservation1;
    @FXML
    private Button button_location1;
    @FXML
    private AnchorPane list_menu_reservation1;
    @FXML
    private AnchorPane list_menu_location1;
    @FXML
    private AnchorPane list_menu_drive1;
    @FXML
    private Button button_avis11;
    @FXML
    private Button button_livraison;
    @FXML
    private Button button_deplacement;
    @FXML
    private Button buttonlouer_velo;
    @FXML
    private Button button_consulter_location;
    @FXML
    private Button button_index;
    @FXML
    private Button button_service;
    @FXML
    private Button button_about;
    @FXML
    private Button button_ajouter_evenement;
    @FXML
    private Button button_afficher_evenement;
    @FXML
    private Button button_ajouter_offre;
    @FXML
    private Button button_afficher_offre;
    @FXML
    private Button button_ajouter_velo;
    @FXML
    private Button button_consulter_velo;
    @FXML
    private Button button_traiter_location;
    @FXML
    private Button button_traiter_reclamation;
    public static Stage ot;
    @FXML
    private Button button_edition_profil;
    @FXML
    private FontAwesomeIconView lst_user;
    @FXML
    private AnchorPane menu_edition;
    
    private int ClickButtonAfficherProfil=0;
    @FXML
    private Button afficherClient;
    @FXML
    private Button afficherChauffeur;
    public static String rechercher="";
    @FXML
    private FontAwesomeIconView btn_men;
    
    
    public ControlerIndexPage() {
    }

    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(test.drive.type_user.equals("rien")){
            btn_men.setVisible(false);
        }
        jun_copy=jun;
               if(!test.drive.type_user.equals("rien")){
                   btn_try.setVisible(false);
               }
        button_reservation.setLayoutY(125);
        button_location.setLayoutY(175);
        button_avis.setLayoutY(225);
        button_reclamation.setLayoutY(275);
        
        new BounceIn(show_image).setDelay(new Duration(500)).play();
        new FadeInRight(show_image).setDelay(new Duration(300)).play();
        fond_dispa.setVisible(false);
        menu.setVisible(false);
        menuAdmin.setVisible(false);
        new FadeOut(fond_dispa).play();
        new FadeOut(menu).play();
        new FadeOut(menuAdmin).play();
        new BounceIn(btn_try).setCycleDuration(9000).play();
        new FadeOut(list_menu_location).play();
        new FadeOut(list_menu_reservation).play();
        new FadeOut(list_menu_drive).play();
        list_menu_drive.setVisible(false);
        list_menu_reservation.setVisible(true);
        list_menu_location.setVisible(true);
        
        String path= new File("src/views/video/background.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        button_edition_profil.setVisible(false);
        new FadeOut(menu_edition).play();
        menu_edition.setVisible(false);
                         if(!test.drive.type_user.equals("rien")){
         String pg="";
                        lst_user.setVisible(false);
                        menu_edition.setVisible(false);
                         if(test.drive.type_user.equals("client")){
                             pg="/views/FXMLIndexClientPage.fxml";
                             System.out.println("ici111111111111111");
                         }
                         if(test.drive.type_user.equals("chauffeur")){
                             pg="/views/FXMLReservationCoteChauffeur.fxml";
                             System.out.println("ici1222222222222222");
                         }
                         if(test.drive.type_user.equals("admin")){
                             pg="/views/FXMLDashboardPage.fxml";
                             System.out.println("ici3333333333333");
                         }      
                         button_edition_profil.setVisible(true);
                         Parent root;
                   try {
                       root = FXMLLoader.load(getClass().getResource(pg));
                       jun_copy.setCenter(root);
                   } catch (IOException ex) {
                       Logger.getLogger(ControlerIndexPage.class.getName()).log(Level.SEVERE, null, ex);
                   }
                        
                         }

        
        
        new BounceIn(btn_try).play();
        drag();
        
         DropShadow dropShadow = new DropShadow();
 //
 dropShadow.setOffsetX(3.0);
 dropShadow.setOffsetY(3.0);
 dropShadow.setColor(Color.web("#282828"));
 top_bar.setEffect(dropShadow);
 if(test.drive.type_user.equals("rien")){
     type_rien();
 }
 if(test.drive.type_user.equals("client")){
     type_client();
 }
 if(test.drive.type_user.equals("chauffeur")){
     type_chauffeur();
 }
    
    }    

    private void type_rien(){
     button_reclamation.setVisible(false);
     button_avis.setVisible(false);
     button_location.setVisible(false);
     button_reservation.setVisible(false);
     list_menu_location.setVisible(false);
     list_menu_reservation.setVisible(false);
     button_index.setVisible(false);
    }
    
    private void type_chauffeur(){
     button_reclamation.setVisible(false);
     icone_btn_reservation.setVisible(false);
     button_location.setVisible(false); 
     list_menu_reservation.setVisible(false);
     list_menu_location.setVisible(false);
     button_index.setVisible(true);
    }
    private void type_client(){
     button_reclamation.setVisible(true);
     icone_btn_reservation.setVisible(true);
     button_location.setVisible(true); 
     button_index.setVisible(true);
    }
@FXML
    private void close(MouseEvent event) {
        System.exit(1);
    }
    private void drag(){
    top_bar.setOnMousePressed((event)->{
        x=event.getSceneX();
        y=event.getSceneY();
    });
    top_bar.setOnMouseDragged((event)->{
        drive.stage.setX(event.getScreenX()-x);
        drive.stage.setY(event.getScreenY()-y);
        drive.stage.setOpacity(0.9f);
    });
    top_bar.setOnDragDone((even)->{
        drive.stage.setOpacity(1.0f);
    });
    top_bar.setOnMouseReleased((event)->{
        drive.stage.setOpacity(1.0f);
    });
    }    

    @FXML
    private void login(MouseEvent event) {
            ot = new Stage();
            BoxBlur blur = new BoxBlur(10,10,10);
                JFXDialogLayout dialogLayout = new JFXDialogLayout();
                JFXDialog dialog = new JFXDialog(rootAnchorPane,dialogLayout,JFXDialog.DialogTransition.TOP);
        dialogLayout.setBody();
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1)->{
        ot.close();
        rootAnchorPane.setEffect(null);
        });
        rootAnchorPane.setEffect(blur);
        
              FXMLLoader startFXML = new FXMLLoader(getClass().getResource("/views/FXMLLoginPage.fxml"));
            Parent root1;
        try {
            root1 = startFXML.load();
             
        ot.setTitle("oui");
            ot.setScene(new Scene(root1));
            ot.initStyle(StageStyle.TRANSPARENT);
        ot.show();
        ot.centerOnScreen(); // permet de centrer la fenetre
        
        /*new Thread(()->{
            try {
                Thread.sleep(5500);
                //ot.hide(); // permet de fermer la fenetre  
                Platform.runLater(()->{
                    ot.close();
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();*/
        } catch (IOException ex) {
            Logger.getLogger(ControlerIndexPage.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    
    }

    @FXML
    private void show_menu(MouseEvent event) {
    fond_dispa.setVisible(true);
    new FadeIn(fond_dispa).play();
    if(test.drive.type_user.equals("client") || test.drive.type_user.equals("chauffeur")){
            menu.setVisible(true);
    new FadeInLeft(menu).play();
    }
    if(test.drive.type_user.equals("admin")){
            menuAdmin.setVisible(true);
    new FadeInLeft(menuAdmin).play();        
    }

    }

    @FXML
    private void hide_menu(MouseEvent event) {
    new FadeOut(fond_dispa).play();
    new FadeOutLeft(menu).play();
    new FadeOutLeft(menuAdmin).play();
    
    Timer timer = new Timer(800, new ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
                            fond_dispa.setVisible(false);                   
               menu.setVisible(false);
        }

});
timer.setRepeats(false); // Only execute once
timer.start(); // Go go go!
}

    @FXML
    private void click_menu_drive(MouseEvent event) {
        button_drive.getStyleClass().add("parentActive");
        while(button_avis.getStyleClass().remove("parentActive")==true){}
        while(button_reclamation.getStyleClass().remove("parentActive")==true){};
        while(button_location.getStyleClass().remove("parentActive")==true){};
        while(button_reservation.getStyleClass().remove("parentActive")==true){};
        menu_active=0;
        
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1),button_reservation);
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(1),button_location);
        TranslateTransition translate3 = new TranslateTransition(Duration.seconds(1),button_avis);
        TranslateTransition translate4 = new TranslateTransition(Duration.seconds(1),button_reclamation);
        if((nbr_clickMenuDrive%2==0 && menu_active==0) ||  (nbr_clickReservatio%2!=0 && menu_active!=0)){
               
            
            if(nbr_clickLocation%2==1 || nbr_clickReservatio%2==1){
                        if(nbr_clickLocation%2==1){
            new FadeOut(list_menu_location).setDelay(new Duration(100)).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_location.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();
                    
                    
            nbr_clickLocation++;
            }
            if(nbr_clickReservatio%2==1){
            new FadeOut(list_menu_reservation).setDelay(new Duration(100)).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_reservation.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            nbr_clickReservatio++;
            }
            
            translate.setToY(53*3);       
            translate2.setToY(53*3);  
            translate3.setToY(53*3);  
            translate4.setToY(53*3);
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_drive.setVisible(true);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            list_menu_drive.setLayoutY(button_drive.getLayoutY()+50);
            new FlipInX(list_menu_drive).play(); 
            }else{
            translate.setToY(50*3);       
            translate2.setToY(50*3);  
            translate3.setToY(50*3);  
            translate4.setToY(50*3);
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_drive.setVisible(true);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();
             new FlipInX(list_menu_drive).play();   
            }            
             
        }else{
            new FlipOutX(list_menu_drive).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_drive.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            translate.setToY(0);       
            translate2.setToY(0);  
            translate3.setToY(0);  
            translate4.setToY(0); 
        
        }
        translate.play();
        translate2.play();
        translate3.play();
        translate4.play();
        nbr_clickMenuDrive++;
    }
    
    @FXML
    private void click_menu_reservation(MouseEvent event) {
        while(button_drive.getStyleClass().remove("parentActive")==true){};
        while(button_avis.getStyleClass().remove("parentActive")==true){}
        while(button_reclamation.getStyleClass().remove("parentActive")==true){};
        while(button_location.getStyleClass().remove("parentActive")==true){};
        button_reservation.getStyleClass().add("parentActive");
        if(test.drive.type_user.equals("client")){
            menu_active=1;
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1),button_reservation);
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(1),button_location);
        TranslateTransition translate3 = new TranslateTransition(Duration.seconds(1),button_avis);
        TranslateTransition translate4 = new TranslateTransition(Duration.seconds(1),button_reclamation);
        if((nbr_clickReservatio%2==0 && menu_active==1) || (nbr_clickReservatio%2!=0 && menu_active!=1) ){
            if(nbr_clickLocation%2==1 || nbr_clickMenuDrive%2==1){
                        if(nbr_clickLocation%2==1){
            new FadeOut(list_menu_location).setDelay(new Duration(100)).play();
           new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_location.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            nbr_clickLocation++;
            }
            if(nbr_clickMenuDrive%2==1){
            new FadeOut(list_menu_drive).setDelay(new Duration(100)).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_drive.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            nbr_clickMenuDrive++;
            }    
            translate.setToY(0);  
            translate2.setToY(52*2);  
            translate3.setToY(52*2);  
            translate4.setToY(52*2); 
            translate.play();
            list_menu_reservation.setLayoutY(button_reservation.getLayoutY()+50);
            new FlipInX(list_menu_reservation).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_reservation.setVisible(true);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();
            }else{
            list_menu_reservation.setLayoutY(button_reservation.getLayoutY()+50);
            translate2.setToY(50*2);  
            translate3.setToY(50*2);  
            translate4.setToY(50*2); 
                        new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_reservation.setVisible(true);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();
             new FlipInX(list_menu_reservation).play();   
            }
        }else{
            new FlipOutX(list_menu_reservation).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_reservation.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            translate2.setToY(0);  
            translate3.setToY(0);  
            translate4.setToY(0); 
            
        }
        translate2.play();
        translate3.play();
        translate4.play();
        nbr_clickReservatio++;
        }
        
        if(test.drive.type_user.equals("chauffeur")){
            
        }
    }


    @FXML
    private void click_menu_location(MouseEvent event) {
        while(button_drive.getStyleClass().remove("parentActive")==true){};
        while(button_avis.getStyleClass().remove("parentActive")==true){}
        while(button_reclamation.getStyleClass().remove("parentActive")==true){};
        button_location.getStyleClass().add("parentActive");
        while(button_reservation.getStyleClass().remove("parentActive")==true){};
        menu_active=2;
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1),button_reservation);
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(1),button_location);
        TranslateTransition translate3 = new TranslateTransition(Duration.seconds(1),button_avis);
        TranslateTransition translate4 = new TranslateTransition(Duration.seconds(1),button_reclamation);
        if((nbr_clickLocation%2==0 && menu_active==2)  || (nbr_clickReservatio%2!=0 && menu_active!=2)){
            if(nbr_clickReservatio%2==1 || nbr_clickMenuDrive%2==1){
                                       if(nbr_clickReservatio%2==1){
            new FadeOut(list_menu_reservation).setDelay(new Duration(100)).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_reservation.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            nbr_clickReservatio++;
            }
            if(nbr_clickMenuDrive%2==1){
            new FadeOut(list_menu_drive).setDelay(new Duration(100)).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_drive.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();

            nbr_clickMenuDrive++;
            }     
            translate.setToY(0);  
            translate2.setToY(0);  
            translate3.setToY(52*2);  
            translate4.setToY(52*2); 
            translate.play();
            translate2.play();
            list_menu_location.setLayoutY(button_location.getLayoutY()+50);
            new FlipInX(list_menu_location).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_location.setVisible(true);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();
            }else{
             list_menu_location.setLayoutY(button_location.getLayoutY()+50);
            translate3.setToY(50*2);  
            translate4.setToY(50*2);
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_location.setVisible(true);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();
            new FlipInX(list_menu_location).play();    
            }
                
        }else{
            new FlipOutX(list_menu_location).play();
            new Thread(()->{try {Thread.sleep(300);Platform.runLater(()->{list_menu_location.setVisible(false);});}catch (InterruptedException ex){Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);}}).start();
            
            translate3.setToY(0);  
            translate4.setToY(0);
        }
        translate3.play();
        translate4.play();
        nbr_clickLocation++;
    }

    @FXML
    private void chnager_page(ActionEvent event) {  
        String page="";
        if(event.getSource()==button_index){
            page="/views/FXMLIndexClientPage.fxml";
        }
        if(event.getSource()==button_service){
            page="/views/FXMLListeOffreClientPage.fxml";
        }        
        if(event.getSource()==button_about){
            page="/views/FXMLAboutPage.fxml";
        } 
        if(event.getSource()==button_reservation && test.drive.type_user.equals("chauffeur")){
            page="/views/FXMLReservationCoteChauffeur.fxml";
        }
        if(event.getSource()==button_livraison){
            page="/views/FXMLLivraisonCoteChauffeur.fxml";
        }
        if(event.getSource()==button_deplacement){
            page="/views/FXMLDeplacementClient.fxml";
        }
        if(event.getSource()==buttonlouer_velo){
            page="/views/FXMLLouerVelo.fxml";
        }
        if(event.getSource()==button_consulter_location){
            page="/views/FXMLConsulterLocation.fxml";
        }
        if(event.getSource()==button_avis){
            if(test.drive.type_user.equals("client")){
                         page="/views/FXMLAjouterAvisClient.fxml";   
            }
            if(test.drive.type_user.equals("chauffeur")){
                         page="/views/FXMLAjouterAvisChauffeur.fxml";   
            }            
        }
                if(event.getSource()==button_reclamation){
                    if(test.drive.type_user.equals("client")){
            page="/views/FXMLReclamationClient.fxml";
                    }
                    if(test.drive.type_user.equals("admin")){
            page="/views/FXMLReclamationAdmin.fxml";
                    }
        } 
        if(event.getSource()==button_ajouter_velo){
            page="/views/FXMLAjouterVelo.fxml";
        } 
        if(event.getSource()==button_consulter_velo){
            page="/views/FXMLConsulterVeloAdmin.fxml";
        } 
        if(event.getSource()==button_ajouter_evenement){
            page="/views/FXMLAjouterEvenement.fxml";
        }
        if(event.getSource()==button_afficher_evenement){
            page="/views/FXMLConsulterEvenementAdmin.fxml";
        }
        if(event.getSource()==button_ajouter_offre){
            page="/views/FXMLAjouterOffre.fxml";
        }
        if(event.getSource()==button_ajouter_offre){
            page="/views/FXMLAjouterOffre.fxml";
        }
        if(event.getSource()==button_afficher_offre){
            page="/views/FXMLConsulterOffreAdmin.fxml";
        }
        if(event.getSource()==button_traiter_location){
            page="/views/FXMLTraiterLocationAdmin.fxml";
        } 
        if(event.getSource()==button_traiter_reclamation){
            page="/views/FXMLReclamationAdmin.fxml";
        }
        if(event.getSource()==afficherClient){
            page="/views/FXMLAfficherClientAdmin.fxml";
        }
        if(event.getSource()==afficherChauffeur){
            page="/views/FXMLAfficherChauffeurAdmin.fxml";
        }
        if(!page.replaceAll(" ", "").equals("")){
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(page));
            jun_copy.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(ControlerIndexPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
             
                       
    }

    
    @FXML
    private void click_menu_avis(MouseEvent event) {
        while(button_drive.getStyleClass().remove("parentActive")==true){};
        button_avis.getStyleClass().add("parentActive");
        while(button_reclamation.getStyleClass().remove("parentActive")==true){};
        while(button_location.getStyleClass().remove("parentActive")==true){};
        while(button_reservation.getStyleClass().remove("parentActive")==true){};

        TranslateTransition translate = new TranslateTransition(Duration.seconds(1),button_reservation);
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(1),button_location);
        TranslateTransition translate3 = new TranslateTransition(Duration.seconds(1),button_avis);
        TranslateTransition translate4 = new TranslateTransition(Duration.seconds(1),button_reclamation);
        
        if(nbr_clickReservatio%2==1){
            new FadeOut(list_menu_reservation).setDelay(new Duration(100)).play();
            nbr_clickReservatio++;
            }
        if(nbr_clickLocation%2==1){
            new FadeOut(list_menu_location).setDelay(new Duration(100)).play();
            nbr_clickLocation++;
            }
            if(nbr_clickMenuDrive%2==1){
            new FadeOut(list_menu_drive).setDelay(new Duration(100)).play();
            nbr_clickMenuDrive++;
            }    
            
            translate.setToY(0); 
            translate2.setToY(0);  
            translate3.setToY(0);  
            translate4.setToY(0); 
            
                    translate.play();
        translate2.play();
        translate3.play();
        translate4.play();
    }

    @FXML
    private void click_menu_reclamation(MouseEvent event) {
        while(button_drive.getStyleClass().remove("parentActive")==true){};
        while(button_avis.getStyleClass().remove("parentActive")==true){}
        button_reclamation.getStyleClass().add("parentActive");
        while(button_location.getStyleClass().remove("parentActive")==true){};
        while(button_reservation.getStyleClass().remove("parentActive")==true){};
    }

    @FXML
    private void hide_menu2(MouseEvent event) {
    new FadeOut(fond_dispa).play();
    new FadeOutLeft(menuAdmin).play();
    
    Timer timer = new Timer(800, new ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
                            fond_dispa.setVisible(false);                   
               menuAdmin.setVisible(false);
        }

});
timer.setRepeats(false); // Only execute once
timer.start(); // Go go go!        
    }

    @FXML
    private void showEditionProfil(MouseEvent event) {
        
        if(ClickButtonAfficherProfil%2==0){
            menu_edition.setVisible(true);
            new FadeIn(menu_edition).play();
        }else{
            new FadeOut(menu_edition).play();
            
                    new Thread(()->{
            try {
                Thread.sleep(800);
                //ot.hide(); // permet de fermer la fenetre  
                Platform.runLater(()->{
                 menu_edition.setVisible(false);  
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
                    
                    
            
        }
        
        ClickButtonAfficherProfil++;
    }

    @FXML
    private void deconnexion(MouseEvent event) {
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
    }
    

    @FXML
    private void editerProfil(MouseEvent event) {
      
        
                String pg="";
                if(test.drive.type_user.equals("client")){
                 pg="/views/FXMLEditionProfilClient.fxml";   
                }
                if(test.drive.type_user.equals("chauffeur")){
                 pg="/views/FXMLEditionProfilChauffeur.fxml";   
                }
                if(test.drive.type_user.equals("admin")){
                 pg="/views/FXMLEditionProfilAdmin.fxml";    
                }
                
                Parent root;
                   try {
                       root = FXMLLoader.load(getClass().getResource(pg));
                       jun_copy.setCenter(root);
                   } catch (IOException ex) {
                       Logger.getLogger(ControlerIndexPage.class.getName()).log(Level.SEVERE, null, ex);
                   }
    }

}
                    