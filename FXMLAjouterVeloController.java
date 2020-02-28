/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import static controls.ControlerIndexPage.jun_copy;
import static controls.FXMLConsulterVeloAdminController.velos;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;
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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;
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
    private int deja=0;
    
    private Image debut;
    @FXML
    private JFXButton btn;
    @FXML
    private Button affmap1;
    @FXML
    private FontAwesomeIconView affmap;
    private double localLat=0.0;
    private double localLon=0.0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ControlerIndexPage.longitudeAdmin=0;
        ControlerIndexPage.latitudeAdmin=0;
        ControlerIndexPage.longitudeAdmin1=0;
        ControlerIndexPage.latitudeAdmin1=0;
        
        
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
        
                                      new Timer().schedule(
    new TimerTask() {

        @Override
        public void run() {
           if(localLat!=ControlerIndexPage.latitudeAdmin || localLon!=ControlerIndexPage.longitudeAdmin){
               localLat=ControlerIndexPage.latitudeAdmin;
               localLon=ControlerIndexPage.longitudeAdmin;
                                NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI();
    NominatimReverseGeocodingJAPI nominatim2 = new NominatimReverseGeocodingJAPI(18);
        String adresss=nominatim1.getAdress(localLat,localLon).toString();
                adresse.setText(adresss);
           }
 
        
    
        }
    }, 0, 300);
    }    

    @FXML
    private void save(MouseEvent event) throws Exception {
                String type="";
        if(veloElectro.isSelected()){
            type="velo Electrique";
        }
        if(veloSimple.isSelected()){
            type="velo";
        }
        
        String ads=models.Entities.translator.Translator.translate("fr",adresse.getText());
        if(FXMLConsulterVeloAdminController.velos==null){
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
            Velo v=new Velo(type,ads,qte,ad,prixs,localLat,localLon);
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
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"adresse", ads);
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"latitude", localLat);
                c.modifierVelo(FXMLConsulterVeloAdminController.velos.getId(),"longitude", localLon);
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

    @FXML
    private void afficherMap(MouseEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/views/MapBackend.fxml"));
        Scene scene=null;
        if(event.getSource()==affmap){
         scene= affmap.getScene();    
        }else{
          scene= affmap1.getScene();     
        }
        
        root.translateYProperty().set(scene.getHeight());
        
             if(deja==0){
                deja=1;
                jun_copy.getChildren().add(root);
            }else{
                 jun_copy.getChildren().remove(jun_copy.getChildren().size()-1);
                 jun_copy.getChildren().add(root);
                 deja=0;
             } 
             
             Timeline timeline=new Timeline();
             KeyValue kv=new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);
             KeyFrame kf=new KeyFrame(Duration.seconds(1),kv);
             timeline.getKeyFrames().add(kf);
             timeline.play();
            
            
                    

    }
    
    
}
