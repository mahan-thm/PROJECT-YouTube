package server.database;

import org.json.JSONArray;

import java.util.List;

public class Dbm {



    public static boolean checkUsername(String username_input){
        //TODO


        return false;
    }


    public static boolean authorize(String usernameInput,String passwordInput) {
        //TODO
        return false;
    }

    public static void signUp(String username, String password, String name, String email, String number){
        //TODO
    }

    public static String getVideo_Title(int id) {
        return "";
    }

    public static String getVideo_TitleBody(int id) {
        return "";
    }

    public static String getVideo_creationTime(int id) {
        return "";
    }

    public static String getVideo_duration(int id) {
        return "";
    }

    public static String getVideo_totalView(int id) {
        return "";
    }

    public static String getVideo_totalLikes(int id) {
        return "";
    }

    public static List<Integer> getRandomVideoId(int videoCount) {
        return List.of();
    }

    public static int get_user_id() {
        return 0;
    }

    public static String getVideo_totalDislikes(int id) {
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

    public static String getChannelDiscreption(int channelId) {
        return "";
    }

    public static void createChannel(String channelName, String channelUsername, String channelDescription, JSONArray tags) {
    }

    public static boolean checkChannelUsername(String channelUsername) {
        return false;
    }

    public static void deleteChannel(int channelId) {
    }

    public static void addVideo(int channelId, String videoName, String videoDescription, JSONArray tags) {
    }

    public static void subscribeChannel(int channelId, int userId) {
        
        //TODO کارش این متد سنگینه حواست باشه 
    }

    public static void unsubscribeChannel(int channelId, int userId) {
    }

    public static void removeVideo(int videoId) {
    }

    public static void addComment(int videoId, String text, int repliedToId) {
    }

    public static void removeComment(int commentId) {
    }

    public static int getUserWatchedVideosId(int userId) {
        return userId;
    }

    public static void addToPlaylist(int videoId, int playlistId) {
    }

    public static void removeFromPlaylist(int videoId, int watchedVideosId) {
    }

    //test 6
}
