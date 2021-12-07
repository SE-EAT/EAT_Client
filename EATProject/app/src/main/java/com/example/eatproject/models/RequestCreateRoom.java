package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCreateRoom {
    @SerializedName("user")
    @Expose
    public UserInfo user;
    @SerializedName("restaurant")
    @Expose
    public String resID;

    public RequestCreateRoom(){}
    public RequestCreateRoom(UserInfo user, String resID) {
        this.user = user;
        this.resID = resID;
    }

    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }
    public String getResID() { return resID; }
    public void setResID(String resID) { this.resID = resID; }
}
