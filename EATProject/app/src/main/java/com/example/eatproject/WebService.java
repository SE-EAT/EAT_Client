package com.example.eatproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.eatproject.models.Room;
import com.example.eatproject.models.UserInfo;
import com.google.gson.Gson;

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
            case "postUserInfo":
                postUserInfo();
                break;
            case "getRooms":
                getRooms();
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

    private void postUserInfo() {
        RestClient.post().getUserInfo(new RestCallback<UserInfo>() {

            @Override
            public void success(UserInfo userInfo, Response response) {
                Log.e("Successful", userInfo.email + "/" + response.getUrl());
                listener.OnResponse(userInfo, false, "postUserInfo");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "postUserInfo");
            }
        });
    }

    private void getRooms(){
        RestClient.get().getRooms(new RestCallback<Room>() {
            @Override
            public void success(Room room, Response response) {
                Log.e("Successful", room.toString() + "/" + response.getUrl());
                listener.OnResponse(room, false, "getRooms");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "getRooms");
            }
        });
    }
}
