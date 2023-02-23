package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class OTP_Verification extends AppCompatActivity {

    private static TextView go_back;

    Button send_otp, verify;
    EditText _txtPhone, _txtVerOTP;
    int randomnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p__verification);
        onClickgoBack();

        _txtPhone = (EditText)findViewById(R.id.txtPhone);
        _txtVerOTP = (EditText)findViewById(R.id.txtVerOTP);

        send_otp = (Button)findViewById(R.id.send_otp);
        verify = (Button)findViewById(R.id.verify);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Construct data
                    String apiKey = "apikey=" + "/JSvuHZzMIM-QFyIh0il1mHoQrdCDLu8NUqJeSDAml";
                    Random random = new Random();
                    randomnumber = random.nextInt(999999);
                    String message = "&message=" + "Hey, your OTP is: "+ randomnumber;
                    String sender = "&sender=" + "TPPS";
                    String numbers = "&numbers=" + _txtPhone.getText().toString();

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();

                    Toast.makeText(getApplicationContext(), "OTP send successfully "+ randomnumber, Toast.LENGTH_LONG).show();
                    //return stringBuffer.toString();
                } catch (Exception e) {
                    //System.out.println("Error SMS "+e);
                    //return "Error "+e;
                    Toast.makeText(getApplicationContext(), "Error SMS"+ e, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Error"+ e, Toast.LENGTH_LONG).show();
                }
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomnumber==Integer.valueOf(_txtVerOTP.getText().toString())){
                    Toast.makeText(getApplicationContext(), "You are successfully signed up", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(".Login");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onClickgoBack(){
        go_back = (TextView)findViewById(R.id.textView11);
        go_back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Signup");
                        startActivity(intent);
                    }
                }
        );
    }
}