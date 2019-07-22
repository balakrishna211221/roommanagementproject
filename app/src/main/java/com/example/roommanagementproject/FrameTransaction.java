package com.example.roommanagementproject;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roommanagementproject.databinding.TransactionsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

class FrameTransaction  extends Fragment {
//TransactionsBinding transactionsBinding;
    RecyclerView recyclerView;

Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
getActivity().setTitle("Personal Transactions");
        View view=inflater.inflate(R.layout.transactions,container,false);
        recyclerView=view.findViewById(R.id.rectan);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        String phoneloged=sharedPreferences.getString("mobile",null);

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_trans_as_personal");
            jsonObject.put("gu_id",phoneloged);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();
        //     Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();
        PTInterface ptInterface=retrofit.create(PTInterface.class);
        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
        // JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();
        Call<PTPojo1> ptPojoCall=ptInterface.PT_POJO_1_CALL(object);
         /*     Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

              PTInterface     ptInterface=retrofit.create(PTInterface.class);
              JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

              Call<PTPojo1>  ptPojoCall=ptInterface.PT_POJO_1_CALL(object);*/

        ptPojoCall.enqueue(new Callback<PTPojo1>() {
            @Override
            public void onResponse(Call<PTPojo1> call, Response<PTPojo1> response) {
                @SuppressLint("WrongConstant") LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new PTAdp(getActivity(),response.body().getData()));
            }

            @Override
            public void onFailure(Call<PTPojo1> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
