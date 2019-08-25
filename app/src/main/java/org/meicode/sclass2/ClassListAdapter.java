package org.meicode.sclass2;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.MyViewHolder>{

    Context context;
    ArrayList<ClassListItem> ClassListItems;

    public ClassListAdapter(Context context, ArrayList<ClassListItem> ClassListItems) {
        this.context = context;
        this.ClassListItems = ClassListItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.class_list_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        TextView classIDView = myViewHolder.classIDView;
        TextView classTimeView = myViewHolder.classTimeView;

        classIDView.setText("ID: " + ClassListItems.get(i).getClassID());
        classTimeView.setText("Name: " + ClassListItems.get(i).getClassName());

        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // start another activity here


            }
        });


    }

    @Override
    public int getItemCount() {
        return ClassListItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView classIDView, classTimeView;


        public MyViewHolder(final View itemView) {
            super(itemView);

            classIDView = itemView.findViewById(R.id.classId);
            classTimeView = itemView.findViewById(R.id.className);

            view=itemView;
        }
    }
}
