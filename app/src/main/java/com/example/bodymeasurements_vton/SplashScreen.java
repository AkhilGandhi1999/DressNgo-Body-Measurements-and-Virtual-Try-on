//package com.example.bodymeasurements_vton;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//
//public class SplashScreen extends AppCompatActivity {
//
//    private static int SPLASH_TIME_OUT = 5000;
//    //Button btn_login;
//    Sharedpreference sp = new Sharedpreference();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (sp.getUserName(SplashScreen.this).length() == 0) {
//                    // call Login Activity
//                    Intent homeIndent = new Intent(SplashScreen.this, LoginPage.class);
//                    startActivity(homeIndent);
//                    finish();
//                } else {
//                    // Stay at the current activity.
//                    Intent homeIndent = new Intent(SplashScreen.this, Dashboard.class);
//                    startActivity(homeIndent);
//                    finish();
//                }
//
//            }
//        }, SPLASH_TIME_OUT);
//    }
//
//}
//  /*  public void onBackPressed(){
//
//    }
//    @Override
//    public boolean onKeyDown(int key_code, KeyEvent key_event) {
//        if (key_code== KeyEvent.KEYCODE_BACK) {
//            super.onKeyDown(key_code, key_event);
//            return true;
//        }
//        return false;
//    }*/