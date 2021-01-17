package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView txt1;
    private Button btn1;
    private EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }


    void register(){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    void signIn(){
        Intent intent = new Intent(this,MainScreen.class);
        startActivity(intent);
    }
    void init(){
        txt1 = (TextView) findViewById(R.id.txtRegister);
        btn1 = (Button) findViewById(R.id.btnSignin);
        ed1 = (EditText) findViewById(R.id.editTextEmail);
        ed2 = (EditText) findViewById(R.id.editTextPassword);
    }
}