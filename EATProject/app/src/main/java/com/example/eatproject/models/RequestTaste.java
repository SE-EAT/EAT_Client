package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestTaste {
    @SerializedName("user")
    @Expose
    public UserInfo user;

    @SerializedName("taste")
    @Expose
    public int taste;

    public RequestTaste(){}
    public RequestTaste(UserInfo user, int taste) {
        this.user = user;
        this.taste = taste;
    }

    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }
    public int getTaste() { return taste; }
    public void setTaste(int taste) { this.taste = taste; }
}
