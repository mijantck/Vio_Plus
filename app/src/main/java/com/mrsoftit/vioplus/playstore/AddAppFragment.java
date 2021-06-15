package com.mrsoftit.vioplus.playstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrsoftit.vioplus.R;

import org.jsoup.Jsoup;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAppFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAppFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddAppFragment() {
        // Required empty public constructor
    }
    public static AddAppFragment newInstance(String param1, String param2) {
        AddAppFragment fragment = new AddAppFragment();
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

    EditText app_add_edite_text;
    Button app_link_add_button;

    String appImageurl;
    String appName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_app, container, false);

        app_add_edite_text = view.findViewById(R.id.app_add_edite_text);
        app_link_add_button = view.findViewById(R.id.app_link_add_button);

        app_link_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = app_add_edite_text.getText().toString();

                if (url == null){
                    Toast.makeText(getContext(), "is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                GetVersionCode getVersionCode = new GetVersionCode(url);
                try {
                    String fullText = getVersionCode.execute().get();
                    String[] parts = fullText.split("#");
                    appImageurl = parts[0]; // 004
                    appName = parts[1];
                    if (appImageurl == null){
                    }else {
                        Intent intent = new Intent(v.getContext(),AppInfoActivity.class);
                        intent.putExtra("appImageUrl",appImageurl);
                        intent.putExtra("appName",appName);
                        intent.putExtra("fullUrl",url);
                        v.getContext().startActivity(intent);
                     }

                } catch (ExecutionException e) {


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });


        return  view;

    }

    private class GetVersionCode extends AsyncTask<Void, String, String> {

        String url ;

        public GetVersionCode(String url) {
            this.url = url;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            String name = null;

            try {
                newVersion = Jsoup.connect(url)
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select(".oQ6oV .hkhL9e .xSyT2c").select("img").eq(0).attr("src");

                name = Jsoup.connect(url)
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("h1.AHFaub").select("span").eq(0).text();



                return newVersion +"#"+name;
            } catch (Exception e) {
                return newVersion;
            }
        }
    }

}