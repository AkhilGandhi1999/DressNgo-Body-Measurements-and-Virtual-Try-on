//package com.example.bodymeasurements_vton;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class MainScreen extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_screen);
//
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
//        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
//
//
//    }
//
//    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            Fragment selectedFragment = null;
//            switch (item.getItemId()){
//                case R.id.home:
//                    selectedFragment = new HomeFragment();
//                    break;
//                case R.id.measurements:
//                    selectedFragment =  new MeasurementsFragment();
//                    break;
//                case R.id.profile:
//                    selectedFragment =  new ProfileFragment();
//                    break;
//            }
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
//            return true;
//        }
//    };
//}

//Version 2
package com.example.bodymeasurements_vton;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.happyfresh.showcase.GuideView;
import com.happyfresh.showcase.config.AlignType;
import com.happyfresh.showcase.listener.GuideListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainScreen extends AppCompatActivity {

    private GuideView mGuideView2;
    private GuideView mGuideView3;
    private GuideView mGuideView4;
    private GuideView.Builder builder2;
    private GuideView.Builder builder3;
    private GuideView.Builder builder4;
    public  Activity myActivity;

    JSONObject jsonObject;
    private Double[] data = new Double[11];
    HomeFragment defaultFragment = new HomeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = null;
        ViewGroup container = null;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myP",MODE_PRIVATE);
        setContentView(R.layout.activity_main_screen);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        Bundle extras = getIntent().getExtras();

        if(extras != null){

            Fragment fragment  = new MeasurementsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, defaultFragment).commit();

        }

        BottomNavigationItemView myButton1 = findViewById(R.id.home);
        //Log.d(TAG, "Success")
        GuideView.Builder builder1 = new GuideView.Builder(this)
                .setTitle("HOME")
                .setContentText("Go to dashboard")
                .setPaddingTitle(2,0,2,0)
                .setPaddingMessage(3,0,3,0)
                .setPaddingButton(1,0,1,-2)
                .setViewAlign(AlignType.auto)
                .setTitleGravity(Gravity.CENTER)
                .setContentGravity(Gravity.CENTER_HORIZONTAL)
                .setButtonText("NEXT")
                .setContentTypeFace(Typeface.defaultFromStyle(Typeface.NORMAL))
                .setTargetView(myButton1)
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        //Log.d(TAG, "Success")

                        mGuideView2 = builder2.build();

                        mGuideView2.mMessageView.okButton.setBackgroundResource(R.drawable.rounded_corners);
                        mGuideView2.mMessageView.okButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        mGuideView2.mMessageView.setBackgroundResource(R.drawable.rounded_corners1);
                        mGuideView2.show();
                        System.out.println("Hello");
                    }
                });
        GuideView mGuideView1 = builder1.build();
        mGuideView1.mMessageView.okButton.setBackgroundResource(R.drawable.rounded_corners);
        mGuideView1.mMessageView.okButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mGuideView1.mMessageView.setBackgroundResource(R.drawable.rounded_corners1);
        if(!pref.getBoolean("IsTourDone", false)) {
            mGuideView1.show();
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("IsTourDone",true);
            editor.apply();
        }
        BottomNavigationItemView myButton2 = findViewById(R.id.measurements);
        builder2 = new GuideView.Builder(this)
                .setTitle("MY MEASUREMENTS")
                .setContentText("View your Measurements")
                .setPaddingTitle(2,0,2,0)
                .setPaddingMessage(3,0,3,0)
                .setPaddingButton(1,0,1,-2)
                .setViewAlign(AlignType.auto)
                .setTitleGravity(Gravity.CENTER)
                .setContentGravity(Gravity.CENTER_HORIZONTAL)
                .setButtonText("NEXT")
                .setContentTypeFace(Typeface.defaultFromStyle(Typeface.NORMAL))
                .setTargetView(myButton2)
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        //Log.d(TAG, "Success")
                        mGuideView3 = builder3.build();
                        mGuideView3.mMessageView.okButton.setBackgroundResource(R.drawable.rounded_corners);
                        mGuideView3.mMessageView.okButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        mGuideView3.mMessageView.setBackgroundResource(R.drawable.rounded_corners1);
                        mGuideView3.show();
                        System.out.println("Hello");
                    }
                });
        BottomNavigationItemView myButton3 = findViewById(R.id.profile);
        builder3 = new GuideView.Builder(this)
                .setTitle("PROFILE")
                .setContentText("Change & manage your profile")
                .setPaddingTitle(2,0,2,0)
                .setPaddingMessage(3,0,3,0)
                .setPaddingButton(1,0,1,-2)
                .setViewAlign(AlignType.auto)
                .setTitleGravity(Gravity.CENTER)
                .setContentGravity(Gravity.CENTER_HORIZONTAL)
                .setButtonText("NEXT")
                .setContentTypeFace(Typeface.defaultFromStyle(Typeface.NORMAL))
                .setTargetView(myButton3)
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        //Log.d(TAG, "Success")
                        defaultFragment.changeprocess();
                        System.out.println("Hello");
                    }
                });
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.measurements:
                    selectedFragment =  new MeasurementsFragment();
                    break;
                case R.id.profile:
                    selectedFragment =  new ProfileFragment();
                    break;
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
            return true;
        }
    };
}