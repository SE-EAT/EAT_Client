package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMessage {
    @SerializedName("msg")
    @Expose
    public String msg;

    public ResponseMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
}
