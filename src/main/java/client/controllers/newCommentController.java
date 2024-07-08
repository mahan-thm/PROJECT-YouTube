package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class newCommentController implements Initializable {
    @FXML
    private TextArea newComment_textArea;
    @FXML
    private GridPane newComment_gridPane;
    @FXML
    private Button profile_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        newComment_textArea.setPrefRowCount(1);
        //TODO
//        String profPath = "-fx-background-image: url('')";
//        profile_button.setStyle(profPath);


        newComment_textArea.textProperty().addListener((observable, oldValue, newValue) -> {

            Text tempText = new Text(newValue);
            tempText.setFont(newComment_textArea.getFont());
            tempText.setWrappingWidth(newComment_textArea.getWidth() - newComment_textArea.getPadding().getLeft() - newComment_textArea.getPadding().getRight());

            newComment_textArea.setMinHeight(tempText.getLayoutBounds().getHeight() + 40);
        });
    }

    @FXML
    public void cancel_action(){
        newComment_gridPane.getChildren().clear();
    }
    @FXML
    public void comment_action(){
        //TODO
        String comment = newComment_textArea.getText();
    }
}
