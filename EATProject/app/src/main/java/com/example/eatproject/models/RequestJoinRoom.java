package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.CookieManager;

public class RequestJoinRoom {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("_id")
    @Expose
    public String _id;

    public RequestJoinRoom() {}
    public RequestJoinRoom(String id, String _id) {
        this.id = id;
        this._id = _id;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }
}
