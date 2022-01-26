package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

public class MeasurementsFragment extends Fragment {

    private Float[] data = new Float[11];
    private ListAdapter listAdapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private Button btn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_measurements,container,false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        btn1 = (Button) v.findViewById(R.id.getsize);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String generalShirt = "",generalPant = "", brand1Shirt = "", brand1Pant = "", brand2Shirt = "", brand2Pant = "";
                SharedPreferences sh = getActivity().getSharedPreferences("User_Measurements",MODE_PRIVATE);
                String gen = sh.getString("Gender","");
               // Toast.makeText(getActivity(),gen,Toast.LENGTH_SHORT).show();
                if(gen.equals("Male")){
                    generalShirt = getMaleGeneralShirt(data);
                    generalPant = getMaleGeneralPant(data);

                    brand1Shirt = getMaleBrand_1_Shirt(data);
                    brand1Pant = getMaleBrand_1_Pant(data);

                    brand2Shirt = getMaleBrand_2_Shirt(data);
                    brand2Pant = getMaleBrand_2_Pant(data);

                   // Toast.makeText(getActivity(),generalShirt,Toast.LENGTH_SHORT).show();
                }
                else if(gen.equals("Female")){
                    generalShirt = getFemaleGeneralShirt(data);
                    generalPant = getFemaleGeneralPant(data);

                    brand1Shirt = getFemaleBrand_1_Shirt(data);
                    brand1Pant = getFemaleBrand_1_Pant(data);

                    brand2Shirt = getFemaleBrand_2_Shirt(data);
                    brand2Pant = getFemaleBrand_2_Pant(data);
                }
                Intent size_intent = new Intent(getActivity(), GettingSizeInfo.class);
                size_intent.putExtra("General Shirt",generalShirt);
                size_intent.putExtra("General Pant",generalPant);

                size_intent.putExtra("Brand1 Shirt",brand1Shirt);
                size_intent.putExtra("Brand1 Pant",brand1Pant);

                size_intent.putExtra("Brand2 Shirt",brand2Shirt);
                size_intent.putExtra("Brand2 Pant",brand2Pant);

