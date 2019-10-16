package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
/**
 * @author mariyan.topalov
 */
public interface Player {

    boolean attack(Player player, char attackedMonster, char monster, Board board);

    boolean isDead();

    boolean place(char monster, Point location, Board board);
}
