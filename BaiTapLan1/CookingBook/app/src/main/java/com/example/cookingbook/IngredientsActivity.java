package com.example.cookingbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class IngredientsActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        webView=(WebView)findViewById(R.id.webView);

        Intent intent=getIntent();

        String url=intent.getStringExtra("URL");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
