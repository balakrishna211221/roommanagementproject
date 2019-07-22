package com.example.roommanagementproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {
    ProgressBar progressBar;

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
progressBar=findViewById(R.id.splash_screen_progress_bar);
progressBar.setProgress(3000);

       SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String phoneloged=sharedPreferences.getString("mobile",null);
        String passloged=       sharedPreferences.getString("pass",null);
        if(phoneloged!=null&&passloged!=null)
        {


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setProgress(3000);

                Intent i = new Intent(Splash.this,ProfilePage.class);
                startActivity(i);
                finish();

            }

    }, SPLASH_TIME_OUT);
    }
    else {
        Intent intent=new Intent(Splash.this,MainActivity.class);
        startActivity(intent);
        }

    }
}
