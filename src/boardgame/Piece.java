package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}
	
	protected Board getBoard() {
		return board;
	}
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		for(int i = 0; i < board.getRows();i++)
			for(int j = 0; j < board.getColumns(); j++) {
				if(possibleMove(new Position(i,j)))
					return true;
			}
		return false;
	}
}

