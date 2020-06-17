package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String>lv=test();
        ListView listView=(ListView) findViewById(R.id.list_item1);
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,lv));

    }


    List<String> test()
    {
        List<String> lv = new ArrayList<String>();
        for(int i=0;i<100;i++)
        {
            lv.add("item "+ i);
        }
        return lv;
    }
}

