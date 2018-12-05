import main.java.logic.Direction;
import main.java.logic.GameBoardStructure;
import main.java.logic.Move;
import main.java.logic.ScoreAddition;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static main.java.logic.Reversi.Status.*;

class ScoreAdditionTest {
    private ArrayList<Direction> optionsOfDirections;
    private Enum[][] matrix = new Enum[10][10];
    private int x;
    private int y;
    private ScoreAddition scoreAddition = new ScoreAddition();
    private Move move = new Move(optionsOfDirections, x, y);

    @Test
    void isCornerTestFalse() {
        move.xMoveDecision = 2;
        move.yMoveDecision = 2;
        assertFalse(scoreAddition.isCorner(move));
    }

    @Test
    void isCornerTestTrue() {
        move.xMoveDecision = 1;
        move.yMoveDecision = 1;
        assertTrue(scoreAddition.isCorner(move));
    }

    @Test
    void isTacticCornerTestTrue() {
        move.xMoveDecision = 1;
        move.yMoveDecision = 4;
        GameBoardStructure structure = new GameBoardStructure();
        JButton[][] gameBoard = new JButton[10][10];
        for (int row = 1; row < gameBoard.length - 1; row++) {
            for (int column = 1; column < gameBoard[row].length - 1; column++) {
                gameBoard[row][column] = new JButton();
                gameBoard[row][column].setBackground(Color.blue.darker());
            }
        }
        structure.initializeGameBoard(gameBoard);
        matrix[1][3] = PLAYER;
        matrix[1][4] = VACANT;
        matrix[1][5] = PLAYER;
        assertTrue(scoreAddition.isTacticBorder(move, matrix));
    }

    @Test
    void isTacticCornerTestFalse() {
        move.xMoveDecision = 1;
        move.yMoveDecision = 4;
        GameBoardStructure structure = new GameBoardStructure();
        JButton[][] gameBoard = new JButton[10][10];
        for (int row = 1; row < gameBoard.length - 1; row++) {
            for (int column = 1; column < gameBoard[row].length - 1; column++) {
                gameBoard[row][column] = new JButton();
                gameBoard[row][column].setBackground(Color.blue.darker());
            }
        }
        structure.initializeGameBoard(gameBoard);
        matrix[1][3] = COMPUTER;
        matrix[1][4] = COMPUTER;
        matrix[1][5] = VACANT;
        assertFalse(scoreAddition.isTacticBorder(move, matrix));
    }

    @Test
    void isBorderTestTrue() {
        move.xMoveDecision = 1;
        move.yMoveDecision = 5;
        assertTrue(scoreAddition.isBorder(move));
    }

    @Test
    void isBorderTestFalse() {
        move.xMoveDecision = 2;
        move.yMoveDecision = 5;
        assertFalse(scoreAddition.isBorder(move));
    }

    @Test
    void isBorderDangerousTestTrue() {
        move.xMoveDecision = 3;
        move.yMoveDecision = 7;
        assertTrue(scoreAddition.isBorderDangerous(move));
    }

    @Test
    void isBorderDangerousTestFalse() {
        move.xMoveDecision = 3;
        move.yMoveDecision = 6;
        assertFalse(scoreAddition.isBorderDangerous(move));
    }

    @Test
    void isisCornerDangerousTestTrue() {
        move.xMoveDecision = 2;
        move.yMoveDecision = 2;
        assertTrue(scoreAddition.isCornerDangerous(move));
    }

    @Test
    void isCornerDangerousFalse() {
        move.xMoveDecision = 2;
        move.yMoveDecision = 3;
        assertFalse(scoreAddition.isCornerDangerous(move));
    }
}