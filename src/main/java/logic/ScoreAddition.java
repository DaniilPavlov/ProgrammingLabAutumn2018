package main.java.logic;

import main.java.GUI.Scoreboard;

import static main.java.logic.Reversi.Status.*;

public class ScoreAddition {
    public boolean isCorner(Move move) {
        return (move.xMoveDecision == 1 || move.xMoveDecision == 8) && (move.yMoveDecision == 1 || move.yMoveDecision == 8);
    }

    public boolean isTacticBorder(Move move, Enum[][] matrix) {
        if ((matrix[move.xMoveDecision + 1][move.yMoveDecision] == PLAYER && matrix[move.xMoveDecision - 1][move.yMoveDecision] == PLAYER) ||
                (matrix[move.xMoveDecision][move.yMoveDecision + 1] == PLAYER && matrix[move.xMoveDecision][move.yMoveDecision - 1] == PLAYER)) {
            return ((move.xMoveDecision == 1 || move.xMoveDecision == 8) && (move.yMoveDecision >= 3 && move.yMoveDecision <= 6)) ||
                    ((move.yMoveDecision == 1 || move.yMoveDecision == 8) && (move.xMoveDecision >= 3 && move.xMoveDecision <= 6));
        }
        return false;
    }

    public boolean isBorder(Move move) {
        return move.xMoveDecision == 1 || move.xMoveDecision == 8 || move.yMoveDecision == 1 || move.yMoveDecision == 8;
    }

    public boolean isBorderDangerous(Move move) {
        return ((move.xMoveDecision >= 3 && move.xMoveDecision <= 6)
                && (move.yMoveDecision == 2 || move.yMoveDecision == 7)) || ((move.xMoveDecision == 2 || move.xMoveDecision == 7) &&
                (move.yMoveDecision >= 3 && move.yMoveDecision <= 6));
    }

    public boolean isCornerDangerous(Move move) {
        return (move.xMoveDecision == 2 || move.xMoveDecision == 7) && (move.yMoveDecision == 2 || move.yMoveDecision == 7)
                || (move.xMoveDecision == 2 || move.xMoveDecision == 7) && (move.yMoveDecision == 1 || move.yMoveDecision == 8)
                || (move.xMoveDecision == 1 || move.xMoveDecision == 8) && (move.yMoveDecision == 2 || move.yMoveDecision == 7);
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