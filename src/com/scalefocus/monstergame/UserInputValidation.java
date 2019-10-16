package com.scalefocus.monstergame;

/**
 * @author Mariyan Topalov
 **/
class UserInputValidation {

    boolean isMoveCommandInvalid(String[] commands) {
        return commands.length != 3
                || ((commands[1].charAt(0) != '@')
                && (commands[1].charAt(0) != '#')
                && (commands[1].charAt(0) != '$')
                && (commands[1].charAt(0) != '*')
                && (commands[1].charAt(0) != '^'))
                || commands[1].length() > 1
                || commands[2] == null
                || commands[2].split(",").length < 2
                || !commands[2].split(",")[0].chars().allMatch(Character::isDigit)
                || !commands[2].split(",")[1].chars().allMatch(Character::isDigit)
                || !commands[2].contains(",");

    }

    boolean isAttackCommandInvalid(String[] commands) {
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

    boolean isReviveCommandInvalid(String[] commands) {
        return commands.length != 2
                || ((commands[1].charAt(0) != '@')
                && (commands[1].charAt(0) != '#')
                && (commands[1].charAt(0) != '$')
                && (commands[1].charAt(0) != '*')
                && (commands[1].charAt(0) != '^'));
    }

    boolean isBoostCommandInvalid(String[] commands) {
        return commands.length != 1;
    }
}
