package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Log extends Sprite {

    float v;

    public Log(float v){
        this.v = v;
    }

    @Override
    public void step() {
        pos.x += v;
    }
}
