package Project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Surround4Panel extends JPanel {

    private JButton[][] board;
    private JButton undoButton;

    private JPanel panel1, panel2, panel3;
    private int boardSize, numPlayers, startingPlayer, player, lastRow, lastCol;
    private boolean undoStatus;
    private ButtonListener listen;
    private JMenuItem quitItem, newGameItem;
    private Surround4Game game;

    private JLabel[] scoreLabels;
    private JLabel timerLabel;
    private int[] scores;

    private Timer javaTimer;
    public TimerListener timer;


    public Surround4Panel(JMenuItem pQuitItem, JMenuItem pNewGameItem, JButton pUndoButton) {

        undoStatus = true;
        quitItem = pQuitItem;
        newGameItem = pNewGameItem;
        undoButton = pUndoButton;
        listen = new ButtonListener();

        setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        String strBdSize = JOptionPane.showInputDialog (null, "Enter in the size of the board: ");
        try {
            boardSize = Integer.parseInt(strBdSize);
            if(boardSize <= 3 || boardSize >= 20) {
                //Jumps to catch statement
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input. Using board of size 10.");
            boardSize = 10;
        }

        String strNumPlayers = JOptionPane.showInputDialog (null, "Enter the number of players: ");
        try {
            numPlayers = Integer.parseInt(strNumPlayers);
            if(numPlayers < 2 || numPlayers > 99) {
                //Jumps to catch statement
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input. Using 2 players.");
            numPlayers = 2;
        }

        String strStartingPlayer = JOptionPane.showInputDialog(null, "Who starts first?");
        try{
            startingPlayer = Integer.parseInt(strStartingPlayer);
            if(startingPlayer < 0 || startingPlayer > numPlayers - 1){
                //Jumps to catch statement
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Invalid input. Starting with Player 0.");
            startingPlayer = 0;
        }

        createScores();
        displayTimer();


        game = new Surround4Game(boardSize,numPlayers,startingPlayer);
        createBoard();


        add(panel2, BorderLayout.NORTH);
        add(panel1, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);


        quitItem.addActionListener(listen);
        newGameItem.addActionListener(listen);
        undoButton.addActionListener(listen);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitItem) {
                System.exit(1);
            }

            if (e.getSource() == newGameItem) {
                timer.stopTimer();
                game.reset();
                panel1.removeAll();
                panel2.removeAll();
                panel3.removeAll();

                String strBdSize = JOptionPane.showInputDialog(null, "Enter in the size of the board: ");
                try {
                    boardSize = Integer.parseInt(strBdSize);
                    if (boardSize <= 3 || boardSize >= 20) {
                        //Jumps to catch statement
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Using board of size 10.");
                    boardSize = 10;
                }

                String strNumPlayers = JOptionPane.showInputDialog(null, "Enter the number of players: ");
                try {
                    numPlayers = Integer.parseInt(strNumPlayers);
                    if (numPlayers < 2 || numPlayers > 99) {
                        //Jumps to catch statement
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Using 2 players.");
                    numPlayers = 2;
                }

                String strStartingPlayer = JOptionPane.showInputDialog(null, "Who starts first?");
                try {
                    startingPlayer = Integer.parseInt(strStartingPlayer);
                    if (startingPlayer < 0 || startingPlayer > numPlayers - 1) {
                        //Jumps to catch statement
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Starting with Player 0.");
                    startingPlayer = 0;
                }
                createScores();
                displayTimer();

                game = new Surround4Game(boardSize,numPlayers,startingPlayer);
                createBoard();
                add(panel2, BorderLayout.NORTH);
                add(panel1, BorderLayout.CENTER);
                add(panel3, BorderLayout.SOUTH);
                panel1.revalidate();
                panel2.revalidate();
                panel3.revalidate();
                panel1.repaint();
                panel2.repaint();
                panel3.repaint();
            }

            if(e.getSource() == undoButton) {
                if(undoStatus) {
                    game.undo(lastRow, lastCol);
                    game.previousPlayer();
                    board[lastRow][lastCol].setBackground(null);
                    undoStatus = false;
                }
                else {
                    timer.stopTimer();
                    JOptionPane.showMessageDialog(null, "You already did undo this turn you dirty cheater.");
                }
            }

            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == e.getSource()) {

                        if (game.select(row, col)) {
                            //		board[row][col].setText(""+game.getCurrentPlayer());
                            lastRow = row;
                            lastCol = col;
                            undoStatus = true;
                            player = game.nextPlayer();
                        }

                        else {
                            timer.stopTimer();
                            JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");
                        }
                    }
                }
            }

            displayBoard();
            timer.resetTimer();

            int winner = game.getWinner();
            if (winner != -1) {
                javaTimer.stop();
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                scores[winner] += 1;
                displayScores();
                game = new Surround4Game(boardSize, numPlayers, startingPlayer);
                for (int row = 0; row < boardSize; row++) {
                    for (int col = 0; col < boardSize; col++) {
                        board[row][col].setBackground(null);
                    }
                }
                displayBoard();
                timer.resetTimer();
            }
        }
    }

    private void createBoard() {

        board = new JButton[boardSize][boardSize];
        panel1.setLayout(new GridLayout(boardSize,boardSize));

        for (int i = 0; i < boardSize; i++) // rows
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new JButton("");
                board[i][j].addActionListener(listen);
                panel1.add(board[i][j]);
            }
    }

    private void displayBoard() {

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                Cell c = game.getCell(row, col);

                if (c != null) {
                    board[row][col].setText("" + c.getPlayerNumber());

                    game.evaluateRiskLevel();
                    int risk = c.getRiskLevel();

                    if (risk == 0) {
                        board[row][col].setBackground(null);
                    }

                    if (risk == 1) {
                        board[row][col].setBackground(Color.green);
                    }

                    if (risk == 2) {
                        board[row][col].setBackground(Color.yellow);
                    }

                    if (risk == 3) {
                        board[row][col].setBackground(new Color(255,204,255));
                    }
                }
                else {
                    board[row][col].setText("");
                }
            }
        }
    }

    private void createScores() {
        scoreLabels = new JLabel[numPlayers];
        scores = new int[numPlayers];

        for(int i = 0; i < scores.length; i++)
            scores[i] = 0;

        for(int i = 0; i < scoreLabels.length; i++)
            scoreLabels[i] = new JLabel("Player " + i + ":   " + scores[i] + "     ");

        for(int i = 0; i < scoreLabels.length; i++)
            panel2.add(scoreLabels[i]);
    }

    private void displayScores() {
        for(int i = 0; i < scores.length; i++)
            scoreLabels[i].setText("Player " + i + ":   " + scores[i] + "     ");
    }

    private void displayTimer() {
        timer = new TimerListener();
        timerLabel = new JLabel("");
        panel3.add(timerLabel);

        javaTimer = new Timer(1,timer);
        javaTimer.start();
    }

    private class TimerListener implements ActionListener {

        int sec = 9;
        int mil = 99;

        public void actionPerformed(ActionEvent e) {
            mil--;
            timerLabel.setText("Time:   " + sec + "." + mil);

            if (mil == 0) {
                sec -= 1;
                mil = 99;
            }

            if (sec == -1) {
                JOptionPane.showMessageDialog(null, "Time's up! Next player's turn.");
                player = game.nextPlayer();
                javaTimer.stop();
                sec = 9;
                mil = 99;
                timerLabel.setText("Time:   " + sec + "." + mil);
                javaTimer.start();
            }
        }

        public void resetTimer() {
            javaTimer.stop();
            sec = 9;
            mil = 99;
            timerLabel.setText("Time:   " + sec + "." + mil);
            javaTimer.start();
        }

        public void stopTimer() {
            javaTimer.stop();
            timerLabel.setText("Time:   " + "Stopped");
        }

    }
}