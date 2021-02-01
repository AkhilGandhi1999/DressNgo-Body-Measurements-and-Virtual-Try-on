package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//public class EditProfile extends Fragment {
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_edit,container,false);
//
//    }
//}
// CHANGING FRAGMENTS TO ACTIVITY
public class EditProfile extends AppCompatActivity {

    private TextView tx1;
    private EditText ed1, ed2, ed3, ed4, ed5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);
        init();

        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, ChangePassword.class);
                startActivity(intent);
            }
        });
        ed1.setText("USER 1");
        ed2.setText("USER 1");
        ed3.setText("169");
        ed4.setText("63");
        ed5.setText("Male");

    }
    void init(){
        tx1 = (TextView) findViewById(R.id.changePassword);
        ed1 = (EditText) findViewById(R.id.changeName);
        ed2 = (EditText) findViewById(R.id.changeEmail);
        ed3 = (EditText) findViewById(R.id.changeHeight);
        ed4 = (EditText) findViewById(R.id.changeWeight);
        ed5 = (EditText) findViewById(R.id.changeGender);

    }
}