package main.java.logic;

public class DirectionOfMoving {
    public enum Direction {
        NORTH,
        NORTH_EAST,
        EAST,
        SOUTH_EAST,
        SOUTH,
        SOUTH_WEST,
        WEST,
        NORTH_WEST,
        EXIST,
        NOT_EXIST
    }

    int[] detectionOfDirectionToDelta(Enum direction) {
        int[] moveDirection = new int[2];
        if (direction == Direction.EAST) {
            moveDirection[0] = 1;
            moveDirection[1] = 0;
        }
        if (direction == Direction.NORTH) {
            moveDirection[0] = 0;
            moveDirection[1] = 1;
        }
        if (direction == Direction.NORTH_EAST) {
            moveDirection[0] = 1;
            moveDirection[1] = 1;
        }
        if (direction == Direction.WEST) {
            moveDirection[0] = -1;
            moveDirection[1] = 0;
        }
        if (direction == Direction.SOUTH) {
            moveDirection[0] = 0;
            moveDirection[1] = -1;
        }
        if (direction == Direction.SOUTH_WEST) {
            moveDirection[0] = -1;
            moveDirection[1] = -1;
        }
        if (direction == Direction.SOUTH_EAST) {
            moveDirection[0] = 1;
            moveDirection[1] = -1;
        }
        if (direction == Direction.NORTH_WEST) {
            moveDirection[0] = -1;
            moveDirection[1] = 1;
        }
        return moveDirection;
    }
}