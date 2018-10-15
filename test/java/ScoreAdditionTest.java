import main.java.logic.GameBoardStructure;
import main.java.logic.Move;
import main.java.logic.ScoreAddition;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreAdditionTest {
    private int[][] optionsOfDirections;
    private int[][] matrix = new int[10][10];
    private int x;
    private int y;
    private ScoreAddition scoreAddition = new ScoreAddition();
    private Move move = new Move(optionsOfDirections, x, y);

    @Test
    void isCornerTestFalse() {
        move.xOption = 2;
        move.yOption = 2;
        assertFalse(scoreAddition.isCorner(move));
    }

    @Test
    void isCornerTestTrue() {
        move.xOption = 1;
        move.yOption = 1;
        assertTrue(scoreAddition.isCorner(move));
    }

    @Test
    void isTacticCornerTestTrue() {
        move.xOption = 1;
        move.yOption = 4;
        GameBoardStructure structure = new GameBoardStructure();
        JButton[][] gameBoard = new JButton[10][10];
        for (int row = 1; row < gameBoard.length - 1; row++) {
            for (int column = 1; column < gameBoard[row].length - 1; column++) {
                gameBoard[row][column] = new JButton();
                gameBoard[row][column].setBackground(Color.blue.darker());
            }
        }
        structure.addStructureToGameBoard(gameBoard, matrix);
        matrix[1][3] = 1;
        matrix[1][4] = 2;
        matrix[1][5] = 1;
        assertTrue(scoreAddition.isTacticCorner(move, matrix, 0));
    }

    @Test
    void isTacticCornerTestFalse() {
        move.xOption = 1;
        move.yOption = 4;
        GameBoardStructure structure = new GameBoardStructure();
        JButton[][] gameBoard = new JButton[10][10];
        for (int row = 1; row < gameBoard.length - 1; row++) {
            for (int column = 1; column < gameBoard[row].length - 1; column++) {
                gameBoard[row][column] = new JButton();
                gameBoard[row][column].setBackground(Color.blue.darker());
            }
        }
        structure.addStructureToGameBoard(gameBoard, matrix);
        matrix[1][3] = 0;
        matrix[1][4] = 0;
        matrix[1][5] = 2;
        assertFalse(scoreAddition.isTacticCorner(move, matrix, 1));
    }

    @Test
    void isBorderTestTrue() {
        move.xOption = 1;
        move.yOption = 5;
        assertTrue(scoreAddition.isBorder(move));
    }

    @Test
    void isBorderTestFalse() {
        move.xOption = 2;
        move.yOption = 5;
        assertFalse(scoreAddition.isBorder(move));
    }

    @Test
    void isBorderDangerousTestTrue() {
        move.xOption = 3;
        move.yOption = 7;
        assertTrue(scoreAddition.isBorderDangerous(move));
    }

    @Test
    void isBorderDangerousTestFalse() {
        move.xOption = 3;
        move.yOption = 6;
        assertFalse(scoreAddition.isBorderDangerous(move));
    }

    @Test
    void isisCornerDangerousTestTrue() {
        move.xOption = 2;
        move.yOption = 2;
        assertTrue(scoreAddition.isCornerDangerous(move));
    }

    @Test
    void isCornerDangerousFalse() {
        move.xOption = 2;
        move.yOption = 3;
        assertFalse(scoreAddition.isCornerDangerous(move));
    }
}
