package com.example.roommanagementproject;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class GroupsAdt extends RecyclerView.Adapter<GroupsAdt.MyViewHolderG> {

    List<GroupslistPojo2> groupslistPojo2s;
    Context context;
    public GroupsAdt(FragmentActivity activity, List<GroupslistPojo2> data) {
        groupslistPojo2s=data;
        context=activity;

    }

    @NonNull
    @Override
    public GroupsAdt.MyViewHolderG onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.grouplistcustom,null);
        return new MyViewHolderG(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupsAdt.MyViewHolderG holder, final int position) {


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(context,Groupstransactionlist.class);
                String adminintent=groupslistPojo2s.get(position).getGadmin();
                String gnameintent=groupslistPojo2s.get(position).getGname();
                String gidintent=groupslistPojo2s.get(position).getGid();
                intent1.putExtra("groupadmin",adminintent );
                intent1.putExtra("groupname", gnameintent);
                intent1.putExtra("gid",gidintent);
                context.startActivities(new Intent[]{intent1});
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+groupslistPojo2s.get(position).getGadmin(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,GroupTransaction.class);

                String adminintent=groupslistPojo2s.get(position).getGadmin();
               String gnameintent=groupslistPojo2s.get(position).getGname();
               String gidintent=groupslistPojo2s.get(position).getGid();
                intent.putExtra("groupadmin",adminintent );
                intent.putExtra("groupname", gnameintent);
                intent.putExtra("gid",gidintent);
                context.startActivities(new Intent[]{intent});
            }
        });
     holder.gid.setText("group id:"+groupslistPojo2s.get(position).getGid());
        holder.gname.setText(groupslistPojo2s.get(position).getGname());
        holder.admin.setText("admin of group:"+groupslistPojo2s.get(position).getGadmin());



    }

    @Override
    public int getItemCount() {
        return groupslistPojo2s.size();


    }

    public class MyViewHolderG extends  RecyclerView.ViewHolder{
        TextView admin,gid,gname;
        Button button;
        LinearLayout linearLayout;
        public MyViewHolderG(View view) {

            super(view);
            linearLayout=view.findViewById(R.id.addmumber);
            button=view.findViewById(R.id.addtransactionsingroup);
            admin=view.findViewById(R.id.admin);
            gid=view.findViewById(R.id.gid);
            gname=view.findViewById(R.id.groupname);
        }
    }
}
