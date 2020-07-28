package boardgame;
public class Board {
	private int rows, columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1)
			throw new BoardException("Error creating board: there must be at least 1 row.");
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}
	
	public Piece piece(int row, int column) {
		if(!PositionExists(row,column))
			throw new BoardException("Position not on the board.");
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		return piece(position.getRow(),position.getColumn());
	}
	
	public Piece PlacePiece(Piece piece, Position position) {
		Piece aux = null;
		if(ThereIsAPiece(position))
			aux = this.pieces[position.getRow()][position.getColumn()];
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		return aux;
	}
	
	private boolean PositionExists(int row, int column) {
		return (row < rows && column < columns)
				&&
				(row >= 0 && column >= 0);
	}
	
	private boolean PositionExists(Position position) {
		return PositionExists(position.getRow(),position.getColumn());
	}
	
	public boolean ThereIsAPiece(Position position) {
		if(!PositionExists(position))
			throw new BoardException("Position not on the board.");
		return (piece(position) != null);
	}
	
	public Piece RemovePiece(Position position){
		if(ThereIsAPiece(position)) {
			Piece aux = pieces[position.getRow()][position.getColumn()];
			aux.position = null;
			pieces[position.getRow()][position.getColumn()] = null;
			return aux;
		}
		else
			throw new BoardException("There is no piece in this position.");
	}
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
}
