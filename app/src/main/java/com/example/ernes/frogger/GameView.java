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

    BitmapFactory.Options opts = new BitmapFactory.Options();
    Bitmap frogImage = BitmapFactory.decodeResource(getResources(), R.drawable.frog, opts);
    Paint paint;

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
        game.draw(canvas, paint);

        /*p.setColor(Color.BLACK);
        p.setTextSize(70);
        canvas.drawText("Score: "+ score, 100,100, p);*/

    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        /*if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xt = event.getX();
            yt = event.getY();
            updateSpeed();
            this.invalidate();
        }*/
        float w = (float) view.getWidth();
        float h = (float) view.getHeight();
        game.touch(event.getX() / w, event.getY() / h);
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


