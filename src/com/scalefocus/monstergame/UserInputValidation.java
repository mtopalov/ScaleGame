package com.scalefocus.monstergame;

import com.scalefocus.monstergame.contract.Monster;
import com.scalefocus.monstergame.player.AbstractPlayer;
import com.scalefocus.monstergame.player.WhitePlayer;

/**
 * Class that implements methods that validates the user input.
 * @author Mariyan Topalov
 **/
class UserInputValidation {

    /**
     * Method that validates if the user input for the "move" operation, implemented in the {@link AbstractPlayer} class, is invalid.
     * @param commands {@link String} - the command, given by the user.
     * @return {@link Boolean} - true if the operation is invalid, otherwise false.
     */
    boolean isMoveCommandInvalid(String[] commands) {
        if (commands[2] == null) return false;
        String[] secondParameterSplit = commands[2].split(",");
        return commands[1].length() > 1
                || !isMonster(commands[1].charAt(0))
                || secondParameterSplit.length != 2
                || !secondParameterSplit[0].chars().allMatch(Character::isDigit)
                || !secondParameterSplit[1].chars().allMatch(Character::isDigit)
                || !commands[2].contains(",");
    }
    /**
     * Method that validates if the user input for the "attack" operation, implemented in the {@link AbstractPlayer} class, is invalid.
     * @param commands {@link String} - the command, given by the user.
     * @return {@link Boolean} - true if the operation is invalid, otherwise false.
     */
    boolean isAttackCommandInvalid(String[] commands) {
        return commands.length != 3
                || commands[1].length() > 1
                || commands[2].length() > 1
                || !isMonster(commands[1].charAt(0))
                || !isMonster(commands[2].charAt(0));
    }

    /**
     * Method that validates if the user input for the "revive" operation, implemented in the {@link WhitePlayer} class, is invalid.
     * @param commands {@link String} - the command, given by the user.
     * @return {@link Boolean} - true if the operation is invalid, otherwise false.
     */
    boolean isReviveCommandInvalid(String[] commands) {
        return commands.length != 2
                || !isMonster(commands[1].charAt(0));
    }

    /**
     * Method that validates if a {@link Monster} with a symbol, given as param, exists.
     * @param symbol {@link Character} - symbol to be checked
     * @return {@link Boolean} - true if monster with given symbol exists, otherwise false
     */
   private boolean isMonster(char symbol) {
        return symbol == '@'
                || symbol == '#'
                || symbol == '$'
                || symbol == '*'
                || symbol == '^';
    }
}
