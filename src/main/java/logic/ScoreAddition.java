package main.java.logic;

import main.java.GUI.Scoreboard;

import static main.java.logic.StatusOfCell.Status.*;

public class ScoreAddition {
    public boolean isCorner(Move move) {
        return (move.xOption == 1 || move.xOption == 8) && (move.yOption == 1 || move.yOption == 8);
    }

    public boolean isTacticBorder(Move move, Enum[][] matrix) {
        if ((matrix[move.xOption + 1][move.yOption] == PLAYER && matrix[move.xOption - 1][move.yOption] == PLAYER) ||
                (matrix[move.xOption][move.yOption + 1] == PLAYER && matrix[move.xOption][move.yOption - 1] == PLAYER)) {
            return ((move.xOption == 1 || move.xOption == 8) && (move.yOption >= 3 && move.yOption <= 6)) ||
                    ((move.yOption == 1 || move.yOption == 8) && (move.xOption >= 3 && move.xOption <= 6));
        }
        return false;
    }

    public boolean isBorder(Move move) {
        return move.xOption == 1 || move.xOption == 8 || move.yOption == 1 || move.yOption == 8;
    }

    public boolean isBorderDangerous(Move move) {
        return ((move.xOption >= 3 && move.xOption <= 6)
                && (move.yOption == 2 || move.yOption == 7)) || ((move.xOption == 2 || move.xOption == 7) &&
                (move.yOption >= 3 && move.yOption <= 6));
    }

    public boolean isCornerDangerous(Move move) {
        return (move.xOption == 2 || move.xOption == 7) && (move.yOption == 2 || move.yOption == 7)
                || (move.xOption == 2 || move.xOption == 7) && (move.yOption == 1 || move.yOption == 8)
                || (move.xOption == 1 || move.xOption == 8) && (move.yOption == 2 || move.yOption == 7);
    }

    public void currentScore(Enum[][] gameMatrix, Scoreboard scoreboard) {
        scoreboard.pointsOfPlayer = 0;
        scoreboard.pointsOfComputer = 0;
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                if (gameMatrix[x][y] == PLAYER)
                    scoreboard.pointsOfPlayer++;
                else if (gameMatrix[x][y] == COMPUTER)
                    scoreboard.pointsOfComputer++;
            }
        }
    }
}