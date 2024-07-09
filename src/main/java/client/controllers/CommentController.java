package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CommentController {

    public void define() {
        //TODO
    }

    @FXML
    private Hyperlink cmntChannelUsername_hyperLink;
    @FXML
    private Label cmntText_label;
    @FXML
    private Button like_button;
    @FXML
    private VBox videoComment_vBox;
    @FXML
    private Label whenCommented_label;
    @FXML
    private Circle commentProf_circle;

    public void setup() {
        //TODO
        cmntChannelUsername_hyperLink.setText("@channel username");
        cmntText_label.setText("hi there. this is test comment");
        whenCommented_label.setText("2 months ago");

        //TODO set comment profile
        try {
            //TODO set profile of who commenting
            Image image = new Image(Objects.requireNonNull(getClass().getResource("../../CACHE/imageCache/img3.jpg")).openStream());
            commentProf_circle.setFill(new ImagePattern(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void likeComment_action() {
        //TODO
        like_button.setText("20k");
    }

    @FXML
    public void dislikeComment_button() {
        //TODO
        like_button.setText("20k");
    }

    @FXML
    public void replyComment_action() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../videoPlayer/newComment.fxml")));
            GridPane gridPane = fxmlLoader.load();
            ((Button)((GridPane) gridPane.getChildren().get(3)).getChildren().get(2)).setText("Reply");
            gridPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../videoPlayer/newCommentStyle.css")).toExternalForm());
            videoComment_vBox.getChildren().add(3, gridPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showCommentReplies_action(){
        //TODO show comment replies
    }
}
