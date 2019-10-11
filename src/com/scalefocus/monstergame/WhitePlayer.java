package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.IMonster;
import com.scalefocus.monstergame.contract.IPlayer;
import com.scalefocus.monstergame.contract.IWhitePlayer;

public class WhitePlayer extends Player implements IWhitePlayer {

    private int revivesLeft = 1;

    public WhitePlayer() {
        super(true);
    }

    @Override
    public boolean revive(char monster) {
        if (revivesLeft == 0) {
            System.out.println("You have no move revives left! ");
            return false;
        }

      if(getMonsterBy(monster).isDead()){
          getMonsterBy(monster).setNewLocation(getMonsterBy(monster).getInitialLocation());
          getMonsterBy(monster).setCurrentLocation(new Point(0,0));
          getMonsterBy(monster).setCurrentHealthPoints(getMonsterBy(monster).getInitialHealthPoints());
          getMonsterBy(monster).setRemoved(false);
          revivesLeft--;
          return true;
      }
        return false;
    }

    @Override
    public boolean move(char monsterToMove, Point positionToMove) {

        IMonster myMonster = getMonsterBy(monsterToMove);
        return myMonster.move(positionToMove);


    }

    @Override
    public boolean attack(IPlayer attacked, char attackingMonster, char attackedChar) {
        IMonster myMonster = getMonsterBy(attackingMonster);
        IMonster attackedMonster = attacked.getMonsterBy(attackedChar);
        return myMonster.attack(attackedMonster);
    }
}
