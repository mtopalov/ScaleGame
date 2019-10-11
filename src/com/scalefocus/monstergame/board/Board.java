package com.scalefocus.monstergame.board;

import com.scalefocus.monstergame.contract.IPlayer;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author mariyan.topalov
 */
public class Board {

    private static final Scanner in = new Scanner(System.in);

    private final int n;

    private final String[][] board;

    public Board() {
        int number = 0;

        do {
            try {
                System.out.print("Please select the size of the map(must be 8 or greater): ");
                String nextLine = in.nextLine();
                if (nextLine == null || nextLine.equals("")) {
                    continue;
                }

                if (nextLine.chars().allMatch(Character::isDigit)) {
                    number = Integer.parseInt(nextLine);
                }

            } catch (InputMismatchException ex) {
                System.out.println("Don't give me blank lines!");
            }
        } while (number < 8);

        n = number;
        board = new String[n][n];
    }


    public void createBoard(IPlayer white, IPlayer black) {

        for (String[] strings : board) {
            Arrays.fill(strings, "   ");
        }

        int initPos = n / 2 - 2;

        board[0][initPos] = " " + white.getMonsterBy('$').getMonsterSymbol() + " ";
        white.getMonsterBy('$').setNewLocation(new Point(0, initPos));
        white.getMonsterBy('$').setCurrentLocation(new Point(0, initPos));
        white.getMonsterBy('$').setInitialLocation(new Point(0, initPos));
        board[0][initPos + 1] = " " + white.getMonsterBy('^').getMonsterSymbol() + " ";
        white.getMonsterBy('^').setNewLocation(new Point(0, initPos + 1));
        white.getMonsterBy('^').setCurrentLocation(new Point(0, initPos + 1));
        white.getMonsterBy('^').setInitialLocation(new Point(0, initPos + 1));
        board[0][initPos + 2] = " " + white.getMonsterBy('#').getMonsterSymbol() + " ";
        white.getMonsterBy('#').setNewLocation(new Point(0, initPos + 2));
        white.getMonsterBy('#').setCurrentLocation(new Point(0, initPos + 2));
        white.getMonsterBy('#').setInitialLocation(new Point(0, initPos + 2));
        board[0][initPos + 3] = " " + white.getMonsterBy('*').getMonsterSymbol() + " ";
        white.getMonsterBy('*').setNewLocation(new Point(0, initPos + 3));
        white.getMonsterBy('*').setCurrentLocation(new Point(0, initPos + 3));
        white.getMonsterBy('*').setInitialLocation(new Point(0, initPos + 3));
        board[0][initPos + 4] = " " + white.getMonsterBy('@').getMonsterSymbol() + " ";
        white.getMonsterBy('@').setNewLocation(new Point(0, initPos + 4));
        white.getMonsterBy('@').setCurrentLocation(new Point(0, initPos + 4));
        white.getMonsterBy('@').setInitialLocation(new Point(0, initPos + 4));

        board[n - 1][initPos] = " " + black.getMonsterBy('$').getMonsterSymbol() + " ";
        black.getMonsterBy('$').setNewLocation(new Point(n - 1, initPos));
        black.getMonsterBy('$').setCurrentLocation(new Point(n - 1, initPos));
        board[n - 1][initPos + 1] = " " + black.getMonsterBy('^').getMonsterSymbol() + " ";
        black.getMonsterBy('^').setNewLocation(new Point(n - 1, initPos + 1));
        black.getMonsterBy('^').setCurrentLocation(new Point(n - 1, initPos + 1));
        board[n - 1][initPos + 2] = " " + black.getMonsterBy('#').getMonsterSymbol() + " ";
        black.getMonsterBy('#').setNewLocation(new Point(n - 1, initPos + 2));
        black.getMonsterBy('#').setCurrentLocation(new Point(n - 1, initPos + 2));
        board[n - 1][initPos + 3] = " " + black.getMonsterBy('*').getMonsterSymbol() + " ";
        black.getMonsterBy('*').setNewLocation(new Point(n - 1, initPos + 3));
        black.getMonsterBy('*').setCurrentLocation(new Point(n - 1, initPos + 3));
        board[n - 1][initPos + 4] = " " + black.getMonsterBy('@').getMonsterSymbol() + " ";
        black.getMonsterBy('@').setNewLocation(new Point(n - 1, initPos + 4));
        black.getMonsterBy('@').setCurrentLocation(new Point(n - 1, initPos + 4));

        printBoard(white, black);

    }

