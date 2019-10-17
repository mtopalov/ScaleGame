package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.contract.Monster;
import com.scalefocus.monstergame.contract.White;
import com.scalefocus.monstergame.monster.AbstractMonster;
import com.scalefocus.monstergame.monster.Demon;
import com.scalefocus.monstergame.monster.Vampire;
import com.scalefocus.monstergame.monster.Warlock;
import com.scalefocus.monstergame.monster.Werewolf;
import com.scalefocus.monstergame.monster.Dragon;

/**
 * Class that inherits the functionality of {@link AbstractPlayer}
 * and also implements methods, defined in {@link White} interface.
 *
 * @author mariyan.topalov
 */
public class WhitePlayer extends AbstractPlayer implements White {

    private int revivesLeft = 1;

    public WhitePlayer(int boardSize, int startLine, Board board) {
        super(boardSize, startLine, board);
    }

    /**
     * This method checks if the {@link Monster}, given as parameter, is dead
     * and if true, assigns a new Object of type {@link Monster} to the same reference of the dead monster.
     *
     * @param monster {@link Character} - monster to be checked if it can be revived.
     * @param board {@link Board} - if the operation is successful, the monster will be again added in the board.
     * @return {@inheritDoc}
     */
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
                    werewolf = new Werewolf(abstractMonster.getLocation());
                    this.place(werewolf.getSymbol(), abstractMonster.getLocation(), board);
                    revivesLeft--;
                    return true;
                }
                case '^': {
                    vampire = new Vampire(abstractMonster.getLocation());
                    this.place(vampire.getSymbol(), abstractMonster.getLocation(), board);
                    revivesLeft--;
                    return true;
                }
                case '#': {
                    dragon = new Dragon(abstractMonster.getLocation());
                    this.place(dragon.getSymbol(), abstractMonster.getLocation(), board);
                    revivesLeft--;
                    return true;
                }
                case '*': {
                    demon = new Demon(abstractMonster.getLocation());
                    this.place(demon.getSymbol(), abstractMonster.getLocation(), board);
                    revivesLeft--;
                    return true;
                }
                case '@': {
                    warlock = new Warlock(abstractMonster.getLocation());
                    this.place(warlock.getSymbol(), abstractMonster.getLocation(), board);
                    revivesLeft--;
                    return true;
                }
            }
        }

        return false;
    }
}
