package application;
import java.util.InputMismatchException;
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
		UI.printBoard(chessMatch.getPieces());
		System.out.println("Mover peça? y/n");
		String aux = "y";
		while(aux.charAt(0) != 'n') {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				
				System.out.println("Source:");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.PossibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println("Target:");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece cp = chessMatch.performChessMove(source,target);	
				System.out.println("Move again? y/n");
				aux = sc.nextLine();
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
	}

}
