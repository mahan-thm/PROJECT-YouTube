package server.models;
import org.json.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.database.Dbm;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private static BufferedWriter writer;

    private int user_id;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            closeClientHandler(socket, reader, writer);
        }
    }
    public static void write(JSONObject response){


        try {
            writer.write(response.toString());
            writer.newLine();
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override

    public void run() {
        requestHandler();

    }

    private void requestHandler() {
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
                case "/login"              -> login(request);
                case "/logout"             -> logout(request);
                case "/signUp"             -> signUp(request);
                case "/videoList"          -> videoList(request);
                case "/channelList"        -> channelList(request);
                case "/hybridList"         -> hybridList(request);
                case "/commentList"        -> commentList(request);
                case "/video"              -> video(request);
                case "/videoFile"          -> videoFile(request);
                case "/comment"            -> comment(request);
                case "/channel"            -> channel(request);
                case "/createChannel"      -> createChannel(request);
                case "/deleteChannel"      -> deleteChannel(request);
                case "/addVideo"           -> addVideo(request);
                case "/removeVideo"        -> removeVideo(request);
                case "/addComment"         -> addComment(request);
                case "/removeComment"      -> removeComment(request);
                case "/subscribeChannel"       -> subscribeChannel(request);
                case "/unsubscribeChannel"     -> unsubscribeChannel(request);
//                case "/add_WatchedVideo"       -> add_WatchedVideo(request);
//                case "/remove_WatchedVideo"    -> remove_WatchedVideo(request);
//                case "/add_WatchLaterVideo"    -> add_WatchLaterVideo(request);
//                case "/remove_WatchLaterVideo" -> remove_WatchLaterVideo(request);
//                case "/add_likedVideo"         -> add_likedVideo(request);
//                case "/remove_likedVideo"      -> remove_likedVideo(request);
//                case "/add_dislikedVideo"      -> add_dislikedVideo(request);
//                case "/remove_dislikedVideo"   -> remove_dislikedVideo(request);
                case "/edit_commentLike"       -> edit_commentLike(request);
                //todo profile picture

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

                user_id = Dbm.get_user_id(username_input);

                response.put("responseType","/login_accepted");
                response.put("username",username_input);
                response.put("password",password_input);


            }
            else{
                response.put("responseType","/login_rejected");
            }

            write(response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void logout(JSONObject request) {
        JSONObject response = new JSONObject();
        response.put("responseType","/logout_accepted");
        user_id = -1;
        write(response);
    }

    private void signUp(JSONObject request) {

        JSONObject response = new JSONObject();

        String username_input = (String) request.get("username_input");
        String password_input = (String) request.get("password_input");
        String name_input = (String) request.get("name_input");
        String email_input = (String) request.get("email_input");
        String number_input = (String) request.get("number_input");

        if (!Dbm.checkUsername(username_input)){
            response.put("responseType","/signUp_rejected");
            response.put("username",username_input);
            response.put("password",password_input);
        }
        else
        {
            response.put("responseType","/signUp_accepted");
            Dbm.addUser(username_input,password_input,name_input,email_input,number_input,false,"");
        }
        write(response);
    }

    private void videoList(JSONObject request) {

        JSONObject response = new JSONObject();

        int videoCount = (int) request.get("count");
        JSONArray tags = (JSONArray) request.get("tags");

        // todo recommendation

        JSONArray videoIdList = new JSONArray();
        List<Integer> idList = Dbm.getRandomVideoId(videoCount);  //todo temporary

        for (Integer id : idList) {
            videoIdList.put(id);
        }
        response.put("responseType","/videoList_accepted");
        response.put("videoIdList",videoIdList);


        write(response);
    }

    private void channelList(JSONObject request) {

        JSONObject response = new JSONObject();

        int channelCount = (int) request.get("count");
        JSONArray tags = (JSONArray) request.get("tags");

        // todo recommendation

        JSONArray channelIdList = new JSONArray();
        List<Integer> idList = Dbm.getRandomChannelId(channelCount);  //todo temporary

        for (Integer id : idList) {
            channelIdList.put(id);
        }
        response.put("responseType","/videoList_accepted");
        response.put("channelIdList",channelIdList);


        write(response);
    }

    private void hybridList(JSONObject request) {

        JSONObject response = new JSONObject();

        int itemCount = (int) request.get("count");
        JSONArray tags = (JSONArray) request.get("tags");

        // todo recommendation

        JSONArray itemIdList = new JSONArray();
        List<Integer> idList = Dbm.getRandomChannelId(itemCount);  //todo temporary

        for (Integer id : idList) {
            itemIdList.put(id);
        }
        response.put("responseType","/videoList_accepted");
        response.put("itemIdList",itemIdList);


        write(response);
    }

    private void commentList(JSONObject request) {
        JSONObject response = new JSONObject();
        int video_id = (int) request.get("video_id");
        JSONArray commentIdList = new JSONArray();
        int commentCount = Dbm.getCommentCount(video_id);
        List<Integer> idList = Dbm.getCommentIdList(video_id);

        for (Integer id : idList) {
            commentIdList.put(id);
        }
        response.put("responseType","/commentList_accepted");
        response.put("videoIdList",commentIdList);
        response.put("commentCount",commentCount);


        write(response);
    }

    private void video(JSONObject request){

        JSONObject response = new JSONObject();

        int id = (int) request.get("video_id");
        String title = Dbm.getVideo_Title(id);
        String title_body = Dbm.getVideo_description(id);
        String duration = Dbm.getVideo_duration(id);
        String creation_time = Dbm.getVideo_creationTime(id);
        String total_view = Dbm.getVideo_totalView(id);
        String total_likes = Dbm.getVideo_totalLikes(id);
        String total_dislikes = Dbm.getVideo_totalDislikes(id);

        response.put("responseType","/video_accepted");
        response.put("title",title);
        response.put("title_body",title_body);
        response.put("duration",duration);
        response.put("creation_time",creation_time);
        response.put("total_view",total_view);
        response.put("total_likes",total_likes);
        response.put("total_dislikes",total_dislikes);

        write(response);

    }

    private void videoFile(JSONObject request){
        int video_id = (int) request.get("video_id");
        String videoLink = Dbm.getVideo_link(video_id);

        File fileToSend = new File(videoLink);
        sendFile(fileToSend);
    }

    private void comment(JSONObject request) {
        JSONObject response = new JSONObject();
        int comment_id = (int) request.get("comment_id");
        String senderName = Dbm.getUsername(Dbm.getCommentSender_id);
        String Text = Dbm.getCommentText(comment_id);
        String repliedTo = Dbm.getcommentRepliedTo(comment_id);
        String creationTime = Dbm.getcommentCreationTime(comment_id);

        response.put("responseType","/comment_accepted");
        response.put("senderName","/senderName");
        response.put("Text",Text);
        response.put("repliedTo",repliedTo);
        response.put("creationTime",creationTime);



        write(response);
    }

    private void channel(JSONObject request) {
        JSONObject response = new JSONObject();
        int channel_id = (int) request.get("channel_id");

        int totalVideos = Dbm.getChannelTotalVideoes(channel_id);
        int totalViews = Dbm.getChannelTotalViews(channel_id);
        int totalSubscribers = Dbm.getChannelTotalSubscribers(channel_id);
        String description= Dbm.getChannelDescription(channel_id);

        response.put("responseType","/comment_accepted");
        response.put("totalVideos",totalVideos);
        response.put("totalViews",totalViews);
        response.put("totalSubscribers",totalSubscribers);
        response.put("channelDescription",description);



        write(response);
    }

    private void createChannel(JSONObject request) {
        JSONObject response = new JSONObject();

        String channelName = (String) request.get("channelName");
        String channelUsername = (String) request.get("channelUsername");
        String channelDescription = (String) request.get("channelDescription");
        JSONArray tags = (JSONArray) request.get("tags");


        if (!Dbm.checkChannelUsername(channelUsername)){
            response.put("responseType","/createChannel_rejected");
        }
        else
        {
            response.put("responseType","/createChannel_accepted");
            Dbm.addChannel(channelName,channelUsername,channelDescription);
        }
        write(response);


    }

    private void deleteChannel(JSONObject request) {
        JSONObject response = new JSONObject();

        String username_input = (String) request.get("username_input");
        String password_input = (String) request.get("password_input");

        if (Dbm.authorize(username_input,password_input)) {

            int channel_id = (int) request.get("channel_id");


            List<Integer> channelVideoList = Dbm.getChannelVideoList(channel_id);
            for (Integer video_id : channelVideoList) {
                List<Integer> videoCommentList = Dbm.getVideoCommentList(video_id);
                for (Integer comment_id : videoCommentList) {
                    Dbm.removeComment(comment_id);
                }
                Dbm.removeVideo(video_id);
            }
            Dbm.removeChannel(channel_id);

            response.put("responseType", "/deleteChannel_accepted");
        }
        else {
            response.put("responseType", "/deleteChannel_accepted");
        }
        write(response);
    }

    private void addVideo(JSONObject request) {
        JSONObject response = new JSONObject();

        int channel_id = (int) request.get("channel_id");
        String videoName = (String) request.get("channelName");
        String videoDescription = (String) request.get("videoDescription");
        JSONArray tags = (JSONArray) request.get("tags");

        Dbm.addVideo(channel_id,videoName,videoDescription,tags);
        response.put("responseType","/addVideo_accepted");

        write(response);
    }

    private void uploadVideoFile(JSONObject request) {
        JSONObject response = new JSONObject();

        int video_id = (int) request.get("video_id");
        try {

            File file = new File(Dbm.getVideoPath(video_id));

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataInputStream dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            byte[] bytes = new byte[1024];
            dataIn.read(bytes);
            fileOutputStream.write(bytes);

            dataIn.close();

        }catch (IOException e){
            e.printStackTrace();
        }



        response.put("responseType","/uploadVideoFile_accepted");

        write(response);
    }

    private void removeVideo(JSONObject request) {
        JSONObject response = new JSONObject();

        int video_id = (int) request.get("video_id");

        List<Integer> videoCommentList = Dbm.getVideoCommentList(video_id);
        for (Integer comment_id : videoCommentList) {
            Dbm.removeComment(comment_id);
        }
        Dbm.removeVideo(video_id);
        response.put("responseType","/removeVideo_accepted");

    }

    private void addComment(JSONObject request) {
        JSONObject response = new JSONObject();

        int video_id = (int) request.get("video_id");
        String text = (String) request.get("text");
        int repliedTo_id = (int) request.get("repliedTo_id");


        Dbm.addComment(video_id,text,repliedTo_id);
        response.put("responseType","/addComment_accepted");


    }

    private void removeComment(JSONObject request) {
        JSONObject response = new JSONObject();

        int comment_id = (int) request.get("comment_id");

        Dbm.removeComment(comment_id);
        response.put("responseType","/removeComment_accepted");

    }

    private void subscribeChannel(JSONObject request) {
        JSONObject response = new JSONObject();

        int channel_id = (int) request.get("channel_id");

        Dbm.addUserSubscribedChannels(channel_id,user_id);
        Dbm.addChannelTotalSubscribers(channel_id);

        response.put("responseType","/subscribeChannel_accepted");
    }

    private void unsubscribeChannel(JSONObject request) {
        JSONObject response = new JSONObject();

        int channel_id = (int) request.get("channel_id");


        Dbm.removeUserSubscribedChannels(channel_id,user_id);
        Dbm.reduceChannelTotalSubscribers(channel_id);
        response.put("responseType","/unsubscribeChannel_accepted");

    }

    private void add_savedVideo(JSONObject request) {

        JSONObject response = new JSONObject();

        int video_id = (int) request.get("video_id");
        String playlist_type = (String) request.get("playlist_type");

        Dbm.addSavedVideo(user_id,playlist_type,video_id);

        response.put("responseType","/add_savedVideo_accepted");

    }

    private void remove_savedVideo(JSONObject request) {
        JSONObject response = new JSONObject();

        int video_id = (int) request.get("video_id");
        String playlist_type = (String) request.get("playlist_type");


        Dbm.removeSavedVideo(user_id,playlist_type,video_id);


        response.put("responseType","/remove_WatchedVideo_accepted");
    }
// may be used fo recommendation system
//    private void add_WatchLaterVideo(JSONObject request) {
//        JSONObject response = new JSONObject();
//
//        int video_id = (int) request.get("video_id");
//
//        int WatchLaterVideos_id = Dbm.getUserWatchedVideosId(user_id);
//
//        Dbm.addToPlaylist(video_id,WatchLaterVideos_id);
//
//        response.put("responseType","/add_WatchLaterVideo_accepted");
//    }
//    private void remove_WatchLaterVideo(JSONObject request) {
//        JSONObject response = new JSONObject();
//
//        int video_id = (int) request.get("video_id");
//
//        int WatchLaterVideos_id = Dbm.getUserWatchedVideosId(user_id);
//
//        Dbm.removeFromPlaylist(video_id,WatchLaterVideos_id);
//
//        response.put("responseType","/remove_WatchLaterVideo_accepted");
//    }
//    private void add_likedVideo(JSONObject request) {
//        JSONObject response = new JSONObject();
//
//        int video_id = (int) request.get("video_id");
//
//        int likedVideos_id = Dbm.getUserWatchedVideosId(user_id);
//
//        Dbm.addToPlaylist(video_id,likedVideos_id);
//
//        response.put("responseType","/add_likedVideo_accepted");
//    }
//    private void remove_likedVideo(JSONObject request) {
//        JSONObject response = new JSONObject();
//
//        int video_id = (int) request.get("video_id");
//
//        int likedVideos_id = Dbm.getUserWatchedVideosId(user_id);
//
//        Dbm.removeFromPlaylist(video_id,likedVideos_id);
//
//        response.put("responseType","/remove_likedVideo_accepted");
//    }
//    private void add_dislikedVideo(JSONObject request) {
//        JSONObject response = new JSONObject();
//
//        int video_id = (int) request.get("video_id");
//
//        int dislikedVideo_id = Dbm.getUserWatchedVideosId(user_id);
//
//        Dbm.addToPlaylist(video_id,dislikedVideo_id);
//
//        response.put("responseType","/add_dislikedVideo_accepted");
//    }
//    private void remove_dislikedVideo(JSONObject request) {
//        JSONObject response = new JSONObject();
//
//        int video_id = (int) request.get("video_id");
//
//        int dislikedVideo_id = Dbm.getUserWatchedVideosId(user_id);
//
//        Dbm.removeFromPlaylist(video_id,dislikedVideo_id);
//
//        response.put("responseType","/remove_dislikedVideo_accepted");
//    }

    private void edit_commentLike(JSONObject request) {

        JSONObject response = new JSONObject();

        int comment_id = (int) request.get("comment_id");
        String editType = (String) request.get("editType");

        switch(editType){
            case "addLike"    -> Dbm.editCommentLike(comment_id,user_id,"liked");
            case "removeLike" -> Dbm.removeCommentLike();
            case "addDislike" -> Dbm.editCommentLike(comment_id,user_id,"Disliked");

        }

        response.put("responseType","/edit_commentLike_accepted");
    }


    private void sendFile(File file) {
        try {

            DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            long length = file.length();
            byte[] bytes = new byte[(int) length];
            FileInputStream fileInputStream= new FileInputStream(file);
            fileInputStream.read(bytes);
            dataOut.write(bytes);                                          //*
            dataOut.flush();
            System.out.println("data has been sent");

            dataOut.close();

        } catch (IOException e) {
            e.printStackTrace();
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
