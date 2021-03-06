package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		int i,j;
		for(i = 1; i > -2; i-=2) {
			for(j = 1; j > -2; j-=2) {
				p.setValues(position.getRow()+i, position.getColumn()+j);
				while(getBoard().PositionExists(p) &&  !getBoard().ThereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow()+i, p.getColumn()+j);
				}
				if(getBoard().PositionExists(p) && isThereOpponentPiece(p))
					mat[p.getRow()][p.getColumn()] = true;
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "B";
	}
}
