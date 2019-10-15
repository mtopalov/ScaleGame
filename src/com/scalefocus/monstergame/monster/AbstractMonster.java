package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Monster;

/**
 * @author mariyan.topalov
 */
public abstract class AbstractMonster implements Monster {

    private final int moveRange;

    private final int attackRange;

    private final char symbol;

    private final int damage;

    private int healthPoints;

    private Point location;

    private boolean removed;

    public AbstractMonster(int moveRange, int attackRange, int damage, int healthPoints, char symbol, Point location) {
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

    public int getDamage() {
        return damage;
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

    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean isDead() {
        return healthPoints <= 0;
    }

    @Override
    public boolean move(Point point) {
        if (validate(point, moveRange)) {
            setLocation(point);
            return true;
        }

        return false;
    }

    @Override
    public boolean attack(Monster monster) {
        AbstractMonster abstractMonster = (AbstractMonster) monster;
        if (validate(abstractMonster.getLocation(), attackRange)) {
            return false;
        }
        monster.beDamageBy(damage);
        return true;
    }

    @Override
    public void beDamageBy(int damage) {
        this.decreaseHealthPoints(damage);
    }

    private boolean validate(Point point, int range) {
        return this.isDead() || this.getLocation().calculateDistance(point) > range;
    }
}
