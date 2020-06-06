package com.example.tempconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private Button btn_convert;
    private EditText txt_F;
    private EditText txt_C;
    private TextView txt_H;
    private RadioButton radioButton_Fahrenheit;
    private RadioButton radioButton_Celsius;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_F=(EditText)findViewById(R.id.txt_F);
        txt_C=(EditText)findViewById(R.id.txt_C);
        txt_H=(TextView)findViewById(R.id.txt_H);
        btn_convert=(Button)findViewById(R.id.btn_convert);
        radioButton_Celsius=(RadioButton)findViewById(R.id.radioButton_Celsius);
        radioButton_Fahrenheit=(RadioButton)findViewById(R.id.radioButton_Fahrenheit);

        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double result=0.0,input;
                try {
                    input=Double.parseDouble(txt_F.getText().toString());
                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this, "Nhap Lai", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(radioButton_Celsius.isChecked())
                {
                    input=(input*9.0/5.0)+32;
                    txt_C.setText(input.toString());
                    txt_H.setText("C=>F: "+txt_F.getText().toString()+" => "+txt_C.getText().toString()+"\n"+txt_H.getText().toString());
                }
                else
                {
                    input=(input-32)*5.0/9.0;
                    txt_C.setText(input.toString());
                    txt_H.setText("F=>C : "+txt_F.getText().toString()+" => "+txt_C.getText().toString()+"\n"+txt_H.getText().toString());
                }
            }
        });


    }
}
