package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateProfile extends AppCompatActivity {


    private Button btn1;
    private EditText ed1, ed2, ed3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User();
            }
        });

    }

    void User(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();
    }

    void init(){
        btn1 = (Button) findViewById(R.id.btnCreateUser);
        ed1 = (EditText) findViewById(R.id.editTextName);
        ed2 = (EditText) findViewById(R.id.editTextHeight);
        ed3 = (EditText) findViewById(R.id.editTextWeight);
    }
}