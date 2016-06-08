package com.testing.atul.movethebox;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SPEECH_REQUEST_CODE = 0;
    ImageView box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        box = (ImageView)findViewById(R.id.box1);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void listen(View v)
    {
        Intent in = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        in.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        in.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

        startActivityForResult(in, SPEECH_REQUEST_CODE);   //0 is the ID to request voice recognition
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {

        List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String spokenText = results.get(0);
        if(spokenText.contains("up"))
            Toast.makeText(this, "Moving up", Toast.LENGTH_SHORT).show();
        else if(spokenText.contains("down"))
            Toast.makeText(this, "Moving down", Toast.LENGTH_SHORT).show();
        else if(spokenText.contains("left"))
            Toast.makeText(this, "Moving left", Toast.LENGTH_SHORT).show();
        else if(spokenText.contains("right"))
            Toast.makeText(this, "Moving right", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
