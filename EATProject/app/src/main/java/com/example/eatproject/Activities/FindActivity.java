package com.example.eatproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatproject.R;
import com.example.eatproject.models.ResponseMessage;
import com.example.eatproject.models.RoomListAdapter;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;
import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.Room;

import java.util.ArrayList;
import java.util.List;


public class FindActivity extends AppCompatActivity implements WebserviceResponseListner {

    List<Button> roomButtons = new ArrayList<Button>();
    ArrayList<Room> rooms = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_find);
        Intent indent = getIntent();

        new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                "getRooms").execute();

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
                new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                        "autoMatching").execute();
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
                // if(rooms.isEmpty()) return;
                new WebService(FindActivity.this, (WebserviceResponseListner)FindActivity.this,
                        "getRoomById", rooms.get(0).get_id()).execute();

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
                        "getRoomById", rooms.get(1).get_id()).execute();

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
                        "getRoomById", rooms.get(2).get_id()).execute();

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
                        "getRoomById", rooms.get(3).get_id()).execute();

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
            roomButton.setText(rooms.get(i).getUsers()[0].nickName + "\n" +
                    rooms.get(i).getRestaurant().name + "\n" +
                    rooms.get(i).getDate());
        }
    }


    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("getRooms")) {
            if (!flagToCheckFailure) {
                rooms = (ArrayList<Room>) response;
                updateRoomList(rooms);
                // RoomListAdapter adapter = new RoomListAdapter(this, R.layout.itemlayout, rooms);
                // ListView mListView = (ListView) findViewById(R.id.listview);
                // mListView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        } else if (webServiceName.equalsIgnoreCase("getRoomById")) {
            if (!flagToCheckFailure) {
                ResponseMessage msg = (ResponseMessage) response;
                Toast.makeText(this, msg.msg, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        } else if (webServiceName.equalsIgnoreCase("autoMatching")) {
            if (!flagToCheckFailure) {
                ArrayList<Room> rooms = (ArrayList<Room>) response;
                Intent intent = new Intent(getApplicationContext(), AutoMatchingActivity.class);
                intent.putExtra("rooms", rooms);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
