package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.contract.IBlackPlayer;
import com.scalefocus.monstergame.contract.IMonster;
import com.scalefocus.monstergame.contract.IPlayer;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.monster.Monster;

/**
 * @author mariyan.topalov
 */
public class BlackPlayer extends Player implements IBlackPlayer {

    private int boostLeft = 3;

    public BlackPlayer() {
        super(false);
    }

    @Override
    public boolean boostAttack( ) {
        if (boostLeft == 0) {
            return false;
        }

        this.getMonsterBy('$').setDamage(this.getMonsterBy('$').getDamage()*2);
        this.getMonsterBy('^').setDamage(this.getMonsterBy('^').getDamage()*2);
        this.getMonsterBy('#').setDamage(this.getMonsterBy('#').getDamage()*2);
        this.getMonsterBy('*').setDamage(this.getMonsterBy('*').getDamage()*2);
        this.getMonsterBy('@').setDamage(this.getMonsterBy('@').getDamage()*2);

        boostLeft--;
        return true;
    }

    @Override
    public boolean move(char monsterToMove, Point positionToMove) {
        IMonster myMonster = getMonsterBy(monsterToMove);
        return myMonster.move(positionToMove);
    }

    @Override
    public boolean attack(IPlayer attacked, char attackingMonster, char attackedChar) {
        IMonster myMonster = getMonsterBy(attackingMonster);
        Monster attackedMonster = attacked.getMonsterBy(attackedChar);
        return myMonster.attack(attackedMonster);
    }
}
