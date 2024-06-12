package client.controllers;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VideoPlayerController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        File file = new File(Objects.requireNonNull(getClass().getResource("../../VideoPlayer/VideoTest.mp4")).getFile());
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        video_mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.totalDurationProperty().addListener((obs, oldDuration, newDuration) ->
                videoTimline_slider.setMax(newDuration.toSeconds())
        );

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!videoTimline_slider.isValueChanging()) {
                videoTimline_slider.setValue(newTime.toSeconds());
            }
        });


//        videoTimline_slider.valueChangingProperty().addListener((obs, wasChanging, isNowChanging) -> {
//                if (!isNowChanging) {
//                    mediaPlayer.seek(Duration.seconds(videoTimline_slider.getValue()));
//                }
//            });


        videoTimline_slider.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            mediaPlayer.seek(Duration.seconds(videoTimline_slider.getValue()));
        });

        //lag
//        videoTimline_slider.valueProperty().addListener((obs, oldValue, newValue) -> {
//            if (!videoTimline_slider.isValueChanging()) {
//                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
//            }
//        });

        videoTimline_slider.setOnMouseDragExited((MouseEvent mouseEvent) -> {
            mediaPlayer.seek(Duration.seconds(videoTimline_slider.getValue()));
        });

            mediaPlayer.play();

            for (int i = 0; i < 60; i++) {
                Label label = new Label("Hi");
                videoComments_vBox.getChildren().add(label);
            }
        }

        public void toolBar_action () {
            boolean toolbarShow = toolBar_pane.isVisible();

            toolBar_pane.setVisible(!toolbarShow);
            toolBar_vBox.setVisible(toolbarShow);
        }

    }
