package chessPiece;

import chessGame.Board;
import enumTypes.MoveType;
import enumTypes.PieceTeam;
import javafx.scene.paint.Color;

public class Pawn extends Piece{

	public Pawn(PieceTeam type, int x, int y) {
		super(type, x, y, Color.GRAY);
	}

	@Override
	public PieceMoveResult move(int newX, int newY, Board board) {

		final int oldX = (int)(this.getOldX() + 100 / 2) / 100,
        		  oldY = (int)(this.getOldY() + 100 / 2) / 100;      
        
        final boolean isValidNoneKillMove = newX == oldX && newY == oldY + this.getType().getMoveDir(),
        			  isPieceThere = board.getTile(newX, newY).hasPiece(),
        			  isEnemyThere = isPieceThere ? board.getTile(newX, newY).getPiece().getType() != this.getType() : false,
					  isKillMove = Math.abs(newX - oldX) == 1 && Math.abs(newY - oldY) == 1;

        if (isValidNoneKillMove && !isPieceThere) {
            return new PieceMoveResult(MoveType.NORMAL);
        } 
        else if(isKillMove && isEnemyThere) {
        	return new PieceMoveResult(MoveType.KILL, board.getTile(newX, newY).getPiece());
        }
        else {
        	return new PieceMoveResult(MoveType.NONE);
        }
	}
}
