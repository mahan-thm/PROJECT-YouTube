package client.models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONObject;
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
    private static Socket socket;



    public static void main(String[] args) {
        request = new Request();
        socket_init();
//        JSONObject temp = new JSONObject();
//        temp.put("requestType","/test");
//        write(temp);
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
            socket = new Socket("localhost", 4444);

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            dataReader = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataWriter = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static JSONObject read(){
        JSONObject response = new JSONObject();
        try {
            String line = reader.readLine();
            response = new JSONObject(line);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.put("respondType", "/error");
        return response;
    }
    public static byte[] readFile(int size){

        try {
            DataInputStream dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            long fileLength = dataIn.readLong();

            byte[] bytes = new byte[(int) fileLength];
            int bytesRead;
            int totalBytesRead = 0;
            while (totalBytesRead < fileLength) {
                bytesRead = dataIn.read(bytes, totalBytesRead, bytes.length - totalBytesRead);
                if (bytesRead == -1) { // End of stream
                    break;
                }
                totalBytesRead += bytesRead;
            }

            return bytes;
        }catch (IOException e){
            e.printStackTrace();
        }

        return new byte[0];
    }
    public static void write(JSONObject request){
        try {
            String requestToSend =  request.toString();
            writer.write(requestToSend);
            writer.newLine();
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //todo

}





