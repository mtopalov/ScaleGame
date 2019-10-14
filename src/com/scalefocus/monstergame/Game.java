package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.IBlackPlayer;
import com.scalefocus.monstergame.contract.IWhitePlayer;
import com.scalefocus.monstergame.player.BlackPlayer;
import com.scalefocus.monstergame.player.WhitePlayer;

import java.util.Scanner;

/**
 * @author mariyan.topalov
 */
class Game {

    private static final Scanner in = new Scanner(System.in);

    private final IWhitePlayer whitePlayer;

    private final IBlackPlayer blackPlayer;

    private final Board board;

    private int round;

    private boolean boosted;

    Game() {
        this.whitePlayer = new WhitePlayer();
        this.blackPlayer = new BlackPlayer();
        this.board = new Board();
        this.round = 1;
    }

    void start() {
        board.createBoard(whitePlayer, blackPlayer);
    }

    void play() {
        String commands;
        String[] commandsArray;
        String action;

        while (!whitePlayer.isTeamDead() && !blackPlayer.isTeamDead()) {

            if (whitePlayer.isOnTurn()) {

                System.out.println("Round: " + round);
                boolean actionDone = false;

                System.out.println("White to move! Please insert action: ");
                commands = in.nextLine();
                commandsArray = commands.split(" ");
                action = commandsArray[0].toLowerCase();

                switch (action) {
                    default:
                        board.printBoard(whitePlayer, blackPlayer);
                        System.out.println("No such action!");
                        break;

                    case "move": {
                        if (isMoveCommandInvalid(commandsArray)) {
                            board.printBoard(whitePlayer, blackPlayer);
                            System.out.println("Move failed! Try again!");
                            break;
                        }

                        char monsterToMove = commandsArray[1].charAt(0);
                        int y = Integer.parseInt(commandsArray[2].split(",")[0]);
                        int x = Integer.parseInt(commandsArray[2].split(",")[1]);
                        Point locationToMove = new Point(x, y);

                        if (board.isLocationAvailable(locationToMove)) {
                            actionDone = whitePlayer.move(monsterToMove, locationToMove);
                        }

                        if (actionDone) {
                            System.out.println("Move succeed!");
                            whitePlayer.setOnTurn(false);
                            blackPlayer.setOnTurn(true);
                        } else {
                            System.out.println("Move failed! Try again!");
                        }

                        board.printBoard(whitePlayer, blackPlayer);
                    }
                    break;
                    case "attack": {
                        if (isAttackCommandInvalid(commandsArray)) {
                            board.printBoard(whitePlayer, blackPlayer);
                            System.out.println("Attack failed! Try again!");
                            break;
                        }

                        char attackingMonster = commandsArray[1].charAt(0);
                        char attackedMonster = commandsArray[2].charAt(0);

                        actionDone = whitePlayer.attack(blackPlayer, attackingMonster, attackedMonster);

                        if (actionDone) {
                            System.out.println("Attack succeed!");
                            whitePlayer.setOnTurn(false);
                            blackPlayer.setOnTurn(true);
                        } else {
                            System.out.println("Attack failed! Try again!");
                        }
                        board.printBoard(whitePlayer, blackPlayer);
                    }
                    break;
                    case "revive": {
                        char monsterToRevive = commandsArray[1].charAt(0);

                        actionDone = whitePlayer.revive(monsterToRevive);

                        if (actionDone) {
                            System.out.println("Revive succeed!");
                        } else {
                            System.out.println("Revive failed! Try again!");
                        }
                        board.printBoard(whitePlayer, blackPlayer);
                    }
                    break;
                }

            } else {
                boolean actionDone = false;

                System.out.println("Black to move! Please insert action: ");
                commands = in.nextLine();
                commandsArray = commands.split(" ");
                action = commandsArray[0].toLowerCase();


                switch (action) {
                    default:
                        board.printBoard(whitePlayer, blackPlayer);
                        System.out.println("No such action!");
                        break;
                    case "move": {

                        if (isMoveCommandInvalid(commandsArray)) {
                            board.printBoard(whitePlayer, blackPlayer);
                            System.out.println("Move failed! Try again!");
                            break;
                        }

                        char monsterToMove = commandsArray[1].charAt(0);
                        int y = Integer.parseInt(commandsArray[2].split(",")[0]);
                        int x = Integer.parseInt(commandsArray[2].split(",")[1]);

                        Point locationToMove = new Point(x, y);
                        if (board.isLocationAvailable(locationToMove)) {
                            actionDone = blackPlayer.move(monsterToMove, locationToMove);

                        }

                        if (actionDone) {
                            System.out.println("Move succeed!");
                            blackPlayer.setOnTurn(false);
                            whitePlayer.setOnTurn(true);
                            if (boosted) {
                                blackPlayer.getMonsterBy('$').setDamage(blackPlayer.getMonsterBy('$').getDamage() / 2);
                                blackPlayer.getMonsterBy('^').setDamage(blackPlayer.getMonsterBy('^').getDamage() / 2);
                                blackPlayer.getMonsterBy('#').setDamage(blackPlayer.getMonsterBy('#').getDamage() / 2);
                                blackPlayer.getMonsterBy('*').setDamage(blackPlayer.getMonsterBy('*').getDamage() / 2);
                                blackPlayer.getMonsterBy('@').setDamage(blackPlayer.getMonsterBy('@').getDamage() / 2);
                                boosted = false;
                            }
                            round++;

                        } else {
                            System.out.println("Move failed! Try again!");
                        }

                        board.printBoard(whitePlayer, blackPlayer);
                    }
                    break;
                    case "attack": {
                        if (isAttackCommandInvalid(commandsArray)) {
                            board.printBoard(whitePlayer, blackPlayer);
                            System.out.println("Attack failed! Try again!");
                            break;
                        }

                        char attackingMonster = commandsArray[1].charAt(0);
                        char attackedMonster = commandsArray[2].charAt(0);

                        actionDone = blackPlayer.attack(whitePlayer, attackingMonster, attackedMonster);

                        if (actionDone) {
                            System.out.println("Attack succeed!");
                            whitePlayer.setOnTurn(true);
                            blackPlayer.setOnTurn(false);
                            if (boosted) {
                                blackPlayer.getMonsterBy('$').setDamage(blackPlayer.getMonsterBy('$').getDamage() / 2);
                                blackPlayer.getMonsterBy('^').setDamage(blackPlayer.getMonsterBy('^').getDamage() / 2);
                                blackPlayer.getMonsterBy('#').setDamage(blackPlayer.getMonsterBy('#').getDamage() / 2);
                                blackPlayer.getMonsterBy('*').setDamage(blackPlayer.getMonsterBy('*').getDamage() / 2);
                                blackPlayer.getMonsterBy('@').setDamage(blackPlayer.getMonsterBy('@').getDamage() / 2);
                                boosted = false;
                            }

                            round++;
                        } else {
                            System.out.println("Attack failed! Try again!");
                        }
                        board.printBoard(whitePlayer, blackPlayer);
                    }
                    break;

                    case "boost-attack": {
                        if (boosted) {
                            System.out.println("Boost failed!");
                            break;
                        } else {
                            System.out.println(blackPlayer.getMonsterBy('#').getDamage());
                            actionDone = blackPlayer.boostAttack();
                            System.out.println(blackPlayer.getMonsterBy('#').getDamage());

                            if (actionDone) {
                                System.out.println("Boost succeed!");
                                boosted = true;
                            } else {
                                System.out.println("Boost failed!");
                            }
                            board.printBoard(whitePlayer, blackPlayer);
                        }
                    }
                    break;
                }
            }
        }
        if (whitePlayer.isTeamDead()) {
            System.out.println("Black is victorious!");
        }
        if(blackPlayer.isTeamDead()){
            System.out.println("White is victorious!");
        }


    }

    private boolean isMoveCommandInvalid(String[] commands) {
        return commands.length != 3
                || commands[2] == null
                || !Character.isDigit(commands[2].charAt(0))
                || !Character.isDigit(commands[2].charAt(commands.length - 1))
                || !commands[2].contains(",")
                || commands[1].length() > 1
                || ((commands[1].charAt(0) != '@')
                && (commands[1].charAt(0) != '#')
                && (commands[1].charAt(0) != '$')
                && (commands[1].charAt(0) != '*')
                && (commands[1].charAt(0) != '^'));
    }

    private boolean isAttackCommandInvalid(String[] commands) {
        return commands.length != 3
                || commands[1].length() > 1
                || commands[2].length() > 1
                || ((commands[1].charAt(0) != '@')
                && (commands[1].charAt(0) != '#')
                && (commands[1].charAt(0) != '$')
                && (commands[1].charAt(0) != '*')
                && (commands[1].charAt(0) != '^'))
                || ((commands[2].charAt(0) != '@')
                && (commands[2].charAt(0) != '#')
                && (commands[2].charAt(0) != '$')
                && (commands[2].charAt(0) != '*')
                && (commands[2].charAt(0) != '^'));
    }
}


