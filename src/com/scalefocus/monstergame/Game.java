package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Board;
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
        this.white = new WhitePlayer(board.getSize(), 0);
        this.black = new BlackPlayer(board.getSize(), board.getSize() - 1);
        this.inputValidation = new UserInputValidation();
        this.round = 1;
    }


    //    void play() {
    //        while (!white.isDead() && !black.isDead()) {
    //
    //            if (white.isOnTurn()) {
    //                whiteToMove();
    //
    //            } else {
    //                blackToMove();
    //
    //            }
    //        }
    //        if (white.isDead()) {
    //            System.out.println("Black is victorious!");
    //        }else{
    //            System.out.println("White is victorious!");
    //        }
    //    }
    //
    //    private void switchTurns(Player white, Player black) {
    //        if (white.isOnTurn()) {
    //            white.setOnTurn(false);
    //            black.setOnTurn(true);
    //        } else {
    //            black.setOnTurn(false);
    //            white.setOnTurn(true);
    //        }
    //    }
    //
    //    private void whiteToMove() {
    //        String commands;
    //        String[] commandsArray;
    //        String action;
    //        boolean actionDone = false;
    //
    //
    //        System.out.println("Round: " + round);
    //        System.out.println("White to move! Please insert action: ");
    //
    //        commands = input.nextLine();
    //        commandsArray = commands.split(" ");
    //        action = commandsArray[0].toLowerCase();
    //
    //        switch (action) {
    //            default:
    //                board.printBoard(white, black);
    //                System.out.println("No such action!");
    //                break;
    //
    //            case "move": {
    //                if (inputValidation.isMoveCommandInvalid(commandsArray)) {
    //                    board.printBoard(white, black);
    //                    System.out.println("Move failed! Try again!");
    //                    break;
    //                }
    //
    //                char monsterToMove = commandsArray[1].charAt(0);
    //                int y = Integer.parseInt(commandsArray[2].split(",")[0]);
    //                int x = Integer.parseInt(commandsArray[2].split(",")[1]);
    //                Point locationToMove = new Point(x, y);
    //
    //                if (board.isLocationAvailable(locationToMove)) {
    //                    actionDone = white.move(monsterToMove, locationToMove);
    //                }
    //
    //                if (actionDone) {
    //                    System.out.println("Move succeed!");
    //                    switchTurns(white, black);
    //                } else {
    //                    System.out.println("Move failed! Try again!");
    //                }
    //
    //                board.printBoard(white, black);
    //            }
    //            break;
    //            case "attack": {
    //                if (inputValidation.isAttackCommandInvalid(commandsArray)) {
    //                    board.printBoard(white, black);
    //                    System.out.println("Attack failed! Try again!");
    //                    break;
    //                }
    //
    //                char attackingMonster = commandsArray[1].charAt(0);
    //                char attackedMonster = commandsArray[2].charAt(0);
    //
    //                actionDone = white.attack(black, attackingMonster, attackedMonster);
    //
    //                if (actionDone) {
    //                    System.out.println("Attack succeed!");
    //                    switchTurns(white, black);
    //                } else {
    //                    System.out.println("Attack failed! Try again!");
    //                }
    //                board.printBoard(white, black);
    //            }
    //            break;
    //            case "revive": {
    //                if (inputValidation.isReviveCommandInvalid(commandsArray)) {
    //                    board.printBoard(white, black);
    //                    System.out.println("Revive failed! Try again!");
    //                    break;
    //                }
    //                char monsterToRevive = commandsArray[1].charAt(0);
    //
    //                actionDone = white.revive(monsterToRevive);
    //
    //                if (actionDone) {
    //                    System.out.println("Revive succeed!");
    //                } else {
    //                    System.out.println("Revive failed! Try again!");
    //                }
    //                board.printBoard(white, black);
    //            }
    //            break;
    //        }
    //    }
    //
    //    private void blackToMove() {
    //        String commands;
    //        String[] commandsArray;
    //        String action;
    //        boolean boosted = false;
    //
    //        boolean actionDone = false;
    //        System.out.println("Black to move! Please insert action: ");
    //        commands = input.nextLine();
    //        commandsArray = commands.split(" ");
    //        action = commandsArray[0].toLowerCase();
    //
    //
    //        switch (action) {
    //            default:
    //                board.printBoard(white, black);
    //                System.out.println("No such action!");
    //                break;
    //            case "move": {
    //
    //                if (inputValidation.isMoveCommandInvalid(commandsArray)) {
    //                    board.printBoard(white, black);
    //                    System.out.println("Move failed! Try again!");
    //                    break;
    //                }
    //
    //                char monsterToMove = commandsArray[1].charAt(0);
    //                int y = Integer.parseInt(commandsArray[2].split(",")[0]);
    //                int x = Integer.parseInt(commandsArray[2].split(",")[1]);
    //
    //                Point locationToMove = new Point(x, y);
    //                if (board.isLocationAvailable(locationToMove)) {
    //                    actionDone = black.move(monsterToMove, locationToMove);
    //
    //                }
    //
    //                if (actionDone) {
    //                    System.out.println("Move succeed!");
    //                    switchTurns(white, black);
    //                    if (boosted) {
    //                        black.removeBoost();
    //                        boosted = false;
    //                    }
    //                    round++;
    //
    //                } else {
    //                    System.out.println("Move failed! Try again!");
    //                }
    //
    //                board.printBoard(white, black);
    //            }
    //            break;
    //            case "attack": {
    //                if (inputValidation.isAttackCommandInvalid(commandsArray)) {
    //                    board.printBoard(white, black);
    //                    System.out.println("Attack failed! Try again!");
    //                    break;
    //                }
    //
    //                char attackingMonster = commandsArray[1].charAt(0);
    //                char attackedMonster = commandsArray[2].charAt(0);
    //
    //                actionDone = black.attack(white, attackingMonster, attackedMonster);
    //
    //                if (actionDone) {
    //                    System.out.println("Attack succeed!");
    //                    switchTurns(white, black);
    //
    //                    if (boosted) {
    //                        black.removeBoost();
    //                        boosted = false;
    //                    }
    //                    round++;
    //                } else {
    //                    System.out.println("Attack failed! Try again!");
    //                }
    //                board.printBoard(white, black);
    //            }
    //            break;
    //
    //            case "boost-attack": {
    //                if (boosted) {
    //                    board.printBoard(white, black);
    //                    System.out.println("Boost failed! Try again!");
    //                    break;
    //                } else {
    //                    if (inputValidation.isBoostCommandInvalid(commandsArray)) {
    //                        board.printBoard(white, black);
    //                        System.out.println("Boost failed! Try again!");
    //                        break;
    //                    }
    //
    //                    actionDone = black.boostAttack();
    //
    //                    if (actionDone) {
    //                        board.printBoard(white, black);
    //                        System.out.println("Boost succeed!");
    //                        boosted = true;
    //                    } else {
    //                        board.printBoard(white, black);
    //                        System.out.println("Boost failed! Try again!");
    //                    }
    //                }
    //            }
    //            break;
    //        }
    //    }
}


