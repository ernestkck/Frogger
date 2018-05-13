package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Sprite {
    Pos pos;
    public void draw(Canvas c, Paint p, Bitmap image){
        int h = c.getHeight();
        int w = c.getWidth();
        c.drawBitmap(image, pos.x * w - image.getWidth() / 2, pos.y * h, p);
    };
    public abstract void step();
}
