package com.example.roommanagementproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class GroupTListPojo2 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("ttype")
    @Expose
    private String ttype;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("cr_or_dr")
    @Expose
    private String crOrDr;
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCrOrDr() {
        return crOrDr;
    }

    public void setCrOrDr(String crOrDr) {
        this.crOrDr = crOrDr;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
