package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *  A simple frog sprite
 *  @author Ernest Kwan (u6381103)
 */

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
