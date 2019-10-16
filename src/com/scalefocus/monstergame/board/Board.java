package com.scalefocus.monstergame.board;

import com.scalefocus.monstergame.contract.Monster;
import com.scalefocus.monstergame.monster.AbstractMonster;
import com.scalefocus.monstergame.player.WhitePlayer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mariyan.topalov
 */
public class Board {

    private static final Scanner input = new Scanner(System.in);

    private final int size;

    private final String[][] board;

    public Board() {
        int number = 0;

        do {
            System.out.print("Please select the size of the map(must be 8 or greater): ");
            String nextLine = input.nextLine();
            if (nextLine == null || nextLine.equals("")) {
                continue;
            }

            if (nextLine.chars().allMatch(Character::isDigit)) {
                number = Integer.parseInt(nextLine);
            }
        }
        while (number < 8);

        size = number;
        board = new String[size][size];

        for (String[] strings : board) {
            Arrays.fill(strings, "   ");
        }

    }

    public int getSize() {
        return size;
    }

    public void printBoard() {
        System.out.print("    ");
        for (int i = 0; i < size; i++) {
            if (i >= 10) {
                System.out.print(" " + i);
            } else {
                System.out.print(" " + i + " ");
            }
        }
        System.out.println();

        for (int x = 0; x < board.length; x++) {
            if (x >= 10) {
                System.out.print(" " + x + "|");
            } else {
                System.out.print("  " + x + "|");
            }

            for (int y = 0; y < board[x].length; y++) {
                System.out.print(board[x][y]);
            }

//                        if (x == 0) {
//                            System.out.println("|" + x + "   White " + ((WhitePlayer)white).getMonsterBy('$').getClass().getSimpleName() + " HP: " + white.getMonsterBy('$').getHealthPoints() + "   White " + white.getMonsterBy('^').getClass().getSimpleName() + " HP: " + white.getMonsterBy('^').getHealthPoints());
//                        } else if (x == 1) {
//                            System.out.println("|" + x + "   White " + white.getMonsterBy('#').getClass().getSimpleName() + " HP: " + white.getMonsterBy('#').getHealthPoints() + "   White " + white.getMonsterBy('*').getClass().getSimpleName() + " HP: " + white.getMonsterBy('*').getHealthPoints());
//                        } else if (x == 2) {
//                            System.out.println("|" + x + "   White " + white.getMonsterBy('@').getClass().getSimpleName() + " HP: " + white.getMonsterBy('@').getHealthPoints());
//                        } else if (x == 5) {
//                            System.out.println("|" + x + "   Black " + black.getMonsterBy('$').getClass().getSimpleName() + " HP: " + black.getMonsterBy('$').getHealthPoints() + "   Black " + black.getMonsterBy('^').getClass().getSimpleName() + " HP: " + black.getMonsterBy('^').getHealthPoints());
//                        } else if (x == 6) {
//                            System.out.println("|" + x + "   Black " + black.getMonsterBy('#').getClass().getSimpleName() + " HP: " + black.getMonsterBy('#').getHealthPoints() + "   Black " + black.getMonsterBy('*').getClass().getSimpleName() + " HP: " + black.getMonsterBy('*').getHealthPoints());
//                        } else if (x == 7) {
//                            System.out.println("|" + x + "   Black " + black.getMonsterBy('@').getClass().getSimpleName() + " HP: " + black.getMonsterBy('@').getHealthPoints());
//                        } else {
//                            System.out.println("|" + x);
//                        }
            System.out.println("|" + x);
        }

        System.out.print("    ");
        for (int i = 0; i < size; i++) {
            if (i >= 10) {
                System.out.print(" " + i);
            } else {
                System.out.print(" " + i + " ");
            }
        }
        System.out.println();

    }

    public void setMonster(char monster, Point location) {
        board[location.getX()][location.getY()] = " " + monster + " ";
    }



    public void clear(Point location) {
        board[location.getX()][location.getY()] = "   ";
    }

    public void clearDeadMonster(Monster monster){
        AbstractMonster abstractMonster = (AbstractMonster) monster;
        if(abstractMonster.isDead()){
            System.out.println(monster.getClass().getSimpleName() + " is dead!");
            board[abstractMonster.getLocation().getX()][abstractMonster.getLocation().getY()]= "   ";
        }
    }

    public boolean isLocationAvailable(Point location) {
        if (location.getX() >= 0 && location.getX() < this.getSize() && location.getY() >= 0 && location.getY() < this.getSize()) {
            return this.board[location.getX()][location.getY()].equals("   ");
        }
        return false;
    }
}
