package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Monster;

/**
 * Abstract class that contains the attributes of the monsters and the implementation of the methods,
 * defined in the {@link Monster} interface.
 * @author mariyan.topalov
 */
public abstract class AbstractMonster implements Monster {

    private final int moveRange;

    private final int attackRange;

    private final int damage;

    private final char symbol;

    private int healthPoints;

    private Point location;

    protected AbstractMonster(int moveRange, int attackRange, int damage, int healthPoints, char symbol, Point location) {
        this.moveRange = moveRange;
        this.attackRange = attackRange;
        this.symbol = symbol;
        this.damage = damage;
        this.healthPoints = healthPoints;
        this.location = location;
    }


    public char getSymbol() {
        return symbol;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    private void decreaseHealthPoints(int points) {
        this.healthPoints -= points;
    }

    public Point getLocation() {
        return location;
    }

    private void setLocation(Point location) {
        this.location = location;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return healthPoints <= 0;
    }

    /**
     * {@inheritDoc}
     * Before changing location, it validates if the new location is in {@link Monster} range.
     * @param point {@link Point} - the new location
     * @return {@inheritDoc}
     */
    @Override
    public boolean move(Point point) {
        if (validate(point, moveRange)) {
            setLocation(point);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Before attacking, it validates if the attacked monster, given as param, is in the attack range of the current monster.
     * @param monster {@link Monster} - monster, being attacked by current monster
     * @return {@inheritDoc}
     */
    @Override
    public boolean attack(Monster monster) {
        AbstractMonster abstractMonster = (AbstractMonster) monster;
        if (validate(abstractMonster.getLocation(), attackRange)) {
            monster.beDamageBy(damage);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * @param damage {@link Integer} - the amount of health points to be decreased from monster's health points.
     */
    @Override
    public void beDamageBy(int damage) {
        this.decreaseHealthPoints(damage);
    }

    /**
     * Validates if the current monster is dead and also if the location, given as param, is in monster's range.
     * @param point {@link Point} - the location to be checked
     * @param range {@link Integer} - the range of the current monster
     * @return {@link Boolean} - true if the monster is alive and the location, given as param, is in current monster's range, otherwise false.
     */
    private boolean validate(Point point, int range) {
        return this.isDead() || this.getLocation().calculateDistance(point) <= range;
    }
}
