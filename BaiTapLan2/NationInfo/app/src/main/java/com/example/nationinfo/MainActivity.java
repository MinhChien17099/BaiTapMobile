package com.example.nationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView_Country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo ListView.
        listView_Country = (ListView) findViewById(R.id.list_view);

        new MyAsyncTask(MainActivity.this, listView_Country).execute();
    }

}
