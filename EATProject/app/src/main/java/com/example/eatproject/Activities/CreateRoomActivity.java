package com.example.eatproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.eatproject.R;
import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.RequestCreateRoom;
import com.example.eatproject.models.ResponseMessage;
import com.example.eatproject.models.Restaurant;
import com.example.eatproject.models.UserInfo;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateRoomActivity extends AppCompatActivity implements WebserviceResponseListner {

    int selectState = 0;
    List<Restaurant> restaurants = new ArrayList<Restaurant>();
    List<String> list = new ArrayList<String>(Arrays.asList("한식", "중식", "양식", "일식", "분식", "치킨", "아시안", "고깃집", "술집"));
    ArrayAdapter adapter = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_create_room);
        Intent intent = getIntent();

        EditText hourText = findViewById(R.id.hourInput);
        EditText minuteText = findViewById(R.id.minuteText);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView = (ListView) findViewById(R.id.categoryListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(selectState == 0){
                    String hour = hourText.getText().toString();
                    String minute = minuteText.getText().toString();

                    if(Integer.parseInt(hour)<0 || Integer.parseInt(hour) >23){
                        Toast.makeText(getApplicationContext(),"시간을 정확히 입력해주세요. (0~23)",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(Integer.parseInt(minute)<0 || Integer.parseInt(minute)>59){
                        Toast.makeText(getApplicationContext(),"분을 정확히 입력해주세요. (0~59)",Toast.LENGTH_LONG).show();
                        return;
                    }

                    String text = (String) adapterView.getItemAtPosition(i);
                    String requestText = "";
                    switch (text) {
                        case "한식": requestText = "korea"; break;
                        case "중식": requestText = "china"; break;
                        case "일식": requestText = "japan"; break;
                        case "양식": requestText = "western"; break;
                        case "분식": requestText = "snack"; break;
                        case "치킨": requestText = "chicken"; break;
                        case "아시안": requestText = "asian"; break;
                        case "고깃집": requestText = "meat"; break;
                        case "술집": requestText = "drink"; break;
                        default: break;
                    }

                    RequestCategory appointment = new RequestCategory(requestText, hour, minute);
                    new WebService(CreateRoomActivity.this, (WebserviceResponseListner)CreateRoomActivity.this,
                            "createRoom", appointment).execute();
                }
                else if(selectState == 1){
                    String text = (String) adapterView.getItemAtPosition(i);
                    RequestCreateRoom requestCreateRoom = new RequestCreateRoom(WebService.userInfo, restaurants.get(i)._id);
                    new WebService(CreateRoomActivity.this, (WebserviceResponseListner)CreateRoomActivity.this,
                            "createRoom2", requestCreateRoom).execute();
                }
            }
        });
    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("createRoom")) {
            if (!flagToCheckFailure) {
                restaurants = (List<Restaurant>) response;
                selectState = 1;

                list.clear();
                for(Restaurant r : restaurants){
                    list.add(r.name);
                    adapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        } else if (webServiceName.equalsIgnoreCase("createRoom2")) {
            if (!flagToCheckFailure) {
                ResponseMessage msg = (ResponseMessage) response;
                Toast.makeText(this, msg.msg, Toast.LENGTH_LONG).show();

                // new WebService(CreateRoomActivity.this, (WebserviceResponseListner)CreateRoomActivity.this,
                //        "getRoomById").execute();

                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
