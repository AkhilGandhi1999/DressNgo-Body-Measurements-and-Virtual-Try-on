package com.example.bodymeasurements_vton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import static com.example.bodymeasurements_vton.R.string.toast_message;

public class Walkthrough extends Fragment {

    Button btn1;
  //  private static final String VIDEO_SAMPLE = "videohigh";

//    private TextView mBufferingTextView;

    // Current playback position (in milliseconds).
    private int mCurrentPosition = 0;

    // Tag for the instance state bundle.
    private static final String PLAYBACK_TIME = "play_time";

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
        MediaController mc= new MediaController(getActivity());

        VideoView view = (VideoView)v.findViewById(R.id.videoview);
        String path = "android.resource://" + Objects.requireNonNull(getActivity()).getPackageName() + "/" + R.raw.videohigh;
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mc);

        return v;
    }

    void init(View v){
        btn1 = (Button) v.findViewById(R.id.btnSkip);
    }
}
