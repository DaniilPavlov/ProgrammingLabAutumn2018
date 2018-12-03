import main.java.logic.ComputerDecision;
import main.java.logic.Move;
import main.java.logic.Reversi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static main.java.logic.Reversi.Status.*;
import static main.java.logic.DirectionOfMoving.Direction.*;


class ReversiTest {
    private Reversi reversi = new Reversi();

    @Test
    void moveInThisDirectionTest() {
        Enum[][] gameMatrix = new Enum[10][10];
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                gameMatrix[x][y] = VACANT;
            }
        }
        gameMatrix[6][5] = COMPUTER;
        gameMatrix[5][4] = COMPUTER;
        gameMatrix[4][3] = COMPUTER;
        gameMatrix[3][2] = PLAYER;
        assertEquals(EXIST, reversi.moveInThisDirection(gameMatrix, 7, 6, -1, -1, false));
        gameMatrix[4][3] = VACANT;
        assertEquals(NOT_EXIST, reversi.moveInThisDirection(gameMatrix, 7, 6, -1, -1, false));
    }

    @Test
    void choiceOfComputerTest() {
        Enum[][] gameMatrix = new Enum[10][10];
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                gameMatrix[x][y] = VACANT;
            }
        }
        gameMatrix[3][4] = COMPUTER;
        gameMatrix[3][5] = COMPUTER;
        gameMatrix[3][6] = COMPUTER;
        gameMatrix[3][7] = PLAYER;
        gameMatrix[4][4] = COMPUTER;
        gameMatrix[5][5] = PLAYER;
        gameMatrix[4][5] = PLAYER;
        Enum[][] directions = new Enum[3][2];
        directions[0][0] = NOT_EXIST;
        directions[0][1] = EAST;
        directions[1][0] = EXIST;
        directions[1][1] = SOUTH;
        directions[2][0] = EXIST;
        directions[2][1] = NORTH_EAST;
        Move move1 = new Move(directions, 3, 3);
        directions[0][0] = EAST;
        Move move2 = new Move(directions, 6, 6);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move1);
        moves.add(move2);
        assertEquals(move2, ComputerDecision.makeDecision(moves));
    }
}