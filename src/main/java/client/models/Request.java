package client.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static client.models.Main.write;

public class Request {
    //account requests--------------------------------------------------------------------------------------

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
    public void logout(String username_input,String password_input){
        JSONObject request = new JSONObject();
        request.put("requestType","/logout");
        request.put("username",username_input);
        request.put("password",password_input);

        write(request);
    }
    public void authorize(String username, String password){
        JSONObject request = new JSONObject();
        request.put("requestType","/authorize");
        request.put("username",username);
        request.put("password",password);

        write(request);
    }
    //-------------------------------------------------------------------------------------------------------

    // item list request-------------------------------------------------------------------------------------
    static void videoList(String videoCount, JSONArray tags){
        JSONObject request = new JSONObject();
        request.put("requestType","/videoList");
        request.put("count",videoCount);
        request.put("tags",tags);

        write(request);
    }
    static void channelList(String channelCount, JSONArray tags){
        JSONObject request = new JSONObject();
        request.put("requestType","/channelList");
        request.put("count",channelCount);
        request.put("tags",tags);

        write(request);
    }
    static void hybridList(String itemCount, JSONArray tags){
        //channel and video list combined
        JSONObject request = new JSONObject();
        request.put("requestType","/hybridList");
        request.put("count",itemCount);
        request.put("tags",tags);

        write(request);
    }
    //-------------------------------------------------------------------------------------------------------

    // item request------------------------------------------------------------------------------------------
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
    public void channel(String channel_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/channel");
        request.put("channel_id",channel_id);


        write(request);
    }
    //------------------------------------------------------------------------------------------------------

    // submit requests -------------------------------------------------------------------------------------
    public void createChannel(String channelName,JSONArray tags){

        JSONObject request = new JSONObject();
        request.put("requestType","/createChannel");
        request.put("channelName",channelName);
        request.put("tags",tags);


        write(request);
    }
    public void createVideo(String channel_id,String videoName,String videoCaption,JSONArray tags){

        JSONObject request = new JSONObject();
        request.put("requestType","/createVideo");
        request.put("channel_id",channel_id);
        request.put("videoName",videoName);
        request.put("videoCaption",videoCaption);
        request.put("tags",tags);


        write(request);
    }
    public void subscribeChannel(String channel_id, String videoName, String videoCaption, JSONArray tags){

        JSONObject request = new JSONObject();
        request.put("requestType","/subscribeChannel");
        request.put("channel_id",channel_id);


        write(request);
    }
    public void unsubscribeChannel(String channel_id, String videoName, String videoCaption, JSONArray tags){

        JSONObject request = new JSONObject();
        request.put("requestType","/unsubscribeChannel");
        request.put("channel_id",channel_id);



        write(request);
    }
    //------------------------------------------------------------------------------------------------------

    // tiny action request----------------------------------------------------------------------------------
    static void add_WatchedVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/watchVideo");
        request.put("video_id",video_id);

        write(request);
    }
    static void remove_WatchedVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/remove_WatchedVideo");
        request.put("video_id",video_id);


        write(request);
    }
    static void add_WatchLaterVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/add_WatchLaterVideo");
        request.put("video_id",video_id);

        write(request);
    }
    static void remove_WatchLaterVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/remove_WatchLaterVideo");
        request.put("video_id",video_id);


        write(request);
    }
    static void add_likedVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/add_likedVideo");
        request.put("video_id",video_id);


        write(request);
    }
    static void remove_likedVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/remove_likedVideo");
        request.put("video_id",video_id);


        write(request);
    }
    static void add_dislikedVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/add_dislikedVideo");
        request.put("video_id",video_id);

        write(request);
    }
    static void remove_dislikedVideo(String video_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/remove_dislikedVideo");
        request.put("video_id",video_id);

        write(request);
    }
    static void add_comment(String comment_id,String repliedTo_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/add_comment");
        request.put("comment_id",comment_id);
        request.put("repliedTo_id",repliedTo_id);

        write(request);
    }
    static void remove_comment(String comment_id,String repliedTo_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/remove_comment");
        request.put("comment_id",comment_id);
        request.put("repliedTo_id",repliedTo_id);

        write(request);
    }
    static void add_commentLike(String comment_id,String repliedTo_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/add_commentLike");
        request.put("comment_id",comment_id);
        request.put("repliedTo_id",repliedTo_id);


        write(request);
    }
    static void remove_commentLike(String comment_id,String repliedTo_id){
        JSONObject request = new JSONObject();
        request.put("requestType","/remove_commentLike");
        request.put("comment_id",comment_id);
        request.put("repliedTo_id",repliedTo_id);


        write(request);
    }
    //-----------------------------------------------------------------------------------------------------

}