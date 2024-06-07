package client.controllers;

import org.json.simple.JSONObject;

import java.io.IOException;

import static client.models.Main.writer;

public class LoginController {







    public void request_login(){
        JSONObject request = new JSONObject();

        //todo create request

        try {
            writer.write(request.toString());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
