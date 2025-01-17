package client.models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Main extends Application {


    public static BufferedReader reader;
    public static BufferedWriter writer;
    public static DataInputStream dataReader;
    public static DataOutputStream dataWriter;
    public static UserAccount userAccount;
    public static Request request;



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

    public static JSONObject read(){
        JSONParser parser = new JSONParser();
        JSONObject response = new JSONObject();
        try {
            response = (JSONObject) parser.parse(reader.readLine());
            return response;
        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        response.put("respondType","/error");
        return response;
    }
    public static void write(JSONObject request){
        try {
            writer.write(request.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
                                                                                              //todo

}





