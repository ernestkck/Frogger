package com.example.ernes.frogger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    float frogX, frogY, radius = 60.0f, xt, yt, speed;
    float[][] car = new float[3][], log = new float[3][];
    Handler timer;
    int score = 0;
    boolean xdir;
    Bitmap frogImage = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
    Paint p = new Paint();

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
        frogX = viewWidth / 2 - 25;
        frogY = viewHeight - 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*p.setColor(Color.BLACK);
        p.setTextSize(70);
        canvas.drawText("Score: "+ score, 100,100, p);*/

        // Draw river
        p.setColor(Color.CYAN);
        canvas.drawRect(0, 150, viewWidth , 700, p);
        //canvas.drawCircle(viewWidth/2, viewHeight-100, radius, p);

        // Draw frog
        canvas.drawBitmap(frogImage, frogX, frogY, p);

        // Draw click location
        p.setColor(Color.YELLOW);
        canvas.drawCircle(xt, yt, 20.0f, p);

        p.setColor(Color.RED);
        canvas.drawLine(viewWidth/2, viewHeight/2, viewWidth/2, viewHeight/2 + 100, p);
    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xt = event.getX();
            yt = event.getY();


            updateSpeed();

            this.invalidate();
        }
        return true;
    }

    @Override
    public void run(){
        if(xdir){
            //xpos += speed;
            //if(xpos >= viewWidth-radius) xdir = false;
        }
        else{
            //xpos -= speed;
            //if(xpos <= radius) xdir = true;
        }
        /*if(ydir){
            ypos += speed;
            if(ypos >= viewHeight - 100 - 2*radius) ydir = false;
        }
        else{
            ypos -= speed;
            if(ypos <= radius) ydir = true;
        }*/
        this.invalidate();
        timer.postDelayed(this, 10);
    }


    private void updateSpeed(){
        Random rand = new Random();
        speed = (float) (rand.nextFloat()+0.3) * 8;
    }
    private float dist(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}


