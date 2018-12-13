package main.java.GUI;

import main.java.logic.Move;
import main.java.logic.Reversi;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Update {
    public void updatingOfGameBoard(Reversi.Status[][] matrix, JButton[][] gameBoard, HashMap<String, Move> moves) {
       JButton[][] updatedGameBoard = gameBoard;
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                switch (matrix[x][y]) {
                    case COMPUTER:
                        updatedGameBoard[x][y].setBackground(Color.black);
                        if (updatedGameBoard[x][y].isEnabled()) {
                            updatedGameBoard[x][y].setEnabled(false);
                            updatedGameBoard[x][y].setBackground(Color.DARK_GRAY);
                        }
                        break;
                    case PLAYER:
                        updatedGameBoard[x][y].setText("");
                        updatedGameBoard[x][y].setBackground(Color.white);
                        break;
                    case VACANT:
                        updatedGameBoard[x][y].setText("");
                        updatedGameBoard[x][y].setBackground(Color.blue.darker());
                        updatedGameBoard[x][y].setEnabled(false);
                        break;
                    case POSSIBLE_MOVE:
                        updatedGameBoard[x][y].setBackground(Color.green);
                        updatedGameBoard[x][y].setFocusable(false);
                        updatedGameBoard[x][y].setEnabled(true);
                        updatedGameBoard[x][y].setText("");
                        if (moves.containsKey(x + "," + y))
                            updatedGameBoard[x][y].setText(moves.get(x + "," + y).counterOfFlips + "");
                        break;
                }
            }
        }
    }
}