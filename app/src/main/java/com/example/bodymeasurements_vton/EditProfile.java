package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    private EditText ed1, ed2, ed3, ed4, ed5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);
        init();


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDb = mDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userKey = user.getUid();


        mDb.child("Users").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String gender = dataSnapshot.child("gender").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String height = dataSnapshot.child("height").getValue().toString();
                String weight = dataSnapshot.child("weight").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                height.concat("cm");
                weight.concat("kg");
                ed1.setText(name);
                ed2.setText(email);
                ed3.setText(height);
                ed4.setText(weight);
                ed5.setText(gender);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


    }

    void init(){
        ed1 = (EditText) findViewById(R.id.changeName);
        ed2 = (EditText) findViewById(R.id.changeEmail);
        ed3 = (EditText) findViewById(R.id.changeHeight);
        ed4 = (EditText) findViewById(R.id.changeWeight);
        ed5 = (EditText) findViewById(R.id.changeGender);

    }
}