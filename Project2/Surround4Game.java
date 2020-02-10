package Project2;

import javax.swing.*;

/**********************************************************
 * This is a class that creates a game
 * of Surround4. The objective of the game
 * is to pick a tile on a board and surround
 * the opponent with your tiles.
 *
 * @author Tim Nguyen and Etham Grant
 */

public class Surround4Game {

	/** A 2 dimensional array of Cell objects */
	private Cell[][] board;

	/** The number of current player, the size of the board,
	    and the number of players */
	private int player, boardSize, numPlayers;

	/** Determines whether or not the player is eliminated */
	private int[] playerStatus;

	/**********************************************************
	 * This method is the default constructor for Surround4Game
	 * that sets all variables to default.
	 */
	public Surround4Game() {
		super();

		//  Creates a new 2 dimensional array of Cells for
		// the board (10x10)
		board = new Cell[10][10];

		// Sets the current player to the first player
		this.player = 1;

		// Sets the size of the board to 10
		boardSize = 10;

		// Sets the number of players to 2
		numPlayers = 2;

		// Creates an array of integers which stores the status
		// of players
		playerStatus = new int[10];
		for(int i = 0; i < playerStatus.length; i++)
			playerStatus[i] = 1;
	}

	/**********************************************************
	 * This method allows the user to create a custom
	 * Surround4Game
	 *
	 * @param boardSize is the size of the 2D array of Cells
	 *                  (boardSize x boardSize)
	 * @param numPlayers is the number of players
	 * @param player is the current active player
	 */
	public Surround4Game(int boardSize, int numPlayers, int player){
		// Sets all instance variables equal to parameters
		this.boardSize = boardSize;
		this.numPlayers = numPlayers;
		this.player = player;

		// Creates the 2D array of Cell objects using boardSize
		board = new Cell[boardSize][boardSize];

		// Sets all player's status to "in the game"
		playerStatus = new int [numPlayers];
		for(int i = 0; i < playerStatus.length; i++)
			playerStatus[i] = 1;
	}

	/**********************************************************
	 * This method removes all player numbers from the board
	 */
	public void reset() {

		// A nested for loop sweeps over the entire board
		// and sets all tiles to null
		for (int r = 0; r < boardSize; r++) {
			for (int c = 0; c < boardSize; c++) {
				board[r][c] = null;
			}
		}
	}

	/**********************************************************
	 * This method is used to return the Cell object at the
	 * position (row , col) given the parameters
	 *
	 * @param row is the row of the Cell you want
	 * @param col is the column of the Cell you want
	 *
	 * @return the Cell object on the board
	 */
	public Cell getCell(int row, int col) {
		return board[row][col];
	}

