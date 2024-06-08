package client.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

import static client.models.Main.*;

public class LoginController {




    //TODO in case of press login button we should run request.login();

    private void getResponse(){
        JSONObject response = new JSONObject();
        response = read();
        if(response.get("responseType").equals("/login_accepted")){
            loginAccepted(response);
        }
        else loginRejected();

    }

    private void loginAccepted(JSONObject response) {

        //todo save client information
        //TODO navigate to next page

    }

    private void loginRejected() {
        //TODO show client username or password is wrong
    }

}
