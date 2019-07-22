package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.roommanagementproject.databinding.ActivityGroupstransactionlistBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Groupstransactionlist extends AppCompatActivity {
ActivityGroupstransactionlistBinding activityGroupstransactionlistBinding;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupstransactionlist2);
        Intent intent=getIntent();
        final String adminum =intent.getStringExtra("groupadmin");
        final String groupname=intent.getStringExtra("groupname");
       String gid=intent.getStringExtra("gid");
       recyclerView=findViewById(R.id.recyleG);

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_trans_from_group");
            jsonObject.put("gu_id",gid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();
        //     Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();
        GroupTListInterface groupTListInterface=retrofit.create(GroupTListInterface.class);
        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
        // JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
        Call<GroupTListPojo1> groupTListPojo1Call =groupTListInterface.GROUP_LIST_POJO_CALL(object);
         /*     Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

              PTInterface     ptInterface=retrofit.create(PTInterface.class);
              JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

              Call<PTPojo1>  ptPojoCall=ptInterface.PT_POJO_1_CALL(object);*/
        groupTListPojo1Call.enqueue(new Callback<GroupTListPojo1>() {
            @Override
            public void onResponse(Call<GroupTListPojo1> call, Response<GroupTListPojo1> response) {
                @SuppressLint("WrongConstant") LinearLayoutManager layoutManager=new LinearLayoutManager(Groupstransactionlist.this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new GroupTAdp(Groupstransactionlist.this,response.body().getData()));
        if(response.body().getResponse().equalsIgnoreCase("empty")){
            Toast.makeText(Groupstransactionlist.this, "empty", Toast.LENGTH_SHORT).show();
        }

            }

            @Override
            public void onFailure(Call<GroupTListPojo1> call, Throwable t) {
                Toast.makeText(Groupstransactionlist.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });


    }
}