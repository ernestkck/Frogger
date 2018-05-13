package com.example.ernes.frogger;

public class Game {

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;
    static final int LOGROWS = 3;
    static final int TRUCKROWS = 3;

    public static final float TRUCKSTEP = 0.06f;
    public static final float LOG = 0.06f;

    private Frog frog;

    private boolean frogHit;

    public Game(){
        frog = new Frog(0.5f, 0.9f);
    }


}
