package com.example.eatproject;

public class User {
    String mail;
    String id;
    String pw;
    String nickname;
    String address;
    String sex;

    public User(String mail, String id, String pw, String nickname, String address, String sex) {
        this.mail = mail;
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.address = address;
        this.sex = sex;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
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
        return id;
    }

    public String getPw() {
        return pw;
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
