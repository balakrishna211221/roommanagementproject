package com.example.roommanagementproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class PTAdp  extends RecyclerView.Adapter<PTAdp.MyViewHolder> {
    List<PTPojo2> ptPojo2s;
    Context context;
    SharedPreferences sharedPreferences;

    public PTAdp(FragmentActivity mainActivity, List<PTPojo2> data) {
        ptPojo2s=data;
        context=mainActivity;
    }



    @NonNull
    @Override
    public PTAdp.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.trancustom,null);
        return   new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.amount.setText(ptPojo2s.get(position).getAmount());
        holder.reason.setText(ptPojo2s.get(position).getReason());
        holder.time.setText(ptPojo2s.get(position).getDatetime());

            int totalPrice = 0;
            for (int i = 0; i < ptPojo2s.size(); i++) {
                totalPrice += Integer.parseInt(ptPojo2s.get(i).getAmount());

            }

        sharedPreferences=context.getSharedPreferences("total",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pt", String.valueOf(totalPrice));
        editor.commit();







    }


    @Override
    public int getItemCount() {
        return ptPojo2s.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView reason,time,amount,total;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reason=itemView.findViewById(R.id.Reason);
            time=itemView.findViewById(R.id.Datetime);
            amount =itemView.findViewById(R.id.amount);



        }
    }
}