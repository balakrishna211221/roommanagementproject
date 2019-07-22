package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roommanagementproject.databinding.ActivityMakeTransactionBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakeTransaction extends AppCompatActivity {
    ActivityMakeTransactionBinding transactionBinding;
    String phone,gid,reason,amount,date,card;
    RadioGroup rg;
    RadioButton radioButton,radioButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MakeTransaction.this.setTitle("Make Personal Transaction");

        transactionBinding= DataBindingUtil.setContentView(MakeTransaction.this,R.layout.activity_make_transaction);
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String num = preferences.getString("mobile", null);
        String pass = preferences.getString("pass", null);
        phone=num;


        gid=num;

        reason=transactionBinding.reason1.getText().toString().trim();
        amount=transactionBinding.amount.getText().toString();
        date=transactionBinding.date.getText().toString();
        transactionBinding.radiogroup.setOnCheckedChangeListener
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

        transactionBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject=new JSONObject();
                try {

                    jsonObject.put("action","add_trans_as_personal");
                    jsonObject.put("mobile_no",phone);
                    jsonObject.put("ttype","S");
                    jsonObject.put("gu_id",gid);
                    jsonObject.put("reason",transactionBinding.reason1.getText().toString().trim());
                    jsonObject.put("bdate",transactionBinding.date.getText().toString());
                    jsonObject.put("cr_or_dr",card);
                    jsonObject.put("amount",transactionBinding.amount.getText().toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

                MakeTransactionInterface ptInterface=retrofit.create(MakeTransactionInterface.class);
                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<PTPojo>  ptPojoCall=ptInterface.PT_POJO_CALL(object);

                ptPojoCall.enqueue(new Callback<PTPojo>() {
                    @Override
                    public void onResponse(Call<PTPojo> call, Response<PTPojo> response) {
                        if(response.body().getResponse().trim().equalsIgnoreCase("success"))
                            Toast.makeText(MakeTransaction.this, "succes", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PTPojo> call, Throwable t) {

                    }
                });


            }

        });

    }
}