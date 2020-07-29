package chess;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public ChessMatch() {
		board = new Board(8,8);
		this.initialSetup();
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;
	}
	
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] chesspieces = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i = 0;i<board.getRows();i++)
			for(int j = 0;j<board.getColumns();j++)
				chesspieces[i][j] = (ChessPiece) board.piece(i,j);
		return chesspieces;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target =  targetPosition.toPosition();
		ValidateSourcePosition(source);
		ValidateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check.");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		nextTurn();
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.RemovePiece(source);
		Piece capturedPiece = board.RemovePiece(target);
		board.PlacePiece(p, target);
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.RemovePiece(target);
		board.PlacePiece(p, source);
		
		if(capturedPiece != null) {
			board.PlacePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	public boolean[][] PossibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		ValidateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	private List<Piece> piecesOfColor(Color color){
		List<Piece> list = new ArrayList<>();
		for(Piece p : piecesOnTheBoard) {
			if(((ChessPiece)p).getColor() == color)
				list.add(p);
		}
		return list;
	}
	private ChessPiece king(Color color) {
		for(Piece p : piecesOfColor(color)) {
			if(p instanceof King)
				return (ChessPiece)p;
		}
		throw new IllegalStateException("There is no "+ color + "king on the board.");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOfColor(opponent(color));
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) 
				return true;
		}
		return false;
	}
	
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private void ValidateSourcePosition(Position source) {
		if(!board.ThereIsAPiece(source))
			throw new ChessException("There is no piece on this source position.");
		Piece piece = board.piece(source);
		if(currentPlayer != ((ChessPiece) piece).getColor())
			throw new ChessException("The chosen piece is not yours.");
		if(!piece.isThereAnyPossibleMove())
			throw new ChessException("There is no any possible move.");
	}
	
	private void ValidateTargetPosition(Position source, Position target) {
		if(!this.board.piece(source).possibleMove(target))
			throw new ChessException("The chosen piece can't be moved to target.");
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition(column,row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	public boolean getCheck() {
		return check;
	}
}
