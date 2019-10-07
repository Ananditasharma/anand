package com.enuke.unicon.activity;

public class ConfettiSource {
    public final int x0, y0, x1, y1;



    public ConfettiSource(int x, int y) {
        this(x, y, x, y);
    }


    public ConfettiSource(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public float getInitialX(float random) {
        return x0 + (x1 - x0) * random;
    }

  public float getInitialY(float random) {
        return y0 + (y1 - y0) * random;
    }
}