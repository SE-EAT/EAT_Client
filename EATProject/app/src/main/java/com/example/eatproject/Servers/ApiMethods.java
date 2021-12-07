package com.example.eatproject.servers;

import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.RequestCreateRoom;
import com.example.eatproject.models.RequestLogin;
import com.example.eatproject.models.RequestTaste;
import com.example.eatproject.models.ResponseMessage;
import com.example.eatproject.models.Restaurant;
import com.example.eatproject.models.Room;
import com.example.eatproject.models.User;
import com.example.eatproject.models.UserInfo;

import java.util.List;

import okhttp3.Response;
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

    // 방을 만들 때, 식당을 선택했을 때 호출됨.
    // 새로운 방을 만듦.
    @POST("/matching/rooms/select")
    void postCreateRoom2(@Body RequestCreateRoom requestCreateRoom, RestCallback<ResponseMessage> restCallbacks);

    // 원하는 방 클릭했을 때 호출.
    // 해당 방으로 입장하기
    @GET("/matching/rooms/{room_id}")
    void getRoomById(@Path(value = "room_id", encode = true) String room_id, RestCallback<ResponseMessage> restCallbacks);

    // 로그인 요청
    @POST("/")
    void postLogin(@Body RequestLogin requestLogin,
                   RestCallback<UserInfo> restCallback);

    // 회원가입 요청
    @POST("/join")
    void postJoin(@Body User user, RestCallback<ResponseMessage> restCallback);

    // 로그인 되어있는 사용자의 프로필 정보를 가져옴.
    @POST("/users")
    void getProfile(@Body UserInfo userInfo,
                    RestCallback<List<String>> restCallback);

    @POST("/users/taste")
    void postTaste(@Body RequestTaste requestTaste, RestCallback<ResponseMessage> restCallback);


}
