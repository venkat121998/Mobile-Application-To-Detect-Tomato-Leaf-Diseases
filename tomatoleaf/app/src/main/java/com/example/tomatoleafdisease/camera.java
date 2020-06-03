package com.example.tomatoleafdisease;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;


public class camera extends AppCompatActivity {
    //function added here for back navigation to work in toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Toolbar toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String Predicted_Value = i.getStringExtra("prediction");

        //converting byte array into bitmap image
        byte[] byteArray = i.getByteArrayExtra("capture_img");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        //setting the captured image
        ImageView leaf_img = (ImageView) findViewById(R.id.leafimg) ;
        leaf_img.setImageBitmap(bitmap);

        //setting the predicted value
        TextView prediction_label = (TextView) findViewById(R.id.prediction_label);
        prediction_label.setText(Predicted_Value);

        getSupportActionBar().setTitle("");

        //get instance of medicine name
        TextView med_name = (TextView)findViewById(R.id.medicine_name);
        ImageView instruction = (ImageView)findViewById(R.id.todo);
        int resid;
        String medicine = null;
        switch(Predicted_Value){
            case "Bacterial Spot":
                medicine = "Mancozeb 75.0% WP";
                resid = R.drawable.bs;
                break;
            case "Early Blight":
                medicine = "Pyraclostrobin 20.0% WG";
                resid = R.drawable.eb;


                break;
            case "Late Blight":
                medicine = "Azoxystrobin 23.0% sc";
                resid = R.drawable.lb;


                break;
            case "Leaf Mold":
                medicine = "Magnesium sulphate(MgSO4)";
                resid = R.drawable.lm;

                break;
            case "Septoria Leaf Spot":
                medicine = "Pyraclostrobin 20.0% WG";
                resid = R.drawable.ss;

                break;
            case "Two Spotted Spider Mite":
                medicine = "Anamection 1.8% EC";
                resid = R.drawable.sm;

                break;
            case "Target Spot":
                medicine = "Metalaxyl-M 3.3% SC";
                resid = R.drawable.ts;

                break;
            case "Yellow Leaf Curl Virus":
                medicine = "Cyantraniliprole 10.26% OD";
                resid = R.drawable.ylc;

                break;
            case "Mosaic Virus":
                medicine = "Urea , NPK, Ammonium nitrate";
                resid = R.drawable.ms;

                break;
            default:
                TextView v = (TextView)findViewById(R.id.disease_label);
                v.setText("Congratulations");
                v.setTextColor(getResources().getColor(R.color.success));
                v = (TextView)findViewById(R.id.prediction_label);
                v.setText("Your Leaf Is Healthy ");
                v.setTextColor(getResources().getColor(R.color.success));
                v = (TextView)findViewById(R.id.medicine_label);
                v.setText("");
                v = (TextView)findViewById(R.id.medicine_name);
                v.setText("");
                v = (TextView)findViewById(R.id.instructions);
                v.setText("");
                resid = R.drawable.ic_search_black_24dp;
                break;

        }

        //setting the varibles to actual values
        med_name.setText(medicine);
        instruction.setImageResource(resid);



       /* final CollapsingToolbarLayout collapsing=findViewById(R.id.collapsing);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.icon);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                if(palette!=null)
                    collapsing.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
            }
        });*/



    }
    /*public ImageView image1;

    public final int REQEST_IMAGE_CAPTURE=1, REQUEST_IMAGE_GALLERY=2;
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

       // image1 = (ImageView) findViewById(R.id.image1);



    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode==REQEST_IMAGE_CAPTURE){
                Bitmap bitmap=(Bitmap)data.getExtras().get("data");
                image1.setImageBitmap(bitmap);
            }else if(requestCode== REQUEST_IMAGE_GALLERY){
                Uri uri=data.getData();
                Bitmap bitmap=null;
                try{
                    bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    image1.setImageBitmap(bitmap);
                }catch(IOException e){
                    e.printStackTrace();
                }

            }
        }
    }*/
}
