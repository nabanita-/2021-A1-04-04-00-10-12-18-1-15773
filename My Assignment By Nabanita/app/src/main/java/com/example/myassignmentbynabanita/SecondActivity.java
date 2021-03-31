package com.example.myassignmentbynabanita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void confirmSend(View view) {
        // Do something in response to button

        // Get the Intent that started this activity and extract the string
        Intent incoming_intent = getIntent();
        String message = incoming_intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        EditText contact = (EditText) findViewById(R.id.editText2);
        String contact_text = contact.getText().toString();

        String ph_regex = "^(\\+\\d{1,2})?\\d{10}$";
        Pattern r = Pattern.compile(ph_regex);

        // check validation
        if (!contact_text.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(contact_text).matches()) {
            Toast.makeText(this, "Sending Email...", Toast.LENGTH_SHORT).show();


            Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {contact_text}); // recipients
//            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            // Always use string resources for UI text.
// This says something like "Share this photo with"
//            String title = getResources().getString(R.string.send_email);
//// Create intent to show chooser
//            Intent chooser = Intent.createChooser(emailIntent, title);
//
//            startActivity(chooser);
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
// You can also attach multiple items by passing an ArrayList of Uris
        }
        else if (!contact_text.isEmpty() && r.matcher(contact_text).matches()) {
            Toast.makeText(this, "Sending SMS...", Toast.LENGTH_SHORT).show();
            Uri number = Uri.parse("smsto:"+contact_text);
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, number);
            smsIntent.putExtra("sms_body",message);
//            smsIntent.putExtra(Intent.EXTRA_TEXT,message);
            startActivity(Intent.createChooser(smsIntent, "Send sms..."));
        }

        else {
//            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
            contact.setHint("Enter a valid phone number or email address");
            contact.setText("");
        }


        // show error message if validation failed


        //show menu if validation passed


    }

}