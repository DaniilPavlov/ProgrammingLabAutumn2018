package main.java.logic;

class ScoreAddition {
    boolean isCorner(Move move) {
        return (move.xOption == 1 || move.xOption == 8) && (move.yOption == 1 || move.yOption == 8);
    }

    boolean isTacticCorner(Move move, int[][] matrix, int numberOfTurn) {
        int nextTurn;
        if (numberOfTurn == 0)
            nextTurn = 1;
        else
            nextTurn = 0;
        if (((matrix[move.xOption + 1][move.yOption] == nextTurn) && (matrix[move.xOption - 1][move.yOption] == nextTurn)) ||
                ((matrix[move.xOption][move.yOption + 1] == nextTurn) && (matrix[move.xOption][move.yOption - 1] == nextTurn))) {
            return (((move.xOption == 1) || (move.xOption == 8)) && ((move.yOption == 3) || (move.yOption == 4) ||
                    (move.yOption == 5) || (move.yOption == 6))) ||
                    (((move.yOption == 1) || (move.yOption == 8)) && ((move.xOption == 3) || (move.xOption == 4) ||
                            (move.xOption == 5) || (move.xOption == 6)));
        }
        return false;
    }

    boolean isBorder(Move move) {
        return move.xOption == 1 || move.xOption == 8 || move.yOption == 1 || move.yOption == 8;
    }

    boolean isBorderDangerous(Move move) {
        return ((move.xOption == 3 || move.xOption == 4 || move.xOption == 5 || move.xOption == 6)
                && (move.yOption == 2 || move.yOption == 7)) || ((move.xOption == 2 || move.xOption == 7) &&
                (move.yOption == 3 || move.yOption == 4 || move.yOption == 5 || move.yOption == 6));
    }

    boolean isCornerDangerous(Move move) {
        return ((move.xOption == 2 || move.xOption == 7) && (move.yOption == 2 || move.yOption == 7)
                || (move.xOption == 2 || move.xOption == 7) && (move.yOption == 1 || move.yOption == 8)
                || (move.xOption == 1 || move.xOption == 8) && (move.yOption == 2 || move.yOption == 7));
    }
}