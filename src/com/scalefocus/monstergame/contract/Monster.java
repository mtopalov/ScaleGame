package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Point;
/**
 * @author mariyan.topalov
 */
public interface Monster {

    void beDamageBy(int damage);

    boolean isDead();

    boolean move(Point point);

    boolean attack(Monster monster);

}
