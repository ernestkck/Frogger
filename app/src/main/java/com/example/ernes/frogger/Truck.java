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

    private static final float VELOCITY = 0.01f;
    float v;
    int i;
    Random random;

    public Truck(float x, float y){
        random = new Random();
        this.i = random.nextInt(3);
        this.v = VELOCITY;
        this.pos = new Pos(x, y);
    }

    public void draw(Canvas c, Paint p, Bitmap[] images) {
        super.draw(c,p, images[i]);
    }

    @Override
    public void step() {
        pos.x += v;
    }
}
