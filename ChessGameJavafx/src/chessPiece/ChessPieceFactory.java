package chessPiece;

import enumTypes.PieceTeam;
import enumTypes.PieceType;

public class ChessPieceFactory {

	public Piece makePiece(PieceType pieceType, PieceTeam team, int x, int y) {
		switch(pieceType) {
			case KING:
				return makeKing(team, x, y);
			case QUEEN:
				return makeQueen(team, x, y);
			case KNIGHT:
				return makeKnight(team, x, y);
			case BISHOP:
				return makeBishop(team, x, y);
			case ROOK:
				return makeRook(team, x, y);
			default:
				return makePawn(team, x, y);
		}
	}
	
    private Piece makeRook(PieceTeam type, int x, int y) {
        Piece piece = new Rook(type, x, y);
        return piece;
    }
    
    private Piece makePawn(PieceTeam type, int x, int y) {
        Piece piece = new Pawn(type, x, y);
        return piece;
    }
    
    private Piece makeKnight(PieceTeam type, int x, int y) {
        Piece piece = new Rook(type, x, y);
        return piece;
    }
    
    private Piece makeQueen(PieceTeam type, int x, int y) {
        Piece piece = new Rook(type, x, y);
        return piece;
    }
    
    private Piece makeBishop(PieceTeam type, int x, int y) {
        Piece piece = new Rook(type, x, y);
        return piece;
    }
    
    private Piece makeKing(PieceTeam type, int x, int y) {
        Piece piece = new Rook(type, x, y);
        return piece;
    }
}
