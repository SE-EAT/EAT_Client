package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("_id")
    @Expose
    public String _id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("address")
    @Expose
    public String address;

    public Restaurant(String _id, String name, String category, String address) {
        this._id = _id;
        this.name = name;
        this.category = category;
        this.address = address;
    }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
