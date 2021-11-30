package com.example.eatproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Room extends RealmObject {
    @PrimaryKey private int roomID;
    private int restaurantID;
    private int[] users;
    private Date date;
    private int roomState;

    Room(){}
    public Room(int roomID, int restaurantID, int[] users, Date date, int roomState) {
        this.roomID = roomID;
        this.restaurantID = restaurantID;
        this.users = users;
        this.date = date;
        this.roomState = roomState;
    }

    public int getRoomID() {
        return roomID;
    }
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    public int getRestaurantID() {
        return restaurantID;
    }
    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }
    public int[] getUsers() {
        return users;
    }
    public void setUsers(int[] users) {
        this.users = users;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getRoomState() {
        return roomState;
    }
    public void setRoomState(int roomState) {
        this.roomState = roomState;
    }
}
