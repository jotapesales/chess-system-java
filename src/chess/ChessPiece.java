package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	private Color color;
	private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreasMoveCount() {
		moveCount--;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	public Color getColor() {
		return color;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	protected boolean isThereOpponentPiece(Position position) {
		if(!getBoard().PositionExists(position)) return false;
		ChessPiece cp = (ChessPiece)getBoard().piece(position);
		return cp != null && cp.getColor( ) != color;
	}	
	


}
