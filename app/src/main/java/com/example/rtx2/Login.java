package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper db;

    EditText login_email, login_password;
    TextView signup_text;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        login_email = (EditText)findViewById(R.id.login_email);
        login_password = (EditText)findViewById(R.id.login_password);

        signup_text = (TextView)findViewById(R.id.signup_text);

        login_button = (Button)findViewById(R.id.login_button);

        onClickSignupText();
        onClickLoginButton();
    }

    public void onClickSignupText(){
        signup_text.setOnClickListener(
                new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(".Signup");
                    startActivity(intent);
                }
            }
        );
    }

    public void onClickLoginButton(){
        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email = login_email.getText().toString();
                        String password = login_password.getText().toString();

                        Boolean chkemailpass = db.emailpassword(email,password);
                        if(chkemailpass==true){
                            Toast.makeText(getApplicationContext(),"Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(".backto");
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Wrong credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}