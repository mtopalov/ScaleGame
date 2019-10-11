package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.monster.Monster;

public interface IPlayer {

    boolean move(char monsterToMove, Point positionToMove);

    boolean attack(IPlayer player, char attacked, char attackingMonster);

    Monster getMonsterBy(char symbol);

    boolean isTeamDead();

    boolean isOnTurn();

    void setOnTurn(boolean onTurn);


}
