package main.java.logic;

import main.java.GUI.Animation;
import main.java.GUI.Scoreboard;
import main.java.GUI.Update;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Reversi extends JPanel {
    private int flipsCount = 0;
    private int flagOfPossibilityMoving = 0;
    private int numberOfTurn = 1;
    private Move computerMove;
    private GameBoardStructure structure;
    private Scoreboard scoreboard;
    private Update update;
    private HashMap<String, Move> moves;
    private ArrayList<Move> listOfComputerMoves = new ArrayList<>();
    private int[][] matrix;
    private JButton[][] gameBoard;

    public Reversi() {
        setLayout(new BorderLayout());
        JPanel gameGrid = new JPanel();
        gameGrid.setLayout(new GridLayout(8, 8));
        add(gameGrid, BorderLayout.CENTER);
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout());
        add(north, BorderLayout.NORTH);
        scoreboard = new Scoreboard();
        north.add(scoreboard);
        JPanel south = new JPanel();
        north.setLayout(new FlowLayout());
        add(south, BorderLayout.SOUTH);
        JButton reset = new JButton();
        reset.addActionListener(new HandlerOfResetGame());
        reset.setText("      Reset      ");
        south.add(reset, BorderLayout.SOUTH);
        gameBoard = new JButton[10][10];
        update = new Update();
        matrix = new int[10][10];
        for (int row = 1; row < gameBoard.length - 1; row++) {
            for (int column = 1; column < gameBoard[row].length - 1; column++) {
                gameBoard[row][column] = new JButton();
                gameBoard[row][column].setBackground(Color.blue.darker());
                gameBoard[row][column].addActionListener(new HandlerOfGameThread(row, column));
                gameGrid.add(gameBoard[row][column]);
            }
        }
        structure = new GameBoardStructure();
        structure.addStructureToGameBoard(gameBoard, matrix);
        findPossibleMoves();
    }

    private void findPossibleMoves() {
        moves = searchForMoves(matrix, numberOfTurn % 2);
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (moves.get(x + "," + y) != null) {
                    matrix[x][y] = 3;
                }
            }
        }
        update.updatingOfGameBoard(matrix, gameBoard, moves);
        scoreboard.currentScore(matrix, numberOfTurn);
    }

    private void gameProcess(int currentRow, int currentColumn) {
        if (numberOfTurn % 2 == 1) {
            for (int x = 1; x < gameBoard.length - 1; x++) {
                for (int y = 1; y < gameBoard[x].length - 1; y++) {
                    gameBoard[x][y].setEnabled(false);
                }
            }
            System.out.println("Player turn");
        } else
            System.out.println("Computer turn");
        if (!moves.isEmpty()) {
            flagOfPossibilityMoving = 0;
            int[][] directions = moves.get(currentRow + "," + currentColumn).getDirections();
            for (int i = 0; i < directions.length; i++) {
                if (directions[i][0] == 1)
                    moveInThisDirection(matrix, currentRow, currentColumn, directions[i][1], directions[i][2],
                            numberOfTurn % 2, true);
            }
        } else {
            flagOfPossibilityMoving++;
            if (numberOfTurn % 2 == 1)
                System.out.println("Player can't move");
            else
                System.out.println("Computer can't move");
        }
        numberOfTurn++;
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (matrix[x][y] == 3)
                    matrix[x][y] = 2;
            }
        }
        findPossibleMoves();
        if (flagOfPossibilityMoving == 2 || scoreboard.pointsOfPlayer == 0
                || scoreboard.pointsOfComputer == 0) {
            if (scoreboard.pointsOfPlayer > scoreboard.pointsOfComputer)
                JOptionPane.showMessageDialog(this, "Player win!");
            else
                JOptionPane.showMessageDialog(this, "Computer win!");
            return;
        }
        if (numberOfTurn % 2 == 0 || moves.isEmpty()) {
            for (int x = 1; x < gameBoard.length - 1; x++) {
                for (int y = 1; y < gameBoard[x].length - 1; y++) {
                    gameBoard[x][y].setEnabled(false);
                }
            }
            computerMove = choiceOfComputer(matrix, listOfComputerMoves, numberOfTurn % 2);
            new Thread(() -> gameProcess(computerMove.xOption, computerMove.yOption)).start();
        }
    }

    private Move choiceOfComputer(int[][] matrix, ArrayList<Move> moves, int numberOfTurn) {
        int maxIndex = 0;
        ScoreAddition score = new ScoreAddition();
        if (moves.isEmpty())
            return new Move(new int[8][3], -1, -1);
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            move.addScore(move.counterOfFlips);
            if (score.isCorner(move))
                move.addScore(15);
            if (score.isTacticCorner(move, matrix, numberOfTurn))
                move.addScore(10);
            if (score.isBorder(move))
                move.addScore(5);
            if (score.isBorderDangerous(move))
                move.addScore(-6);
            if (score.isCornerDangerous(move))
                move.addScore(-12);
        }
        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i).getScore() >= moves.get(maxIndex).getScore())
                maxIndex = i;
        }
        return moves.get(maxIndex);
    }

    private HashMap<String, Move> searchForMoves(int[][] matrix, int numberOfTurn) {
        HashMap<String, Move> moves = new HashMap<>();
        listOfComputerMoves.clear();
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (isBordersNear(matrix, x, y)) {
                    int[][] directions = new int[8][3];
                    flipsCount = 0;
                    directions[0][0] = moveInThisDirection(matrix, x, y, 1, 0, numberOfTurn, false);
                    directions[0][1] = 1;
                    directions[0][2] = 0;
                    directions[1][0] = moveInThisDirection(matrix, x, y, 0, 1, numberOfTurn, false);
                    directions[1][1] = 0;
                    directions[1][2] = 1;
                    directions[2][0] = moveInThisDirection(matrix, x, y, 1, 1, numberOfTurn, false);
                    directions[2][1] = 1;
                    directions[2][2] = 1;
                    directions[3][0] = moveInThisDirection(matrix, x, y, -1, 0, numberOfTurn, false);
                    directions[3][1] = -1;
                    directions[3][2] = 0;
                    directions[4][0] = moveInThisDirection(matrix, x, y, 0, -1, numberOfTurn, false);
                    directions[4][1] = 0;
                    directions[4][2] = -1;
                    directions[5][0] = moveInThisDirection(matrix, x, y, -1, -1, numberOfTurn, false);
                    directions[5][1] = -1;
                    directions[5][2] = -1;
                    directions[6][0] = moveInThisDirection(matrix, x, y, 1, -1, numberOfTurn, false);
                    directions[6][1] = 1;
                    directions[6][2] = -1;
                    directions[7][0] = moveInThisDirection(matrix, x, y, -1, 1, numberOfTurn, false);
                    directions[7][1] = -1;
                    directions[7][2] = 1;
                    Move move = new Move(directions, x, y);
                    if (move.isMove()) {
                        move.setCount(flipsCount);
                        flipsCount = 0;
                        moves.put(x + "," + y, move);
                        listOfComputerMoves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    private boolean isBordersNear(int[][] matrix, int x, int y) {
        if (matrix[x][y] == 1 || matrix[x][y] == 0)
            return false;
        else if (matrix[x - 1][y] == 1 || matrix[x - 1][y] == 0)
            return true;
        else if (matrix[x][y - 1] == 1 || matrix[x][y - 1] == 0)
            return true;
        else if (matrix[x - 1][y - 1] == 1 || matrix[x - 1][y - 1] == 0)
            return true;
        else if (matrix[x + 1][y] == 1 || matrix[x + 1][y] == 0)
            return true;
        else if (matrix[x][y + 1] == 1 || matrix[x][y + 1] == 0)
            return true;
        else if (matrix[x + 1][y + 1] == 1 || matrix[x + 1][y + 1] == 0)
            return true;
        else if (matrix[x + 1][y - 1] == 1 || matrix[x + 1][y - 1] == 0)
            return true;
        else return matrix[x - 1][y + 1] == 1 || matrix[x - 1][y + 1] == 0;
    }

    private int moveInThisDirection(int[][] matrix, int x, int y, int xDifference, int yDifference, int numberOfTurn, boolean flip) {
        int nextTurn;
        Animation animation = new Animation();
        int positionOfX = x;
        int positionOfY = y;
        int counterFlipsOfCurrentDirection = 0;
        if (numberOfTurn == 0)
            nextTurn = 1;
        else
            nextTurn = 0;
        if (flip && this.matrix[x][y] != numberOfTurn) {
            this.matrix[x][y] = numberOfTurn;
            animation.animationOfFlip(positionOfX, positionOfY, numberOfTurn, gameBoard);
        }
        positionOfX += xDifference;
        positionOfY += yDifference;
        while (matrix[positionOfX][positionOfY] == nextTurn) {
            if (flip) {
                this.matrix[positionOfX][positionOfY] = numberOfTurn;
                animation.animationOfFlip(positionOfX, positionOfY, numberOfTurn, gameBoard);
            }
            counterFlipsOfCurrentDirection++;
            positionOfX += xDifference;
            positionOfY += yDifference;
        }
        if (matrix[positionOfX][positionOfY] == numberOfTurn && counterFlipsOfCurrentDirection > 0) {
            flipsCount += counterFlipsOfCurrentDirection;
            return 1;
        } else
            return 0;
    }

    private class HandlerOfGameThread implements ActionListener {
        private int currentRow, currentColumn;

        HandlerOfGameThread(int row, int column) {
            currentRow = row;
            currentColumn = column;
        }

        public void actionPerformed(ActionEvent e) {
            new Thread(() -> gameProcess(currentRow, currentColumn)).start();
        }
    }

    private class HandlerOfResetGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            structure.addStructureToGameBoard(gameBoard, matrix);
            findPossibleMoves();
        }
    }
}