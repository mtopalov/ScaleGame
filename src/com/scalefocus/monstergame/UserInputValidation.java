package com.scalefocus.monstergame;

/**
 * @author Mariyan Topalov
 **/
class UserInputValidation {

    boolean isMoveCommandInvalid(String[] commands) {
        return commands.length != 3
                || commands[2] == null
                || commands[2].split(",").length < 2
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
