package org.meicode.sclass2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity {


    ArrayList<ClassListItem> items;
    ClassListAdapter adapter;
    RecyclerView recyclerView;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        mQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager=new LinearLayoutManager(RecyclerViewActivity.this);
        recyclerView.setLayoutManager(manager);
        items=new ArrayList<>();
        adapter=new ClassListAdapter(RecyclerViewActivity.this, items);
        recyclerView.setAdapter(adapter);



        adapter.notifyDataSetChanged();


        // it will load data from api and show on recyclerview

        // getUserData();


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCourseDialog();

            }
        });


    }


    private void addCourseDialog(){




        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(RecyclerViewActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_add_course, null);
        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();

        TextView courseNameView = dialogView.findViewById(R.id.courseName);
        Button d_submit = dialogView.findViewById(R.id.submit);


        alertDialog.getWindow()
                .setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        alertDialog.show();


        d_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // call API

//                addCourseAPICall(
//                        serDetails.getUserID(),
//                        courseNameView.getText().toString()
//                );



            }
        });



    }



    public  void getUserData(String pass, String id){
        //final Intent dash = new Intent(this, dashboard.class);
        final Intent dash = new Intent(this, RecyclerViewActivity.class);
        mQueue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap<>();
        final Map<String, String> classList = new HashMap<>();

        params.put("id",id);
        params.put("pass", pass);

        JSONObject payload = new JSONObject(params);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://url of user info", payload, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Log.d("json response", response.toString());
                    if(response.getString("status").equals("Ok")){

                        JSONArray jsonArray = response.getJSONArray("classList");

                        for (int i=0; i < jsonArray.length(); i++){


                            items.add(new ClassListItem(
                                            jsonArray.getJSONObject(i).getString("classid"),
                                            jsonArray.getJSONObject(i).getString("classname")
                                      ));

                        }


                        adapter.notifyDataSetChanged();

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




}
