package main.java.logic;

import javax.swing.*;

import static main.java.logic.Reversi.Status.*;

public class GameBoardStructure {
    public void addStructureToGameBoard(JButton[][] gameBoard, Enum[][] matrix) {
        gameBoard[4][4].setEnabled(false);
        gameBoard[5][5].setEnabled(false);
        gameBoard[4][5].setEnabled(false);
        gameBoard[5][4].setEnabled(false);
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrix[x][y] = VACANT;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = BOARD;
            matrix[i][9] = BOARD;
            matrix[9][i] = BOARD;
            matrix[0][i] = BOARD;
        }
        matrix[4][4] = PLAYER;
        matrix[5][5] = PLAYER;
        matrix[5][4] = COMPUTER;
        matrix[4][5] = COMPUTER;
    }
}