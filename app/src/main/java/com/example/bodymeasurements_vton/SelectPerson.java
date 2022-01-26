package com.example.bodymeasurements_vton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;



public class SelectPerson extends AppCompatActivity {
    String personimage = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_person);

        String cloth = getIntent().getStringExtra("cloth_image_id");
        CardView first = (CardView) findViewById(R.id.first);
        CardView second = (CardView) findViewById(R.id.second);
        CardView third = (CardView) findViewById(R.id.third);
        CardView fourth = (CardView) findViewById(R.id.fourth);
        CardView fifth = (CardView) findViewById(R.id.fifth);
        CardView sixth = (CardView) findViewById(R.id.sixth);
        ImageView imgfirst = (ImageView) findViewById(R.id.imgfirst);
        ImageView imgsecond = (ImageView) findViewById(R.id.imgsecond);
        ImageView imgthird = (ImageView) findViewById(R.id.imgthird);
        ImageView imgfourth = (ImageView) findViewById(R.id.imgfourth);
        ImageView imgfifth = (ImageView) findViewById(R.id.imgfifth);
        ImageView imgsixth = (ImageView) findViewById(R.id.imgsixth);

        Button confirm=(Button)findViewById(R.id.confirm1);
        Button confirm1 = (Button)findViewById(R.id.confirm2);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(personimage.length()>0)
                    getResult(cloth);

            } });

        confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent results = new Intent(SelectPerson.this,DynamicVTON.class);
                results.putExtra("cloth_image_id1",cloth);
                startActivity(results);
                finish();
            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                personimage = "im1.jpeg";
                imgsecond.setPadding(0, 0, 0, 0);
                imgthird.setPadding(0, 0, 0, 0);
                imgfourth.setPadding(0, 0, 0, 0);
                imgfifth.setPadding(0, 0, 0, 0);
                imgsixth.setPadding(0, 0, 0, 0);
                imgfirst.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgfirst.getResources(), R.drawable.title_logo, null);
                imgfirst.setBackground(highlight);

            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personimage = "im2.jpeg";
                imgfirst.setPadding(0, 0, 0, 0);
                imgthird.setPadding(0, 0, 0, 0);
                imgfourth.setPadding(0, 0, 0, 0);
                imgfifth.setPadding(0, 0, 0, 0);
                imgsixth.setPadding(0, 0, 0, 0);
                imgsecond.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgsecond.getResources(), R.drawable.title_logo, null);
                imgsecond.setBackground(highlight);

            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personimage = "im3.jpeg";
                imgfirst.setPadding(0, 0, 0, 0);
                imgsecond.setPadding(0, 0, 0, 0);
                imgfourth.setPadding(0, 0, 0, 0);
                imgfifth.setPadding(0, 0, 0, 0);
                imgsixth.setPadding(0, 0, 0, 0);
                imgthird.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgthird.getResources(), R.drawable.title_logo, null);
                imgthird.setBackground(highlight);

            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personimage = "im4.jpeg";
                imgfirst.setPadding(0, 0, 0, 0);
                imgthird.setPadding(0, 0, 0, 0);
                imgsecond.setPadding(0, 0, 0, 0);
                imgfifth.setPadding(0, 0, 0, 0);
                imgsixth.setPadding(0, 0, 0, 0);
                imgfourth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgfourth.getResources(), R.drawable.title_logo, null);
                imgfourth.setBackground(highlight);

            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personimage = "im5.jpeg";
                imgfirst.setPadding(0, 0, 0, 0);
                imgthird.setPadding(0, 0, 0, 0);
                imgfourth.setPadding(0, 0, 0, 0);
                imgsecond.setPadding(0, 0, 0, 0);
                imgsixth.setPadding(0, 0, 0, 0);
                imgfifth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgfifth.getResources(), R.drawable.title_logo, null);
                imgfifth.setBackground(highlight);

            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personimage = "im6.jpeg";
                imgfirst.setPadding(0, 0, 0, 0);
                imgthird.setPadding(0, 0, 0, 0);
                imgfourth.setPadding(0, 0, 0, 0);
                imgfifth.setPadding(0, 0, 0, 0);
                imgsecond.setPadding(0, 0, 0, 0);
                imgsixth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgsixth.getResources(), R.drawable.title_logo, null);
                imgsixth.setBackground(highlight);

            }
        });


    }
        void getResult(String cloth){
            Intent results = new Intent(SelectPerson.this,ShowVTONResults.class);
            results.putExtra("Cloth Name",cloth);
            results.putExtra("Person Name", personimage);
            startActivity(results);
            finish();
        }

}
