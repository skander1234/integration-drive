/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXScrollPane;
import static controls.ControlerIndexPage.jun_copy;
import static controls.FXMLConsulterVeloAdminController.velos;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import models.Core.ClientC;
import models.Core.UserC;
import models.Core.VeloC;
import models.Entities.Client;
import models.Entities.User;
import models.Entities.Velo;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLAfficherClientAdminController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private ScrollPane liste;
    @FXML
    private TextField tyd;
    private UserC clients;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!ControlerIndexPage.rechercher.equals("")){
            tyd.setText(ControlerIndexPage.rechercher);
        }
        clients=null;
                new FadeIn(parent).play();
        final FlowPane container = new FlowPane();
        container.setStyle("-fx-background-color:#eaeaea;");
        container.setPrefSize(650, 712);
                 DropShadow dropShadow = new DropShadow();
 //
 dropShadow.setOffsetX(0.0);
 dropShadow.setOffsetY(0.0);
 dropShadow.setColor(Color.web("#c4c4c4"));
        

    UserC v=new UserC();
    int i=0;
    List<User> cv=new ArrayList<>();
    if(!ControlerIndexPage.rechercher.equals("")){
        cv=v.RechercheAvance(ControlerIndexPage.rechercher);
    }
    else{
        cv=v.afficher();
    }
    for(User client:cv){
            
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
            Label numero=new Label("numero : "+client.getN_tel());
            Label login=new Label("login : "+client.getLogin());
            Label mail=new Label("mail : "+client.getMail());
            
            numero.setLayoutY(10);
            login.setLayoutY(35);
            mail.setLayoutY(60);
            
             login.setLayoutX(170);
            mail.setLayoutX(170);
            numero.setLayoutX(170);
            
            p1.getChildren().add(login);
           p1.getChildren().add(mail);
            p1.getChildren().add(numero);
            
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
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLAfficherClientAdmin.fxml"));
                        jun_copy.setCenter(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLConsulterVeloAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }  
    }
    
}
