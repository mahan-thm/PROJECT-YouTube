package client.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.ScrollBarSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class UploadFileController implements Initializable {

    private String selectedPath;

    @FXML
    private ImageView upload_imageView;
    @FXML
    private Label selectedFile_label;
    @FXML
    private ScrollPane details_scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void selectFile_action(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");
        FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 Files", "*.mp4");
        fileChooser.getExtensionFilters().addAll(mp4Filter);
        File selectedFile = fileChooser.showOpenDialog(stage);

        selectedPath = selectedFile.getAbsolutePath();
        selectedFile_label.setText("SELECTED FILE:\n" + selectedPath);

        try {
            Image uploadGif = new Image(Objects.requireNonNull(getClass().getResource("../../creat/uploadGif.gif")).openStream());
            upload_imageView.setImage(uploadGif);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Timeline delayTimeline = new Timeline(
                new KeyFrame(Duration.seconds(2.766667), event ->
                        upload_imageView.setVisible(false)
                )
        );
        delayTimeline.play();

    }

    public void close_action(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
