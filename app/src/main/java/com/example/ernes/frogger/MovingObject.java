package com.example.ernes.frogger;

public class MovingObject {
    Pos pos;
    float speed, length;

    MovingObject(float x, float y, float speed, float length){
        this.pos = new Pos(x, y);
        this.speed = speed;
        this.length = length;
    }
}
