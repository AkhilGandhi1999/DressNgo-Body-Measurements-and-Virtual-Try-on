package com.example.bodymeasurements_vton;
// Version 2

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.ExifInterface;
import android.media.MediaActionSound;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amazonaws.ClientConfiguration;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.api.rest.RestResponse;
import com.amplifyframework.core.Amplify;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class Camera extends AppCompatActivity implements SensorEventListener{
    private static final String TAG = "AndroidCameraApi";
    private SensorManager sensorManager;
    private FloatingActionButton takePictureButton;
    private FloatingActionButton retakeButton;
    private FloatingActionButton saveButton;
    public SimpleDateFormat mDateFormat;
    private TextureView textureView;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }
    private String cameraId;
    private int flag=0;
    protected CameraDevice cameraDevice;
    protected CameraCaptureSession cameraCaptureSessions;
    protected CaptureRequest captureRequest;
    protected CaptureRequest.Builder captureRequestBuilder;
    private Size imageDimension;
    private ImageReader imageReader;
    private int temp=0;
    private File file;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private boolean mFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    public ImageView f,r,fw,rw,pre;
    private Sensor AccelerometerSensor;
    private LinearLayout l;
    private String filename;
    public String fname;
    private int currentSensor;
    private ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        AccelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (checkSensorAvailability(Sensor.TYPE_ACCELEROMETER)) {
            currentSensor = Sensor.TYPE_ACCELEROMETER;
        } else {
            Toast.makeText(Camera.this,"Gyroscope Sensor not available",Toast.LENGTH_LONG).show();
        }
        p = (ProgressBar) findViewById(R.id.progressBar2);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        textureView = (TextureView) findViewById(R.id.texture);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);
        retakeButton = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        takePictureButton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        saveButton = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        assert takePictureButton != null;
        f=findViewById(R.id.front_correct);
        r=findViewById(R.id.right_correct);
        fw=findViewById(R.id.front_wrong);
        rw=findViewById(R.id.right_wrong);
        pre=findViewById(R.id.preview);
        textureView=findViewById(R.id.texture);
        MediaActionSound sound = new MediaActionSound();
        l=findViewById(R.id.take);
        //CAPTURE BUTTON
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.play(MediaActionSound.SHUTTER_CLICK);
                flag=1;
                takePicture();
                new Handler().postDelayed(() -> {
                    textureView.setVisibility(View.INVISIBLE);
                    l.setVisibility(View.INVISIBLE);
                    fw.setVisibility(View.INVISIBLE);
                    f.setVisibility(View.INVISIBLE);
                    fname=new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), filename+ ".jpeg").getAbsolutePath();
                  //  Toast.makeText(Camera.this,fname,Toast.LENGTH_LONG).show();
                    Bitmap img = BitmapFactory.decodeFile(fname);
                    ExifInterface ei = null;
                    try {
                        ei = new ExifInterface(fname);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int orientation = 0;
                    try {
                        orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                    }catch (NullPointerException n){
                        orientation = 0;
                    }
                    Bitmap bmp=null;
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            bmp=rotateImage(img, 90);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            bmp=rotateImage(img, 180);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            bmp=rotateImage(img, 270);
                            break;
                        case ExifInterface.ORIENTATION_NORMAL:
                            bmp = img;
                            break;
                        default:
                            bmp=img;
                            break;
                    }
                    pre.setImageBitmap(bmp);
                    pre.setVisibility(View.VISIBLE);
                    retakeButton.setVisibility(View.VISIBLE);
                    saveButton.setVisibility(View.VISIBLE);
                }, 1000);

            }
        });
        //RETAKE BUTTON
        retakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                pre.setVisibility(View.INVISIBLE);
                textureView.setVisibility(View.VISIBLE);
                retakeButton.setVisibility(View.INVISIBLE);
                saveButton.setVisibility(View.INVISIBLE);
                l.setVisibility(View.VISIBLE);
                if(temp==0)
                {
                    r.setVisibility(View.INVISIBLE);
                    rw.setVisibility(View.INVISIBLE);
                }
                else
                {
                    f.setVisibility(View.INVISIBLE);
                    fw.setVisibility(View.INVISIBLE);
                }
            }
        });
        //SAVE BUTTON
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(temp==1)
                {
                    //LINKING WOULD BEGIN OVER HERE//
                    File file1 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), "FRONT.jpeg");
                    File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), "RIGHT.jpeg");

                    server_call(file1, file2);
                   // Toast.makeText(Camera.this,"lets move ahead",Toast.LENGTH_LONG).show();
                }
                else
                {
                    flag=0;
                    temp=1;
                    pre.setVisibility(View.INVISIBLE);
                    textureView.setVisibility(View.VISIBLE);
                    retakeButton.setVisibility(View.INVISIBLE);
                    saveButton.setVisibility(View.INVISIBLE);
                    l.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    //FUNCTION FOR ROTATING THE IMAGE BITMAP
    public static Bitmap rotateImage(Bitmap im, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(im, 0, 0, im.getWidth(), im.getHeight(), matrix, true);
        im.recycle();
        return rotatedImg;
    }
    //USED TO CHECK WHETHER THE GYROSCOPE SENSOR IS ENABLED OR NOT
    public boolean checkSensorAvailability(int sensorType) {
        boolean isSensor = false;
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            isSensor = true;
        }
        return isSensor;
    }
    //WORKS ON REAL-TIME BASIS AND IS TRIGGERED ACCORDINGLY
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(flag==1)
        {
            r.setVisibility(View.INVISIBLE);
            rw.setVisibility(View.INVISIBLE);
            fw.setVisibility(View.INVISIBLE);
            f.setVisibility(View.INVISIBLE);
            return;
        }
        if (event.sensor.getType() == currentSensor) {
            Log.e(TAG,"In OnSensorChanged");
            if (currentSensor == Sensor.TYPE_ACCELEROMETER) {
                float[] g = new float[3];
                g = event.values.clone();

                double norm_Of_g = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);

                /* Normalize the accelerometer vector */
                g[0] = (float) (g[0] / norm_Of_g);
                g[1] = (float) (g[1] / norm_Of_g);
                g[2] = (float) (g[2] / norm_Of_g);
                int inclination = (int) Math.round(Math.toDegrees(Math.acos(g[2])));
                int inclination_x=(int) Math.round(Math.toDegrees(Math.acos(g[0])));

                if(temp==1)
                {

                    Log.e("Accelerometer", String.valueOf(inclination_x));
                    if (inclination>=77 && inclination<=95 && inclination_x>=77 && inclination_x<=95) {
                        rw.setVisibility(View.INVISIBLE);
                        r.setVisibility(View.VISIBLE);
                        takePictureButton.setEnabled(true);
                        // Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG);
                    } else {
                        r.setVisibility(View.INVISIBLE);
                        rw.setVisibility(View.VISIBLE);
                        takePictureButton.setEnabled(false);
                        //Toast.makeText(MainActivity.this, "Please hold the camera steady and perpendicular to the surface", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Log.e("Accelerometer", String.valueOf(inclination_x));
                    if (inclination>=77 && inclination<=95 && inclination_x>=77 && inclination_x<=95) {
                        fw.setVisibility(View.INVISIBLE);
                        f.setVisibility(View.VISIBLE);
                        takePictureButton.setEnabled(true);
                        //Toast.makeText(MainActivity.this, "Anticlock", Toast.LENGTH_LONG);
                    } else {
                        f.setVisibility(View.INVISIBLE);
                        fw.setVisibility(View.VISIBLE);
                        takePictureButton.setEnabled(false);
                        //Toast.makeText(MainActivity.this, "Please hold the camera steady and perpendicular to the surface", Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, "Clock", Toast.LENGTH_LONG);
                    }
                }
            }

        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //open your camera here
            openCamera();
        }
        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            // Transform you image captured size according to the surface width and height
        }
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            Log.e(TAG, "onSurfaceTextureDestroyed");
            if(cameraDevice != null){
                closeCamera();

                cameraDevice = null;
            }
            return false;
        }
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    };
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            //This is called when the camera is open
            Log.e(TAG, "onOpened");
            cameraDevice = camera;
            createCameraPreview();
        }
        @Override
        public void onDisconnected(CameraDevice camera) {
            cameraDevice.close();
        }
        @Override
        public void onError(CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice = null;
        }
    };
    final CameraCaptureSession.CaptureCallback captureCallbackListener = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
          //  Toast.makeText(Camera.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
            createCameraPreview();
        }
    };
    protected void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }
    protected void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //TRIGGERED A SOON AS THE CAPTURE BUTTON IS CLICKED
    protected void takePicture() {
        if(null == cameraDevice) {
            Log.e(TAG, "cameraDevice is null");
            return;
        }
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes = null;
            if (characteristics != null) {
                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
            }
            int width = 640;
            int height = 480;
            if (jpegSizes != null && 0 < jpegSizes.length) {
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();
            }
            ImageReader reader = ImageReader.newInstance(((int) width), ((int) height), ImageFormat.JPEG, 1);
            List<Surface> outputSurfaces = new ArrayList<Surface>(2);
            outputSurfaces.add(reader.getSurface());
            outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
            final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
            // Orientation
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
            //final File file = new File(Environment.getExternalStorageDirectory()+"/pic.jpeg");
            mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            if(temp==0)
                filename="FRONT";
            else
                filename="RIGHT";
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(), filename+ ".jpeg");
            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    Image image = null;
                    try {
                        image = reader.acquireLatestImage();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];
                        buffer.get(bytes);
                        //Log.e(TAG, String.valueOf(bytes.length));
                        /*Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,null);
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bs);
                        byte[] compbytes=bs.toByteArray();
                        Bitmap compbitmap=BitmapFactory.decodeByteArray(compbytes, 0, compbytes.length);
                        pre.setImageBitmap(compbitmap);*/
                        save(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (image != null) {
                            Log.d(TAG,"Hiiiiiiii");
                        }
                    }
                }
                //CALLED IN ORDER TO SAVE THE CLICKED IMAGE ON THE END-USER'S DEVICE
                private void save(byte[] bytes) throws IOException {
                    OutputStream output = null;
                    try {
                        output = new FileOutputStream(file);
                        output.write(bytes);
                    } finally {
                        if (null != output) {
                            output.close();
                        }
                    }
                }
            };
            reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
            final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    //Toast.makeText(Camera.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
                    createCameraPreview();
                }
            };
            cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        session.capture(captureBuilder.build(), captureListener, mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    //CALLED AUTOMATICALLY AND GENERATED THE CAMERA PREVIEW SCREEN FOR CLICKING AN IMAGE
    protected void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    //The camera is already closed
                    if (null == cameraDevice) {
                        return;
                    }
                    // When the session is ready, we start displaying the preview.
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(Camera.this, "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    //TRIGGERED WHEN THE PERMISSIONS ARE MET AND THE HARDWARE IS AVAILABLE
    private void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        Log.e(TAG, "is camera open");
        try {
            cameraId = manager.getCameraIdList()[0];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            // Add permission for camera and let user grant the permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Camera.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.MANAGE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "openCamera X");
    }
    protected void updatePreview() {
        if(null == cameraDevice) {
            Log.e(TAG, "updatePreview error, return");
        }
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        try {
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    //CALLED IN ORDER TO CLOSE THE CAMERA
    private void closeCamera() {
        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }
        if (null != imageReader) {
            imageReader.close();
            imageReader = null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        startBackgroundThread();
        sensorManager.registerListener(this, AccelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        if (textureView.isAvailable()) {
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        //closeCamera();
        stopBackgroundThread();
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //FINAL FUNCTION SO AS TO PASS THE IMAGES TO THE AWS SERVER
    @RequiresApi(api = Build.VERSION_CODES.O)
    void server_call(File file1, File file2) {


        p.setVisibility(View.VISIBLE);
        String b64_1 = "",b64_2 = "";
        try{
            FileInputStream fileInputStreamReader1 = new FileInputStream(file1);
            FileInputStream fileInputStreamReader2 = new FileInputStream(file2);


            byte[] bytes1 = new byte[(int)file1.length()];
            byte[] bytes2 = new byte[(int)file2.length()];

            fileInputStreamReader1.read(bytes1);
            fileInputStreamReader2.read(bytes2);

            b64_1=  Base64.getEncoder().encodeToString(bytes1);
            b64_2=  Base64.getEncoder().encodeToString(bytes2);

            fileInputStreamReader1.close();
            fileInputStreamReader2.close();

        }
        catch(IOException e){
            System.out.println(e);
        }

        SharedPreferences sh = getSharedPreferences("User_Measurements", MODE_PRIVATE);
        String height = String.valueOf(sh.getInt("height",0));
        String jsonRequestString = "{\"height\" : \"%s\"  , \"front_image\" : \"%s\", \"right_image\" : \"%s\"}";
        String result = String.format(jsonRequestString, height, b64_1, b64_2);
        RestOptions options = RestOptions.builder()
                .addPath("/fashionm")
                .addBody(result.getBytes())
                .build();
        ClientConfiguration clientConfig = new ClientConfiguration();

        clientConfig.setSocketTimeout(20000);
        clientConfig.setConnectionTimeout(5000);

        Amplify.API.post(options,
                restResponse -> {
                    try {
                        Log.i("MyAmplifyApp", "POST succeed: " + restResponse.getData().asJSONObject());
                        check(restResponse.getData(), restResponse.getCode());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                apiFailure -> {
                    Log.e("MyAmplifyApp", "POST failed.", apiFailure);
                    Intent intent = new Intent(Camera.this,Walkthrough.class);
                    String again = "Error in the images uploaded start again, server error";
                   // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("again",again);
                    startActivity(intent);
                }
        );
        saveButton.setVisibility(View.GONE);
        retakeButton.setVisibility(View.GONE);
    }

    void check(RestResponse.Data data, RestResponse.Code code) throws JSONException {

        JSONObject jsonObject = data.asJSONObject();

        if(code.isSuccessful()){
            if(jsonObject.has("Error")){
                Log.i("MyAmplifyApp", "POST succeeded: " + jsonObject.get("Error"));
                Intent intent = new Intent(Camera.this,Walkthrough.class);
                String again = "Error in the images uploaded start again";
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("again",again);
                startActivity(intent);
            }
            else{
                Log.i("MyAmplifyApp", "POST succeeded: " + jsonObject);
                //Log.i("MyAmplifyApp", (String) jsonObject.get("Shoulder"));

                SharedPreferences sharedPreferences = getSharedPreferences("User_Measurements",MODE_PRIVATE);

                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                // Storing the key and its value as the data fetched from edittext
                Float Shoulder = Float.valueOf(jsonObject.getString("Shoulder"));
                Float Right_hand = Float.valueOf(jsonObject.getString("Right_hand"));

                Float Left_hand = Float.valueOf(jsonObject.getString("Left_hand"));
                Float Right_leg = Float.valueOf(jsonObject.getString("Right_leg"));
                Float Left_leg = Float.valueOf(jsonObject.getString("Left_leg"));
                Float Hip = Float.valueOf(jsonObject.getString("Hip"));
                Float Waist = Float.valueOf(jsonObject.getString("Waist"));
                Float Chest_girth = Float.valueOf(jsonObject.getString("Chest Girth"));
                Float Thigh_girth = Float.valueOf(jsonObject.getString("Thigh Girth"));
                Float Ankle_regular = Float.valueOf(jsonObject.getString("Ankle Regular"));
                Float Ankle_tight = Float.valueOf(jsonObject.getString("Ankle Tight"));


                myEdit.putFloat("shoulder",  Shoulder);
                myEdit.putFloat("right_hand", Right_hand );
                myEdit.putFloat("left_hand", Left_hand);
                myEdit.putFloat("right_leg", Right_leg);
                myEdit.putFloat("left_leg", Left_leg);
                myEdit.putFloat("hip", Hip);
                myEdit.putFloat("waist", Waist);
                myEdit.putFloat("chest_girth", Chest_girth);
                myEdit.putFloat("thigh_girth", Thigh_girth);
                myEdit.putFloat("ankle_regular", Ankle_regular);
                myEdit.putFloat("ankle_tight", Ankle_tight);

                myEdit.commit();

                // Update DB
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String userKey = user.getUid();

                HashMap<String,String> measurements = new HashMap<String, String>();
                measurements.put("shoulder", String.valueOf(Shoulder));
                measurements.put("right_hand", String.valueOf(Right_hand));
                measurements.put("left_hand", String.valueOf(Left_hand));
                measurements.put("right_leg", String.valueOf(Right_leg));
                measurements.put("left_leg", String.valueOf(Left_leg));
                measurements.put("hip", String.valueOf(Hip));
                measurements.put("waist", String.valueOf(Waist));
                measurements.put("chest_girth", String.valueOf(Chest_girth));
                measurements.put("thigh_girth", String.valueOf(Thigh_girth));
                measurements.put("ankle_regular", String.valueOf(Ankle_regular));
                measurements.put("ankle_tight", String.valueOf(Ankle_tight));

                databaseReference.child(userKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("measurements").setValue(measurements);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Calling the measurements activity
                String measure = "measure";
                Intent intent = new Intent(Camera.this,MainScreen.class);
                intent.putExtra("measure",measure);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            finish();

        }
//        else if(code.isServiceFailure()){
//
//        }
//        else  if(code.isClientError()){
//
//        }

    }
}