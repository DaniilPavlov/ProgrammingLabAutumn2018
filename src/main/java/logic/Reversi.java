package main.java.logic;

import main.java.GUI.Animate;
import main.java.GUI.Scoreboard;
import main.java.GUI.Update;

import static main.java.logic.Reversi.Status.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static main.java.logic.Direction.*;

public class Reversi extends JPanel {
    private static final Logger logger = Logger.getLogger(Reversi.class.getName());
    private int flipsCount = 0;
    private int flagOfPossibilityMoving = 0;
    private Status turnStatus = PLAYER;
    private Move computerMove;
    private GameBoardStructure structure = new GameBoardStructure();
    private Scoreboard scoreboard;
    private ScoreAddition score = new ScoreAddition();
    private Update update = new Update();
    private HashMap<String, Move> possibleMoves;
    private List<Move> listOfComputerMoves = new ArrayList<>();
    static Status[][] matrix;
    private JButton[][] gameBoard;

    public enum Status {COMPUTER, PLAYER, VACANT, POSSIBLE_MOVE}

    public Reversi() {
        JPanel gameGrid = new JPanel();
        gameBoard = new JButton[10][10];
        matrix = new Status[10][10];
        addOurGameGrid(gameGrid);
        addNorthPanel();
        addSouthPanel();
        addStructure();
    }

    private void addOurGameGrid(JPanel gameGrid) {
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

    private void addNorthPanel() {
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout());
        add(north, BorderLayout.NORTH);
        scoreboard = new Scoreboard();
        north.add(scoreboard);
        north.setLayout(new FlowLayout());
    }

    private void addSouthPanel() {
        JPanel south = new JPanel();
        add(south, BorderLayout.SOUTH);
        JButton reset = new JButton();
        reset.addActionListener(new HandlerOfResetGame());
        reset.setText("      Reset      ");
        south.add(reset, BorderLayout.SOUTH);
    }

    private void findPossibleMoves() {
        possibleMoves = checkAllDirectionsForMoves();
        for (int x = 1; x < matrix.length - 1; x++)
            for (int y = 1; y < matrix[x].length - 1; y++)
                if (possibleMoves.containsKey(x + "," + y))
                    matrix[x][y] = POSSIBLE_MOVE;
        update.updatingOfGameBoard(matrix, gameBoard, possibleMoves);
        score.currentScore(matrix, scoreboard);
        scoreboard.changingVisualOfTurn(turnStatus);
    }

    private void oneTurn(int currentRow, int currentColumn) {
        if (turnStatus == PLAYER) {
            enableGameBoard();
            logger.fine("Player turn");
        } else {
            logger.fine("Computer turn");
        }
        if (!possibleMoves.isEmpty()) {
            computerTurn(currentRow, currentColumn);
        } else {
            flagOfPossibilityMoving++;
            if (turnStatus == PLAYER)
                logger.fine("Player can't move");
            else
                logger.fine("Computer can't move");
        }
        turnStatus = swapTurn();
        clearFromPossibleMoves();
        findPossibleMoves();
        if (isEndGame())
            return;
        if ((turnStatus == COMPUTER || possibleMoves.isEmpty()) && (!listOfComputerMoves.isEmpty())) {
            enableGameBoard();
            computerMove = ComputerDecision.makeDecision(listOfComputerMoves);
            new Thread(() -> oneTurn(computerMove.xMoveDecision, computerMove.yMoveDecision)).start();
        }
    }

    private void enableGameBoard() {
        for (int x = 1; x < gameBoard.length - 1; x++)
            for (int y = 1; y < gameBoard[x].length - 1; y++)
                gameBoard[x][y].setEnabled(false);
    }

    private void computerTurn(int currentRow, int currentColumn) {
        flagOfPossibilityMoving = 0;
        List<Direction> directions = possibleMoves.get(currentRow + "," + currentColumn).getDirections();
        for (int i = 0; i < directions.size(); i++) {
            int[] moveDirection = Direction.detectionOfDirectionToDelta(directions.get(i));
            moveInThisDirection(matrix, currentRow, currentColumn, moveDirection[0], moveDirection[1], true);
        }
    }

