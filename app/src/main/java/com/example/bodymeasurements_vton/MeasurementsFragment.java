package com.example.bodymeasurements_vton;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

public class MeasurementsFragment extends Fragment {

    private Double[] data = new Double[11];
    private  ListAdapter listAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_measurements,container,false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDb = mDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userKey = user.getUid();


        mDb.child("Users").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String check = dataSnapshot.child("shoulder").getValue().toString();
                if(check.equals("0")){
                    data[0] = (double) 0;
                    data[1] = (double) 0;
                    data[2] = (double) 0;
                    data[3] = (double) 0;
                    data[4] = (double) 0;
                    data[5] = (double) 0;
                    data[6] = (double) 0;
                    data[7] = (double) 0;
                    data[8] = (double) 0;
                    data[9] = (double) 0;
                    data[10] = (double) 0;
                }
                else{
                    data[0] = (Double) dataSnapshot.child("shoulder").getValue();
                    Log.i("Fetching", String.valueOf(data[0]));
                    data[1] = Double.valueOf(dataSnapshot.child("right_hand").getValue().toString());
                    data[2] = Double.valueOf(dataSnapshot.child("left_hand").getValue().toString());
                    data[3] = Double.valueOf(dataSnapshot.child("right_leg").getValue().toString());
                    data[4] = Double.valueOf(dataSnapshot.child("left_leg").getValue().toString());
                    data[5] = Double.valueOf(dataSnapshot.child("hip").getValue().toString());
                    data[6] = Double.valueOf(dataSnapshot.child("waist").getValue().toString());
                    data[7] = Double.valueOf(dataSnapshot.child("chest_girth").getValue().toString());
                    data[8] = Double.valueOf(dataSnapshot.child("thigh_girth").getValue().toString());
                    data[9] = Double.valueOf(dataSnapshot.child("ankle_regular").getValue().toString());
                    data[10] = Double.valueOf(dataSnapshot.child("ankle_tight").getValue().toString());
                    listAdapter = new ListAdapter(data);
                    recyclerView.setAdapter(listAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return v;
    }
}
