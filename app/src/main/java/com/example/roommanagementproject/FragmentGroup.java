package com.example.roommanagementproject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

class FragmentGroup  extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.grouplist,container,false);
        getActivity().setTitle("Groups");
recyclerView=view.findViewById(R.id.groupslist);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        String phoneloged=sharedPreferences.getString("mobile",null);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_groups");
            jsonObject.put("member",phoneloged);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://androindian.com/apps/fm/").addConverterFactory(GsonConverterFactory.create()).build();

                GroupsListInterface groupsListInterface=retrofit.create(GroupsListInterface.class);

        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();


        Call<GroupslistPojo> groupslistPojoCall=groupsListInterface.GROUPSLIST_POJO_CALL(object);

        groupslistPojoCall.enqueue(new Callback<GroupslistPojo>() {
            @Override
            public void onResponse(Call<GroupslistPojo> call, Response<GroupslistPojo> response) {
                @SuppressLint("WrongConstant") LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new GroupsAdt(getActivity(),response.body().getData()));
            }

            @Override
            public void onFailure(Call<GroupslistPojo> call, Throwable t) {

            }
        });




        return view;
    }
}
