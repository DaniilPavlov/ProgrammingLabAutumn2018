package main.java.logic;

import java.util.List;

public class Move {
    private List<Direction> matrixOfPossibleDirections;
    public int xMoveDecision;
    public int yMoveDecision;
    public int counterOfFlips;
    private double scoreOfOption = 0;

    List<Direction> getDirections() {
        return matrixOfPossibleDirections;
    }

    boolean isMovesExist() {
        return matrixOfPossibleDirections.size() != 0;
    }

    public Move(List<Direction> directions, int x, int y) {
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