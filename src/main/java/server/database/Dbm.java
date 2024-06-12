package server.database;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.*;

public class Dbm {
    private static final String url = "jdbc:mysql://localhost:3306/youtube_db";
    private static final String username = "root";
    private static final String password = "qazmlp2990";
    private static Connection con;
    private static Statement stat;
    private final String videoPath="" ;
    private final String profileImagePath="" ;
    private final String channelImagePath="" ;




    private static void open(){
        try {
            con = DriverManager.getConnection(url, username, password);
            stat =  con.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void close(){
        try {

            stat.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static Object getCommentSender_id;

    public static boolean checkUsername(String usernameInput){
        open();
        String query = "SELECT * FROM users" +
                "WHERE username =  '" + usernameInput+ "'";
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
            return rs.next();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return false;
    }


    public static boolean authorize(String usernameInput,String passwordInput) {
        open();
        String query = "SELECT * FROM users" +
                "WHERE username =  '" + usernameInput+ "'" +"AND pass = '" + passwordInput+ "'";
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
            return rs.next();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return false;
    }



    public static String getVideo_Title(int id) {
        open();
        String query = "SELECT title  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getString("title");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }


    public static String getVideo_creationTime(int id) {
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getString("creationDate");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }

    public static String getVideo_duration(int id) {
        // todo chera int return nemikone

        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getString("duration");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }

    public static int getVideo_totalView(int id) {


        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getInt("total_view");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return 0;
    }

    public static int getVideo_totalLikes(int id) {
        // todo chera int return nemikone
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getInt("total_likes");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return 0;
    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public static List<Integer> getRandomVideoId(int videoCount) {
        String maxId = "SELECT MAX(id) AS maxId FROM videos";
        int max=0;
        List <Integer> randList= new ArrayList<>();
        try {
            ResultSet rs = stat.executeQuery(maxId);
            // bug probablity
            max =rs.getInt("id") ;
        }catch(SQLException e){
            e.printStackTrace();
        }
        for(int i=0 ; i< videoCount ; i++){
            int randomNumber =getRandomNumber(1 ,max );
            randList.add(randomNumber);
            }
            return randList;
        }

    public static int get_user_id(String username) {
        open();
        String query = "SELECT *  FROM users" +
                "WHERE username =  '" + username +"'";
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getInt("id");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return 0;
    }

    public static int getVideo_totalDislikes(int id) {
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getInt("total_dislikes");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return 0;
    }

    public static List<Integer> getRandomChannelId(int channelCount) {
        String maxId = "SELECT MAX(id) AS maxId FROM channels";
        int max=0;
        List <Integer> randList= new ArrayList<>();
        try {
            ResultSet rs = stat.executeQuery(maxId);
            // bug probablity
            max =rs.getInt("id") ;
        }catch(SQLException e){
            e.printStackTrace();
        }
        for(int i=0 ; i< channelCount ; i++){
            int randomNumber =getRandomNumber(1 ,max );
            randList.add(randomNumber);
        }
        return randList;
    }

    public static int getCommentCount(int videoId) {
        String query = " SELECT * FROM comments" +
                        "WHERE video_id = " + videoId ;
        int count =0;
        try {
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()){
                count ++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public static List<Integer> getCommentIdList(int videoId) {
        List<Integer> commentIdList = new ArrayList<>();
        String query = "SELECT id FROM comments" +
                        " WHERE video_id =" + videoId;
        try {
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()){
                commentIdList.add( rs.getInt("id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return commentIdList;
    }

    public static String getCommentText(int commentId) {
        String query = "SELECT body FROM comments" +
                " WHERE id =" + commentId;
        String body="";
        try {
            ResultSet rs = stat.executeQuery(query);
               body =rs.getString("body");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return body;
    }

    public static Timestamp getcommentCreationTime(int commentId) {
        String query = "SELECT * FROM comments" +
                " WHERE id =" + commentId;
        String creation_time="";
        try {
            ResultSet rs = stat.executeQuery(query);
            creation_time =rs.getString("creationDate");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Timestamp.valueOf(creation_time);
    }



    public static int getChannelTotalVideoes(int channelId) {
        String query = " SELECT * FROM channels" +
                "WHERE channel_id = " + channelId ;
        int total_videos =0;
        try {
             ResultSet rs = stat.executeQuery(query);
             total_videos = rs.getInt("total_videos");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total_videos;
    }

    public static int getChannelTotalViews(int channelId) {
        String query = " SELECT * FROM channels" +
                "WHERE channel_id = " + channelId ;
        int total_views =0;
        try {
            ResultSet rs = stat.executeQuery(query);
            total_views = rs.getInt("total_views");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total_views;
    }

    public static int getChannelTotalSubscribers(int channelId) {
        String query = " SELECT * FROM channels" +
                "WHERE channel_id = " + channelId ;
        int total_subscribers =0;
        try {
            ResultSet rs = stat.executeQuery(query);
            total_subscribers = rs.getInt("total_subscribers");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total_subscribers;
    }




    public static boolean checkChannelUsername(String channelUsername) {
        // todo bayad ham channel id bede ham oon user
//        String query = " SELECT * FROM channels" +
//                "WHERE owner_id = " + channelUsername ;
//        boolean bool = false;
//        try {
//            ResultSet rs = stat.executeQuery(query);
//            total_videos = rs.getint("total_videos");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        return false;
    }



    public static void addVideo_toVideos(int channelId, String videoName, String videoDescription, JSONArray tags) {
//        todo must have creation time
        String maxId = "SELECT MAX(id) AS maxId FROM uploaded_videos";
        int lastId=0;
        try {
            ResultSet rs = stat.executeQuery(maxId);
            // bug probablity
            lastId =rs.getInt("id") ;
            lastId++;
            // todo nemishe inja vase har
            String query ="INSERT INTO TABLE uploaded_videos (id ,title , video_description) VALUES ("+
                            + lastId +","+ channelId + ")"+
                        "INSERT INTO TABLE videos (id ,channel_id) VALUES ("+
                            + lastId +","+ channelId + ")"
                            ;


        }catch(SQLException e){
            e.printStackTrace();
        }
        String query ="INSERT INTO uploaded_videos (channel_id , id)" +
                        "VALUES (" + channelId +",";
    }





    public static void removeVideo(int videoId) {
        // todo mmdhosain

    }

    public static void addComment(int videoId, String text, int repliedToId) {

    }

    public static void removeComment(int commentId) {
    }


    public static void editCommentLike(int commentId, int userId, String liked) {
        // todo why string liked?
    }

    public static void removeCommentLike() {
        int total_likes =0;
        try{
            // do
            ResultSet rs = stat.executeQuery("");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        String query =" UPDATE comments total_like =";
    }

    public static void addUser(String username, String password, String name,String email,
                               String number,boolean is_premium,String creationDate ) {
        open();
        String query = "INSERT INTO users ( username , number ,pass , email , is_premium ,creationDate) VALUES ("+
                username+","+ number + "," +password + "," +email+"," + false +","+ creationDate +")";
        try{
            ResultSet rs = stat.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();

    }

    public static String getVideo_description(int id) {
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getString("video_description");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }

    public static String getVideo_link(int videoId) {
        // todo must be deleted . we get video path
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +videoId ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getString("total_likes");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }


    public static String getUsername(Object getCommentSenderId) {
        return null;
    }

    public static String getChannelDescription(int channelId) {

        return null;
    }

    public static void addChannel(String channelName, String channelUsername, String channelDescription) {
    }

    public static List<Integer> getChannelVideoList(int channelId) {
        return null;
    }

    public static List<Integer> getVideoCommentList(Integer videoId) {
        return null;
    }

    public static void removeChannel(int channelId) {
    }

    public static String getVideoPath(int videoId) {
        return null;
    }

    public static void addUserSubscribedChannels(int channelId, int userId) {
    }

    public static void addChannelTotalSubscribers(int channelId) {
    }

    public static void removeUserSubscribedChannels(int channelId, int userId) {
    }

    public static void reduceChannelTotalSubscribers(int channelId) {
    }

    public static void addSavedVideo(int userId, String playlistType, int videoId) {
    }

    public static void removeSavedVideo(int userId, String playlistType, int videoId) {
    }
    public static String getcommentRepliedTo(int commentId) {
        // todo is it to understand that this comment is replied to which comment?
//        String query = "SELECT * FROM comments" +
//                " WHERE id =" + commentId;
//        int commentRepliedToId=0;
//        try {
//            ResultSet rs = stat.executeQuery(query);
//            commentRepliedTo =rs.getString("body");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        return "";
    }
    public static int getCommentTotalLikes(int commentId){
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +commentId ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getInt("total_likes");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return 0;
    }
}
