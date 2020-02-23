/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import static controls.ControlerIndexPage.jun_copy;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.Entities.*;
import models.Core.*;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLConsulterVeloAdminController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private ScrollPane liste;
        public static Velo velos;
    @FXML
    private TextField tyd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(!ControlerIndexPage.rechercher.equals("")){
            tyd.setText(ControlerIndexPage.rechercher);
        }
        velos=null;
                new FadeIn(parent).play();
        final FlowPane container = new FlowPane();
        container.setStyle("-fx-background-color:#eaeaea;");
        container.setPrefSize(650, 712);
                 DropShadow dropShadow = new DropShadow();
 //
 dropShadow.setOffsetX(0.0);
 dropShadow.setOffsetY(0.0);
 dropShadow.setColor(Color.web("#c4c4c4"));
        

    VeloC v=new VeloC();
    int i=0;
    List<Velo> cv=new ArrayList<>();
    if(!ControlerIndexPage.rechercher.equals("")){
        cv=v.RechercheAvance(ControlerIndexPage.rechercher);
    }
    else{
        cv=v.afficher();
    }
    for(Velo velo:cv){
            
            Pane p=new Pane();
            
            Pane p1=new Pane();
            p1.setStyle("-fx-border-insets:0px 0px 6px 0px;-fx-border-color:#eaeaea;");
            p.setPrefWidth(645);
            p.setStyle("-fx-background-color:#eaeaea;-fx-border-insets:6px 6px 6px 6px;-fx-border-color:#eaeaea;");
            p.setLayoutY((i*50)+10);
            
            p1.setPrefWidth(640);
            
            
            p1.setStyle("-fx-background-color:white;-fx-background-radius:3px;");
            
            p1.setEffect(dropShadow);
            
            ImageView image=new ImageView();
            image.setFitWidth(100); 
            image.setFitHeight(130); 
            image.setLayoutX(5);
            image.setLayoutY(5);
            image.setImage(new Image("http://localhost/drive/uploads/"+velo.getPhoto()));
            p1.getChildren().add(image);
            Label type=new Label("type : "+velo.getType());
            Label adresse=new Label("adresse : "+velo.getAdresse());
            Label qte=new Label("quantite : "+Integer.toString(velo.getQte()));
            Label prix=new Label("prix : "+Float.toString(velo.getPrix()));
            type.setLayoutY(10);
            adresse.setLayoutY(35);
            qte.setLayoutY(60);
            prix.setLayoutY(85);
            
             type.setLayoutX(170);
            adresse.setLayoutX(170);
            qte.setLayoutX(170);
            prix.setLayoutX(170);
            
            p1.getChildren().add(type);
           p1.getChildren().add(adresse);
            p1.getChildren().add(qte);
             p1.getChildren().add(prix);
            
            p1.setLayoutX(4);
            p1.setLayoutY(4);
            p.getChildren().add(p1);
            
            Button supprimer=new Button("supprimer");
           
            supprimer.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        VeloC vs=new VeloC();
                        vs.supprimerVelo(velo.getId());
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLConsulterVeloAdmin.fxml"));
                        jun_copy.setCenter(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLConsulterVeloAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
    
});
                        supprimer.setLayoutY(80);
            supprimer.setLayoutX(400);
            p1.getChildren().add(supprimer);


            Button modif=new Button("modifier");
           
            modif.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        Parent root;
                        velos=velo;
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLAjouterVelo.fxml"));
                        jun_copy.setCenter(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLConsulterVeloAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
    
});            
            modif.setLayoutY(80);
            modif.setLayoutX(500);
            p1.getChildren().add(modif);            
            
            
        container.getChildren().add(p);        
        i++;
    }
                liste.setContent(container);
        liste.setPannable(true);
    
    }


    @FXML
    private void rechercher(MouseEvent event) {
        ControlerIndexPage.rechercher=tyd.getText();
                     try {
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLConsulterVeloAdmin.fxml"));
                        jun_copy.setCenter(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLConsulterVeloAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }       
    }

    @FXML
    private void tyd(KeyEvent event) {
    }
    
}
