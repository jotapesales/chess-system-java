package application;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChessMatch chessMatch = new ChessMatch();
		Scanner sc = new Scanner(System.in);
		String aux = "y";
		List<ChessPiece> captured = new ArrayList<>();
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				
				System.out.println("Source:");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.PossibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println("Target:");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece cp = chessMatch.performChessMove(source,target);	
				
				if(cp != null)
					captured.add(cp);
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		sc.close();
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		System.out.println("CHECKMATE!");
	}

}
