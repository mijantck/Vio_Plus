package com.mrsoftit.vioplus.activity;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrsoftit.vioplus.R;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class TestActivity extends AppCompatActivity {


    ImageView imageView;
    String final_String;
    TextView ttte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        imageView = findViewById(R.id.imageId);
        ttte = findViewById(R.id.text22);


        GetVersionCode getVersionCode = new GetVersionCode();
        AppName appName = new AppName();

       try {
            String name = appName.execute().get();
            Toast.makeText(this, name + "", Toast.LENGTH_SHORT).show();
            ttte.setText(name);
           Picasso.get()
                   .load(name)
                   .into(imageView);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/*
        try {
            String vv = getVersionCode.execute().get();

            Toast.makeText(this, vv + "", Toast.LENGTH_SHORT).show();

           *//* String str = vv.substring(0, vv.length() - 8);

            String ss = str + "-rw";*//*

            ttte.setText(vv);


            String dd = "https://play-lh.googleusercontent.com/ldcQMpP7OaVmglCF6kGas9cY_K0PsJzSSosx2saw9KF1m3RHaEXpH_9mwBWaYnkmctk";
            Picasso.get()
                    .load(vv)
                    .into(imageView);


        } catch (ExecutionException e) {


        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }


    private class GetVersionCode extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            String name = null;

            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + "com.dts.freefireth" + "&hl=it")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select(".oQ6oV .hkhL9e .xSyT2c").select("img").eq(0).attr("src");

                name = Jsoup.connect("https://play.google.com/store/apps/details?id=" + "com.dts.freefireth" + "&hl=it")
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

    private class AppName extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            String dd = null;

            StringBuilder sb = new StringBuilder();
            String imageUrl = null;

            try {
                //HTML Parsing of the data coming from the url
                Document doc = Jsoup.connect("https://www.facebook.com/DarazBangladesh/")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .get();
                String text = null;
                Elements metaOgTitle = doc.select("meta[property=og:title]");
                if (metaOgTitle!=null) {
                    text = metaOgTitle.attr("content");
                }
                else {
                    text = doc.title();
                }


                Elements metaOgImage = doc.select("meta[property=og:image]");
                if (metaOgImage!=null) {
                    imageUrl = metaOgImage.attr("content");
                    Log.d("dfgdh", imageUrl);
                }

                if (imageUrl!=null) {
                    sb.append("<img src='");
                    sb.append(imageUrl);
                    sb.append("' align='left' hspace='12' vspace='12' width='150px'>");
                }

                if (text!=null) {
                    sb.append(text);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageUrl;
        }
    }


}