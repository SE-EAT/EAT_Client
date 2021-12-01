package com.example.eatproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class FindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_find);
        Intent indent = getIntent();

        // Automating 화면 전환
        Button autoMatchingButton = (Button) findViewById(R.id.autoMatchingButton);
        autoMatchingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), AutoMatchingActivity.class);
                startActivity(intent);
            }
        });

        Button roomButton = (Button) findViewById(R.id.room1Button);
        roomButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });
    }
}
