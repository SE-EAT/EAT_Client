package com.example.eatproject.models;


import com.example.eatproject.models.Date;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("users")
    @Expose
    private UserInfo[] users;
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("roomState")
    @Expose
    private int roomState;

    public Room(String _id, UserInfo[] users, Restaurant restaurant, String date, int roomState) {
        this._id = _id;
        this.users = users;
        this.restaurant = restaurant;
        this.date = date;
        this.roomState = roomState;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public UserInfo[] getUsers() {
        return users;
    }
    public void setUsers(UserInfo[] users) {
        this.users = users;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getRoomState() {
        return roomState;
    }
    public void setRoomState(int roomState) {
        this.roomState = roomState;
    }
}
