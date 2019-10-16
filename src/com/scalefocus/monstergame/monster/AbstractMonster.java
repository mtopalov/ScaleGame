package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Monster;

/**
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

    public int getDamage() {
        return damage;
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

    @Override
    public boolean isDead() {
        return healthPoints <= 0;
    }

    /**
     * {@inheritDoc}
     * This implementation validates is the move possible
     * @param point {@link Point}
     * @return {@link Boolean} - is the move successful (0 : no, 1 : yes)
     */
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
            monster.beDamageBy(damage);
            return true;
        }
        return false;
    }

    @Override
    public void beDamageBy(int damage) {
        this.decreaseHealthPoints(damage);
    }

    private boolean validate(Point point, int range) {
        return this.isDead() || this.getLocation().calculateDistance(point) <= range;
    }
}
