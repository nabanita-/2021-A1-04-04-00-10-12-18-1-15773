package com.example.myassignmentbynabanita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myassignmentbynabanita.MESSAGE";
//    public static final String EXTRA_MESSAGE1 = "com.example.myassignmentbynabanita.MESSAGE1";
    private static final int SPEECH_REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            EditText editText = (EditText) findViewById(R.id.editText);
            editText.setText(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void clearText(View view){
//        EditText editText = (EditText) findViewById(R.id.editText);
//        editText.setText("");
    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (message.isEmpty()) {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }
//        putExtra takes key , value pair and puts it in the intent. Key should be accessible from
//        other components, hence  Extra_message is public
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void getVoiceInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// This starts the activity and populates the intent with the speech text.
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

//    public void testsendMessage(View view) {
//        // Do something in response to button
//        Intent intent1 = new Intent(this, SecondActivity.class);
//        Intent intent2 = new Intent(this, TestActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
////        putExtra takes key , value pair and puts it in the intent. Key should be accessible from
////        other components, hence  Extra_message is public
//        intent1.putExtra(EXTRA_MESSAGE, message);
//        intent2.putExtra(EXTRA_MESSAGE1,"test");
//        startActivity(intent2);
//    }
}