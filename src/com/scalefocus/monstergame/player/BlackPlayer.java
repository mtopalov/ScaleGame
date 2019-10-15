package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.contract.Monster;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.monster.AbstractMonster;

/**
 * @author mariyan.topalov
 */
public class BlackAbstractPlayer extends AbstractPlayer implements com.scalefocus.monstergame.contract.BlackPlayer {

    private int boostLeft = 3;

    public BlackAbstractPlayer() {
        super(false);
    }

    @Override
    public boolean boostAttack() {
        if (boostLeft == 0) {
            System.out.println("No more boosts left!");
            return false;
        }

        this.getMonsterBy('$').setDamage(this.getMonsterBy('$').getDamage() * 2);
        this.getMonsterBy('^').setDamage(this.getMonsterBy('^').getDamage() * 2);
        this.getMonsterBy('#').setDamage(this.getMonsterBy('#').getDamage() * 2);
        this.getMonsterBy('*').setDamage(this.getMonsterBy('*').getDamage() * 2);
        this.getMonsterBy('@').setDamage(this.getMonsterBy('@').getDamage() * 2);

        boostLeft--;
        return true;
    }

    @Override
    public void removeBoost() {
        this.getMonsterBy('$').setDamage(this.getMonsterBy('$').getDamage() / 2);
        this.getMonsterBy('^').setDamage(this.getMonsterBy('^').getDamage() / 2);
        this.getMonsterBy('#').setDamage(this.getMonsterBy('#').getDamage() / 2);
        this.getMonsterBy('*').setDamage(this.getMonsterBy('*').getDamage() / 2);
        this.getMonsterBy('@').setDamage(this.getMonsterBy('@').getDamage() / 2);
    }

    @Override
    public boolean move(char monsterToMove, Point positionToMove) {
        Monster myMonster = getMonsterBy(monsterToMove);
        return myMonster.move(positionToMove);
    }

    @Override
    public boolean attack(Player attacked, char attackingMonster, char attackedChar) {
        Monster myMonster = getMonsterBy(attackingMonster);
        AbstractMonster attackedAbstractMonster = attacked.getMonsterBy(attackedChar);
        return myMonster.attack(attackedAbstractMonster);
    }

    @Override
    public void place(char monster, Point location) {
        this.getMonsterBy(monster).setNewLocation(location);
        this.getMonsterBy(monster).setCurrentLocation(location);
    }
}
