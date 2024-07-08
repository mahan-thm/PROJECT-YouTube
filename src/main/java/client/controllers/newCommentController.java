package client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class newCommentController implements Initializable {
    @FXML
    private TextArea newComment_textArea;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        newComment_textArea.setPrefRowCount(1);

        newComment_textArea.textProperty().addListener((observable, oldValue, newValue) -> {

            Text tempText = new Text(newValue);
            tempText.setFont(newComment_textArea.getFont());
            tempText.setWrappingWidth(newComment_textArea.getWidth() - newComment_textArea.getPadding().getLeft() - newComment_textArea.getPadding().getRight());

            newComment_textArea.setMinHeight(tempText.getLayoutBounds().getHeight() + 40);
        });
    }
}
