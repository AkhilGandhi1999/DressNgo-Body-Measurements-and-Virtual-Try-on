package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {


    private Button btn1;
    private EditText ed1, ed2, ed3;
    private TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProfile();
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alreadyUser();
            }
        });
    }

    void createProfile(){
        Intent intent = new Intent(this,CreateProfile.class);
        startActivity(intent);
    }

    void alreadyUser(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    void init(){
        btn1 = (Button) findViewById(R.id.btnRegister);
        txt1 = (TextView) findViewById(R.id.txtNotReg);
        txt2 = (TextView) findViewById(R.id.txtAlreadyUser);
        ed1 = (EditText) findViewById(R.id.editTextEmail1);
        ed2 = (EditText) findViewById(R.id.editTextPassword1);
        ed3 = (EditText) findViewById(R.id.editTextConfirm);
    }
}