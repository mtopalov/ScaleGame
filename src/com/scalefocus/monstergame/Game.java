package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.Player;
import com.scalefocus.monstergame.contract.White;
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


    Game() {
        this.board = new Board();
        this.white = new WhitePlayer(0, board.getSize(), board);
        this.black = new BlackPlayer(board.getSize() - 1, board.getSize(), board);
        this.inputValidation = new UserInputValidation();
        this.round = 1;
        white.printInfo();
        black.printInfo();
        board.printBoard();
    }

    void play() {
        while (!white.isDead() && !black.isDead()) {

            if (((WhitePlayer) white).isTurn()) {
                whiteToMove();
            } else {
                blackToMove();
            }
        }
    }


    private void whiteToMove() {
        String commands;
        String[] commandsArray;
        String action;
        boolean actionDone = false;

        System.out.println("Round: " + round);
        System.out.println("White to move! Please insert action: ");

        commands = input.nextLine();
        commandsArray = commands.split(" ");
        action = commandsArray[0].toLowerCase();

        switch (action) {
            default:
                board.printBoard();
                System.out.println("No such action!");
                break;

            case "move": {
                if (inputValidation.isMoveCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Move failed! Try again!");
                    break;
                }

                char monsterToMove = commandsArray[1].charAt(0);
                int y = Integer.parseInt(commandsArray[2].split(",")[0]);
                int x = Integer.parseInt(commandsArray[2].split(",")[1]);
                Point locationToMove = new Point(x, y);

                if (board.isLocationAvailable(locationToMove)) {
                    actionDone = white.place(monsterToMove, locationToMove, board);
                }

                if (actionDone) {
                    System.out.println("Move succeed!");
                    ((WhitePlayer) white).setTurn(false);

                } else {
                    System.out.println("Move failed! Try again!");
                }
                white.printInfo();
                black.printInfo();
                board.printBoard();
            }
            break;
            case "attack": {
                if (inputValidation.isAttackCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Attack failed! Try again!");
                    break;
                }

                char attackingMonster = commandsArray[1].charAt(0);
                char attackedMonster = commandsArray[2].charAt(0);

                actionDone = white.attack(black, attackingMonster, attackedMonster, board);

                if (actionDone) {
                    System.out.println("Attack succeed!");
                    ((WhitePlayer) white).setTurn(false);
                } else {
                    System.out.println("Attack failed! Try again!");
                }
                white.printInfo();
                black.printInfo();
                board.printBoard();
            }
            break;
            case "revive": {
                if (inputValidation.isReviveCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Revive failed! Try again!");
                    break;
                }
                char monsterToRevive = commandsArray[1].charAt(0);

                actionDone = ((WhitePlayer) white).revive(monsterToRevive, board);

                if (actionDone) {
                    System.out.println("Revive succeed!");
                } else {
                    System.out.println("Revive failed! Try again!");
                }
                white.printInfo();
                black.printInfo();
                board.printBoard();
            }
            break;
        }
    }

    private void blackToMove() {
        String commands;
        String[] commandsArray;
        String action;


        boolean actionDone = false;
        System.out.println("Black to move! Please insert action: ");
        commands = input.nextLine();
        commandsArray = commands.split(" ");
        action = commandsArray[0].toLowerCase();

        switch (action) {
            default:
                board.printBoard();
                System.out.println("No such action!");
                break;
            case "move": {

                if (inputValidation.isMoveCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Move failed! Try again!");
                    break;
                }

                char monsterToMove = commandsArray[1].charAt(0);
                int y = Integer.parseInt(commandsArray[2].split(",")[0]);
                int x = Integer.parseInt(commandsArray[2].split(",")[1]);

                Point locationToMove = new Point(x, y);
                if (board.isLocationAvailable(locationToMove)) {
                    actionDone = black.place(monsterToMove, locationToMove, board);

                }

                if (actionDone) {
                    System.out.println("Move succeed!");
                    ((WhitePlayer) white).setTurn(true);
                    round++;
                } else {
                    System.out.println("Move failed! Try again!");
                }
                white.printInfo();
                black.printInfo();
                board.printBoard();
            }
            break;
            case "attack": {
                if (inputValidation.isAttackCommandInvalid(commandsArray)) {
                    board.printBoard();
                    System.out.println("Attack failed! Try again!");
                    break;
                }

                char attackingMonster = commandsArray[1].charAt(0);
                char attackedMonster = commandsArray[2].charAt(0);

                actionDone = black.attack(white, attackingMonster, attackedMonster, board);

                if (actionDone) {
                    System.out.println("Attack succeed!");
                    ((WhitePlayer) white).setTurn(true);
                    round++;
                } else {
                    System.out.println("Attack failed! Try again!");
                }
                white.printInfo();
                black.printInfo();
                board.printBoard();
            }
            break;

            case "boost-attack": {
                char attackedMonster = commandsArray[1].charAt(0);
                char myMonster = commandsArray[2].charAt(0);

                actionDone = ((BlackPlayer) black).boostAttack(white, attackedMonster, myMonster);

                if (actionDone) {
                    System.out.println("Boost-attack succeed!");
                    ((WhitePlayer) white).setTurn(true);
                } else {
                    System.out.println("Boost-attack failed! Try again!");
                }

                white.printInfo();
                black.printInfo();
                board.printBoard();
            }
            break;
        }

    }
}



