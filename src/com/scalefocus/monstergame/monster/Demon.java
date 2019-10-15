package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;

/**
 * @author mariyan.topalov
 */
public class Demon extends AbstractMonster {

    public Demon(Point location) {
        super(2, 2, 2, 50, '*', location);
    }
}