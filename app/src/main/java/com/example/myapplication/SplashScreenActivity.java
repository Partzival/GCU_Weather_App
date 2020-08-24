package com.example.myapplication;
/*
RYAN DUFFY - S1826064
 */
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withBeforeLogoText("GCU WEATHER APP")
                .withFooterText("Developed Ryan Duffy")
                .withLogo(R.drawable.gcu300x300);

        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextSize(30);
        config.getFooterTextView().setTextColor(Color.WHITE);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
