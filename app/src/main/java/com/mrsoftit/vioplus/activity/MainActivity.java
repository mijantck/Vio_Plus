package com.mrsoftit.vioplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.mrsoftit.vioplus.R;

public class MainActivity extends AppCompatActivity {

    CardView yotube;
    CardView facebook;
    CardView instragam;
    CardView playstore;
    CardView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yotube = findViewById(R.id.youtubeButton);
        facebook = findViewById(R.id.facebookButton);
        instragam = findViewById(R.id.instagramButton);
        playstore = findViewById(R.id.playstoreButton);
        web = findViewById(R.id.webButton);

        yotube.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, YoutubeActivity.class)));


    }
}