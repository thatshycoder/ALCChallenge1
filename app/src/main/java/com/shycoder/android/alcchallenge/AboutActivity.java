package com.shycoder.android.alcchallenge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AboutActivity extends AppCompatActivity {
    private WebView mWebpage;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mWebpage = findViewById(R.id.wv_andela);
        mProgressBar = findViewById(R.id.progressBar2);

        mWebpage.getSettings().setJavaScriptEnabled(true);
        mWebpage.getSettings().setLoadWithOverviewMode(true);
        mWebpage.getSettings().setUseWideViewPort(true);

        mWebpage.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mProgressBar.setVisibility(View.VISIBLE);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        if (savedInstanceState != null) {
            mWebpage.restoreState(savedInstanceState);
        } else {
            mWebpage.loadUrl(getString(R.string.andela_url));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mWebpage.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
