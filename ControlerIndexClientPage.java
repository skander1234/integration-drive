/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.*;
import static controls.ControlerIndexPage.jun_copy;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.Core.LocationC;
import models.Core.VeloC;
import models.Entities.Chauffeur;
import models.Entities.Location;
import models.Entities.Velo;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import test.drive;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class ControlerIndexClientPage implements Initializable {
    @FXML
    private AnchorPane parent;
    Label labelFromJavascript;
    @FXML
    private AnchorPane premiereAffichage;
    @FXML
    private ImageView cachemoi;
    JavaApplication unefois=new JavaApplication();
    int deja=0;
    @FXML
    private AnchorPane rentBicycle;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private TextField price;
    @FXML
    private AnchorPane dispa;
    public Velo veloSelectionner=null;
    private String debutDate="";
    private String finDate="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeIn(parent).play();
        new FadeIn(premiereAffichage).play();
        HBox toolbar;
        VBox toolbox;
         
        WebView webView = new WebView();
       
        
        WebEngine webEngine = webView.getEngine();               
              
            final URL urlHello = getClass().getResource("map_global.html");
            webEngine.load(urlHello.toExternalForm());
             
            webEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener<Worker.State>(){
                         
                        @Override
                        public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                            if(newState == Worker.State.SUCCEEDED){
                                JSObject window = (JSObject)webEngine.executeScript("window");
                                window.setMember("app",unefois);
                            }
                        }
                    });
             
             
            JSObject window = (JSObject)webEngine.executeScript("window");
            window.setMember("app",unefois);
             
            final TextField textField = new TextField ();
            textField.setPromptText("Hello! Who are?");
             
            Button buttonEnter = new Button("Enter");
            buttonEnter.setPrefHeight(40);
            buttonEnter.setPrefWidth(128);
            buttonEnter.setLayoutX(235);
            buttonEnter.setLayoutY(372);
            buttonEnter.setStyle("-fx-background-color:#305891;-fx-cursor:HAND");
            buttonEnter.setTextFill(Color.WHITE);
            buttonEnter.setOnAction(new EventHandler<ActionEvent>(){
                 
                @Override
                public void handle(ActionEvent arg0) {
                    premiereAffichage.setVisible(false);
                    cachemoi.setStyle("-fx-opacity:0");
                    cachemoi.setVisible(false);
                            new Thread(()->{
            try {
                Thread.sleep(300);
                dispa.setVisible(false);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
        
                    VeloC cn=new VeloC();
                    JSONArray jsonInfo = new JSONArray();
                    JSONArray jsonLatitude = new JSONArray();
                    JSONArray jsonLongitude = new JSONArray();
                    JSONArray jsonIdVelo = new JSONArray();
                    
                    JSONArray jsonType = new JSONArray();
                   
                    for(Velo cm:cn.afficher()){
                        String vc="type : "+cm.getType()+"</br> address : "+cm.getAdresse()+"</br>"+ "quantity : "+cm.getQte()+"</br> price :"+cm.getPrix()+" </br>";
                        jsonInfo.put(vc);
                        jsonLatitude.put(String.valueOf(cm.getLatitude()));
                        jsonLongitude.put(String.valueOf(cm.getLongitude()));
                        jsonIdVelo.put(String.valueOf(cm.getId()));
                        jsonType.put("velo");
                     
                    }
                    System.out.println(jsonInfo);
                    System.out.println(jsonLatitude);
                    System.out.println(jsonLongitude);
                    webEngine.executeScript("shGo("+test.drive.infoMapping.latitude+","+test.drive.infoMapping.longitude+",'"+test.drive.client.getLogin()+"',"+jsonInfo.toString()+","+jsonLatitude.toString().replaceAll("\"", "")+","+jsonLongitude.toString().replaceAll("\"", "")+","+jsonIdVelo.toString().replaceAll("\"", "")+","+jsonType.toString()+")");
                }
            });

            webView.setPrefWidth(parent.getPrefWidth());
            webView.setPrefHeight(parent.getPrefHeight());
            AnchorPane p=(AnchorPane) parent.getChildren().get(0);
            
            parent.getChildren().clear();
            parent.getChildren().add(webView); 
            //parent.getChildren().add(toolbox);
            parent.getChildren().add(p);
            premiereAffichage.getChildren().add(buttonEnter);
            
                                                  new Timer().schedule(
    new TimerTask() {

        @Override
        public void run() {
            
            String ds="";
            String dc="";
            if(dateEnd.getValue()==null || dateStart.getValue()==null){
                ds="";
                dc="";
            }
           else{
                if(!debutDate.equals(dateStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) || !finDate.equals(dateEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
               try {
                   debutDate=dateStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                   finDate=dateEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                   java.util.Date parsedDate = dateFormat.parse(debutDate);
                   Timestamp timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
                   java.util.Date parsedDate2 = dateFormat.parse(finDate);
                   Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
                   
                   float prix=models.Utils.FonctionsPartages.
                           calculerPrixParraportAuTemps(veloSelectionner.getPrix(),
                                   models.Utils.FonctionsPartages.calculerNombreSeconde(timestamp1,timestamp2));
                   price.setText(String.valueOf(prix));
               } catch (ParseException ex) {
                   Logger.getLogger(ControlerIndexClientPage.class.getName()).log(Level.SEVERE, null, ex);
               }
            

           }
            }
 
        
    
        }
    }, 0, 300);
    }    

    @FXML
    private void RsetLocation(MouseEvent event) {
                    rentBicycle.setVisible(false);
                    cachemoi.setVisible(false);
                    cachemoi.setStyle("-fx-opacity:0");
                                                new Thread(()->{
            try {
                Thread.sleep(300);
                dispa.setVisible(false);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(drive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @FXML
    private void saveLocation(MouseEvent event) throws ParseException, IOException {
        
        String date1=dateStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String date2=dateEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            java.util.Date parsedDate = dateFormat.parse(date1);
            Timestamp timestamp1 = new java.sql.Timestamp(parsedDate.getTime());
            java.util.Date parsedDate2 = dateFormat.parse(date2);
            Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
            
        Location l=new Location(test.drive.client,veloSelectionner,timestamp1,timestamp2);
        LocationC lc=new LocationC();
        String resultat=lc.ajouterLocation(l);
        if(resultat.equals("ok")){
           models.Utils.FonctionsPartages.notification("success Add", resultat, "success");
                                           Parent root;
                        root = FXMLLoader.load(getClass().getResource("/views/FXMLConsulterLocation.fxml"));
                        jun_copy.setCenter(root);
        }else{
           models.Utils.FonctionsPartages.notification("Error Add", resultat, "warning");
        }

    }
    
        
            public class JavaApplication {
        public void returnItem(JSObject msg) {
        int id=Integer.parseInt(msg.getMember("id").toString());
        String type=msg.getMember("type").toString();
        
        if(type.equals("velo")){
            VeloC vc=new VeloC();
            veloSelectionner=vc.retournerVelo(id);
                        AnchorPane p=new AnchorPane();
            p.setLayoutX(1000);
            p.setLayoutY(50);
            p.setPrefWidth(280);
            p.setPrefHeight(350);
            p.setStyle("-fx-background-color:#595959;-fx-opacity:0;-fx-background-radius:5;-fx-opacity:0.5");
            
            if(deja==0){
            parent.getChildren().add(p); 
            deja=1;
            }else{
                new FadeOutRight(p).play(); 
                parent.getChildren().remove(parent.getChildren().size() - 1);
            parent.getChildren().add(p);
            deja=0;
            }
            new FadeInLeft(p).play(); 
            ImageView img=new ImageView(new Image("http://localhost/drive/uploads/"+veloSelectionner.getPhoto()));
            img.setLayoutX(65);
            img.setLayoutY(40);
            img.setFitWidth(150);
            img.setFitHeight(150);
            p.getChildren().add(img);
            
            
            Button buttonEnter = new Button("rent a bicycle");
            buttonEnter.setPrefHeight(40);
            buttonEnter.setPrefWidth(128);
            buttonEnter.setLayoutX(75);
            buttonEnter.setLayoutY(210);
            buttonEnter.setStyle("-fx-background-color:#305891;-fx-cursor:HAND");
            buttonEnter.setTextFill(Color.WHITE);
            buttonEnter.setOnAction(new EventHandler<ActionEvent>(){
                 
                @Override
                public void handle(ActionEvent arg0) {
                    dispa.setVisible(true);
                     rentBicycle.setVisible(true);
                    cachemoi.setVisible(true);
                    
                    new FadeIn(rentBicycle).play();
                    new FadeIn(cachemoi).play();                 
                }
            });
            p.getChildren().add(buttonEnter);
            
        }
        
        }
    }
    
}
