package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GuessActivity extends AppCompatActivity {

    private static final String TAG = "Guess activity";

    Button heads;
    Button tails;
    TextView totalWins;
    int currentWins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Log.i(TAG, "Guess Activity launched!");

        heads = findViewById(R.id.heads);
        tails = findViewById(R.id.tails);
        totalWins = findViewById(R.id.wins);
    }

    public int getRnd() {
        Random rnd = new Random();
        return rnd.nextInt(2);
    }

    public void winnings(){
        String winnings = Integer.toString(currentWins);
        totalWins.setText(winnings);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.heads:
                Log.i(TAG, "heads");
               Log.i(TAG, Integer.toString(getRnd()));
                if(getRnd() == 0){
                    currentWins++;
                   winnings();
                    Log.i(TAG, "kruuna");
                } else {
                     currentWins = 0;
                  winnings();
                }
                break;

            case R.id.tails:
                Log.i(TAG, "tails");
                Log.i(TAG, Integer.toString(getRnd()));
                if(getRnd() == 1){
                    currentWins++;
                    winnings();
                    Log.i(TAG, "klaava");
                } else {
                    currentWins = 0;
                    winnings();
                }
                break;

                default:
                    break;

        }
    }
}
