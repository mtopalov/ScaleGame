package com.scalefocus.monstergame.contract;

import com.scalefocus.monstergame.board.Point;

public interface IMonster {

    boolean isRemoved();

    void setRemoved(boolean removed);

    Point getNewLocation();

    void setNewLocation(Point newLoPosition);

    Point getCurrentLocation();

    void setCurrentLocation(Point currentLocation);

    Point getInitialLocation();

    void setInitialLocation(Point initialLocation);

    char getMonsterSymbol();

    void beDamageBy(int damage);

    boolean isDead();

    int getCurrentHealthPoints();

    void setCurrentHealthPoints(int currentHealthPoints);

    int getInitialHealthPoints();

    void setDamage(int damage);

    int getDamage();


    boolean move(Point point);

    boolean attack(IMonster attackedMonster);



}
