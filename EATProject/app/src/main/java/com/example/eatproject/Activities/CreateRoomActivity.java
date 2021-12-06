package com.example.eatproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatproject.R;
import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.Restaurant;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;

import java.util.List;

public class CreateRoomActivity extends AppCompatActivity implements WebserviceResponseListner {

    List<Restaurant> restaurants = null;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_create_room);
        Intent intent = getIntent();

        String[] LIST_CATEGORY = {"한식", "중식", "양식", "일식", "분식", "치킨", "아시안", "고깃집", "술집"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_CATEGORY);

        ListView listView = (ListView) findViewById(R.id.categoryListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = (String) adapterView.getItemAtPosition(i);
                String requestText = "";
                switch (text) {
                    case "한식": requestText = "korea"; break;
                    case "중식": requestText = "china"; break;
                    case "일식": requestText = "japan"; break;
                    case "양식": requestText = "western"; break;
                    case "분식": requestText = "snack"; break;
                    case "치킨": requestText = "chicken"; break;
                    case "아시인": requestText = "asian"; break;
                    case "고깃집": requestText = "meat"; break;
                    case "술집": requestText = "drink"; break;
                    default: break;
                }
                RequestCategory category = new RequestCategory(requestText);
                new WebService(CreateRoomActivity.this, (WebserviceResponseListner)CreateRoomActivity.this,
                        "createRoom", category).execute();
            }
        });
    }


    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("createRoom")) {
            if (!flagToCheckFailure) {
                restaurants = (List<Restaurant>) response;
                Toast.makeText(this, restaurants.get(0).name, Toast.LENGTH_LONG).show();
                Toast.makeText(this, restaurants.get(1).name, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
