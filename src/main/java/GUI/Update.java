package main.java.GUI;

import main.java.logic.Move;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Update {
    public void updatingOfGameBoard(int[][] matrix, JButton[][] gameBoard, HashMap<String, Move> moves) {
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (matrix[x][y] == 0) {
                    gameBoard[x][y].setBackground(Color.black);
                    if (gameBoard[x][y].isEnabled()) {
                        gameBoard[x][y].setEnabled(false);
                        gameBoard[x][y].setBackground(Color.DARK_GRAY);
                    }
                } else if (matrix[x][y] == 1) {
                    gameBoard[x][y].setText("");
                    gameBoard[x][y].setBackground(Color.white);
                } else if (matrix[x][y] == 2) {
                    gameBoard[x][y].setText("");
                    gameBoard[x][y].setBackground(Color.blue.darker());
                    gameBoard[x][y].setEnabled(false);
                } else if (matrix[x][y] == 3) {
                    gameBoard[x][y].setBackground(Color.green);
                    gameBoard[x][y].setFocusable(false);
                    gameBoard[x][y].setEnabled(true);
                    gameBoard[x][y].setText("");
                    if (moves.get(x + "," + y) != null) {
                        gameBoard[x][y].setText(moves.get(x + "," + y).counterOfFlips + "");
                    }
                }
            }
        }
    }
}