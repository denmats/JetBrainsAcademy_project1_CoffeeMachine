package com.denmats.ai;

public class PointTSP {
    private final char counter;
    private final double x;
    private final double y;

    public PointTSP(char counter, double x, double y) {
        this.counter = counter;
        this.x = x;
        this.y = y;
    }

    public char getCounter() {
        return counter;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return counter + "("+x+","+y+")";
    }
}
