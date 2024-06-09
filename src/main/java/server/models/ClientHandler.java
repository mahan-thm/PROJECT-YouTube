package server.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.database.Dbm;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.GregorianCalendar;
import java.util.List;

import static client.models.Main.write;


public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private static BufferedWriter writer;

    private int user_id;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            closeClientHandler(socket, reader, writer);
        }
    }
    public static void write(JSONObject response){


        try {
            writer.write(response.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override

    public void run() {
        requestHandler();

    }

    private void requestHandler() {
        while (socket.isConnected()){
            JSONParser parser = new JSONParser();
            JSONObject request = new JSONObject();
            try {
                String clientRequest = reader.readLine();
                request = (JSONObject) parser.parse(clientRequest);

            }catch (IOException e){
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            String requestType = (String) request.get("requestType");
            switch (requestType){
                case "/login"      -> login(request);
                case "/logout"      -> logout(request);
                case "/signUp"     -> signUp(request);
                case "/videoList"      -> videoList(request);
                case "/video"      -> video(request);
                case "/videoFile"  -> videoFile(request);

            }
        }
    }


    private void login(JSONObject request){
        JSONObject response = new JSONObject();
        String username_input = (String) request.get("username_input");
        String password_input = (String) request.get("password_input");
        try {

            if (Dbm.checkUsername(username_input) && Dbm.authorize(username_input,password_input))
            {

                user_id = Dbm.get_user_id();

                response.put("responseType","/login_accepted");
                response.put("username",username_input);
                response.put("password",password_input);


            }
            else{
                response.put("responseType","/login_rejected");
            }

            write(response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void logout(JSONObject request) {
        JSONObject response = new JSONObject();
        response.put("responseType","/logout_accepted");
        user_id = -1;
        write(response);
    }

    private void signUp(JSONObject request) {

        JSONObject response = new JSONObject();

        String username_input = (String) request.get("username_input");
        String password_input = (String) request.get("password_input");
        String name_input = (String) request.get("name_input");
        String email_input = (String) request.get("email_input");
        String number_input = (String) request.get("number_input");

        if (!Dbm.checkUsername(username_input)){
            response.put("responseType","/signUp_rejected");
        }
        else
        {
            response.put("responseType","/signUp_accepted");
            Dbm.signUp(username_input,password_input,name_input,email_input,number_input);
        }
        write(response);
    }
    private void videoList(JSONObject request) {

        JSONObject response = new JSONObject();

        int videoCount = (int) request.get("count");
        JSONArray tags = (JSONArray) request.get("tags");
        // todo recommendation
        List<Integer> videoIdList = Dbm.getRandomVideoId();
        Gson gson = new Gson();



        write(response);
    }

    private void video(JSONObject request){
        JSONObject response = new JSONObject();

        int id = (int) request.get("video_id");
        String title = Dbm.getVideo_Title(id);
        String title_body = Dbm.getVideo_TitleBody(id);
        String duration = Dbm.getVideo_duration(id);
        String creation_time = Dbm.getVideo_creationTime(id);
        String total_view = Dbm.getVideo_totalView(id);
        String total_likes = Dbm.getVideo_totalLikes(id);


        response.put("title",title);
        response.put("title_body",title_body);
        response.put("duration",duration);
        response.put("creation_time",creation_time);
        response.put("total_view",total_view);
        response.put("total_likes",total_likes);

        write(response);

    }

    private void videoFile(JSONObject request){
        //todo
    }

    public void closeClientHandler(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){

        try {
            bufferedReader.close();
            bufferedWriter.close();
            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
