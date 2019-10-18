package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.monster.*;

/**
 * Abstract class that the attributes of the players
 * and also implements the functionality, defined in the {@link Player} interface.
 * @author mariyan.topalov
 */
public abstract class AbstractPlayer implements Player {

    protected AbstractMonster werewolf;

    protected AbstractMonster vampire;

    protected AbstractMonster dragon;

    protected AbstractMonster demon;

    protected AbstractMonster warlock;

    /**
     * Constructor that initializes the players, giving them monster and initializes monster's initial location.
     * Access modifier is protected, because it can be called only from a child class.
     *
     * @param startLine {@link Integer} - starting location of the monster by x-axis
     * @param boardSize {@link Integer} - starting location of the monster by y-axis
     * @param board {@link Board} - the board at which the monster's are being placed
     */
    protected AbstractPlayer(int startLine, int boardSize, Board board) {
        int start = (boardSize / 2) - 2;
        this.werewolf = new Werewolf(new Point(startLine, start));
        this.vampire = new Vampire(new Point(startLine, start + 1));
        this.dragon = new Dragon(new Point(startLine, start + 2));
        this.demon = new Demon(new Point(startLine, start + 3));
        this.warlock = new Warlock(new Point(startLine, start + 4));
        initialPlace(werewolf.getSymbol(), werewolf.getLocation(), board);
        initialPlace(vampire.getSymbol(),vampire.getLocation(), board);
        initialPlace(dragon.getSymbol(), dragon.getLocation(), board);
        initialPlace(demon.getSymbol(), demon.getLocation(), board);
        initialPlace(warlock.getSymbol(), warlock.getLocation(), board);
    }

    /**
     * This method provides to White and Black player's monster by its symbol.
     * Can only be used within current class and child classes.
     *
     * @param symbol {@link Character} - the symbol that the method searches by.
     * @return {@link AbstractMonster} - object that matches the gives symbol
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
     * @return true if all monsters are dead, otherwise false.
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
     * {@inheritDoc}
     * @param monster {@link Character} - the monster to be moved.
     * @param location {@link Point} - the new location at which the monster will be moved.
     * @param board {@link Board} - the board in which the monster will added to the new location
     * @return {@inheritDoc}
     */
    @Override
    public boolean place(char monster, Point location, Board board) {
        AbstractMonster myMonster = getMonsterBy(monster);
        Point oldLocation = myMonster.getLocation();
        //check if the location is available
        if (board.isLocationAvailable(location)) {

            //if available, move to the location and assign the result to the variable
            boolean result = myMonster.move(location);

            //if true, clear the old position from the board and assign the monster to the new position
            if (result) {
                board.clear(oldLocation);
                board.setMonster(monster, location);
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * @param player {@link Player} - the played to be attacked by current player
     * @param monster {@link Character} - current player's monster
     * @param attackedMonster {@link Character} - attacked player's monster
     * @param board {@link Board} - if the attack is successful and the attacked player's monster is dead, clear it from the board given as param.
     * @return {@inheritDoc}
     */
    @Override
    public boolean attack(Player player, char monster, char attackedMonster, Board board) {
        //assign the parameters to local variables
        AbstractMonster myMonster = getMonsterBy(monster);
        AbstractPlayer attackedPlayer = (AbstractPlayer) player;
        AbstractMonster attackedAbstractMonster = attackedPlayer.getMonsterBy(attackedMonster);

        //check if the attack is successful and assign the result to a variable
        boolean result = myMonster.attack(attackedAbstractMonster);


        if (result) {
            //if attack is successful, check if the attacked monster is dead, and if true - clear it from the board
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
     * Prints all {@link Player}'s Monsters' remaining health points.
     */
    public void printInfo() {
        System.out.print(this.getClass().getSimpleName() + "'s Werewolf HP: " + Math.max(this.getMonsterBy('$').getHealthPoints(), 0) + "   ");
        System.out.println(this.getClass().getSimpleName() + "'s Vampire HP: " + Math.max(this.getMonsterBy('^').getHealthPoints(), 0));
        System.out.print(this.getClass().getSimpleName() + "'s Dragon HP: " + Math.max(this.getMonsterBy('#').getHealthPoints(), 0) + "   ");
        System.out.println(this.getClass().getSimpleName() + "'s Demon HP: " + Math.max(this.getMonsterBy('*').getHealthPoints(), 0));
        System.out.println(this.getClass().getSimpleName() + "'s Warlock HP: " + Math.max(this.getMonsterBy('@').getHealthPoints(), 0) + "\n");
    }
}
