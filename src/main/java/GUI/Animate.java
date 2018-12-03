package main.java.GUI;

import javax.swing.*;
import java.awt.*;

import static main.java.logic.Reversi.Status.*;

public class Animate {
    public void animationOfFlip(Enum turnStatus, JButton gameBoard) {
        gameBoard.setText("");
        Color color;
        if (turnStatus == PLAYER) {
            color = new Color(0, 0, 0);
            for (int i = 0; i < 40; i++) {
                color = color.brighter();
                draw(gameBoard, color);
            }
        } else {
            color = new Color(255, 255, 255);
            for (int i = 0; i < 40; i++) {
                color = color.darker();
                draw(gameBoard, color);
            }
        }
    }

    private void draw(JButton gameBoard, Color color) {
        gameBoard.setBackground(color);
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}