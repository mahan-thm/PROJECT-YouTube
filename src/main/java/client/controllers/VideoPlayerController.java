package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 0; i < 60; i++){
            Label label = new Label("Hi");
            videoComments_vBox.getChildren().add(label);
        }
    }

    public void toolBar_action() {
        Boolean toolbarShow = toolBar_pane.isVisible();

        toolBar_pane.setVisible(!toolbarShow);
        toolBar_vBox.setVisible(toolbarShow);
    }

}
