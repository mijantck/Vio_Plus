package com.mrsoftit.vioplus.playstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrsoftit.vioplus.R;
import com.squareup.picasso.Picasso;

public class AppInfoActivity extends AppCompatActivity {


    ImageView app_logo;
    TextView app_name;
    String appLogoUrl;
    String appName;
    String fullUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

        app_logo = findViewById(R.id.app_logo);
        app_name = findViewById(R.id.app_name);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appLogoUrl = extras.getString("appImageUrl");
            appName = extras.getString("appName");
            fullUrl = extras.getString("fullUrl");
            //The key argument here must match that used in the other activity
        }
        Picasso.get()
                .load(appLogoUrl)
                .into(app_logo);
        app_name.setText(appName);


    }
}