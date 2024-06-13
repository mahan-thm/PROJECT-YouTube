package client.models;

import org.json.simple.JSONObject;
import server.database.Dbm;

public class Video {
    int id ;
    String title ;
    String title_body ;
    String duration ;
    String creation_time ;
    String total_view ;
    String total_likes ;
    String total_dislikes ;

    int channel_id ;
    String channel_name ;

    public Video(JSONObject response){

        title = (String) response.get("title");
        title_body = (String) response.get("title_body");
        duration = (String) response.get("duration");
        creation_time = (String) response.get("creation_time");
        total_view = (String) response.get("total_view");
        total_likes = (String) response.get("total_likes");
        total_dislikes = (String) response.get("total_dislikes");

        channel_id = (int) response.get("channel_id");
        channel_name = (String) response.get("channel_name");
    }



}
