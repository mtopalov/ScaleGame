package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
/**
 * Defines operations that {@link Player}s can use.
 * @author mariyan.topalov
 */
public interface Player {

    /**
     * Method that the current {@link Player} can use to attack other {@link Player}.
     *
     * @param player {@link Player} - the played to be attacked by current player
     * @param attackedMonster {@link Character} - attacked player's monster
     * @param monster {@link Character} - current player's monster
     * @param board {@link Board} - if the attack is successful and the attacked player's monster is dead, clear it from the board given as param.
     * @return {@link Boolean} - true if attack is successfull, otherwise false.
     */
    boolean attack(Player player, char attackedMonster, char monster, Board board);

    /**
     * Method that checks if a {@link Player} has no more {@link Monster}s alive.
     *
     * @return {@link Boolean} - true if the Player has no more Monsters alive, otherwise false.
     */
    boolean isDead();

    /**
     * Method that the current {@link Player} can use to move it's {@link Monster} from it's current location to another location.
     *
     * @param monster {@link Character} - the monster to be moved.
     * @param location {@link Point} - the new location at which the monster will be moved.
     * @param board {@link Board} - the board in which the monster will added to the new location.
     * @return {@link Boolean} - true if the monster can be moved to the chosen location, given as parameter.
     */
    boolean place(char monster, Point location, Board board);
}
