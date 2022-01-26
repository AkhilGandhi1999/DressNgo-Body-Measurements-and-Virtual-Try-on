package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GettingSizeInfo extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private TextView t1,t2,t3,t4,t5,t6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_size_info);

        t1 = (TextView) findViewById(R.id.tvalue1);
        t2 = (TextView) findViewById(R.id.pvalue1);

        t3 = (TextView) findViewById(R.id.tvalue2);
        t4 = (TextView) findViewById(R.id.pvalue2);

        t5 = (TextView) findViewById(R.id.tvalue3);
        t6 = (TextView) findViewById(R.id.pvalue3);

        t1.setText(getIntent().getStringExtra("General Shirt"));
        //Toast.makeText(GettingSizeInfo.this,t1.toString(),Toast.LENGTH_SHORT).show();
        t2.setText(getIntent().getStringExtra("General Pant"));

        t3.setText(getIntent().getStringExtra("Brand1 Shirt"));
        t4.setText(getIntent().getStringExtra("Brand1 Pant"));

        t5.setText(getIntent().getStringExtra("Brand2 Shirt"));
        t6.setText(getIntent().getStringExtra("Brand2 Pant"));

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        //builder.setTitle("Name");
//
//        // set the custom layout
//        final View customLayout
//                = getLayoutInflater()
//                .inflate(
//                        R.layout.custom_dailog,
//                        null);
//        builder.setView(customLayout);
//
//
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Yes button Clicked", Toast.LENGTH_LONG).show();
//                Log.i("Code2care ", "Yes button Clicked!");
//                dialog.dismiss();
//            }
//        });

        img1 = (ImageView) findViewById(R.id.chart1);
        img2 = (ImageView) findViewById(R.id.chart2);
        img3 = (ImageView) findViewById(R.id.chart3);
        //AlertDialog dialog = builder.create();

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent general  = new Intent(GettingSizeInfo.this,GeneralChart.class);
                startActivity(general);
//                dialog.show();
//                dialog.getWindow().setLayout(1100, 800);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent brand1 = new Intent(GettingSizeInfo.this,BrandChart1.class);
                startActivity(brand1);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent brand2 = new Intent(GettingSizeInfo.this,BrandChart2.class);
                startActivity(brand2);
            }
        });

    }
}