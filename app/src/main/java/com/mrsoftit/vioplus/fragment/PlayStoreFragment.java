package com.mrsoftit.vioplus.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrsoftit.vioplus.R;
import com.mrsoftit.vioplus.playstore.AddAppFragment;
import com.mrsoftit.vioplus.playstore.AppInstallFragment;
import com.mrsoftit.vioplus.playstore.ReviewFragment;
import com.zagori.bottomnavbar.BottomNavBar;

public class PlayStoreFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PlayStoreFragment() {
        // Required empty public constructor
    }

    public static PlayStoreFragment newInstance(String param1, String param2) {
        PlayStoreFragment fragment = new PlayStoreFragment();
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


    private BottomNavBar.OnBottomNavigationListener mOnBottomNavItemSelectedListener_app =
            new BottomNavBar.OnBottomNavigationListener() {
                Fragment selectedFragment = null;

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.app_install:
                            selectedFragment = new AppInstallFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_app,
                                    selectedFragment).commit();

                            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.app_add:
                            selectedFragment = new AddAppFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_app,
                                    selectedFragment).commit();
                            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.app_review:
                            selectedFragment = new ReviewFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_app,
                                    selectedFragment).commit();
                            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                            return true;
                    }

                    return false;
                }
            };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_playstore, container, false);

        BottomNavBar bottomNavView = view.findViewById(R.id.bottom_nav_view_app);
        bottomNavView.setBottomNavigationListener(mOnBottomNavItemSelectedListener_app);
//I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_app,
                    new AppInstallFragment()).commit();
        }





        return view;
    }
}