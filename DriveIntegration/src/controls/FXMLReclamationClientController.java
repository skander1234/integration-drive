/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Core.ChauffeurC;
import models.Core.ReclamationC;
import models.Entities.Chauffeur;
import models.Entities.Reclamation;
import models.Utils.Criteres;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLReclamationClientController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private TextArea message;
    @FXML
    private MenuButton liste;
    @FXML
    private MenuButton liste_nom;
    @FXML
    private MenuButton liste_prenom;
    
    public static Chauffeur cf=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeIn(parent).play();
        cf=null;
        message.setVisible(false);
        
        MenuItem menu1=new MenuItem(" 1. Reclamation : retard du chauffeur");
        MenuItem menu2=new MenuItem(" 2. Reclamation : etat du vehicule");
        MenuItem menu3=new MenuItem(" 3. Reclamation : comportement inaproprié");
        MenuItem menu4=new MenuItem(" 4. Reclamation : mauvaise conduite");
        MenuItem menu5=new MenuItem(" 5. Reclamation : autre");
        liste.getItems().clear();
        liste_nom.getItems().clear();
        liste_prenom.getItems().clear();
        liste.getItems().add(menu1);
        liste.getItems().add(menu2);
        liste.getItems().add(menu3);
        liste.getItems().add(menu4);
        liste.getItems().add(menu5);

            menu1.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        message.setVisible(false);
        liste.setText("retard du chauffeur");
    }
});
  menu2.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        message.setVisible(false);
        liste.setText("etat du vehicule");
    }
});
  menu3.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        message.setVisible(false);
        liste.setText("comportement inaproprié");
    }
});
  menu4.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        message.setVisible(false);
        liste.setText("mauvaise conduite");
    }
});
  
  menu5.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        message.setVisible(true);
        liste.setText("autre");
    }
});
            
        ChauffeurC ch=new ChauffeurC();
        List<Chauffeur> lch=new ArrayList<>();
        lch=ch.afficher();
        for(String nom:lch.stream().map(e->e.getNom()).collect(Collectors.toList())){
          MenuItem menu10;  
            menu10 = new MenuItem(nom);
            menu10.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        liste_nom.setText(nom);
    }
});
            liste_nom.getItems().add(menu10);
        }
        for(String prenom:lch.stream().map(e->e.getPrenom()).collect(Collectors.toList())){
          MenuItem menu10;  
            menu10 = new MenuItem(prenom);
            menu10.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        liste_prenom.setText(prenom);
    }
});
            liste_prenom.getItems().add(menu10);
        }
    }    

    @FXML
    private void changePhoneNumber(MouseEvent event) {
        ChauffeurC ch=new ChauffeurC();
        List<Chauffeur> lch=new ArrayList<>();
        Criteres cr=new Criteres();
        cr.ajouterCritere("nom", liste_nom.getText());
        cr.ajouterCritere("prenom", liste_prenom.getText());
        lch=ch.filterSelonDesCritere(cr);   
        int i=0;
        for(Chauffeur bn: lch){
            cf=bn;
            i++;
        }
        if(i==0){
            models.Utils.FonctionsPartages.notification("recherche chauffeur", "le chauffeur n'existe  pas", "warning");
        }else{
           models.Utils.FonctionsPartages.notification("recherche chauffeur", "chauffeur trouver vous pouvez continuer la recherche", "success"); 
        }
        
    }

    @FXML
    private void trouverReclamation(MouseEvent event) {
        if(!liste.getText().equals("Choisir un type de reclamation")){
        if(message.isVisible()==false && cf!=null){
            
            
            String messages=String.valueOf(cf.getId_user());
            ReclamationC rc=new ReclamationC();
            Reclamation r=new Reclamation(test.drive.client,liste.getText(), messages,0);
            rc.ajouterReclamation(r);
                       models.Utils.FonctionsPartages.notification("Reclamation", "Reclamation envoyer avec succee", "success"); 
                       cf=null;
        }else{
            if(message.isVisible()==true && cf!=null){
             
            String messages=message.getText();
            ReclamationC rc=new ReclamationC();
            Reclamation r=new Reclamation(test.drive.client,liste.getText(), messages,0);
            rc.ajouterReclamation(r);
            models.Utils.FonctionsPartages.notification("Reclamation", "Reclamation envoyer avec succee", "success"); 
            cf=null;
            }else{
             models.Utils.FonctionsPartages.notification("Reclamation", "Vous devez selectionner un chauffeur", "warning");    
            }
            
        }            
        }else{
            models.Utils.FonctionsPartages.notification("Reclamation", "Vous devez selectionner un type de reclamation", "warning"); 
        }
    }
    
}
