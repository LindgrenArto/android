package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GuessActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Guess activity";

    Button heads;
    Button tails;
    TextView totalWins;
    TextView scoreView;
    int currentWins;
    int highScore;
    private String best = "HighScore";
    ImageView rightAnswerImage, wrongAnswerImage;
    Animation rightAnimation, wrongAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Log.i(TAG, "Guess Activity launched!");

        totalWins = findViewById(R.id.wins);
        scoreView = findViewById(R.id.highScore);
        heads = findViewById(R.id.heads);
        heads.setOnClickListener(this);
        tails = findViewById(R.id.tails);
        tails.setOnClickListener(this);
        rightAnswerImage = findViewById(R.id.rightAnswer);
        wrongAnswerImage = findViewById(R.id.wrongAnswer);

        Intent intent = new Intent(this, GuessActivity.class);
        intent.putExtra(best, highScore);

        Bundle extras = getIntent().getExtras();
        if( extras == null) {
            highScore = 0;
        } else {
            highScore = extras.getInt(best);
            scoreView.setText(Integer.toString(highScore));
        }

        rightAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rightanswer);

        wrongAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.wronganswer);

        rightAnswerImage.setVisibility(View.INVISIBLE);
        wrongAnswerImage.setVisibility(View.INVISIBLE);
    }

    public int getRnd() {
        Random rnd = new Random();
        return rnd.nextInt(2);
    }
    public void setHighScore(int score) {
        Log.i("score", Integer.toString(score));
        if(score > highScore) {
            highScore = score;
            scoreView.setText(Integer.toString(score));
        }
    }
    public void setWinnings(){
        String winnings = Integer.toString(currentWins);
        totalWins.setText(winnings);

    }
    public void zeroWins() {
        currentWins = 0;
        String lost = Integer.toString(currentWins);
        totalWins.setText(lost);
    }


    public void onClick(View view){
        int rnd = getRnd();
        switch (view.getId()){
            case R.id.heads:
                if(rnd == 0){
                    currentWins++;
                    setWinnings();
                    rightAnswerImage.startAnimation(rightAnimation);
                    setHighScore(currentWins);
                } else {
                    currentWins = 0;
                    zeroWins();
                    wrongAnswerImage.startAnimation(wrongAnimation);
                }
                break;

            case R.id.tails:
                if(rnd == 1){
                    currentWins++;
                    setWinnings();
                    rightAnswerImage.startAnimation(rightAnimation);
                    setHighScore(currentWins);
                } else {
                    currentWins = 0;
                    zeroWins();
                    wrongAnswerImage.startAnimation(wrongAnimation);
                }
                break;

            default:
                break;

        }
    }

    public void onRefresh(View view) {
        Intent intent = new Intent(this, GuessActivity.class);
        intent.putExtra(best, highScore);
        startActivity(intent);
    }
}