    private void clearFromPossibleMoves() {
        for (int x = 1; x < matrix.length - 1; x++)
            for (int y = 1; y < matrix[x].length - 1; y++)
                if (matrix[x][y] == POSSIBLE_MOVE)
                    matrix[x][y] = VACANT;
    }

    private boolean isEndGame() {
        String winner;
        if (flagOfPossibilityMoving == 2 || scoreboard.pointsOfPlayer == 0
                || scoreboard.pointsOfComputer == 0) {
            if (scoreboard.pointsOfPlayer > scoreboard.pointsOfComputer) {
                winner = "Player win!";
                JOptionPane.showMessageDialog(this, winner);
            } else {
                winner = "Computer win!";
                JOptionPane.showMessageDialog(this, winner);
            }
            return true;
        }
        return false;
    }

    private HashMap<String, Move> checkAllDirectionsForMoves() {
        HashMap<String, Move> currentMoves = new HashMap<>();
        listOfComputerMoves.clear();
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (matrix[x][y] != PLAYER && matrix[x][y] != COMPUTER) {
                    List<Direction> directions = new ArrayList<>();
                    flipsCount = 0;
                    if (moveInThisDirection(matrix, x, y, 1, 0, false))
                        directions.add(EAST);
                    if (moveInThisDirection(matrix, x, y, 0, 1, false))
                        directions.add(NORTH);
                    if (moveInThisDirection(matrix, x, y, 1, 1, false))
                        directions.add(NORTH_EAST);
                    if (moveInThisDirection(matrix, x, y, -1, 0, false))
                        directions.add(WEST);
                    if (moveInThisDirection(matrix, x, y, 0, -1, false))
                        directions.add(SOUTH);
                    if (moveInThisDirection(matrix, x, y, -1, -1, false))
                        directions.add(SOUTH_WEST);
                    if (moveInThisDirection(matrix, x, y, 1, -1, false))
                        directions.add(SOUTH_EAST);
                    if (moveInThisDirection(matrix, x, y, -1, 1, false))
                        directions.add(NORTH_WEST);
                    Move move = new Move(directions, x, y);
                    if (move.isMovesExist()) {
                        move.setCount(flipsCount);
                        flipsCount = 0;
                        currentMoves.put(x + "," + y, move);
                        listOfComputerMoves.add(move);
                    }
                }
            }
        }
        return currentMoves;
    }

    private Status swapTurn() {
        if (turnStatus == COMPUTER)
            return PLAYER;
        else
            return COMPUTER;
    }

    public boolean moveInThisDirection(Status[][] matrix, int x, int y, int xDifference, int yDifference, boolean flip) {
        int xPosition = x;
        int yPosition = y;
        int counterFlipsOfCurrentDirection = 0;
        Status nextTurn = swapTurn();
        if (flip && Reversi.matrix[x][y] != turnStatus) {
            doFlips(gameBoard[x][y], x, y);
        }
        xPosition += xDifference;
        yPosition += yDifference;
        while (matrix[xPosition][yPosition] == nextTurn) {
            if (flip) {
                doFlips(gameBoard[xPosition][yPosition], xPosition, yPosition);
            }
            counterFlipsOfCurrentDirection++;
            xPosition += xDifference;
            yPosition += yDifference;
        }
        if (matrix[xPosition][yPosition] == turnStatus && counterFlipsOfCurrentDirection > 0) {
            flipsCount += counterFlipsOfCurrentDirection;
            return true;
        } else
            return false;
    }

    private void doFlips(JButton gameBoard, int x, int y) {
        Animate animate = new Animate();
        Reversi.matrix[x][y] = turnStatus;
        animate.animationOfFlip(turnStatus, gameBoard);
    }

    private void addStructure() {
        structure.initializeGameBoard(gameBoard);
        findPossibleMoves();
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
            addStructure();
        }
    }
}