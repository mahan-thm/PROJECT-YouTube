package server.models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.database.Dbm;

import java.io.*;
import java.net.Socket;




public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;


    String userName;
    //tst2

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            closeClientHandler(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override

    public void run() {
        requestGet();

    }

    private void requestGet() {
        while (socket.isConnected()){
            JSONParser parser = new JSONParser();
            JSONObject request = new JSONObject();
            try {
                String clientRequest = bufferedReader.readLine();
                request = (JSONObject) parser.parse(clientRequest);

            }catch (IOException e){
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            String requestType = (String) request.get("requestType");
            switch (requestType){
                case "/login":
                    login(request);
                    break;
                case "/signUp":
                    signUp(request);
                    break;
            }
        }
    }


    private void login(JSONObject request){
        String username_input = ""; //todo
        String password_input = "";

        if (Dbm.checkUsername(username_input))
        {
            if (Dbm.authorize(username_input,password_input)){
                //todo send account information
            }
        }

    }

    private void signUp(JSONObject request) {
        while(true){

            String username_input = ""; //todo
            String password_input = "";
            String name_input = "";
            String email_input = "";
            if (!Dbm.checkUsername(username_input)){
                //todo send impossible
            }
            else {
                //todo save in data base
            }
        }
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
