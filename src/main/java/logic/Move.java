package main.java.logic;

import static main.java.logic.DirectionOfMoving.Direction.*;

public class Move {
    private Enum[][] optionsOfDirections;
    public int xOption;
    public int yOption;
    public int counterOfFlips;
    private double scoreOfOption = 0;

    Enum[][] getDirections() {
        return optionsOfDirections;
    }

    boolean isMove() {
        for (int x = 0; x < optionsOfDirections.length; x++)
            if (optionsOfDirections[x][0] == EXIST)
                return true;
        return false;
    }

    public Move(Enum[][] directions, int x, int y) {
        xOption = x;
        yOption = y;
        optionsOfDirections = directions;
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