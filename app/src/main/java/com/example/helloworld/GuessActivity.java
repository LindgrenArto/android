package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GuessActivity extends AppCompatActivity {

    private static final String TAG = "Guess activity";

    Button heads;
    Button tails;
    TextView totalWins = findViewById(R.id.wins;
    TextView scoreView = findViewById(R.id.highScore) ;
    ProgressBar scoreBar = findViewById(R.id.scoreBar);
    int currentWins;
    int highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Log.i(TAG, "Guess Activity launched!");

        heads = findViewById(R.id.heads);
        tails = findViewById(R.id.tails);
    }

    public int getRnd() {
        Random rnd = new Random();
        return rnd.nextInt(2);
    }
    public void setHighScore(int score) {
        scoreView.append(Integer.toString(score));
    }
    public void setWinnings(){
        String winnings = Integer.toString(currentWins);
        totalWins.setText(winnings);

    }


    public void onClick(View view){
        int rnd = getRnd();
        switch (view.getId()){
            case R.id.heads:
                if(rnd == 0){
                    currentWins++;
                    setWinnings();
                } else {
                    currentWins = 0;
                    totalWins.setText("0");
                }
                break;

            case R.id.tails:
                if(rnd == 1){
                    currentWins++;
                    setWinnings();
                } else {
                    currentWins = 0;
                    totalWins.setText("0");
                }
                break;

                default:
                    break;

        }
    }
}
