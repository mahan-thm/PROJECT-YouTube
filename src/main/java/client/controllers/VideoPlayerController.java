package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
    private Slider videoTimeline_slider;
    @FXML
    private ScrollPane videoPlayer_scrollPane;


    public void setup() {
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        video_mediaView.setMediaPlayer(mediaPlayer);
        video_mediaView.fitHeightProperty().bind(videoPlayer_scrollPane.widthProperty().subtract(254));
        video_mediaView.fitWidthProperty().bind(videoPlayer_scrollPane.widthProperty().subtract(254));

        mediaPlayer.totalDurationProperty().addListener((obs, oldDuration, newDuration) ->
                videoTimeline_slider.setMax(newDuration.toSeconds())
        );

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!videoTimeline_slider.isValueChanging()) {
                videoTimeline_slider.setValue(newTime.toSeconds());
            }
        });


        videoTimeline_slider.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            mediaPlayer.pause();
        });
//        mediaPlayer.setRate(2);
        videoTimeline_slider.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            mediaPlayer.seek(Duration.seconds(videoTimeline_slider.getValue()));
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
    public void goToHome_action(ActionEvent actionEvent) {
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
