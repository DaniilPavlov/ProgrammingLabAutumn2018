package main.java.GUI;

import main.java.logic.Move;
import main.java.logic.Reversi;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Update {
    public void updatingOfGameBoard(Reversi.Status[][] matrix, JButton[][] gameBoard, HashMap<String, Move> moves) {
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                switch (matrix[x][y]) {
                    case COMPUTER:
                        gameBoard[x][y].setBackground(Color.black);
                        if (gameBoard[x][y].isEnabled()) {
                            gameBoard[x][y].setEnabled(false);
                            gameBoard[x][y].setBackground(Color.DARK_GRAY);
                        }
                        break;
                    case PLAYER:
                        gameBoard[x][y].setText("");
                        gameBoard[x][y].setBackground(Color.white);
                        break;
                    case VACANT:
                        gameBoard[x][y].setText("");
                        gameBoard[x][y].setBackground(Color.blue.darker());
                        gameBoard[x][y].setEnabled(false);
                        break;
                    case POSSIBLE_MOVE:
                        gameBoard[x][y].setBackground(Color.green);
                        gameBoard[x][y].setFocusable(false);
                        gameBoard[x][y].setEnabled(true);
                        gameBoard[x][y].setText("");
                        if (moves.containsKey(x + "," + y))
                            gameBoard[x][y].setText(moves.get(x + "," + y).counterOfFlips + "");
                        break;
                }
            }
        }
    }
}