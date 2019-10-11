package com.scalefocus.monstergame.board;

public class Point {

    private final int x;

    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int calculateDistance(Point b) {
        return (int) Math.hypot(Math.abs(b.getY() - this.getY()), Math.abs(b.getX() - this.getX()));
    }



}
