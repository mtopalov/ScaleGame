package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
/**
 * Class that inherits the functionality of {@link AbstractMonster}
 * @author mariyan.topalov
 */
public class Warlock extends AbstractMonster {

    public Warlock(Point location) {
        super(1, 2, 7, 10, '@', location);
    }
}
