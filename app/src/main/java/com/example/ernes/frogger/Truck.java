package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Truck extends Sprite {
    float v;
    Random random = new Random();

    public Truck(float v){
        this.v = v;
    }

    public void draw(Canvas c, Paint p, Bitmap[] images) {
        super.draw(c,p, images[random.nextInt(images.length)]);
    }

    @Override
    public void step() {
        pos.x += v;
    }
}
