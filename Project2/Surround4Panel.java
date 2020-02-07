package Project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Surround4Panel extends JPanel {

    private JButton[][] board;
    private JButton undoButton;

    private JPanel panel1, panel2;
    private int boardSize, numPlayers, startingPlayer, player, lastRow, lastCol;
    private boolean undoStatus;
    private ButtonListener listen;
    private JMenuItem quitItem, newGameItem;
    private Surround4Game game;

    private JLabel[] scoreLabels;
    private int[] scores;


    public Surround4Panel(JMenuItem pQuitItem, JMenuItem pNewGameItem, JButton pUndoButton) {

        undoStatus = true;
        quitItem = pQuitItem;
        newGameItem = pNewGameItem;
        undoButton = pUndoButton;
        listen = new ButtonListener();

        setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel();

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
            if(numPlayers < 2) {
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

        game = new Surround4Game(boardSize,numPlayers,startingPlayer);
        createBoard();

        add(panel2, BorderLayout.NORTH);
        add(panel1, BorderLayout.CENTER);


        quitItem.addActionListener(listen);
        newGameItem.addActionListener(listen);
        undoButton.addActionListener(listen);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitItem)
                System.exit(1);

            if (e.getSource() == newGameItem) {
                game.reset();
                panel1.removeAll();
                panel2.removeAll();
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
                    if (numPlayers < 2) {
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
                game = new Surround4Game(boardSize,numPlayers,startingPlayer);
                createBoard();
                add(panel2, BorderLayout.NORTH);
                add(panel1, BorderLayout.CENTER);
                panel1.revalidate();
                panel2.revalidate();
                panel1.repaint();
                panel2.repaint();
            }

            if(e.getSource() == undoButton){
                if(undoStatus) {
                    game.undo(lastRow, lastCol);
                    game.previousPlayer();
                    board[lastRow][lastCol].setBackground(null);
                    undoStatus = false;
                }
                else
                    JOptionPane.showMessageDialog(null,"You already did undo this turn you dirty cheater.");
            }

            for (int row = 0; row < board.length; row++)
                for (int col = 0; col < board[0].length; col++)
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) {
                            //		board[row][col].setText(""+game.getCurrentPlayer());
                            lastRow = row;
                            lastCol = col;
                            undoStatus = true;
                            player = game.nextPlayer();
                        } else
                            JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");

            displayBoard();
            int winner = game.getWinner();
            if (winner != -1) {
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
                        board[row][col].setBackground(Color.blue);
                    }

                    if (risk == 1) {
                        board[row][col].setBackground(Color.green);
                    }

                    if (risk == 2) {
                        board[row][col].setBackground(Color.yellow);
                    }

                    if (risk == 3) {
                        board[row][col].setBackground(Color.red);
                    }
                } else {
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
            scoreLabels[i] = new JLabel("Player " + i + ": " + scores[i]);

        for(int i = 0; i < scoreLabels.length; i++)
            panel2.add(scoreLabels[i]);
    }

    private void displayScores(){
        for(int i = 0; i < scores.length; i++)
            scoreLabels[i].setText("Player " + i + ": " + scores[i]);
    }

}