package com.example.eatproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatproject.R;
import com.example.eatproject.models.RequestLogin;
import com.example.eatproject.models.UserInfo;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;

public class MainActivity extends AppCompatActivity implements WebserviceResponseListner {
    private Button btn_register;
    private Button btn_login;
    private EditText txt_id;
    private EditText txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.buttonLogin);
        btn_register = findViewById(R.id.buttonRegister);

        txt_id = (EditText)findViewById(R.id.editTextPersonName);
        txt_password = (EditText)findViewById(R.id.editTextPassword);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString();
                String pw = txt_password.getText().toString();
                RequestLogin requestLogin = new RequestLogin(id, pw);
                new WebService(MainActivity.this, (WebserviceResponseListner)MainActivity.this,
                        "postLogin", requestLogin).execute();
            }
        });

        //회원가입버튼 클릭 시 회원가입 화면으로 이동
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void OnResponse(Object response, boolean flagToCheckFailure, String webServiceName) {
        if (webServiceName.equalsIgnoreCase("postLogin")) {
            if (!flagToCheckFailure) {
                UserInfo user = (UserInfo) response;

                if(user != null){
                    int sum = 0;
                    for(int i=0; i<user.taste.length; i++){
                        sum += user.taste[i];
                    }
                    if(sum == 0){   // 선호도 조사를 안했다면 조사 화면으로.
                        Intent intent = new Intent(getApplicationContext(), ResearchActivity.class);
                        startActivity(intent);
                    }
                    else{   // 이미 선호도 조사를 마쳤다면 Start 화면으로.
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        startActivity(intent);
                    }
                }
            } else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}