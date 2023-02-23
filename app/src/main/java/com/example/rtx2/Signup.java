package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    DatabaseHelper db;

    private static TextView login_text;
    private static Button signup;

    EditText sign_name, sign_email, sign_phone, sign_city, sign_state, sign_password, sign_cpassword;
    Button sign_button;
    CheckBox terms_and_conditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);

        sign_name = (EditText)findViewById(R.id.sign_name);
        sign_email = (EditText)findViewById(R.id.sign_email);
        sign_phone = (EditText)findViewById(R.id.sign_phone);
        sign_city = (EditText)findViewById(R.id.sign_city);
        sign_state = (EditText)findViewById(R.id.sign_state);
        sign_password = (EditText)findViewById(R.id.sign_password);
        sign_cpassword = (EditText)findViewById(R.id.sign_cpassword);

        sign_button = (Button)findViewById(R.id.sign_button);

        terms_and_conditions = (CheckBox)findViewById(R.id.terms_and_conditions);

        onClickLoginText();
        onClickSignupButton();
    }

    public void onClickLoginText(){
        login_text = (TextView)findViewById(R.id.textView8);
        login_text.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Login");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickSignupButton(){
        sign_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String s_name = sign_name.getText().toString();
                        String s_email = sign_email.getText().toString();
                        String s_phone = sign_phone.getText().toString();
                        String s_city = sign_city.getText().toString();
                        String s_state = sign_state.getText().toString();
                        String s_password = sign_password.getText().toString();
                        String s_cpassword = sign_cpassword.getText().toString();

                        if(s_name.equals("")||s_email.equals("")||s_phone.equals("")||s_city.equals("")||s_state.equals("")||s_password.equals("")||s_cpassword.equals("")){
                            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            if(s_password.equals(s_cpassword)){

                                if(terms_and_conditions.isChecked()) {
                                    Boolean chkemail = db.chkemail(s_email);

                                    if (chkemail == true) {
                                        Boolean insert = db.insert(s_name, s_email, s_phone, s_city, s_state, s_password);

                                        if (insert == true) {
                                            Toast.makeText(getApplicationContext(), "Please verify your id", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(".VerificationViaEmail");
                                            startActivity(intent);
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Please accept our terms and conditions", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }
}