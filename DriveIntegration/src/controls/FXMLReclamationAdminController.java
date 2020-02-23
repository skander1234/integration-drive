/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import static controls.ControlerIndexPage.jun_copy;
import static controls.FXMLConsulterVeloAdminController.velos;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import models.Core.ClientC;
import models.Core.ReclamationC;
import models.Core.VeloC;
import models.Entities.Client;
import models.Entities.Reclamation;
import models.Entities.Velo;
import models.Utils.Criteres;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLReclamationAdminController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private ScrollPane liste;
    @FXML
    private TextField tyd;
    private Reclamation reclamations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       new FadeIn(parent).play();
               
        if(!ControlerIndexPage.rechercher.equals("")){
            tyd.setText(ControlerIndexPage.rechercher);
        }
        reclamations=null;
        final FlowPane container = new FlowPane();
        container.setStyle("-fx-background-color:#eaeaea;");
        container.setPrefSize(650, 712);
                 DropShadow dropShadow = new DropShadow();
 //
 dropShadow.setOffsetX(0.0);
 dropShadow.setOffsetY(0.0);
 dropShadow.setColor(Color.web("#c4c4c4"));
        

    ReclamationC v=new ReclamationC();
    int i=0;
    List<Reclamation> cv=new ArrayList<>();
    if(!ControlerIndexPage.rechercher.equals("")){
        cv=v.RechercheAvance(ControlerIndexPage.rechercher).stream().filter(e->e.getSujet_rec().equals("autre")).collect(Collectors.toList());
    }
    else{
        Criteres c=new Criteres();
        c.ajouterCritere("sujet_rec", "autre");
        cv=v.filterSelonDesCritere(c);
    }
    for(Reclamation reclamation:cv){
            Client c=reclamation.getClient();
            Pane p=new Pane();
            
            Pane p1=new Pane();
            p1.setStyle("-fx-border-insets:0px 0px 6px 0px;-fx-border-color:#eaeaea;");
            p.setPrefWidth(645);
            p.setStyle("-fx-background-color:#eaeaea;-fx-border-insets:6px 6px 6px 6px;-fx-border-color:#eaeaea;");
            p.setLayoutY((i*50)+10);
            
            p1.setPrefWidth(640);
            
            
            p1.setStyle("-fx-background-color:white;-fx-background-radius:3px;");
            
            p1.setEffect(dropShadow);
            
            /*ImageView image=new ImageView();
            image.setFitWidth(100); 
            image.setFitHeight(130); 
            image.setLayoutX(5);
            image.setLayoutY(5);
            image.setImage(new Image("http://localhost/drive/uploads/"+velo.getPhoto()));
            p1.getChildren().add(image);*/
            Label message=new Label("message : "+reclamation.getMsg());
            Label mail=new Label("mail du client : "+c.getMail());
            Label dateAjout=new Label("date ajout : "+reclamation.getDateAjout().toString());
            
            message.setLayoutY(10);
            mail.setLayoutY(35);
            dateAjout.setLayoutY(60);
            
             message.setLayoutX(170);
            mail.setLayoutX(170);
            dateAjout.setLayoutX(170);
            
            p1.getChildren().add(message);
           p1.getChildren().add(mail);
            p1.getChildren().add(dateAjout);
            
            p1.setLayoutX(4);
            p1.setLayoutY(4);
            p.getChildren().add(p1);
            
            Button supprimer=new Button("rejetter la reclamation");
           
            supprimer.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        ReclamationC vs=new ReclamationC();
                        vs.supprimerReclamation(reclamation.getId_rec());
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLReclamationAdmin.fxml"));
                        jun_copy.setCenter(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLConsulterVeloAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
    
});
                        supprimer.setLayoutY(80);
            supprimer.setLayoutX(430);
            p1.getChildren().add(supprimer);


            Button modif=new Button("repondre");
           
        
            modif.setLayoutY(80);
            modif.setLayoutX(310);
            p1.getChildren().add(modif);            
            
            
        container.getChildren().add(p);        
        i++;
    }
                liste.setContent(container);
        liste.setPannable(true);
    
    }    

    @FXML
    private void tyd(KeyEvent event) {
    }

    @FXML
    private void rechercher(MouseEvent event) {
        ControlerIndexPage.rechercher=tyd.getText();
                     try {
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLReclamationAdmin.fxml"));
                        jun_copy.setCenter(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLConsulterVeloAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }  
    }
    
}
