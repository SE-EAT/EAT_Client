package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLogin {
    @SerializedName("ID")
    @Expose
    public String ID;

    @SerializedName("password")
    @Expose
    public String password;

    public RequestLogin(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
