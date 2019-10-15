package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.contract.Black;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.monster.AbstractMonster;

/**
 * @author mariyan.topalov
 */
public class BlackPlayer extends AbstractPlayer implements Black {

    private int boostLeft = 3;

    private static final int BOOST_MULTIPLIER = 2;

    public BlackPlayer(int boardSize, int startLine) {
        super(boardSize, startLine);
    }

    @Override
    public boolean boostAttack(Player player, char attackedMonster, char monster) {
        if (boostLeft == 0) {
            System.out.println("No more boost left!");
            return false;
        }

        AbstractMonster myMonster = getMonsterBy(monster);
        AbstractPlayer attackedPlayer = (AbstractPlayer) player;
        AbstractMonster attackedAbstractMonster = attackedPlayer.getMonsterBy(monster);

        for (int i = 0; i < BOOST_MULTIPLIER; i++) {
            if (!myMonster.attack(attackedAbstractMonster)) {
                return false;
            }
            if (attackedAbstractMonster.isDead()) {
                break;
            }
        }
        boostLeft--;
        return true;
    }
}
