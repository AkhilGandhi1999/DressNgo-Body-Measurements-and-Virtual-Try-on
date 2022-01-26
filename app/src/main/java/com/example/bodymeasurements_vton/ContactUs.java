package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
//public class ContactUs extends Fragment {
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_contact,container,false);
//
//    }
//}
// CHANGING FRAGMENTS TO ACTIVITY

public class ContactUs extends AppCompatActivity {

    private EditText ed1,ed2;
    private Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact);
        init();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/html");
                email.putExtra(Intent.EXTRA_EMAIL, new String[] {"akhil.gandhi10.ag@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Feedback/Issues for the App");
                email.putExtra(Intent.EXTRA_TITLE,"Please select email to share the response");
                email.putExtra(Intent.EXTRA_TEXT,"Name: " + ed1.getText()+"\n\n Message: \n" + ed2.getText());

                try{
                    startActivity(Intent.createChooser(email,"Please Select Email"));
                    
                }
                catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(ContactUs.this,"There are no email clients",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(ContactUs.this,"Response sent",Toast.LENGTH_LONG).show();
        finish();
    }


    void init(){
        ed1 = (EditText) findViewById(R.id.feedName);
        ed2 = (EditText) findViewById(R.id.feedback);
        btn1 = (Button) findViewById(R.id.btnSubmit);
    }
}