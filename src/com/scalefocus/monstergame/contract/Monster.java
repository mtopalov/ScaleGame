package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Point;
/**
 * Defines methods that a {@link Monster} can use.
 * @author mariyan.topalov
 */
public interface Monster {

    /**
     * Method that decreases {@link Monster}'s health points by the amount given as param.
     * @param damage {@link Integer} - the amount of health points to be decreased from monster's health points.
     */
    void beDamageBy(int damage);

    /**
     * Method that checks if the current {@link Monster} has any health points left.
     * @return {@link Boolean} - true if current monster's health points are below or equal to 0, otherwise false.
     */
    boolean isDead();

    /**
     * Method that changes current {@link Monster}'s location to a new location given as param.
     * @param point {@link Point} - the new location
     * @return {@link Boolean} - true if the move operation is successful, otherwise false.
     */
    boolean move(Point point);

    /**
     * Method that a {@link Monster} can attack another monster, given as param, to decrease it's health points.
     * @param monster {@link Monster} - monster, being attacked by current monster
     * @return {@link Boolean} - true if the attack is successful, otherwise false.
     */
    boolean attack(Monster monster);

}
