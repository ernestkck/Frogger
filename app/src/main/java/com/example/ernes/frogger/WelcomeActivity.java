package com.example.ernes.frogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 *  The activity that holds the welcome screen
 *  @author Ernest Kwan (u6381103)
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void playGame(View view) {
        Log.d("game", "button clicked");

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
