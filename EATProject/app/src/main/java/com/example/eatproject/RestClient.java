package com.example.eatproject;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestClient {
    private static ApiMethods REST_CLIENT;
    private static String ROOT = "http://10.0.2.2:8080/";


    static {
        setupRestClient();
    }

    private RestClient() {
    }

    public static ApiMethods get() {
        return REST_CLIENT;
    }
    public static ApiMethods post() { return REST_CLIENT; }

    private static void setupRestClient() {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(20 * 1000, TimeUnit.MILLISECONDS);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ROOT).setClient(new OkClient(okHttpClient)).setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        REST_CLIENT = restAdapter.create(ApiMethods.class);
    }

}
