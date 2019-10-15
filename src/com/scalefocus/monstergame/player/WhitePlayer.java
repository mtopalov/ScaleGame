package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.contract.White;

/**
 * @author mariyan.topalov
 */
public class WhitePlayer extends AbstractPlayer implements White {

    private int revivesLeft = 1;

    public WhitePlayer(int boardSize, int startLine) {
        super(boardSize, startLine);
    }


    @Override
    public boolean revive(char monster) {
        if (revivesLeft == 0) {
            System.out.println("No more revives left! ");
            return false;
        }

        if(this.getMonsterBy(monster).isDead()){
            revivesLeft--;
            return true;
        }

        return false;
    }
}
