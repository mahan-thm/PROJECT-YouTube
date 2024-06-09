package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private VBox postInHome_vBox;
    @FXML
    private ScrollPane post_scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i = 0; i < 5; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(15);
            hBox.setFillHeight(true);
//            hBox.prefWidthProperty().bind(post_scrollPane.widthProperty());
            hBox.prefHeightProperty().bind(post_scrollPane.widthProperty());
            hBox.prefWidthProperty().bind(post_scrollPane.heightProperty());


//            hBox.setMinWidth(post_scrollPane.getWidth());
            for (int j = 0; j < 3; j++) {
                try {
//                PostInHomeController postInHomeController = new PostInHomeController("C:\\Users\\17203\\Pictures\\WallPaper\\girl_AI-generated_wallpapers-4.jpg");
                    AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../home/PostInHome.fxml")));

                    pane.prefWidthProperty().bind(post_scrollPane.widthProperty());
                    pane.prefHeightProperty().bind(post_scrollPane.widthProperty());
                    ((ImageView)((VBox)pane.getChildren().get(0)).getChildren().get(0)).fitWidthProperty().bind(pane.widthProperty());
                    ((ImageView)((VBox)pane.getChildren().get(0)).getChildren().get(0)).fitHeightProperty().bind(pane.widthProperty());

                    hBox.getChildren().add(pane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            postInHome_vBox.getChildren().add(hBox);
        }

    }



}
