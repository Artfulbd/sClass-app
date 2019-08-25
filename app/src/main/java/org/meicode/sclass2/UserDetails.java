package org.meicode.sclass2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;
import java.util.Set;

public class UserDetails {
    static private String ip = "http://192.168.43.11/sclassapi/serve/";
    static String login = ip+"login.php";
    static String signup = ip+"reguser.php";

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;

    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Shared preferences file name
    private static final String PREF_NAME = "UserDetails";


    UserDetails(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }




    public void setErrorMassege(String msg){
        editor.putString("error", msg);
        editor.commit();
    }

    public String getErrorMassege(){
        return pref.getString("error","");
    }

    public void clearAll(){

        editor.clear().commit();

    }

    public String getFirstName() {
        return  pref.getString("firstname","");
    }

    public void setFirstName(String name) {
        editor.putString("firstname", name);
        editor.commit();
    }

    public String getLastName() {
        return  pref.getString("lastname","");
    }

    public void setLastName(String name) {
        editor.putString("lastname", name);
        editor.commit();
    }

    public String getSpeed() {
        return  pref.getString("speed","0");
    }

    public void setSpeed(String speed) {
        editor.putString("speed", speed);
        editor.commit();
    }


    public void setStatus(String status) {
        editor.putString("status", status);
        editor.commit();
    }

    public String getStatus(){
        return pref.getString("status","");
    }

    public void setId(String id) {
        editor.putString("id", id);
        editor.commit();
    }

    public void setType(String type) {
        editor.putString("type", type);
        editor.commit();
    }

    public String getId() {
        return pref.getString("id","");
    }

    public void setMood(String mood){
        editor.putString("mood", mood);
        editor.commit();
    }
    public String getMood(){
        return pref.getString("mood","");
    }

    public  void setClassList(String clasid, String className){
        editor.putString(clasid,className);
        editor.commit();
    }



    public  void setList(Map map){
        Set< Map.Entry<String, String>> st = map.entrySet();
        for(Map.Entry<String, String> me:  st){
            setClassList(me.getKey(), me.getValue());
        }
    }


}