package com.scalefocus.monstergame.board;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Class that provides matrix of type String which holds
 * all Players' monsters' symbols, visualizing them at the location they are.
 * The size of the matrix is defined with user input.
 *
 * @author mariyan.topalov
 */
public class Board {

    private static final Scanner input = new Scanner(System.in);

    private static final String EMPTY_PLACE = "   ";

    private static final String INDENT = " ";

    private final int size;

    private final String[][] board;

    /**
     * Constructor that initializes the board's size through user input,
     * based on the rule that the size must be greater than 8,
     * initializes the board itself and fills the board with empty values.
     */
    public Board() {
        int number = 0;

        do {
            System.out.print("Please select the size of the map (must be 8 or greater): ");
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
            Arrays.fill(strings, EMPTY_PLACE);
        }
    }

    public int getSize() {
        return size;
    }


    /**
     * Method that outputs the board.
     */
    public void printBoard() {
        printBorder();

        for (int x = 0; x < board.length; x++) {
            if (x >= 10) {
                System.out.print(INDENT + x + "|");
            } else {
                System.out.print(INDENT + INDENT + x + "|");
            }

            for (int y = 0; y < board[x].length; y++) {
                System.out.print(board[x][y]);
            }
            System.out.println("|" + x);
        }

        printBorder();
    }

    /**
     * tyuka dumpq gore i dolu granici
     */
    private void printBorder() {
        System.out.print("    ");
        for (int i = 0; i < size; i++) {
            if (i >= 10) {
                System.out.print(INDENT + i);
            } else {
                System.out.print(INDENT + i + INDENT);
            }
        }
        System.out.println();
    }

    /**
     * Method that adds a Monster to the given location.
     *
     * @param monster  Monster to be added to the Board.
     * @param location Location at which the Monster will be added.
     */
    public void setMonster(char monster, Point location) {
        board[location.getX()][location.getY()] = " " + monster + " ";
    }

    public void clear(Point location) {
        board[location.getX()][location.getY()] = EMPTY_PLACE;
    }

    /**
     * Method that check if a location is available - it's not outside the board and it's empty.
     *
     * @param location location on the board to be checked.
     * @return true if the location is inside the board and it's empty, otherwise false.
     */
    public boolean isLocationAvailable(Point location) {
        if (location.getX() >= 0 && location.getX() < this.getSize() && location.getY() >= 0 && location.getY() < this.getSize()) {
            return this.board[location.getX()][location.getY()].equals(EMPTY_PLACE);
        }
        return false;
    }
}
