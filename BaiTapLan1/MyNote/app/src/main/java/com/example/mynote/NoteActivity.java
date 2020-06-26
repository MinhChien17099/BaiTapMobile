package com.example.mynote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ListView listView;

    ReadWriteXML xml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, NoteEditActivity.class);
                startActivity(intent);
            }
        });
        xml= new ReadWriteXML("appdata.xml", NoteActivity.this);
        listView.setAdapter(new NoteAdapter(xml,NoteActivity.this));


    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.e("Main","Resume");
        xml.read();
        this.listView.invalidateViews();
    }


}
