package com.mrsoftit.vioplus.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.View;

import com.mrsoftit.vioplus.databinding.ActivityHomeBinding;
import com.mrsoftit.vioplus.fragment.FacebookFragment;
import com.mrsoftit.vioplus.fragment.InstagramFragment;
import com.mrsoftit.vioplus.fragment.PlayStoreFragment;
import com.mrsoftit.vioplus.fragment.TwFragment;
import com.mrsoftit.vioplus.fragment.WebFragment;
import com.mrsoftit.vioplus.fragment.YoutubeFragment;
import com.mrsoftit.vioplus.youtubefragment.ViewFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding binding;

   // private String[] titles = new String[]{"YT", "FB", "IG", "PS", "TW", "WB"};
    private String[] titles = new String[]{"YT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();

    }

    private void init() {
        // removing toolbar elevation
        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));
        // attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();
        binding.viewPager.setOffscreenPageLimit(5);
    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new YoutubeFragment();
//                case 1:
//                    return new FacebookFragment();
//                case 2:
//                    return new InstagramFragment();
//                case 3:
//                    return new PlayStoreFragment();
//                case 4:
//                    return new TwFragment();
//                case 5:
//                    return new WebFragment();
            }
            return new ViewFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

    }

}