package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.IPlayer;
import com.scalefocus.monstergame.monster.*;

/**
 * @author mariyan.topalov
 */
public abstract class Player implements IPlayer {

    private boolean onTurn;

    private final Monster werewolf;

    private final Monster vampire;

    private final Monster dragon;

    private final Monster demon;

    private final Monster warlock;

    public Player(boolean onTurn, int boardSize, int startLine) {
        int start = (boardSize / 2) - 2;
        this.werewolf = new Werewolf(new Point(start, startLine));
        this.vampire = new Vampire(new Point(start + 1, startLine));
        this.dragon = new Dragon(new Point(start + 2, startLine));
        this.demon = new Demon(new Point(start + 3, startLine));
        this.warlock = new Warlock(new Point(start + 4, startLine));
        this.onTurn = onTurn;
    }

    @Override
    public boolean isOnTurn() {
        return onTurn;
    }

    @Override
    public void setOnTurn(boolean onTurn) {
        this.onTurn = onTurn;
    }

    public Monster getMonsterBy(char symbol) {

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
                System.out.println("No such monster!");
                return null;
        }

    }

    @Override
    public abstract boolean move(char monsterToMove, Point positionToMove);

    @Override
    public abstract boolean attack(IPlayer player, char attacked, char attackingMonster);

    @Override
    public boolean isDead() {
        if (this.werewolf.isDead() && this.vampire.isDead() && this.dragon.isDead() && this.demon.isDead() && this.warlock.isDead()) {
            return true;
        }
        return false;
    }
}
