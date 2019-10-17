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
     * based on the only rule that the size must be greater than 8,
     * initializes the board itself and fills the board with empty values.
     * Also validates if the user input is not null, empty or contains non-digit values.
     */
    public Board() {
        int number = 0;

        do {
            System.out.print("Please select the size of the map (must be 8 or greater): ");
            String nextLine = input.nextLine();

            if (nextLine == null || nextLine.equals("")) {
                continue;
            }

            //checks if the user input contains only digits
            if (nextLine.chars().allMatch(Character::isDigit)) {
                number = Integer.parseInt(nextLine);
            }
        }
        while (number < 8);

        //initializing the board and filling it
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
     * Method that prints the board.
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
     * This method prints top and bottom borders.
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
     * Method that adds Monster's symbol to the given location at the current {@link Board}.
     *
     * @param symbol  {@link Character} - monster's symbol to be added to the {@link Board}
     * @param location {@link Point} - location at which the Monster's symbol will be added.
     */
    public void setMonster(char symbol, Point location) {
        board[location.getX()][location.getY()] = " " + symbol + " ";
    }

    public void clear(Point location) {
        board[location.getX()][location.getY()] = EMPTY_PLACE;
    }

    /**
     * Method that check if a location is available. If it's not outside of the board and it's empty, method returns true.
     *
     * @param location {@link Point} location on the board to be checked.
     * @return {@link Boolean} - true if the location is not outside of the board and it's empty, otherwise false.
     */
    public boolean isLocationAvailable(Point location) {
        if (location.getX() >= 0 && location.getX() < this.getSize() && location.getY() >= 0 && location.getY() < this.getSize()) {
            return this.board[location.getX()][location.getY()].equals(EMPTY_PLACE);
        }
        return false;
    }
}
