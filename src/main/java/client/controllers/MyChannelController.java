package client.controllers;

import client.models.VideoInfo;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static client.models.Main.*;

public class MyChannelController implements Initializable {
    @FXML
    private BorderPane homeMain_borderPain;
    @FXML
    private VBox postInHome_vBox;
    @FXML
    private ScrollPane post_scrollPane;
    @FXML
    private Pane toolBar_pane;
    @FXML
    private VBox toolBar_vBox;
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
    private Label channelName_label;
    @FXML
    private Label channelInfo_label;
    @FXML
    private Hyperlink aboutChennel_hyperLink;
    @FXML
    private Tab videos_tab;
    @FXML
    private VBox video_vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        request.channel(userAccount.channel_username);
        JSONObject response = read();
        channelName_label.setText(response.getString("channel_username"));
        channelInfo_label.setText("@" + response.getString("channel_username") + " • " +response.getInt("totalSubscribers")+ " subscribers • "+ response.getInt("totalVideos")+" videos");
        aboutChennel_hyperLink.setText(response.getString("channelDescription"));

        request.ChannelVideoList(userAccount.channel_username);
        response = read();
        JSONArray video_idList = response.getJSONArray("videoIdList");
        ArrayList<VideoInfo> videoInfoList = new ArrayList<>();
        for (int k = 0; k < video_idList.length(); k++) {
            int video_id = video_idList.getInt(k);
            request.video(video_id);
            JSONObject response2 = read();
            videoInfoList.add(new VideoInfo(video_id, response2));
        }


        for (int i = 0; i < videoInfoList.size(); i++) {

            try {
                VideoInfo video = videoInfoList.get(i);
                request.imageFile(video.id);
                byte[] imageBytes = readFile();


                File file = new File("src/main/resources/CACHE/imageCache" + "/img" + (i) + ".jpg");

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(imageBytes);

                FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../home/PostInHome.fxml")));
                AnchorPane pane = fxmlLoader.load();
                pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../home/PostInHomeStyle.css")).toExternalForm());

                pane.setId(String.valueOf(video.id));



                PostInHomeController postInHomeController = fxmlLoader.getController();
                postInHomeController.define(imageBytes, video.id, video.getTitle(), video.getChannel_name(), video.getTotal_view(), video.getCreation_time(),video);
                postInHomeController.setup();

                video_vBox.getChildren().add(pane);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../create/MyChannel.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../create/MyChannelStyle.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
