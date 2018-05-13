package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Frog extends Sprite {
    private static final float STARTX = 0.5f;
    private static final float STARTY = 0.9f;
    public Frog() {
        pos = new Pos(STARTX, STARTY);
    }

    @Override
    public void draw(Canvas c, Paint p) {

    }

    @Override
    public void draw(Canvas c, Paint p, Bitmap image) {
        int h = c.getHeight();
        int w = c.getWidth();
        c.drawBitmap(image, pos.x * w - image.getWidth() / 2, pos.y * h, p);
    }

    @Override
    public void step() {

    }
}
