package com.example.ernes.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Game {

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;
    static final int LOGROWS = 3;
    static final int TRUCKROWS = 3;


    private Frog frog;
    private Logs logs;
    private Trucks trucks;

    private boolean frogKilled;


    public Game(){
        frog = new Frog(0.5f, 0.9f);
        logs = new Logs();
        trucks = new Trucks();
    }

    public void draw(Canvas canvas, Paint paint){

        // Draw river
        paint.setColor(Color.CYAN);
        canvas.drawRect(0, 120, canvas.getWidth() , 650, paint);

        frog.draw(canvas, paint);
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
