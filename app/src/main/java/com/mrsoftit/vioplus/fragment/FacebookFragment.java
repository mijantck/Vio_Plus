package com.mrsoftit.vioplus.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrsoftit.vioplus.R;
import com.mrsoftit.vioplus.facebookfragment.FbAddFragment;
import com.mrsoftit.vioplus.facebookfragment.FbLikeFragment;
import com.mrsoftit.vioplus.facebookfragment.FbPageLikeFragment;
import com.mrsoftit.vioplus.facebookfragment.FbViewFragment;
import com.zagori.bottomnavbar.BottomNavBar;

public class FacebookFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FacebookFragment() {
        // Required empty public constructor
    }
    public static FacebookFragment newInstance(String param1, String param2) {
        FacebookFragment fragment = new FacebookFragment();
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

    private BottomNavBar.OnBottomNavigationListener mOnBottomNavItemSelectedListener_fb =
            new BottomNavBar.OnBottomNavigationListener() {
                Fragment selectedFragment = null;

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.all_view:
                            selectedFragment = new FbViewFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_fb,
                                    selectedFragment).commit();

                            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.all_like:
                            selectedFragment = new FbLikeFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_fb,
                                    selectedFragment).commit();
                            return true;
                        case R.id.all_add:
                            selectedFragment = new FbAddFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_fb,
                                    selectedFragment).commit();
                            return true;
                        case R.id.all_coin:
                            selectedFragment = new EarnCoinFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_fb,
                                    selectedFragment).commit();
                            return true;
                        case R.id.page_like:
                            selectedFragment = new FbPageLikeFragment();
                            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_fb,
                                    selectedFragment).commit();
                            return true;
                    }

                    return false;
                }
            };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook, container, false);

        BottomNavBar bottomNavView = view.findViewById(R.id.bottom_nav_view_fb);
        bottomNavView.setBottomNavigationListener(mOnBottomNavItemSelectedListener_fb);
//I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_fb,
                    new FbViewFragment()).commit();
        }

        return view;
    }
}