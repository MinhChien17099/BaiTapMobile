package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class CountryInfo extends AppCompatActivity {


    ImageView imageView_countryFlag;
    TextView textView_countryName;
    TextView textView_Population;
    TextView textView_Area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countryinfo);

        imageView_countryFlag = (ImageView) findViewById(R.id.imageView_countryFlag);
        textView_countryName = (TextView) findViewById(R.id.textView_countryName);
        textView_Population = (TextView) findViewById(R.id.textView_Population);
        textView_Area = (TextView) findViewById(R.id.textView_Area);


        Intent intent = getIntent();
        textView_countryName.setText(intent.getStringExtra("Country Name"));
        textView_Population.setText(textView_Population.getText() + " : " + NumberFormat.getNumberInstance(Locale.US).format(intent.getLongExtra("Population",0))+" người");
        textView_Area.setText(textView_Area.getText() + " : " + NumberFormat.getNumberInstance(Locale.US).format(intent.getDoubleExtra("Area",0.0) )+ " km²");

        String countryCode = intent.getStringExtra("Country Code");
        String url = "https://img.geonames.org/flags/x/" + countryCode.toLowerCase() + ".gif";

        Picasso.with(CountryInfo.this)
                .load(url)
                .placeholder(R.drawable.loading)
                .into(imageView_countryFlag);
    }
}
