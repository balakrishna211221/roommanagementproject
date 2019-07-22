package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.roommanagementproject.databinding.ActivityMakeMadetoryBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakeMadetory extends AppCompatActivity {
ActivityMakeMadetoryBinding  activityMakeMadetoryBinding;
    String phone,amount,startdate,enddate,reason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activityMakeMadetoryBinding= DataBindingUtil.setContentView(MakeMadetory.this,R.layout.activity_make_madetory);

       MakeMadetory.this.setTitle("Add Mandetory Payment");
        activityMakeMadetoryBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone= activityMakeMadetoryBinding.phone.getText().toString();
                amount = activityMakeMadetoryBinding.amount.getText().toString();
                startdate=  activityMakeMadetoryBinding.startdate.getText().toString();
                enddate= activityMakeMadetoryBinding.enddate.getText().toString();
                reason= activityMakeMadetoryBinding.reason.getText().toString();

                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("action","put_saving");
                    jsonObject.put("mobile",phone);
                    jsonObject.put("stype","t");
                    jsonObject.put("reason",reason);
                    jsonObject.put("amount",amount);
                    jsonObject.put("start_date",startdate);
                    jsonObject.put("end_date",enddate);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

                MakeMandetoryInterface retroInterface=retrofit.create(MakeMandetoryInterface.class);

                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();


                Call<MakeMandetoryPojo> makeMandetoryPojoCall=retroInterface.MAKE_MANDETORY_POJO_CALL(object);


                makeMandetoryPojoCall.enqueue(new Callback<MakeMandetoryPojo>() {
                    @Override
                    public void onResponse(Call<MakeMandetoryPojo> call, Response<MakeMandetoryPojo> response) {
                        if(response.body().getResponse().trim().equalsIgnoreCase("success")){
                            Toast.makeText(MakeMadetory.this, "saved", Toast.LENGTH_SHORT).show();

                        }                    }

                    @Override
                    public void onFailure(Call<MakeMandetoryPojo> call, Throwable t) {

                    }
                });





            }
        });

    }
}
