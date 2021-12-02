package com.example.eatproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatproject.models.Room;
import com.example.eatproject.models.UserInfo;


public class FindActivity extends AppCompatActivity implements WebserviceResponseListner {

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

        // new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
        //        "getRooms").execute();
    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("getRooms")) {
            if (!flagToCheckFailure) {
                Room data = (Room) response;
                Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
                // tv_msg.setText(data.getMessage());
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
