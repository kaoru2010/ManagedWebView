package com.example.kaoru.webviewerrorhandling;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by kaoru on 15/11/01.
 */
public class ManagedWebViewController {
    private static final String TAG = "WebView";
    private ManagedWebView mManagedWebView;

    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(ManagedWebView managedWebView) {
        mManagedWebView = managedWebView;
        mManagedWebView.setWebViewClient(mWebViewClient);

        managedWebView.getSettings().setJavaScriptEnabled(true);
        managedWebView.loadData("<html><body><a href=http://a.b.c.d/>@@@@</a></body></html>", "text/html", null);
    }

    public void onDestroy() {
        if (mManagedWebView != null) {
            ViewParent viewParent = mManagedWebView.getParent();
            if (viewParent instanceof ViewGroup) {
                ((ViewGroup) viewParent).removeView(mManagedWebView);
            }
            mManagedWebView.destroy();
            mManagedWebView = null;
        }
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mManagedWebView.onPageStarted(url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mManagedWebView.onPageFinished(url);
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            mManagedWebView.onLoadResource(url);
            super.onLoadResource(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mManagedWebView.onReceivedError(failingUrl);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    };

    public boolean onBackPressed() {
        if (mManagedWebView.canGoBack()) {
            mManagedWebView.goBack();
            return true;
        } else {
            return false;
        }
    }
}
