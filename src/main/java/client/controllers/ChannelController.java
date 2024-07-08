package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChannelController {

    public void define() {
        //TODO define the privet elements
    }

    @FXML
    private Label channelName_label;
    @FXML
    private Label channelUserSubVid_label;
    @FXML
    private Hyperlink aboutChannel_hyperLink;
    @FXML
    private Tab videos_tab;

    public void setup() {
        //TODO setup the scene
        channelName_label.setText("");
        channelUserSubVid_label.setText("@username • 10k subscribers • 15 video");
        aboutChannel_hyperLink.setText("");

        //TODO add channel videos to videos_tab
    }

    @FXML
    public void subscribe_action(){
        //TODO client will subscribe the channel by pressing this button
    }
}
