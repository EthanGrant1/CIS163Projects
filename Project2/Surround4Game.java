package Project2;

public class Surround4Game {
	private Cell[][] board;
	private int player;

	public Surround4Game() {
		//super();
		board = new Cell[10][10];		
		this.player = 1;
	}

	public void reset() {
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				board[r][c] = null;
			}
		}
	}

	public Cell getCell(int row, int col) {
		return board[row][col];
	}

	public int getCurrentPlayer() {
		return player;
	}

	public int nextPlayer() {
		//		player = player + 1;
		//		if (player == 2)
		//			player = 0;

		player = (player + 1) % 2;
		return player;
	}

	public boolean select(int row, int col) {
		if (board[row][col] == null ) {  //|| (cats() && board[row][col].getPlayeNumber() != player)) {
			Cell c = new Cell(player);
			board[row][col] = c;
			return true;
		}
		else 
			return false;
	}

	public int getWinner() {
		for (int row = 0; row < 10; row++) 
			for (int col = 0; col < 10; col++) 
				if (board[row][col] != null) {
					return player;
				}
		return -1;	
	}
}






