package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Board;
/**
 * @author mariyan.topalov
 */
public interface White {

    boolean revive(char monster, Board board);
}
