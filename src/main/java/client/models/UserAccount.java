package client.models;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static client.models.Main.read;
import static client.models.Main.request;

public class UserAccount {

    public String username;
    public String password;

    public UserAccount(String username,String password){
        this.username = username;
        this.password = password;
    }
    public UserAccount(){
        username = "";
        password = "";
    }

    public void writeRememberMe(){

        try {

            File file = new File("src/main/resources/rememberMe.txt");
            FileWriter fos = new FileWriter(file) ;
            JSONObject data = new JSONObject();
            data.put("isLoggedIn",true);
            data.put("username",username);
            data.put("password",password);

            fos.write(data.toString());
            fos.flush();


        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public boolean autoLogin() throws RuntimeException{

        try {

            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/rememberMe.txt")));
            JSONObject data = null;
            if (!content.equals("")) {

                data = new JSONObject(content);
                if (data.getBoolean("isLoggedIn")){
                    request.login(data.getString("username"),data.getString("password"));

                    String responseType = read().getString("responseType");
                    if(responseType.equals("/login_accepted")){
                        username = data.getString("username");
                        password = data.getString("password");
                        return true;
                    }
                }
                else {
                    username = "";
                    password = "";
                    return false;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        return false;
    }

}
