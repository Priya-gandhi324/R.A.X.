package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static Button get_started;
    private static Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickButtonListener();
        onClickButtonListenerLogin();
    }

    public void onClickButtonListener()
    {
        get_started = (Button)findViewById(R.id.getstarted);
        get_started.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".NewApp");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickButtonListenerLogin()
    {
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Login");
                        startActivity(intent);
                    }
                }
        );
    }
}