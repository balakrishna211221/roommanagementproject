package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.roommanagementproject.databinding.ActivityAddGroupBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddGroup extends AppCompatActivity {
ActivityAddGroupBinding activityAddGroupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activityAddGroupBinding= DataBindingUtil.setContentView(AddGroup.this,R.layout.activity_add_group);
       Intent intent=getIntent();
     final String adminum =intent.getStringExtra("groupadmin");
      final String groupname=intent.getStringExtra("groupname");
      activityAddGroupBinding.addgropubt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
              String num = preferences.getString("mobile", null);
              String pass = preferences.getString("pass", null);


              JSONObject json = new JSONObject();
              JSONArray members = new JSONArray();
              JSONObject mumjsonobect=new JSONObject();
              try {
                  mumjsonobect.put("name",activityAddGroupBinding.name.getText().toString().trim());
                  mumjsonobect.put("mobile",activityAddGroupBinding.phone.getText().toString().trim());

                  members.put(mumjsonobect);
              } catch (JSONException e) {
                  e.printStackTrace();
              }

              try {
                  json.put("action","create_group");
                  json.put("admin_id",adminum);
                  json.put("members",members);
                  json.put("gname",groupname);

              } catch (JSONException e) {
                  e.printStackTrace();
              }
              Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

              GroupAddInterface groupAddInterface=retrofit.create(GroupAddInterface.class);

              JsonObject object=new JsonParser().parse(json.toString()).getAsJsonObject();
              Call<GroupAddPojo> groupAddPojoCall=groupAddInterface.GROUP_ADD_POJO_CALL(object);

              groupAddPojoCall.enqueue(new Callback<GroupAddPojo>() {
                  @Override
                  public void onResponse(Call<GroupAddPojo> call, Response<GroupAddPojo> response) {
                      if(response.body().getResponse().equalsIgnoreCase("success")){
                          Toast.makeText(AddGroup.this, "success", Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onFailure(Call<GroupAddPojo> call, Throwable t) {

                  }
              });






          }
      });

    }
}
