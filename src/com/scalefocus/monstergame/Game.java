package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.player.AbstractPlayer;
import com.scalefocus.monstergame.player.BlackPlayer;
import com.scalefocus.monstergame.player.WhitePlayer;

import java.util.Scanner;

/**
 * @author mariyan.topalov
 */
class Game {

    private static final Scanner input = new Scanner(System.in);

    private final Board board;

    private final AbstractPlayer white;

    private final AbstractPlayer black;

    private final UserInputValidation inputValidation;

    private int round;

    private boolean whiteOnTurn = true;


    Game() {
        this.board = new Board();
        this.white = new WhitePlayer(0, board.getSize(), board);
        this.black = new BlackPlayer(board.getSize() - 1, board.getSize(), board);
        this.inputValidation = new UserInputValidation();
        this.round = 1;
        outputCurrentState();
    }

    void play() {

        while (!white.isDead() && !black.isDead()) {

            if (whiteOnTurn) {
                whiteToMove();
            } else {
                blackToMove();
            }

            if (white.isDead()) {
                System.out.println("Black is victorious!");
            } else if (black.isDead()) {
                System.out.println("White is victorious!");
            }

        }
    }

    private void whiteToMove() {
        System.out.println("Round: " + round);
        System.out.println("White to move! Please insert action: ");

        String commands = input.nextLine();
        String[] commandsArray = commands.split(" ");
        if (commandsArray.length != 3) {
            board.printBoard();
            System.out.println("No such action!");
        }

        String action = commandsArray[0].toLowerCase();
        String firstParameter = commandsArray[1];
        String secondParameter = commandsArray[2];

        switch (action) {
            case "move":
                move(white, firstParameter, secondParameter);
                break;
            case "attack": {
                if (inputValidation.isAttackCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Attack failed! Try again!");
                    break;
                }

                char attackingMonster = commandsArray[1].charAt(0);
                char attackedMonster = commandsArray[2].charAt(0);

                boolean attackDone = white.attack(black, attackingMonster, attackedMonster, board);

                if (attackDone) {
                    System.out.println("Attack succeed!");
                    whiteOnTurn = false;
                } else {
                    System.out.println("Attack failed! Try again!");
                }
                outputCurrentState();
            }
            break;
            case "revive": {
                if (inputValidation.isReviveCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Revive failed! Try again!");
                    break;
                }
                char monsterToRevive = commandsArray[1].charAt(0);

                boolean reviveDone = ((WhitePlayer) white).revive(monsterToRevive, board);

                if (reviveDone) {
                    System.out.println("Revive succeed!");
                } else {
                    System.out.println("Revive failed! Try again!");
                }
                outputCurrentState();
            }
            break;
            default: {
                board.printBoard();
                System.out.println("No such action!");
            }
        }
    }

    private void blackToMove() {
        System.out.println("Black to move! Please insert action: ");
        String commands = input.nextLine();
        String[] commandsArray = commands.split(" ");
        String action = commandsArray[0].toLowerCase();

        switch (action) {
            default:
                board.printBoard();
                System.out.println("No such action!");
                break;
            case "move": {
                //validate is the move is invalid
                if (inputValidation.isMoveCommandInvalid(commandsArray[1], commandsArray[2])) {
                    board.printBoard();
                    System.out.println("Move failed! Try again!");
                    break;
                }

                //retrieve parameters(symbol and location) from the move command
                char monsterToMove = commandsArray[1].charAt(0);
                int y = Integer.parseInt(commandsArray[2].split(",")[0]);
                int x = Integer.parseInt(commandsArray[2].split(",")[1]);
                Point locationToMove = new Point(x, y);

                //call the actual move
                boolean moveResult = black.place(monsterToMove, locationToMove, board);

                //validate the result from the move operation
                if (moveResult) {
                    System.out.println("Move succeed!");
                    whiteOnTurn = true;
                    round++;
                } else {
                    System.out.println("Move failed! Try again!");
                }

                //print the current state on the board
                outputCurrentState();
            }
            break;
            case "attack": {
                if (inputValidation.isAttackCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Attack failed! Try again!");
                    break;
                }

                char myMonster = commandsArray[1].charAt(0);
                char attackedMonster = commandsArray[2].charAt(0);

                boolean attackDone = black.attack(white, myMonster, attackedMonster, board);

                if (attackDone) {
                    System.out.println("Attack succeed!");
                    whiteOnTurn = true;
                    round++;
                } else {
                    System.out.println("Attack failed! Try again!");
                }
                outputCurrentState();
            }
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

                outputCurrentState();
            }
            break;
        }
    }

    private void outputCurrentState() {
        white.printInfo();
        black.printInfo();
        board.printBoard();
    }

    private void move(Player player, String firstParameter, String secondParameter) {

        if (inputValidation.isMoveCommandInvalid(firstParameter, secondParameter)) {
            board.printBoard();
            System.out.println("Move failed! Try again!");
            return;
        }

        char monsterToMove = firstParameter.charAt(0);
        String[] locations = secondParameter.split(",");
        int y = Integer.parseInt(locations[0]);
        int x = Integer.parseInt(locations[1]);
        Point locationToMove = new Point(x, y);

        boolean moveDone = player.place(monsterToMove, locationToMove, board);

        if (moveDone) {
            System.out.println("Move succeed!");
            whiteOnTurn = !(player instanceof WhitePlayer);

        } else {
            System.out.println("Move failed! Try again!");
        }
        outputCurrentState();
    }
}



