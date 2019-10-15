package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;

/**
 * @author mariyan.topalov
 */
public class Vampire extends AbstractMonster {

    public Vampire(Point location) {
        super(2, 1, 6, 20, '^', location);
    }
}
