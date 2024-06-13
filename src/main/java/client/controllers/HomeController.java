package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private VBox postInHome_vBox;
    @FXML
    private ScrollPane post_scrollPane;
    @FXML
    private Pane toolBar_pane;
    @FXML
    private VBox toolBar_vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i = 0; i < 3; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(15);
            hBox.setFillHeight(true);
            hBox.prefHeightProperty().bind(post_scrollPane.widthProperty());
            hBox.prefWidthProperty().bind(post_scrollPane.heightProperty());


            for (int j = 0; j < 3; j++) {
                try {

                    //to convert an image to bytes
                    BufferedImage bufferedImage = ImageIO.read(new File(Objects.requireNonNull(getClass().getResource("../../home/girl_AI-generated_wallpapers-4.jpg")).toURI()));
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bos.close();
                    ImageIO.write(bufferedImage, "png", bos);
                    byte[] imageBytes = bos.toByteArray();

                    //to load fxml file
                    FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../home/PostInHome.fxml")));
                    AnchorPane pane = fxmlLoader.load();
                    PostInHomeController postInHomeController = fxmlLoader.getController();

                    //TODO
                    //to set up PostInHome fxml file
                    postInHomeController.define(imageBytes, "AI Girl", "MyChanel", "25k", "12 march");
                    postInHomeController.setup();


                    pane.prefWidthProperty().bind(post_scrollPane.widthProperty());
                    pane.prefHeightProperty().bind(post_scrollPane.widthProperty());
                    ((ImageView) ((VBox) pane.getChildren().get(0)).getChildren().get(0)).fitWidthProperty().bind(pane.widthProperty());
                    ((ImageView) ((VBox) pane.getChildren().get(0)).getChildren().get(0)).fitHeightProperty().bind(pane.widthProperty());


                    hBox.getChildren().add(pane);
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
            postInHome_vBox.getChildren().add(hBox);
        }


    }


    public void toolBar_action() {
        boolean toolbarShow = toolBar_pane.isVisible();

        toolBar_pane.setVisible(!toolbarShow);
        toolBar_vBox.setVisible(toolbarShow);
    }


}
