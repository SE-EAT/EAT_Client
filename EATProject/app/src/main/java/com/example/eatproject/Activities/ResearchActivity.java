package com.example.eatproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatproject.R;
import com.example.eatproject.models.RequestCategory;
import com.example.eatproject.models.RequestTaste;
import com.example.eatproject.models.ResponseMessage;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResearchActivity extends AppCompatActivity implements WebserviceResponseListner {

    public int preferCnt = 1;
    public List<String> list_category = new ArrayList<String>(Arrays.asList(
            new String[]{"한식", "중식", "일식", "양식", "분식", "치킨", "아시안", "고깃집", "술집"}));

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_research);
        Intent intent = getIntent();

        final String[] list = {"한식", "중식", "일식", "양식", "분식", "치킨", "아시안", "고깃집", "술집"};

        TextView mainText = findViewById(R.id.searchText);
        Button button = findViewById(R.id.OKButton);
        button.setActivated(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });

        mainText.setText(preferCnt + " 순위를 선택해 주세요.");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_category);
        ListView listView = (ListView) findViewById(R.id.researchListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                if(preferCnt > 5) return;
                mainText.setText(preferCnt + " 순위를 선택해 주세요.");
                String text = (String) adapterView.getItemAtPosition(index);
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                list_category.remove(index);
                adapter.notifyDataSetChanged();
                preferCnt++;

                int j = 0;
                for(j=0; j<list.length; j++){
                    if(text == list[j]) break;
                }

                RequestTaste requestTaste = new RequestTaste(WebService.userInfo, j);

                new WebService(ResearchActivity.this, (WebserviceResponseListner)ResearchActivity.this,
                        "postTaste", requestTaste).execute();
            }
        });


        if(preferCnt >5){
            mainText.setText("선호도 조사를 종료합니다.");
            button.setActivated(true);
        }


    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("postTaste")) {
            if (!flagToCheckFailure) {
                ResponseMessage msg = (ResponseMessage) response;
                Log.v("Test", msg.msg);
                // tv_msg.setText(data.getMessage());
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
