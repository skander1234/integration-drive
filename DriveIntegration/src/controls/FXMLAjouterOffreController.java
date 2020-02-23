/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import models.Core.OffreC;
import models.Core.VeloC;
import models.Entities.Offre;
import models.Entities.Velo;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLAjouterOffreController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView photo_velo;
    @FXML
    private TextField nom;
    @FXML
    private TextField reduction;
    @FXML
    private TextField code_promo;
    @FXML
    private JFXRadioButton offreLocation;
    @FXML
    private JFXRadioButton offreReservation;
    @FXML
    private JFXButton btn;
    @FXML
    private DatePicker date_f;
    @FXML
    private DatePicker date_d;
    
    private String ad="";
    @FXML
    private ToggleGroup typeVelo;
    private Image debut;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        new FadeIn(parent).play();
                if(FXMLConsulterOffreAdminController.offre!=null){
            debut=photo_velo.getImage();
            nom.setText(FXMLConsulterOffreAdminController.offre.getNom());
            reduction.setText(String.valueOf(FXMLConsulterOffreAdminController.offre.getReduction_offre()));
            date_f.setValue(FXMLConsulterOffreAdminController.offre.getDate_f().toLocalDateTime().toLocalDate());
            date_d.setValue(FXMLConsulterOffreAdminController.offre.getDate_d().toLocalDateTime().toLocalDate());
            code_promo.setText(FXMLConsulterOffreAdminController.offre.getCode_promo());
            
     Image image = new Image("http://localhost/drive/uploads/"+FXMLConsulterOffreAdminController.offre.getImg());
     
     
    photo_velo.setImage(image);   
    
    if(FXMLConsulterOffreAdminController.offre.getType().equals("location")){
        offreLocation.setSelected(true);
    }else{
        offreReservation.setSelected(true);
    }
        }
    }    

    @FXML
    private void save(MouseEvent event) throws ParseException {
        String type="";
          if(offreLocation.isSelected()){
            type="location";
        }
        if(offreReservation.isSelected()){
            type="reservation";
        }
    

        if(FXMLConsulterOffreAdminController.offre==null){
        String noms=nom.getText();
        String codepromo=code_promo.getText();
        String date2;
        String date1;
        if(date_f.getValue()==null){
            date2="";
        }else{
            date2=date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if(date_d.getValue()==null){
            date1="";
        }else{
            date1=date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

            
        float redu;
        if(reduction.getText().equals("")){
            redu=0.56f;
        }else{
         redu = Float.parseFloat(reduction.getText());
        }        
        
        
        if(noms.equals("") || type.equals("") || ad.equals("") || redu==0.56f || codepromo.equals("") || date2.equals("") || date1.equals("")){
            
            models.Utils.FonctionsPartages.notification("ajout de l'offre","veuillez remplir tous les champs","warning");
        }else{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate1;
            java.util.Date parsedDate = dateFormat.parse(date1);
            Timestamp timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
            java.util.Date parsedDate2 = dateFormat.parse(date2);
            Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
            Offre o=new Offre(timestamp1, timestamp2, type, noms, redu, codepromo,ad);
            OffreC oc = new OffreC();
            String res=oc.ajouterReduction(o);
            if(res.equals("ok")){
               models.Utils.FonctionsPartages.notification("ajout terminer","ajout terminé avec succée","success"); 
            }else{
               models.Utils.FonctionsPartages.notification("ajout terminer",res,"warning");
            }
            
        }            
        }else{
            OffreC c=new OffreC();
            if(!debut.equals(photo_velo.getImage()) && ad.equals("")!=true){
                FXMLConsulterOffreAdminController.offre.setImg(ad);
                c.modifierOffre(FXMLConsulterOffreAdminController.offre.getId_offre(),"img", ad);
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(ad.equals("")!=true){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
                
            }
            if(!FXMLConsulterOffreAdminController.offre.getNom().equals(nom.getText())  && nom.getText().equals("")!=true){
                FXMLConsulterOffreAdminController.offre.setNom(nom.getText());
                c.modifierOffre(FXMLConsulterOffreAdminController.offre.getId_offre(),"nom", nom.getText());
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(!FXMLConsulterOffreAdminController.offre.getNom().equals(nom.getText())){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
            
            if(!FXMLConsulterOffreAdminController.offre.getType().equals(type)  && type.equals("")!=true){
                FXMLConsulterOffreAdminController.offre.setType(type);
                c.modifierOffre(FXMLConsulterOffreAdminController.offre.getId_offre(),"type", type);
                models.Utils.FonctionsPartages.notification("modificatiooon terminer","modication terminé avec succée","success");
            }else{
                            if(!FXMLConsulterOffreAdminController.offre.getType().equals(type)){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
            
            
            if(FXMLConsulterOffreAdminController.offre.getReduction_offre()!=Float.parseFloat(reduction.getText()) && reduction.getText().equals("")!=true){
                FXMLConsulterOffreAdminController.offre.setReduction_offre(Float.parseFloat(reduction.getText()));
                c.modifierOffre(FXMLConsulterOffreAdminController.offre.getId_offre(),"reduction_offre", Float.parseFloat(reduction.getText()));
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(FXMLConsulterOffreAdminController.offre.getReduction_offre()!=Float.parseFloat(reduction.getText())){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
            
            
            
          if(!FXMLConsulterOffreAdminController.offre.getCode_promo().equals(code_promo.getText())  && code_promo.getText().equals("")!=true){
                FXMLConsulterOffreAdminController.offre.setCode_promo(code_promo.getText());
                c.modifierOffre(FXMLConsulterOffreAdminController.offre.getId_offre(),"code_promo", code_promo.getText());
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(!FXMLConsulterOffreAdminController.offre.getCode_promo().equals(code_promo.getText())){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }

          if(!FXMLConsulterOffreAdminController.offre.getDate_d().equals(date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))  &&
                  date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals("")!=true){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate1;
        String date1=date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.util.Date parsedDate = dateFormat.parse(date1);
            Timestamp timestamp1 = new java.sql.Timestamp(parsedDate.getTime());              
                FXMLConsulterOffreAdminController.offre.setDate_d(timestamp1);
                c.modifierOffre(FXMLConsulterOffreAdminController.offre.getId_offre(),"date_d", timestamp1);
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(!FXMLConsulterOffreAdminController.offre.getDate_d().equals(date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
                               models.Utils.FonctionsPartages.notification("modification","remplir tous les champs","warning"); 
            }
            }
          
          
          if(!FXMLConsulterOffreAdminController.offre.getDate_f().equals(date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))  &&
                  date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals("")!=true){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate1;
        String date1=date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.util.Date parsedDate = dateFormat.parse(date1);
            Timestamp timestamp1 = new java.sql.Timestamp(parsedDate.getTime());              
                FXMLConsulterOffreAdminController.offre.setDate_f(timestamp1);
                c.modifierOffre(FXMLConsulterOffreAdminController.offre.getId_offre(),"date_f", timestamp1);
                models.Utils.FonctionsPartages.notification("ajout terminer","modication terminé avec succée","success");
            }else{
                            if(!FXMLConsulterOffreAdminController.offre.getDate_f().equals(date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
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
