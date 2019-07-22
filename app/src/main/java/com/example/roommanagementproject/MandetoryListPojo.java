package com.example.roommanagementproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class MandetoryListPojo {

    @SerializedName("data")
    @Expose
    private List<MandetoryListObjectPojo> data = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<MandetoryListObjectPojo> getData() {
        return data;
    }

    public void setData(List<MandetoryListObjectPojo> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
