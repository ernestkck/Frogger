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

public class Game {

    private static final float MAXXY = 1.0f;
    private static final float MINXY = 0.0f;
    private static final int LOGROWS = 3;
    private static final int TRUCKROWS = 3;

    private static final int SWIPE_THRESHOLD = 100;


    Frog frog;
    private Logs logs;
    private Trucks trucks;

    ArrayList<Logs> logss;

    private boolean frogKilled;


    public Game(){
        frog = new Frog();
        logs = new Logs(true, 0.3f);

        logs.add(new Log(0, 0.3f));

        /*for(int i = 0; i < LOGROWS; i++){
            logs[i] = new Logs(i%2 == 0, 0.3f);
            logs[i].add(new Log(0, 0.3f));
        }*/
        /*Trucks[] trucks = new Trucks[TRUCKROWS];
        for(int i = 0; i < TRUCKROWS; i++){
            trucks[i] = new Trucks(i%2 == 0);
        }*/
        frogKilled = false;
    }

    public void draw(Canvas canvas, Paint paint, Bitmap frogImage, Bitmap logImage, Bitmap[] truckImages){

        // Draw river
        paint.setColor(Color.CYAN);
        canvas.drawRect(0, 120, canvas.getWidth() , 650, paint);

        frog.draw(canvas, paint, frogImage);
        logs.draw(canvas, paint, logImage);
        //logss.get(0).draw(canvas, paint, logImage);
        /*logs[0].add(new Log(0, 0.03f));
        logs[0].get(0).draw(canvas, paint, logImage);*/
        //for(Logs logrow : logs) logrow.draw(canvas, paint, logImage);
        //for(Trucks truckrow : trucks) truckrow.draw(canvas, paint, truckImages);

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
        //for(Logs logrow : logs) logrow.step();
        //for(Trucks truckrow : trucks) truckrow.step();
    }

    public boolean frogKilled() {
        return false;
    }

    public boolean hasWon() {
        return false;
    }
}
