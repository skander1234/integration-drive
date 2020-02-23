/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import animatefx.animation.FadeIn;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Armand
 */
public class FXMLAboutPageController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private MediaView mediaView;
    @FXML
    private AnchorPane rte;
    @FXML
    private ImageView img_function;
    @FXML
    private Rectangle head2;
    @FXML
    private ImageView imgf;
    @FXML
    private ImageView show_image;
    @FXML
    private ImageView imgf1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeIn(rootPane).play();
                String path= new File("src/views/video/background.mp4").getAbsolutePath();
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }    
    
}
