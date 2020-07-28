package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		Board board = getBoard();
		for(int i = -1;i<= 1;i++) {
			for(int j= -1; j<=1;j++) {
				p.setValues(position.getRow()+i, position.getColumn()+j);
				if (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p))
					mat[p.getRow()][p.getColumn()] = true;
				if (isThereOpponentPiece(p))
					mat[p.getRow()][p.getColumn()] = true;
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "K";
	}

}
