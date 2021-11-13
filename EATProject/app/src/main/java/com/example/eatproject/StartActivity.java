package com.example.eatproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button profileButton = (Button) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 새로운 창을 만들기 위한 Intent 객체
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);

                // 만든 Intent를 실행
                startActivity(intent);
            }
        });

        Button findButton = (Button) findViewById(R.id.findMealGroupButton);
        findButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });

        Button recoRestButton = (Button) findViewById(R.id.recommendRestButton);
        recoRestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RecoRestaurantActivity.class);
                startActivity(intent);
            }
        });

        Button feedbackButton = (Button) findViewById(R.id.feedbackButton);
        feedbackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }
}