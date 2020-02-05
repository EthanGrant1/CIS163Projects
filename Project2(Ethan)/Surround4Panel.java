package Project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Surround4Panel extends JPanel {

    private JButton[][] board;
    private JButton undoButton;

    private JPanel panel1;
    private int boardSize, numPlayers, startingPlayer, player, lastRow, lastCol;
    private ButtonListener listen;
    private JMenuItem quitItem, newGameItem;
    private Surround4Game game;


    public Surround4Panel(JMenuItem pQuitItem, JMenuItem pNewGameItem) {

        quitItem = pQuitItem;
        newGameItem = pNewGameItem;
        listen = new ButtonListener();

        setLayout(new BorderLayout());
        panel1 = new JPanel();

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

        game = new Surround4Game(boardSize,numPlayers,startingPlayer);
        createBoard();

        add(panel1, BorderLayout.CENTER);

        quitItem.addActionListener(listen);
        newGameItem.addActionListener(listen);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitItem)
                System.exit(1);

            if (e.getSource() == newGameItem) {
                game.reset();
                panel1.removeAll();
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
                createBoard();
                add(panel1, BorderLayout.CENTER);
                panel1.revalidate();
                panel1.repaint();
            }

            if(e.getSource() == undoButton){
                game.undo(lastRow, lastCol);
                //FIX ME don't do previous player if undo was already used that turn
                game.previousPlayer();
            }

            for (int row = 0; row < board.length; row++)
                for (int col = 0; col < board[0].length; col++)
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) {
                            //		board[row][col].setText(""+game.getCurrentPlayer());
                            lastRow = row;
                            lastCol = col;
                            player = game.nextPlayer();
                        } else
                            JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");

            displayBoard();
            int winner = game.getWinner();
            if (winner != -1) {
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                game = new Surround4Game(boardSize, numPlayers, startingPlayer);
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

        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++) {

                game.evaluateRiskLevel();
                Cell c = game.getCell(row, col);
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

                if (c != null) {
                    board[row][col].setText("" + c.getPlayerNumber());
                }
                else {
                    board[row][col].setText("");
                }
            }
    }


}