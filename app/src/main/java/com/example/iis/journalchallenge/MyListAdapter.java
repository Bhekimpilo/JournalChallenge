package com.example.iis.journalchallenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{


    private ArrayList<Arrange> myJournalEntries = new ArrayList<Arrange>();
    private SQLiteClass sqLiteClass;
    private Context context;


    public MyListAdapter(Context mContext, ArrayList<Arrange> names){
        myJournalEntries = names;
        context = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.entryTitle.setText(myJournalEntries.get(position).toString());
        holder.entry.setText(myJournalEntries.get(position).getDate());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("title", myJournalEntries.get(position).getTitle())
                .putExtra("date", myJournalEntries.get(position).getDate())
                        .putExtra("entry", myJournalEntries.get(position).getEntry())
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );


            }
        });

    }

    @Override
    public int getItemCount() {
        return myJournalEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView entryTitle, entry;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            entryTitle = itemView.findViewById(R.id.listText);
            entry = itemView.findViewById(R.id.dateView);
            parentLayout = itemView.findViewById(R.id.parentLayout);

        }
    }

}
