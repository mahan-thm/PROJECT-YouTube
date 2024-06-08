package client.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static client.models.Main.write;

public class Request {

    public void login(String username_input,String password_input){
        JSONObject request = new JSONObject();
        request.put("requestType","/login");
        request.put("username",username_input);
        request.put("password",password_input);

        write(request);
    }

    public void signUp(String username_input,String password_input,String name_input,String email_input,String number_input){
        JSONObject request = new JSONObject();
        request.put("requestType","/signUp");
        request.put("username_input",username_input);
        request.put("password_input",password_input);
        request.put("name_input",name_input);
        request.put("email_input",email_input);
        request.put("number_input",number_input);


        write(request);
    }
    static void videoList(String videoCount, JSONArray tags){
        JSONObject request = new JSONObject();
        request.put("requestType","/videoList");
        request.put("count",videoCount);
        request.put("tags",tags);

        write(request);
    }

    static void video(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/video");
        request.put("video_id",video_id);

        write(request);
    }
    static void videoFile(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/videoFile");
        request.put("video_id",video_id);

        write(request);
    }
    public void comments(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/comments");
        request.put("video_id",video_id);


        write(request);
    }

}
