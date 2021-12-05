package com.example.eatproject.servers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.RequestJoinRoom;
import com.example.eatproject.models.ResponseMessage;
import com.example.eatproject.models.Restaurant;
import com.example.eatproject.models.Room;
import com.example.eatproject.models.UserInfo;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class WebService extends AsyncTask<String, Void, String> {

    Activity activity;
    WebserviceResponseListner listener = null;
    String METHOD_NAME = "";

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
    }

    @Override
    protected String doInBackground(String... strings) {
        switch(METHOD_NAME){
            case "userInfo":
                getUserInfo();
                break;
            case "getRooms":
                getRooms();
                break;
            case "createRoom":
                createRoom();
                break;
            case "getRoomById":
                getRoomById();
                break;
        }
        return null;
    }

    private void getUserInfo() {
        RestClient.get().getUserInfo(new RestCallback<UserInfo>() {

            @Override
            public void success(UserInfo userInfo, Response response) {
                Log.e("Successful", userInfo.nickName + "/" + response.getUrl());
                listener.OnResponse(userInfo, false, "userInfo");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "userInfo");
            }
        });
    }

    private void getRooms(){
        RestClient.get().getRooms(new RestCallback<List<Room>>() {
            @Override
            public void success(List<Room> room, Response response) {
                Log.e("Successful", room.get(0).toString() + "/" + response.getUrl());
                listener.OnResponse(room, false, "getRooms");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "getRooms");
            }
        });
    }

    private RequestCategory tmpCategory = new RequestCategory("한식");
    private void createRoom() {
        RestClient.get().postCreateRoom(tmpCategory, new RestCallback<List<Restaurant>>() {

            @Override
            public void success(List<Restaurant> restaurants, Response response) {
                Log.e("Successful", restaurants.toString() + "/" + response.getUrl());
                listener.OnResponse(restaurants, false, "createRoom");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "createRoom");
            }
        });
    }


    private RequestJoinRoom tmpJoinRoom = new RequestJoinRoom("tmp", "tmp2");
    private void getRoomById() {
        RestClient.get().getRoomById(tmpJoinRoom.id, new RestCallback<ResponseMessage>() {
            @Override
            public void success(ResponseMessage responseMessage, Response response) {
                Log.e("Successful", responseMessage + "/" + response.getUrl());
                listener.OnResponse(responseMessage, false, "getRoomById");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "getRoomById");
            }
        });
    }
}
