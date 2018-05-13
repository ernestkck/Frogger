package com.example.ernes.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Game {

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;
    static final int LOGROWS = 3;
    static final int TRUCKROWS = 3;

    public static final float TRUCKSTEP = 0.06f;
    public static final float LOG = 0.06f;

    private Frog frog;

    private boolean frogHit;


    public Game(){
        frog = new Frog(0.5f, 0.9f);
    }

    public void draw(Canvas canvas, Paint paint){

        // Draw river
        paint.setColor(Color.CYAN);
        canvas.drawRect(0, 120, canvas.getWidth() , 650, paint);
        
        

    }

    public void touch(float x, float y){

    }

    public void step() {
    }

    public boolean frogKilled() {
        return false;
    }

    public boolean hasWon() {
        return false;
    }
}
