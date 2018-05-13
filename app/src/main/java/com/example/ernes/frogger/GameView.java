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
    public static final int STEPDELAY= 50;
    float viewWidth, viewHeight;
    float frogX, frogY, radius = 60.0f, xt, yt, speed;

    float[] carY = new float[3], logY = new float[3];
    ArrayList<MovingObject> cars = new ArrayList<>();
    ArrayList<MovingObject> logs = new ArrayList<>();
    Handler timer;
    Game game;

    Paint paint;
    Bitmap frogImage;
    Bitmap[] truckImages;
    Bitmap logImage;
    ArrayList<GameOver> observers;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3.0f);
        this.setOnTouchListener(this);
        observers = new ArrayList<GameOver>();
        game = new Game();
        timer = new Handler();
        timer.postDelayed(this, STEPDELAY);

        frogImage = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
        logImage = BitmapFactory.decodeResource(getResources(), R.drawable.log);
        truckImages = new Bitmap[4];
        truckImages[0] = BitmapFactory.decodeResource(getResources(), R.drawable.truck);
        truckImages[1] = BitmapFactory.decodeResource(getResources(), R.drawable.truck_2);
        truckImages[2] = BitmapFactory.decodeResource(getResources(), R.drawable.truck_4);
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
        game.draw(canvas, paint, frogImage, logImage, truckImages);
        // Draw click location
        paint.setColor(Color.RED);
        canvas.drawCircle(xt, yt, 20.0f, paint);
    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float w = (float) view.getWidth();
            float h = (float) view.getHeight();
            xt = event.getX();
            yt = event.getY();
            game.touch(xt / w, yt / h);
            this.invalidate();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){

        }
        return true;
    }

    @Override
    public void run(){
        if(step()){
            timer.postDelayed(this, STEPDELAY);
        }
    }


    public boolean step(){
        game.step();
        if(game.hasWon() || game.frogKilled()){
            notifyGameOver();
            return false;
        }
        this.invalidate();
        return true;
    }

    private void notifyGameOver() {
        for (GameOver o : observers) o.gameOver();
    }

    public void registerGameOver(GameOver gameover) {
        observers.add(gameover);
    }

}


