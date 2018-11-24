package main.java.logic;

import main.java.GUI.Animate;
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

import static main.java.logic.StatusOfCell.Status.*;
import static main.java.logic.DirectionOfMoving.Direction.*;

public class Reversi extends JPanel {
    private int flipsCount = 0;
    private int flagOfPossibilityMoving = 0;
    private Enum turnStatus = PLAYER;
    private DirectionOfMoving direction = new DirectionOfMoving();
    private Move computerMove;
    private GameBoardStructure structure = new GameBoardStructure();
    ;
    private Scoreboard scoreboard;
    private ScoreAddition score = new ScoreAddition();
    private Update update = new Update();
    private HashMap<String, Move> moves;
    private ArrayList<Move> listOfComputerMoves = new ArrayList<>();
    static Enum[][] matrix;
    private JButton[][] gameBoard;

    public Reversi() {
        JPanel gameGrid = new JPanel();
        gameBoard = new JButton[10][10];
        matrix = new Enum[10][10];
        ourGameGrid(gameGrid);
        northPanel();
        southPanel();
        structure.addStructureToGameBoard(gameBoard, matrix);
        findPossibleMoves();
    }

    private void ourGameGrid(JPanel gameGrid) {
        setLayout(new BorderLayout());
        gameGrid.setLayout(new GridLayout(8, 8));
        add(gameGrid, BorderLayout.CENTER);
        for (int row = 1; row < gameBoard.length - 1; row++) {
            for (int column = 1; column < gameBoard[row].length - 1; column++) {
                gameBoard[row][column] = new JButton();
                gameBoard[row][column].setBackground(Color.blue.darker());
                gameBoard[row][column].addActionListener(new HandlerOfGameThread(row, column));
                gameGrid.add(gameBoard[row][column]);
            }
        }
    }

