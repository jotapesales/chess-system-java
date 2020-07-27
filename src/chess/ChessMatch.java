package chess;
import boardgame.*;
public class ChessMatch {
	private int turn;
	private Board board;
	public ChessMatch() {
		board = new Board(8,8);
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] chesspieces = new ChessPiece[board.getRows()][board.getColumns()];
		int i=0,j=0;
		for(;i<board.getRows();i++)
			for(;j<board.getColumns();j++)
				chesspieces[i][j] = (ChessPiece) board.piece(i,j);
		return chesspieces;
	}

}
