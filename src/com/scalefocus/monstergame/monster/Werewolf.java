package com.scalefocus.monstergame.monster;

import com.scalefocus.monstergame.board.Point;
/**
 * Class that inherits the functionality of {@link AbstractMonster}
 * @author mariyan.topalov
 */
public class Werewolf extends AbstractMonster {

    public Werewolf(Point location) {
        super(1, 2, 5, 20, '$', location);
    }
}
