package org.meicode.sclass2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.HashMap;
import java.util.Map;
import java.util.logging.ErrorManager;


public class MainActivity extends AppCompatActivity {
    public UserDetails user;
    private static String logUrl = UserDetails.login;
    private RequestQueue mQueue;
    private static RadioGroup rg;
    private static RadioButton mood;
    private static Button login;
    private static TextView attempt, err, sign;
    private static EditText tid, tpass;
    private String  id, pass, logMood;
    private int count = 0;
    static String errorMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign = findViewById(R.id.textView2);
        err = findViewById(R.id.txtError);

        login = findViewById(R.id.buttonLogin);
        rg = findViewById(R.id.radioGrp);
        tid = findViewById(R.id.txtId);
        tpass = findViewById(R.id.txtPass);
        attempt = findViewById(R.id.textView);
        mQueue = Volley.newRequestQueue(this);
        user = new UserDetails(MainActivity.this);
        err.setText(user.getErrorMassege());
        user.setErrorMassege("");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = tid.getText().toString();
                pass = tpass.getText().toString();

                logMood = getRadioData();

                if(!id.equals("") && !pass.equals("")){

                    CheckAndLogin(pass,id);

                    ///means wrong pass
                    count++;
                    if(count == 3)login.setEnabled(false);
                    attempt.setText(3-count+" attempt left");
                }
                else{            ///some error
                    err.setText(errorMsg);
                }

            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onSignUpButtonClick();
            }
        });
    }


    public String getRadioData(){
        mood = findViewById(rg.getCheckedRadioButtonId());
        return mood.getText().toString();
    }


    public  void CheckAndLogin(String pass, String id){
        //final Intent dash = new Intent(this, dashboard.class);
        final Intent dash = new Intent(this, RecyclerViewActivity.class);
        mQueue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap<>();
        final Map<String, String> classList = new HashMap<>();

        params.put("id",id);
        params.put("pass", pass);
        params.put("mood", logMood);
        user.setMood(logMood);
        user.setId(id);

        JSONObject payload = new JSONObject(params);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, logUrl, payload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Log.d("json response", response.toString());
                    user = new UserDetails(MainActivity.this);
                    Log.d("Radio", logMood);

                    if(response.getString("status").equals("Ok")){
                        user.setStatus(response.getString("status"));
                        user.setFirstName(response.getString("fname"));
                        user.setLastName(response.getString("lname"));
                        user.setSpeed(response.getString("speed"));
                        JSONArray jsonArray = response.getJSONArray("classList");
                        JSONObject temp;
                        if( jsonArray != null){
                            for(int i = 0; i<jsonArray.length(); i++){
                                temp = jsonArray.getJSONObject(i);
                                Log.d("Testing: "+temp.getString("classid") , temp.getString("classname"));
                                classList.put(temp.getString("classid") , temp.getString("classname"));
                            }

                        }


                        user.setType(getRadioData());
                        startActivity(dash);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //user.setErrorMassege("Connection error");
            }
        });
        mQueue.add(req);


    }

    public void onSignUpButtonClick(){
        Intent dash = new Intent(this, signup.class);
        startActivity(dash);

    }



}