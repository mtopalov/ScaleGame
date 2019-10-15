package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Monster;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.monster.*;

/**
 * @author mariyan.topalov
 */
public abstract class AbstractPlayer implements Player {

    private final AbstractMonster werewolf;

    private final AbstractMonster vampire;

    private final AbstractMonster dragon;

    private final AbstractMonster demon;

    private final AbstractMonster warlock;

    public AbstractPlayer(int boardSize, int startLine) {
        int start = (boardSize / 2) - 2;
        this.werewolf = new Werewolf(new Point(start, startLine));
        this.vampire = new Vampire(new Point(start + 1, startLine));
        this.dragon = new Dragon(new Point(start + 2, startLine));
        this.demon = new Demon(new Point(start + 3, startLine));
        this.warlock = new Warlock(new Point(start + 4, startLine));

    }

    /**
     * This method provides to White and Black player's monster by its symbol.
     *
     * @param symbol
     * @return object of type AbstractMonster
     */
    protected AbstractMonster getMonsterBy(char symbol) {
        switch (symbol) {
            case '$':
                return werewolf;
            case '^':
                return vampire;
            case '#':
                return dragon;
            case '*':
                return demon;
            case '@':
                return warlock;
            default:
                throw new IllegalStateException("Not found monster with symbol: " + symbol);
        }
    }

    @Override
    public boolean isDead() {
        return this.werewolf.isDead() &&
                this.vampire.isDead() &&
                this.dragon.isDead() &&
                this.demon.isDead() &&
                this.warlock.isDead();
    }



    @Override
    public boolean place(char monster, Point location, Board board) {
        Monster myMonster = getMonsterBy(monster);
        boolean result = myMonster.move(location);
        if (result) {
            board.setMonster(monster, location);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(Player player, char attackedMonster, char monster) {
        AbstractMonster myMonster = getMonsterBy(monster);
        AbstractPlayer attackedPlayer = (AbstractPlayer) player;
        AbstractMonster attackedAbstractMonster = attackedPlayer.getMonsterBy(monster);
        return myMonster.attack(attackedAbstractMonster);
    }
}
