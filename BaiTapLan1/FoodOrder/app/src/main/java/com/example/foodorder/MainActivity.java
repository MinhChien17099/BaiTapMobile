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

    public List<CheckBox> Fillings;
    public List<CheckBox> Beverage;
    private RadioGroup radioGroup_Size;
    private RadioGroup radioGroup_Tortilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fillings = new ArrayList<CheckBox>();
        Beverage = new ArrayList<CheckBox>();


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

        String message = "I want a # taco with $, ";
        String fillings = "\nfillings : ";
        String beverage = "\nbeverage : ";

        RadioButton radioButton_Size = (RadioButton) findViewById(radioGroup_Size.getCheckedRadioButtonId());
        RadioButton radioButton_Tortilla = (RadioButton) findViewById(radioGroup_Tortilla.getCheckedRadioButtonId());

        //Lấy danh sách fillings
        for (CheckBox item : Fillings) {
            if (item.isChecked())
                fillings += item.getText()+", " ;
        }

        //Lấy danh sách beverage
        for (CheckBox item : Beverage) {
            if (item.isChecked())
                beverage += item.getText()+ ", ";
        }


        message = message.replace("#", radioButton_Size.getText().toString());
        message = message.replace("$", radioButton_Tortilla.getText().toString());
        message += fillings+ beverage;
        message = message.replace("Large", "Big");

        //Gửi tin nhắn
        String phoneNumber = ((TextView) findViewById(R.id.business_phone)).getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);

    }

    public void onCheckBoxClicked(View view) {
        if (((CheckBox) view).isChecked()) {
            Fillings.add((CheckBox) view);

        } else {
            Fillings.remove(((CheckBox) view));
        }
    }

    public void onClicked(View view) {
        if (((CheckBox) view).isChecked()) {
            Beverage.add((CheckBox) view);
        } else {
            Beverage.remove(((CheckBox) view));
        }
    }
}

