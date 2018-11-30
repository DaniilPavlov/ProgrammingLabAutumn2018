package main.java.GUI;

import javax.swing.*;
import java.awt.*;

import static main.java.logic.Reversi.Status.*;


public class Scoreboard extends JPanel {
    public int pointsOfComputer;
    public int pointsOfPlayer;
    private JLabel playerStyle;
    private JLabel computerStyle;

    public Scoreboard() {
        playerStyle();
        gameStyle();
        computerStyle();
    }

    private void playerStyle() {
        playerStyle = new JLabel("", JLabel.CENTER);
        playerStyle.setBackground(Color.GREEN);
        add(playerStyle);
    }

    private void gameStyle() {
        JLabel nameOfGameStyle = new JLabel("          Reversi          ");
        nameOfGameStyle.setFont(new Font("Dialog", Font.BOLD | Font.PLAIN, 30));
        add(nameOfGameStyle);
    }

    private void computerStyle() {
        computerStyle = new JLabel("", JLabel.CENTER);
        computerStyle.setBackground(Color.GREEN);
        add(computerStyle);
    }

    public void changingVisualOfTurn(Enum statusOfTurn) {
        if (statusOfTurn == COMPUTER) {
            playerStyle.setOpaque(false);
            computerStyle.setOpaque(true);
        } else {
            computerStyle.setOpaque(false);
            playerStyle.setOpaque(true);
        }
        playerStyle.setText("Player: " + pointsOfPlayer);
        computerStyle.setText("Computer: " + pointsOfComputer);
    }
}