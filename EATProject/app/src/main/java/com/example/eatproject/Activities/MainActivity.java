package com.example.eatproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eatproject.R;
import com.example.eatproject.SearchInfo;

public class MainActivity extends AppCompatActivity {
    private Button btn_register;
    private Button btn_search;
    private View register_view;
    private EditText inpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        inpId = findViewById(R.id.loginId);
        inpId.setSelection(inpId.length());

        btn_register = findViewById(R.id.register_btn);
        btn_search = findViewById(R.id.search_btn);
        //회원가입버튼 클릭 시 회원가입 화면으로 이동
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        //아이디/PW 찾기 버튼 클릭시 이동
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchInfo.class);
                startActivity(intent);
            }
        });
    }
}