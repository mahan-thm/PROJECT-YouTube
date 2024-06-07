package client.models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Main extends Application {


    public static BufferedReader reader;
    public static BufferedWriter writer;
    public static DataInputStream dataReader;
    public static DataOutputStream dataWriter;


    public static void main(String[] args) {

        socket_init();
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

    private static void socket_init() {
        try{
            Socket socket = new Socket("localhost", 4444);

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            dataReader = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataWriter = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}



