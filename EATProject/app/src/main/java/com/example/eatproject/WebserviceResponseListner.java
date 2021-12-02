package com.example.eatproject;

public interface WebserviceResponseListner {
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName);
}
