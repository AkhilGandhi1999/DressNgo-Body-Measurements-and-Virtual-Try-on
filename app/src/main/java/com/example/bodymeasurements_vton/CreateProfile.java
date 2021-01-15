package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateProfile extends AppCompatActivity {


    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        btn1 = (Button) findViewById(R.id.btnUser);

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
    }
}