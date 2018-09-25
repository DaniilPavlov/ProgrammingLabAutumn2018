package main.java;

import main.java.logic.Reversi;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Reversi");
        frame.setContentPane(new Reversi());
        frame.setSize(600, 600);
        frame.setLocation(200, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}