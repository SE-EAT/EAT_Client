package com.example.eatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("ID")
    @Expose
    String ID;
    @SerializedName("password")
    @Expose
    String pw;
    @SerializedName("password_confirm")
    @Expose
    String pw_confirm;
    @SerializedName("email")
    @Expose
    String mail;
    @SerializedName("nickname")
    @Expose
    String nickname;
    @SerializedName("studentID")
    @Expose
    int studentID = 2016;
    @SerializedName("sex")
    @Expose
    String sex;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("taste")
    @Expose
    int[] taste;

    public User(){}
    public User(String mail, String ID, String pw, String pw_confirm, String nickname, int studentID, String address, String sex) {
        this.mail = mail;
        this.ID = ID;
        this.pw = pw;
        this.pw_confirm = pw_confirm;
        this.nickname = nickname;
        this.studentID = studentID;
        this.address = address;
        this.sex = sex;
        this.taste = new int[]{5,4,3,0,0,0,0,1,2};
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setId(String ID) {
        this.ID = ID;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public void setPw_confirm(String pw_confirm) {
        this.pw_confirm = pw_confirm;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getMail() {
        return mail;
    }
    public String getId() {
        return ID;
    }
    public String getPw() {
        return pw;
    }
    public String getPw_confirm() {
        return pw_confirm;
    }
    public String getNickname() {
        return nickname;
    }
    public String getAddress() {
        return address;
    }
    public String getSex() {
        return sex;
    }
}
