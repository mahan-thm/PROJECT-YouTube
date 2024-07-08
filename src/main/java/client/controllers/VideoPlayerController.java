package client.controllers;

import client.models.VideoInfo;
import javafx.animation.*;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static client.models.Main.*;

public class VideoPlayerController {
    private File file;
    private VideoInfo videoInfo;

    private boolean is_subscribed;
    private boolean is_liked;
    private boolean is_disliked;

    public void define(int video_id) { //like a constructor
        request.videoFile(video_id);
        byte[] videoBytes = readFile();

        File file = new File("src/main/resources/CACHE/videoCache" + "/video" + video_id + ".mp4");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(videoBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.file = file;
        request.video(video_id);
        JSONObject response2 = read();
        videoInfo = new VideoInfo(video_id, response2);

        request.channel(videoInfo.channel_username);
        JSONObject response = read();

        channelName_label.setText(response.getString("channelTitle"));
        totalSubscribers_label.setText(response.getInt("totalSubscribers") +" subscribers");
        video_lable.setText(videoInfo.getTitle());
        videoDiscription_label.setText(videoInfo.getTitle_body());
    }
    @FXML
    private Label videoDiscription_label;
    @FXML
    public Label totalSubscribers_label;
    @FXML
    public Label commentCount;
    @FXML
    private Label channelName_label;
    @FXML
    private MediaPlayer mediaPlayer;
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
    @FXML
    private BorderPane homeMain_borderPain;
    @FXML
    private VBox postInHome_vBox;
    @FXML
    private ScrollPane post_scrollPane;
    @FXML
    private Pane account_pane;
    @FXML
    private Pane fadeRectangle_pane;
    @FXML
    private Pane creat_pane;
    @FXML
    private Button accountProfHome_button;
    @FXML
    private ScrollPane toolBar_scrollPane;
    @FXML
    private Rectangle fadeRectangle_rectangle;
    @FXML
    private Button accountProfHome_button1;
    @FXML
    private HBox tags_hBox;
    @FXML
    private ScrollPane tags_scrollPane;
    @FXML
    private VBox videoController_vBox;
    @FXML
    private Button playAndPause_button;
    @FXML
    private Button mute_button;
    @FXML
    private Label duration_label;
    @FXML
    private VBox video_vBox;
    @FXML
    private VBox videoPlayer_vBox;
    @FXML
    private Circle videoProf_circle;
    @FXML
    public Button next_button;
    @FXML
    public Button fullScream_button;
    @FXML
    public Slider volume_slider;
    @FXML
    public Button speed_button;
    @FXML
    public Label video_lable;
    @FXML
    public Button subscribe_button;
    @FXML
    public Button like_button;
    @FXML
    public Button dislike_button;


    public void setup() {


        for (int i = 0; i < 6; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../videoPlayer/Comment.fxml")));
                AnchorPane anchorPane = fxmlLoader.load();

                anchorPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../videoPlayer/CommentStyle.css")).toExternalForm());
                CommentController commentController = fxmlLoader.getController();

                //TODO video comments
                commentController.define(); //defining the prof image, comment text, when commented & ...
                commentController.setup();

                videoComments_vBox.getChildren().add(anchorPane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            //TODO set prof
            request.channelProfileImg(videoInfo.channel_username);
            JSONObject response = read();
            byte[] videoBytes = readFile();

            File file = new File("src/main/resources/CACHE/imageCache/channelProf0.jpg");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(videoBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = new Image(Objects.requireNonNull(getClass().getResource("../../CACHE/imageCache/channelProf0.jpg")).openStream());
            videoProf_circle.setFill(new ImagePattern(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO to show recommended videos add PostInHome.fxml to "recommendVideo_vBox"

        //______________________________________________________________________________


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../videoPlayer/newComment.fxml")));
            GridPane gridPane = fxmlLoader.load();
            gridPane.getChildren().remove(((GridPane) gridPane.getChildren().get(2)).getChildren().remove(1));
            gridPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../videoPlayer/newCommentStyle.css")).toExternalForm());
            videoPlayer_vBox.getChildren().add(5, gridPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        video_mediaView.setMediaPlayer(mediaPlayer);
        video_mediaView.fitWidthProperty().bind(videoPlayer_scrollPane.widthProperty().subtract(349));
        video_mediaView.fitHeightProperty().bind(videoPlayer_scrollPane.widthProperty().subtract(349));


        mediaPlayer.totalDurationProperty().addListener((obs, oldDuration, newDuration) ->
                videoTimeline_slider.setMax(newDuration.toSeconds())
        );

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!videoTimeline_slider.isValueChanging()) {
                videoTimeline_slider.setValue(newTime.toSeconds());

                String totalTimeStr = mediaPlayer.getTotalDuration().toString();
                String currentTimeStr = mediaPlayer.getCurrentTime().toString();
                double totalDuration = Double.parseDouble(totalTimeStr.substring(0, totalTimeStr.length() - 3));
                System.out.println(media.getDuration());
                double currentDuration = Double.parseDouble(currentTimeStr.substring(0, currentTimeStr.length() - 3));
                System.out.println(mediaPlayer.getCurrentTime());
                double percent = currentDuration / totalDuration * 100;
                System.out.println(percent);
                videoTimeline_slider.setStyle("-fx-background-color: linear-gradient(to right, green 0%, green " + percent + "%, gray " + percent + "%, gray 100%);");
            }
        });


        videoTimeline_slider.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            mediaPlayer.pause();
            playAndPause_button.setStyle("-fx-shape: 'M 12,26 18.5,22 18.5,14 12,10 z M 18.5,22 25,18 25,18 18.5,14 z'");
        });
//        mediaPlayer.setRate(2);
        videoTimeline_slider.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            mediaPlayer.seek(Duration.seconds(videoTimeline_slider.getValue()));
            playAndPause_button.setStyle("-fx-shape: 'M 12,26 16,26 16,10 12,10 z M 21,26 25,26 25,10 21,10'");
            mediaPlayer.play();
        });


        mediaPlayer.setOnReady(() -> {
            Duration totalDuration = media.getDuration();
            Duration currentDuration = mediaPlayer.getCurrentTime();
            String totalDurationStr = formatTime(totalDuration);
            duration_label.setText(formatTime(currentDuration) + " / " + totalDurationStr);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), actionEvent ->
                    {
                        Duration currentDuration1 = mediaPlayer.getCurrentTime();
                        duration_label.setText(formatTime(currentDuration1) + " / " + totalDurationStr);
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });


    }

