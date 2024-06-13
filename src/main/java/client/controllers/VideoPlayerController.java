package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class VideoPlayerController {
    private File file;


    public void define() { //like a constructor
        this.file = new File(Objects.requireNonNull(getClass().getResource("../../VideoPlayer/VideoTest.mp4")).getFile());
    }


    @FXML
    private Pane toolBar_pane;
    @FXML
    private VBox toolBar_vBox;
    @FXML
    private VBox videoComments_vBox;
    @FXML
    private VBox recommendVideo_vBox;
    @FXML
    private MediaView video_mediaView;
    @FXML
    private Slider videoTimline_slider;
    @FXML
    private VBox first_vBox;


    public void setup(){
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        video_mediaView.setMediaPlayer(mediaPlayer);
        video_mediaView.fitHeightProperty().bind(first_vBox.widthProperty());
        video_mediaView.fitWidthProperty().bind(first_vBox.widthProperty());

        mediaPlayer.totalDurationProperty().addListener((obs, oldDuration, newDuration) ->
                videoTimline_slider.setMax(newDuration.toSeconds())
        );

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!videoTimline_slider.isValueChanging()) {
                videoTimline_slider.setValue(newTime.toSeconds());
            }
        });


        videoTimline_slider.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            mediaPlayer.pause();
        });

        videoTimline_slider.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            mediaPlayer.seek(Duration.seconds(videoTimline_slider.getValue()));
            mediaPlayer.play();
        });

        mediaPlayer.play();

    }

    public void toolBar_action() {
        boolean toolbarShow = toolBar_pane.isVisible();

        toolBar_pane.setVisible(!toolbarShow);
        toolBar_vBox.setVisible(toolbarShow);
    }

    @FXML
    public void goToHome_action(ActionEvent actionEvent){
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../home/Home.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../home/HomeStyle.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
