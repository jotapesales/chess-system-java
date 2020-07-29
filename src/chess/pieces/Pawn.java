package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		int i,direction = (getColor() == Color.BLACK) ? 1 : -1;
		int possibleHouses = (getMoveCount() == 0) ? 2 : 1;
		for (i = 1; i <= possibleHouses; i++) {
			p.setValues(position.getRow() + (i * direction), position.getColumn());
			if (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p))
				mat[p.getRow()][p.getColumn()] = true;
			else
				break;
		}
		for(i = 1; i > -2; i-=2) {
			p.setValues(position.getRow()+direction, position.getColumn()+(direction*i));
			if (getBoard().PositionExists(p) && isThereOpponentPiece(p))
				mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

	@Override 
	public String toString() {
		return "P";
	}
}
