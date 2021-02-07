package com.example.bodymeasurements_vton;

import android.content.SharedPreferences;
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

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;
import static android.os.ParcelFileDescriptor.MODE_APPEND;

public class MeasurementsFragment extends Fragment {

    private Float[] data = new Float[11];
    private  ListAdapter listAdapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_measurements,container,false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        // Read values from DB
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userKey = user.getUid();


        databaseReference.child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                HashMap<String,String> measurements = (HashMap<String, String>) snapshot.child("measurements").getValue();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User_Measurements",MODE_PRIVATE);



                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                // Storing the key and its value as the data fetched from edittext
                Float Shoulder = Float.valueOf(measurements.get("shoulder"));
                Float Right_hand = Float.valueOf(measurements.get("right_hand"));
                Float Left_hand = Float.valueOf(measurements.get("left_hand"));
                Float Right_leg = Float.valueOf(measurements.get("right_leg"));
                Float Left_leg = Float.valueOf(measurements.get("left_leg"));
                Float Hip = Float.valueOf(measurements.get("hip"));
                Float Waist = Float.valueOf(measurements.get("waist"));
                Float Chest_girth = Float.valueOf(measurements.get("chest_girth"));
                Float Thigh_girth = Float.valueOf(measurements.get("thigh_girth"));
                Float Ankle_regular = Float.valueOf(measurements.get("ankle_regular"));
                Float Ankle_tight = Float.valueOf(measurements.get("ankle_tight"));


                myEdit.putFloat("shoulder",  Shoulder);
                myEdit.putFloat("right_hand", Right_hand );
                myEdit.putFloat("left_hand", Left_hand);
                myEdit.putFloat("right_leg",Right_leg);
                myEdit.putFloat("left_leg", Left_leg);
                myEdit.putFloat("hip", Hip);
                myEdit.putFloat("waist", Waist);
                myEdit.putFloat("chest_girth",Chest_girth);
                myEdit.putFloat("thigh_girth", Thigh_girth);
                myEdit.putFloat("ankle_regular", Ankle_regular);
                myEdit.putFloat("ankle_tight", Ankle_tight);

                myEdit.commit();

                //Update SharedPreferences

                SharedPreferences sh = getActivity().getSharedPreferences("User_Measurements", MODE_PRIVATE);

                // The value will be default as empty string because for
                // the very first time when the app is opened, there is nothing to show
                data[0] = sh.getFloat("shoulder",0);
                data[1] = sh.getFloat("right_hand",0);
                data[2] = sh.getFloat("left_hand",0);
                data[3] = sh.getFloat("right_leg",0);
                data[4] = sh.getFloat("left_leg",0);
                data[5] = sh.getFloat("hip",0);
                data[6] = sh.getFloat("waist",0);
                data[7] = sh.getFloat("chest_girth",0);
                data[8] = sh.getFloat("thigh_girth",0);
                data[9] = sh.getFloat("ankle_regular",0);
                data[10] = sh.getFloat("ankle_tight",0);


                listAdapter = new ListAdapter(data);
                recyclerView.setAdapter(listAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}
