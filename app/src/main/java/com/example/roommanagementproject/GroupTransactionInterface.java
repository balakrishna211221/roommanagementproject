package com.example.roommanagementproject;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

interface GroupTransactionInterface {
    @POST("api.php")
    @Headers("Content-Type:application/json")
    Call<GroupTransactionPojo> GROUP_TRANSACTION_POJO_CALL(@Body JsonObject jsonObject1);
}
