package com.example.bodymeasurements_vton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {


    private Button btn1;
    private EditText ed1, ed2, ed3;
    private TextView txt2;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProfile();
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alreadyUser();
            }
        });
    }

    void createProfile(){
        String email = ed1.getText().toString();
        String password = ed2.getText().toString();
        String confirm_ed = ed3.getText().toString();

        if (!TextUtils.equals(password, confirm_ed)) {
            Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(Register.this,"Invalid email!",Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            this.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                            User user = new User(email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "User has been created successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent();
                                        setResult(Activity.RESULT_OK,intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Register.this, "Failed to create the user", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });

                    }else{
                        Toast.makeText(Register.this, "Failed to create the user", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            });
//            Intent intent = new Intent(this, CreateProfile.class);
//            startActivity(intent);
        }
    }

    void alreadyUser(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    void init(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn1 = (Button) findViewById(R.id.btnRegister);
        txt2 = (TextView) findViewById(R.id.txtAlreadyUser);
        ed1 = (EditText) findViewById(R.id.editTextEmail1);
        ed2 = (EditText) findViewById(R.id.editTextPassword1);
        ed3 = (EditText) findViewById(R.id.editTextConfirm);
    }
}