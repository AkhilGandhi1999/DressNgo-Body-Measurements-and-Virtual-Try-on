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
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button btn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btn1 = (Button) v.findViewById(R.id.btnBody);

        btn1.setOnClickListener(this);


        return v;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBody:
                Intent walk_intent = new Intent(getActivity(), Walkthrough.class);
                startActivity(walk_intent);
                break;

        }

    }
}
