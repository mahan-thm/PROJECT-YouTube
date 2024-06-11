package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class PostInHomeController implements Initializable {

    @FXML
    private ImageView imageThumbnail_imageView;
    @FXML
    private AnchorPane postInHome_anchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        imageThumbnail_imageView.
        Image image = null;
        try {
            image = new Image(Objects.requireNonNull(getClass().getResource("../../home/girl_AI-generated_wallpapers-4.jpg")).openStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageThumbnail_imageView.setImage(image);
    }
}
