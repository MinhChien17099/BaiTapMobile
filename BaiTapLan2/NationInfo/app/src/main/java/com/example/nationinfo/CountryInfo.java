package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CountryInfo extends AppCompatActivity {


    public ImageView imageView_countryFlag;
    public TextView textView_countryName;
    public TextView textView_Population;
    public TextView textView_Area;

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
        textView_Population.setText(textView_Population.getText() + " : " + intent.getStringExtra("Population"));
        textView_Area.setText(textView_Area.getText() + " : " + intent.getStringExtra("Area") + " km²");

        String countryCode=intent.getStringExtra("Country Code");
        String url = "https://img.geonames.org/flags/x/";
        url = url + countryCode.toLowerCase() + ".gif";
        Picasso.with(this)
                .load(url)
                .into(imageView_countryFlag);
    }
}
