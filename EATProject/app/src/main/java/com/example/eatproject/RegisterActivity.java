package com.example.eatproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//회원가입 Activity
public class RegisterActivity extends AppCompatActivity {
    private Button btn_request_register;
    private EditText etxt_mail, etxt_id, etxt_pw, etxt_nickname, etxt_address;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int selectedID;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_request_register= findViewById(R.id.buttonRequestRegister);
        etxt_mail = findViewById(R.id.editTextEmailAddress);
        etxt_id = findViewById(R.id.editTextRegisterID);
        etxt_pw = findViewById(R.id.editTextRegisterPW);
        etxt_nickname = findViewById(R.id.editTextRegisterNickname);
        etxt_address = findViewById(R.id.editTextRegisterAddress);
        rg = (RadioGroup) findViewById(R.id.radioGroup);

        btn_request_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = etxt_mail.getText().toString();
                String id = etxt_id.getText().toString();
                String pw = etxt_pw.getText().toString();
                String nickname = etxt_nickname.getText().toString();
                String address = etxt_nickname.getText().toString();
                RadioButton rd = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                String str_Qtype = rd.getText().toString();
                User user = new User(mail, id, pw, nickname, address, str_Qtype);
                Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}