package com.example.bodymeasurements_vton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private TextView txt1;
    private Button btn1;
    private EditText ed1, ed2;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mAuth = FirebaseAuth.getInstance();
        Bundle extras = getIntent().getExtras();
        if(extras == null){
          //  Toast.makeText(this,"Null ",Toast.LENGTH_LONG).show();
            if(mAuth.getCurrentUser() != null)
            {
                login();
            }
        }
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    void register(){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
        finish();
    }
    void login(){
        Intent profile = new Intent(MainActivity.this, MainScreen.class);
        startActivity(profile);
        finish();
    }
    void signIn(){
        String email = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(MainActivity.this,"Email is empty!",Toast.LENGTH_LONG).show();

        }
       else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(MainActivity.this,"Invalid email!",Toast.LENGTH_LONG).show();

        }
       else {

            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        //redirect
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot datas: dataSnapshot.getChildren()){
                                    String flag = datas.child("flag").getValue().toString();
                                    if(flag.equals("0")){
                                        datas.getRef().child("flag").setValue(1);
                                        Toast.makeText(MainActivity.this, "Inside flag", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(MainActivity.this, CreateProfile.class);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        progressBar.setVisibility(View.GONE);
                                        login();

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });

                        //Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Failed to login, check entered details", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            });
        }

    }
    void init(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        txt1 = (TextView) findViewById(R.id.txtRegister);
        btn1 = (Button) findViewById(R.id.btnSignin);
        ed1 = (EditText) findViewById(R.id.editTextEmail);
        ed2 = (EditText) findViewById(R.id.editTextPassword);
    }
}