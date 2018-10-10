package main.java.logic;

public class Move {
    private int[][] optionsOfDirections;
    int xOption;
    int yOption;
    public int counterOfOptions;
    double rankOfOption = 0;

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

    Move(int[][] directions, int x, int y) {
        xOption = x;
        yOption = y;
        optionsOfDirections = directions;
    }

    void addRank(int addition) {
        rankOfOption += addition;
    }

    double getRank() {
        return rankOfOption;
    }

    void setCount(int count) {
        counterOfOptions = count;
    }
}