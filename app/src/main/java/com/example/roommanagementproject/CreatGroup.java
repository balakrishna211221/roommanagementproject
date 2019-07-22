package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LongSummaryStatistics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatGroup extends AppCompatActivity {
TextView name,phoneno,gname;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_group);
       name=findViewById(R.id.namecreatg);
     //   phoneno=findViewById(R.id.numcreatg);
        CreatGroup.this.setTitle("Creat Group");
        gname=findViewById(R.id.grname);
button=findViewById(R.id.button2);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {



        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String num = preferences.getString("mobile", null);
       String pass = preferences.getString("pass", null);


        JSONObject json = new JSONObject();
        JSONArray members = new JSONArray();
        JSONObject mumjsonobect=new JSONObject();

        try {
            mumjsonobect.put("name",name.getText().toString().trim());
            mumjsonobect.put("mobile",preferences.getString("mobile", null));

            members.put(mumjsonobect);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            json.put("action","create_group");
            json.put("admin_id",preferences.getString("mobile", null));
            json.put("members",members);
            json.put("gname",gname.getText().toString().trim());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

        CreateGroupInterFace createGroupInterFace=retrofit.create(CreateGroupInterFace.class);

        JsonObject object=new JsonParser().parse(json.toString()).getAsJsonObject();

        Call<CreatgroupPojo> creatgroupPojoCall=createGroupInterFace.CREATGROUP_POJO_CALL(object);
        creatgroupPojoCall.enqueue(new Callback<CreatgroupPojo>() {
            @Override
            public void onResponse(Call<CreatgroupPojo> call, Response<CreatgroupPojo> response) {
                if (response.body().getResponse().equalsIgnoreCase("success")){
                    Toast.makeText(CreatGroup.this, "success group created", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreatgroupPojo> call, Throwable t) {

            }
        });

    }
});



    }
}
