package com.example.landmarks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void onClick(View v)
    {
        TextView t=(TextView) v;
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("Location Name", t.getText().toString());
        startActivity(intent);
    }
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        finish();
//    }
}
