package com.example.bookland.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.CardForm;
import com.example.bookland.R;

public class CrdCardForm extends AppCompatActivity {

    CardForm cardForm;
    TextView txtDes;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_form);

        cardForm = findViewById(R.id.card_form);
        txtDes = findViewById(R.id.payment_amount);
        btnPay = findViewById(R.id.btn_pay);

        txtDes.setText("$18.5");
        btnPay.setText(String.format("Payer %s", txtDes.getText()));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CrdCardForm.this, "Payed successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}