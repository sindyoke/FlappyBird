package com.sindyoke;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView tvScore, tvMaxScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        int score = getIntent().getIntExtra("score", 0);

        SharedPreferences preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int maxScore = preferences.getInt("maxScore", 0);
        if(score > maxScore){
            maxScore = score;
            preferences.edit().putInt("maxScore", maxScore).commit();
        }

        tvScore = findViewById(R.id.tv_score);
        tvMaxScore = findViewById(R.id.tv_best);
        tvScore.setText(""+score);
        tvMaxScore.setText(""+maxScore);
    }


    public void restartGame(View view) {
        MainActivity.start(this);
        finish();
    }

    public void exitGame(View view) {
        finish();
    }

    public static void start(Context context, int score){
        Intent intent = new Intent(context, GameOverActivity.class);
        intent.putExtra("score", score);
        context.startActivity(intent);
    }
}
