package org.meicode.sclass2;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    public UserDetails user;
    private String logUrl = UserDetails.signup;
    private RequestQueue mQueue;
    private static  EditText firstName, lastName, idd, speed, password, repassword, q1,q2;
    private  TextView er;
    private  Button sub;
    static String  errorMsg2 = "Please fill up all the field", hold ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firstName = findViewById(R.id.txtFname);
        firstName.setText("");
        lastName = findViewById(R.id.txtLname);
        lastName.setText("");
        idd = findViewById(R.id.txtId);
        idd.setText("");
        speed = findViewById(R.id.txtSpeed);
        speed.setText("");
        password = findViewById(R.id.txtpass1);
        password.setText("");
        repassword = findViewById(R.id.txtpass2);
        repassword.setText("");
        q1 = findViewById(R.id.txtq1);
        q1.setText("");
        q2 = findViewById(R.id.txtq2);
        q2.setText("");
        sub = findViewById(R.id.create);
        er = findViewById(R.id.txtErr);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAndCreate();
            }
        });


    }
    public void CheckAndCreate(){
        if(     (!idd.getText().equals(""))        &&
                (!firstName.getText().equals("")) &&
                (!lastName.getText().equals(""))  &&
                (!speed.getText().equals(""))     &&
                (!password.getText().equals(""))  &&
                (!repassword.getText().equals(""))&&
                (!q1.getText().equals(""))        &&
                (!q2.getText().equals(""))){

            createNew();
        }
        else{
            er.setText(errorMsg2);
        }

    }


    public  void createNew(){
        final Intent main = new Intent(this, MainActivity.class);
        mQueue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap<>();
        params.put("fname",firstName.getText().toString());
        hold = firstName.getText().toString();
        params.put("lname",lastName.getText().toString());
        params.put("pass", password.getText().toString());
        params.put("speed",speed.getText().toString());
        params.put("id", idd.getText().toString());
        params.put("qstn1", q1.getText().toString());
        params.put("qstn2", q2.getText().toString());


        JSONObject payload = new JSONObject(params);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, logUrl, payload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    user = new UserDetails(signup.this);
                    if(response.getString("status").equals("ID already exist")){
                        er.setText("This ID already exist");

                    }
                    else if(response.getString("status").equals("ID Successfully created") && response.getString("fname").equals(hold)){
                       user.setErrorMassege("Id successfully created");
                        startActivity(main);
                    }
                    else if(response.getString("status").equals("Problem on creating user")){
                        er.setText("Problem on creating user!!Try again later");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //user = new UserDetails(signup.this);
                //user.setErrorMassege("Connection error");
            }
        });
        mQueue.add(req);


    }


}
