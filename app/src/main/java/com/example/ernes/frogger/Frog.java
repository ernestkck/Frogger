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
    public void step() {

    }
}
