package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Board;
/**
 * @author mariyan.topalov
 */
public interface Black {

    boolean boostAttack(Player player, char attackedMonster, char monster, Board board);

}
