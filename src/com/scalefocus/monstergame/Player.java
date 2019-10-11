package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.IMonster;
import com.scalefocus.monstergame.contract.IPlayer;
import com.scalefocus.monstergame.monster.*;



public abstract class Player implements IPlayer {

    private boolean onTurn;

    private final IMonster werewolf;
    private final IMonster vampire;
    private final IMonster dragon;
    private final IMonster demon;
    private final IMonster warlock;

    public Player(boolean onTurn) {
        this.werewolf = new Werewolf();
        this.vampire = new Vampire();
        this.dragon = new Dragon();
        this.demon = new Demon();
        this.warlock = new Warlock();
        this.onTurn=onTurn;
    }
    @Override
    public boolean isOnTurn() {
        return onTurn;
    }
    @Override
    public void setOnTurn(boolean onTurn) {
        this.onTurn = onTurn;
    }

    public IMonster getMonsterBy(char symbol) throws NullPointerException {

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
    public abstract boolean attack(IPlayer player, char attacked, char attackingMonster) ;

    @Override
    public boolean isTeamDead() {
        if(this.werewolf.isDead() && this.vampire.isDead() && this.dragon.isDead() && this.demon.isDead() && this.warlock.isDead()) return true;
        return false;
    }
}
