package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCategory {

    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("hour")
    @Expose
    public String hour;
    @SerializedName("minute")
    @Expose
    public String minute;

    public RequestCategory(String category, String hour, String minute) {
        this.category = category;
        this.hour = hour;
        this.minute = minute;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getHour() { return hour; }
    public void setHour(String hour) { this.hour = hour; }
    public String getMinute() { return minute; }
    public void setMinute(String minute) { this.minute = minute; }
}
