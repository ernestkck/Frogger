package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 *  Trucks that move below the river
 *  @author Ernest Kwan (u6381103)
 */

public class Truck extends Sprite {

    private static final float VELOCITY = 0.06f;
    float v;
    Random random = new Random();

    public Truck(){
        this.v = VELOCITY;
    }

    public void draw(Canvas c, Paint p, Bitmap[] images) {
        super.draw(c,p, images[random.nextInt(images.length)]);
    }

    @Override
    public void step() {
        pos.x += v;
    }
}
