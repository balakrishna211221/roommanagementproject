package com.example.roommanagementproject;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

interface MakeMandetoryInterface {

    @POST("api.php")
    @Headers("Content-Type:application/json")
    Call<MakeMandetoryPojo> MAKE_MANDETORY_POJO_CALL(@Body JsonObject jsonObject);
}
