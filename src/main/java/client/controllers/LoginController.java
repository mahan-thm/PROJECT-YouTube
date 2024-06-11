package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static client.models.Main.*;

public class LoginController {

    @FXML
    private TextField loginUsername_textField;
    @FXML
    private PasswordField loginPass_passwordField;
    @FXML
    private Label loginStatus_label;


    @FXML
    public void login_action(ActionEvent actionEvent) throws IOException {
        String username = loginUsername_textField.getText();
        String password = loginPass_passwordField.getText();
        loginStatus_label.setText("wrong pass or user"); //uses to show the login error




        //if everything correct:
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../home/Home.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../home/HomeStyle.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToSignup_action(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../entry/Signup.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../entry/SignupStyle.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();

    }








    //TODO in case of press login button we should run request.login();

    private void getResponse(){
        JSONObject response = new JSONObject();
        response = read();
        if(response.get("responseType").equals("/login_accepted")){
            loginAccepted(response);
        }
        else loginRejected();

    }

    private void loginAccepted(JSONObject response) {

        //todo save client information
        //TODO navigate to next page

    }

    private void loginRejected() {
        //TODO show client username or password is wrong
    }

}
