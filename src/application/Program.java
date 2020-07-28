package application;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChessMatch chessMatch = new ChessMatch();
		Scanner sc = new Scanner(System.in);
		UI.printBoard(chessMatch.getPieces());
		System.out.println("Mover peça? n");
		while(sc.nextLine() != "n") {
			chessMatch.performChessMove(UI.readChessPosition(sc), UI.readChessPosition(sc));
			UI.printBoard(chessMatch.getPieces());
			System.out.println("Mover peça? y/n");
		}
		sc.close();
	}

}
