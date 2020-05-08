package com.u3205216.myucmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wv = findViewById(R.id.webView);
        wv.setWebViewClient(new MyWebView());
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        if(savedInstanceState == null) {
            wv.loadUrl("https://www.canberra.edu.au/");
        } else {
            String url = (String) savedInstanceState.getSerializable("url");
            wv.loadUrl(url);
        }
    }
}
