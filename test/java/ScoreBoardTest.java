import main.java.GUI.Scoreboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreBoardTest {
    private Scoreboard board = new Scoreboard();

    @Test
    void currentScoreTest() {
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
        board.currentScore(gameMatrix, 1);
        assertEquals(5, board.pointsOfPlayer);
        assertEquals(4, board.pointsOfComputer);
    }
}