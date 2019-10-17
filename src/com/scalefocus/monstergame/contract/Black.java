package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Board;
/**
 * Defines a special attack option that only {@link Black} player has.
 * @author mariyan.topalov
 */
public interface Black {

    /**
     * This method defines a special type of attack that only {@link Black} player has.
     *
     * @param player {@link Player} to be attacked
     * @param attackedMonster {@link Player}'s {@link Monster}'s to be attacked
     * @param monster {@link Black}'s monster that's attacking
     * @param board {@link Board} - if the attack is successful and the attacked monster is dead, clear it from the board given as param.
     * @return {@link Boolean}  - true if the attack is successful, otherwise false
     */
    boolean boostAttack(Player player, char attackedMonster, char monster, Board board);

}
