package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GuessActivity extends AppCompatActivity {

    private static final String TAG = "Guess activity";

    private Button heads;
    private Button tails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Log.i(TAG, "Guess Activity launched!");

        heads = findViewById(R.id.heads);
        tails = findViewById(R.id.tails);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.heads:
                Log.i(TAG, "heads");
                break;

            case R.id.tails:
                Log.i(TAG, "tails");
                break;

                default:
                    break;

        }
    }
}
