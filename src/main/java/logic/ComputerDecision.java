package main.java.logic;

import java.util.ArrayList;

public class ComputerDecision {
    public static Move choiceOfComputer(ArrayList<Move> moves) {
        int maxIndex = 0;
        ScoreAddition score = new ScoreAddition();
        if (moves.isEmpty())
            return new Move(new Enum[8][3], -1, -1);
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            move.addScore(move.counterOfFlips);
            if (score.isCorner(move))
                move.addScore(15);
            if (score.isTacticBorder(move, Reversi.matrix))
                move.addScore(10);
            if (score.isBorder(move))
                move.addScore(5);
            if (score.isBorderDangerous(move))
                move.addScore(-6);
            if (score.isCornerDangerous(move))
                move.addScore(-12);
        }
        for (int i = 0; i < moves.size(); i++)
            if (moves.get(i).getScore() >= moves.get(maxIndex).getScore())
                maxIndex = i;
        return moves.get(maxIndex);
    }
}