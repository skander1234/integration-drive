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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.Core.ChauffeurC;
import models.Core.UserC;
import models.Entities.Chauffeur;
import models.Entities.User;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLAfficherChauffeurAdminController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private ScrollPane liste;
    @FXML
    private TextField tyd;
    
    public static Chauffeur chauffeurs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!ControlerIndexPage.rechercher.equals("")){
            tyd.setText(ControlerIndexPage.rechercher);
        }
        chauffeurs=null;
                new FadeIn(parent).play();
        final FlowPane container = new FlowPane();
        container.setStyle("-fx-background-color:#eaeaea;");
        container.setPrefSize(650, 712);
                 DropShadow dropShadow = new DropShadow();
 //
 dropShadow.setOffsetX(0.0);
 dropShadow.setOffsetY(0.0);
 dropShadow.setColor(Color.web("#c4c4c4"));
        

    ChauffeurC v=new ChauffeurC();
    int i=0;
    List<Chauffeur> cv=new ArrayList<>();
    if(!ControlerIndexPage.rechercher.equals("")){
        cv=v.RechercheAvance(ControlerIndexPage.rechercher);
    }
    else{
        cv=v.afficher();
    }
    for(Chauffeur ch:cv){
            
            Pane p=new Pane();
            
            Pane p1=new Pane();
            p1.setStyle("-fx-border-insets:0px 0px 6px 0px;-fx-border-color:#eaeaea;");
            p.setPrefWidth(645);
            p.setStyle("-fx-background-color:#eaeaea;-fx-border-insets:6px 6px 6px 6px;-fx-border-color:#eaeaea;");
            p.setLayoutY((i*50)+10);
            
            p1.setPrefWidth(640);
            
            
            p1.setStyle("-fx-background-color:white;-fx-background-radius:3px;");
            
            p1.setEffect(dropShadow);
            /*
            ImageView image=new ImageView();
            image.setFitWidth(100); 
            image.setFitHeight(130); 
            image.setLayoutX(5);
            image.setLayoutY(5);
            image.setImage(new Image("http://localhost/drive/uploads/"+velo.getPhoto()));
            p1.getChildren().add(image);*/
            Label numero=new Label("numero : "+ch.getN_tel());
            Label login=new Label("login : "+ch.getLogin());
            Label mail=new Label("mail : "+ch.getMail());
            Label adresse=new Label("adresse : "+ch.getAdresse());
            Label cin=new Label("cin : "+ch.getCin());
            Label permis=new Label("premis : "+ch.getPermis());
            Label nom=new Label("nom : "+ch.getNom());
            Label prenom=new Label("prenom : "+ch.getNom());
            
            numero.setLayoutY(10);
            login.setLayoutY(35);
            mail.setLayoutY(60);
            cin.setLayoutY(85);
            permis.setLayoutY(110);
            nom.setLayoutY(135);
            prenom.setLayoutY(160);
            
            numero.setLayoutX(170);
            login.setLayoutX(170);
            mail.setLayoutX(170);
            cin.setLayoutX(170);
            permis.setLayoutX(170);
            nom.setLayoutX(170);
            prenom.setLayoutX(170);
            
            p1.getChildren().add(login);
           p1.getChildren().add(mail);
            p1.getChildren().add(numero);
            p1.getChildren().add(cin);
            p1.getChildren().add(permis);
            p1.getChildren().add(nom);
            p1.getChildren().add(prenom);
            
            p1.setLayoutX(4);
            p1.setLayoutY(4);
            p.getChildren().add(p1);                       
            
            
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
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLAfficherChauffeurAdmin.fxml"));
                        jun_copy.setCenter(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLConsulterVeloAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }  
    }
    
}
