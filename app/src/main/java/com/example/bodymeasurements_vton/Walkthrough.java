package com.example.bodymeasurements_vton;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Objects;
import java.util.Vector;

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

public class Walkthrough extends YouTubeBaseActivity{
    //RecyclerView recyclerView;
    public static final String api_key = "AIzaSyDZylaRp7gAoIErPL2fIuLJfOX1H_BvonE";
    //Vector<youTubeVideos> youtubeVideos = new Vector<>();
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_walkthrough);

        init();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Toast.makeText(this,"Start again, Error in the images uploaded",Toast.LENGTH_LONG).show();

        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Walkthrough.this,Camera.class);
                startActivity(intent);
                finish();
            }
        });

        YouTubePlayerView ytPlayer = (YouTubePlayerView)findViewById(R.id.ytPlayer);

        ytPlayer.initialize(
                api_key,
                new YouTubePlayer.OnInitializedListener() {
                    // Implement two methods by clicking on red
                    // error bulb inside onInitializationSuccess
                    // method add the video link or the playlist
                    // link that you want to play In here we
                    // also handle the play and pause
                    // functionality
                    @Override
                    public void onInitializationSuccess(

                            YouTubePlayer.Provider provider,
                            YouTubePlayer youTubePlayer, boolean b)
                    {
                        youTubePlayer.loadVideo("sIZUEwIif0k");
                        youTubePlayer.play();
                    }

                    // Inside onInitializationFailure
                    // implement the failure functionality
                    // Here we will show toast
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult
                                                                youTubeInitializationResult)
                    {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    void init(){
        btn1 = (Button) findViewById(R.id.btnSkip);
    }
}
