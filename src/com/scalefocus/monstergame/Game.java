package com.scalefocus.monstergame;

import com.scalefocus.monstergame.board.Board;
import com.scalefocus.monstergame.board.Point;
import com.scalefocus.monstergame.contract.IBlackPlayer;
import com.scalefocus.monstergame.contract.IWhitePlayer;

import java.util.Scanner;

public class Game {

    private final IWhitePlayer whitePlayer;
    private final IBlackPlayer blackPlayer;
    private final Board board;
    private final Scanner in = new Scanner(System.in);

    private int round;

    boolean boosted;

    public Game() {
        this.whitePlayer = new WhitePlayer();
        this.blackPlayer = new BlackPlayer();
        this.board = new Board();
        this.round = 1;
    }

    public void start() {
        board.createBoard(whitePlayer, blackPlayer);
    }

    public void play() {
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
                    case "move": {
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

                System.out.println(blackPlayer.getMonsterBy('#').getDamage());


                System.out.println("Black to move! Please insert action: ");
                commands = in.nextLine();
                commandsArray = commands.split(" ");
                action = commandsArray[0].toLowerCase();


                switch (action) {
                    case "move": {
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
                            round++;

                        } else {
                            System.out.println("Move failed! Try again!");
                        }

                        board.printBoard(whitePlayer, blackPlayer);
                    }
                    break;
                    case "attack": {
                        char attackingMonster = commandsArray[1].charAt(0);
                        char attackedMonster = commandsArray[2].charAt(0);

                        actionDone = blackPlayer.attack(whitePlayer, attackingMonster, attackedMonster);

                        if (actionDone) {
                            System.out.println("Attack succeed!");
                            whitePlayer.setOnTurn(true);
                            blackPlayer.setOnTurn(false);
                            blackPlayer.getMonsterBy('$').setDamage(blackPlayer.getMonsterBy('$').getDamage() / 2);
                            blackPlayer.getMonsterBy('^').setDamage(blackPlayer.getMonsterBy('^').getDamage() / 2);
                            blackPlayer.getMonsterBy('#').setDamage(blackPlayer.getMonsterBy('#').getDamage() / 2);
                            blackPlayer.getMonsterBy('*').setDamage(blackPlayer.getMonsterBy('*').getDamage() / 2);
                            blackPlayer.getMonsterBy('@').setDamage(blackPlayer.getMonsterBy('@').getDamage() / 2);
                            boosted = false;
                            round++;

                        } else {
                            System.out.println("Attack failed! Try again!");
                        }
                        board.printBoard(whitePlayer, blackPlayer);
                    }
                    break;
                    case "skip": {
                        actionDone = true;
                        if (actionDone) {
                            System.out.println("Skip succeed!");
                            whitePlayer.setOnTurn(true);
                            blackPlayer.setOnTurn(false);
                            round++;

                        }
                    }
                    break;
                    case "boost-attack": {
                        if (boosted) {
                            System.out.println("Boost failed!"); break;
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
        } else {
            System.out.println("White is victorious!");
        }


    }


}


