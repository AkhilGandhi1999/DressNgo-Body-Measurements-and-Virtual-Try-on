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
        if(mAuth.getCurrentUser() != null)
        {
            login();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if(resultCode== Activity.RESULT_OK)
                {
                    Toast.makeText(MainActivity.this, "You Can Now Login", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    void register(){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
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
                        Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        login();

                    } else {
                        Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_LONG).show();
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