    private String formatTime(Duration time) {
        long seconds = (long) time.toSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        if (hours != 0) {
            return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
        } else {
            return String.format("%02d:%02d", minutes % 60, seconds % 60);

        }
    }

    @FXML
    public void toolBar_action() {
        boolean show = toolBar_pane.isVisible();


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), toolBar_pane);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), fadeRectangle_pane);
        if (!show) {
            toolBar_pane.setLayoutX(-200);
            translateTransition.setToX(200);
            toolBar_pane.setVisible(!show);
            toolBar_vBox.setVisible(show);
            fadeRectangle_pane.setVisible(!show);

            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
        } else {
            toolBar_pane.setLayoutX(-200);
            translateTransition.setToX(-400);
            translateTransition.setOnFinished(actionEvent -> {
                toolBar_pane.setVisible(!show);
                toolBar_vBox.setVisible(show);
                fadeRectangle_pane.setVisible(!show);
            });

            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
        }
        fadeTransition.play();
        translateTransition.play();
    }

    @FXML
    public void account_action() {
        account_pane.setVisible(true);
        fadeRectangle_pane.setVisible(true);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), account_pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), fadeRectangle_pane);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);

        fadeTransition.play();
        fadeTransition1.play();
    }

    @FXML
    public void create_action() {
        fadeRectangle_pane.setVisible(true);
        creat_pane.setVisible(true);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), creat_pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), fadeRectangle_pane);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);

        fadeTransition.play();
        fadeTransition1.play();
    }

    @FXML
    public void closeBars() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), fadeRectangle_pane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(actionEvent -> {
            fadeRectangle_pane.setVisible(false);
        });
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), toolBar_pane);
        toolBar_pane.setLayoutX(-200);
        translateTransition.setToX(-400);
        translateTransition.setOnFinished(actionEvent -> {
            toolBar_pane.setVisible(false);
            toolBar_vBox.setVisible(true);
        });
        fadeTransition.play();
        translateTransition.play();

        account_pane.setVisible(false);
        creat_pane.setVisible(false);
    }

    @FXML
    public void uploadVideo_action() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../create/UploadFile.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("../../create/UploadFileStyle.css")).toExternalForm();
        scene.getStylesheets().add(css);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Upload file");
        stage.setScene(scene);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../create/uploadImage.jpg")));
        stage.getIcons().add(icon);
        stage.show();
    }

    @FXML
    public void refresh_action(ActionEvent actionEvent) {
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

    @FXML
    public void myChannel_action(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../creat/MyChannel.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../creat/MyChannelStyle.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void signOut_action(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../entry/Login.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../entry/LoginStyle.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void on_like(ActionEvent actionEvent) {
        if (!is_liked) {
            request.likeVideo(videoInfo.id);
            JSONObject response = read();


            if (response.getString("responseType").equals("/likeVideo_accepted"))
            {
                System.out.println("/likeVideo_accepted");
                //todo show video is liked
                is_liked = true;
            }
            else{
                System.out.println(response.getString("responseType"));
            }
        }
        else {
            request.remove_likedVideo(videoInfo.id);
            JSONObject response = read();
            if (response.getString("responseType").equals("/remove_likedVideo_rejected"))
            {
                System.out.println("/remove_likedVideo");
                //todo show video is not liked
                is_liked = false;
            }
            else {
                System.out.println(response.getString("responseType"));
            }
        }
    }
    @FXML
    public void on_dislike(ActionEvent actionEvent) {
        if (!is_disliked) {
            request.dislikeVideo(videoInfo.id);
            JSONObject response = read();


            if (response.getString("responseType").equals("/dislikeVideo_accepted"))
            {
                System.out.println("/dislikeVideo_accepted");
                //todo show video is disliked
                is_disliked = true;
            }
            else{
                System.out.println(response.getString("responseType"));
            }
        }
        else {
            request.remove_dislikedVideo(videoInfo.id);
            JSONObject response = read();
            if (response.getString("responseType").equals("/remove_dislikedVideo_rejected"))
            {
                System.out.println("/remove_dislikedVideo");
                //todo show video is not disliked
                is_disliked = false;
            }
            else {
                System.out.println(response.getString("responseType"));
            }
        }
    }
    @FXML
    public void on_subscribe(ActionEvent actionEvent) {

        if (!is_subscribed) {
            request.subscribeChannel(videoInfo.channel_username);
            JSONObject response = read();
            if (response.getString("responseType").equals("/subscribeChannel_accepted")) ;
            {
                System.out.println("/subscribeChannel_accepted");
                subscribe_button.setText("subscribed");
                is_subscribed = true;
            }
        }
        else {
            request.unsubscribeChannel(videoInfo.channel_username);
            JSONObject response = read();
            if (response.getString("responseType").equals("/unsubscribeChannel_accepted")) ;
            {
                System.out.println("/unsubscribeChannel_accepted");
                subscribe_button.setText("subscribe");
                is_subscribed = false;
            }
        }
    }
    //__________________________________________________________________________________________________________
    //________________________________________________PRIVET____________________________________________________
    //__________________________________________________________________________________________________________

    private Timeline delayTimeline = new Timeline(
            new KeyFrame(Duration.seconds(3), event ->
            {
                videoController_vBox.setVisible(true);
                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), videoController_vBox);
                fadeTransition1.setFromValue(1);
                fadeTransition1.setToValue(0);
                fadeTransition1.setOnFinished(actionEvent ->
                        videoController_vBox.setVisible(false)
                );
                fadeTransition1.play();
            }
            ));

    @FXML
    public void showVideoController_action() {


        if (!videoController_vBox.isVisible()) {
            videoController_vBox.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), videoController_vBox);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            delayTimeline.play();
        } else {
            delayTimeline.playFrom(Duration.seconds(0));
        }


    }

    @FXML
    public void playPauseVide_action() {
        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            mediaPlayer.pause();
            playAndPause_button.setStyle("-fx-shape: 'M 12,26 18.5,22 18.5,14 12,10 z M 18.5,22 25,18 25,18 18.5,14 z'");
        } else {
            playAndPause_button.setStyle("-fx-shape: 'M 12,26 16,26 16,10 12,10 z M 21,26 25,26 25,10 21,10'");
            mediaPlayer.play();
        }
    }

    @FXML
    public void next_action() {
        //TODO
        //start the next video for video player

    }

    @FXML
    public void muteUnmute_action() {
        if (mediaPlayer.isMute()) {
            mediaPlayer.setMute(false);
            mute_button.setStyle("-fx-shape: 'M8,21 L12,21 L17,26 L17,10 L12,15 L8,15 L8,21 Z M19,14 L19,22 C20.48,21.32 21.5,19.77 21.5,18 C21.5,16.26 20.48,14.74 19,14 ZM19,11.29 C21.89,12.15 24,14.83 24,18 C24,21.17 21.89,23.85 19,24.71 L19,26.77 C23.01,25.86 26,22.28 26,18 C26,13.72 23.01,10.14 19,9.23 L19,11.29 Z'");
        } else {
            mediaPlayer.setMute(true);
            mute_button.setStyle("-fx-shape: 'm 21.48,17.98 c 0,-1.77 -1.02,-3.29 -2.5,-4.03 v 2.21 l 2.45,2.45 c .03,-0.2 .05,-0.41 .05,-0.63 z m 2.5,0 c 0,.94 -0.2,1.82 -0.54,2.64 l 1.51,1.51 c .66,-1.24 1.03,-2.65 1.03,-4.15 0,-4.28 -2.99,-7.86 -7,-8.76 v 2.05 c 2.89,.86 5,3.54 5,6.71 z M 9.25,8.98 l -1.27,1.26 4.72,4.73 H 7.98 v 6 H 11.98 l 5,5 v -6.73 l 4.25,4.25 c -0.67,.52 -1.42,.93 -2.25,1.18 v 2.06 c 1.38,-0.31 2.63,-0.95 3.69,-1.81 l 2.04,2.05 1.27,-1.27 -9,-9 -7.72,-7.72 z m 7.72,.99 -2.09,2.08 2.09,2.09 V 9.98 z'");
        }
    }

    private int i = 1;

    @FXML
    public void speed_action() {
        if (i == 0) {
            mediaPlayer.setRate(1);
            i = (i + 1) % 3;
        } else if (i == 1) {
            mediaPlayer.setRate(2);
            i = (i + 1) % 3;
        } else if (i == 2) {
            mediaPlayer.setRate(0.5);
            i = (i + 1) % 3;
        }
    }

    @FXML
    public void fullScreen_action(ActionEvent actionEvent) {
        Pane pane = new Pane(video_vBox);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

}
