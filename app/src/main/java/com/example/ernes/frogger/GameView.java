package com.example.ernes.frogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameView extends View implements View.OnTouchListener, Runnable {
    float viewWidth, viewHeight;
    float xpos, ypos, radius = 60.0f, xt, yt, speed;
    Handler timer;
    int score = 0;
    boolean xdir, ydir;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        timer = new Handler();
        timer.postDelayed(this, 10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        xpos = viewWidth/2;
        ypos = viewHeight/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(70);
        canvas.drawText("Score: "+ score, 100,100, p);

        p = new Paint();
        p.setColor(Color.RED);
        canvas.drawCircle(xpos, ypos, radius, p);

        p.setColor(Color.YELLOW);
        canvas.drawCircle(xt, yt, 20.0f, p);

    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xt = event.getX();
            yt = event.getY();
            if(yt >= viewHeight - 100) score = 0;
            else if(dist(xpos, ypos, xt, yt) <= radius) score++;

            updateSpeed();

            this.invalidate();
        }
        return true;
    }

    @Override
    public void run(){
        if(xdir){
            xpos += speed;
            if(xpos >= viewWidth-radius) xdir = false;
        }
        else{
            xpos -= speed;
            if(xpos <= radius) xdir = true;
        }
        if(ydir){
            ypos += speed;
            if(ypos >= viewHeight - 100 - 2*radius) ydir = false;
        }
        else{
            ypos -= speed;
            if(ypos <= radius) ydir = true;
        }
        this.invalidate();
        timer.postDelayed(this, 10);
    }


    private void updateSpeed(){
        Random rand = new Random();
        xdir = rand.nextBoolean();
        ydir = rand.nextBoolean();
        speed = (float) (rand.nextFloat()+0.3) * 8;
    }
    private float dist(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}


