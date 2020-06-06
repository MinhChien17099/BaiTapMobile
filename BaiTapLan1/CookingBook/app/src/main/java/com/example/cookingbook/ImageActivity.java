package com.example.cookingbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView=(ImageView)findViewById(R.id.imageView);

        Intent intent=getIntent();

        int imageID=intent.getIntExtra("Image ID",0);
        imageView.setImageResource(imageID);

    }
}
