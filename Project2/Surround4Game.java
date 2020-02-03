package Project2;

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

	public int nextPlayer() {
		player = player + 1;
		if (player == numPlayers)
			player = 0;

//		player = (player + 1) % 2;
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
				}
		return -1;	
	}
}