                startActivity(size_intent);
            }
        });

        // Read values from DB
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userKey = user.getUid();


        databaseReference.child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                HashMap<String,String> measurements = (HashMap<String, String>) snapshot.child("measurements").getValue();
                long he = (long) snapshot.child("height").getValue();
                int height = (int) he;
                String gender = String.valueOf(snapshot.child("gender").getValue());


                //Toast.makeText(getActivity(),gender + height,Toast.LENGTH_SHORT).show();

                //String Gender = snapshot.child("gender").getValue().toString();
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

                myEdit.putInt("height", height);
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
                myEdit.putString("Gender",gender);

                myEdit.commit();

                //Update SharedPreference
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
    // all the male sizes
     String getMaleGeneralShirt(Float[] data) {


            if(data[0] >= 35 && data[0]<=38){
                return "XS";
            }
            else if(data[0] >= 38 && data[0]<=42){
                return "S";
            }
            else if(data[0] >=42 && data[0]<=46) {
                return "M";
            }
            else if(data[0] >=46 && data[0]<=50) {
                return "L";
            }
            else if(data[0] >=50 && data[0]<=54) {
                return "XL";
            }
            else if(data[0] >=54) {
                return "XXL";
            }
            return "None";
    }

     String getMaleGeneralPant(Float[] data){
         if(data[6] >= 71 && data[6]<=76){
             return "XS";
         }
         else if(data[6] >= 76 && data[6]<=81){
             return "S";
         }
         else if(data[6] >=81 && data[6]<=86) {
             return "M";
         }
         else if(data[6] >=86 && data[6]<=91) {
             return "L";
         }
         else if(data[6] >=91 && data[6]<=96) {
             return "XL";
         }
         else if(data[6] >=96) {
             return "XXL";
         }
        return "None";
    }
    String getMaleBrand_1_Shirt(Float[] data){
        if(data[7] >= 91 && data[7]<=96){
            return "XS";
        }
        else if(data[7] >= 96 && data[7]<=101){
            return "S";
        }
        else if(data[7] >=101 && data[7]<=106) {
            return "M";
        }
        else if(data[7] >=106 && data[7]<=111) {
            return "L";
        }
        else if(data[7] >=111 && data[7]<=116) {
            return "XL";
        }
        else if(data[7] >=116) {
            return "XXL";
        }
        return "None";
    }
    String getMaleBrand_1_Pant(Float[] data){
        if(data[6] >= 76 && data[6]<=79){
            return "XS";
        }
        else if(data[6] >= 79 && data[6]<=82){
            return "S";
        }
        else if(data[6] >=82 && data[6]<=85) {
            return "M";
        }
        else if(data[6] >=85 && data[6]<=88) {
            return "L";
        }
        else if(data[6] >=88 && data[6]<=91) {
            return "XL";
        }
        else if(data[6] >=91) {
            return "XXL";
        }
        return "None";
    }
    String getMaleBrand_2_Shirt(Float[] data){
        if(data[7] >= 85 && data[7]<=89){
            return "XS";
        }
        else if(data[7] >= 89 && data[7]<=93){
            return "S";
        }
        else if(data[7] >=93 && data[7]<=97) {
            return "M";
        }
        else if(data[7] >=97 && data[7]<=101) {
            return "L";
        }
        else if(data[7] >=101 && data[7]<=105) {
            return "XL";
        }
        else if(data[7] >=105) {
            return "XXL";
        }
        return "None";
    }
    String getMaleBrand_2_Pant(Float[] data){
        if(data[6] >= 76 && data[6]<=80){
            return "XS";
        }
        else if(data[6] >= 80 && data[6]<=84){
            return "S";
        }
        else if(data[6] >=84 && data[6]<=88) {
            return "M";
        }
        else if(data[6] >=88 && data[6]<=92) {
            return "L";
        }
        else if(data[6] >=92 && data[6]<=96) {
            return "XL";
        }
        else if(data[6] >=96) {
            return "XXL";
        }
        return "None";
    }

    //all the female sizes

    String getFemaleGeneralShirt(Float[] data) {
        if(data[7] >= 81 && data[7]<=85){
            return "XS";
        }
        else if(data[7] >= 85 && data[7]<=90){
            return "S";
        }
        else if(data[7] >=90 && data[7]<=95) {
            return "M";
        }
        else if(data[7] >=95 && data[7]<=100) {
            return "L";
        }
        else if(data[7] >=100 && data[7]<=105) {
            return "XL";
        }
        else if(data[7] >=105) {
            return "XXL";
        }
        return "None";
    }

    String getFemaleGeneralPant(Float[] data){
        if(data[5] >= 84 && data[5]<=89){
            return "XS";
        }
        else if(data[5] >= 89 && data[5]<=94){
            return "S";
        }
        else if(data[5] >=94 && data[5]<=99) {
            return "M";
        }
        else if(data[5] >=104 && data[5]<=104) {
            return "L";
        }
        else if(data[5] >=104 && data[5]<=109) {
            return "XL";
        }
        else if(data[5] >=109) {
            return "XXL";
        }
        return "None";
    }
    String getFemaleBrand_1_Shirt(Float[] data){

        if(data[7] >= 80 && data[7]<=85){
            return "XS";
        }
        else if(data[7] >= 85 && data[7]<=90){
            return "S";
        }
        else if(data[7] >=90 && data[7]<=95) {
            return "M";
        }
        else if(data[7] >=95 && data[7]<=100) {
            return "L";
        }
        else if(data[7] >=100 && data[7]<=105) {
            return "XL";
        }
        else if(data[7] >=105) {
            return "XXL";
        }
        return "None";
    }
    String getFemaleBrand_1_Pant(Float[] data){
        if(data[6] >= 65 && data[6]<=67){
            return "XS";
        }
        else if(data[6] >= 67 && data[6]<=69){
            return "S";
        }
        else if(data[6] >=69 && data[6]<=71) {
            return "M";
        }
        else if(data[6] >=71 && data[6]<=73) {
            return "L";
        }
        else if(data[6] >=73 && data[6]<=75) {
            return "XL";
        }
        else if(data[6] >=75) {
            return "XXL";
        }
        return "None";
    }
    String getFemaleBrand_2_Shirt(Float[] data){
        if(data[7] >= 81 && data[7]<=86){
            return "XS";
        }
        else if(data[7] >= 86 && data[7]<=91){
            return "S";
        }
        else if(data[7] >=91 && data[7]<=96) {
            return "M";
        }
        else if(data[7] >=96 && data[7]<=101) {
            return "L";
        }
        else if(data[7] >=101 && data[7]<=106) {
            return "XL";
        }
        else if(data[7] >=106) {
            return "XXL";
        }
        return "None";
    }
    String getFemaleBrand_2_Pant(Float[] data){
        if(data[6] >= 62 && data[6]<=66){
            return "XS";
        }
        else if(data[6] >= 66 && data[6]<=70){
            return "S";
        }
        else if(data[6] >=70 && data[6]<=74) {
            return "M";
        }
        else if(data[6] >=74 && data[6]<=78) {
            return "L";
        }
        else if(data[6] >=78 && data[6]<=82) {
            return "XL";
        }
        else if(data[6] >=82) {
            return "XXL";
        }
        return "None";
    }

}
