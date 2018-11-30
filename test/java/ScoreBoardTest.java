import main.java.GUI.Scoreboard;
import main.java.logic.ScoreAddition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static main.java.logic.Reversi.Status.*;

class ScoreBoardTest {
    private Scoreboard scoreboard = new Scoreboard();
    private ScoreAddition score = new ScoreAddition();

    @Test
    void currentScoreTest() {
        Enum[][] gameMatrix = new Enum[10][10];
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                gameMatrix[x][y] = VACANT;
            }
        }
        gameMatrix[1][2] = PLAYER;
        gameMatrix[3][4] = COMPUTER;
        gameMatrix[6][5] = COMPUTER;
        gameMatrix[1][6] = PLAYER;
        gameMatrix[5][2] = COMPUTER;
        gameMatrix[3][5] = PLAYER;
        gameMatrix[8][2] = PLAYER;
        gameMatrix[8][3] = COMPUTER;
        gameMatrix[8][4] = PLAYER;
        score.currentScore(gameMatrix, scoreboard);
        assertEquals(5, scoreboard.pointsOfPlayer);
        assertEquals(4, scoreboard.pointsOfComputer);
    }
}