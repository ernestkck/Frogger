package com.example.ernes.frogger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;

public class Game {

    private static final float MAXXY = 1.0f;
    private static final float MINXY = 0.0f;
    private static final int LOGROWS = 3;
    private static final int TRUCKROWS = 3;

    private static final int SWIPE_THRESHOLD = 100;


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

    public void draw(Canvas canvas, Paint paint, Bitmap frogImage, Bitmap logImage, Bitmap[] truckImages){

        // Draw river
        paint.setColor(Color.CYAN);
        canvas.drawRect(0, 120, canvas.getWidth() , 650, paint);

        frog.draw(canvas, paint, frogImage);
        logs.draw(canvas, paint, logImage);
        trucks.draw(canvas, paint, truckImages);

    }

    public void touch(MotionEvent e1, MotionEvent e2, float x, float y){
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();
        if(Math.abs(diffX) > Math.abs(diffY)){
            if(Math.abs(diffX) > SWIPE_THRESHOLD) {
                if (e1.getX() < e2.getX()) frog.pos.x += 0.05f;
                else if (e1.getX() > e2.getX()) frog.pos.x -= 0.05f;
            }
        }
        else{
            if(Math.abs(diffY) > SWIPE_THRESHOLD) {
                if (e1.getY() < e2.getY()) frog.pos.y += 0.05f;
                else if (e1.getY() > e2.getY()) frog.pos.y -= 0.05f;
            }
        }
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
