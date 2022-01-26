package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShowVTONResults extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button btn1;
    private  ImageView img1, img2;
    private TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_v_t_o_n_results);

        btn1 = (Button) findViewById(R.id.btndone);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBarResult);

        img1 = (ImageView) findViewById(R.id.imgclothshow);
        img1.setVisibility(View.INVISIBLE);

        img2 = (ImageView) findViewById(R.id.imgpersonshow);
        img2.setVisibility(View.INVISIBLE);

        txt1 = (TextView) findViewById(R.id.text1);
        txt1.setVisibility(View.INVISIBLE);

        String cloth = getIntent().getStringExtra("Cloth Name");
        String person = getIntent().getStringExtra("Person Name");

        
        connectServer1(cloth);
        connectServer2(person);
        connectServer(cloth, person);
    }
    void connectServer(String cloth, String person){
        String postUrl= "http://192.168.1.8:5000";
        String postBodyText=cloth + " " + person;
        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        RequestBody postBody = RequestBody.create(mediaType, postBodyText);

        postRequest(postUrl, postBody);
    }

    void postRequest(String postUrl, RequestBody postBody) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//
                        Toast.makeText(ShowVTONResults.this,"Failed to Connect to Server",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int SDK_INT = android.os.Build.VERSION.SDK_INT;
                        if (SDK_INT > 8)
                        {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                    .permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            //your codes here
                            final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                            ImageView returnImage = findViewById(R.id.setimage);
                            returnImage.setImageBitmap(bitmap);
//                            img1.setVisibility(View.VISIBLE);
//                            img2.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            txt1.setVisibility(View.VISIBLE);
                            btn1.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        });
    }

    void connectServer1(String cloth){
        String postUrl= "http://192.168.1.8:5000/cloth";
        String postBodyText=cloth;
        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        RequestBody postBody = RequestBody.create(mediaType, postBodyText);

        postRequest1(postUrl, postBody);
    }

    void postRequest1(String postUrl, RequestBody postBody) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//
                        Toast.makeText(ShowVTONResults.this,"Failed to Connect to Server",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int SDK_INT = android.os.Build.VERSION.SDK_INT;
                        if (SDK_INT > 8)
                        {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                    .permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            //your codes here
                            final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                            img1.setImageBitmap(bitmap);

                        }

                    }
                });
            }
        });
    }

    void connectServer2(String person){
        String postUrl= "http://192.168.1.8:5000/person";
        String postBodyText=person;
        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        RequestBody postBody = RequestBody.create(mediaType, postBodyText);

        postRequest2(postUrl, postBody);
    }

    void postRequest2(String postUrl, RequestBody postBody) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//
                        Toast.makeText(ShowVTONResults.this,"Failed to Connect to Server",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int SDK_INT = android.os.Build.VERSION.SDK_INT;
                        if (SDK_INT > 8)
                        {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                    .permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            //your codes here
                            final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                            img2.setImageBitmap(bitmap);

                        }

                    }
                });
            }
        });
    }
}