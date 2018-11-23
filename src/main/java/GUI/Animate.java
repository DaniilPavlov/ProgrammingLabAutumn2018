package main.java.GUI;

import javax.swing.*;
import java.awt.*;

import static main.java.logic.StatusOfCell.Status.*;

public class Animate {
    public void animationOfFlip(int xPosition, int yPosition, Enum turnStatus, JButton[][] gameBoard) {
        gameBoard[xPosition][yPosition].setText("");
        Color color;
        if (turnStatus == PLAYER) {
            color = new Color(0, 0, 0);
            for (int i = 0; i < 40; i++) {
                color = color.brighter();
                draw(xPosition, yPosition, gameBoard, color);
            }
        } else {
            color = new Color(255, 255, 255);
            for (int i = 0; i < 40; i++) {
                color = color.darker();
                draw(xPosition, yPosition, gameBoard, color);
            }
        }
    }

    private void draw(int positionOfX, int positionOfY, JButton[][] gameBoard, Color color) {
        gameBoard[positionOfX][positionOfY].setBackground(color);
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}