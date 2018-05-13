package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Logs extends ArrayList<Log> {
    float y;
    public static final float LOGSTEP = 0.06f;
    boolean movingRight;
    private Random random = new Random();

    public Logs(boolean movingRight, float y){
        this.movingRight = movingRight;
        this.y = y;
    }

    public void draw(Canvas c, Paint p, Bitmap logImage) {
        for(Log log : this) log.draw(c, p, logImage);
    }

    public void step() {
        if(this.size()<3){
            if(movingRight) this.add(new Log(0, y));
            else this.add(new Log(1.0f, y));
        }
    }
}
