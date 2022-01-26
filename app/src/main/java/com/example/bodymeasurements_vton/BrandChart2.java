package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class BrandChart2 extends AppCompatActivity {

    private ImageView img1,img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_chart2);

        SharedPreferences sh = getSharedPreferences("User_Measurements",MODE_PRIVATE);
        String gen = sh.getString("Gender","");

        init();

        if(gen.equals("Male")){
            String uri = "@drawable/chart9";  // where myresource (without the extension) is the file
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            img1.setImageDrawable(res);

            String uri1 = "@drawable/chart10";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            Drawable res1 = getResources().getDrawable(imageResource1);
            img2.setImageDrawable(res1);
        }
        else{
            String uri = "@drawable/chart11";  // where myresource (without the extension) is the file
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            img1.setImageDrawable(res);

            String uri1 = "@drawable/chart12";
            int imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
            Drawable res1 = getResources().getDrawable(imageResource1);
            img2.setImageDrawable(res1);
        }

    }

    void init(){
        img1 = (ImageView) findViewById(R.id.i1);
        img2 = (ImageView) findViewById(R.id.i2);

    }
}