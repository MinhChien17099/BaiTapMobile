package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<CheckBox> checkBoxList;
    private RadioGroup radioGroup_Size;
    private RadioGroup radioGroup_Tortilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxList = new ArrayList<CheckBox>();

        Button button = (Button) findViewById(R.id.button_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    void sendMessage() {
        radioGroup_Size = (RadioGroup) findViewById(R.id.radioGroup_Size);
        radioGroup_Tortilla = (RadioGroup) findViewById(R.id.radioGroup_Tortilla);

        String Test = "I want a # taco with $, adding ";

        RadioButton radioButton_Size = (RadioButton) findViewById(radioGroup_Size.getCheckedRadioButtonId());
        RadioButton radioButton_Tortilla = (RadioButton) findViewById(radioGroup_Tortilla.getCheckedRadioButtonId());

        for (CheckBox item : checkBoxList) {
            if (item.isChecked())
                Test += ", " + item.getText();
        }
        Test = Test.replace("#", radioButton_Size.getText().toString());
        Test = Test.replace("$", radioButton_Tortilla.getText().toString());
        Test = Test.replace("Large", "Big");

        //gui tin nhan
        String phoneNumber = ((TextView) findViewById(R.id.business_phone)).getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", Test);
        startActivity(intent);


    }

    public void onCheckBoxClicked(View view) {
        if (((CheckBox) view).isChecked()) {
            Toast.makeText(MainActivity.this, ((CheckBox) view).getText(), Toast.LENGTH_SHORT).show();
            checkBoxList.add((CheckBox) view);

        } else {
            checkBoxList.remove(((CheckBox) view));
        }
    }

}

