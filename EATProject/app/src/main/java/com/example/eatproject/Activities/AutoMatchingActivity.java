package com.example.eatproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatproject.R;
import com.example.eatproject.models.Restaurant;
import com.example.eatproject.models.Room;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;

import java.util.ArrayList;
import java.util.List;

public class AutoMatchingActivity extends AppCompatActivity implements WebserviceResponseListner {

    List<Button> roomButtons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_automatching);
        Intent intent = getIntent();

        ArrayList<Room> rooms = (ArrayList<Room>) intent.getSerializableExtra("rooms");

        Button room1Button = (Button) findViewById(R.id.reco_room1button);
        room1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new WebService(AutoMatchingActivity.this, (WebserviceResponseListner)AutoMatchingActivity.this,
                        "getRoomById", rooms.get(0).get_id()).execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        Button room2Button = (Button) findViewById(R.id.reco_room2button);
        room1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new WebService(AutoMatchingActivity.this, (WebserviceResponseListner)AutoMatchingActivity.this,
                        "getRoomById", rooms.get(1).get_id()).execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        Button room3Button = (Button) findViewById(R.id.reco_room3button);
        room1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new WebService(AutoMatchingActivity.this, (WebserviceResponseListner)AutoMatchingActivity.this,
                        "getRoomById", rooms.get(2).get_id()).execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        roomButtons.add(room1Button);
        roomButtons.add(room2Button);
        roomButtons.add(room3Button);

        init(roomButtons);
        updateRoomList(rooms);
    }

    private void init(List<Button> roomButtons){
        for(Button b : roomButtons){
            b.setVisibility(View.INVISIBLE);
        }
    }

    private void updateRoomList(List<Room> rooms){
        for(int i =0; i<rooms.size() && i<3; i++){
            Button roomButton = roomButtons.get(i);
            roomButton.setVisibility(View.VISIBLE);
            roomButton.setText(rooms.get(i).getUsers()[0].nickName + "\n" +
                    rooms.get(i).getRestaurant().name + "\n" +
                    rooms.get(i).getDate());
        }
    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("autoMatching")) {
            if (!flagToCheckFailure) {
                // success !
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
