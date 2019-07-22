package com.example.roommanagementproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class PTPojo1 {

    @SerializedName("data")
    @Expose
    private List<PTPojo2> data = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<PTPojo2> getData() {
        return data;
    }

    public void setData(List<PTPojo2> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
