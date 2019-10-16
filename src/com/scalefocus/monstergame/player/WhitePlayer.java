package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.contract.White;
import com.scalefocus.monstergame.monster.AbstractMonster;
import com.scalefocus.monstergame.monster.Demon;
import com.scalefocus.monstergame.monster.Vampire;
import com.scalefocus.monstergame.monster.Warlock;
import com.scalefocus.monstergame.monster.Werewolf;
import com.scalefocus.monstergame.monster.Dragon;

/**
 * @author mariyan.topalov
 */
public class WhitePlayer extends AbstractPlayer implements White {

    private boolean turn = true;

    private int revivesLeft = 1;

    public WhitePlayer(int boardSize, int startLine, Board board) {
        super(boardSize, startLine, board);
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    @Override
    public boolean revive(char monster, Board board) {

        AbstractMonster abstractMonster = getMonsterBy(monster);

        if (revivesLeft == 0) {
            System.out.println("No more revives left! ");
            return false;
        }

        if (abstractMonster.isDead()) {
            switch (monster) {
                case '$': {
                    abstractMonster= new Werewolf(abstractMonster.getLocation());
                    this.place('$',abstractMonster.getLocation(),board);
                    return true;
                }
                case '^': {
                    abstractMonster = new Vampire(abstractMonster.getLocation());
                    this.place('^',abstractMonster.getLocation(),board);
                    return true;
                }
                case '#': {
                    abstractMonster = new Dragon(abstractMonster.getLocation());
                    this.place('#',abstractMonster.getLocation(),board);
                    return true;
                }
                case '*': {
                    abstractMonster = new Demon(abstractMonster.getLocation());
                    this.place('*',abstractMonster.getLocation(),board);
                    return true;
                }
                case '@': {
                    abstractMonster = new Warlock(abstractMonster.getLocation());
                    this.place('@',abstractMonster.getLocation(),board);
                    return true;
                }
            }
        }

        return false;
    }
}
