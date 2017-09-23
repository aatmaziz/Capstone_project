package com.ahmedaziz.coofde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

import com.akhooo.coofde.R;

/**
 * Created by Ahmed Aziz on 9/3/2017.
 */

public class splash extends AppCompatActivity {
    private static int SPLASH_TimeOut = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TimeOut);

    }
}

