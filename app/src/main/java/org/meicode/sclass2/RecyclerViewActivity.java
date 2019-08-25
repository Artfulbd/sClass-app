package org.meicode.sclass2;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {


    ArrayList<ClassListItem> items;
    ClassListAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager=new LinearLayoutManager(RecyclerViewActivity.this);
        recyclerView.setLayoutManager(manager);
        items=new ArrayList<>();
        adapter=new ClassListAdapter(RecyclerViewActivity.this, items);
        recyclerView.setAdapter(adapter);


        items.add(new ClassListItem("8", "CSE332"));
        items.add(new ClassListItem("9", "MAT250"));
        items.add(new ClassListItem("12", "POL101"));

        adapter.notifyDataSetChanged();


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

}
