package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinMainWeight, spinMainHeight;
    Button btnMainCalculate;
    TextView txtWeight,txtHeight,txtResult,txtResultState;
    EditText etHeight,etWeight;
    LinearLayout llBmiRes;
    int selectedHeight = 0;
    int selectedWeight = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinMainWeight = findViewById(R.id.spinMainWeight);
        spinMainHeight = findViewById(R.id.spinMainHeight);
        btnMainCalculate = findViewById(R.id.btnMainCalculate);
        txtWeight = findViewById(R.id.tvMainweightunit);
        txtHeight = findViewById(R.id.tvMainheightunit);
        etHeight = findViewById(R.id.etMainHeight);
        etWeight = findViewById(R.id.etMainWeight);
        llBmiRes = findViewById(R.id.llBmiRes);
        txtResult = findViewById(R.id.tvMainResult);
        txtResultState = findViewById(R.id.tvMainResultstate);

        llBmiRes.setVisibility(View.GONE);
        txtHeight.setText("");
        txtWeight.setText("");

        spinMainHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinMainHeight.getSelectedItemPosition() != 0) {
                    txtHeight.setText(spinMainHeight.getSelectedItem().toString());
                    selectedHeight = spinMainHeight.getSelectedItemPosition();
                    spinMainHeight.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinMainWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinMainWeight.getSelectedItemPosition() != 0) {
                    txtWeight.setText(spinMainWeight.getSelectedItem().toString());
                    selectedWeight = spinMainWeight.getSelectedItemPosition();
                    spinMainWeight.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnMainCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedWeight == 0){
                    makeToast("Select Weight Unit");
                }else if(selectedHeight == 0){
                    makeToast("Select Height Unit");
                }else if(etWeight.getText().toString().trim().isEmpty() || etHeight.getText().toString().trim().isEmpty()){
                    makeToast("All fields are mandatory");
                }else{
                    double weight = 0.0;
                    double height = 0.0;
                    if(selectedWeight == 1){
                        weight = Double.parseDouble(etWeight.getText().toString().trim());
                    }else if(selectedWeight == 2){
                        weight =  Double.parseDouble(etWeight.getText().toString().trim())*0.454;
                    }

                    if(selectedHeight == 1){
                        height = Double.parseDouble(etHeight.getText().toString().trim())/100;
                    }else if(selectedHeight == 2){
                        height = Double.parseDouble(etHeight.getText().toString().trim());
                    }else if(selectedHeight == 3){
                        height = Double.parseDouble(etHeight.getText().toString().trim())*0.305;
                    }else if(selectedHeight == 4){
                        height = Double.parseDouble(etHeight.getText().toString().trim())*0.025;
                    }

                    double bmi = weight/(height*height);
                    txtResult.setText(String.format("%.2f",bmi));

                    if(bmi>=16.0 && bmi<18.5){
                        txtResultState.setText("Underweight");
                        txtResult.setTextColor(getResources().getColor(R.color.blue));
                        txtResultState.setTextColor(getResources().getColor(R.color.blue));
                    }else if(bmi>=18.5 && bmi<25.0){
                        txtResult.setTextColor(getResources().getColor(R.color.green));
                        txtResultState.setTextColor(getResources().getColor(R.color.green));
                        txtResultState.setText("Normal");
                    }else {
                        txtResult.setTextColor(getResources().getColor(R.color.red));
                        txtResultState.setTextColor(getResources().getColor(R.color.red));
                        txtResultState.setText("Over weight");
                    }

                    llBmiRes.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void makeToast(String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}