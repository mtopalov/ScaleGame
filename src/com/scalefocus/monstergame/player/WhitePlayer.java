package com.scalefocus.monstergame.player;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Monster;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.monster.AbstractMonster;

/**
 * @author mariyan.topalov
 */
public class WhiteAbstractPlayer extends AbstractPlayer implements com.scalefocus.monstergame.contract.WhitePlayer {

    private int revivesLeft = 1;

    public WhiteAbstractPlayer() {
        super(true);
    }

    @Override
    public boolean revive(char monster) {
        if (revivesLeft == 0) {
            System.out.println("No move revives left! ");
            return false;
        }

      if(getMonsterBy(monster).isDead()){
          getMonsterBy(monster).setNewLocation(getMonsterBy(monster).getInitialLocation());
          getMonsterBy(monster).setCurrentLocation(null);
          getMonsterBy(monster).setCurrentHealthPoints(getMonsterBy(monster).getHealthPoints());
          getMonsterBy(monster).setRemoved(false);

          revivesLeft--;
          return true;
      }
        return false;
    }

    @Override
    public boolean move(char monsterToMove, Point positionToMove) {
        Monster myMonster = getMonsterBy(monsterToMove);
        return myMonster.move(positionToMove);
    }

    @Override
    public boolean attack(Player attacked, char attackingMonster, char attackedChar) {
        Monster myMonster = getMonsterBy(attackingMonster);
        AbstractMonster attackedAbstractMonster = attacked.getMonsterBy(attackedChar);
        return myMonster.attack(attackedAbstractMonster);
    }

    @Override
    public void place(char monster, Point location) {
        this.getMonsterBy(monster).setCurrentLocation(location);
        this.getMonsterBy(monster).setNewLocation(location);
        this.getMonsterBy(monster).setInitialLocation(location);
    }
}
