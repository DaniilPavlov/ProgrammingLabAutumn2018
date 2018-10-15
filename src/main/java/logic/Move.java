package main.java.logic;

public class Move {
    private int[][] optionsOfDirections;
    public int xOption;
    public int yOption;
    public int counterOfFlips;
    private double scoreOfOption = 0;

    int[][] getDirections() {
        return optionsOfDirections;
    }

    boolean isMove() {
        for (int x = 0; x < optionsOfDirections.length; x++) {
            if (optionsOfDirections[x][0] == 1)
                return true;
        }
        return false;
    }

    public Move(int[][] directions, int x, int y) {
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