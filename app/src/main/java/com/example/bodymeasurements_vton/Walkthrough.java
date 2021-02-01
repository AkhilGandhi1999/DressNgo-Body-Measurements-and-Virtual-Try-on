package com.example.bodymeasurements_vton;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.example.bodymeasurements_vton.R.string.toast_message;

//public class Walkthrough extends Fragment {
//
//    Button btn1;
//  //  private static final String VIDEO_SAMPLE = "videohigh";
//
////    private TextView mBufferingTextView;
//
//    // Current playback position (in milliseconds).
//    private int mCurrentPosition = 0;
//
//    // Tag for the instance state bundle.
//    private static final String PLAYBACK_TIME = "play_time";
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v =  inflater.inflate(R.layout.fragment_walkthrough,container,false);
//        init(v);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),Camera.class);
//                startActivity(intent);
//            }
//        });
//        MediaController mc= new MediaController(getActivity());
//
//        VideoView view = (VideoView)v.findViewById(R.id.videoview);
//        String path = "android.resource://" + Objects.requireNonNull(getActivity()).getPackageName() + "/" + R.raw.videohigh;
//        view.setVideoURI(Uri.parse(path));
//        view.setMediaController(mc);
//
//        return v;
//    }
//
//    void init(View v){
//        btn1 = (Button) v.findViewById(R.id.btnSkip);
//    }
//}

public class Walkthrough extends AppCompatActivity {

    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_walkthrough);

        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Walkthrough.this,Camera.class);
                startActivity(intent);
            }
        });

        MediaController mc= new MediaController(this);
        VideoView view = (VideoView) findViewById(R.id.videoview);
        String path = "android.resource://" + Objects.requireNonNull(Walkthrough.this).getPackageName() + "/" + R.raw.videohigh;
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mc);

    }

    void init(){
        btn1 = (Button) findViewById(R.id.btnSkip);
    }
}
