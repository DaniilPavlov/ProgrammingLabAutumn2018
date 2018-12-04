package main.java.logic;

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
    NOT_EXIST;

    static int[] detectionOfDirectionToDelta(Direction direction) {
        int[] moveDirection = new int[2];
        switch (direction) {
            case EAST:
                moveDirection[0] = 1;
                moveDirection[1] = 0;
                break;
            case NORTH:
                moveDirection[0] = 0;
                moveDirection[1] = 1;
                break;
            case NORTH_EAST:
                moveDirection[0] = 1;
                moveDirection[1] = 1;
                break;
            case WEST:
                moveDirection[0] = -1;
                moveDirection[1] = 0;
                break;
            case SOUTH:
                moveDirection[0] = 0;
                moveDirection[1] = -1;
                break;
            case SOUTH_WEST:
                moveDirection[0] = -1;
                moveDirection[1] = -1;
                break;
            case SOUTH_EAST:
                moveDirection[0] = 1;
                moveDirection[1] = -1;
                break;
            case NORTH_WEST:
                moveDirection[0] = -1;
                moveDirection[1] = 1;
                break;
        }
        return moveDirection;
    }
}