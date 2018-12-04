package main.java.logic;

import javax.swing.*;

import static main.java.logic.Reversi.Status.*;
import static main.java.logic.Reversi.matrix;

public class GameBoardStructure {
    public void initializeGameBoard(JButton[][] gameBoard) {
        makeCellsVacant(matrix);
        putStartCheckers(gameBoard);
    }

    private void makeCellsVacant(Enum[][] matrix) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrix[x][y] = VACANT;
            }
        }
    }

    private void putStartCheckers(JButton[][] gameBoard) {
        gameBoard[4][4].setEnabled(false);
        gameBoard[5][5].setEnabled(false);
        gameBoard[4][5].setEnabled(false);
        gameBoard[5][4].setEnabled(false);
        matrix[4][4] = PLAYER;
        matrix[5][5] = PLAYER;
        matrix[5][4] = COMPUTER;
        matrix[4][5] = COMPUTER;
    }
}