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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements View.OnTouchListener, Runnable {
    float viewWidth, viewHeight;
    float frogX, frogY, radius = 60.0f, xt, yt, speed;
    float[] carY = new float[3], logY = new float[3];
    ArrayList<MovingObject> cars = new ArrayList<>();
    ArrayList<MovingObject> logs = new ArrayList<>();
    Handler timer;
    int score = 0;

    BitmapFactory.Options opts = new BitmapFactory.Options();
    Bitmap frogImage = BitmapFactory.decodeResource(getResources(), R.drawable.frog, opts);
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
        Log.d("game", "onSizeChanged!");
        viewWidth = w;
        viewHeight = h;
        frogX = viewWidth / 2 - frogImage.getWidth()/2;
        frogY = viewHeight - 150;
        Log.d("game", "Frog width: "+  frogImage.getWidth() + " height: " + frogImage.getHeight());

        carY[0] = viewHeight - 300;
        carY[1] = viewHeight - 480;
        carY[2] = viewHeight- 660;
        logY[0] = 200;
        logY[1] = 350;
        logY[2] = 500;

        cars.add(new MovingObject(0, carY[0], 1.5f, 200.0f));
        cars.add(new MovingObject(viewWidth, carY[1], -1.5f, 200.0f));
        cars.add(new MovingObject(0, carY[2], 1.5f, 200.0f));

        logs.add(new MovingObject(viewWidth, logY[0], -1.5f, 200.0f));
        logs.add(new MovingObject(0, logY[1], 1.5f, 200.0f));
        logs.add(new MovingObject(viewWidth, logY[2], -1.5f, 200.0f));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = 0;

        /*p.setColor(Color.BLACK);
        p.setTextSize(70);
        canvas.drawText("Score: "+ score, 100,100, p);*/

        // Draw river
        p.setColor(Color.CYAN);
        canvas.drawRect(0, 120, viewWidth , 650, p);
        //canvas.drawCircle(viewWidth/2, viewHeight-100, radius, p);

        // Draw frog
        canvas.drawBitmap(frogImage, frogX, frogY, p);

        // Draw click location
        p.setColor(Color.RED);
        canvas.drawCircle(xt, yt, 20.0f, p);

        // Draw cars
        p.setColor(Color.BLACK);
        for(MovingObject c : cars)
            canvas.drawRect(c.x, c.y, c.x+c.length, c.y+80.0f, p);

        // Draw logs
        p.setColor(Color.parseColor("#A52A2A"));
        for(MovingObject c : logs)
            canvas.drawRect(c.x, c.y, c.x+c.length, c.y+80.0f, p);
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
        for(MovingObject c : cars) {
            c.x += c.speed;
        }
        for(MovingObject c : logs) {
            c.x += c.speed;
        }
        this.invalidate();

        timer.postDelayed(this, 10);
    }


    private void updateSpeed(){
        Random rand = new Random();
        speed = (float) (rand.nextFloat()+0.3) * 8;
    }

}


