import main.java.logic.Move;
import main.java.logic.Reversi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReversiTest {
    private Reversi reversi = new Reversi();

    @Test
    void isBordersNearTest() {
        int[][] gameMatrix = new int[10][10];
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                gameMatrix[x][y] = 2;
            }
        }
        gameMatrix[1][2] = 1;
        gameMatrix[3][4] = 0;
        gameMatrix[6][5] = 0;
        gameMatrix[1][6] = 1;
        gameMatrix[5][2] = 0;
        gameMatrix[3][5] = 1;
        gameMatrix[8][2] = 1;
        gameMatrix[8][3] = 0;
        gameMatrix[8][4] = 1;
        assertTrue(reversi.isBordersNear(gameMatrix, 4, 2));
        gameMatrix[3][4] = 2;
        gameMatrix[3][5] = 2;
        gameMatrix[1][6] = 2;
        assertFalse(reversi.isBordersNear(gameMatrix, 3, 7));
        assertFalse(reversi.isBordersNear(gameMatrix, 8, 4));
    }

    @Test
    void moveInThisDirectionTest() {
        int[][] gameMatrix = new int[10][10];
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                gameMatrix[x][y] = 2;
            }
        }
        gameMatrix[6][5] = 0;
        gameMatrix[5][4] = 0;
        gameMatrix[4][3] = 0;
        gameMatrix[3][2] = 1;
        assertEquals(1, reversi.moveInThisDirection(gameMatrix, 7, 6, -1, -1,
                1, false));
        gameMatrix[4][3] = 2;
        assertEquals(0, reversi.moveInThisDirection(gameMatrix, 7, 6, -1, -1,
                1, false));
    }

    @Test
    void choiceOfComputerTest() {
        int[][] gameMatrix = new int[10][10];
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                gameMatrix[x][y] = 2;
            }
        }
        gameMatrix[3][4] = 0;
        gameMatrix[3][5] = 0;
        gameMatrix[3][6] = 0;
        gameMatrix[3][7] = 1;
        gameMatrix[4][4] = 0;
        gameMatrix[5][5] = 1;
        gameMatrix[4][5] = 1;
        int[][] directions = new int[3][3];
        directions[0][0] = 0;
        directions[0][1] = 1;
        directions[0][2] = 0;
        directions[1][0] = 1;
        directions[1][1] = 0;
        directions[1][2] = 1;
        directions[2][0] = 1;
        directions[2][1] = 1;
        directions[2][2] = 1;
        Move move1 = new Move(directions, 3, 3);
        directions[0][0] = 1;
        directions[1][0] = 0;
        Move move2 = new Move(directions, 6, 6);
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move1);
        moves.add(move2);
        assertEquals(move2, reversi.choiceOfComputer(gameMatrix, moves, 1));
    }
}