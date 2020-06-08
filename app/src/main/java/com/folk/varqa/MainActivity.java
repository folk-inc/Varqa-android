package com.folk.varqa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
WebView webView;
String url = "https://web.varqa.ir/";
ProgressBar progressBar;
TextView textView;
boolean doubleBackToExitPressedOnce = false;
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.pg);
        textView = findViewById(R.id.tv);
        websetting();
        webView.loadUrl(url);
        webView.setWebChromeClient( new MyWebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void websetting(){
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setScrollbarFadingEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setScrollContainer(false);
        webView.addJavascriptInterface(this, "jsinterface");
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
    }

//    public class MyWebChromeClient extends WebChromeClient {
//        public void onProgressChanged(WebView view, int newProgress) {
//            progressBar.setVisibility(View.VISIBLE);
//            progressBar.setProgress(newProgress);
//        }
//    }
//
//    public class webClient extends WebViewClient {
//        public boolean  shouldOverrideUrlLoading(WebView webView, String url) {
//            webView.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            progressBar.setVisibility(View.GONE);
//        }
//    }

    public void onLoadResource (WebView view, String url) {

        // if url contains string androidexample
        // Then show progress  Dialog
        if (progressDialog == null && url.contains("androidexample")
        ) {

            // in standard case YourActivity.this
            progressDialog = new ProgressDialog(ShowWebView.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
    }

    // Called when all page resources loaded
    public void onPageFinished(WebView view, String url) {

        try{
            // Close progressDialog
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        }
    }
}
