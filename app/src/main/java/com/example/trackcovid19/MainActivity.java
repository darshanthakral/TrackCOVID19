package com.example.trackcovid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
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

public class MainActivity extends AppCompatActivity {
    WebView Covidtrack;
    String url;
    private ProgressBar viewPB;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPB = findViewById(R.id.showProgress);
        url = "https://www.bing.com/covid";
        Covidtrack = findViewById(R.id.viewCovid);
        Covidtrack.getSettings().setJavaScriptEnabled(true);
        Covidtrack.loadUrl(url);
        Covidtrack.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                viewPB.setVisibility(View.VISIBLE);
                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                viewPB.setVisibility(View.GONE);
                setTitle("TrackCOVID-19");
            }
        });
        Covidtrack.setWebChromeClient(new WebChromeClient() {
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
        if (Covidtrack.canGoBack()) {
            Covidtrack.goBack();
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

            case R.id.Forward:
                onForward();
                break;

            case R.id.Reload:
                Covidtrack.reload();

        }
        return super.onOptionsItemSelected(item);
    }

    private void onForward() {
        if (Covidtrack.canGoForward()) {
            Covidtrack.goForward();
        } else {
            Toast.makeText(this, "Cant go Forward!", Toast.LENGTH_LONG).show();
        }
    }
}


