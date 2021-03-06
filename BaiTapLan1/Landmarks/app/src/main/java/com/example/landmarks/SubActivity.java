package com.example.landmarks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView textView = (TextView) findViewById(R.id.header);
        Intent intent = getIntent();
        locationName = intent.getStringExtra("Location Name");

        textView.setText(locationName);

        Button buttonMapIt = (Button) findViewById(R.id.buttonMapIt);
        buttonMapIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + locationName));
                    startActivity(i);
                } catch (Exception ex) {
                    Toast.makeText(SubActivity.this, "Không thể mở bản đồ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonMoreInfo = (Button) findViewById(R.id.buttonMoreInfo);
        buttonMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "";
                switch (locationName) {
                    case "Cleveland Tower City":
                        URL = "https://en.wikipedia.org/wiki/Tower_City_Center";
                        break;
                    case "Browns Football Field":
                        URL = "https://www.clevelandbrowns.com/";
                        break;
                    case "Cleveland State University":
                        URL = "https://www.csuohio.edu/";
                        break;
                    case "Playhouse Square":
                        URL = "http://www.playhousesquare.org/";
                        break;
                    case "Art Museum":
                        URL = "https://www.clevelandart.org/";
                        break;
                    case "Đại học Sài Gòn":
                        URL = "https://sgu.edu.vn/";
                        break;
                    default:
                        break;
                }
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
