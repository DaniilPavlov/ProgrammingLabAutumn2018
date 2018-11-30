import main.java.GUI.Animate;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static main.java.logic.Reversi.Status.*;

class AnimateTest {
    private Animate animate = new Animate();

    @Test
    void AnimationBlackAndWhiteTest() {
        JButton[][] gameBoard = new JButton[10][10];
        for (int row = 1; row < gameBoard.length - 1; row++) {
            for (int column = 1; column < gameBoard[row].length - 1; column++) {
                gameBoard[row][column] = new JButton();
                gameBoard[row][column].setBackground(Color.blue.darker());
            }
        }
        animate.animationOfFlip(3, 3, COMPUTER, gameBoard);
        assertEquals(new Color(0, 0, 0), gameBoard[3][3].getBackground());
        animate.animationOfFlip(7, 7, PLAYER, gameBoard);
        assertEquals(new Color(255, 255, 255), gameBoard[7][7].getBackground());
    }
}