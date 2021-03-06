package chessPieces;

import chessGame.Board;
import chessPieceComponents.PieceMoveResult;
import enumTypes.MoveType;
import enumTypes.PieceTeam;
import javafx.scene.paint.Color;

public class Knight extends Piece{

	public Knight(PieceTeam type, int x, int y) {
		super(type, x, y, Color.DARKORANGE);
	}

	@Override
	public PieceMoveResult move(int newX, int newY, Board board) {
		final int oldX = (int)(this.getOldX() + 100 / 2) / 100,
				  oldY = (int)(this.getOldY() + 100 / 2) / 100;   
        
	    final boolean isKnightLegal = Math.abs(newX - oldX) == 2 && Math.abs(newY - oldY) == 1 || 
	    						    	Math.abs(newX - oldX) == 1 && Math.abs(newY - oldY) == 2,
					  isPieceThere = board.getTile(newX, newY).hasPiece(),
					  isAllyPieceThere = isPieceThere ? board.getTile(newX, newY).getPiece().getType() == this.getType() : false;

        if (isKnightLegal && !isAllyPieceThere) {
        	
            final boolean isEnemyPieceThere = isPieceThere 
            		? board.getTile(newX, newY).getPiece().getType() != this.getType() 
            		: false;
            
        	return isEnemyPieceThere 
        			? new PieceMoveResult(MoveType.KILL, board.getTile(newX, newY).getPiece())
        			: new PieceMoveResult(MoveType.NORMAL);
        } else {
        	return new PieceMoveResult(MoveType.NONE);
        }
	}

}
