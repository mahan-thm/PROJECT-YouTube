package client.models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../entry/Login.fxml")));
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("../../entry/LoginStyle.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("YouTube");
        stage.setScene(scene);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../entry/youtube-icon.png")));
        stage.getIcons().add(icon);
        stage.show();
    }
}



