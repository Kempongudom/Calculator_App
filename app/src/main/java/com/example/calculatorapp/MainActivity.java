package com.example.calculatorapp;

import static android.text.method.TextKeyListener.clear;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCalculateWorking;
    private boolean isLastNumber = false;
    private boolean isLastDecimalPoint = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewCalculateWorking = findViewById(R.id.textViewCalculateWorking);

    }

    // method for get number when click button
    public void onDigit(View view) {
        textViewCalculateWorking.append("" + ((Button) view).getText());
        isLastNumber = true;
        isLastDecimalPoint = false;
    }

    //method for clear all number from textViewCalculateWorking
    public void onClear(View view) {
        textViewCalculateWorking.setText("");
        isLastNumber = false;
        isLastDecimalPoint = false;
    }

    // method for add decimal point when click on it button
    public void onDecimal(View view) {
        if (isLastNumber && !isLastDecimalPoint) {
            textViewCalculateWorking.append(".");
            isLastNumber = false;
            isLastDecimalPoint = true;
        }
    }

    //method for add operator
    public void onOperation(View view) {
        if (isLastNumber && !isOperationAdd(textViewCalculateWorking.getText().toString())) {
            textViewCalculateWorking.append(("" + ((Button) view).getText()));
            isLastNumber = false;
            isLastDecimalPoint = false;
        }
    }

    // method for check if operator are already add
    private boolean isOperationAdd(String value) {
        if (value.startsWith("-")) {
            return false;
        } else {
            return value.contains("+") ||
                    value.contains("-") ||
                    value.contains("×") ||
                    value.contains("÷");
        }
    }

    // method for remove .0 from textViewCalculateWorking
    private String removeZeroAfterDot(String result) {
        String value = result;
        if (result.contains(".0")) {
            value = result.substring(0, result.length() - 2);
        }
        return value;
    }

    // method for calculate number
    public void onCalculate(View view) {
        if (isLastNumber) {
            String value = textViewCalculateWorking.getText().toString();
            String prefix = "";

            if (value.startsWith("-")) {
                prefix = "-";
                value = value.substring(1);
            }

            if (value.contains("+")) {
                String[] splitValue = value.split("\\+");
                String one = splitValue[0];
                String two = splitValue[1];

                if (prefix != "") {
                    one = prefix + one;
                }

                double value1 = Double.parseDouble(one);
                double value2 = Double.parseDouble(two);
                String result = String.valueOf(value1 + value2);

                textViewCalculateWorking.setText("" + removeZeroAfterDot(result));
            } else if (value.contains("-")) {
                String[] splitValue = value.split("-");
                String one = splitValue[0];
                String two = splitValue[1];

                if (prefix != "") {
                    one = prefix + one;
                }

                double value1 = Double.parseDouble(one);
                double value2 = Double.parseDouble(two);
                String result = String.valueOf(value1 - value2);

                textViewCalculateWorking.setText("" + removeZeroAfterDot(result));
            } else if (value.contains("×")) {
                String[] splitValue = value.split("×");
                String one = splitValue[0];
                String two = splitValue[1];

                if (prefix != "") {
                    one = prefix + one;
                }

                double value1 = Double.parseDouble(one);
                double value2 = Double.parseDouble(two);
                String result = String.valueOf(value1 * value2);
                textViewCalculateWorking.setText("" + removeZeroAfterDot(result));

            } else if (value.contains("÷")) {
                String[] splitValue = value.split("÷");
                String one = splitValue[0];
                String two = splitValue[1];

                if (prefix != "") {
                    one = prefix + one;
                }

                double value1 = Double.parseDouble(one);
                double value2 = Double.parseDouble(two);
                String result = String.valueOf(value1 / value2);
                textViewCalculateWorking.setText("" + removeZeroAfterDot(result));
            } else if (value.contains("^")) {
                String[] splitValue = value.split("\\^");
                String one = splitValue[0];
                String two = splitValue[1];

                if (prefix != "") {
                    one = prefix + one;
                }

                double value1 = Double.parseDouble(one);
                double value2 = Double.parseDouble(two);
                String result = String.valueOf(Math.pow(value1, value2));
                textViewCalculateWorking.setText("" + removeZeroAfterDot(result));
            }
        }

    }
}