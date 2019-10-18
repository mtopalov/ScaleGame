package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.contract.Black;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.monster.AbstractMonster;

/**
 * Class that inherits the functionality of the {@link AbstractPlayer} class
 * and also implements the methods defined in the {@link Black} interface.
 * @author mariyan.topalov
 */
public class BlackPlayer extends AbstractPlayer implements Black {

    private int boostLeft = 3;

    private static final int BOOST_MULTIPLIER = 2;


    public BlackPlayer(int boardSize, int startLine, Board board) {
        super(boardSize, startLine, board);
    }

    /**{@inheritDoc}
     * This type of attack does double the damage that {@link Black}'s Monster has.
     * It's implemented as dual attack.
     *
     * @param player {@inheritDoc}
     * @param monster {@inheritDoc}
     * @param attackedMonster {@inheritDoc}
     * @param board {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean boostAttack(Player player, char monster, char attackedMonster, Board board) {
        if (boostLeft == 0) {
            System.out.println("No more boost left!");
            return false;
        }

        AbstractMonster myMonster = getMonsterBy(monster);
        AbstractPlayer attackedPlayer = (AbstractPlayer) player;
        AbstractMonster attackedAbstractMonster = attackedPlayer.getMonsterBy(attackedMonster);

        for (int i = 0; i < BOOST_MULTIPLIER; i++) {

            if (!myMonster.attack(attackedAbstractMonster)) {
                return false;
            }
            //checks if the attacked monster is dead, if true - exits the loop
            if (attackedAbstractMonster.isDead()) {
                board.clear(attackedAbstractMonster.getLocation());
                break;
            }
        }
        boostLeft--;
        return true;
    }
}