	/**********************************************************
	 * This method sets the risk level of the tiles on the
	 * board depending on the number of tiles surrounding it
	 */
	public void evaluateRiskLevel() {

		// A variable used for the edge of the board
		int edge = boardSize - 1;

		// Nested for loops used to sweep the entire board
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {

				// Checks if the Cell at the given row and column
				// is not null
				if (board[row][col] != null) {

					// top left corner
					if (row == 0 && col == 0) {
						// right filled
						if ((board[0][1] != null && board[1][0] == null)) {
							if (board[row][col].getPlayerNumber() != board[0][1].getPlayerNumber()){
								board[row][col].setRiskLevel(3);
							}
						}

						//bottom filled
						if ((board[0][1] == null && board[1][0] != null)) {
							if (board[row][col].getPlayerNumber() != board[1][0].getPlayerNumber()){
								board[row][col].setRiskLevel(3);
							}
						}

						// neither are filled
						if ((board[0][1] == null && board[1][0] == null)) {
							board[row][col].setRiskLevel(0);
						}
					}

					// top right corner
					if (row == 0 && col == edge) {
						// left filled
						if ((board[0][edge - 1] != null && board[1][edge] == null)) {
							if (board[row][col].getPlayerNumber() != board[0][edge - 1].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// bottom filled
						if ((board[0][edge - 1] == null && board[1][edge] != null)) {
							if (board[row][col].getPlayerNumber() != board[1][edge].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// neither are filled
						if ((board[0][edge - 1] == null && board[1][edge] == null)) {
							board[row][col].setRiskLevel(0);
						}
					}

					// bottom left corner
					if (row == edge && col == 0) {
						// right filled
						if ((board[edge][1] != null && board[edge - 1][0] == null)) {
							if (board[edge][col].getPlayerNumber() != board[edge][1].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top filled
						if ((board[edge][1] == null && board[edge - 1][0] != null)) {
							if (board[edge][col].getPlayerNumber() != board[edge - 1][0].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// neither are filled
						if ((board[edge - 1][0] == null && board[edge][1] == null)) {
							board[row][col].setRiskLevel(0);
						}
					}

					// bottom right corner
					if (row == edge && col == edge) {
						// left filled
						if ((board[edge][edge - 1] != null && board[edge - 1][edge] == null)) {
							if (board[edge][edge].getPlayerNumber() != board[edge][edge - 1].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top filled
						if ((board[edge][edge - 1] == null && board[edge - 1][edge] != null)) {
							if (board[edge][edge].getPlayerNumber() != board[edge - 1][edge].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// neither are filled
						if ((board[edge][edge - 1] == null && board[edge - 1][edge] == null)) {
							board[row][col].setRiskLevel(0);
						}
					}

					// entire top row
					if (row == 0 && col != 0 && col != edge) {
						// left and bottom filled
						if (board[row][col - 1] != null && board[row + 1][col] != null && board[row][col + 1] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// right and bottom filled
						if (board[row][col - 1] == null && board[row + 1][col] != null && board[row][col + 1] != null) {
							if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// left and right filled
						if (board[row][col - 1] != null && board[row + 1][col] == null && board[row][col + 1] != null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// left filled only
						if (board[row][col - 1] != null && board[row + 1][col] == null && board[row][col + 1] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// bottom filled only
						if (board[row][col - 1] == null && board[row + 1][col] != null && board[row][col + 1] == null) {
							if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// right filled only
						if (board[row][col - 1] == null && board[row + 1][col] == null && board[row][col + 1] != null) {
							if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// none filled
						if (board[row][col - 1] == null && board[row + 1][col] == null && board[row][col + 1] == null) {
							board[row][col].setRiskLevel(0);
						}
					}

					// entire left column
					if (col == 0 && row != 0 && row != edge) {
						// top and right filled
						if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] == null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// right and bottom filled
						if (board[row - 1][col] == null && board[row][col + 1] != null && board[row + 1][col] != null) {
							if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top and bottom filled
						if (board[row - 1][col] != null && board[row][col + 1] == null && board[row + 1][col] != null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top filled only
						if (board[row - 1][col] != null && board[row][col + 1] == null && board[row + 1][col] == null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// right filled only
						if (board[row - 1][col] == null && board[row][col + 1] != null && board[row + 1][col] == null) {
							if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// bottom filled only
						if (board[row - 1][col] == null && board[row][col + 1] == null && board[row + 1][col] != null) {
							if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// none filled
						if (board[row - 1][col] == null && board[row][col + 1] == null && board[row + 1][col] == null) {
							board[row][col].setRiskLevel(0);
						}
					}

					// entire right column
					if (col == edge && row != 0 && row != edge) {
						// top and left filled
						if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] == null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// left and bottom filled
						if (board[row - 1][col] == null && board[row][col - 1] != null && board[row + 1][col] != null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top and bottom filled
						if (board[row - 1][col] != null && board[row][col - 1] == null && board[row + 1][col] != null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top filled only
						if (board[row - 1][col] != null && board[row][col - 1] == null && board[row + 1][col] == null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// left filled only
						if (board[row - 1][col] == null && board[row][col - 1] != null && board[row + 1][col] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// bottom filled only
						if (board[row - 1][col] == null && board[row][col - 1] == null && board[row + 1][col] != null) {
							if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// none filled
						if (board[row - 1][col] == null && board[row][col - 1] == null && board[row + 1][col] == null) {
							board[row][col].setRiskLevel(0);
						}
					}

					// entire bottom row
					if (row == edge && col != 0 && col != edge) {
						// left and top filled
						if (board[row][col - 1] != null && board[row - 1][col] != null && board[row][col + 1] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top and right filled
						if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] != null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// left and right filled
						if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] != null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// left filled only
						if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// top filled only
						if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] == null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// right filled only
						if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] != null) {
							if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// none filled
						if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] == null) {
							board[row][col].setRiskLevel(0);
						}
					}

					// middle of the board (all spaces that are not corners or edges)
					if (row != 0 && row != edge && col != 0 && col != edge) {
						// left top right filled
						if (board[row][col - 1] != null && board[row - 1][col] != null && board[row][col + 1] != null
								&& board[row + 1][col] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// top right bottom filled
						if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] != null
								&& board[row + 1][col] != null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// right left bottom filled
						if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] != null
								&& board[row + 1][col] != null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// bottom left top filled
						if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] != null
								&& board[row + 1][col] != null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(3);
							}
						}

						// left and top filled
						if (board[row][col - 1] != null && board[row - 1][col] != null && board[row][col + 1] == null
								&& board[row + 1][col] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// top and right filled
						if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] != null
								&& board[row + 1][col] == null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// right and bottom filled
						if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] != null
								&& board[row + 1][col] != null) {
							if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// bottom and left filled
						if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] == null
								&& board[row + 1][col] != null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// top and bottom filled
						if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] == null
								&& board[row + 1][col] != null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// left and right filled
						if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] != null
								&& board[row + 1][col] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
									board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(2);
							}
						}

						// left filled only
						if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] == null
								&& board[row + 1][col] == null) {
							if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(1);
							}
						}

						// top filled only
						if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] == null
								&& board[row + 1][col] == null) {
							if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(1);
							}
						}

						// right filled only
						if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] != null
								&& board[row + 1][col] == null) {
							if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(1);
							}
						}

						// bottom filled only
						if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] == null
								&& board[row + 1][col] != null) {
							if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
								board[row][col].setRiskLevel(1);
							}
						}

						// none filled
						if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] == null
								&& board[row + 1][col] == null) {
							board[row][col].setRiskLevel(0);
						}
					}
				}
			}
		}
	}

	/**********************************************************
	 * This method advances the player number to the next one
	 *
	 * @return the new player number
	 */
	public int nextPlayer() {
		//Skips to the other player ahead if the very next player is eliminated
		do {
			player = player + 1;
			if (player == numPlayers)
				player = 0;
		}while(playerStatus[player] == -1);
		return player;
	}

	/**********************************************************
	 * This method decrements the player number to the previous
	 * one
	 *
	 * @return the new player number
	 */
	public int previousPlayer(){
		//Skips to the other player behind if the very last player is eliminated
		do {
			player = player - 1;
			if (player == -1)
				player = numPlayers - 1;
		}while(playerStatus[player] == -1);
		return player;
	}

	/**********************************************************
	 * This method selects and creates a new Cell object using
	 * the current player's number
	 *
	 * @param row is the row of the Cell you want to fill
	 * @param col is the column of the Cell you want to fill
	 *
	 * @return will be true if the Cell is not null. Will be
	 * false otherwise
	 */
	public boolean select(int row, int col) {

		// If the Cell is not null, the action of filling the
		// Cell is carried out
		if (board[row][col] == null ) {  //|| (cats() && board[row][col].getPlayerNumber() != player)) {
			Cell c = new Cell(player);
			board[row][col] = c;
			return true;
		}
		// Will return false if the Cell is currently null
		else
			return false;
	}

	/**********************************************************
	 * This method will undo the previous action taken, and set
	 * that Cell back to null
	 *
	 * @param row is the row of the Cell to undo
	 * @param col is the column of the Cell to undo
	 */
	public void undo(int row, int col){

		// Sets the Cell at the given row and column back to null
		board[row][col] = null;
	}

	/**********************************************************
	 * This method eliminates and takes a player out of the game
	 *
	 * @param playerNumber is the player to eliminate
	 */
	public void eliminatePlayer(int playerNumber){

		// Sets the status of the player to "not in the game"
		playerStatus[playerNumber] = -1;
	}

	/**********************************************************
	 * This method sets all of the eliminated player's spaces
	 * to null
	 */
	public void eliminatePlayerSpace(){

		// Sweeps the entire array of player statuses
		for(int i = 0; i < playerStatus.length; i++)
			if (playerStatus[i] == -1)

				// Sweeps the entire board of Cells
				for (int row = 0; row < boardSize; row++)
					for (int col = 0; col < boardSize; col++)

						// If there are Cells that contain the
						// eliminated player's number, they are
						// set back to null
						if(board[row][col] != null
								&& board[row][col].getPlayerNumber() == i)
							board[row][col] = null;
	}

	/**********************************************************
	 * This method returns the number of the player that won
	 * the game
	 *
	 * @return the number of the winning player
	 */
	public int getWinner(){

		// Counts the number of players that are still in the game
		int countPlayersStillIn = 0;
		for(int i = 0; i < playerStatus.length; i++)
			if(playerStatus[i] == 1)
				countPlayersStillIn++;

		//If there is only one player left, return their number
		if(countPlayersStillIn == 1)
			for(int i = 0; i < playerStatus.length; i++)
				if(playerStatus[i] == 1)
					return i;
		return -1;
	}

	/**********************************************************
	 * This method evaluates the board and checks if there are
	 * any players that need to be eliminated from the game
	 *
	 * @return the number of the player that needs elimination
	 */
	public int getEliminated() {

		//Gives the farthest row down and farthest col right
		int edge = boardSize - 1;

		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				if (board[row][col] != null) {
					// top-left corner case (check 2 sides only)
					if (row == 0 && col == 0)
						if (board[0][1] != null && board[1][0] != null)
							if (board[0][0].getPlayerNumber() != board[1][0].getPlayerNumber()
									&& board[0][0].getPlayerNumber() != board[0][1].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					// bottom-left corner case
					if(row == edge && col == 0)
						if(board[edge][1] != null && board[edge - 1][0] != null)
							if(board[edge][0].getPlayerNumber() != board[edge - 1][0].getPlayerNumber()
									&& board[edge][0].getPlayerNumber() != board[edge][1].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					// top-right corner case
					if(row == 0 && col == edge)
						if(board[0][edge - 1] != null && board[1][edge] != null)
							if(board[0][edge].getPlayerNumber() != board[1][edge].getPlayerNumber()
									&& board[0][edge].getPlayerNumber() != board[0][edge - 1].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					// bottom-right corner case
					if(row == edge && col == edge)
						if(board[edge][edge - 1] != null && board[edge - 1][edge] != null)
							if(board[edge][edge].getPlayerNumber() != board[edge - 1][edge].getPlayerNumber()
									&& board[edge][edge].getPlayerNumber() != board[edge][edge - 1].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					// left-border case (excluding corners - check 3 sides only)
					if (row != 0 && row != edge && col == 0)
						if (board[row-1][col] != null && board[row][col+1] != null && board[row+1][col] != null)
							if (board[row][col].getPlayerNumber() != board[row][col+1].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row-1][col].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					// top-border case
					if(row == 0 && col != 0 && col != edge)
						if(board[row][col-1] != null && board[row+1][col] != null && board[row][col+1] != null)
							if(board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row][col+1].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row][col-1].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					// right-border case
					if(row != 0 && row != edge && col == edge)
						if(board[row-1][col] != null && board[row][col-1] != null && board[row+1][col] != null)
							if(board[row][col].getPlayerNumber() != board[row][col-1].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row-1][col].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					//bottom-border case
					if(row == edge && col != 0 && col != edge)
						if(board[row][col-1] != null && board[row-1][col] != null && board[row][col+1] != null)
							if(board[row][col].getPlayerNumber() != board[row-1][col].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row][col+1].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row][col-1].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}

					//Interior case
					if(row != 0 && row != edge && col != 0 && col != edge)
						if(board[row-1][col] != null && board[row][col+1] != null
								&& board[row+1][col] != null && board[row][col-1] != null)
							if(board[row][col].getPlayerNumber() != board[row][col+1].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row][col-1].getPlayerNumber()
									&& board[row][col].getPlayerNumber() != board[row-1][col].getPlayerNumber()) {
								eliminatePlayer(board[row][col].getPlayerNumber());
								return board[row][col].getPlayerNumber();
							}
				}
		return -1;
	}

}