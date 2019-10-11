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

    @Override
    public Point getInitialLocation() {
        return initialLocation;
    }

    @Override
    public void setInitialLocation(Point initialLocation) {
        this.initialLocation = initialLocation;
    }

    @Override
    public boolean isRemoved() {
        return removed;
    }

    @Override
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public int getMovesPerTurn() {
        return movesPerTurn;
    }

    public int getAttackRadius() {
        return attackRadius;
    }

    @Override
    public int getInitialHealthPoints() {
        return initialHealthPoints;
    }

    @Override
    public char getMonsterSymbol() {
        return monsterSymbol;
    }

    @Override
    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    @Override
    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    @Override
    public Point getNewLocation() {
        return newLocation;
    }

    @Override
    public void setNewLocation(Point newLoPosition) {
        this.newLocation = newLoPosition;
    }

    @Override
    public Point getCurrentLocation() {
        return currentLocation;
    }

    @Override
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
