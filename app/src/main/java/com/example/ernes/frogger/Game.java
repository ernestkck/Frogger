package com.example.ernes.frogger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 *  The main game class
 *  @author Ernest Kwan (u6381103)
 */

public class Game {

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;
    static final int LOGROWS = 3;
    static final int TRUCKROWS = 3;
    public static final double PROBOFLOG = 0.01;
    public static final double PROBOFTRUCK = 0.01;

    private static final int SWIPE_THRESHOLD = 100;

    Random random = new Random();

    Frog frog;
    private Logs[] logs;
    private Trucks[] trucks;

    private boolean frogKilled, hasWon;

    public Game(){
        frog = new Frog();
        logs = new Logs[LOGROWS];
        trucks = new Trucks[TRUCKROWS];
        for (int i=0; i<LOGROWS;i++){
            logs[i] = new Logs(false, 0.1f + 0.13f*i);
        }

        for (int i=0; i<TRUCKROWS;i++){
            trucks[i] = new Trucks(true, 0.8f - 0.12f*i);
        }

        frogKilled = false;
    }

    public void draw(Canvas canvas, Paint paint, Bitmap frogImage, Bitmap logImage, Bitmap[] truckImages){
        // Draw green finish area
        paint.setColor(Color.GREEN);
        int h = canvas.getHeight();
        canvas.drawRect(0, 0, canvas.getWidth() , (int)(h*0.1), paint);

        // Draw river
        paint.setColor(Color.CYAN);
        canvas.drawRect(0, (int)(h*0.1), canvas.getWidth() , (int)(h*0.45), paint);

        // Draw road
        paint.setColor(Color.GRAY);
        canvas.drawRect(0, (int)(h*0.55), canvas.getWidth() , (int)(h*0.9), paint);

        for(Trucks truckrow : trucks){
            truckrow.draw(canvas, paint, truckImages);
        }
        for(Logs logrow : logs){
            logrow.draw(canvas, paint, logImage);
        }

        frog.draw(canvas, paint, frogImage);
    }

    public void touch(MotionEvent e1, MotionEvent e2, float x, float y){
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();
        if(Math.abs(diffX) > Math.abs(diffY)){
            if(Math.abs(diffX) > SWIPE_THRESHOLD) {
                if (e1.getX() < e2.getX()) frog.pos.x += 0.2f;
                else if (e1.getX() > e2.getX()) frog.pos.x -= 0.2f;
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
        for(Trucks truckrow : trucks){
            truckrow.step();
            if(truckrow.size()<3){
                if(random.nextDouble()<PROBOFTRUCK)
                    truckrow.add(new Truck(0f, truckrow.y));
            }
        }

        for(Logs logrow : logs){
            logrow.step();
            if(logrow.size()<3){
                if(random.nextDouble()<PROBOFLOG)
                    logrow.add(new Log(1.0f, logrow.y));
            }
        }

        // if hit by a truck
        for(Trucks truckrow : trucks) {
            for (Truck t : truckrow) {
                if (Math.abs(t.pos.x - frog.pos.x) < 0.13f && Math.abs(t.pos.y - frog.pos.y) < 0.05f )
                    frogKilled = true;
            }
        }
        boolean onLog = false;
        // if not on log
        for(Logs logrow : logs) {
            for (Log l : logrow) {
                if(frog.pos.y>0.1 && frog.pos.y<0.45) {
                    if (Math.abs(l.pos.x - frog.pos.x) < 0.13f && Math.abs(l.pos.y - frog.pos.y) < 0.02f)
                       onLog = true;
                }
            }
        }
        if (onLog == false) frogKilled = true;
        // Win
        if(frog.pos.y>0.01f && frog.pos.y<0.1f)
            hasWon = true;
    }

    public boolean frogKilled() {
        return frogKilled;
    }

    public boolean hasWon() {
        return hasWon;
    }
}
