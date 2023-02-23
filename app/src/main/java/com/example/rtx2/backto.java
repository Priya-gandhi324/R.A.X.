package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class backto extends AppCompatActivity {

    Button backtohome, publish_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backto);

        backtohome = (Button)findViewById(R.id.backtohome);
        publish_news = (Button)findViewById(R.id.publish_news);

        onClickHomeButton();
        onClickPublishButton();
    }

    public void onClickHomeButton(){
        backtohome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".NewApp");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickPublishButton(){
        publish_news.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Publishing");
                        startActivity(intent);
                    }
                }
        );
    }
}