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
    private static final String videoPath="" ;
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
    public static boolean checkUsername(String usernameInput){
        open();
        String query = "SELECT * FROM users " +
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
        String query = "SELECT * FROM users " +
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
        String query = "SELECT title  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
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
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
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
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
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
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
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
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
            return rs.getString("total_likes");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
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
        String query = "SELECT *  FROM users " +
                "WHERE username =  '" + username +"'";
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
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
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
            return rs.getString("total_dislikes");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return "";
    }

    public static void addUser(String username, String password, String name,String email,
                               String number,int is_premium,String creationDate ) {
        open();
        String query = "INSERT INTO users ( username ,name , number ,pass , email , is_premium ,creationDate) VALUES ('"+
                username + "','" + name+"','"+ number + "','" +password + "','" +email+" '," + is_premium +", '"+ creationDate +"')";
        try{
            int rs = stat.executeUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        close();

    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static List<Integer> getRandomChannelId(int channelCount) {
        open();
        String maxId = "SELECT MAX(id) AS maxId FROM channels";
        int max=0;
        List <Integer> randList= new ArrayList<>();
        try {
            ResultSet rs = stat.executeQuery(maxId);
            if(rs.next()) {     // rs.next() movesthe curser to the first row of resultset
                max = rs.getInt("maxId");
            }
        for(int i=0 ; i< channelCount ; i++){
            int randomNumber = getRandomNumber(1 ,max );
            randList.add(randomNumber);
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
         finally {
            close();
        }
        return randList;
    }


    public static int getCommentCount(int videoId) {
        open();
        String query = " SELECT * FROM comments " +
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
        finally {
            close();
        }
        return count;
    }

    public static List<Integer> getCommentIdList(int videoId) {
        open();
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
        finally {
            close();
        }
        return commentIdList;
    }

    public static String getCommentText(int commentId) {
        open();
        String query = "SELECT body FROM comments" +
                " WHERE id =" + commentId;
        String body="";
        try {
            ResultSet rs = stat.executeQuery(query);
               body =rs.getString("body");
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return body;
    }

    public static Timestamp getcommentCreationTime(int commentId) {
        open();
        String query = "SELECT * FROM comments" +
                " WHERE id =" + commentId;
        String creation_time="";
        try {
            ResultSet rs = stat.executeQuery(query);
            creation_time =rs.getString("creationDate");
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return Timestamp.valueOf(creation_time);
    }



    public static int getChannelTotalVideoes(int channelId) {
        open();
        String query = " SELECT * FROM channels " +
                "WHERE channel_id = " + channelId ;
        int total_videos =0;
        try {
             ResultSet rs = stat.executeQuery(query);
             total_videos = rs.getInt("total_videos");
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return total_videos;
    }

    public static int getChannelTotalViews(int channelId) {
        open();
        String query = " SELECT * FROM channels " +
                "WHERE channel_id = " + channelId ;
        int total_views =0;
        try {
            ResultSet rs = stat.executeQuery(query);
            total_views = rs.getInt("total_views");
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return total_views;
    }

    public static int getChannelTotalSubscribers(int channelId) {
        open();
        String query = " SELECT * FROM channels " +
                "WHERE channel_id = " + channelId ;
        int total_subscribers =0;
        try {
            ResultSet rs = stat.executeQuery(query);
            total_subscribers = rs.getInt("total_subscribers");
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return total_subscribers;
    }
    public static boolean checkChannelUsername(String channelUsername) {
        open();
        String query = "SELECT * FROM channels " +
                "WHERE channel_name =  '" + channelUsername+ "'";
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



    public static int addVideo(int channelId, String title , String videoDescription,String duration , String creationDate , JSONArray tags) {
        // todo mmd hosain we don't need channel id
        open();
        String maxId = "SELECT MAX(id) AS maxId FROM videos";
        int lastId=0;
        try {
            ResultSet rs = stat.executeQuery(maxId);
            // bug probablity
            lastId =rs.getInt("maxId") ;
            lastId++;

            String query =
                    "INSERT INTO TABLE videos (id , title, video_description ,creationDate, duration) VALUES ("
                            + lastId +",'"+ title + "','" + videoDescription +"','" + creationDate + "','" + duration + "')";
            stat.executeUpdate(query);
            close();
            return lastId;

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return -1;
    }





    public static void removeVideo(int videoId) {
        // todo mmdhosain

    }

    public static int addComment(int video_id,int user_id, String text, int repliedToId,String creationDate) {
        open();
        String maxId = "SELECT MAX(id) AS maxId FROM comments";
        int lastId=0;
        try {
            ResultSet rs = stat.executeQuery(maxId);
            // bug probablity
            lastId =rs.getInt("maxId") ;
            lastId++;

            String query =
                    "INSERT INTO TABLE comments (id ,user_id, video_id,creationDate, body, repliedTo) VALUES ("
                    + lastId +","+ user_id + "," + video_id +",'" + creationDate + "','" + text + "',"+ repliedToId+")";
            stat.executeUpdate(query);
            close();
            return lastId;

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return -1;
    }

    public static void removeComment(int commentId) {
        open();
        try {



            String query ="DELETE FROM comments WHERE id = " + commentId;

            stat.executeUpdate(query);

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }


    public static void editCommentLike(int commentId, int userId, String likeType) {
        open();
        try {
            String query = " UPDATE comments SET type = '"+likeType+"' WHERE commentId =  "+commentId ;

            stat.executeUpdate(query);

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public static void removeCommentLike(int commentLike_id) {
        open();
        try {
            String query = "DELETE FROM liked_comments WHERE id = " + commentLike_id ;

            stat.executeUpdate(query);

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
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
            close();
            return rs.getString("video_description");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return "";
    }

    public static String getVideo_link(int videoId) {
        return videoPath +"/"+ videoId;
    }


    public static String getUsername(int userId) {
        open();
        String query = "SELECT *  FROM users" +
                "WHERE id =  " +userId ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
            return rs.getString("username");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return "";
    }

    public static String getChannelDescription(int channelId) {

        open();
        String query = "SELECT *  FROM channels" +
                "WHERE id =  " +channelId ;
        try{
            ResultSet rs = stat.executeQuery(query);
            close();
            return rs.getString("channel_description");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return "";
    }

    public static int addChannel(String channelUsername, int owner_id, String creationDate ,String channelDescription ) {
        // it returns the new channel id
        open();
        String maxId = "SELECT MAX(id) AS maxId FROM channels";
        int lastId=0;
        try {
            ResultSet rs = stat.executeQuery(maxId);
            // bug probablity
            lastId =rs.getInt("maxId") ;
            lastId++;
            String query =
                    "INSERT INTO TABLE channels (id , owner_id, channel_username, channel_description ,creationDate) VALUES ("
                            + lastId +","+ owner_id + ",'"+ channelUsername + "','" + channelDescription +"','" + creationDate + "')";
            stat.executeUpdate(query);
            close();
            return lastId;

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return -1;
    }

    public static List<Integer> getChannelVideoList(int channelId) {
        // todo mmd hosain age catch kard , NULL return kone?
        open();
        List <Integer> channelVideoList = new ArrayList<>();
        String query = "SELECT id FROM uploaded_videos WHERE channel_id = " + channelId;
        try {
            ResultSet rs = stat.executeQuery(query);
            close();
            while (rs.next()){
                channelVideoList .add (rs.getInt("id"));
            }
            return channelVideoList;
        }catch (SQLException e){
            e.printStackTrace();
            close();
        }
        return null;
    }

    public static List<Integer> getVideoCommentList(Integer videoId) {
        // todo mmd hosain age catch kard , NULL return kone?
        open();
        List <Integer> videoCommentList = new ArrayList<>();
        String query = "SELECT id FROM comments WHERE video_id = " + videoId;
        try {
            ResultSet rs = stat.executeQuery(query);
            close();
            while (rs.next()){
                videoCommentList .add (rs.getInt("id"));
            }
            return videoCommentList;
        }catch (SQLException e){
            e.printStackTrace();
            close();
        }
        return null;
    }

    public static void removeChannel(int channelId) {
    }

    public static String getVideoPath() {
        return videoPath;
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
