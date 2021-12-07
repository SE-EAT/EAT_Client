package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("_id")
    @Expose
    public String _id;
    @SerializedName("ID")
    @Expose
    public String userID;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("nickname")
    @Expose
    public String nickName;
    @SerializedName("studentID")
    @Expose
    public int studentID;
    @SerializedName("sex")
    @Expose
    public String sex;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("taste")
    @Expose
    public int[] taste;
    @SerializedName("rating")
    @Expose
    public int score;
    // @SerializedName("history")
    // @Expose
    // public Room[] history;
    // @SerializedName("name")
    // @Expose
    // public int[] favorites;
    // @SerializedName("name")
    // @Expose
    // public int userState;

    public UserInfo(){ }
    public UserInfo(String _id, String userID, String password, String email, String nickName, int studentID, String sex, String address, int[] taste, int score) {
        this._id = _id;
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.studentID = studentID;
        this.sex = sex;
        this.address = address;
        // this.taste = taste;
        this.taste = taste;
        this.score = score;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int[] getTaste() { return taste; }
    public void setTaste(int[] taste) { this.taste = taste; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
