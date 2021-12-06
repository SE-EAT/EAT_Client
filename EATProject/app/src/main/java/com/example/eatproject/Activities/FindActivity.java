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

import java.util.ArrayList;
import java.util.List;


public class FindActivity extends AppCompatActivity implements WebserviceResponseListner {

    List<Button> roomButtons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_find);
        Intent indent = getIntent();

        try {
            new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                    "getRooms").execute();
        } catch (Exception e) {

        }

        // 현재 약속 화면 전환
        Button appointmentButton = (Button) findViewById(R.id.curAppointmentButton);
        appointmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        // Automating 화면 전환
        Button autoMatchingButton = (Button) findViewById(R.id.automatchingButton);
        autoMatchingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), AutoMatchingActivity.class);
                startActivity(intent);
            }
        });

        // Create Room 화면 전환
        Button createRoomButton = (Button) findViewById(R.id.createRoomButton);
        createRoomButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), CreateRoomActivity.class);
                startActivity(intent);
            }
        });

        // Room1 Join
        Button room1Button = (Button) findViewById(R.id.room1Button);
        room1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                        "getRoomById").execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        // Room2 Join
        Button room2Button = (Button) findViewById(R.id.room2Button);
        room2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                        "getRoomById").execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        // Room3 Join
        Button room3Button = (Button) findViewById(R.id.room3Button);
        room3Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                        "getRoomById").execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        // Room4 Join
        Button room4Button = (Button) findViewById(R.id.room4Button);
        room4Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                        "getRoomById").execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        roomButtons.add(room1Button);
        roomButtons.add(room2Button);
        roomButtons.add(room3Button);
        roomButtons.add(room4Button);

        init(appointmentButton, roomButtons);

    }


    private void init(Button appointmentButton, List<Button> roomButtons){
        appointmentButton.setEnabled(false);

        for(Button b : roomButtons){
            b.setVisibility(View.INVISIBLE);
        }
    }


    private void updateRoomList(List<Room> rooms){
        for(int i =0; i<rooms.size() && i<4; i++){
            Button roomButton = roomButtons.get(i);
            roomButton.setVisibility(View.VISIBLE);
            roomButton.setText(rooms.get(i).getUsers()[0]);
        }
    }


    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("getRooms")) {
            if (!flagToCheckFailure) {
                List<Room> rooms = (List<Room>) response;
                // Toast.makeText(this, rooms.get(0).toString(), Toast.LENGTH_LONG).show();
                updateRoomList(rooms);
                // tv_msg.setText(data.getMessage());
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
