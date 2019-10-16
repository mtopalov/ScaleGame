package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;

/**
 * @author mariyan.topalov
 */
public class Dragon extends AbstractMonster {

    public Dragon(Point location) {
        super(30, 30, 80, 40, '#', location);
    }
}
