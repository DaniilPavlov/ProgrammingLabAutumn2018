package main.java.logic;

import static main.java.logic.Direction.*;

public class Move {
    private Direction[][] matrixOfPossibleDirections;
    public int xMoveDecision;
    public int yMoveDecision;
    public int counterOfFlips;
    private double scoreOfOption = 0;

    Direction[][] getDirections() {
        return matrixOfPossibleDirections;
    }

    boolean isMove() {
        for (int x = 0; x < matrixOfPossibleDirections.length; x++)
            if (matrixOfPossibleDirections[x][0] == EXIST)
                return true;
        return false;
    }

    public Move(Direction[][] directions, int x, int y) {
        xMoveDecision = x;
        yMoveDecision = y;
        matrixOfPossibleDirections = directions;
    }

    void addScore(int score) {
        scoreOfOption += score;
    }

    double getScore() {
        return scoreOfOption;
    }

    void setCount(int flipsCount) {
        counterOfFlips = flipsCount;
    }
}