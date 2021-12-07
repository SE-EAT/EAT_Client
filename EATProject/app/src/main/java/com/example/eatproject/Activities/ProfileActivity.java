package com.example.eatproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatproject.R;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements WebserviceResponseListner {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        List<String> preferList = new ArrayList<String>();

        for(int i=0; i<5; i++) {
            preferList.add(intent.getStringExtra("list" + i));
        }

        TextView nicknameText = (TextView) findViewById(R.id.nicknameText);
        TextView studentIDText = (TextView) findViewById(R.id.studentIDText2);
        TextView scoreText = (TextView) findViewById(R.id.scoreText);
        TextView sexText = (TextView) findViewById(R.id.sexText);
        TextView emailText = (TextView) findViewById(R.id.emailText);
        TextView addressText = (TextView) findViewById(R.id.addressText);

        nicknameText.setText("닉네임: " + WebService.userInfo.nickName);
        //studentIDText.setText("학번: " + WebService.userInfo.studentID);
        studentIDText.setText("학번: 2016");
        //studentIDText.setText("평점: " + WebService.userInfo.score);
        scoreText.setText("평점: 5"); // 임의로.
        sexText.setText("성별: " + WebService.userInfo.sex);
        emailText.setText("Email: " + WebService.userInfo.email);
        addressText.setText("주소: " + WebService.userInfo.address);

        TextView prefer1Text = (TextView) findViewById(R.id.prefer1Text);
        TextView prefer2Text = (TextView) findViewById(R.id.prefer2Text);
        TextView prefer3Text = (TextView) findViewById(R.id.prefer3Text);
        TextView prefer4Text = (TextView) findViewById(R.id.prefer4Text);
        TextView prefer5Text = (TextView) findViewById(R.id.prefer5Text);

        prefer1Text.setText("1. " + preferList.get(0));
        prefer2Text.setText("2. " + preferList.get(1));
        prefer3Text.setText("3. " + preferList.get(2));
        prefer4Text.setText("4. " + preferList.get(3));
        prefer5Text.setText("5. " + preferList.get(4));
    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("getProfile")) {
            if (!flagToCheckFailure) {
                List<String> preferList = (List<String>) response;
                for(String s : preferList){
                   Log.e("Text", s);
                }
                // Toast.makeText(this, preferList.toString(), Toast.LENGTH_LONG).show();

                // Log.v("Test", msg.get(0));
                // tv_msg.setText(data.getMessage());
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}
