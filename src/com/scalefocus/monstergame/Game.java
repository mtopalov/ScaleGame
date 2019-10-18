package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Monster;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.player.AbstractPlayer;
import com.scalefocus.monstergame.player.BlackPlayer;
import com.scalefocus.monstergame.player.WhitePlayer;

import java.util.Scanner;

/**
 * Class that holds information for the whole game,
 * all {@link Player}s, the {@link Board} at which the game is played.
 *
 * @author mariyan.topalov
 */
class Game {

    private static final Scanner input = new Scanner(System.in);

    private final Board board;

    private final Player white;

    private final Player black;

    private final UserInputValidation inputValidation;

    private int round;

    private boolean whiteOnTurn = true;

    /**
     * Initializes all the fields declared.
     * Prints the initial state of the board.
     */
    Game() {
        this.board = new Board();
        this.white = new WhitePlayer(0, board.getSize(), board);
        this.black = new BlackPlayer(board.getSize() - 1, board.getSize(), board);
        this.inputValidation = new UserInputValidation();
        this.round = 1;
        printCurrentState();
    }

    /**
     * Method that has infinite loop at which {@link Player}s switch turns.
     * The loop ends when one of players is dead - all his monsters are dead.
     */
    void play() {

        while (!white.isDead() && !black.isDead()) {
            if (whiteOnTurn) {
                whiteToMove();
            } else {
                blackToMove();
            }

            if (white.isDead()) {
                System.out.println("-------------------------\n" +
                        "-------------------------\n" +
                        "   BLACK IS VICTORIOUS!\n" +
                        "   Rounds played: " + round + "\n" +
                        "-------------------------\n" +
                        "-------------------------");
            }
            if (black.isDead()) {
                System.out.println("-------------------------\n" +
                        "-------------------------\n" +
                        "   WHITE IS VICTORIOUS!\n" +
                        "   Rounds played: " + round + "\n" +
                        "-------------------------\n" +
                        "-------------------------");
            }
        }
    }

    private String[] parseInput() {
        String commands = input.nextLine();
        return commands.split(" ");
    }

    /**
     * Method that holds the operation calls of the {@link WhitePlayer}.
     * Validates the user input and decides which operation to call.
     */
    private void whiteToMove() {
        System.out.println("Round: " + round);
        System.out.println("White to move! Please insert action: ");

        String[] commandsArray = parseInput();
        String action = commandsArray[0];

        switch (action) {
            case "move":
                move(white, commandsArray);
                break;
            case "attack":
                attack(white, black, commandsArray);
                break;
            case "revive": {
                ///
                if (inputValidation.isReviveCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Revive failed! Try again!");
                    break;
                }
                char monsterToRevive = commandsArray[1].charAt(0);
//
                boolean reviveDone = ((WhitePlayer) white).revive(monsterToRevive, board);

                if (reviveDone) {
                    System.out.println("Revive succeed!");
                } else {
                    System.out.println("Revive failed! Try again!");
                }
                printCurrentState();
            }
            break;
            default: {
                board.printBoard();
                System.out.println("No such action!");
            }
        }
    }

    /**
     * Method that holds the operation calls of the {@link BlackPlayer}.
     * Validates the user input and decides which operation to call.
     */
    private void blackToMove() {
        System.out.println("Black to move! Please insert action: ");

        String[] commandsArray = parseInput();
        String action = commandsArray[0];

        switch (action) {
            case "move":
                move(black, commandsArray);
                round++;
                break;
            case "attack":
                attack(black, white, commandsArray);
                round++;
                break;
            case "boost-attack": {
                if (inputValidation.isAttackCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Attack failed! Try again!");
                    break;
                }
                char myMonster = commandsArray[1].charAt(0);
                char attackedMonster = commandsArray[2].charAt(0);

                boolean boostAttackDone = ((BlackPlayer) black).boostAttack(white, myMonster, attackedMonster, board);

                if (boostAttackDone) {
                    System.out.println("Boost-attack succeed!");
                    whiteOnTurn = true;
                } else {
                    System.out.println("Boost-attack failed! Try again!");
                }
                round++;
                printCurrentState();
            }
            break;
            default: {
                board.printBoard();
                System.out.println("No such action!");
            }
        }
    }

    /**
     * Prints the current state of the {@link Player}s and the {@link Board}.
     */
    private void printCurrentState() {
        System.out.println();
        ((AbstractPlayer) white).printInfo();
        ((AbstractPlayer) black).printInfo();
        board.printBoard();
    }

    /**
     * Method that validates the user input, given as param,
     * if valid - calls the {@link Player}'s method "place".
     * If true - switches turns of player and
     * prints the current state of the players and the {@link Board}.
     *
     * @param player        {@link Player} - the player that will move it's {@link Monster}
     * @param commandsArray {@link String} - the user input to be checked
     */
    private void move(Player player, String[] commandsArray) {
        //validates the user input, if invalid exits the method
        if (commandsArray.length != 3 || inputValidation.isMoveCommandInvalid(commandsArray)) {
            board.printBoard();
            System.out.println("Move failed! Try again!");
            return;
        }

        //retrieves parameters(symbol and location) from the move command
        char monsterToMove = commandsArray[1].charAt(0);
        String[] locations = commandsArray[2].split(",");
        int y = Integer.parseInt(locations[0]);
        int x = Integer.parseInt(locations[1]);
        Point locationToMove = new Point(x, y);

        //call the actual move
        boolean moveDone = player.place(monsterToMove, locationToMove, board);

        //validate the result from the move operation
        if (moveDone) {
            System.out.println("Move succeed!");
            whiteOnTurn = !(player instanceof WhitePlayer);
        } else {
            System.out.println("Move failed! Try again!");
        }

        //print the current state on the board
        printCurrentState();
    }

    /**
     * Method that validates the user input, given as param,
     * if valid - calls the  attacking {@link Player}'s method "attack".
     * If  the attack method returns true - switches turns of player and
     * prints the current state of the players and the {@link Board}.
     *
     * @param attacking     {@link Player} - the player that's attacking
     * @param attacked      {@link Player} - the player that's being attacked
     * @param commandsArray {@link String} - the user input that will be checked
     */
    private void attack(Player attacking, Player attacked, String[] commandsArray) {
        //validates the user input, if invalid exits the method
        if (inputValidation.isAttackCommandInvalid(commandsArray)) {
            board.printBoard();
            System.out.println("Attack failed! Try again!");
            return;
        }

        //retrieves parameters(attackingMonsterSymbol and attackedMonsterSymbol) from the move command
        char attackingMonster = commandsArray[1].charAt(0);
        char attackedMonster = commandsArray[2].charAt(0);

        //call the actual attack
        boolean attackDone = attacking.attack(attacked, attackingMonster, attackedMonster, board);

        //validate the result from the move operation
        if (attackDone) {
            System.out.println("Attack succeed!");
            whiteOnTurn = !(attacking instanceof WhitePlayer);
        } else {
            System.out.println("Attack failed! Try again!");
        }

        //print the current state on the board
        printCurrentState();

    }
}



