package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {
    public int pointsOfComputer;
    public int pointsOfPlayer;
    private JLabel playerStyle;
    private JLabel computerStyle;

    public Scoreboard() {
        playerStyle = new JLabel("", JLabel.CENTER);
        add(playerStyle);
        JLabel nameOfGameStyle = new JLabel("Reversi");
        nameOfGameStyle.setFont(new Font("Dialog", Font.BOLD | Font.PLAIN, 30));
        add(nameOfGameStyle);
        computerStyle = new JLabel("", JLabel.CENTER);
        add(computerStyle);
    }

    public void currentScore(int[][] gameMatrix, int numberOfTurn) {
        if (numberOfTurn % 2 == 0) {
            computerStyle.setOpaque(true);
            computerStyle.setBackground(Color.GREEN);
            playerStyle.setOpaque(false);
        } else {
            playerStyle.setOpaque(true);
            playerStyle.setBackground(Color.GREEN);
            computerStyle.setOpaque(false);
        }
        pointsOfPlayer = 0;
        pointsOfComputer = 0;
        for (int x = 1; x < gameMatrix.length - 1; x++) {
            for (int y = 1; y < gameMatrix[x].length - 1; y++) {
                if (gameMatrix[x][y] == 1) pointsOfPlayer++;
                else if (gameMatrix[x][y] == 0) pointsOfComputer++;
            }
        }

        playerStyle.setText("Player: " + pointsOfPlayer);
        computerStyle.setText("Computer: " + pointsOfComputer);
    }
}