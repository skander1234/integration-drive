/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import static controls.FXMLConsulterVeloAdminController.velos;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import models.Core.VeloC;
import models.Entities.Velo;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLAjouterVeloController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView photo_velo;
    @FXML
    private TextField adresse;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;
    @FXML
    private JFXRadioButton veloElectro;
    @FXML
    private ToggleGroup typeVelo;
    @FXML
    private JFXRadioButton veloSimple;
    
    private String ad="";
    
    private Image debut;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        new FadeIn(parent).play();
        if(FXMLConsulterVeloAdminController.velos!=null){
            debut=photo_velo.getImage();
            adresse.setText(FXMLConsulterVeloAdminController.velos.getAdresse());
            quantite.setText(String.valueOf(FXMLConsulterVeloAdminController.velos.getQte()));
            prix.setText(String.valueOf(FXMLConsulterVeloAdminController.velos.getPrix()));
     Image image = new Image("http://localhost/drive/uploads/"+FXMLConsulterVeloAdminController.velos.getPhoto());
    photo_velo.setImage(image);   
    
    if(FXMLConsulterVeloAdminController.velos.getType().equals("velo Electrique")){
        veloElectro.setSelected(true);
    }else{
        veloSimple.setSelected(true);
    }
        }
    }    

    @FXML
    private void save(MouseEvent event) {
                String type="";
        if(veloElectro.isSelected()){
            type="velo Electrique";
        }
        if(veloSimple.isSelected()){
            type="velo";
        }
        if(FXMLConsulterVeloAdminController.velos==null){
        String ads=adresse.getText();
        int qte;
        float prixs;
        if(quantite.getText().equals("")){
            qte=0;
        }else{
         qte=Integer.parseInt(quantite.getText());   
        }
        if(prix.getText().equals("")){
            prixs=0.56f;
        }else{
         prixs = Float.parseFloat(prix.getText()); 
            System.out.println(prixs);
        }        
        
        
        if(ads.equals("") || type.equals("") || qte==0 || ad.equals("") || prixs==0.56f){
            models.Utils.FonctionsPartages.notification("ajout d'un velo","veuillez remplir tous les champs","warning");
        }else{
            Velo v=new Velo(type,ads,qte,ad,prixs);
            VeloC vc=new VeloC();
            vc.ajouterVelo(v);
            models.Utils.FonctionsPartages.notification("ajout terminer","ajout terminé avec succée","success");
        }            
        }else{
            VeloC c=new VeloC();
            if(!debut.equals(photo_velo.getImage()) && ad.equals("")!=true){
                FXMLConsulterVeloAdminController.velos.setPhoto(ad);
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"photo", ad);
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(ad.equals("")!=true){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
                
            }
            if(!FXMLConsulterVeloAdminController.velos.getAdresse().equals(adresse.getText())  && adresse.getText().equals("")!=true){
                FXMLConsulterVeloAdminController.velos.setAdresse(adresse.getText());
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"adresse", adresse.getText());
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(!FXMLConsulterVeloAdminController.velos.getAdresse().equals(adresse.getText())){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
            if(FXMLConsulterVeloAdminController.velos.getQte()!=Integer.parseInt(quantite.getText())   && quantite.getText().equals("")!=true){
                FXMLConsulterVeloAdminController.velos.setQte(Integer.parseInt(quantite.getText()));
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"qte", Integer.parseInt(quantite.getText()));
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(FXMLConsulterVeloAdminController.velos.getQte()!=Integer.parseInt(quantite.getText())){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
            if(!FXMLConsulterVeloAdminController.velos.getType().equals(type)  && type.equals("")!=true){
                FXMLConsulterVeloAdminController.velos.setType(type);
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"type", type);
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(!FXMLConsulterVeloAdminController.velos.getType().equals(type)){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
            if(FXMLConsulterVeloAdminController.velos.getPrix()!=Float.parseFloat(prix.getText()) && prix.getText().equals("")!=true){
                FXMLConsulterVeloAdminController.velos.setPrix(Float.parseFloat(prix.getText()));
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"prix", Float.parseFloat(prix.getText()));
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(FXMLConsulterVeloAdminController.velos.getPrix()!=Float.parseFloat(prix.getText())){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
        }

    }

    @FXML
    private void ajouterImage(MouseEvent event) throws ProtocolException, IOException {
        JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(null);
    File f = chooser.getSelectedFile();
    String fileName=f.getAbsolutePath();
    FileUploader fu=new FileUploader("localhost/drive");
    String fileNameInServer = fu.upload(fileName);
    Image image = new Image("http://localhost/drive/uploads/"+fileNameInServer);
    photo_velo.setImage(image);
    ad=fileNameInServer;
    }
    
    
}
