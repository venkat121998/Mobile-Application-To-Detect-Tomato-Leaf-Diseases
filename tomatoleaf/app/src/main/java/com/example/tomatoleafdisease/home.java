package com.example.tomatoleafdisease;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class home extends  AppCompatActivity implements View.OnClickListener {

    public ImageView cam, gal;
    public String Prediction;
    public Bitmap bitmap;
    byte[] byteArray;


    public final int REQEST_IMAGE_CAPTURE = 1, REQUEST_IMAGE_GALLERY = 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.ins:
                startActivity(new Intent(getApplicationContext(), instruction.class));
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cam = (ImageView) findViewById(R.id.cam);
        cam.setOnClickListener(this);
        gal = (ImageView) findViewById(R.id.gal);
        gal.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(home.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(home.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }


    }

    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.cam:
                Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (icamera.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(icamera, REQEST_IMAGE_CAPTURE);
                }
                break;

            case R.id.gal:
                Intent igallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                igallery.setType("image/*");
                startActivityForResult(igallery, REQUEST_IMAGE_GALLERY);
                break;

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode==REQEST_IMAGE_CAPTURE){
                Bitmap bitmap=(Bitmap)data.getExtras().get("data");
                //Making the REST API CALL HERE
                String ipv4Address = "192.168.0.103";
                String portNumber = "5000";
                String postUrl = "http://" + ipv4Address + ":" + portNumber + "/" + "api/v02/image";

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                }catch(Exception e){
                    e.printStackTrace();
                }
                byteArray = stream.toByteArray();

                RequestBody postBodyImage = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image", "androidFlask.jpg", RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                        .build();
                postRequest(postUrl, postBodyImage);

                //image1.setImageBitmap(bitmap);
            }else if(requestCode== REQUEST_IMAGE_GALLERY){
                Uri uri=data.getData();
                bitmap = null;
                try{
                    bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    //Making the REST API CALL HERE
                    String ipv4Address = "192.168.0.103";
                    String portNumber = "5000";
                    String postUrl = "http://" + ipv4Address + ":" + portNumber + "/" + "api/v02/image";

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    byteArray = stream.toByteArray();

                    RequestBody postBodyImage = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("image", "androidFlask.jpg", RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                            .build();
                    postRequest(postUrl, postBodyImage);
                    //image1.setImageBitmap(bitmap);
                }catch(IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

    private void postRequest(String postUrl, RequestBody postBody) {
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
//                        TextView responseText = findViewById(R.id.responseText);
//                        responseText.setText("Failed to Connect to Server. Please Try Again.");
                        Log.d("Error", "Could'nt connect to server");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //TextView responseText = findViewById(R.id.responseText);
                        try {
                            Prediction = response.body().string();
                            Log.d("Predicted value",Prediction);
                            // to the open new activity that shows prediction and remedy
                            ShowRemedyAndPredictions();
                            //responseText.setText(Prediction);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public void ShowRemedyAndPredictions(){
        Intent i = new Intent(getApplicationContext(),camera.class);
        String key = "prediction";
        i.putExtra(key,Prediction);
        i.putExtra("capture_img",byteArray);
        startActivity(i);


    }
}
