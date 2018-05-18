package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *  @author Ernest Kwan (u6381103)
 */
public class Logs extends ArrayList<Log> {
    private static final float LOGSTEP = 0.01f;
    float y;
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
        // make logs move
        for (Log l : this) l.pos.x += LOGSTEP;

        // remove logs off screen
        Iterator<Log> li = this.iterator();
        while (li.hasNext()) {
            Log l = li.next();
            if (l.pos.x < Game.MINXY || l.pos.x > Game.MAXXY) li.remove();
        }
    }
}
