package com.example.hostlyand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentPayrent extends AppCompatActivity {
    Button payment;
    EditText actholder, cardno, ex, cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_payrent);

        actholder = findViewById(R.id.editTextTextPersonName11);
        cardno = findViewById(R.id.editTextTextPersonName12);
        ex = findViewById(R.id.editTextTextPersonName13);
        cvv = findViewById(R.id.editTextTextPersonName14);

        payment = findViewById(R.id.button9);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
    }

    private void validateFields() {
        String accountHolder = actholder.getText().toString().trim();
        String cardNumber = cardno.getText().toString().trim();
        String expirationDate = ex.getText().toString().trim();
        String cvvNumber = cvv.getText().toString().trim();

        // Basic validation checks
        if (accountHolder.isEmpty()) {
            showToast("Account holder name is required.");
            return;
        }

        if (cardNumber.isEmpty() || cardNumber.length() != 16) {
            showToast("Valid card number (16 digits) is required.");
            return;
        }

        if (expirationDate.isEmpty() || !isValidExpirationDate(expirationDate)) {
            showToast("Valid expiration date is required (MM/YY).");
            return;
        }

        if (cvvNumber.isEmpty() || cvvNumber.length() != 3) {
            showToast("Valid CVV number (3 digits) is required.");
            return;
        }

        // If all validations pass, start the Payment activity
        startActivity(new Intent(getApplicationContext(), Payment.class));
    }

    private boolean isValidExpirationDate(String expirationDate) {
        // Simple check for MM/YY format
        String[] parts = expirationDate.split("/");
        if (parts.length != 2) {
            return false;
        }

        String month = parts[0];
        String year = parts[1];

        return month.length() == 2 && year.length() == 2 && month.matches("\\d{2}") && year.matches("\\d{2}");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
