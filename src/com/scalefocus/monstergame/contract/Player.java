package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Point;

/**
 * @author mariyan.topalov
 */
public interface IPlayer {

    boolean attack(IPlayer player, char attacked, char attackingMonster);

    boolean isDead();

    void place(char monster, Point location);
}
