package client.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.ScrollBarSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class UploadFileController implements Initializable {

    private String selectedPath;
@FXML
private AnchorPane uploadVideo_anchorPain;
    @FXML
    private ImageView upload_imageView;
    @FXML
    private Label selectedFile_label;
    @FXML
    private ScrollPane details_scrollPane;
    @FXML
    private Pane selectFile_pane;
    @FXML
    private Pane details_pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
        fadeTransition.setNode(uploadVideo_anchorPain);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
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
            Image uploadImage2 = new Image(Objects.requireNonNull(getClass().getResource("../../creat/uploadImage2.jpg")).openStream());

            upload_imageView.setImage(uploadGif);

            Timeline delayTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(2.566667), event ->
                            upload_imageView.setImage(uploadImage2)
                    )
            );
            delayTimeline.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void close_action(ActionEvent actionEvent) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
        fadeTransition.setNode(uploadVideo_anchorPain);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(actionEvent1 -> {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        });
        fadeTransition.play();
    }

    @FXML
    public void next_action() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(1));
        fadeTransition1.setNode(selectFile_pane);
        fadeTransition1.setFromValue(1);
        fadeTransition1.setToValue(0);

        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2));
        fadeTransition2.setNode(details_pane);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);

        selectFile_pane.setVisible(true);
        details_pane.setVisible(true);
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1.5));
        translateTransition1.setNode(selectFile_pane);
        translateTransition1.setToX(-750);


        details_pane.setLayoutX(750);
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1.5));
        translateTransition2.setNode(details_pane);
        translateTransition2.setToX(-750);
        translateTransition1.setOnFinished(actionEvent -> {
                    selectFile_pane.setVisible(false);
                }
        );
        fadeTransition1.play();
        fadeTransition2.play();
        translateTransition1.play();
        translateTransition2.play();
    }

    public void refresh_action(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../creat/UploadFile.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("../../creat/UploadFileStyle.css")).toExternalForm();
        scene.getStylesheets().add(css);
        scene.setFill(Color.TRANSPARENT);
//        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Upload file");
        stage.setScene(scene);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../creat/uploadImage.jpg")));
        stage.getIcons().add(icon);
        stage.show();
    }
}
