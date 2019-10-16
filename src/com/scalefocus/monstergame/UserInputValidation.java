package com.scalefocus.monstergame;

/**
 * @author Mariyan Topalov
 **/
class UserInputValidation {

    boolean isMoveCommandInvalid(String firstParameter, String secondParameter) {
        if (secondParameter == null) return false;
        String[] secondParameterSplit = secondParameter.split(",");
        return ((firstParameter.charAt(0) != '@')
                && (firstParameter.charAt(0) != '#')
                && (firstParameter.charAt(0) != '$')
                && (firstParameter.charAt(0) != '*')
                && (firstParameter.charAt(0) != '^'))
                || firstParameter.length() > 1
                || secondParameterSplit.length < 2
                || !secondParameterSplit[0].chars().allMatch(Character::isDigit)
                || !secondParameterSplit[1].chars().allMatch(Character::isDigit)
                || !secondParameter.contains(",");
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
}
