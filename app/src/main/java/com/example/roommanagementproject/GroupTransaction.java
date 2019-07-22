package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.roommanagementproject.databinding.ActivityGroupTransactionBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GroupTransaction extends AppCompatActivity {
ActivityGroupTransactionBinding activityGroupTransactionBinding;
String reason,amount,date,phone,gid,card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       activityGroupTransactionBinding= DataBindingUtil.setContentView(GroupTransaction.this,R.layout.activity_group_transaction);
        Intent intent=getIntent();
        final String adminum =intent.getStringExtra("groupadmin");
        final String groupname=intent.getStringExtra("groupname");
        gid=intent.getStringExtra("gid");

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String num = preferences.getString("mobile", null);
        String pass = preferences.getString("pass", null);
        phone=num;




        reason=activityGroupTransactionBinding.groreason.getText().toString().trim();
        amount=activityGroupTransactionBinding.amountg.getText().toString().trim();


        date=activityGroupTransactionBinding.dateg.getText().toString();
        activityGroupTransactionBinding.radiogroup.setOnCheckedChangeListener
                (new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged
                            (RadioGroup group, int checkedId) {
                        if(checkedId==R.id.creditcard){
                            card="C";
                        }else if(checkedId==R.id.debitcard){
                            card="D";
                        }

                    }
                });

        activityGroupTransactionBinding.buttong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject=new JSONObject();
                try {

                    jsonObject.put("action","add_trans_as_personal");
                    jsonObject.put("mobile_no",phone);
                    jsonObject.put("ttype","S");
                    jsonObject.put("gu_id",gid);
                    jsonObject.put("reason",activityGroupTransactionBinding.groreason.getText().toString().trim());
                    jsonObject.put("bdate",activityGroupTransactionBinding.dateg.getText().toString());
                    jsonObject.put("cr_or_dr",card);
                    jsonObject.put("amount",activityGroupTransactionBinding.amountg.getText().toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

                GroupTransactionInterface GTInterface=retrofit.create(GroupTransactionInterface.class);
                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<GroupTransactionPojo> groupTransactionPojoCall=GTInterface.GROUP_TRANSACTION_POJO_CALL(object);

              groupTransactionPojoCall.enqueue(new Callback<GroupTransactionPojo>() {
                  @Override
                  public void onResponse(Call<GroupTransactionPojo> call, Response<GroupTransactionPojo> response) {
                      Toast.makeText(GroupTransaction.this, "transaction added to group", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onFailure(Call<GroupTransactionPojo> call, Throwable t) {

                  }
              });

            }

        });
    }
}
