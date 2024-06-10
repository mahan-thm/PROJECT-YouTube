package server.database;

import org.json.JSONArray;

import java.util.List;
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

    public static String getVideo_totalView(int id) {
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getString("total_view");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }

    public static String getVideo_totalLikes(int id) {
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
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

    public static List<Integer> getRandomVideoId(int videoCount) {
        return List.of();
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

    public static String getVideo_totalDislikes(int id) {
        open();
        String query = "SELECT *  FROM videos" +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            return rs.getString("total_dislikes");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }

    public static List<Integer> getRandomChannelId(int channelCount) {
        return List.of();
    }

    public static int getCommentCount(int videoId) {
        return videoId;
    }

    public static List<Integer> getCommentIdList(int videoId) {
        return List.of();
    }

    public static String getCommentText(int commentId) {
        return "";
    }

    public static String getcommentCreationTime(int commentId) {
        return "";
    }

    public static String getcommentRepliedTo(int commentId) {
        return "";
    }

    public static int getChannelTotalVideoes(int channelId) {
        return 0;
    }

    public static int getChannelTotalViews(int channelId) {
        return channelId;
    }

    public static int getChannelTotalSubscribers(int channelId) {
        return 0;
    }




    public static boolean checkChannelUsername(String channelUsername) {
        return false;
    }



    public static void addVideo(int channelId, String videoName, String videoDescription, JSONArray tags) {
    }




    public static void removeVideo(int videoId) {
    }

    public static void addComment(int videoId, String text, int repliedToId) {
    }

    public static void removeComment(int commentId) {
    }


    public static void editCommentLike(int commentId, int userId, String liked) {
    }

    public static void removeCommentLike() {
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
        return null;
    }

    public static String getVideo_link(int videoId) {
        return null;
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

    //test 6
}
