package com.example.tomatoleafdisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //splash
        new Handler().postDelayed(new Runnable() {

            public void run() {

                Intent i=new Intent(SplashScreen.this,login.class);
                startActivity(i);
                finish();
            }
        },6000);




    }
}
