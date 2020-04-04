package chessPiece;

import chessGame.Board;
import enumTypes.MoveType;
import enumTypes.PieceTeam;
import javafx.scene.paint.Color;

public class King extends Piece{

	public King(PieceTeam type, int x, int y) {
		super(type, x, y, Color.DARKBLUE);
	}

	@Override
	public PieceMoveResult move(int newX, int newY, Board board) {
		final int oldX = (int)(this.getOldX() + 100 / 2) / 100,
				  oldY = (int)(this.getOldY() + 100 / 2) / 100;      
        
        final boolean isVerticle = newX == oldX && newY != oldY && Math.abs(newY - oldY) == 1,
					  isHorizontal = newX != oldX && newY == oldY && Math.abs(newX - oldX) == 1,
					  isDiagonal = Math.abs(newX - oldX) == Math.abs(newY - oldY) && Math.abs(newY - oldY) == 1,
					  isLegalMovement = (isVerticle || isHorizontal || isDiagonal),
					  isPieceThere = board.getTile(newX, newY).hasPiece(),
					  isAllyPieceThere = isPieceThere ? board.getTile(newX, newY).getPiece().getType() == this.getType() : false;

        if (isLegalMovement && !isAllyPieceThere) {
        	
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
