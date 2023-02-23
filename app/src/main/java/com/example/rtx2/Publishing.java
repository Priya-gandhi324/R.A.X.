package com.example.rtx2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Publishing extends AppCompatActivity {

    EditText publish_name, publish_date, publish_address, publish_title, publish_desc, publish_email;
    Button publish_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishing);
    }

    public void send_Click(View v)
    {
        publish_name = (EditText)findViewById(R.id.publish_name);
        publish_date = (EditText)findViewById(R.id.publish_date);
        publish_address = (EditText)findViewById(R.id.publish_address);
        publish_title = (EditText)findViewById(R.id.publish_title);
        publish_desc = (EditText)findViewById(R.id.publish_desc);
        publish_email = (EditText)findViewById(R.id.publish_email);

        publish_button = (Button)findViewById(R.id.publish_button);

        if(publish_name.getText().toString().equals("")||publish_email.getText().toString().equals("")
                ||publish_title.getText().toString().equals("")||publish_desc.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:"));
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"rtxofficial2020@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, publish_title.getText().toString());
            i.putExtra(Intent.EXTRA_TEXT, "Date of Incident: "+publish_date.getText().toString()+"\nCurrent Location: "+publish_address.getText().toString()+
                    "\nDescription: "+publish_desc.getText().toString()+"\nRegards,\n"+publish_name.getText().toString());

            try{
                startActivity(Intent.createChooser(i,"send mail"));
            }
            catch (android.content.ActivityNotFoundException ex){
               Toast.makeText(this,"No mail app found", Toast.LENGTH_SHORT).show();
            }
            catch (Exception ex){
                Toast.makeText(this, "Unexpected error"+ex.toString(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}