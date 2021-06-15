package com.mrsoftit.vioplus.facebookfragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrsoftit.vioplus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FbViewFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FbViewFragment() {
        // Required empty public constructor
    }


    public static FbViewFragment newInstance(String param1, String param2) {
        FbViewFragment fragment = new FbViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ImageView imageView1;
    TextView textView;
    String imageUlLike;
    String textLike;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fb_view, container, false);

        imageView1 = view.findViewById(R.id.likeviewImagefb);
        textView = view.findViewById(R.id.likeviewNamefb);

      /*  FbView fbView = new FbView();


        try {
            String ss = fbView.execute().get();
            String string = ss;
            String[] parts = string.split("#");
            String part1 = parts[0]; // 004
            String part2 = parts[1];
            imageUlLike = part1;
            textLike = part2;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Picasso.get()
                .load(imageUlLike)
                .into(imageView1);
        textView.setText(textLike);*/
        return view;
    }


    private class FbView extends AsyncTask<Void, String, String> {


        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            String name = null;
            String imageUrl = null;

            try {
                Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id=shareit.lite")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                        .get();

                Elements metaOgTitle = doc.select("meta[property=og:title]");
                if (metaOgTitle != null) {
                    name = metaOgTitle.attr("content");
                }

                Elements metaOgImage = doc.select("meta[property=og:image]");
                if (metaOgImage != null) {
                    imageUrl = metaOgImage.attr("content");
                    Log.d("dfgdh", imageUrl);
                }
                return  imageUrl+"#"+name ;
            } catch (Exception e) {
                return newVersion;
            }
        }
    }

}