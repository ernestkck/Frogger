package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Logs extends ArrayList<Log> {

    public static final float LOGSTEP = 0.06f;
    private Random random = new Random();
    boolean movingLeft;


    public void draw(Canvas c, Paint p, Bitmap logImage) {
        for(Log log : this) log.draw(c, p, logImage);
    }

    public void step() {
    }
}
