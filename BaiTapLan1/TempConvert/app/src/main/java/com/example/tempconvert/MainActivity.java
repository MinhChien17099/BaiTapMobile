package com.example.tempconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnConvert;
    private EditText edtInput;
    private EditText edtOutput;
    private TextView tvResults;
    private RadioButton radioCtoF;
    private RadioButton radioFtoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtInput = findViewById(R.id.edtInput);
        edtOutput = findViewById(R.id.edtOutput);
        tvResults = findViewById(R.id.tvResults);
        btnConvert = findViewById(R.id.btnConvert);
        radioCtoF = findViewById(R.id.radioCtoF);
        radioFtoC = findViewById(R.id.radioFtoC);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double input=Double.parseDouble(edtInput.getText().toString());
                if (radioCtoF.isChecked()) {
                    input = (input * 9.0 / 5.0) + 32;
                    edtOutput.setText(NumberFormat.getNumberInstance(Locale.US).format(input));
                    tvResults.setText("C to F: " + edtInput.getText().toString() + " ➔ " + edtOutput.getText().toString() + "\n" + tvResults.getText().toString());
                } else {
                    input = (input - 32) * 5.0 / 9.0;
                    edtOutput.setText(NumberFormat.getNumberInstance(Locale.US).format(input));
                    tvResults.setText("F to C : " + edtInput.getText().toString() + " ➔ " + edtOutput.getText().toString() + "\n" + tvResults.getText().toString());
                }
            }
        });


    }
}
