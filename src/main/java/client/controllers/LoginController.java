package client.controllers;

import org.json.simple.JSONObject;

import java.io.IOException;

import static client.models.Main.writer;

public class LoginController {




    //TODO in case of press login button we should run request_login
    public void request_login(String username_input,String password_input){
        JSONObject request = new JSONObject();
        request.put("requestType","/login");
        request.put("username",username_input);
        request.put("password",password_input);


        try {
            writer.write(request.toString());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
