package com.mrsoftit.vioplus.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrsoftit.vioplus.R;
import com.mrsoftit.vioplus.youtubefragment.LikeFragment;
import com.mrsoftit.vioplus.youtubefragment.SubscriberFragment;
import com.mrsoftit.vioplus.youtubefragment.ViewFragment;
import com.mrsoftit.vioplus.youtubefragment.YoutubeCampaignFragment;
import com.zagori.bottomnavbar.BottomNavBar;

public class YoutubeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public YoutubeFragment() {
        // Required empty public constructor
    }
    public static YoutubeFragment newInstance(String param1, String param2) {
        YoutubeFragment fragment = new YoutubeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private BottomNavBar.OnBottomNavigationListener mOnBottomNavItemSelectedListener =
            new BottomNavBar.OnBottomNavigationListener() {
                Fragment selectedFragment = null;

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.youtube_view:
                            selectedFragment = new ViewFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();

                            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.youtube_like:
                            selectedFragment = new LikeFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                        case R.id.youtube_add:
                            selectedFragment = new YoutubeCampaignFragment(getContext());
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            Toast.makeText(getContext(), "adddd", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.youtube_coin:
                            selectedFragment = new EarnCoinFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                        case R.id.youtube_sub:
                            selectedFragment = new SubscriberFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                    }

                    return false;
                }
            };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_youtube, container, false);

        BottomNavBar bottomNavView = view.findViewById(R.id.bottom_nav_view);
        bottomNavView.setBottomNavigationListener(mOnBottomNavItemSelectedListener);
//I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ViewFragment()).commit();
        }
        return view;
    }
}