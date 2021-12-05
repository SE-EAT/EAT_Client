package com.example.eatproject.servers;

import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.RequestJoinRoom;
import com.example.eatproject.models.ResponseMessage;
import com.example.eatproject.models.Restaurant;
import com.example.eatproject.models.Room;
import com.example.eatproject.models.UserInfo;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiMethods {
    // Test
    // TODO : 삭제할 것임
    @GET("/home")
    void getUserInfo(RestCallback<UserInfo> restCallback);



    // Category를 선택했을 때
    // 해당하는 식당들을 가져옴.
    @POST("/matching/rooms")
    void postCreateRoom(@Body RequestCategory requestCategory, RestCallback<List<Restaurant>> restCallbacks);

    // @GET("/matching/rooms")
    // void getCreateRoom(RestCallback<Room> restCallback);

    // findActivity로 넘어왔을 때, 모든 방을 가져옴.
    @GET("/matching")
    void getRooms(RestCallback<List<Room>> restCallback);

    @GET("/matching/rooms/{room_id}")
    void getRoomById(@Path(value = "room_id", encode = true) String room_id, RestCallback<ResponseMessage> restCallbacks);


}
