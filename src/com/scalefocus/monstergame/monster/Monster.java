package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.IMonster;

public abstract class Monster implements IMonster {

    private final int movesPerTurn;

    private final int attackRadius;

    private final int initialHealthPoints;

    private final char monsterSymbol;

    private int damage;

    private int currentHealthPoints;

    private Point newLocation;

    private Point currentLocation;

    private Point initialLocation;

    private boolean removed;

    public Monster(int movesPerTurn, int attackRadius, int damage, int healthPoints, char monsterSymbol) {
        this.movesPerTurn = movesPerTurn;
        this.attackRadius = attackRadius;
        this.damage = damage;
        this.initialHealthPoints = healthPoints;
        this.monsterSymbol = monsterSymbol;
        currentHealthPoints = initialHealthPoints;
    }

    int getMovesPerTurn() {
        return movesPerTurn;
    }

    int getAttackRadius() {
        return attackRadius;
    }

    public int getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public char getMonsterSymbol() {
        return monsterSymbol;
    }



    public Point getInitialLocation() {
        return initialLocation;
    }

    public void setInitialLocation(Point initialLocation) {
        this.initialLocation = initialLocation;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public Point getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(Point newLoPosition) {
        this.newLocation = newLoPosition;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public boolean isDead() {
        if (this.currentHealthPoints <= 0) {
            currentHealthPoints = 0;
            return true;
        }
        return false;
    }
}