    private void northPanel() {
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout());
        add(north, BorderLayout.NORTH);
        scoreboard = new Scoreboard();
        north.add(scoreboard);
        north.setLayout(new FlowLayout());
    }

    private void southPanel() {
        JPanel south = new JPanel();
        add(south, BorderLayout.SOUTH);
        JButton reset = new JButton();
        reset.addActionListener(new HandlerOfResetGame());
        reset.setText("      Reset      ");
        south.add(reset, BorderLayout.SOUTH);
    }

    private void findPossibleMoves() {
        moves = searchForMoves(turnStatus);
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (moves.get(x + "," + y) != null) {
                    matrix[x][y] = POSSIBLE_MOVE;
                }
            }
        }
        update.updatingOfGameBoard(matrix, gameBoard, moves);
        score.currentScore(matrix, scoreboard);
        scoreboard.changingVisualOfTurn(turnStatus);
    }

    private void oneTurn(int currentRow, int currentColumn) {
        if (turnStatus == PLAYER) {
            for (int x = 1; x < gameBoard.length - 1; x++) {
                for (int y = 1; y < gameBoard[x].length - 1; y++) {
                    gameBoard[x][y].setEnabled(false);
                }
            }
            System.out.println("Player turn");
        } else {
            System.out.println("Computer turn");
        }
        if (!moves.isEmpty()) {
            flagOfPossibilityMoving = 0;
            Enum[][] directions = moves.get(currentRow + "," + currentColumn).getDirections();
            for (int i = 0; i < directions.length; i++) {
                if (directions[i][0] == EXIST) {
                    int[] moveDirection = direction.detectionOfDirection(directions[i][1]);
                    moveInThisDirection(matrix, currentRow, currentColumn, moveDirection[0], moveDirection[1],
                            turnStatus, true);
                }
            }
        } else {
            flagOfPossibilityMoving++;
            if (turnStatus == PLAYER)
                System.out.println("Player can't move");
            else
                System.out.println("Computer can't move");
        }
        if (turnStatus == PLAYER)
            turnStatus = COMPUTER;
        else
            turnStatus = PLAYER;
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (matrix[x][y] == POSSIBLE_MOVE)
                    matrix[x][y] = VACANT;
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
        if (turnStatus == COMPUTER || moves.isEmpty()) {
            for (int x = 1; x < gameBoard.length - 1; x++) {
                for (int y = 1; y < gameBoard[x].length - 1; y++) {
                    gameBoard[x][y].setEnabled(false);
                }
            }
            computerMove = ComputerDecision.choiceOfComputer(listOfComputerMoves);
            new Thread(() -> oneTurn(computerMove.xOption, computerMove.yOption)).start();
        }
    }

    private HashMap<String, Move> searchForMoves(Enum turnStatus) {
        HashMap<String, Move> moves = new HashMap<>();
        listOfComputerMoves.clear();
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (isBordersNear(matrix, x, y)) {
                    Enum[][] directions = new Enum[8][3];
                    flipsCount = 0;
                    directions[0][0] = moveInThisDirection(matrix, x, y, 1, 0, turnStatus, false);
                    directions[0][1] = EAST;
                    directions[1][0] = moveInThisDirection(matrix, x, y, 0, 1, turnStatus, false);
                    directions[1][1] = NORTH;
                    directions[2][0] = moveInThisDirection(matrix, x, y, 1, 1, turnStatus, false);
                    directions[2][1] = NORTH_EAST;
                    directions[3][0] = moveInThisDirection(matrix, x, y, -1, 0, turnStatus, false);
                    directions[3][1] = WEST;
                    directions[4][0] = moveInThisDirection(matrix, x, y, 0, -1, turnStatus, false);
                    directions[4][1] = SOUTH;
                    directions[5][0] = moveInThisDirection(matrix, x, y, -1, -1, turnStatus, false);
                    directions[5][1] = SOUTH_WEST;
                    directions[6][0] = moveInThisDirection(matrix, x, y, 1, -1, turnStatus, false);
                    directions[6][1] = SOUTH_EAST;
                    directions[7][0] = moveInThisDirection(matrix, x, y, -1, 1, turnStatus, false);
                    directions[7][1] = NORTH_WEST;
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

    public boolean isBordersNear(Enum[][] matrix, int x, int y) {
        if (matrix[x][y] == PLAYER || matrix[x][y] == COMPUTER)
            return false;
        else if (matrix[x - 1][y] == PLAYER || matrix[x - 1][y] == COMPUTER)
            return true;
        else if (matrix[x][y - 1] == PLAYER || matrix[x][y - 1] == COMPUTER)
            return true;
        else if (matrix[x - 1][y - 1] == PLAYER || matrix[x - 1][y - 1] == COMPUTER)
            return true;
        else if (matrix[x + 1][y] == PLAYER || matrix[x + 1][y] == COMPUTER)
            return true;
        else if (matrix[x][y + 1] == PLAYER || matrix[x][y + 1] == COMPUTER)
            return true;
        else if (matrix[x + 1][y + 1] == PLAYER || matrix[x + 1][y + 1] == COMPUTER)
            return true;
        else if (matrix[x + 1][y - 1] == PLAYER || matrix[x + 1][y - 1] == COMPUTER)
            return true;
        else return matrix[x - 1][y + 1] == PLAYER || matrix[x - 1][y + 1] == COMPUTER;
    }

    public Enum moveInThisDirection(Enum[][] matrix, int x, int y, int xDifference, int yDifference, Enum turnStatus, boolean flip) {
        Enum nextTurn;
        Animate animate = new Animate();
        int xPosition = x;
        int yPosition = y;
        int counterFlipsOfCurrentDirection = 0;
        if (turnStatus == COMPUTER)
            nextTurn = PLAYER;
        else
            nextTurn = COMPUTER;
        if (flip && this.matrix[x][y] != turnStatus) {
            this.matrix[x][y] = turnStatus;
            animate.animationOfFlip(xPosition, yPosition, turnStatus, gameBoard);
        }
        xPosition += xDifference;
        yPosition += yDifference;
        while (matrix[xPosition][yPosition] == nextTurn) {
            if (flip) {
                this.matrix[xPosition][yPosition] = turnStatus;
                animate.animationOfFlip(xPosition, yPosition, turnStatus, gameBoard);
            }
            counterFlipsOfCurrentDirection++;
            xPosition += xDifference;
            yPosition += yDifference;
        }
        if (matrix[xPosition][yPosition] == turnStatus && counterFlipsOfCurrentDirection > 0) {
            flipsCount += counterFlipsOfCurrentDirection;
            return EXIST;
        } else
            return NOT_EXIST;
    }

    private class HandlerOfGameThread implements ActionListener {
        private int currentRow, currentColumn;

        HandlerOfGameThread(int row, int column) {
            currentRow = row;
            currentColumn = column;
        }

        public void actionPerformed(ActionEvent e) {
            new Thread(() -> oneTurn(currentRow, currentColumn)).start();
        }
    }

    private class HandlerOfResetGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            structure.addStructureToGameBoard(gameBoard, matrix);
            findPossibleMoves();
        }
    }
}