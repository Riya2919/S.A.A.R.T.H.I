package com.example.saarthi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;

public class viewpdfjudgecaseinfo extends AppCompatActivity {
    WebView viewpdf;
    ImageView back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdfjudgecaseinfo);

        viewpdf = findViewById(R.id.viewpdf);
        back2 = findViewById(R.id.back2);

        viewpdf.getSettings().setJavaScriptEnabled(true);

        String filename = getIntent().getStringExtra("filename");
        String fileurl = getIntent().getStringExtra("fileurl");

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(filename);
        pd.setMessage("File Opening....");

        viewpdf.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), laymenhomepg.class);
                startActivity(i);
            }
        });

        String url = "";
        try{
            url = URLEncoder.encode(fileurl, "UTF-8");
        }
        catch (Exception ex){

        }

        viewpdf.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
    }
}