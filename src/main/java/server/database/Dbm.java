package server.database;

import org.json.simple.JSONObject;

import java.util.List;

import static client.models.Main.write;

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

    public static List<Integer> getRandomVideoId() {
        return List.of();
    }

    public static int get_user_id() {
        return 0;
    }

    //test 6
}
