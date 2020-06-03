package com.example.tomatoleafdisease;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper flip;
    Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("APP DEMO");

        int images[]={R.drawable.one,R.drawable.two,R.drawable.th};
        flip=findViewById(R.id.flip);

        for(int i=0;i<3;i++){
            flipperimg(images[i]);

        }

        }

        public void flipperimg(int image){

            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(image);

            flip.addView(imageView);
            flip.setFlipInterval(4000);
            flip.setAutoStart(true);

            flip.setInAnimation(this,android.R.anim.slide_in_left);
            flip.setOutAnimation(this,android.R.anim.slide_out_right);



        }

    public void b3(View view) {
        startActivity(new Intent(getApplicationContext(),home.class));
    }



}

