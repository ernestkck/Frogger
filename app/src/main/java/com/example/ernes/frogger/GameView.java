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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 *  This is the view where the game runs on
 *  @author Ernest Kwan (u6381103)
 */

public class GameView extends View implements Runnable, GestureDetector.OnGestureListener {
    public static final int STEPDELAY= 50;
    float viewWidth, viewHeight;
    float xt, yt;
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
        logImage = Bitmap.createScaledBitmap(logImage, (int) (w*0.3), (int) (h*0.05), true);
        truckImages[0] = Bitmap.createScaledBitmap(truckImages[0], (int) (w*0.25), (int) (h*0.08), true);
        truckImages[1] = Bitmap.createScaledBitmap(truckImages[1], (int) (w*0.25), (int) (h*0.08), true);
        truckImages[2] = Bitmap.createScaledBitmap(truckImages[2], (int) (w*0.25), (int) (h*0.08), true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        game.draw(canvas, paint, frogImage, logImage, truckImages);
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

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v1, float v2) {
        if(Math.abs(v1) < 100 && Math.abs(v2) < 100) return true; // ignore if the swipe is too slow
        game.touch(e1, e2, v1, v2);
        return true;
    }
}


