package client.models;

import org.json.JSONObject;


public class Video {
    public int id ;
    String title ;
    String title_body ;
    String duration ;
    String creation_time ;
    String total_view ;
    String total_likes ;
    String total_dislikes ;

    public String getTitle_body() {
        return title_body;
    }

    public String getDuration() {
        return duration;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public String getTotal_view() {
        return total_view;
    }

    public String getTotal_likes() {
        return total_likes;
    }

    public String getTotal_dislikes() {
        return total_dislikes;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    int channel_id ;
    String channel_name ;

    public Video(int video_id,JSONObject response){
        id = video_id;

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
