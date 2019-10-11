package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.monster.Monster;

public interface IMonster {

    void beDamageBy(int damage);

    boolean isDead();

    boolean move(Point point);

    boolean attack(Monster attackedMonster);

}
