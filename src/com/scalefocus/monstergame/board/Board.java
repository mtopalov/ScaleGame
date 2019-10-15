package com.scalefocus.monstergame.board;

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

        printBoard();

    }

    public int getSize() {
        return size;
    }

    public void printBoard() {

        //refreshBoard(white, black);

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

//            if (x == 0) {
//                System.out.println("|" + x + "   White " + white.getMonsterBy('$').getClass().getSimpleName() + " HP: " + white.getMonsterBy('$').getHealthPoints() + "   White " + white.getMonsterBy('^').getClass().getSimpleName() + " HP: " + white.getMonsterBy('^').getHealthPoints());
//            } else if (x == 1) {
//                System.out.println("|" + x + "   White " + white.getMonsterBy('#').getClass().getSimpleName() + " HP: " + white.getMonsterBy('#').getHealthPoints() + "   White " + white.getMonsterBy('*').getClass().getSimpleName() + " HP: " + white.getMonsterBy('*').getHealthPoints());
//            } else if (x == 2) {
//                System.out.println("|" + x + "   White " + white.getMonsterBy('@').getClass().getSimpleName() + " HP: " + white.getMonsterBy('@').getHealthPoints());
//            } else if (x == 5) {
//                System.out.println("|" + x + "   Black " + black.getMonsterBy('$').getClass().getSimpleName() + " HP: " + black.getMonsterBy('$').getHealthPoints() + "   Black " + black.getMonsterBy('^').getClass().getSimpleName() + " HP: " + black.getMonsterBy('^').getHealthPoints());
//            } else if (x == 6) {
//                System.out.println("|" + x + "   Black " + black.getMonsterBy('#').getClass().getSimpleName() + " HP: " + black.getMonsterBy('#').getHealthPoints() + "   Black " + black.getMonsterBy('*').getClass().getSimpleName() + " HP: " + black.getMonsterBy('*').getHealthPoints());
//            } else if (x == 7) {
//                System.out.println("|" + x + "   Black " + black.getMonsterBy('@').getClass().getSimpleName() + " HP: " + black.getMonsterBy('@').getHealthPoints());
//            } else {
//                System.out.println("|" + x);
//            }
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


    // private void printIfAlive(Player player, char monster) {
    //        if (player.getMonsterBy(monster).isDead()) {
    //            return;
    //        }
    //        if (isLocationAvailable(player.getMonsterBy(monster).getNewLocation())) {
    //            board[player.getMonsterBy(monster).getNewLocation().getX()][player.getMonsterBy(monster).getNewLocation().getY()] = " " + player.getMonsterBy(monster).getMonsterSymbol() + " ";
    //            if (player.getMonsterBy(monster).getCurrentLocation() != null) {
    //                board[player.getMonsterBy(monster).getCurrentLocation().getX()][player.getMonsterBy(monster).getCurrentLocation().getY()] = "   ";
    //            }
    //        }
    //}

    // public boolean isLocationAvailable(Point location) {
    //        if (location.getX() >= 0 && location.getX() < n && location.getY() >= 0 && location.getY() < n) {
    //            return board[location.getX()][location.getY()].equals("   ");
    //        }
    //        return false;
    //    }

    //private void refreshBoard(Player white, Player black) {

    //        removeIfDead(white, '$');
    //        removeIfDead(white, '^');
    //        removeIfDead(white, '#');
    //        removeIfDead(white, '*');
    //        removeIfDead(white, '@');
    //
    //        printIfAlive(white, '$');
    //        printIfAlive(white, '^');
    //        printIfAlive(white, '#');
    //        printIfAlive(white, '*');
    //        printIfAlive(white, '@');
    //
    //        removeIfDead(black, '$');
    //        removeIfDead(black, '^');
    //        removeIfDead(black, '#');
    //        removeIfDead(black, '*');
    //        removeIfDead(black, '@');
    //
    //        printIfAlive(black, '$');
    //        printIfAlive(black, '^');
    //        printIfAlive(black, '#');
    //        printIfAlive(black, '*');
    //        printIfAlive(black, '@');


    // }





}
