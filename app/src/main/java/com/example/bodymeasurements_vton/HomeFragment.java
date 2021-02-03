//package com.example.bodymeasurements_vton;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//public class HomeFragment extends Fragment implements View.OnClickListener {
//    private Button btn1;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_home, container, false);
//
//        btn1 = (Button) v.findViewById(R.id.btnBody);
//
//        btn1.setOnClickListener(this);
//
//
//        return v;
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnBody:
//                Intent walk_intent = new Intent(getActivity(), Walkthrough.class);
//                startActivity(walk_intent);
//                break;
//
//        }
//
//    }
//}
// Version 2

package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.happyfresh.showcase.GuideView;
import com.happyfresh.showcase.config.AlignType;
import com.happyfresh.showcase.listener.GuideListener;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button btn1;
    public  View v;
    public int flag=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_home,container,false);
        flag=0;
        btn1 = (Button) v.findViewById(R.id.btnBody);

        btn1.setOnClickListener(this);
        return v;
    }
    public void changeprocess(){
        Log.e("hellll","I'm changed");
        Button myButton4=v.findViewById(R.id.btnBody);
        GuideView.Builder
                builder4 = new GuideView.Builder(getContext())
                .setTitle("GET MEASURED")
                .setContentText("Find your Measurements")
                .setPaddingTitle(2,0,2,0)
                .setPaddingMessage(3,0,3,0)
                .setPaddingButton(1,0,1,-2)
                .setViewAlign(AlignType.auto)
                .setTitleGravity(Gravity.CENTER)
                .setContentGravity(Gravity.CENTER_HORIZONTAL)
                .setButtonText("NEXT")
                .setContentTypeFace(Typeface.defaultFromStyle(Typeface.NORMAL))
                .setTargetView(myButton4)
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        //Log.d(TAG, "Success")

                        System.out.println("Hello");
                    }
                });
        GuideView mGuideView4 = builder4.build();
        //mGuideView4.mMessageView.okButton.setBackgroundColor(Color.parseColor("#0a04ba"));
        mGuideView4.mMessageView.okButton.setBackgroundResource(R.drawable.rounded_corners);
        mGuideView4.mMessageView.okButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mGuideView4.mMessageView.setBackgroundResource(R.drawable.rounded_corners1);
        //mGuideView4.mMessageView.mTitleTextView.setPadding(2,2,2,2);
        //mGuideView4.mMessageView.okButton.setPadding(0,0,0,2);
        //mGuideView4.mMessageView.okButton.setHeight(6);
        mGuideView4.show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBody:
                Intent walk_intent = new Intent(getActivity(), Walkthrough.class);
                startActivity(walk_intent);
                break;

        }

    }
}
