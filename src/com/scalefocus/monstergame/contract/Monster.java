package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.monster.AbstractMonster;

/**
 * @author mariyan.topalov
 */
public interface Monster {

    void beDamageBy(int damage);

    boolean isDead();

    boolean move(Point point);

    boolean attack(Monster monster);

}
