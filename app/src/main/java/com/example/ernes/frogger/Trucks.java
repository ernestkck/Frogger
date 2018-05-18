package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *  @author Ernest Kwan (u6381103)
 */

public class Trucks extends ArrayList<Truck> {
    private static final float TRUCKSTEP = 0.015f;
    private final boolean movingRight;
    float y;
    Random random = new Random();

    public Trucks(boolean movingRight, float y){
        this.movingRight = movingRight;
        this.y = y;
    }

    public void draw(Canvas c, Paint p, Bitmap[] truckImages) {
        for(Truck truck : this) truck.draw(c, p, truckImages);
    }


    public void step() {
        // make trucks move
        if(movingRight)
            for (Truck t : this) t.pos.x+=TRUCKSTEP;
        else
            for (Truck t : this) t.pos.x-=TRUCKSTEP;

        // remove trucks off screen
        Iterator<Truck> ti = this.iterator();
        while (ti.hasNext()) {
            Truck t = ti.next();
            if (t.pos.x < Game.MINXY || t.pos.x > Game.MAXXY) ti.remove();
        }
    }
}
