package com.example.trackcovid19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.showProgress);
        webView = findViewById(R.id.viewCovid);
        webView.getSettings().setJavaScriptEnabled(true);

        /*TODO*/
        /*App Rate System*//*
        AppRate.with(this)
                .setInstallDays(0) // day of installation
                .setLaunchTimes(2) // 2 launches after install
                .setRemindInterval(7) //after seven days
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);*/

        String url = "https://www.bing.com/covid";
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                setTitle("TrackCOVID-19");
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //getSupportActionBar().setTitle(title);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.web_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.forward:
                onForward();
                break;

            case R.id.refresh:
                webView.reload();
                break;
            case R.id.help:
                feedback_email();
                break;
            case R.id.share:
                shareApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onForward() {
        if (webView.canGoForward()) {
            webView.goForward();
        } else {
            Toast.makeText(this, "Cant go Forward!", Toast.LENGTH_LONG).show();
        }
    }

    private void feedback_email() {

        Toast.makeText(Objects.requireNonNull(getApplicationContext()).getApplicationContext(), "Thankyou for your feedback!", Toast.LENGTH_SHORT).show();
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:codeguy0098@gmail.com" +
                "?subject=TrackCOVID19 Feedback"));
        email.putExtra(Intent.EXTRA_SUBJECT, "TrackCOVID-19 Feedback");
        startActivity(email);
    }

    private void shareApp() {
        Toast.makeText(getApplicationContext(), "Coming soon!", Toast.LENGTH_SHORT).show();

        /*Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String url = "url here"*//*TODO*//*;
        String shareBody = "Sentence here" + url;*//*TODO*//*
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "TrackCOVID19");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));*/

    }
}


