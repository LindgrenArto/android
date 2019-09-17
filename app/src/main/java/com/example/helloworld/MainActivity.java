package com.example.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MyActivity";

    ImageButton webViewButton;
    Button testButton;
    TextView testText;
    Button headsOrTails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewButton = findViewById(R.id.webViewButton);
        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(this);
        testText = findViewById(R.id.testText);
        headsOrTails = findViewById(R.id.headsOrTailsButton);
    }

    public void activateGuess(View view) {
        Intent intent = new Intent(this, GuessActivity.class);
        startActivity(intent);
    }
    public void activateWebPage(View view) {
        Uri webPage = Uri.parse("https://google.fi");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }

    public void setTextViewVisibility() {
        testText.setVisibility(testText.getVisibility() == View.VISIBLE ? View.GONE: View.VISIBLE);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.testButton:
                Log.i(TAG, "working");
                setTextViewVisibility();
                break;

            case R.id.headsOrTailsButton:
                activateGuess(view);
                break;

            case R.id.webViewButton:
                activateWebPage(view);
                break;
        }
    }
}
