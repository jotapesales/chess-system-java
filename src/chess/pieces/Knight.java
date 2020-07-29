package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		int aux;
 		for(int i = -2;i <= 2; i++) {
			if(i != 0) {
				aux = (Math.abs(i) == 2) ? 1 : 2;
				int vetor[] = {1*aux,-1*aux};
				for(int j : vetor) {
					p.setValues(position.getRow()+i, position.getColumn()+j);
					if (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p))
						mat[p.getRow()][p.getColumn()] = true;
					if (isThereOpponentPiece(p))
						mat[p.getRow()][p.getColumn()] = true;
				}
			}
		}
		return mat;
	}
	
	public String toString() {
		return "L";
	}

}
