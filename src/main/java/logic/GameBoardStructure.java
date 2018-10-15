package main.java.logic;

import javax.swing.*;

public class GameBoardStructure {
    public void addStructureToGameBoard(JButton[][] gameBoard, int[][] matrix) {
        gameBoard[4][4].setEnabled(false);
        gameBoard[5][5].setEnabled(false);
        gameBoard[4][5].setEnabled(false);
        gameBoard[5][4].setEnabled(false);
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrix[x][y] = 2;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 9;
            matrix[i][9] = 9;
            matrix[9][i] = 9;
            matrix[0][i] = 9;
        }
        matrix[4][4] = 1;
        matrix[5][5] = 1;
        matrix[5][4] = 0;
        matrix[4][5] = 0;
    }
}