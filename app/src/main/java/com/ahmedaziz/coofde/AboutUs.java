package com.ahmedaziz.coofde;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akhooo.coofde.R;


/**
 * Created by Ahmed Aziz on 9/3/2017.
 */

public class AboutUs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About Us");

    }
}
