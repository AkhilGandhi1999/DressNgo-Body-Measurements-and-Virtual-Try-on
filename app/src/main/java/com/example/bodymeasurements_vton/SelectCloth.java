package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SelectCloth extends AppCompatActivity {
    String status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cloth);


        CardView first=(CardView)findViewById(R.id.first);
        CardView second=(CardView)findViewById(R.id.second);
        CardView third=(CardView)findViewById(R.id.third);
        CardView fourth=(CardView)findViewById(R.id.fourth);
        CardView fifth=(CardView)findViewById(R.id.fifth);
        CardView sixth=(CardView)findViewById(R.id.sixth);
        CardView seventh=(CardView)findViewById(R.id.seventh);
        CardView eighth=(CardView)findViewById(R.id.eighth);
        CardView nineth=(CardView)findViewById(R.id.nineth);
        CardView tenth=(CardView)findViewById(R.id.tenth);
        CardView eleventh=(CardView)findViewById(R.id.eleventh);
        CardView twelve=(CardView)findViewById(R.id.twelve);

        ImageView imgfirst=(ImageView)findViewById(R.id.imgfirst);
        ImageView imgsecond=(ImageView)findViewById(R.id.imgsecond);
        ImageView imgthird=(ImageView)findViewById(R.id.imgthird);
        ImageView imgfourth=(ImageView)findViewById(R.id.imgfourth);
        ImageView imgfifth=(ImageView)findViewById(R.id.imgfifth);
        ImageView imgsixth=(ImageView)findViewById(R.id.imgsixth);
        ImageView imgseventh=(ImageView)findViewById(R.id.imgseventh);
        ImageView imgeighth=(ImageView)findViewById(R.id.imgeighth);
        ImageView imgnineth=(ImageView)findViewById(R.id.imgnineth);
        ImageView imgtenth=(ImageView)findViewById(R.id.imgtenth);
        ImageView imgeleventh=(ImageView)findViewById(R.id.imgeleventh);
        ImageView imgtwelve=(ImageView)findViewById(R.id.imgtwelve);

        Button confirm=(Button)findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(status.length()>0) {
                    Intent intent = new Intent(SelectCloth.this, SelectPerson.class);
                    intent.putExtra("cloth_image_id", status);
                    startActivity(intent);
                    finish();
                }
            }
        });



        first.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                status="cloth1.jpeg";
                imgsecond.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgfirst.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgfirst.getResources(), R.drawable.title_logo, null);
                imgfirst.setBackground(highlight);

            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth2.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgsecond.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgsecond.getResources(), R.drawable.title_logo, null);
                imgsecond.setBackground(highlight);

            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth3.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgthird.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgthird.getResources(), R.drawable.title_logo, null);
                imgthird.setBackground(highlight);

            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth4.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgfourth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgfourth.getResources(), R.drawable.title_logo, null);
                imgfourth.setBackground(highlight);

            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth5.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgfifth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgfifth.getResources(), R.drawable.title_logo, null);
                imgfifth.setBackground(highlight);

            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth6.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgsixth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgsixth.getResources(), R.drawable.title_logo, null);
                imgsixth.setBackground(highlight);

            }
        });
        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth7.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgseventh.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgseventh.getResources(), R.drawable.title_logo, null);
                imgseventh.setBackground(highlight);

            }
        });
        eighth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth8.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgeighth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgeighth.getResources(), R.drawable.title_logo, null);
                imgeighth.setBackground(highlight);

            }
        });
        nineth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth9.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgnineth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgnineth.getResources(), R.drawable.title_logo, null);
                imgnineth.setBackground(highlight);

            }
        });
        tenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth10.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgtenth.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgtenth.getResources(), R.drawable.title_logo, null);
                imgtenth.setBackground(highlight);

            }
        });
        eleventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth11.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgtwelve.setPadding(0,0,0,0);
                imgeleventh.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgeleventh.getResources(), R.drawable.title_logo, null);
                imgeleventh.setBackground(highlight);

            }
        });
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="cloth12.jpeg";
                imgfirst.setPadding(0,0,0,0);
                imgthird.setPadding(0,0,0,0);
                imgfourth.setPadding(0,0,0,0);
                imgfifth.setPadding(0,0,0,0);
                imgsixth.setPadding(0,0,0,0);
                imgseventh.setPadding(0,0,0,0);
                imgsecond.setPadding(0,0,0,0);
                imgnineth.setPadding(0,0,0,0);
                imgtenth.setPadding(0,0,0,0);
                imgeleventh.setPadding(0,0,0,0);
                imgeighth.setPadding(0,0,0,0);
                imgtwelve.setPadding(8, 8, 8, 8);
                Drawable highlight = ResourcesCompat.getDrawable(imgtwelve.getResources(), R.drawable.title_logo, null);
                imgtwelve.setBackground(highlight);

            }
        });
    }
}