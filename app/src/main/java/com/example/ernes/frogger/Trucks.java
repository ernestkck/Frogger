package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Trucks extends ArrayList<Truck> {

    public static final float TRUCKSTEP = 0.06f;
    private final boolean movingRight;
    Random random = new Random();

    public Trucks(boolean movingRight){
        this.movingRight = movingRight;
    }

    public void draw(Canvas c, Paint p, Bitmap[] truckImages) {
        for(Truck truck : this) truck.draw(c, p, truckImages);
    }


    public void step() {
    }
}
