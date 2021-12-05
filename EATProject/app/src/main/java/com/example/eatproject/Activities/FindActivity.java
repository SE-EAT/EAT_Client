package com.example.eatproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatproject.R;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;
import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.Room;

import java.util.List;


public class FindActivity extends AppCompatActivity implements WebserviceResponseListner {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_find);
        Intent indent = getIntent();

        try {
            // RequestCategory requestCategory = new RequestCategory("한식");
            new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                    "createRoom").execute();

        } catch (Exception e) {

        }

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
                new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                        "getRoomById").execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this, "getRooms").execute();
    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("getRooms")) {
            if (!flagToCheckFailure) {
                List<Room> rooms = (List<Room>) response;
                Toast.makeText(this, rooms.get(0).toString(), Toast.LENGTH_LONG).show();
                // tv_msg.setText(data.getMessage());
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        } else if (webServiceName.equalsIgnoreCase("createRoom")) {
            if (!flagToCheckFailure) {
                RequestCategory data = (RequestCategory) response;
                Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
                // tv_msg.setText(data.getMessage());
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
