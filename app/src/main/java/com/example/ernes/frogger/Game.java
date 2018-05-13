package com.example.ernes.frogger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class Game {

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;
    static final int LOGROWS = 3;
    static final int TRUCKROWS = 3;


    Frog frog;
    private Logs logs;
    private Trucks trucks;

    private boolean frogKilled;


    public Game(){
        frog = new Frog();
        logs = new Logs();
        trucks = new Trucks();
        frogKilled = false;
    }

    public void draw(Canvas canvas, Paint paint, Bitmap frogImage){

        // Draw river
        paint.setColor(Color.CYAN);
        canvas.drawRect(0, 120, canvas.getWidth() , 650, paint);

        frog.draw(canvas, paint, frogImage);
        logs.draw(canvas, paint);
        trucks.draw(canvas, paint);

    }

    public void touch(float x, float y){


        // TODO: implement controls for movement of frog, 4 IF cases

    }

    public void step() {
        logs.step();
        trucks.step();
    }

    public boolean frogKilled() {
        return false;
    }

    public boolean hasWon() {
        return false;
    }
}
