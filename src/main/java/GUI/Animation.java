package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class Animation {
    public void animationOfFlip(int positionOfX, int positionOfY, int indexOfTurn, JButton[][] gameBoard) {
        gameBoard[positionOfX][positionOfY].setText("");
        Color color = new Color(255, 255, 255);
        if (indexOfTurn == 1) {
            color = new Color(0, 0, 0);
            for (int i = 0; i < 40; i++) {
                color = color.brighter();
                gameBoard[positionOfX][positionOfY].setBackground(color);
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < 40; i++) {
                color = color.darker();
                gameBoard[positionOfX][positionOfY].setBackground(color);
                try {
                    Thread.sleep(25);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}