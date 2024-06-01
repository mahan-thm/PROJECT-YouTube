package models;

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Entry/Login.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("YouTube");
        stage.setScene(scene);
//        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("beforeEnterReddit/reddit-icon.png")));
//        stage.getIcons().add(icon);
        stage.show();
    }
}



