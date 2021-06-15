package com.mrsoftit.vioplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.mrsoftit.vioplus.R;
import com.mrsoftit.vioplus.fragment.AddFragment;
import com.mrsoftit.vioplus.youtubefragment.YoutubeCampaignFragment;
import com.mrsoftit.vioplus.youtubefragment.LikeFragment;
import com.mrsoftit.vioplus.youtubefragment.SubscriberFragment;
import com.mrsoftit.vioplus.youtubefragment.ViewFragment;
import com.zagori.bottomnavbar.BottomNavBar;

public class YoutubeActivity extends AppCompatActivity {


    private BottomNavBar.OnBottomNavigationListener mOnBottomNavItemSelectedListener =
            new BottomNavBar.OnBottomNavigationListener() {
                Fragment selectedFragment = null;

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.youtube_view:
                            selectedFragment = new ViewFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();

                            Toast.makeText(YoutubeActivity.this, "click", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.youtube_like:
                            selectedFragment = new SubscriberFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                        case R.id.youtube_add:
                            selectedFragment = new AddFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                        case R.id.youtube_coin:
                            selectedFragment = new LikeFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                        case R.id.youtube_sub:
                            selectedFragment = new YoutubeCampaignFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                    }

                    return false;
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        BottomNavBar bottomNavView = findViewById(R.id.bottom_nav_view);
        bottomNavView.setBottomNavigationListener(mOnBottomNavItemSelectedListener);
//I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ViewFragment()).commit();
        }
    }
}