package com.example.roommanagementproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MandetoryAdp extends RecyclerView.Adapter<MandetoryAdp.MyViewHolder> {

    List<MandetoryListObjectPojo> mandetoryListObjectPojos;
    Context context;
    public MandetoryAdp(FragmentActivity mainActivity, List<MandetoryListObjectPojo> data) {
        mandetoryListObjectPojos=data;
        context=mainActivity;
    }

    @NonNull
    @Override
    public MandetoryAdp.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.mandecustom,null);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MandetoryAdp.MyViewHolder holder, int position) {



        holder.reason.setText(mandetoryListObjectPojos.get(position).getReason());
        holder.amount.setText("Amount:"+mandetoryListObjectPojos.get(position).getAmount());
        holder.sdate.setText("StartDate:"+mandetoryListObjectPojos.get(position).getDatetime());
        holder.edate.setText("LastDate:"+mandetoryListObjectPojos.get(position).getEnddate());



    }

    @Override
    public int getItemCount() {
        return mandetoryListObjectPojos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,phone,sdate,edate,amount,reason,status,stype;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sdate=itemView.findViewById(R.id.datetime);
            edate=itemView.findViewById(R.id.enddate);
            amount=itemView.findViewById(R.id.amount);
            reason=itemView.findViewById(R.id.reason);

        }
    }
}
