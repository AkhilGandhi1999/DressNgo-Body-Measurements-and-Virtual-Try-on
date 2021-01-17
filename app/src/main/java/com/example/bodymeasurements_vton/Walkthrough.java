package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Walkthrough extends Fragment {

    Button btn1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_walkthrough,container,false);

        init(v);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Camera.class);
                startActivity(intent);
            }
        });
        return v;
    }
    void init(View v){
        btn1 = (Button) v.findViewById(R.id.btnSkip);
    }
}
