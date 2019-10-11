package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.IMonster;

public class Demon extends Monster {


    public Demon() {
        super(2, 2, 2, 50, '*');
    }

    @Override
    public boolean move(Point point) {
        if (this.isDead()) return false;

        if (this.getNewLocation().calculateDistance(point) <= this.getMovesPerTurn()) {
            setCurrentLocation(getNewLocation());
            setNewLocation(point);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(IMonster attackedMonster) {
        if (this.isDead()) return false;

        if (this.getNewLocation().calculateDistance(attackedMonster.getNewLocation()) <= this.getAttackRadius()) {
            attackedMonster.beDamageBy(this.getDamage());
            return true;
        }
        return false;
    }

    @Override
    public void beDamageBy(int damage) {
        setCurrentHealthPoints(getCurrentHealthPoints() - damage);
    }
}
