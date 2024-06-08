package server.models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.database.Dbm;

import java.io.*;
import java.net.Socket;




public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;


    String userName;
    //tst2

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            closeClientHandler(socket, reader, writer);
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
                String clientRequest = reader.readLine();
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
        JSONObject response = new JSONObject();
        String username_input = (String) request.get("username_input");
        String password_input = (String) request.get("password_input");
        try {

            if (Dbm.checkUsername(username_input) && Dbm.authorize(username_input,password_input))
            {

                request.put("responseType","/login_accepted");
                request.put("username",username_input);
                request.put("password",password_input);
            }
            else{
                request.put("responseType","/login_rejected");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void signUp(JSONObject request) {
        while(true){

            String username_input = (String) request.get("username_input");
            String password_input = (String) request.get("password_input");
            String name_input = (String) request.get("name_input");
            String email_input = (String) request.get("email_input");
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
