package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static ChessPosition readChessPosition(Scanner sc){
		try {
			String s = sc.nextLine();
			return new ChessPosition(s.charAt(0),Integer.parseInt(s.substring(1)));
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Values a1 to k8.");
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++)
				printPiece(pieces[i][j]);
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++)
				printPiece(pieces[i][j],possibleMoves[i][j]);
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: "+chessMatch.getTurn());
		System.out.println("Waiting player:.. "+chessMatch.getCurrentPlayer());
		if(chessMatch.getCheck())
			System.out.println("CHECK!");
	}
	private static void printPiece(ChessPiece piece, boolean background) {
		if(!background)
			printPiece(piece);
		else {
			if (piece == null)
				System.out.print(ANSI_RED+"-"+ANSI_RESET);
			else {
				if (piece.getColor() == Color.WHITE)
					System.out.print(ANSI_RED + piece + ANSI_RESET);
				else
					System.out.print(ANSI_RED + piece + ANSI_RESET);
			}
			System.out.print(" ");
		}
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = new ArrayList<>();
		List<ChessPiece> black = new ArrayList<>();
		for(ChessPiece cp : captured) {
			((cp.getColor() == Color.WHITE) ? white : black).add(cp);
		}
		System.out.print("Captured pieces: \nWhite:");
		System.out.println(ANSI_WHITE+Arrays.toString(white.toArray())+ANSI_RESET);
		System.out.print("Black:");
		System.out.print(ANSI_YELLOW+Arrays.toString(black.toArray())+ANSI_RESET);
	}
}
