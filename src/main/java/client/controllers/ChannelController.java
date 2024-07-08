package client.controllers;

import client.models.VideoInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static client.models.Main.*;

public class ChannelController {
    String channel_username ;
    private boolean is_subscribed ;
    public void define(VideoInfo videoinfo) {
        request.channel(videoinfo.channel_username);
        JSONObject response = read();

        channelName_label.setText(response.getString("channelTitle"));
        channelUserSubVid_label.setText("@"+response.getString("channel_username")
                +" • "+response.getInt("totalSubscribers")
                +" subscribers • "+response.getInt("totalSubscribers")+" video");
        aboutChannel_hyperLink.setText(response.getString("channelDescription"));
        channel_username = response.getString("channel_username");
        is_subscribed = response.getBoolean("is_subscribed");

        if (is_subscribed){
            subscribe_button.setText("subscribed");
        }


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
    private Label channelName_label;
    @FXML
    private Label channelUserSubVid_label;
    @FXML
    private Hyperlink aboutChannel_hyperLink;
    @FXML
    private Tab videos_tab;
    @FXML
    private VBox video_vBox;
    @FXML
    private Button subscribe_button ;



    public void setup() {
        //TODO setup the scene
//        channelName_label.setText("");
//        channelUserSubVid_label.setText("@username • 10k subscribers • 15 video");
//        aboutChannel_hyperLink.setText("");

        //TODO add channel videos to videos_tab
    }

    @FXML
    public void subscribe_action(){

        if (!is_subscribed) {
            request.subscribeChannel(channel_username);
            JSONObject response = read();
            if (response.getString("responseType").equals("/subscribeChannel_accepted")) ;
            {
                System.out.println("/subscribeChannel_accepted");
                subscribe_button.setText("subscribed");
                is_subscribed = true;
            }
        }
        else {
            request.unsubscribeChannel(channel_username);
            JSONObject response = read();
            if (response.getString("responseType").equals("/unsubscribeChannel_accepted")) ;
            {
                System.out.println("/unsubscribeChannel_accepted");
                subscribe_button.setText("subscribe");
                is_subscribed = false;
            }
        }
    }

    public void toolBar_action(ActionEvent actionEvent) {
    }

    public void create_action(ActionEvent actionEvent) {
    }

    public void account_action(ActionEvent actionEvent) {

    }

    public void closeBars(MouseEvent mouseEvent) {
    }
}
