package com.example.roommanagementproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class GroupListPojo {

    @SerializedName("data")
    @Expose
    private List<GroupTListPojo2> data = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<GroupTListPojo2> getData() {
        return data;
    }

    public void setData(List<GroupTListPojo2> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
