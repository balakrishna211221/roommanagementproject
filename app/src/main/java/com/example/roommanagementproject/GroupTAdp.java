package com.example.roommanagementproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class GroupTAdp extends RecyclerView.Adapter<GroupTAdp.MyViewHolder> {
    List<GroupTListPojo2> groupTListPojo2s;
    Context context;

    public GroupTAdp(Groupstransactionlist groupstransactionlist, List<GroupTListPojo2> data) {
        groupTListPojo2s =data;
        context=groupstransactionlist;
    }

    @NonNull
    @Override
    public GroupTAdp.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.grouptlistcustom,null);
        return   new GroupTAdp.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GroupTAdp.MyViewHolder holder, int position) {
        holder.amount.setText(groupTListPojo2s.get(position).getAmount());
        holder.date.setText(groupTListPojo2s.get(position).getDatetime());
        holder.reason.setText(groupTListPojo2s.get(position).getReason());


    }

    @Override
    public int getItemCount() {
        return groupTListPojo2s.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView reason,date,amount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reason=itemView.findViewById(R.id.Reasongl);
            date=itemView.findViewById(R.id.Datetimegl);
            amount=itemView.findViewById(R.id.amountgl);
        }
    }
}