    public void printBoard(IPlayer white, IPlayer black) {

        removeIfDead(white, '$');
        removeIfDead(white, '^');
        removeIfDead(white, '#');
        removeIfDead(white, '*');
        removeIfDead(white, '@');

        printIfMoved(white, '$');
        printIfMoved(white, '^');
        printIfMoved(white, '#');
        printIfMoved(white, '*');
        printIfMoved(white, '@');

        removeIfDead(black, '$');
        removeIfDead(black, '^');
        removeIfDead(black, '#');
        removeIfDead(black, '*');
        removeIfDead(black, '@');

        printIfMoved(black, '$');
        printIfMoved(black, '^');
        printIfMoved(black, '#');
        printIfMoved(black, '*');
        printIfMoved(black, '@');

        System.out.print("    ");
        for (int i = 0; i < n; i++)
            if (i >= 10) System.out.print(" " + i);
            else System.out.print(" " + i + " ");
        System.out.println();

        for (int x = 0; x < board.length; x++) {
            if (x >= 10) System.out.print(" " + x + "|");
            else System.out.print("  " + x + "|");

            for (int y = 0; y < board[x].length; y++) {
                System.out.print(board[x][y]);
            }

            if (x == 0)
                System.out.println("|" + x + "   White " + white.getMonsterBy('$').getClass().getSimpleName() + " HP: " + white.getMonsterBy('$').getCurrentHealthPoints() + "   White " + white.getMonsterBy('^').getClass().getSimpleName() + " HP: " + white.getMonsterBy('^').getCurrentHealthPoints());
            else if (x == 1)
                System.out.println("|" + x + "   White " + white.getMonsterBy('#').getClass().getSimpleName() + " HP: " + white.getMonsterBy('#').getCurrentHealthPoints() + "   White " + white.getMonsterBy('*').getClass().getSimpleName() + " HP: " + white.getMonsterBy('*').getCurrentHealthPoints());
            else if (x == 2)
                System.out.println("|" + x + "   White " + white.getMonsterBy('@').getClass().getSimpleName() + " HP: " + white.getMonsterBy('@').getCurrentHealthPoints());
            else if (x == 5)
                System.out.println("|" + x + "   Black " + black.getMonsterBy('$').getClass().getSimpleName() + " HP: " + black.getMonsterBy('$').getCurrentHealthPoints() + "   Black " + black.getMonsterBy('^').getClass().getSimpleName() + " HP: " + black.getMonsterBy('^').getCurrentHealthPoints());
            else if (x == 6) {
                System.out.println("|" + x + "   Black " + black.getMonsterBy('#').getClass().getSimpleName() + " HP: " + black.getMonsterBy('#').getCurrentHealthPoints() + "   Black " + black.getMonsterBy('*').getClass().getSimpleName() + " HP: " + black.getMonsterBy('*').getCurrentHealthPoints());
            } else if (x == 7) {
                System.out.println("|" + x + "   Black " + black.getMonsterBy('@').getClass().getSimpleName() + " HP: " + black.getMonsterBy('@').getCurrentHealthPoints());
            } else
                System.out.println("|" + x);
        }

        System.out.print("    ");
        for (int i = 0; i < n; i++)
            if (i >= 10) System.out.print(" " + i);
            else System.out.print(" " + i + " ");
        System.out.println();

    }

    private void removeIfDead(IPlayer player, char monster) {
        if (player.getMonsterBy(monster).isRemoved()) {
            return;
        }

        if (player.getMonsterBy(monster).isDead()) {
            System.out.println(player.getClass().getSimpleName() + "'s " + player.getMonsterBy(monster).getClass().getSimpleName() + " is dead");

            Point point = new Point(player.getMonsterBy(monster).getNewLocation().getX(), player.getMonsterBy(monster).getNewLocation().getY());

            board[point.getX()][point.getY()] = "   ";

            player.getMonsterBy(monster).setNewLocation(null);
            player.getMonsterBy(monster).setRemoved(true);
        }
    }

    private void printIfMoved(IPlayer player, char monster) {
        if (player.getMonsterBy(monster).isDead()) {
            return;
        }
        if (isLocationAvailable(player.getMonsterBy(monster).getNewLocation())) {
            board[player.getMonsterBy(monster).getNewLocation().getX()][player.getMonsterBy(monster).getNewLocation().getY()] = " " + player.getMonsterBy(monster).getMonsterSymbol() + " ";
            if (player.getMonsterBy(monster).getCurrentLocation() != null) {
                board[player.getMonsterBy(monster).getCurrentLocation().getX()][player.getMonsterBy(monster).getCurrentLocation().getY()] = "   ";
            }
        }
    }

    /**
     * Checks if a location is available and it's not outside of the board.
     *
     * @param location location to be checked
     * @return true if available, false if not
     */
    public boolean isLocationAvailable(Point location) {
        if (location.getX() >= 0 && location.getX() < n && location.getY() >= 0 && location.getY() < n) {
            return board[location.getX()][location.getY()].equals("   ");
        }
        return false;
    }

}
