package com.example.eatproject.servers;

public interface WebserviceResponseListner {
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName);
}
