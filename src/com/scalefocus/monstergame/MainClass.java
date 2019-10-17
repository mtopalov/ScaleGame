package com.scalefocus.monstergame;

/**
 * Class that serves as an entry point of the application.
 *
 * @author mariyan.topalov
 */
public class MainClass {

    /**
     * Call the main method of the application so it can be executed.
     *
     * @param args {@link String} - The command line arguments.
     */
    public static void main(String[] args) {
        // Intro
        System.out.println("Welcome to the Scale Monsters Console Board Game!");
        System.out.println("The game is based on the PvP principles.");
        System.out.println("Two players - White and Black fight each other till one of the players loses all his monsters.");
        System.out.println("At the start of the game, players choose the size of the board. The size must be at least 8.");
        System.out.println("Each of the players has 5 monsters : Werewolf, Vampire, Dragon, Demon and Warlock.");
        System.out.println("All of the monsters have their own attributes: Move range, Attack range, Damage, Health points and a unique Symbol that is used to visualize them at the playing board.");
        System.out.println("Werewolf has: Move range - 1 square, Attack range - 2 squares, Damage - 5, Health points - 20, Symbol - '$'.");
        System.out.println("Vampire has: Move range - 2 squares, Attack range - 1 square, Damage - 6, Health points - 20, Symbol - '^'.");
        System.out.println("Dragon has: Move range - 3 squares, Attack range - 3 squares, Damage - 8, Health points - 40, Symbol - '#'.");
        System.out.println("Demon has: Move range - 2 squares, Attack range - 2 squares, Damage - 2, Health points - 50, Symbol - '*'.");
        System.out.println("Warlock has: Move range - 1 square, Attack range - 2 squares, Damage - 7, Health points - 10, Symbol - '@'.");
        System.out.println("Each player can move his monster, attack opponent's monster and have 1 special skill.");
        System.out.println("The Move command is: \"move 'monster's symbol' location\" " +
                "(move # 4,3) where '#' is player's monster to move, 4,3 are the coordinates - 4 from top/bottom border, 3 from left/right border. \n" +
                "The coordinates must be separated by \',\'!");
        System.out.println("The Attack command is: \"attack 'attacking monster's Symbol' 'attacked monster's Symbol'\" " +
                "(attack # $) where '#' is attackint player's monster Symbol, '$' is attacked player's monster Symbol.");
        System.out.println("White's special skill is to revive a dead monster. \nCommand is:\"revive 'Symbol'\" (revive $) where '$' is the monster to be revived.");
        System.out.println("Black's special skill is to boost the attack of a monster for 1 turn. " +
                "\nCommand for boosted attack is: \"boost-attack 'Black's monster Symbol' 'White's monster Symbol\" (boost-attack # $) where '#' is Black's attacking monster" +
                "'$' is White's attacked monster.");

        Game game = new Game();
        game.play();
    }

}
