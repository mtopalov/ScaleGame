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
     * The method is based on the Manhattan distance.
     * Returns  sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>) as {@link Integer}
     *
     * @param point - The {@link Point} to which to calculate the distance from current point
     * @return  {@link Integer} - the distance between current {@link Point} and the passed argument {@link Point}.
     */
    public int calculateDistance(Point point) {
        return (int) Math.hypot(Math.abs(point.getY() - this.getY()), Math.abs(point.getX() - this.getX()));
    }
}
