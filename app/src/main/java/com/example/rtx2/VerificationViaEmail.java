package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class VerificationViaEmail extends AppCompatActivity {

    RadioButton verify_phone;
    RadioButton verify_email;
    EditText verify_via_otp;
    Button verify_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_via_email);

        verify_phone = (RadioButton) findViewById(R.id.verify_phone);
        verify_email = (RadioButton) findViewById(R.id.verify_email);
        verify_via_otp = findViewById(R.id.verify_via_otp);
        verify_button = findViewById(R.id.verify_button);

        verify_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_via_otp.setVisibility(View.VISIBLE);
                verify_button.setVisibility(View.VISIBLE);
            }
        });

        verify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verify_via_otp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(".Login");
                    startActivity(intent);
                }
            }
        });

        verify_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_via_otp.setVisibility(View.VISIBLE);
                verify_button.setVisibility(View.VISIBLE);
            }
        });

        verify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verify_via_otp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(".Login");
                    startActivity(intent);
                }
            }
        });
    }
}