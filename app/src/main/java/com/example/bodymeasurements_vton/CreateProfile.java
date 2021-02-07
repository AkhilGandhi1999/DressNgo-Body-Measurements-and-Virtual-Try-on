package com.example.bodymeasurements_vton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateProfile extends AppCompatActivity {


    private Button btn1;
    private EditText ed1, ed2, ed3;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User();
            }
        });

    }
    @Override
    public void onBackPressed(){
    //super.onBackPressed();
    }

    void User(){
        String name = ed1.getText().toString();
        int weight = Integer.parseInt(ed2.getText().toString());
        int height = Integer.parseInt(ed3.getText().toString());
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        String gender = radioButton.getText().toString();

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("email");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datas: snapshot.getChildren()) {
                    datas.getRef().child("name").setValue(name);
                    datas.getRef().child("gender").setValue(gender);
                    datas.getRef().child("height").setValue(height);
                    datas.getRef().child("weight").setValue(weight);

                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("User_Measurements",MODE_PRIVATE);

                    // Creating an Editor object to edit(write to the file)
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    // Storing the key and its value as the data fetched from edittext
                    myEdit.putInt("height", height);
                    myEdit.putFloat("shoulder",0);
                    myEdit.putFloat("right_hand",0);
                    myEdit.putFloat("left_hand",0);
                    myEdit.putFloat("right_leg",0);
                    myEdit.putFloat("left_leg",0);
                    myEdit.putFloat("hip",0);
                    myEdit.putFloat("waist",0);
                    myEdit.putFloat("chest_girth",0);
                    myEdit.putFloat("thigh_girth",0);
                    myEdit.putFloat("ankle_regular",0);
                    myEdit.putFloat("ankle_tight",0);


                    // Once the changes have been made,
                    // we need to commit to apply those changes made,
                    // otherwise, it will throw an error
                    myEdit.commit();

                    Intent intent = new Intent(CreateProfile.this, MainScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    void init(){
        btn1 = (Button) findViewById(R.id.btnCreateUser);
        ed1 = (EditText) findViewById(R.id.editTextName);
        ed2 = (EditText) findViewById(R.id.editTextWeight);
        ed3 = (EditText) findViewById(R.id.editTextHeight);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }
}