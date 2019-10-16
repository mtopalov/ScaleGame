package com.scalefocus.monstergame.board;

/**
 * Class that provides simple implementation of 2D Point.
 * Holds fields for x and y,
 * and a method that calculates the distance between two Points.
 *
 * @author mariyan.topalov
 */
public class Point {

    private final int x;

    private final int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    /**
     * Calculates the distance between two Points.
     *
     * @param point - The {@link Point} to which to calculate the distance from current point
     * @return the distance between this Point and the other Point
     */
    public int calculateDistance(Point point) {
        return (int) Math.hypot(Math.abs(point.getY() - this.getY()), Math.abs(point.getX() - this.getX()));
    }
}
