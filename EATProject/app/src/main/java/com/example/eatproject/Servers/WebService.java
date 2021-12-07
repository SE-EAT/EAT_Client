package com.example.eatproject.servers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.RequestCreateRoom;
import com.example.eatproject.models.RequestJoinRoom;
import com.example.eatproject.models.RequestLogin;
import com.example.eatproject.models.RequestTaste;
import com.example.eatproject.models.ResponseMessage;
import com.example.eatproject.models.Restaurant;
import com.example.eatproject.models.Room;
import com.example.eatproject.models.User;
import com.example.eatproject.models.UserInfo;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;


public class WebService extends AsyncTask<String, Void, String> {

    Activity activity;
    WebserviceResponseListner listener = null;
    String METHOD_NAME = "";
    // LoggedInUser loggedInUser = null;
    public static UserInfo userInfo = null;
    public static User user = null;

    private RequestCategory category = new RequestCategory("", "", "");
    private RequestLogin requestLogin = new RequestLogin("", "");
    private RequestTaste requestTaste = new RequestTaste();
    private RequestCreateRoom requestCreateRoom = new RequestCreateRoom();
    private RequestJoinRoom requestJoinRoom = new RequestJoinRoom();
    private String roomId = "";

    public WebService(){}

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
    }

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME, RequestLogin requestLogin) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
        this.requestLogin = requestLogin;
    }

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME, User user) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
        this.user = user;
    }

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME, RequestCategory category) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
        this.category = category;
    }

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME, RequestTaste requestTaste) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
        this.requestTaste = requestTaste;
    }

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME, RequestCreateRoom requestCreateRoom) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
        this.requestCreateRoom = requestCreateRoom;
    }

    public WebService(Activity activity, WebserviceResponseListner listener, String METHOD_NAME, String roomId) {
        this.activity = activity;
        this.listener = listener;
        this.METHOD_NAME = METHOD_NAME;
        this.roomId = roomId;
    }

    @Override
    protected String doInBackground(String... strings) {
        switch(METHOD_NAME){
            case "userInfo":
                getUserInfo();
                break;
            case "postLogin":
                postLogin();
                break;
            case "postJoin":
                postJoin();
                break;
            case "getProfile":
                getProfile();
                break;
            case "postTaste":
                postTaste();
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
            case "createRoom2":
                createRoom2();
                break;
            default:
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

    private void postLogin() {
        RestClient.get().postLogin(requestLogin, new RestCallback<UserInfo>() {
            @Override
            public void success(UserInfo user, Response response) {
                userInfo = user;
                Log.e("Successful", user.userID + "/" + response.getUrl());
                listener.OnResponse(user, false, "postLogin");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "postLogin");
            }
        });
    }

    private void postJoin() {
        RestClient.get().postJoin(user, new RestCallback<ResponseMessage>() {
            @Override
            public void success(ResponseMessage msg, Response response) {

                Log.e("Successful", msg.toString() + "/" + response.getUrl());
                listener.OnResponse(msg, false, "postJoin");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "postJoin");
            }
        });
    }

    private void postTaste() {
        RestClient.get().postTaste(requestTaste, new RestCallback<ResponseMessage>() {
            @Override
            public void success(ResponseMessage msg, Response response) {
                Log.e("Successful", msg.toString() + "/" + response.getUrl());
                listener.OnResponse(msg, false, "postTaste");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "postTaste");
            }
        });
    }

    private void getProfile(){
        RestClient.get().getProfile(userInfo, new RestCallback<List<String>>() {
            @Override
            public void success(List<String> msg, Response response) {
                Log.e("Successful", msg.get(0).toString() + "/" + response.getUrl());
                listener.OnResponse(msg, false, "getProfile");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "getProfile");
            }
        });
    }

    private void getRooms(){
        RestClient.get().getRooms(new RestCallback<List<Room>>() {
            @Override
            public void success(List<Room> rooms, Response response) {
                Log.e("Successful", rooms.get(0).getRestaurant().name + "/" + response.getUrl());
                listener.OnResponse(rooms, false, "getRooms");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "getRooms");
            }
        });
    }

    private void createRoom() {
        RestClient.get().postCreateRoom(category, new RestCallback<List<Restaurant>>() {
            @Override
            public void success(List<Restaurant> restaurants, Response response) {
                // Log.e("Successful",  restaurants.get(0).name + "/" + response.getUrl());
                listener.OnResponse(restaurants, false, "createRoom");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "createRoom");
            }
        });
    }

    RequestJoinRoom tmpJoinRoom = new RequestJoinRoom(" ", " ");
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

    private void createRoom2() {
        RestClient.get().postCreateRoom2(requestCreateRoom, new RestCallback<ResponseMessage>() {
            @Override
            public void success(ResponseMessage msg, Response response) {
                Log.e("Successful",  msg.msg + "/" + response.getUrl());
                listener.OnResponse(msg, false, "createRoom2");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Url", "error: " + error.toString() + "/" + error.getUrl());
                listener.OnResponse(new Object(), true, "createRoom2");
            }
        });
    }
}
