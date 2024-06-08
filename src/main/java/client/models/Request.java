package client.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static client.models.Main.write;

public class Request {

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

    public void login(String username_input,String password_input){
        JSONObject request = new JSONObject();
        request.put("requestType","/login");
        request.put("username",username_input);
        request.put("password",password_input);

        write(request);
    }
}
