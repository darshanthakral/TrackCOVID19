package com.example.trackcovid19;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setGeolocationDatabasePath(getFilesDir().getPath());
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);


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

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // return super.shouldOverrideUrlLoading(view, request);

                final Uri uri = request.getUrl();
                if (uri.toString().startsWith("mailto:")) {

                    //Handle mail Urls
                    startActivity(new Intent(Intent.ACTION_SENDTO, uri));
                } else if (uri.toString().startsWith("tel:")) {

                    //Handle telephony Urls
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                } else if (uri.toString().startsWith("https://www.facebook.com/")) {

                    Intent facebook = new Intent(Intent.ACTION_VIEW, uri);
                    facebook.setPackage("com.facebook.android");
                    try {
                        startActivity(facebook);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }
                } else if (uri.toString().startsWith("https://twitter.com")) {

                    Intent twitter = new Intent(Intent.ACTION_VIEW, uri);
                    twitter.setPackage("com.twitter.android");
                    try {
                        startActivity(twitter);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }
                } else if (uri.toString().startsWith("https://drive.google.com/")) {

                    Intent drive = new Intent(Intent.ACTION_VIEW, uri);
                    drive.setPackage("com.drive.google.android");
                    try {
                        startActivity(drive);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }
                } else if (uri.toString().startsWith("https://api.whatsapp.com/")) {

                    Intent whatsapp = new Intent(Intent.ACTION_VIEW, uri);
                    whatsapp.setPackage("com.whatsapp.android");
                    try {
                        startActivity(whatsapp);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }
                } else if (uri.toString().startsWith("https://www.google.com/maps/")) {

                    Intent maps = new Intent(Intent.ACTION_VIEW, uri);
                    maps.setPackage("com.maps.android");
                    try {
                        startActivity(maps);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }

                } else if (uri.toString().startsWith("https://play.google.com/")) {

                    Intent playConsole = new Intent(Intent.ACTION_VIEW, uri);
                    playConsole.setPackage("com.android.application");
                    try {
                        startActivity(playConsole);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }

                } else if (uri.toString().startsWith("https://www.instagram.com/")) {

                    Intent playConsole = new Intent(Intent.ACTION_VIEW, uri);
                    playConsole.setPackage("com.instagram.android");
                    try {
                        startActivity(playConsole);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }

                } else if (uri.toString().startsWith("https://www.linkedin.com/")) {

                    Intent playConsole = new Intent(Intent.ACTION_VIEW, uri);
                    playConsole.setPackage("com.linkedin.android");
                    try {
                        startActivity(playConsole);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }

                } else if (uri.toString().startsWith("https://github.com/")) {

                    Intent playConsole = new Intent(Intent.ACTION_VIEW, uri);
                    playConsole.setPackage("com.github.android");
                    try {
                        startActivity(playConsole);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }

                } else if (uri.toString().startsWith("https://www.youtube.com/")) {

                    Intent playConsole = new Intent(Intent.ACTION_VIEW, uri);
                    playConsole.setPackage("com.google.android.youtube");
                    try {
                        startActivity(playConsole);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(String.valueOf(uri))));
                    }

                } else {
                    //Handle Web Urls
                    view.loadUrl(uri.toString());
                }
                return true;
            }
        });


        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //getSupportActionBar().setTitle(title);
            }
        });

        requestPermission();
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

    @SuppressLint("NonConstantResourceId")
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

        Toast.makeText(Objects.requireNonNull(getApplicationContext()).getApplicationContext(), "ThankYou for your feedback!", Toast.LENGTH_SHORT).show();
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:dtprofessional98@gmail.com" +
                "?subject=TrackCOVID19 Feedback"));
        email.putExtra(Intent.EXTRA_SUBJECT, "TrackCOVID-19 Feedback");
        startActivity(email);
    }


    //Location Permission
    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "permission granted!", Toast.LENGTH_SHORT).show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            Log.i("PERMISSION: ", "OK");

        } else {
            requestPermission();
        }
    }
}


