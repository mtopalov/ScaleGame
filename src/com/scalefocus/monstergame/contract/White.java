package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Board;
/**
 * Defines a special operation that only the {@link White} player has.
 * @author mariyan.topalov
 */
public interface White {

    /**
     * Defines a special operation that only {@link White} player has.
     * This operation revives a dead {@link Monster} and returns it to it's starting position at the {@link Board}.
     * @param monster {@link Character} - monster to be checked if it can be revived.
     * @param board {@link Board} - if the operation is successful, the monster will be again added in the board.
     * @return {@link Boolean} - true if the monster given as parameter is dead and the revive operation is successful, otherwise false.
     */
    boolean revive(char monster, Board board);
}
