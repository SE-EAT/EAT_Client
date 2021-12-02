package com.example.eatproject;

import com.example.eatproject.models.Room;
import com.example.eatproject.models.UserInfo;

import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiMethods {
    @GET("/home")
    void getUserInfo(RestCallback<UserInfo> restCallback);

    @POST("/home")
    void postUserInfo(RestCallback<UserInfo> restCallback);

    @GET("/matching")
    void getRooms(RestCallback<Room> restCallback);
}
