package client.controllers;

import client.models.Video;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

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
    @FXML
    private Pane account_pane;
    @FXML
    private Pane fadeRectangle_pane;
    @FXML
    private Pane creat_pane;
    @FXML
    private Button accountProfHome_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //____________________________________PROFILE PHOTO__________________________________________
        //TODO
            String profPath = "-fx-background-image: url('CACHE/imageCache/img0.jpg')";
            request.profileImg();
            if (read().getString("responseType").equals("/profileImg_accepted")){

                byte[] profImgBytes = readFile();


                File file = new File("src/main/resources/CACHE/imageCache/img0.jpg");

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(profImgBytes);

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                accountProfHome_button.setStyle(profPath);
            }
        //_________________________________________VIDEO THUMBNAIL___________________________________________
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


                    File file = new File("src/main/resources/CACHE/imageCache" + "/img" + (i * 3 + j) + ".jpg");
                    // to resize image
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(imageBytes);

//                  imageBytes = resize(file);
                    //to load fxml file
                    FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../home/PostInHome.fxml")));
                    AnchorPane pane = fxmlLoader.load();
                    pane.setId(String.valueOf(i * 3 + j));
                    PostInHomeController postInHomeController = fxmlLoader.getController();

                    //TODO
                    //to set up PostInHome fxml file
                    postInHomeController.define(imageBytes, video.id, video.getTitle(), video.getChannel_name(), video.getTotal_view(), video.getCreation_time());
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
        //_______________________________________________________________________________________________________
    }

    public static byte[] resize(File icon) {
        try {
            BufferedImage originalImage = ImageIO.read(icon);

//            originalImage= Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 128, 153);
            //To save with original ratio uncomment next line and comment the above.
            originalImage = Scalr.resize(originalImage, 306, 256);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (Exception e) {
            return null;
        }


    }

    @FXML
    public void toolBar_action() {
        boolean show = toolBar_pane.isVisible();

        toolBar_pane.setVisible(!show);
        toolBar_vBox.setVisible(show);
    }

    @FXML
    public void account_action() {
//        boolean show = account_pane.isVisible();
//        account_pane.setVisible(!show);
        account_pane.setVisible(true);
        fadeRectangle_pane.setVisible(true);
    }

    @FXML
    public void create_action() {
        fadeRectangle_pane.setVisible(true);
        creat_pane.setVisible(true);
    }

    @FXML
    public void closeBars() {
        fadeRectangle_pane.setVisible(false);
        account_pane.setVisible(false);
        creat_pane.setVisible(false);

    }

}
