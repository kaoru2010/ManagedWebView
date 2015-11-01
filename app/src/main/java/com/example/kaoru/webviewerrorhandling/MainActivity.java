package com.example.kaoru.webviewerrorhandling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final ManagedWebViewController mManagedWebViewController = new ManagedWebViewController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManagedWebViewController.onCreate((ManagedWebView) findViewById(R.id.webview1));
    }

    @Override
    protected void onDestroy() {
        mManagedWebViewController.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mManagedWebViewController.onBackPressed()) {
            return;
        }

        super.onBackPressed();
    }
}
