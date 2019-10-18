package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
/**
 * Class that inherits the functionality of {@link AbstractMonster}
 * @author mariyan.topalov
 */
public class Dragon extends AbstractMonster {

    public Dragon(Point location) {
        super(3, 30, 80, 40, '#', location);
    }
}
