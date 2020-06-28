package com.example.cookingbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class IngredientsActivity extends AppCompatActivity {

    WebView webView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView=(WebView)findViewById(R.id.webView);

       intent=getIntent();

        String url=intent.getStringExtra("URL");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
