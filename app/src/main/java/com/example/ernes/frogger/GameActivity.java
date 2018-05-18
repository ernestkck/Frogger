package com.example.ernes.frogger;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 *  The activity that holds the game
 *  @author Ernest Kwan (u6381103)
 */

public class GameActivity extends AppCompatActivity implements GameOver{

    private GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameView gameView = (GameView) findViewById(R.id.gameview);
        gestureDetector = new GestureDetector(this, gameView);
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        gameView.registerGameOver(this);
    }

    @Override
    public void gameOver(int cond) {
        if(cond == 1) Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show();
        else  Toast.makeText(this, "You Lose!", Toast.LENGTH_LONG).show();
        setResult(AppCompatActivity.RESULT_OK);
        finish();
    }

}
