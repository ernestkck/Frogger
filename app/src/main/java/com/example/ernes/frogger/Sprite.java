package com.example.ernes.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Sprite {
    Pos pos;
    public abstract void draw(Canvas c, Paint p);
    public abstract void draw(Canvas c, Paint p, Bitmap image);
    public abstract void step();
}
