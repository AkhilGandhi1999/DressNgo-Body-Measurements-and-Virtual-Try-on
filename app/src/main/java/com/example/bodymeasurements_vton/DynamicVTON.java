package com.example.bodymeasurements_vton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DynamicVTON extends AppCompatActivity {

    // One Button
    Button BSelectImage;
    Button button;
    String picturePath;
    private ImageView imageview;
    LinearLayout instructions;
    private TextView mTextView;
    File fname;
    byte[] byteArray;
    // One Preview Image

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_v_t_o_n);
        mTextView = findViewById(R.id.textView2);
        imageview = findViewById(R.id.imageview);
        BSelectImage = findViewById(R.id.BSelectImage);
        instructions = findViewById(R.id.instructions);
        button =findViewById(R.id.button);

        String cloth = getIntent().getStringExtra("cloth_image_id1");
        //Toast.makeText(DynamicVTON.this,cloth,Toast.LENGTH_SHORT).show();
        // register the UI widgets with their appropriate IDs
        //IVPreviewImage = findViewById(R.id.IVPreviewImage);

        // handle the Choose Image button to trigger
        // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(DynamicVTON.this);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postUrl = "http://192.168.1.8:5000/upload";

                MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                multipartBodyBuilder.addFormDataPart("image" + 0, "person_" + 0 + ".jpg", RequestBody.create(MediaType.parse("image/*jpg"), byteArray));
                RequestBody postBodyImage = multipartBodyBuilder.build();
                postRequest(postUrl, postBodyImage);
               // Toast.makeText(getApplicationContext(),fname.getAbsolutePath(),Toast.LENGTH_LONG).show();

                String personimage = "person_0.jpg";
                Intent results = new Intent(DynamicVTON.this,ShowVTONResults.class);
                results.putExtra("Cloth Name",cloth);
                results.putExtra("Person Name", personimage);
                startActivity(results);
                finish();

            }
        });
    }


    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Upload from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Upload from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    // this function is triggered when user
    // selects the image from the imageChooser

    @Override
    public void onBackPressed() {

        if(imageview.getVisibility()==View.VISIBLE){
            imageview.setVisibility(View.INVISIBLE);
            instructions.setVisibility(View.VISIBLE);
        }
        else
            finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byteArray = stream.toByteArray();
                        fname= new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), "IMAGE.jpeg");
                        OutputStream output = null;
                        try {
                            output = new FileOutputStream(fname);
                            output.write(byteArray);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (null != output) {
                                try {
                                    output.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        new Handler().postDelayed(()->{
                            instructions.setVisibility(View.INVISIBLE);
                            imageview.setImageBitmap(selectedImage);
                            imageview.setVisibility(View.VISIBLE);
                        },1000);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {


                        Uri selectedImage = data.getData();
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                        byteArray = stream.toByteArray();


                        fname=new File(selectedImage.getPath());
                        if (selectedImage != null) {
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                picturePath = cursor.getString(columnIndex);
                                fname=new File(picturePath);
                                cursor.close();
                            }
                            instructions.setVisibility(View.INVISIBLE);
                            imageview.setImageURI(selectedImage);
                            imageview.setVisibility(View.VISIBLE);
                        }

                    }
                    break;
            }
        }
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
                Log.d("FAIL", e.getMessage());

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // TextView responseText = findViewById(R.id.responseText);
                        //responseText.setText("Failed to Connect to Server. Please Try Again.");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // TextView responseText = findViewById(R.id.responseText);
                        // responseText.setText("Server's Response\n" + response.body().string());
                        Toast.makeText(DynamicVTON.this,response.body().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}