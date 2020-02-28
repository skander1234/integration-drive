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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.Core.LocationC;
import models.Core.VeloC;
import models.Entities.Location;
import models.Entities.Velo;
import models.Utils.Criteres;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLConsulterLocationController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private AnchorPane VeloLouer;
    @FXML
    private ImageView imgVelo;
    @FXML
    private Text typeVelo;
    @FXML
    private Text dateDebut;
    @FXML
    private Text dateFin;
    @FXML
    private Text prixVelo;
    @FXML
    private AnchorPane veloNonLouer;
    public LocationC lc;
    public Location l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeIn(parent).play();
       lc=new LocationC();
       Criteres c=new Criteres();
       c.ajouterCritere("id_client", test.drive.client.getId_user());
       l=null;
       for(Location vb:lc.filterSelonDesCritere(c)){
           l=vb;
       }
       
       if(l==null){
           VeloLouer.setVisible(false);
       }else{
           veloNonLouer.setVisible(false);
           VeloC xc=new VeloC();
           
           typeVelo.setText(typeVelo.getText()+" "+l.getVelo().getType());
           dateDebut.setText(dateDebut.getText()+" "+l.getDate_d().toLocalDateTime().toLocalDate().toString());
           dateFin.setText(dateFin.getText()+" "+l.getDate_f().toLocalDateTime().toLocalDate().toString());
           prixVelo.setText(prixVelo.getText()+" "+String.valueOf(l.getPrix()));
           imgVelo.setImage(new Image("http://localhost/drive/uploads/"+l.getVelo().getPhoto()));
       }
    }    

    @FXML
    private void supprimer(MouseEvent event) throws IOException {
        VeloC gv=new VeloC();
        gv.modifierVelo(l.getVelo().getId(),"qte",l.getVelo().getQte()+1);
        lc.supprimerLocation(l.getId_location());
                                Parent root;
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLConsulterLocation.fxml"));
                        jun_copy.setCenter(root);
    }
    
}
