package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;

/**
 * @author mariyan.topalov
 */
public class Dragon extends Monster {

    public Dragon() {
        super(3, 10, 100, 40, '#');
    }

    @Override
    public boolean move(Point positionToMove) {
        if (this.isDead()) {
            return false;
        }

        if (this.getNewLocation().calculateDistance(positionToMove) <= this.getMovesPerTurn()) {
            setCurrentLocation(getNewLocation());
            setNewLocation(positionToMove);
            return true;
        }

        return false;
    }

    @Override
    public boolean attack(Monster attackedMonster) {
        if (this.isDead()) {
            return false;
        }

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
