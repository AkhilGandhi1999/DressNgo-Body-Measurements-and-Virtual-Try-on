package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView txt3;
    private TextView t1, t2, t3, t4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Button txt1 = (Button) rootView.findViewById(R.id.textContactUs);


        txt3 = (TextView) rootView.findViewById(R.id.textLogout);

        init(rootView);

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
                height.concat("cm");
                weight.concat("kg");
                t1.setText(email);
                t2.setText(height);
                t3.setText(weight);
                t4.setText(gender);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


        txt1.setOnClickListener(this);
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Logged Out",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.textContactUs:
                Intent contact_intent = new Intent(getActivity(),ContactUs.class);
                startActivity(contact_intent);
                break;

//            case R.id.textEditProfile:
//                Intent profile_intent = new Intent(getActivity(),EditProfile.class);
//                startActivity(profile_intent);
//                break;
        }
    }

    void init(View v){
        t1 = (TextView) v.findViewById(R.id.t1);
        t2 = (TextView) v.findViewById(R.id.t2);
        t3 = (TextView) v.findViewById(R.id.t3);
        t4 = (TextView) v.findViewById(R.id.t4);
    }
}
