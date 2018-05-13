package com.example.ernes.frogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity implements GameOver{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameView gameView = (GameView) findViewById(R.id.gameview);
        gameView.registerGameOver(this);
    }

    @Override
    public void gameOver() {
        setResult(AppCompatActivity.RESULT_OK);
        finish();
    }
}
