package server.database;

import org.json.JSONArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, username, password);
            stat =  con.createStatement();
        }catch (SQLException | ClassNotFoundException e){
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
            boolean toReturn = rs.next();
            close();
            return toReturn;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return false;
    }


    public static boolean authorize(String usernameInput,String passwordInput) {
        open();
        String query = "SELECT * FROM users " +
                "WHERE username =  '" + usernameInput+ "'" +"AND pass = '" + passwordInput+ "'";
        try{
            ResultSet rs = stat.executeQuery(query);
            boolean bool = rs.next();
            close();
            return bool;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return false;
    }

    public static String getVideo_Title(int id) {
        open();
        String query = "SELECT title  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            rs.next();
            String str = rs.getString("title");
            close();
            return str;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return "";
    }


    public static String getVideo_creationTime(int id) {
        open();
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            rs.next();
            String str= rs.getString("creation_date");
            close();
            return str;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return "";
    }

    public static String getVideo_duration(int id) {
        open();
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            rs.next();
            String str= rs.getString("duration");
            close();
            return str;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return "";
    }

    public static String getVideo_totalView(int id) {
        open();
        String query = "SELECT *  FROM videos " +
                "WHERE id =  " +id ;
        try{
            ResultSet rs = stat.executeQuery(query);
            rs.next();
            String str= rs.getString("total_view");
            close();
            return str;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return "";
    }

    public static int getVideo_totalLikes_dislikes(int id , String type) {
        open();
        String query = "SELECT type  FROM saved_videos " +
                "WHERE video_id =  " +id +" AND type = ";
        if (type =="liked"){
            query+="\'liked\'";
        }
        else if (type == "disliked") {
            query+="\'disliked\'";
        }
        else System.out.println("invlaid expression");
        int counter = 0;
        try{
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()){
                counter++;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
            return counter;
        }
    }

    public static int get_user_id(String username) {
        open();
        String query = "SELECT *  FROM users " +
                "WHERE username =  '" + username +"'";
        try{
            ResultSet rs = stat.executeQuery(query);
            rs.next();
            int res = rs.getInt("id");
            close();
            return res;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return 0;
    }
    public  static  void main (String args[]) {
//        addUser("kourosh" , "111" ,"kourosh", "k.rasht@gmail.com" ,"1234" ,false ,"29.3.403" );
//        System.out.println( checkUsername("koursh"));
//        System.out.println(authorize("koursh" ,"111"));
//        todo addVideo(1 , "vid" ,"this is a video" , "320" ,"29.3.403" ,["[\"fun\", \"horror\", \"adventure\"]"]);
//        System.out.println((getVideo_Title(1)));
//        System.out.println(getVideo_creationTime(1));
//        System.out.println(getVideo_duration(1));
//        System.out.println(getVideo_totalLikes(1));
//        System.out.println(getRandomVideoId(6));
//        System.out.println(get_user_id("kourosh"));
//        System.out.println(getVideo_totalLikes_dislikes(1 , "disliked"));
//        System.out.println(getRandomChannelId(5));
//        System.out.println(getCommentCount(1));
//        System.out.println(getCommentIdList(1));
//        System.out.println(getCommentText(1));
        //todo : get commentCreationTime :Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]  . so we must save such String in the dataBase
//        System.out.println(getcommentCreationTime(1));
//        System.out.println(getChannelTotalVideoes(1));
//        System.out.println(getChannelTotalSubscribers(1));
//        System.out.println(checkChannelUsername("kingMen"));

    }


    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static boolean isRepeated (List<Integer> numList , int newNum){
        for(Integer i : numList){
            if ( (int)i == newNum){
                return true;
            }
        }
        return false;
    }
    public static List<Integer> getRandomChannelId(int channelCount) {
        // todo change the name to getRandomChannel_VideoId
        open();
        String maxId = "SELECT MAX(id) AS maxId FROM channels";
        int max=0;
        List <Integer> randList= new ArrayList<>();
        try {
            ResultSet rs = stat.executeQuery(maxId);
            if(rs.next()) {     // rs.next() moves the curser to the first row of resultset
                max = rs.getInt("maxId");
            }
            for(int i=0 ; i< channelCount ; i++){
                int randomNumber = getRandomNumber(1 ,max );
                if(! isRepeated(randList , randomNumber)) {
                    randList.add(randomNumber);
                }
                else i--;
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
            rs.next();
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
        //todo : Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]  . so we must save such String in the dataBase

        open();
        String query = "SELECT * FROM comments" +
                " WHERE id =" + commentId;
        String creation_time="";
        try {
            ResultSet rs = stat.executeQuery(query);
            rs.next();
            creation_time =rs.getString("creation_date");
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
        String query = " SELECT * FROM uploaded_videos " +
                "WHERE channel_id = " + channelId ;
        int total_videos =0;
        try {
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                total_videos++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return total_videos;
    }

    public static int getChannelTotalViews(int channelId) {
        // todo will we save total views in channels table or we gonna count it every time?
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
        String query = " SELECT * FROM subscribed_channels " +
                "WHERE channel_id = " + channelId ;
        int total_subscribers =0;
        try {
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()){
                total_subscribers++;
            }
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
                "WHERE channel_username =  '" + channelUsername+ "'";
        try{
            ResultSet rs = stat.executeQuery(query);
            boolean bool = rs.next();
            close();
            return bool;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
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
                    "INSERT INTO TABLE videos (id , title, video_description ,creation_date, duration) VALUES ("
                            + lastId +",'"+ title + "','" + videoDescription +"','" + creationDate + "','" + duration + "')";
            int res = stat.executeUpdate(query);
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
        String query =
                "DELETE FROM videos WHERE id = " + videoId
                +"DELETE FROM uploaded_videos WHERE id = " + videoId
                +"DELETE FROM saved_videos WHERE  id = " + videoId // don't need to set the type of the video
                +"DELETE FROM tags WHERE type = \"video\""
                +"DELETE FROM ";


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
                    "INSERT INTO TABLE comments (id ,user_id, video_id,creation_date, body, repliedTo) VALUES ("
                            + lastId +","+ user_id + "," + video_id +",'" + creationDate + "','" + text + "',"+ repliedToId+")";
            int res = stat.executeUpdate(query);
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
        // todo all void methods has int rs = stat.executeUpdate(query);
        // todo nemikhay age amaliat dorost anjam nashod -1 return kone?
        open();
        try {
        String query ="DELETE FROM comments WHERE id = " + commentId;
        int rs = stat.executeUpdate(query);
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

            int rs = stat.executeUpdate(query);

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

            int rs = stat.executeUpdate(query);

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
        String query = "INSERT INTO users ( username ,name , number ,pass , email , is_premium ,creation_date) VALUES ('"+
                username + "','" + name+"','"+ number + "','" +password + "','" +email+" '," + is_premium +", '"+ creationDate +"')";
        try{
            int rs = stat.executeUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }

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

    public static int addChannel(String channelName,String channelUsername, int owner_id, String creationDate ,String channelDescription ) {
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
                    "INSERT INTO TABLE channels (id , owner_id, channel_username, channel_description ,creation_date) VALUES ("
                            + lastId +","+ owner_id + ",'"+ channelUsername + "','" + channelDescription +"','" + creationDate + "')";
            int res = stat.executeUpdate(query);
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
        open();
        try {
            String query = "DELETE FROM channels WHERE id = " + channelId ;
            int rs = stat.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }



    public static String getVideoPath() {
        return videoPath;
    }

    public static void addUserSubscribedChannels(int channelId, int userId /*, String add_date*/) {
        open();
        String query = "INSERT INTO subscribed_channels (channel_id , user_id , is_notif_on , add_date"
                        +"VALUES ( " + channelId +","+ userId +","+ false /*",'"+ add_date+"')"*/ ;
        try{
           int rs = stat.executeUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public static void addChannelTotalSubscribers(int channelId) {
        // todo
    }

    public static void removeUserSubscribedChannels(int channelId, int userId) {
        open();
        try {
            String query = "DELETE FROM subscribed_channels WHERE id = " + channelId ;
            int rs = stat.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public static void reduceChannelTotalSubscribers(int channelId) {
    }

    public static void addSavedVideo(int userId, String playlistType, int videoId) {
        open();
//        String maxId = "SELECT MAX(id) AS maxId FROM videos";
//        int lastId=0;
//        try {
//            ResultSet rs = stat.executeQuery(maxId);
//            // bug probablity
//            lastId =rs.getInt("maxId") ;
//            lastId++;

//            String query =
//                    "INSERT INTO TABLE saved_videos ( user_id ,video_Id , type ) VALUES ("
//                            + userId +","+ videoId + ",'" + playlistType+ "')";
//            int res = stat.executeUpdate(query);
//            close();
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        finally {
//            close();
//        }
//        return -1;

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
        finally {
            close();
        }
        return 0;
    }

    public static int getCommentSender_id(int commentId) {
        return commentId;
    }

}

