package com.example.morr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout creditCardInputLayout, dateInputLayout, securityInputLayout, firstNameInputLayout, lastNameInputLayout;
    private TextInputEditText cardNumberEt, dateEt, securityEt, firstNameEt, lastNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }

    @Override
    public void onClick(View v) {
        if (!cardNumberEt.getText().toString().isEmpty() && cardNumberEt.getText().toString().length() == 16) {
            creditCardInputLayout.setError("");
            if (!dateEt.getText().toString().isEmpty() && dateEt.getText().toString().length() == 5) {
                dateInputLayout.setError("");
                if (!securityEt.getText().toString().isEmpty() && securityEt.getText().toString().length() == 3) {
                    securityInputLayout.setError("");
                    if (!firstNameEt.getText().toString().isEmpty()) {
                        firstNameInputLayout.setError("");
                        if (!checkForNameErrors(firstNameEt.getText().toString()))
                            firstNameInputLayout.setError("This Fileld should  only contain alphabets and spaces ");
                        else {
                            firstNameInputLayout.setError("");

                            if (!lastNameEt.getText().toString().isEmpty()) {
                                if (!checkForNameErrors(lastNameEt.getText().toString()))
                                    lastNameInputLayout.setError("This Fileld should  only contain alphabets and space ");
                                else {
                                    lastNameInputLayout.setError("");
                                    checkForErrors();
                                }
                            } else
                                lastNameEt.setError("Field Emplty");
                            return;
                        }

                    } else {
                        firstNameInputLayout.setError("Field Emplty");
                        return;
                    }
                } else {
                    securityInputLayout.setError("Field Emplty or invalid security code");
                    return;
                }
            } else {
                dateEt.setError("Field Emplty or invalid date");
                return;
            }
        } else {
            creditCardInputLayout.setError("Field Emplty or invalid card no");
            return;
        }
    }

    private void checkForErrors() {
        // Luhn algorithm
        String cardNo = cardNumberEt.getText().toString();
        int sumOfOdd = 0, sumOfEven = 0;
        for (int i = 0; i < cardNo.length(); i++) {
            if (i % 2 != 0) {
                sumOfOdd = sumOfOdd + Integer.parseInt(cardNo.charAt(i) + "");
            } else {
                String no = Integer.parseInt(cardNo.charAt(i) + "") * 2 + "";
                if (no.length() == 1)
                    sumOfEven = sumOfEven + Integer.parseInt(no);
                else
                    sumOfEven = sumOfEven + Integer.parseInt(no.charAt(0) + "") + Integer.parseInt(no.charAt(1) + "");
            }
        }
        int total = sumOfEven + sumOfOdd;
        if (total % 10 == 0) {
            new AlertDialog.Builder(this).setTitle("Payment Successful ").setPositiveButton("OK", null).create().show();
        } else
            creditCardInputLayout.setError("Incorrect Card No ");
    }

    private void initializeView() {
        creditCardInputLayout = findViewById(R.id.id_creditCardTextLayout);
        dateInputLayout = findViewById(R.id.id_dateTextInputLayout);
        securityInputLayout = findViewById(R.id.id_securityCodeTextInputLayout);
        firstNameInputLayout = findViewById(R.id.id_firstnameTextInputLayout);
        lastNameInputLayout = findViewById(R.id.id_lastNameTextInputLayout);
        cardNumberEt = findViewById(R.id.id_creditCard);
        dateEt = findViewById(R.id.id_date);
        securityEt = findViewById(R.id.id_securityCode);
        firstNameEt = findViewById(R.id.id_firstname);
        lastNameEt = findViewById(R.id.id_lastName);
    }

    private boolean checkForNameErrors(String name) {
        Log.e("name is ", name);
        for (int i = 0; i < name.length(); i++)
            if (!((name.charAt(i) == ' ') || (name.charAt(i) >= 65 && name.charAt(i) <= 90) || (name.charAt(i) >= 97 && name.charAt(i) <= 122)))
                return false;

        return true;
    }
}
