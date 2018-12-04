package main.java.logic;

import java.util.Collection;
import java.util.List;

public class ComputerDecision {
    public static Move makeDecision(List<Move> listOfComputerMoves) {
        int maxIndex = 0;
        ScoreAddition score = new ScoreAddition();
        if (listOfComputerMoves.isEmpty())
            return new Move(new Direction[8][2], -1, -1);
        for (int i = 0; i < listOfComputerMoves.size(); i++) {
            Move move = listOfComputerMoves.get(i);
            move.addScore(move.counterOfFlips);
            if (score.isCorner(move))
                move.addScore(30);
            if (score.isTacticBorder(move, Reversi.matrix))
                move.addScore(15);
            if (score.isBorder(move))
                move.addScore(5);
            if (score.isBorderDangerous(move))
                move.addScore(-6);
            if (score.isCornerDangerous(move))
                move.addScore(-12);
        }
        for (int i = 0; i < listOfComputerMoves.size(); i++)
            if (listOfComputerMoves.get(i).getScore() >= listOfComputerMoves.get(maxIndex).getScore())
                maxIndex = i;
        return listOfComputerMoves.get(maxIndex);
    }
}