package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.monster.*;

/**
 * @author mariyan.topalov
 */
public abstract class AbstractPlayer implements Player {

    protected AbstractMonster werewolf;

    protected AbstractMonster vampire;

    protected AbstractMonster dragon;

    protected AbstractMonster demon;

    protected AbstractMonster warlock;

    protected AbstractPlayer(int startLine, int boardSize, Board board) {
        int start = (boardSize / 2) - 2;
        this.werewolf = new Werewolf(new Point(startLine, start));
        this.vampire = new Vampire(new Point(startLine, start + 1));
        this.dragon = new Dragon(new Point(startLine, start + 2));
        this.demon = new Demon(new Point(startLine, start + 3));
        this.warlock = new Warlock(new Point(startLine, start + 4));
        initialPlace('$', getMonsterBy('$').getLocation(), board);
        initialPlace('^', getMonsterBy('^').getLocation(), board);
        initialPlace('#', getMonsterBy('#').getLocation(), board);
        initialPlace('*', getMonsterBy('*').getLocation(), board);
        initialPlace('@', getMonsterBy('@').getLocation(), board);
    }

    /**
     * This method provides to White and Black player's monster by its symbol.
     *
     * @param symbol
     * @return object that matches the gives symbol
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

    /**
     * This method checks if all Player's monsters are dead.
     *
     * @return true if all monsters are dead, otherwise returns false.
     */
    @Override
    public boolean isDead() {
        return this.werewolf.isDead() &&
                this.vampire.isDead() &&
                this.dragon.isDead() &&
                this.demon.isDead() &&
                this.warlock.isDead();
    }

    /**
     * This method moves a Monster
     *
     * @param monster
     * @param location
     * @param board
     */
    @Override
    public boolean place(char monster, Point location, Board board) {
        AbstractMonster myMonster = getMonsterBy(monster);
        Point oldLocation = myMonster.getLocation();

        if (board.isLocationAvailable(location)) {
            boolean result = myMonster.move(location);
            if (result) {
                board.clear(oldLocation);
                board.setMonster(monster, location);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean attack(Player player, char monster, char attackedMonster, Board board) {
        AbstractMonster myMonster = getMonsterBy(monster);
        AbstractPlayer attackedPlayer = (AbstractPlayer) player;
        AbstractMonster attackedAbstractMonster = attackedPlayer.getMonsterBy(attackedMonster);
        boolean result = myMonster.attack(attackedAbstractMonster);

        if (result) {
            if (attackedAbstractMonster.isDead()) {
                board.clear(attackedAbstractMonster.getLocation());
            }
            return true;
        }
        return false;
    }

    /**
     * This method places the Monsters to their initial locations.
     *
     * @param monster  Monster to be placed
     * @param location initial location of the Monster
     * @param board    the board at which Monsters are being placed
     */
    private void initialPlace(char monster, Point location, Board board) {
        board.setMonster(monster, location);
    }

    /**
     * Prints all Monsters' remaining health points.
     */
    public void printInfo() {
        System.out.print(this.getClass().getSimpleName() + "'s Werewolf HP: " + Math.max(this.getMonsterBy('$').getHealthPoints(), 0) + "   ");
        System.out.println(this.getClass().getSimpleName() + "'s Vampire HP: " + Math.max(this.getMonsterBy('^').getHealthPoints(), 0));
        System.out.print(this.getClass().getSimpleName() + "'s Dragon HP: " + Math.max(this.getMonsterBy('#').getHealthPoints(), 0) + "   ");
        System.out.println(this.getClass().getSimpleName() + "'s Demon HP: " + Math.max(this.getMonsterBy('*').getHealthPoints(), 0));
        System.out.println(this.getClass().getSimpleName() + "'s Warlock HP: " + Math.max(this.getMonsterBy('@').getHealthPoints(), 0) + "\n");
    }
}
