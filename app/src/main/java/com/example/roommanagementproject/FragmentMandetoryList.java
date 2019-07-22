package com.example.roommanagementproject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

class FragmentMandetoryList  extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
getActivity().setTitle("Mandetory Payments");
        View view=inflater.inflate(R.layout.mandetorylist,container,false);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        String phoneloged=sharedPreferences.getString("mobile",null);

        recyclerView=view.findViewById(R.id.rec);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_savings");
            jsonObject.put("stype","S");
            jsonObject.put("user_id",phoneloged);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

        MandetoryInterface mandetoryInterface=retrofit.create(MandetoryInterface.class);
        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

        Call<MandetoryListPojo> mandetoryListPojoCall=mandetoryInterface.MANDETORY_POJO_CALL(object);
        mandetoryListPojoCall.enqueue(new Callback<MandetoryListPojo>() {
            @Override
            public void onResponse(Call<MandetoryListPojo> call, Response<MandetoryListPojo> response) {
                @SuppressLint("WrongConstant") LinearLayoutManager layoutManager=new
                        LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
               recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new MandetoryAdp(getActivity(),
                        response.body().getData()));


            }

            @Override
            public void onFailure(Call<MandetoryListPojo> call, Throwable t) {

            }
        });


        return view;

    }
}
