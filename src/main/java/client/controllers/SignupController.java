package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupController {

    @FXML
    private TextField signupUsername_textField;
    @FXML
    private PasswordField signupPass_passwordField;
    @FXML
    private PasswordField signupConfirmPass_passwordField;
    @FXML
    private Label signupStatus_label;


    @FXML
    public void signup_action(ActionEvent actionEvent) throws IOException {
        String username = signupUsername_textField.getText();
        String password = signupPass_passwordField.getText();
        String passwordConfirm = signupConfirmPass_passwordField.getText();

        signupStatus_label.setText("user exist"); //to show the errors


        //if everything correct:
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../home/Home.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../home/HomeStyle.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void backToLogin_action(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../entry/Login.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../../entry/LoginStyle.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}
