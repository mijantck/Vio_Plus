package com.mrsoftit.vioplus.facebookfragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrsoftit.vioplus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutionException;

public class FbAddFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FbAddFragment() {
        // Required empty public constructor
    }

    public static FbAddFragment newInstance(String param1, String param2) {
        FbAddFragment fragment = new FbAddFragment();
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


    EditText fb_add_edite_text;
    Button fb_link_add_text;
    String imageUrl;
    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fb_add, container, false);

        fb_link_add_text = view.findViewById(R.id.fb_link_add_text);
        fb_add_edite_text = view.findViewById(R.id.fb_add_edite_text);


        fb_link_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = fb_add_edite_text.getText().toString();
                if (url.isEmpty()){
                    Toast.makeText(getContext(), "Enter Link", Toast.LENGTH_SHORT).show();
                    return;
                }
                FbIconName fbIconName = new FbIconName(url);

                try {
                    String fullText = fbIconName.execute().get();
                    String[] parts = fullText.split("#");

                    if (parts[0] == null){

                    }else {
                        imageUrl = parts[0];
                    }
                  if (parts[1] == null){

                  }else {
                      name = parts[1];
                  }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (imageUrl == null){
                    Toast.makeText(getContext(), "Try Anather one", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent = new Intent(v.getContext(),FbPageInfoActivity.class);
                    intent.putExtra("imageUrl",imageUrl);
                    intent.putExtra("name",name);
                    intent.putExtra("fullUrl",url);
                    v.getContext().startActivity(intent);
                }
            }
        });

        return view;
    }


    private class FbIconName extends AsyncTask<Void, String, String> {

        String link;

        public FbIconName(String link) {
            this.link = link;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            String name = null;
            String imageUrl = null;

            try {
                Document doc = Jsoup.connect(link)
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                        .get();

                Elements metaOgTitle = doc.select("meta[property=og:title]");
                if (metaOgTitle != null) {
                    Log.d("fgdfdg", "doInBackground: "+metaOgTitle);
                    name = metaOgTitle.attr("content");
                }

                Elements metaOgImage = doc.select("meta[property=og:image]");
                if (metaOgImage != null) {
                    imageUrl = metaOgImage.attr("content");
                    Log.d("dfgdh11", imageUrl);
                }
                return  imageUrl+"#"+name ;
            } catch (Exception e) {
                return newVersion;
            }
        }
    }

}