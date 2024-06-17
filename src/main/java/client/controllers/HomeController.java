package client.controllers;

import client.models.Video;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        request.videoList(1, new JSONArray());
        JSONObject response1 = read();
//        JSONObject response1 = new JSONObject();
        JSONArray video_idList = response1.getJSONArray("videoIdList");
        ArrayList<Video> videoList = new ArrayList<>();

        for (int i = 0; i < video_idList.length(); i++) {
            int video_id = video_idList.getInt(i);
            request.video(video_id);
            JSONObject response2 = read();
            videoList.add(new Video(video_id, response2));
        }


        for (int i = 0; i < 6; i++) {
            HBox hBox = new HBox();
            hBox.setSpacing(15);
            hBox.setFillHeight(true);
            hBox.prefHeightProperty().bind(post_scrollPane.widthProperty());
            hBox.prefWidthProperty().bind(post_scrollPane.heightProperty());


            for (int j = 0; j < 3; j++) {
                try {


                    Video video = videoList.get(i * 3 + j);
                    request.imageFile(video.id);
                    byte[] imageBytes = readFile();
                    File file = new File("src/main/resources/imageCache" + "/img" + (i * 3 + j) + ".jpg");

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(imageBytes);

                    //to load fxml file
                    FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../home/PostInHome.fxml")));
                    AnchorPane pane = fxmlLoader.load();
                    pane.setId(String.valueOf(i * 3 + j));
                    PostInHomeController postInHomeController = fxmlLoader.getController();

                    //TODO
                    //to set up PostInHome fxml file
                    postInHomeController.define(imageBytes,video.id, video.getTitle(), video.getChannel_name(), video.getTotal_view(), video.getCreation_time());
//                    postInHomeController.define(imageBytes,"Ai Girl", "My Channel", "2k", "12 july");
                    postInHomeController.setup();


                    pane.prefWidthProperty().bind(post_scrollPane.widthProperty());
                    pane.prefHeightProperty().bind(post_scrollPane.widthProperty());
                    ((ImageView) ((VBox) pane.getChildren().get(0)).getChildren().get(0)).fitWidthProperty().bind(post_scrollPane.widthProperty().divide(3).subtract(20));
                    ((ImageView) ((VBox) pane.getChildren().get(0)).getChildren().get(0)).fitHeightProperty().bind(post_scrollPane.widthProperty().divide(3).subtract(20));


                    hBox.getChildren().add(pane);
                } catch (IOException e) {
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
