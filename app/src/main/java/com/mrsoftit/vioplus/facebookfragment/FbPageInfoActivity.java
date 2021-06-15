package com.mrsoftit.vioplus.facebookfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrsoftit.vioplus.R;
import com.squareup.picasso.Picasso;

public class FbPageInfoActivity extends AppCompatActivity {


    String imageUrl;
    String Name;
    String fullUrl;

    ImageView fb_page_logo;
    TextView fb_page_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_page_info);

        fb_page_name = findViewById(R.id.fb_page_name);
        fb_page_logo = findViewById(R.id.fb_page_logo);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            imageUrl = extras.getString("imageUrl");
            Name = extras.getString("name");
            fullUrl = extras.getString("fullUrl");
            //The key argument here must match that used in the other activity
        }
        Picasso.get()
                .load(imageUrl)
                .into(fb_page_logo);
        fb_page_name.setText(Name);



    }
}