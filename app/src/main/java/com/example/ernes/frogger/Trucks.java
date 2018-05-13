package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Trucks extends ArrayList<Truck> {
    public static final float TRUCKSTEP = 0.06f;

    public void draw(Canvas c, Paint p, Bitmap[] truckImages) {
        for(Truck truck : this) truck.draw(c, p, truckImages);
    }


    public void step() {
    }
}
