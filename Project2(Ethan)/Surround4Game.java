package Project2;

import javax.swing.*;

public class Surround4Game {
	private Cell[][] board;
	private int player, boardSize, numPlayers;

	public Surround4Game() {
		super();
		board = new Cell[10][10];
		this.player = 1;
		boardSize = 10;
		numPlayers = 2;
	}

	public Surround4Game(int boardSize, int numPlayers, int player){
		this.boardSize = boardSize;
		this.numPlayers = numPlayers;
		this.player = player;
		board = new Cell[boardSize][boardSize];
	}

	public void reset() {
		for (int r = 0; r < boardSize; r++) {
			for (int c = 0; c < boardSize; c++) {
				board[r][c] = null;
			}
		}
	}

	public Cell getCell(int row, int col) {
		return board[row][col];
	}

	public void setCurrentPlayer(int p) { player = p; }

	public int getCurrentPlayer() {
		return player;
	}

	public void setNumPlayers(int np) { numPlayers = np; }

	public int getNumPlayers() { return numPlayers; }

	public int getBoardSize(){ return boardSize; }

	public int getRiskLevel() {

		int edge = boardSize - 1;

		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {

				// top left corner
				if (row == 0 && col == 0) {
					// right or bottom filled
					if ((board[0][1] != null && board[1][0] == null) ||
							(board[0][1] == null && board[1][0] != null)) {
						if (board[0][0].getPlayerNumber() != board[1][0].getPlayerNumber()
								&& board[0][0].getPlayerNumber() != board[0][1].getPlayerNumber()) {
							return 3;
						}
					}

					// neither are filled
					if ((board[0][1] == null && board[1][0] == null)) {
						return 0;
					}
				}

				// top right corner
				if (row == edge && col == 0) {
					if ((board[edge - 1][0] != null && board[edge][1] == null) ||
							(board[edge - 1][0] == null && board[edge][1] != null)) {
						if(board[edge][col].getPlayerNumber() != board[edge][col + 1].getPlayerNumber()
								&& board[edge][col].getPlayerNumber() != board[edge - 1][col].getPlayerNumber()) {
							return 3;
						}
					}

					// neither are filled
					if ((board[edge - 1][0] == null && board[edge][1] == null)) {
						return 0;
					}
				}

				// bottom left corner
				if (row == 0 && col == edge) {
					if ((board[0][edge - 1] != null && board[1][edge] == null) ||
							(board[0][edge - 1] == null && board[1][edge] != null)) {
						if(board[row][edge].getPlayerNumber() != board[row][edge - 1].getPlayerNumber()
								&& board[row][edge].getPlayerNumber() != board[row + 1][edge].getPlayerNumber()) {
							return 3;
						}
					}

					// neither are filled
					if ((board[0][edge - 1] == null && board[1][edge] == null)) {
						return 0;
					}
				}

				// bottom right corner
				if (row == edge && col == edge) {
					if ((board[edge][edge - 1] != null && board[edge - 1][edge] == null) ||
							(board[edge][edge - 1] == null && board[edge - 1][edge] != null)) {
						if(board[edge][edge].getPlayerNumber() != board[edge - 1][edge].getPlayerNumber()
								&& board[edge][edge].getPlayerNumber() != board[edge][edge - 1].getPlayerNumber()) {
							return 3;
						}
					}

					// neither are filled
					if ((board[edge][edge - 1] == null && board[edge - 1][edge] == null)) {
						return 0;
					}
				}

				// entire top row
				if (col == 0 && row != 0 && row != edge) {
					// left and bottom filled
					if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] == null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// right and bottom filled
					if (board[row - 1][col] == null && board[row][col + 1] != null && board[row + 1][col] != null) {
						if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// left and right filled
					if (board[row - 1][col] != null && board[row][col + 1] == null && board[row + 1][col] != null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// left filled only
					if (board[row - 1][col] != null && board[row][col + 1] == null && board[row + 1][col] == null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// bottom filled only
					if (board[row - 1][col] == null && board[row][col + 1] != null && board[row + 1][col] == null) {
						if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// right filled only
					if (board[row - 1][col] == null && board[row][col + 1] == null && board[row + 1][col] != null) {
						if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// none filled
					if (board[row - 1][col] == null && board[row][col + 1] == null && board[row + 1][col] == null) {
						return 0;
					}
				}

				// entire left column
				if (row == 0 && col != 0 && col != edge) {
					// top and right filled
					if (board[row][col - 1] != null && board[row + 1][col] != null && board[row][col + 1] == null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// right and bottom filled
					if (board[row][col - 1] == null && board[row + 1][col] != null && board[row][col + 1] != null) {
						if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// top and bottom filled
					if (board[row][col - 1] != null && board[row + 1][col] == null && board[row][col + 1] != null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// top filled only
					if (board[row][col - 1] != null && board[row + 1][col] == null && board[row][col + 1] == null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// right filled only
					if (board[row][col - 1] == null && board[row + 1][col] != null && board[row][col + 1] == null) {
						if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// bottom filled only
					if (board[row][col - 1] == null && board[row + 1][col] == null && board[row][col + 1] != null) {
						if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// none filled
					if (board[row][col - 1] == null && board[row + 1][col] == null && board[row][col + 1] == null) {
						return 0;
					}
				}

				// entire right column
				if (row == edge && col != 0 && col != edge) {
					// top and left filled
					if (board[row][col - 1] != null && board[row - 1][col] != null && board[row][col + 1] == null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// left and bottom filled
					if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] != null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// top and bottom filled
					if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] != null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// top filled only
					if (board[row][col - 1] != null && board[row - 1][col] == null && board[row][col + 1] == null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// left filled only
					if (board[row][col - 1] == null && board[row - 1][col] != null && board[row][col + 1] == null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// bottom filled only
					if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] != null) {
						if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// none filled
					if (board[row][col - 1] == null && board[row - 1][col] == null && board[row][col + 1] == null) {
						return 0;
					}
				}

				// entire bottom row
				if (col == edge && row != 0 && row != edge) {
					// left and top filled
					if (board[row -1][col] != null && board[row][col - 1] != null && board[row + 1][col] == null) {
						if (board[row -1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// top and right filled
					if (board[row -1][col] == null && board[row][col - 1] != null && board[row + 1][col] != null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// left and right filled
					if (board[row -1][col] != null && board[row][col - 1] == null && board[row + 1][col] != null) {
						if (board[row -1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// left filled only
					if (board[row -1][col] != null && board[row][col - 1] == null && board[row + 1][col] == null) {
						if (board[row -1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// top filled only
					if (board[row -1][col] == null && board[row][col - 1] != null && board[row + 1][col] == null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// right filled only
					if (board[row -1][col] == null && board[row][col - 1] == null && board[row + 1][col] != null) {
						if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// none filled
					if (board[row -1][col] == null && board[row][col - 1] == null && board[row + 1][col] == null) {
						return 0;
					}
				}

				// middle of the board (all spaces that are not corners or edges)
				if (row != 0 && row != edge && col != 0 && col != edge) {
					// left top right filled
					if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] != null
							&& board[row][col + 1] == null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// top right bottom filled
					if (board[row - 1][col] == null && board[row][col - 1] != null && board[row + 1][col] != null
							&& board[row][col + 1] != null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// right bottom left filled
					if (board[row - 1][col] != null && board[row][col - 1] == null && board[row + 1][col] != null
							&& board[row][col + 1] != null) {
						if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// bottom left top filled
					if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] == null
							&& board[row][col + 1] != null) {
						if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 3;
						}
					}

					// left and top filled
					if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] == null
							&& board[row][col + 1] == null) {
						if (board[row -1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// top and right filled
					if (board[row - 1][col] == null && board[row][col - 1] != null && board[row + 1][col] != null
							&& board[row][col + 1] == null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// right and bottom filled
					if (board[row - 1][col] == null && board[row][col - 1] == null && board[row + 1][col] != null
							&& board[row][col + 1] != null) {
						if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// bottom and left filled
					if (board[row - 1][col] != null && board[row][col - 1] == null && board[row + 1][col] == null
							&& board[row][col + 1] != null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// top and bottom filled
					if (board[row - 1][col] == null && board[row][col - 1] != null && board[row + 1][col] == null
							&& board[row][col + 1] != null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// left and right filled
					if (board[row - 1][col] != null && board[row][col - 1] == null && board[row + 1][col] != null
							&& board[row][col + 1] == null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber() &&
								board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 2;
						}
					}

					// left filled only
					if (board[row - 1][col] != null && board[row][col - 1] == null && board[row + 1][col] == null
							&& board[row][col + 1] == null) {
						if (board[row - 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 1;
						}
					}

					// top filled only
					if (board[row - 1][col] == null && board[row][col - 1] != null && board[row + 1][col] == null
							&& board[row][col + 1] == null) {
						if (board[row][col - 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 1;
						}
					}

					// right filled only
					if (board[row - 1][col] == null && board[row][col - 1] == null && board[row + 1][col] != null
							&& board[row][col + 1] == null) {
						if (board[row + 1][col].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 1;
						}
					}

					// bottom filled only
					if (board[row - 1][col] == null && board[row][col - 1] == null && board[row + 1][col] == null
							&& board[row][col + 1] != null) {
						if (board[row][col + 1].getPlayerNumber() != board[row][col].getPlayerNumber()) {
							return 1;
						}
					}

					// none filled
					if (board[row - 1][col] == null && board[row][col - 1] == null && board[row + 1][col] == null
							&& board[row][col + 1] == null) {
						return 0;
					}
				}
			}
		}
		return -1;
	}

	public int nextPlayer() {
		player = player + 1;
		if (player == numPlayers)
			player = 0;

//		player = (player + 1) % 2;
		return player;
	}

	public int previousPlayer(){
		player = player - 1;
		if(player == - 1)
			player = numPlayers - 1;
		return player;
	}

	public boolean select(int row, int col) {
		if (board[row][col] == null ) {  //|| (cats() && board[row][col].getPlayerNumber() != player)) {
			Cell c = new Cell(player);
			board[row][col] = c;
			return true;
		}
		else
			return false;
	}

	public void undo(int row, int col){
		board[row][col] = null;
	}

	public int getWinner() {

		//Gives the farthest row down and farthest col right
		int edge = boardSize - 1;

		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				if (board[row][col] != null) {
					// top-left corner case (check 2 sides only)
					if (row == 0 && col == 0)
						if (board[0][1] != null && board[1][0] != null)
							if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber()
									&& board[0][0].getPlayerNumber() != board[0][1].getPlayerNumber())
								return board[0][1].getPlayerNumber();

					// bottom-left corner case
					if(row == edge && col == 0)
						if(board[edge][1] != null && board[edge - 1][0] != null)
							if(board[edge][1].getPlayerNumber() == board[edge - 1][0].getPlayerNumber()
									&& board[edge][0].getPlayerNumber() != board[edge][1].getPlayerNumber())
								return board[edge][1].getPlayerNumber();

					// top-right corner case
					if(row == 0 && col == edge)
						if(board[0][edge - 1] != null && board[1][edge] != null)
							if(board[0][edge - 1].getPlayerNumber() == board[1][edge].getPlayerNumber()
									&& board[0][edge].getPlayerNumber() != board[0][edge - 1].getPlayerNumber())
								return board[0][edge - 1].getPlayerNumber();

					// bottom-right corner case
					if(row == edge && col == edge)
						if(board[edge][edge - 1] != null && board[edge - 1][edge] != null)
							if(board[edge][edge - 1].getPlayerNumber() == board[edge - 1][edge].getPlayerNumber()
									&& board[edge][edge].getPlayerNumber() != board[edge][edge - 1].getPlayerNumber())
								return board[edge][edge - 1].getPlayerNumber();

					// left-border case (excluding corners - check 3 sides only)
					if (row != 0 && row != edge && col == 0)
						if (board[row-1][col] != null && board[row][col+1] != null && board[row+1][col] != null)
							if (board[row-1][col].getPlayerNumber() == board[row][col+1].getPlayerNumber()
									&& board[row-1][col].getPlayerNumber() == board[row+1][col].getPlayerNumber()
									&& board[row-1][col].getPlayerNumber() != board[row][col].getPlayerNumber())
								return board[row-1][col].getPlayerNumber(); // just pick one of them

					// top-border case
					if(row == 0 && col != 0 && col != edge)
						if(board[row][col-1] != null && board[row+1][col] != null && board[row][col+1] != null)
							if(board[row][col-1].getPlayerNumber() == board[row+1][col].getPlayerNumber()
									&& board[row][col-1].getPlayerNumber() == board[row][col+1].getPlayerNumber()
									&& board[row][col-1].getPlayerNumber() != board[row][col].getPlayerNumber())
								return board[row][col-1].getPlayerNumber();

					// right-border case
					if(row != 0 && row != edge && col == edge)
						if(board[row-1][col] != null && board[row][col-1] != null && board[row+1][col] != null)
							if(board[row-1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber()
									&& board[row-1][col].getPlayerNumber() == board[row+1][col].getPlayerNumber()
									&& board[row-1][col].getPlayerNumber() != board[row][col].getPlayerNumber())
								return board[row-1][col].getPlayerNumber();

					//bottom-border case
					if(row == edge && col != 0 && col != edge)
						if(board[row][col-1] != null && board[row-1][col] != null && board[row][col+1] != null)
							if(board[row][col-1].getPlayerNumber() == board[row-1][col].getPlayerNumber()
									&& board[row][col-1].getPlayerNumber() == board[row][col+1].getPlayerNumber()
									&& board[row][col-1].getPlayerNumber() != board[row][col].getPlayerNumber())
								return board[row][col-1].getPlayerNumber();

					//Interior case
					if(row != 0 && row != edge && col != 0 && col != edge)
						if(board[row-1][col] != null && board[row][col+1] != null
								&& board[row+1][col] != null && board[row][col-1] != null)
							if(board[row-1][col].getPlayerNumber() == board[row][col+1].getPlayerNumber()
									&& board[row-1][col].getPlayerNumber() == board[row+1][col].getPlayerNumber()
									&& board[row-1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber()
									&& board[row-1][col].getPlayerNumber() != board[row][col].getPlayerNumber())
								return board[row-1][col].getPlayerNumber();
				}
		return -1;
	}

}